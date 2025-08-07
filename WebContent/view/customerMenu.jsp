<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
	<title>顧客情報登録</title>
	
	<script>
		function confirmDelete(form) {
			if (confirm("選択した顧客データを削除します。よろしいですか?")) {
			form.submit(); // OKなら送信
			}
		}
	</script>
</head>
<body>
	<!-- ヘッダー -->
	<%@ include file="../common/header.jsp" %>

	<!-- 画面タイトル -->
	<div class="pageTitle">
		<h2>顧客情報登録</h2>
	</div>

	<!-- ログインユーザー名の表示 -->
	<div class="loginUser" style="text-align: center;">
		<p>ログインユーザー名：${loginUser.name}</p>
	</div>

	<div class="message" style="text-align: center";>
		<p>${message}</p>
	</div>
	
	<!-- エラーメッセージ表示領域 -->
	<%@ include file="../common/errorMsg.jsp" %>
	
<div class="customer-form-wrapper">

    <!-- 顧客登録フォーム -->
    <form method="post" action="${pageContext.request.contextPath}/CustomerRegisterServlet" class="customer-form">
        <div class="form-group">
            <span>顧客名</span>
            <input type="text" name="customerName">
        </div>
        <div class="form-group">
            <span>&nbsp;</span> <!-- ボタンのラベル位置 -->
            <input type="submit" value="登録" class="btn-register">
        </div>
        <div class="form-group">
            <span>&nbsp;</span> <!-- ダミー枠 -->
        </div>
    </form>

    <!-- 検索フォーム -->
    <form method="post" action="${pageContext.request.contextPath}/CustomerSearchServlet" class="customer-form">
        <div class="form-group">
            <span>顧客ID検索</span>
            <input type="text" name="customerId">
        </div>
        <div class="form-group">
            <span>顧客名検索</span>
            <input type="text" name="customerName">
        </div>
        <div class="form-group">
            <span>&nbsp;</span>
            <input type="submit" value="検索" class="btn-search">
        </div>
    </form>

</div>


<!-- 商品一覧の表示 -->
	<table class="product-table">
		<thead>
			<tr>
				<th>操作</th>
				<th>顧客ID</th>
				<th>顧客名</th>
			</tr>
		</thead>
		<tbody>
		
			<c:forEach var="customer" items="${CustomerList}">
			    <tr>
			       <td>
					  <div class="btn-group-horizontal">
					    <form method="post" action="${pageContext.request.contextPath}/CustomerDeleteServlet" onsubmit="return false;">
						    <input type="hidden" name="customerId" value="${customer.customerId}">
						    <button type="button" class="btn-action btn-delete" onclick="confirmDelete(this.form)">削除</button>
						</form>

					
					    <form method="post" action="${pageContext.request.contextPath}/CustomerUpdateServlet">
					        <input type="hidden" name="customerId" value="${customer.customerId}">
					        <button type="submit" class="btn-action btn-edit">編集</button>
					    </form>
					  </div>
					</td>
					
			        <td>${customer.customerId}</td>
			        <td>${customer.customerName}</td>
			    </tr>
			</c:forEach>

		</tbody>
	</table>

	<!-- logout -->
	<div style="text-align:center; margin-top:1em;">
		<form method="post" action="Logout" style="display:inline-block; width:40%;">
			<button type="submit">ログアウト</button>
		</form>
	</div>
</body>
</html>
