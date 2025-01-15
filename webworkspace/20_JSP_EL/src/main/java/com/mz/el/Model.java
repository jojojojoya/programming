package com.mz.el;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class Model {

	public static void getAvg(HttpServletRequest request) {

		String name = request.getParameter("name");
		
		Double mid = Double.parseDouble(request.getParameter("mid"));
		Double last = Double.parseDouble(request.getParameter("last"));
		
		
		
		double avg = (mid + last) /2 ;
		String grade = "F";
		if (avg >= 90) {
			grade = "A";
			
		} else if (avg >=80) {
			grade = "B";
		} else if (avg >=70) {
			grade = "C";
		} else if (avg >=60) {
			grade = "D";
		}
		
	
	Student st = new Student(name,mid,last,avg,grade); 
	request.setAttribute("st", st);

	
	
	//////////////////////////////////////////////////////////////////////
	
	request.setAttribute("a", "ㅋㄷ");
	request.setAttribute("b", "ㅎㅅㅎ");
	
	int[] ar = {3,6,9};
	request.setAttribute("c", ar);
	
	Student[] ss = new Student[2];
	ss[0] = new Student("수진", 100, 100, 100, "sss");
	ss[1] = new Student("원호",100,100,100,"sss");
	request.setAttribute("ss", ss);
	
	System.out.println("----------");
	
	ArrayList<Student> students = new ArrayList<Student>();
	students.add(new Student("동혁",10,20,30,"AA"));
	students.add(new Student("정민",1,2,3,"BB"));
	students.add(new Student("도윤",8,2,3,"CC"));
	
	request.setAttribute("students", students);
	
	
	students.get(0);
}}
