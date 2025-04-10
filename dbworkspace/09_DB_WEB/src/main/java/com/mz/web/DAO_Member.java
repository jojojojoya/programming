package com.mz.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

// model.	database access object
public class DAO_Member {

	public static void getAllMember(HttpServletRequest request) {
		// 1. 값 받기 or db 세팅
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from member_test";
		
		try {
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();	
			
			ArrayList<MemberDTO> members = new ArrayList<MemberDTO>();
			MemberDTO mDTO = null;
			
			while(rs.next()) {
				int no =  rs.getInt(1);
				String name = rs.getString(2);
				int age = rs.getInt(3);
				// 값 뭉치기 no, name, age -> data object -> 하나의 멤버, bean
				
				mDTO = new MemberDTO(no, name, age);
				members.add(mDTO);
			}
			
			System.out.println(members);
			request.setAttribute("members", members);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public static void regMember(HttpServletRequest request) {
		// 1. 값 받기 or db 세팅
		
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into member_test values(member_test_seq.nextval, ?, ?)";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, age);
			
			if(pstmt.executeUpdate() == 1) {
				System.out.println("등록 성공");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
		
	}

	public static void deleteMember(HttpServletRequest request) {
		// 1. 값 받기 or db 세팅
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "delete member_test where m_no = ?";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("no"));
			
			if(pstmt.executeUpdate() == 1) {
				System.out.println("삭제 성공");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
		
		
		
	}

}
