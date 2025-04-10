package com.mz.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import oracle.net.aso.m;

public class MemberDao {
	
	static Connection con = null;
	
	public static ArrayList<MemberVO> selectAllMember() {
		//값 받기 or db 세팅
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member_test";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<MemberVO> members = new ArrayList<MemberVO>();
			MemberVO member = null;
			
			while (rs.next()) {
				member = new MemberVO();
				member.setM_no(rs.getInt(1));
				member.setM_name(rs.getString(2));
				member.setM_age(rs.getInt(3));
				members.add(member);
			}
			
			System.out.println(members);
			return members;
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBManager.close(con, pstmt, rs);
		}
		return null;
	}

	public static void insertAllMember(MemberVO memberVO) {
		
		//값 받기 or db 세팅
		PreparedStatement pstmt = null;
		String sql = "insert into member_test values(member_test_seq.nextval,?,?)";
				
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,memberVO.getM_name());
			pstmt.setInt(2,memberVO.getM_age());
			
			
			
		if (pstmt.executeUpdate()==1) {
			System.out.println("성공");
		}
			
		

			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBManager.close(con, pstmt, null);
		}

	}




public static void DeleteMember(MemberVO memberVO) {
	
	//값 받기 or db 세팅
	PreparedStatement pstmt = null;
	String sql = "delete member_test where m_no=?";
//	String no = request.getParameter("no");
	
	
	try {
		con = DBManager.connect();
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,memberVO.getM_no());
		
		
		
	if (pstmt.executeUpdate()==1) {
		System.out.println("성공");
	}
		
	

		
	} catch (Exception e) {
		// TODO: handle exception
	}finally {
		DBManager.close(con, pstmt, null);
	}

}


public static void UpdateMember(MemberVO memberVO) {
	
	//값 받기 or db 세팅
	PreparedStatement pstmt = null;
	String sql = "update member_test set m_name=? where m_no=?";
	
	
	try {
		con = DBManager.connect();
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1,memberVO.getM_name());
		pstmt.setInt(2,memberVO.getM_no());
		
		
		
	if (pstmt.executeUpdate()==1) {
		System.out.println("성공");
	}
		
	

		
	} catch (Exception e) {
		// TODO: handle exception
	}finally {
		DBManager.close(con, pstmt, null);
	}

}}

