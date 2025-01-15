package com.mz.model2;

import javax.servlet.http.HttpServletRequest;

// M model - 비즈니스 로직을 처리하는 공간
// 계산, db, 일
public class M {
	public static void calc(HttpServletRequest request) {
		// 1. 값받기
			int a = Integer.parseInt(request.getParameter("a"));
			int b = Integer.parseInt(request.getParameter("b"));
			int c = a+b;
			
			System.out.println(a);
			System.out.println(b);
			System.out.println(c);
			// 여기서 만들어진 C를 v2.jsp라는 최종 결과 페이지에서 쓸수있게 만들어야 함 
			request.setAttribute("ccc", c);
	}

}
