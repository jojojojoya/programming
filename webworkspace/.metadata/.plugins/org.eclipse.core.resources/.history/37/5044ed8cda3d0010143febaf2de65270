package com.sz.test;

import java.beans.beancontext.BeanContext;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;

public class Model {

	
	
	// ??
	public static void test(HttpServletRequest request) throws UnsupportedEncodingException {
		
		request.setCharacterEncoding("utf-8");
		//1.값 받기	
				String name = request.getParameter("name");
				int age = Integer.parseInt(request.getParameter("age"));
				String gen = request.getParameter("gen");
				String[] inter = request.getParameterValues("inter");
		//2. 로직처리
				Bean b = new Bean(name,age,gen,inter);
						request.setAttribute("bb", b);
	}

}
