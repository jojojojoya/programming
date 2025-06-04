<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>상품 추가</title>
  <link rel="stylesheet" href="/css/adminproduct.css">
  <!-- Quill Editor CSS & JS -->
  <link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">
  <script src="https://cdn.quilljs.com/1.3.6/quill.min.js"></script>

</head>
<body>

<jsp:include page="/WEB-INF/views/main/header.jsp" />

<div class="adminmp-container">

  <!-- 사이드바 -->
  <div class="adminmp-sidebar-container">
    <div class="adminmp-sidebar-list">
      <div onclick="location.href='/adminmypage'">MYPAGE</div>
      <div onclick="location.href='/adminproductlist'">유저관리</div>
      <div onclick="location.href='/adminorder'">주문관리</div>
      <div onclick="location.href='/adminproduct'">상품관리</div>
      <div onclick="location.href='/adminreview'">리뷰관리</div>
      <div onclick="location.href='/adminqna'">문의관리</div>
    </div>
  </div>

    <div class="adminmp-subpage-header">
      <div class="adminmp-productlist-header">
  		<div class="adminmp-productlist-hhd">
        <div style="font-size:14px; font-weight: bold;">상품 등록</div>
        </div>
        <div class="section-divider"></div>

        <!-- 등록 버튼 -->

  		  <form action="/add/product" enctype="multipart/form-data"
		  method="post" onsubmit="return confirm('최종 등록하시겠습니까?')">
  			<div class="productaddment"> 직접 상품을 등록할 수 있습니다. </div>
			<div class="productaddinput">
			  <!-- 왼쪽: 이미지 업로드 -->
			  <div class="productaddinput">
			    <!-- 왼쪽: 이미지 업로드 + 미리보기 -->
			    <div class="productimagebox">
			      <input type="file" name="imageFile" accept="image/*" onchange="previewImage(event)" />
				  
			      <img id="preview" src="#" alt="미리보기" style="display:none; width:140px; height:140px; margin-top:8px; object-fit:cover; border:1px solid #ccc;" />
			   대표 이미지 </div>

			    <!-- 오른쪽: 인풋들 -->
			    <div class="productform-fields">
			      <div>
			        <select name="productCategory" class="productinput">
			          <option value=""  disabled selected>카테고리 선택</option>
			          <option name="outer" value="outer">아우터</option>
			          <option name="top" value="top">상의</option>
			          <option name="bottom" value="bottom">하의</option>
			          <option name="acc" value="acc">악세서리</option>
			          <option name="sale" value="sale">세일</option>
			        </select>
					
			      </div>
			      <div><input type="text"  required name="productName" placeholder="상품 이름" class="productinput" /></div>
			      <div><input type="number"  max="1000000"  step="1000" required  name="productPrice" placeholder="상품 가격" class="productinput" /></div>
			      <div><input type="number"  min="1" max="100" required  name="productAmount" placeholder="상품 수량" class="productinput" /></div>
			      <div><input type="text" name="productIntrotext" placeholder="요약 설명" class="productinput" /></div>
			      <div>
					<!-- Rich Text Editor -->
					<div id="editor-container" style="height: 200px;"></div>
					<!-- 실제 form에 넘길 hidden input -->
					<input type="hidden" name="productInfocontent" id="editor-content">

			      </div>
			      <div><button class="productadd-btn" type="submit">등록</button></div>
			    </div>
			  </div>


  		  </form>
  	    </div>

          </div>
        </div>
      </div>
    </div>
  </div>
  
  
  <script>
	
	  const quill = new Quill('#editor-container', {
	    theme: 'snow'
	  });

	  document.querySelector('form').addEventListener('submit', function (e) {
	    const content = document.querySelector('input[name=productInfocontent]');
	    content.value = quill.root.innerHTML;
	  });

    function previewImage(event) {
      const reader = new FileReader();
      reader.onload = function (e) {
        const preview = document.getElementById('preview');
        preview.src = e.target.result;
        preview.style.display = 'block';
      };
      reader.readAsDataURL(event.target.files[0]);
    }
  </script>


<jsp:include page="/WEB-INF/views/main/footer.jsp" />
</body>
</html>
