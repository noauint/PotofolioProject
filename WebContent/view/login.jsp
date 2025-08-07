<!-- http://localhost:8080/custome_rise/Login -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/base.css?v=20250723">
<title>ログイン</title>
</head>
<body>
	<!-- ヘッダー -->
	<%@ include file="/common/header.jsp"%>

	<!-- 画面タイトル -->
	<div class="pageTitle">
		<h2>ログイン</h2>
	</div>

	<%@ include file="../common/errorMsg.jsp"%>

	<!-- メインコンテンツ表示領域 -->
	<div class="main">
		<form method="post" action="${pageContext.request.contextPath}/Login">
			<table>
				<tbody>
					<tr>
  <tr>
  <td colspan="2" style="text-align: center;">
    <div class="login-form-wrapper">
      <label class="login-label">ユーザーID：</label>
      <input type="text" name="user_id" value="${userId}" required="required" class="login-input">
    </div>
  </td>
</tr>
<tr>
  <td colspan="2" style="text-align: center;">
    <div class="login-form-wrapper">
      <label class="login-label">パスワード：</label>
      <input type="password" name="login_password" required="required" class="login-input">
    </div>
  </td>
</tr>


					<tr>
					    <td class="middle-align" colspan="2">
					        <div class="button_area login-button-area">
					            <button type="submit" class="login-btn">ログイン</button>
					        </div>
					    </td>
					</tr>

				</tbody>
			</table>
		</form>
	</div>
</body>
</html>