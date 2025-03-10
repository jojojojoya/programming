<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">

    <title> KOYOI </title>
</head>
<style>

.habitpage{
    display: flex;
    width: 1000px;
    margin-left: 300px;
}
.myhabit{
    display: flex;
    background-color: beige;
    width: 300px;
    height: 300px;
}
.recommend-habit{
    background-color: blue;
    width: 300px;
    height: 300px;
}

.tabs {
    display: flex;
    cursor: pointer;
    background-color: #f1f1f1;
    padding: 10px;
    border-radius: 8px;
}
.tab {
    flex: 1;
    text-align: center;
    padding: 10px;
    font-size: 18px;
    font-weight: bold;
    border-bottom: 3px solid transparent;
    transition: 0.3s;
}
.tab.active {
    border-bottom: 3px solid #007BFF;
    background-color: #e9ecef;
}
.content {
    display: none;
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 8px;
    margin-top: 10px;
}
.content.active {
    display: block;
}
.content ul {
    list-style: none;
    padding: 0;
}
.content li {
    background: #f8f9fa;
    padding: 10px;
    margin: 5px 0;
    border-radius: 5px;
    text-align: center;
}
.habit-firstline{
    display: flex;
}
.habit-calendar{
    background-color: blueviolet;
    width: 300px;
    height: 300px;
}
table {
    border-collapse: collapse;
    width: 100%;
    max-width: 400px;
    text-align: center;
    margin-top: 50px;

    font-size: 15pt;

}


.weekly-habit-check{
    background-color: cadetblue;
    width: 300px;
    height: 300px
}
.habit-secondline{
    display: flex;
}
.week-rate{
    background-color: aquamarine;
    width: 300px;
    height: 300px;
}
.habit-memo{
    background-color: chartreuse;
    width: 300px;
    height: 300px;
}


</style>
<script>
    $(document).ready(function () {
        $(".tab").click(function () {
            // 모든 탭과 콘텐츠의 active 클래스 제거
            $(".tab").removeClass("active");
            $(".content").removeClass("active");

            // 현재 클릭한 탭에 active 클래스 추가
            $(this).addClass("active");

            // data-target 속성에서 해당 콘텐츠 ID 가져와서 표시
            var target = $(this).attr("data-target");
            $("#" + target).addClass("active");
        });

        // 초기 상태 설정 (첫 번째 탭이 기본 활성화됨)
        $(".tab:first").addClass("active");
        $(".content:first").addClass("active");
    });
</script>
<body>

<div class="habitpage" >
    <div>
        <div class="myhabit">내습관</div>
        <div class="recommend-habit">
            <div class="tabs">
                <div class="tab active" data-target="mental">정신건강</div>
                <div class="tab" data-target="physical">신체건강</div>
            </div>

            <div id="mental" class="content active">
                <ul>
                    <li>책 읽기</li>
                    <li>음악 듣기</li>
                    <li>그림 그리기</li>
                </ul>
            </div>

            <div id="physical" class="content">
                <ul>
                    <li>물 마시기</li>
                    <li>산책 가기</li>
                    <li>식사 하기</li>
                </ul>
            </div>

        </div>
    </div>
    <div>
    <div class="habit-firstline">
        <div class="habit-calendar">달력</div>
        <div class="weekly-habit-check">
            <table border="1">
                <tr>
                    <th></th>
                    <th>월</th>
                    <th>화</th>
                    <th>수</th>
                    <th>목</th>
                    <th>금</th>
                    <th>토</th>
                    <th>일</th>
                </tr>
                <tr>
                    <td>물마시기 습관</td>
                    <td>●</td> <td>×</td> <td>×</td> <td>●</td> <td>●</td> <td>●</td> <td>×</td>
                </tr>
                <tr>
                    <td>산책가기 습관</td>
                    <td>×</td> <td>×</td> <td>●</td> <td>×</td> <td>●</td> <td>×</td> <td>●</td>
                </tr>
            </table>
        </div>
    </div>
    <div class="habit-secondline">
        <div class="week-rate">주간달성률</div>
        <div class="habit-memo" >유저총평</div>
    </div>
    </div>
</div>



</body>
</html>
