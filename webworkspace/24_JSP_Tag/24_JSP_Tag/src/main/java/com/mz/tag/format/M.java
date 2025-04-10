package com.mz.tag.format;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class M {

	public static void dataMaker(HttpServletRequest request) {

		int a = 123456789;
		double b = 0.4566;
		double c = 123123.456456;
		Date d = new Date();
		
		request.setAttribute("a", a);
		request.setAttribute("b", b);
		request.setAttribute("c", c);
		request.setAttribute("d", d);
		
		
		
		// 과자 객체 저장 
		// 3개 이상 / 이름, 가격, 제조일
		
		ArrayList<Snack> snacks = new ArrayList<Snack>();
		snacks.add(new Snack("감자깡",1000,new Date()));
		snacks.add(new Snack("쌀약과",5000,new Date()));
		snacks.add(new Snack("고래밥",1500,new Date()));
		snacks.add(new Snack("새우깡",2500,new Date()));
		request.setAttribute("snacks", snacks);
		
	
		
		
		
		
	}

}
