package com.ce.board.movie;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.ce.board.main.DBManager;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

// static -> 프로그램을 실행하는 순간 메모리에 static 메서드가 모두 할당되어버림 ==> 즉, 코드를 짤 때 static을 많이 쓰는것은 좋지 않음.(메모리 낭비)
// but, 임시적으로 단위적인 test를 수행할 때에는 static 메서드를 활용하는것이 좋음.

public class MovieDAO {

	private static final MovieDAO MDAO = new MovieDAO(); // 상수
	
	// 상수는 재정의가 안되기 때문에 getter 메서드만 생성가능.
	public static MovieDAO getMdao() {
		return MDAO;
	}
	
	private MovieDAO() { // private를 선언함으로써 다른클래스에서 new MovieDAO()로 객체를 새로 생성할 수 없음.
		// TODO Auto-generated constructor stub
	}

	private static Connection con = null;
	private ArrayList<MovieDTO> ms;
	
	
	public void showAllMovie(HttpServletRequest request) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from movie_test";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			MovieDTO m = null;
			ms = new ArrayList<MovieDTO>();
			
			while (rs.next()) {
				m = new MovieDTO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
				ms.add(m);
			}

			request.setAttribute("movies", ms);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public void addMovie(HttpServletRequest request) {
		// 파일 저장 경로 (로컬/서버)
//		String path = "파일경로";
		String path = request.getServletContext().getRealPath("jsp/movie/img");
		
		PreparedStatement pstmt = null;
		
		String sql = "insert into movie_test values(movie_test_seq.nextval,?,?,?,?)";
		
		try {
			// 업로드 기능
			MultipartRequest mr = new MultipartRequest(request, path, 1024*1024*20, "utf-8", new DefaultFileRenamePolicy());
			con = DBManager.connect();
			String title = mr.getParameter("title");
			String actor = mr.getParameter("actor");
			String file = mr.getFilesystemName("file");
			String story = mr.getParameter("story").replace("\r\n","<br>");
			
			System.out.println(title+","+actor+","+file+","+story);
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, actor);
			pstmt.setString(3, file);
			pstmt.setString(4, story);
			
			if (pstmt.executeUpdate() == 1) {
				System.out.println("등록성공");
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}

	public void deleteMovie(HttpServletRequest request) {
		String no = request.getParameter("no");
		
		PreparedStatement pstmt = null;
		
		String sql = "delete movie_test where m_no = ?";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, no);
			if (pstmt.executeUpdate() == 1) {
				System.out.println("삭제 성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
	}

	public void showMovieDetail(HttpServletRequest request) {
		String no = request.getParameter("no");
		System.out.println(no);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from movie_test where m_no = ?";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				MovieDTO movie = new MovieDTO();
				movie.setNo(rs.getInt(1));
				movie.setTitle(rs.getString(2));
				movie.setActor(rs.getString(3));
				movie.setImg(rs.getString(4));
				movie.setStory(rs.getString(5));
				String story2 = rs.getString(5).replace("<br>","\r\n");
				request.setAttribute("story2", story2);
				request.setAttribute("movie", movie);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public void updateMovie1(HttpServletRequest request) {
		String title = request.getParameter("title");
		String actor = request.getParameter("actor");
		String story = request.getParameter("story").replace("\r\n", "<br>");
		
		String no = request.getParameter("no");
		
		System.out.println(title);
		System.out.println(actor);
		System.out.println(story);
		System.out.println(no);
		
		PreparedStatement pstmt = null;
		
		String sql = "update movie_test set m_title = ?, m_actor = ?, ";
		
		if (story != null) {
			sql += "m_story = ? ";
		}
		
		sql += "where m_no = ?";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, actor);
			if(story != null) {
				pstmt.setString(3, story);
				pstmt.setString(4, no);
			}else {
				pstmt.setString(3, no);
			}
			
			if (pstmt.executeUpdate() == 1) {
				System.out.println("수정성공");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} {
			DBManager.close(con, pstmt, null);
		}
	}

	// 사진까지 교체가능한 수정 기능.
	public void updateMovie2(HttpServletRequest request) {
		// 파일 저장 경로 (로컬/서버)
//		String path = "파일경로";
		String path = request.getServletContext().getRealPath("jsp/movie/img");
		
		PreparedStatement pstmt = null;
		
		try {
			// 업로드 기능
			MultipartRequest mr = new MultipartRequest(request, path, 1024*1024*20, "utf-8", new DefaultFileRenamePolicy());
			con = DBManager.connect();
			String title = mr.getParameter("title");
			String actor = mr.getParameter("actor");
			String newImg = mr.getFilesystemName("newImg");
			String oldImg = mr.getParameter("oldImg");
			String story = mr.getParameter("story").replace("\r\n", "<br>");
			
			String no = mr.getParameter("no");
			request.setAttribute("no", no);
			
			String sql = "update movie_test set m_title = ?, m_actor = ?, m_story = ?, m_img = ? where m_no = ?";

			String img = oldImg;
			if(newImg != null) {
				img = newImg;
			}
			
			System.out.println(title+","+actor+","+newImg+","+oldImg+","+story);
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, actor);
			pstmt.setString(3, story);
			pstmt.setString(4, img);
			pstmt.setString(5, no);
			
			if (pstmt.executeUpdate() == 1) {
				System.out.println("등록성공");
				// 데이터 수정이 완료되었을 경우 기존에 저장되어 있던 사진데이터는 삭제
				if(newImg != null) {					
					File f = new File(path+"/"+oldImg);
					f.delete();
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}

	public void paging(int pageNum, HttpServletRequest request) {
		request.setAttribute("curPageNum", pageNum);
		int total = ms.size(); // 총데이터수
		int count = 3; // 한페이지당보여줄개수
		
		// 페이지 수
		int pageCount = (int)Math.ceil((double)total / count); // 페이지 개수 (총페이지수)
		request.setAttribute("pageCount", pageCount);
		System.out.println(pageCount);
				
		// 시작, 끝
		int start = total - (count * (pageNum - 1)); 
		int end = (pageNum == pageCount) ? -1 : start - (count + 1);
		
		ArrayList<MovieDTO> items = new ArrayList<MovieDTO>();
		
		for (int i = start - 1; i > end; i--) {
			items.add(ms.get(i));
			System.out.println(i);
		}
		
		request.setAttribute("movies", items);

	}
	
}
