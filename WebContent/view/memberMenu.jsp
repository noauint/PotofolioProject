<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
	<title>メニュー</title>
</head>
<body>
	<!-- ヘッダー -->
	<%@ include file="../common/header.jsp" %>

	<!-- 画面タイトル -->
	<div class="pageTitle">
		<h2>メニュー</h2>
	</div>
	
	<div class="loginUser" style="text-align: center;">
		<p>ログインユーザー名：${loginUserName }</p>
	</div>

	<!-- メインコンテンツ表示領域 -->
	<div class="main">
	<!-- 商品情報登録に遷移する -->
		<form method="post" action="${pageContext.request.contextPath}/GoodsMenuTopServlet">
			<button type="submit">商品情報登録</button>
		</form>

	<!-- 顧客情報登録画面に遷移する -->
		<form method="post" action="${pageContext.request.contextPath}/CustomerMenuTopServlet">
			<button type="submit">顧客情報登録</button>
		</form>

	<!-- 売上情報登録に遷移する -->
		<form method="post" action="${pageContext.request.contextPath}/ProceedsMenuTopServlet">
			<button type="submit">売上情報登録</button>
		</form>
	</div>
	
	<!-- logout -->
	<div style="text-align:center; margin-top:1em;">
		<form method="post" action="${pageContext.request.contextPath}/Logout" style="display:inline-block; width:50%;">
			<button type="submit">ログアウト</button>
		</form>
	</div>
</body>
</html>
