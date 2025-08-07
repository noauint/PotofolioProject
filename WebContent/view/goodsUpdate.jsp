<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
	<title>商品編集画面</title>
</head>
<body>
	<!-- ヘッダー -->
	<%@ include file="../common/header.jsp" %>

			<!-- 画面タイトル -->
			<div class="pageTitle">
				<h2>商品編集画面</h2>
			</div>
		
			<div class="message" style="text-align: center";>
				<p>${message}</p>
			</div>
			
			<!-- エラーメッセージ表示領域 -->
			<%@ include file="../common/errorMsg.jsp" %>
			
		<form method="get" action="${pageContext.request.contextPath}/GoodsUpdateServlet" class="product-form">

		  <div class="update-row">
		    <div class="form-group">
		      <span>商品名</span>
		      <input type="text" name="name" value="${goods.goodsName}">
		    </div>
		    <div class="form-group">
		      <span>単価</span>
		      <input type="text" name="price" value="${goods.goodsPrice}">
		    </div>
		    <div class="form-group">
		      <span>原価</span>
		      <input type="text" name="cost" value="${goods.goodsCost}">
		    </div>
		  </div>
		
		  <input type="hidden" name="id" value="${goods.goodsId}">
		
		  <div class="form-button-wrapper">
		    <input type="submit" value="更新" class="btn-common">
		  </div>
		
		</form>


			<!-- 商品登録画面へのボタン -->
			<div class="form-button-wrapper">
			  <form method="post" action="${pageContext.request.contextPath }/GoodsMenuTopServlet">
			    <button type="submit" class="btn-common">商品登録画面へ</button>
			  </form>
			</div>
	</body>
</html>
