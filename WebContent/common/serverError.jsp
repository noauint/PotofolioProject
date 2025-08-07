<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="../css/base.css">
	<title>サーバーエラー</title>
</head>
<body>
	<%@ include file="header.jsp" %>

	<h2 align="center">サーバーエラー</h2>
	<div align="center" class="error-div error-div-center">
		サーバエラーが発生しました。ブラウザを閉じてください。
	</div>
	<%-- サーバーエラーの詳細をコンソールへ出力する --%>
	<% System.out.println("エラーコード：" + request.getAttribute("javax.servlet.error.status_code").toString()); %>
	<% System.out.println("エラー詳細：\n" + request.getAttribute("javax.servlet.error.message").toString()); %>
	<%
		Throwable throwable = (Throwable)request.getAttribute("javax.servlet.error.exception");
		throwable.printStackTrace(System.out);
	%>
</body>
</html>
