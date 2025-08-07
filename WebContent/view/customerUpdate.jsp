<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
	<title>顧客編集画面</title>
</head>
<body>
	<!-- ヘッダー -->
	<%@ include file="../common/header.jsp" %>

	<!-- 画面タイトル -->
	<div class="pageTitle">
		<h2>顧客編集画面</h2>
	</div>

	<!-- メッセージ表示 -->
	<div class="message" style="text-align: center;">
		<p>${message}</p>
	</div>

	<!-- エラーメッセージ表示領域 -->
	<%@ include file="../common/errorMsg.jsp" %>

	<!-- 顧客更新フォーム -->
	<form method="get" action="${pageContext.request.contextPath}/CustomerUpdateServlet">
		<div class="product-form">
			<!-- 入力項目 -->
			<div class="update-row">
				<input type="hidden" name="customerId" value="${customer.customerId}">

				<div class="form-group">
					<span>顧客名</span>
					<input type="text" name="customerName" value="${customer.customerName}">
				</div>
			</div>

			<!-- 更新ボタン -->
			<div class="form-button-wrapper">
				<input type="submit" value="更新" class="btn-common">
			</div>
		</div>
	</form>

	<!-- 顧客登録画面へのボタン -->
	<div class="form-button-wrapper">
		<form method="post" action="${pageContext.request.contextPath}/CustomerMenuTopServlet">
			<button type="submit" class="btn-common">顧客登録画面へ</button>
		</form>
	</div>
</body>
</html>
