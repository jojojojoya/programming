<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<h1> 성적 계산 </h1>
<form action="/record-show" method="post">
    <div>
        <div>
            이름 : <input type="text" name="name">
        </div>
        <div>
            중간 : <input type="text" name="mid">
        </div>
        <div>
            기말 : <input type="text" name="fin">
        </div>
        <div>
            <button type="submit"> 확인 </button>
        </div>
    </div>
</form>

<hr>

<pre>
    1. mvc
    2. 파라미터 - 객체 자동 매칭 (dto 활용)
    3. m 외부 주입

    4. 결과화면
    이름 : ㅇㅇ
    중간 : ㅇㅇ
    기말 : ㅇㅇ
    평점 : ㅇㅇ     ex) 평점 50.5점.   소수점 1자리 formatting
    등급 : A       (A : 90이상, B : 80이상, c : 70이상, 나머지 D)
</pre>

</body>
</html>