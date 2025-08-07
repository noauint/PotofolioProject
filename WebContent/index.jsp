<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
// Webブラウザに「ユーザーログインページ」にリダイレクトするように要求する
response.sendRedirect("customer/Login");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/base.css">
	<title>ようこそ｜ダイレクトバンク</title>
</head>
<body>

	<%@ include file="common/customerHeader.jsp" %>

	<!-- ページ内容タイトル -->
	<h2>ユーザーログイン</h2>

	<%@ include file="common/errorMsg.jsp" %>

	<!-- メインコンテンツ表示領域 -->
	<div class="main">
		ユーザーログインページは<a href="customer/Login">こちら</a>をクリックしてください。
	</div>
</body>
</html>
