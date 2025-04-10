package com.mz.tag.core;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class M {

	public static void work(HttpServletRequest request) {
		// 1. 값 받기
		
		int birth = Integer.parseInt(request.getParameter("y"));
		System.out.println(birth);
		
		int curYear = LocalDate.now().getYear();
		int age = curYear - birth + 1;
		request.setAttribute("age", age);
		
		////////////////////////////////////////////
		
		int[] ar = {1,2,3,4,5};
		request.setAttribute("ar", ar);
		
		ArrayList<Menu> menus = new ArrayList<Menu>();
		
		menus.add(new Menu("버거킹", 1000));
		menus.add(new Menu("치킨", 3000));
		menus.add(new Menu("칼국수", 6000));
		menus.add(new Menu("순대국", 9000));
		
		request.setAttribute("menus", menus);
		
	}

}
