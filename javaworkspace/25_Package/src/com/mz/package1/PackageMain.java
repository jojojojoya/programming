package com.mz.package1;

public class PackageMain {

	
	// 패키지 작명 방법
		// 회사 이름이나 도메인 등은 유일
		// 소문자로만 작명 (약속)
			
	
			// 도메인. 회사명. 프로그램명.
		// com. kr. net. gov....

		// 팀명, 그룹명 

		// 프로젝트명
	
	// ****도메인. 회사이름. 플랫폼. 프로그램명
		// com.mz.android.testapp
		// com.mz.ios.testapp
	
	// 네이버라는 이름을 같이 쓰는 전혀 다른 회사가 있다.
		// www.naver.com  -  TestApp.java
		// www.naver.kr  - 	TestApp.java

	
	// ****도메인. 회사이름. 플랫폼. 프로그램명
	// BEST PRACTICE
		// com.naver.testapp
		// kr.naver.testapp
	
	
	
	// 필드의 접근 범위를 지정 (범용적인 순서)
		// public : 아무데나 접근 가능 
		// protected : 같은 클래스 O, 같은 패키지 O, 하위클래스(상속) 
		// default : 같은 패키지끼리만 O 
		// private : 같은 클래스 o 
	
	
	
	
	
	
}
