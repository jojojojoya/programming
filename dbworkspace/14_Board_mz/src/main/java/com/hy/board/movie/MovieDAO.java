package com.hy.board.movie;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.hy.board.main.DBManager;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MovieDAO {
	
	private static final MovieDAO MDAO = new MovieDAO();
	private static Connection con = null;
	private ArrayList<MovieDTO> movies;

	
	private MovieDAO() {
		
	}
	
	public static MovieDAO getMdao() {
		return MDAO;
	}

	public  void showAllMovie(HttpServletRequest request) {

		// DB 세팅
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from movie_test";
		
		try {
			
			if (con == null) {
				con = DBManager.connect();
			}
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			MovieDTO movie = null;
			
			movies = new ArrayList<MovieDTO>();
			
			while (rs.next()) {
				// 객체 뭉치기
				movie = new MovieDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				movies.add(movie);
			}
			System.out.println(movies);
			request.setAttribute("movies", movies);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(null, pstmt, rs);
		}
		
		
		
	}

	public  void addMovie(HttpServletRequest request) {
		// 값 받기 or DB 세팅
		
		// String title = request.getParameter("title");
		// System.out.println(title);
		
		// 파일 저장 경로 ( 로컬 or 서버상 경로)
		// String path = "c:/desktop/";
		String path = request.getServletContext().getRealPath("jsp/movie/movieFile");
		System.out.println(path);
		
		// DB 세팅
		
		PreparedStatement pstmt = null;
		
		// 업로드 기능. 		
		try {
			
			if (con == null) {
				con = DBManager.connect();
			}
			
			MultipartRequest mr = new MultipartRequest(request, path, 1024 * 1024 * 20, "utf-8",
													new DefaultFileRenamePolicy());
		
			String title = mr.getParameter("title");
			String actor = mr.getParameter("actor");
			String story = mr.getParameter("story");
			// 서버에 올라간 파일명
			String file = mr.getFilesystemName("file");
		
			System.out.println(title);
			System.out.println(actor);
			System.out.println(story);
			
			story = story.replaceAll("\r\n", "<br>");
			
					
			System.out.println(file);
		
			String sql = "insert into movie_test values(movie_test_seq.nextval, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, actor);
			pstmt.setString(3, file);
			pstmt.setString(4, story);
		
			if (pstmt.executeUpdate() == 1) {
				System.out.println("등록 성공");
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(null, pstmt, null);
		}
		
		
	}

	public  void deleteMovie(HttpServletRequest request) {
		
		String no = request.getParameter("no");
		
		PreparedStatement pstmt = null;
		
		String sql = "delete movie_test where m_no=?";
		
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, no);
			
			if(pstmt.executeUpdate() == 1) {
				System.out.println("삭제 성공");
			}
			
		} catch (Exception e) {
		} finally {
			DBManager.close(null, pstmt, null);
		}
		
		
	}

	public  void getMovie(HttpServletRequest request) {

		String no = request.getParameter("no");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from movie_test where m_no=?";
		
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
					
			if(rs.next()) {
				MovieDTO movie = new MovieDTO();
				movie.setM_no(rs.getInt(1));
				movie.setM_title(rs.getString(2));
				movie.setM_actor(rs.getString(3));
				movie.setM_img(rs.getString(4));
				String story2 = rs.getString(5).replaceAll("<br>", "\r\n");
				request.setAttribute("story2", story2);
				
				
				movie.setM_story(rs.getString(5));
				request.setAttribute("movie", movie);
			}
			
			
		} catch (Exception e) {
		
		} finally {
			DBManager.close(null, pstmt, rs);
		}
		
		
		
	}

	public void updateMovie(HttpServletRequest request) {

		String no = request.getParameter("no");
		String title = request.getParameter("title");
		String actor = request.getParameter("actor");
		String story = request.getParameter("story");
		System.out.println(title);
		System.out.println(actor);
		System.out.println(story);
		story = story.replaceAll("\r\n", "<br>");
		String sql = "update movie_test set m_title=?, m_actor=?";
		if (story != null) {
			sql += ",m_story=?";
		}
		sql += "where m_no=?";
		PreparedStatement pstmt = null;
			
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, actor);
			if (story != null) {
				pstmt.setString(3, story);
				pstmt.setString(4, no);
			} else {
				pstmt.setString(3, no);
				
			}
			
			if(pstmt.executeUpdate() == 1) {
				System.out.println("수정 성공");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(null, pstmt, null);
		}
		
	}

	public void updateMovie2(HttpServletRequest request) {
		//사진까지 교체가능한 수정 기능.
	String path = request.getServletContext().getRealPath("jsp/movie/movieFile");
	
		// DB 세팅
		PreparedStatement pstmt = null;
		
		// 업로드 기능. 		
		try {
			
			if (con == null) {
				con = DBManager.connect();
			}
			
			MultipartRequest mr = new MultipartRequest(request, path, 1024 * 1024 * 20, "utf-8",
													new DefaultFileRenamePolicy());
		
			String title = mr.getParameter("title");
			String actor = mr.getParameter("actor");
			String story = mr.getParameter("story");
			
			// 서버에 올라간 파일명
			String no = mr.getParameter("no");
			String newImg = mr.getFilesystemName("newImg");
			String oldImg = mr.getParameter("oldImg");
		
			System.out.println(no);
			System.out.println(title);
			System.out.println(actor);
			System.out.println(story);
			System.out.println(newImg);
			System.out.println(oldImg);
			 String sql = "update movie_test set m_title=?, m_actor=?, m_story=?, m_img=? where m_no=?";
				
			
			String img = oldImg;
			if (newImg != null) {
				img = newImg; 
			}

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, actor);
			pstmt.setString(3, story);
			pstmt.setString(4, img);
			pstmt.setString(5, no);
		
			if (pstmt.executeUpdate() == 1) {
				System.out.println("등록 성공");
				if (newImg != null) {
					File f = new File(path+ "/" + oldImg);
					f.delete();
				}
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(null, pstmt, null);
		}
		
		
	}

	public void paging(int pageNum, HttpServletRequest request) {
		request.setAttribute("curPageNum", pageNum);
		
		int total = movies.size(); // 총데이터수
		int cnt = 3; //한페이지당보여줄개수 
		
		// 페이지 수 
		int pageCount = (int)Math.ceil((double)total / cnt);
		request.setAttribute("pageCount", pageCount);
		
		System.out.println(pageCount);
		
		// 페이지 번호 
		
		
		//  시작, 끝
		int start = total - (cnt * (pageNum - 1));
		
		int end = (pageNum == pageCount) ? -1 : start - (cnt +1 );
		
		ArrayList<MovieDTO> items = new ArrayList<MovieDTO>();		
		for (int i = start -1 ; i > end; i--) {
			items.add(movies.get(i));
			System.out.println(i);
			
		}
		
		request.setAttribute("movies", items);
		
	
	}
	

	
	
	
}
