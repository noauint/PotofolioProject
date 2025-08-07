<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="../css/base.css">
	<title>システムエラー</title>
</head>
<body>

	<%@ include file="/common/header.jsp" %>

	<!-- ページ内容タイトル -->
	<div class="pageTitleArea">
		<div class="pageTitleAreaInner">
			<h2>システムエラー</h2>
		</div>
		<hr>
	</div>

	<%@ include file="/common/errorMsg.jsp" %>

	<!-- メインコンテンツ表示領域 -->
	<div class="main">
		トップページに戻るには<a href="${pageContext.request.contextPath}/TopMenu">こちら</a>をクリックしてください。
	</div>
</body>
</html>
