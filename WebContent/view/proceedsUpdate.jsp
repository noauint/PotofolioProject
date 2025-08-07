<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
	<title>売上編集画面</title>
</head>
<body>
	<!-- ヘッダー -->
	<%@ include file="../common/header.jsp" %>

			<!-- 画面タイトル -->
			<div class="pageTitle">
				<h2>売上編集画面</h2>
			</div>
		
			<div class="message" style="text-align: center";>
				<p>${message}</p>
			</div>
			
			<!-- エラーメッセージ表示領域 -->
			<%@ include file="../common/errorMsg.jsp" %>
			
		<!-- 商品更新フォーム -->
		<form method="get" action="${pageContext.request.contextPath}/ProceedsUpdateServlet">
		  <div class="product-form">
		    <div class="update-row">
		      <input type="hidden" name="id" value="${customerList.proceedsId}">
		
		      <!-- 売上日 -->
		      <div class="form-group">
		        <span>売上日</span>
		        <input type="date" name="date" value="${customerList.salesDate}">
		      </div>
		
		      <!-- 売上個数 -->
		      <div class="form-group">
		        <span>売上個数</span>
		        <input type="text" name="pcs" value="${customerList.pcs}">
		      </div>
		
		      <!-- 顧客ID -->
		      <div class="form-group">
		        <span>顧客ID</span>
		        <input type="text" name="customerId" value="${customerList.customerId}">
		      </div>
		
		      <!-- 商品ID -->
		      <div class="form-group">
		        <span>商品ID</span>
		        <input type="text" name="goodsId" value="${customerList.goodsId}">
		      </div>
		
		      <!-- 担当者名 -->
		      <div class="form-group">
		        <span>担当者名</span>
		        <input type="text" name="person" value="${customerList.person}">
		      </div>
		    </div>
		
		    <!-- 更新ボタン -->
		    <div class="form-button-wrapper">
		      <input type="submit" value="更新" class="btn-common">
		    </div>
		  </div>
			<!-- 商品登録画面へのボタン -->
			<div class="form-button-wrapper">
			  <form method="post" action="${pageContext.request.contextPath}/ProceedsMenuTopServlet">
			    <button type="submit" class="btn-common">顧客登録画面へ</button>
			  </form>
			</div>
</body>
</html>
