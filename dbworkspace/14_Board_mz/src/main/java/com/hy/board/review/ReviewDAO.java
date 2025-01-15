package com.hy.board.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;


import com.hy.board.main.DBManager;
import com.hy.board.movie.MovieDTO;

public class ReviewDAO {

	
		static ArrayList<ReviewDTO> reviews = null;
		public static final ReviewDAO RDAO = new ReviewDAO();
		private static Connection con = null;
		
		public ReviewDAO() {
			// TODO Auto-generated constructor stub
		}
		
	public static ReviewDAO getRdao() {
		return RDAO;
	}
		
		public static void dbCon() {
			try {
				if (con == null) {				
					con = DBManager.connect();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public  void listingRv(HttpServletRequest request) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "select * from review_test";
			
			ReviewDTO review = null;
			
			reviews = new ArrayList<ReviewDTO>();
			try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					review = new ReviewDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4));
					reviews.add(review);
				}
//				request.setAttribute("reviews", reviews);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBManager.close(null, pstmt, rs);
			}
		}

		public  void addReview(HttpServletRequest request) {
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
			}}}
		
	
		
		
//		public void paging(int pageNum, HttpServletRequest request) {
//	        request.setAttribute("curPageNum", pageNum);
//
//
//	        int total = reviews.size(); //총데이터수
//	        int cnt = 3; //한페이지당보여줄개수
//
//	        // 페이지 수 == 마지막 페이지 번호
//	        int pageCount =(int) Math.ceil((double)total/ cnt); //총페이지수
//	        //System.out.println(pageCount); //페이지 개수(총페이지수)
//	        request.setAttribute("pageCount", pageCount);
//
//	        //int pageNum = 1; //페이지번호
//
//	        //시작,끝
//	        int start = total - (cnt * (pageNum - 1));
//	        int end = (pageNum == pageCount) ? -1 : start - (cnt + 1);
//
//	        ArrayList<ReviewDTO> items = new ArrayList<>();
//
//
//	        for (int i = start - 1; i > end; i--) {
//	            items.add(reviews.get(i));
//	        }
//
//	        // For each 로 뿌리기
//	        request.setAttribute("reviews", items);
//
//	    }
//	}



