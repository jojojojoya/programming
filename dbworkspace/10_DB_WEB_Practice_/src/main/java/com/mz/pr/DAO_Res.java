package com.mz.pr;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class DAO_Res {
	
	private static Connection con = null;
	

	public static void selectRestaurants(HttpServletRequest request) {
		// 1. �� or db

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from restaurant_test";
		try {
			if(con == null) {
				con = DBManager.connect();
			}
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<ResDTO> restaurants = new ArrayList<ResDTO>();
			ResDTO r = null;
			while (rs.next()) {
				r = new ResDTO();
				r.setNo(rs.getInt(1));
				r.setName(rs.getString(2));
				r.setMenu(rs.getString(3));
				r.setPlace(rs.getString(4));
				restaurants.add(r);
			}
			System.out.println(restaurants);
			request.setAttribute("restaurants", restaurants);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(null, pstmt, rs);
		}

	}

	public static void addRestaurant(HttpServletRequest request) throws UnsupportedEncodingException {
		// ���ޱ�, db
		request.setCharacterEncoding("utf-8");

		String name = request.getParameter("n");
		String menu = request.getParameter("m");
		String place = request.getParameter("p");

		PreparedStatement pstmt = null;
		String sql = "insert into restaurant_test values(res_test_seq.nextval, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, menu);
			pstmt.setString(3, place);

			if (pstmt.executeUpdate() == 1) {
				System.out.println("��ϼ���!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(null, pstmt, null);
		}

	}

	public static void deleteRestaurant(HttpServletRequest request) {
		// �� or db
		// ���ޱ�, db

		String num = request.getParameter("num");

		PreparedStatement pstmt = null;
		String sql = "delete restaurant_test where r_no=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, num);

			if (pstmt.executeUpdate() == 1) {
				System.out.println("��������!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(null, pstmt, null);
		}

	}

	public static void updateRes(HttpServletRequest request) {

		String col = request.getParameter("col");
		String val = request.getParameter("val");
		String no = request.getParameter("no");

		PreparedStatement pstmt = null;
		String sql = "update restaurant_test"
					+ " set " + col + "=?"
					+ " where r_no=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, val);
			pstmt.setString(2, no);

			if (pstmt.executeUpdate() == 1) {
				System.out.println("���� ����!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(null, pstmt, null);
		}

		
	}}
