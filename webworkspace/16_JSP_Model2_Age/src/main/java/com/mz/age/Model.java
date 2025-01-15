package com.mz.age;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

public class Model {
public static void main(String[] args) {
	//날짜처리 
	Date d = new Date(0);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
	System.out.println(d);
	System.out.println(sdf.format(d));
	System.out.println("----------");
	
	int year1 = d.getYear();
	System.out.println(year1 + 1900);
	System.out.println("----------");
	
	int year2 = LocalDate.now().getYear();
	System.out.println(year2);
	
	int year3 = Year.now().getValue();
	System.out.println(year3);
	
	System.out.println("========");
	
	GregorianCalendar today = new GregorianCalendar();
	System.out.println(today);
	int year4 = today.get(today.YEAR);
	System.out.println(year4);
	

}
	public static void calcAge(HttpServletRequest request) {
	//1.값 받기	
		int birth = Integer.parseInt(request.getParameter("birth"));
		System.out.println(birth);
		
		
		//날짜처리 
		Date d = new Date(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		System.out.println(d);
		System.out.println(sdf.format(d));
		System.out.println("----------");
		
		int year1 = d.getYear();
		System.out.println(year1 + 1900);
		System.out.println("----------");
		
		int year2 = LocalDate.now().getYear();
		System.out.println(year2);
		
	//2. 로직처리
		int age = year2 - birth +1;
		
		
		Bean b = new Bean(birth,age);
		request.setAttribute("bb", b);
	}

}
