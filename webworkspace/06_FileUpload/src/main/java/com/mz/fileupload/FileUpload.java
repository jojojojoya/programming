package com.mz.fileupload;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/FileUpload")
public class FileUpload extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // 1. 값 받기
	      
	      // 로컬 -> 내컴퓨터에다가
	      String path = "C:\\Users\\soldesk\\Documents\\Study_SHY";

	      // 2. 경로설정 / 실제 서버상 경로                        // file 이라는 폴더가 있어야됨.
	      String path2 = request.getServletContext().getRealPath("file");
	      System.out.println(path2);
	      
	      // 2. 파일 처리
	      // 파일 업로드 처리됨
	      
		MultipartRequest mr 
		= new MultipartRequest(request, 
				path2,
				30*1024*1024, //파일의 최대 허용 용량 (넘으면 예외) 단위, 바이트
				"utf-8" //req.setCharacterEncoding("utf-8");
				,new DefaultFileRenamePolicy());
		
		
		
				// 서버를 나만 쓰는게 아니라 여러사람이 사용함
		// 로컬파일의 이름들은 같을수 있음
		// 근데 서버에서 겹치면? 중복이 안되기 때문에 그경우 a.jpg, a1.jpg, a2,jpg.....
		
		
		//3. 값 받기 (박스를 포장했으니 박스를 까서 req(비닐)에 접근해야됨)
		// 원래 값 받기가 제일 중요, 1순위인데, 왜 3번이 됐나?
		// 파일을 업로드 할꺼니까. -> 2. 업로드 기능(mr객체) -> 1. 경로설정
		
		
		
		String name = mr.getParameter("name");
		// 파일명 읽을때 
		// 중복 안되게 처리한 것 때문에 올릴때의 파일명이 중요한게 아니고
		// 실제 서버상에 올라간 파일명이 중요
	
	
		String pic = mr.getFilesystemName("pic");
		String etc = mr.getFilesystemName("etc");
		
		
		System.out.println(name);
		System.out.println(pic);
		System.out.println(etc);
		
		
		

		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("<meta charset=\"UTF-8\">");
		out.print("</head>");
		out.print("<body>");

		out.print("<h1>" + name + "</h1>");
		out.printf("<h1><img src='file/%s'></h1>",pic);
		out.printf("<h1><a href='file/%s'> 다운로드</a></h1>",etc);

			
		
		out.print("</body>");	
		out.print("</html>");
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
