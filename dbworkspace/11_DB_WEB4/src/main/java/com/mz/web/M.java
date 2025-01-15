package com.mz.web;

import javax.servlet.http.HttpServletRequest;

public class M {

	// 파라미터 값으로 member 객체를 생성해주는 기능.
	public static MemberVO getMember(HttpServletRequest request) {
		String no = request.getParameter("noo");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		int no2 = 0;
		int age2 = 0;
		if (no != null) {
			no2 = Integer.parseInt(no);
		}
		
		if (age != null) {
			age2 = Integer.parseInt(age);
			
		}
		
		MemberVO m = new MemberVO(no2, name, age2);
		return m;
		
	}
}
