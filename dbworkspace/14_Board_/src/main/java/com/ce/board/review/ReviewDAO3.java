package com.ce.board.review;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ce.board.main.DBManager;

public class ReviewDAO3 {
	
	public static final ReviewDAO3 RDAO = new ReviewDAO3();
	//싱글톤 > 내부에서 인스턴스를 생성 
	private Connection con = null;
	
	private ReviewDAO3() {
	try {
		con = DBManager.connect();
		
	} catch (Exception e) {
		
		
	}}
	
	ArrayList<ReviewDTO> reviews = null;
	
	public void showAllReview(HttpServletRequest request) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from review_test order by r_date";
		
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			reviews = new ArrayList<ReviewDTO>();
			ReviewDTO review = null;
			
			while (rs.next()) {
				review = new ReviewDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4));
				reviews.add(review);
			}
			request.setAttribute("reviews", reviews);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(null, pstmt, rs);
		}
	}

	public void addReview(HttpServletRequest request) {
		String title = request.getParameter("title");
		String txt = request.getParameter("txt");
		
		PreparedStatement pstmt = null;
		
		String sql = "insert into review_test values (review_test_seq.nextval, ?, ?, sysdate)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, txt);
			if (pstmt.executeUpdate() == 1) {
				System.out.println("등록성공");
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(null, pstmt, null);
		}
	}

	public void getReviewDetail(HttpServletRequest request) {
		String no = request.getParameter("no");
		// 파라미터를 먼저 넘겨줌. 
		// pk 넘버를 가지고 리뷰를 보여줌 
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from review_test where r_no = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ReviewDTO review = new ReviewDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4));
				request.setAttribute("review", review);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(null, pstmt, rs);
		}
	}

	public void deleteReview(HttpServletRequest request) {
		String no = request.getParameter("no");
		// delete, insert는  pstmt 만 필요
		
		PreparedStatement pstmt = null;
		
		String sql = "delete review_test where r_no = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, no);
			if(pstmt.executeUpdate() == 1) {
				System.out.println("삭제성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(null, pstmt, null);
		}
	}
	


	public void updReview(HttpServletRequest request) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {request.setCharacterEncoding("utf-8");
		String no = request.getParameter("no");
		String title = request.getParameter("title");
		String txt = request.getParameter("txt");
		// 파라미터를 먼저 넘겨줌. 
		// pk 넘버를 가지고 리뷰를 보여줌 
		
		
		String sql = "update review_test set r_title =? , r_txt =? where r_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, txt);
			pstmt.setString(3, no);
			if(pstmt.executeUpdate() ==1 ) {
				System.out.println("업데이트 성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(null, pstmt, rs);
		}
	}
		
	



public void paging(int pageNum, HttpServletRequest request) {
    request.setAttribute("curPageNum", pageNum);


	int total = reviews.size(); //총데이터수
    int cnt = 3; //한페이지당보여줄개수

   // 페이지 수 == 마지막 페이지 번호
    int pageCount =(int) Math.ceil((double)total/ cnt); //총페이지수
    //System.out.println(pageCount); //페이지 개수(총페이지수)
    request.setAttribute("pageCount", pageCount);

    //int pageNum = 1; //페이지번호

    //시작,끝
    int start = total - (cnt * (pageNum - 1));
    int end = (pageNum == pageCount) ? -1 : start - (cnt + 1);

    ArrayList<ReviewDTO> items = new ArrayList<>();
    
    for (int i = start - 1; i > end; i--) {
        items.add(reviews.get(i));
    }
    // For each 로 뿌리기
   request.setAttribute("reviews", items);
}

public void searchReview(HttpServletRequest request, HttpServletResponse response) {

	//db 세팅 or 값받기 
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "select * from review_test where r_title like '%'||?||'%'";
			
	try {
		
		con = DBManager.connect();
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, request.getParameter("reviewTitle"));
		
		rs =  pstmt.executeQuery();
		ArrayList<String> reviews = new ArrayList<String>();
		ReviewDTO r = null;
		
		while (rs.next()) {
			r = new ReviewDTO();
			r.setNo(rs.getInt(1));
			r.setTitle(rs.getString(2));
			r.setTxt(rs.getString(3));
			r.setDate(rs.getDate(4));
			reviews.add(r.toJsonByMe());
		}
		System.out.println(reviews);
		System.out.println(reviews.size());
		
		//lib -> Gson
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(reviews);
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		DBManager.close(null, pstmt, rs);
	}
	
	
	
}}




