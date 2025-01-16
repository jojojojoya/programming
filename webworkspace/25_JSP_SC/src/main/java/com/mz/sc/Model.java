package com.mz.sc;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Model {

	/*
	 1. parameter = V에서 만든 값 (html, get 요청할때 만든 문자열일수도 있고, form을 사용해 input name의 데이터일수도)
	 			String, String[] _checkbox 
	 			
	 			request에 실려서 넘어오는거. 
	 - 값 읽을때 
	 	- java : req.getParameter("파라미터명");
	 	- EL: ${param.파라미터명}
	 	
	 2. attribute = java에서 만든 값 (model, c)	Object -> req에 실려서 넘어오는 거 
	 - 값 읽을때 
	  	- java : req.getAttribute("애트리뷰트 이름");
	 	- EL : ${애트리뷰트명}
	 	
	 	
	  */
	
	
	
	public static void printSC(HttpServletRequest request) {
		
		// 세션, 쿠키 
		
		// session. attr - M에서 만든값 - 서버~~~~~~클라이언트 연결 상태에 담김.
		// object - 시간 제한

		// 값 읽을때 = java : requ.getSession().getAttribute("이름"); 
		// 			EL : ${이름} / ${SessionScope.이름}
		
		Object session = request.getSession().getAttribute("c");
		System.out.println(session);
		
		
		// cookie 
		// M에서 만든값
		
		// 값 읽을때
		// EL : ${cookie.쿠키이름.value}
		// java : 
		
		Cookie[] cookies = request.getCookies();
		for (Cookie c :	cookies) {
			System.out.println(c.getName());
			if (c.getName().equals("d")) {
				System.out.println(c.getValue());
				
			}
			
		}
	}

	
	
	
	public static void make(HttpServletRequest request, HttpServletResponse response) {
		String bbb = "zzz";
		request.setAttribute("b", bbb);

		//값
		// parameter, attribute
		// 특성 : third에서 못씀.
		// session, cookie 
		
		String ccc = "세션~!";
		
		HttpSession hs = request.getSession();
		hs.setAttribute("c", ccc);
		
		hs.setMaxInactiveInterval(5); // 세션 유지시간 5초
		
		
		// 서버와 클라이언트 연결상태. (접속한거)
		// 서버 & 클라이언트 연결만 살아있으면 이 사이트 어디에서든 사용 가능
		// 연결 끊어지면 못씀(접속 종료 / 브라우저 다 닫은거)
		// 세션 유지시간 (기본 30분)
		// 유지시간 이내에 '요청'이 일어나지 않으면 죽음
		// 시간내에 '요청' 등의 작업을 하면 갱신됨 (유지시간 초기화)
		
		// cookie 
		// 사용자 컴터에 파일로 저장 
		
		// 재부팅을 해도, 접속 끊어도 살아있음.
		// 시간 제한 걸 수 있음. 
		
		
		
		String ddd = "쿠키~";
		Cookie cookie = new Cookie("d", ddd);
		cookie.setMaxAge(5*60); // 5분 
		
		response.addCookie(cookie);
		
		
	}

}
