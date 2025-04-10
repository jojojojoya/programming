<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<% 
//1. 값 받기 

    // 입력 값 받기
    int gap = Integer.parseInt(request.getParameter("vari_val"));
    double mi_input = gap * 0.6214;
    double inch_input = gap * 0.3937;
    double pp_input = gap / 3.3058;
    double ff_input = (gap * 9.0 / 5.0) + 32;
    String danwe = request.getParameter("danwe");

    
    String mi_input2 = String.format("%.1f", mi_input);
String inch_input2 = String.format("%.1f", inch_input);
String pp_input2 = String.format("%.1f", pp_input);
String ff_input2 = String.format("%.1f", ff_input);

    // 각 변환 값들을 String.format으로 소수점 1자리까지만 포맷

%>




	<% 	if(danwe.equals("default")) { %>  
	  <style>
      .tbl {
        background-color:   rgb(208, 158, 255);
        border: 2px solid rgb(130, 145, 187);
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
      }

      td {
        text-align: center; /* 가로 중앙 정렬 */
        vertical-align: middle; /* 세로 중앙 정렬 */
      }

      .tblbody {
        display: grid;
        justify-items: center;
        align-items: center;
        gap: 10px;
        padding: 20px;
      }
    </style>
  </head>
  <body>
    <table class="tbl">
      <tr>
        <td>
          <div
            style="
              background-color: rgb(255, 255, 255);
              border-color: 2px solid rgb(119, 119, 119);
              text-align: center;
            "
          >
            변환결과
          </div>
          <div class="tblbody">
            <div>
            <input
            style="
              background-color: rgb(208, 158, 255);
              border: none;
              outline: none;
              width: 70px;
            "
            value= <%=gap %>
           
            name="cm_input">
       
        <span style="color: red;">cm</span>
      </div>
      <div>↓</div>

      <div>
            <input
            style="
              background-color:  rgb(208, 158, 255);
              border: none;
              outline: none;
              width: 70px;
            "
            value=<%=inch_input2 %>
            name="inch_input">
   
        <span style="color: red;">inch</span>
      </div>

            <input
              style="border: none; width: 200px; background-color: white;"
                type="button" onclick="history.back()"
              value="돌아가기"
              name="enter"
            />
          </div>
        </div>
        </td>
      </tr>
    </table>

				<% } else if(danwe.equals("m2")) { %>
				
				  <style>
      .tbl {
        background-color:  rgb(184, 255, 200);
        border: 2px solid rgb(130, 145, 187);
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
      }

      td {
        text-align: center; /* 가로 중앙 정렬 */
        vertical-align: middle; /* 세로 중앙 정렬 */
      }

      .tblbody {
        display: grid;
        justify-items: center;
        align-items: center;
        gap: 10px;
        padding: 20px;
      }
    </style>
  </head>
  <body>
				 <table class="tbl">
      <tr>
        <td>
          <div
            style="
              background-color: rgb(255, 255, 255);
              border-color: 2px solid rgb(119, 119, 119);
              text-align: center;
            "
          >
            변환결과
          </div>
          <div class="tblbody">
            <div>
            <input
            style="
              background-color: rgb(184, 255, 200);
              border: none;
              outline: none;
              width: 70px;
            "
            value=<%=gap %>
            name="m2_input">
       
        <span style="color:  rgb(14, 162, 81);">㎡</span>
      </div>
      <div>↓</div>

      <div>
            <input
            style="
              background-color:  rgb(184, 255, 200);
              border: none;
              outline: none;
              width: 70px;
            "
            value=<%=pp_input2%>
            name="pp_input">
   
        <span style="color:  rgb(14, 162, 81);">평</span>
      </div>

            <input
              style="border: none; width: 200px; background-color: white;"
                type="button" onclick="history.back()"
              value="돌아가기"
              name="enter"
            />
          </div>
        </div>
        </td>
      </tr>
    </table>
				<% } else if (danwe.equals("cf")) {%>
   <style>
      .tbl {
        background-color:  rgb(241, 255, 189);
        border: 2px solid rgb(130, 145, 187);
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);

      }

      td {
        text-align: center; /* 가로 중앙 정렬 */
        vertical-align: middle; /* 세로 중앙 정렬 */
      }

      .tblbody {
        display: grid;
        justify-items: center;
        align-items: center;
        gap: 10px;
        padding: 20px;
      }
    </style>
  </head>
  <body>
    <table class="tbl">
      <tr>
        <td>
          <div
            style="
              background-color: rgb(255, 255, 255);
              border-color: 2px solid rgb(119, 119, 119);
              text-align: center;
            "
          >
            변환결과
          </div>
          <div class="tblbody">
            <div>
            <input
            style="
              background-color: rgb(241, 255, 189);
              border: none;
              outline: none;
              width: 70px;
            "
            value=<%=gap %>
            name="cc_input">
       
        <span style="color: rgb(200, 213, 63);">℃</span>
      </div>
      <div>↓</div>

      <div>
            <input
            style="
              background-color:  rgb(241, 255, 189);
              border: none;
              outline: none;
              width: 70px;
            "
            value=<%=ff_input2%>
            name="ff_input">
   
        <span style="color: rgb(200, 213, 63);">℉</span>
      </div>

            <input
              style="border: none; width: 200px; background-color: white;"
               type="button" onclick="history.back()"
              value="돌아가기"
              name="enter"
            />
          </div>
        </div>
        </td>
      </tr>
    </table>
				<% } else if (danwe.equals("km")) {%>
     <style>
      .tbl {
        background-color:  rgb(255, 211, 208);
        border: 2px solid rgb(130, 145, 187);
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
      }

      td {
        text-align: center; /* 가로 중앙 정렬 */
        vertical-align: middle; /* 세로 중앙 정렬 */
      }

      .tblbody {
        display: grid;
        justify-items: center;
        align-items: center;
        gap: 10px;
        padding: 20px;
      }
    </style>
  </head>
  <body>
    <table class="tbl">
      <tr>
        <td>
          <div
            style="
              background-color: rgb(255, 255, 255);
              border-color: 2px solid rgb(119, 119, 119);
              text-align: center;
            "
          >
            변환결과
          </div>
          <div class="tblbody">
            <div>
            <input
            style="
              background-color:rgb(255, 211, 208);
              border: none;
              outline: none;
              width: 70px;
            "
            value=<%=gap %>
            name="km_input">
       
        <span style="color: rgb(148, 33, 33);">km/h</span>
      </div>
      <div>↓</div>

      <div>
            <input
            style="
              background-color:  rgb(255, 211, 208);
              border: none;
              outline: none;
              width: 70px;
            "
            value=<%=mi_input2 %>
            name="mi_input">
   
        <span style="color: rgb(148, 33, 33);">mi/h</span>
      </div>

            <input
              style="border: none; width: 200px; background-color: white;"
              type="button" onclick="history.back()"
              value="돌아가기"
              name="enter"
            />
          </div>
        </div>
        </td>
      </tr>
    </table>
    
    			<% } else {%>
    <h1>뒤로 돌아가서 단위를 선택하세요^^</h1>
 <%} 
 %>

</body>
</html>