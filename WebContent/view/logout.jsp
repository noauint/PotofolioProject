<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
	<title>ログアウト完了｜ダイレクトバンク</title>
</head>
<body>
	<%@ include file="/common/header.jsp" %>

	<!-- ページ内容タイトル -->
	<div class="pageTitleArea">
		<div class="pageTitleAreaInner" style="text-align:center">
			<h2>ログアウトが完了しました</h2>
			<div class="right-align"></div>
		</div>
		<hr>
	</div>

	<%@ include file="../common/errorMsg.jsp" %>

	<!-- メインコンテンツ表示領域 -->
	<div class="main" style="text-align:center">
		<a href="${pageContext.request.contextPath}/Login">ログイン画面へ戻る</a>
	</div>
</body>
</html>
