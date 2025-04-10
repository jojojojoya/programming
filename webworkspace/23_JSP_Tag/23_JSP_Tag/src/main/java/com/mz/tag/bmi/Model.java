package com.mz.tag.bmi;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class Model {

	public static void calc(HttpServletRequest request) {
		
		try {
			
		
		// 1. 경로
		//String path = "C:/Users/terin/OneDrive/바탕 화면/SBT10";

		// 서버상 경로
		String path = request.getServletContext().getRealPath("bmi/uploadFile");
		System.out.println(path);
		

		//라이브러리 가져다가 씀 cos.jar
		// 2. multipart 객체 생성 => 업로드 기능

		MultipartRequest mr = new MultipartRequest(request, path,
				1024*1024*20, // 최대 허용 용량 단위는 바이트 20mb
				"utf-8", // req.setCharacter("utf-8") 그거
				new DefaultFileRenamePolicy() // 중복처리 1,2,3,...
				);

		// 3. 값 mr로 박스 포장 까서 값 얻어야됨.
	String name = mr.getParameter("name");
	double height = Double.parseDouble(mr.getParameter("height"));
	double weight = Double.parseDouble(mr.getParameter("weight"));
	//String imgFile = request.getParameter("imgFile");
	String fName = mr.getFilesystemName("imgFile");
	// 서버상에 업로드 된 파일 이름.

	double bmi = 0;

	if (height > 10) {
		 bmi = weight / (height/100 * height*0.01); //cm로 입력했을것
	}else {
		 bmi = weight / (height * height); //m용
		 height *= 100;
		 
	}

	String bmi2 = String.format("%.2f", bmi);

	//if (height > 10) {
//		height /= 100;
	//}
	//double bmi = we / (height * height);


	String status = "3단계비만";
	if (bmi < 18.5) {
		status = "저체중";
	}	else if (bmi <= 22.9) {
		status = "정상";
	}	else if (bmi <= 24.9) {
		status = "비만전단계";
	}	else if (bmi <= 29.9) {
		status = "1단계비만";
	} else if (bmi <= 34.9) {
		status = "2단계비만";
	}
		
	
	// 객체로 만들어서 뭉쳐서 한번에 보내자.
	Bean b = new Bean(name, height, weight, fName, bmi2, status);
	request.setAttribute("bb", b);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
