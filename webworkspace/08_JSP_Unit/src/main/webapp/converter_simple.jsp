<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


 <!--  
1. CSS 클래스 사용: 배경색을 조건별로 설정할 필요 없이, CSS 클래스에 background-color를 정의하여 JSP에서 동적으로 변경.
2. 코드 중복 제거: HTML 구조는 동일하므로 하나의 구조만 사용하며, 값과 단위를 조건에 따라 동적으로 변경.
3. 가독성 향상: bgClass 변수와 조건을 통해 스타일 변경을 명확히 표현.
4. 이렇게 수정하면 코드가 훨씬 간결하고 유지보수도 쉬워집니다!
 
 -->
 
 
 
<title>변환 결과</title>
<style>
  /* 공통 테이블 스타일 */
  .tbl {
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

  /* 배경색 클래스 */
  .default-bg { 
  background-color: rgb(208, 158, 255); }
  .m2-bg { background-color: rgb(184, 255, 200); }
  .cf-bg { background-color: rgb(241, 255, 189); }
  .km-bg { background-color: rgb(255, 211, 208); }
</style>
</head>
<body>

<% 
// 값 받기
int gap = Integer.parseInt(request.getParameter("vari_val"));
double mi_input = gap * 0.6214;
int ff_input = (gap * 9 / 5) + 32;
String danwe = request.getParameter("danwe");

// 단위에 따라 배경색 클래스 결정
String bgClass = "";
if (danwe.equals("default")) {
    bgClass = "default-bg";
} else if (danwe.equals("m2")) {
    bgClass = "m2-bg";
} else if (danwe.equals("cf")) {
    bgClass = "cf-bg";
} else if (danwe.equals("km")) {
    bgClass = "km-bg";
} else {
    out.println("<h1>뒤로 돌아가서 단위를 선택하세요^^</h1>");
    return;
}
%>

<table class="tbl <%= bgClass %>">
  <tr>
    <td>
      <div
        style="background-color: rgb(255, 255, 255); border: 2px solid rgb(119, 119, 119); text-align: center;">
        변환결과
      </div>
      <div class="tblbody">
        <div>
          <input
            style="background-color: inherit; border: none; outline: none; width: 70px;"
            value="<%= gap %>"
            name="input_value">
          <span style="color: red;">
            <% if (danwe.equals("default")) { %>cm
            <% } else if (danwe.equals("m2")) { %>㎡
            <% } else if (danwe.equals("cf")) { %>℃ 
            <% } else if (danwe.equals("km")) { %>km/h
            <% } %>
          </span>
        </div>
        <div>↓</div>
        <div>
          <input
            style="background-color: inherit; border: none; outline: none; width: 70px;"
            value="<%= danwe.equals("default") ? gap * 0.3937 
                       : danwe.equals("m2") ? gap / 3.3058 
                       : danwe.equals("cf") ? ff_input 
                       : danwe.equals("km") ? mi_input : "" %>">
          <span style="color: red;">
            <% if (danwe.equals("default")) { %>inch
            <% } else if (danwe.equals("m2")) { %>평
            <% } else if (danwe.equals("cf")) { %>℉
            <% } else if (danwe.equals("km")) { %>mi/h
            <% } %>
          </span>
        </div>
        <input
          style="border: none; width: 200px; background-color: white;"
          type="button" onclick="history.back()"
          value="돌아가기">
      </div>
    </td>
  </tr>
</table>

</body>
</html>