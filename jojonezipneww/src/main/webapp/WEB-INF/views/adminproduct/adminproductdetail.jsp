<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>상품 편집</title>
  <link rel="stylesheet" href="/css/adminproduct2.css">
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

  		  <form action="/update/product" enctype="multipart/form-data"
		  method="post" onsubmit="return confirm('최종 등록하시겠습니까?')">
  			<div class="productaddment"> 상품을 편집하세요. </div>
			<div class="productaddinput">
			  <!-- 왼쪽: 이미지 업로드 -->
			  <div class="productaddinput">
			    <!-- 왼쪽: 이미지 업로드 + 미리보기 -->
			    <div class="productimagebox">
					대표 이미지 
					<c:if test="${not empty product.product_image}">
					  <img src="/img/${product.product_image}" width="100"/>
					</c:if>
					<input type="file" name="imageFile">

			      <img id="preview" src="#" alt="미리보기" style="display:none; width:140px; height:140px; margin-top:8px; object-fit:cover; border:1px solid #ccc;" />
			   </div>

			    <!-- 오른쪽: 인풋들 -->
			    <div class="productform-fields">
			      <div>
					<select name="product_category" class="productinput">
					  <option value="" disabled>카테고리 선택</option>
					  <option value="outer" ${product.product_category eq 'outer' ? 'selected' : ''}>아우터</option>
					  <option value="top" ${product.product_category eq 'top' ? 'selected' : ''}>상의</option>
					  <option value="bottom" ${product.product_category eq 'bottom' ? 'selected' : ''}>하의</option>
					  <option value="acc" ${product.product_category eq 'acc' ? 'selected' : ''}>악세서리</option>
					  <option value="sale" ${product.product_category eq 'sale' ? 'selected' : ''}>세일</option>
					</select>

					
			      </div>
	<input type="hidden" name="product_id" value="${product.product_id}">
			      <div><input type="text"  required name="product_name" value="${product.product_name}" placeholder="상품 이름" class="productinput" /></div>
			      <div><input type="number"  max="1000000"  step="1000" value="${product.product_price}" required  name="product_price" placeholder="상품 가격" class="productinput" /></div>
			      <div><input type="number"  min="1" max="100" required value="${product.product_amount}" name="product_amount" placeholder="상품 수량" class="productinput" /></div>
			      <div><input type="text" name="product_introtext" value="${product.product_introtext}" placeholder="요약 설명" class="productinput" /></div>
			      <div>
					<!-- Rich Text Editor -->
					<div id="editor-container" style="height: 200px;"></div>
					<!-- 실제 form에 넘길 hidden input -->
					<input type="hidden" name="product_infocontent" id="editor-content">
					<input type="hidden" name="originalImage" value="${product.product_image}">

				<div>
					 <div style="display: flex; gap: 10px; margin-top: 20px;">
					    <button type="submit" class="productadd-btn">수정 완료</button>
					</form>
					
					
					<form action="/delete/product" method="post" onclick="return confirm('정말 삭제하시겠습니까?');">
<input type="hidden" name="productId" value="${product.product_id}">				    
					    <button type="submit" class="productdelete-btn"> 삭제 </button>
					  </div>

					</form>
			  </div>


  	    </div>

          </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  
  
  <script>
    // Quill 에디터 초기화
    const quill = new Quill('#editor-container', {
      theme: 'snow'
    });

    // 기존 데이터 삽입
    const existingContent = `${product.product_infocontent}`;
    quill.root.innerHTML = existingContent;

    // 폼 전송 시, 에디터 내용 hidden input에 복사
    document.querySelector('form[action="/update/product"]').addEventListener('submit', function (e) {
      const contentInput = document.getElementById('editor-content');
      contentInput.value = quill.root.innerHTML;
    });

    // 이미지 미리보기
    document.querySelector('input[type="file"]').addEventListener('change', previewImage);

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
