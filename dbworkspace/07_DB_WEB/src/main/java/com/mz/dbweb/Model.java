package com.mz.dbweb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.valves.rewrite.Substitution.StaticElement;

public class Model {

	
		public static void getAllPeople(HttpServletRequest request)
		{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from name_age_test";
		
		try {
			con = DBManager.connect();
			System.out.println("연결 성공!");
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			
			
			ArrayList<Human> humans = new ArrayList<Human>();
			Human h = null;
			String name = null;
			int age = 0;
			
			while (rs.next()) {
				name = rs.getString(1);
				age = rs.getInt("n_age");
				// 한 바퀴의 name, age 곧 한 사람 => bean으로 뭉치자 (객체)
				h = new Human(name,age);
				humans.add(h);
				
				
			}
			
			request.setAttribute("humans", humans);
				
				
				
				
				
		
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				rs.close();
				pstmt.close();
				con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		}
		
		
		public static void regPeople(HttpServletRequest request) {
			
			//1. 값받기 or db 세팅
			String name = request.getParameter("name");
			String age = request.getParameter("age");
		
		Connection con = null;
		PreparedStatement pstmt = null; 
		String sql = "insert into name_age_test values(?,?)";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			//sql 미완성 상태, 물음표 채우기
			pstmt.setString(1,name);
			pstmt.setString(2,age);
			
			if (pstmt.executeUpdate()==1) {
				System.out.println("등록 성공");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con,pstmt,null);
			}
		}
		}
	
