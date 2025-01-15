<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="reg.css" />
</head>
<body>

<% 

		//post 방식 한글처리
		request.setCharacterEncoding("utf-8");
		
		// 1. 값 받기
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String gen = request.getParameter("gender");
		String addr = request.getParameter("addr");
		String intro = request.getParameter("introduce");
		System.out.println(name);
		System.out.println(id);
		System.out.println(pw);
		System.out.println(gen);
		System.out.println(addr);
		
		String[] inter = request.getParameterValues("interest");
		String inter2 = "";
		for (String s : inter) {
			System.out.println(s + " ");
			inter2 += s + " ";
		}
		
		System.out.println(inter2);
		System.out.println(intro);
		
	
%>

<!--  1. html in java.		2. java in html	 -->

 <div class="container">
        <div class="items">
          <div class="item1">Name</div>
          <div class="item2">
            <%= name %>
          </div>
        </div>
        <div class="items">
          <div class="item1">ID</div>
          <div class="item2">
            <%= id %>
          </div>
        </div>
        <div class="items">
          <div class="item1">PW</div>
          <div class="item2">
            <%= pw %>
          </div>
        </div>
        <div class="items">
          <div class="item1">Gender</div>
          <div class="item2">
            <%= gen %>
          </div>
        </div>
        <div class="items">
          <div class="item1">Addr</div>
          <div class="item2">
            <%= addr %>
          </div>
        </div>
        <div class="items inter">
          <div class="item1">Interest</div>
          <div class="item2 interest">
            <%= inter2 %>
           </div>              
        </div>
        <div class="items text">
          <div class="item1">Introduce</div>
          <div class="item2">
            <%= intro %>
          </div>
        </div>
        <div class="items item3">
          <div class="button"><button>sign up</button></div>
        </div>
      </div>



</body>
</html>