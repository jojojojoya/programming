package com.sj.store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class DAO_Store {
	
	static Connection con = null;
	
	

	public static void FindStore(HttpServletRequest request) {
		// 1. 값 받기 or db 세팅
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from restaurant_test";
				
		try {
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			ArrayList<Store> stores = new ArrayList<Store>();
			Store ss = null;
			
			// 값 뭉치기 no, name, age -> data object -> 하나의 멤버, bean
			
			
			while (rs.next()) {
				int no = rs.getInt(1);
				String name = rs.getString(2);
				String menu = rs.getString(3);
				String place = rs.getString(4);
				
				ss = new Store(no,name,menu,place);
				stores.add(ss);
				
			}
			System.out.println(stores);
			request.setAttribute("stores", stores);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(null, pstmt, rs);
		}
		
		
		
	}

	public static void SetStore(HttpServletRequest request) {
		
		String name = request.getParameter("name");
		String menu = request.getParameter("menu");
		String place = request.getParameter("place");
		
		PreparedStatement pstmt = null;
		String sql = "insert into restaurant_test values(res_test_seq.nextval,?,?,?)";
		
		try {
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, menu);
			pstmt.setString(3, place);
			
			if (pstmt.executeUpdate() ==1 ) {
				System.out.println("업데이트 성공!");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(null, pstmt, null);
		}
		
	}

	public static void DelStore(HttpServletRequest request) {
		//값 or db 
		String num = request.getParameter("num");
		
		PreparedStatement pstmt = null;
		String sql = "delete restaurant_test where r_no=?)";
		
		try {
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, num);
			
			if (pstmt.executeUpdate() ==1 ) {
				System.out.println("삭제 성공!");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(null, pstmt, null);
		}
		
	}

	
	
	
	public static void UpdStore(HttpServletRequest request) {
		
		String val = request.getParameter("val");
		String no = request.getParameter("no");
		
		PreparedStatement pstmt = null;
		String sql = "update restaurant_test"
				+ " set r_name=?"
				+ " where r_no=?";
		
		
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, val);
			pstmt.setString(2, no);
			
			if (pstmt.executeUpdate() ==1 ) {
				System.out.println("업뎃 성공!");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(null, pstmt, null);
		}
		
	}}


