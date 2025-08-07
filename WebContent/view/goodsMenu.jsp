<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
	<title>商品情報登録</title>
	<script>
		function confirmDelete(form) {
			if (confirm("選択した商品データを削除します。よろしいですか?")) {
				form.submit();
			}
		}
	</script>
</head>
<body>

	<!-- ヘッダー -->
	<%@ include file="../common/header.jsp" %>

	<!-- タイトル -->
	<div class="pageTitle">
		<h2>商品情報登録</h2>
	</div>

	<!-- ログインユーザー名 -->
	<div class="loginUser" style="text-align: center;">
		<p>ログインユーザー名：${loginUser.name}</p>
	</div>

	<!-- メッセージ -->
	<div class="message" style="text-align: center;">
		<p>${message}</p>
	</div>

	<!-- エラーメッセージ -->
	<%@ include file="../common/errorMsg.jsp" %>

	<!-- 登録フォーム -->
	<form method="post" action="${pageContext.request.contextPath}/GoodsRegisterServlet" class="customer-form">
		<div class="form-group">
			<span>商品ID（４桁）</span>
			<input type="text" name="itemID">
		</div>
		<div class="form-group">
			<span>商品名</span>
			<input type="text" name="itemName">
		</div>
		<div class="form-group">
			<span>単価(9桁)</span>
			<input type="text" name="Price">
		</div>
		<div class="form-group">
			<span>原価(9桁)</span>
			<input type="text" name="cost">
		</div>
		<div class="form-group">
			<span>&nbsp;</span>
			<input type="submit" value="登録" class="btn-register">
		</div>
	</form>

	<!-- 検索フォーム -->
	<form method="post" action="${pageContext.request.contextPath}/GoodsSearchServlet" class="customer-form">
		<div class="form-group">
			<span>ID検索</span>
			<input type="text" name="goodsId">
		</div>
		<div class="form-group">
			<span>商品名検索</span>
			<input type="text" name="goodsName">
		</div>
		<div class="form-group">
			<span>&nbsp;</span>
			<input type="submit" value="検索" class="btn-search">
		</div>
	</form>

	<!-- 商品一覧 -->
	<table class="product-table">
		<thead>
			<tr>
				<th>操作</th>
				<th>商品ID</th>
				<th>商品名</th>
				<th>単価</th>
				<th>原価</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="goods" items="${goodsList}">
				<tr>
					<td>
						<div class="btn-group-horizontal">
							<form method="post" action="${pageContext.request.contextPath}/GoodsDeleteServlet" onsubmit="return false;">
								<input type="hidden" name="goodsId" value="${goods.goodsId}">
								<button type="button" class="btn-action btn-delete" onclick="confirmDelete(this.form)">削除</button>
							</form>
							<form method="post" action="${pageContext.request.contextPath}/GoodsUpdateServlet">
								<input type="hidden" name="goodsId" value="${goods.goodsId}">
								<button type="submit" class="btn-action btn-edit">編集</button>
							</form>
						</div>
					</td>
					<td>${goods.goodsId}</td>
					<td>${goods.goodsName}</td>
					<td>${goods.goodsPrice}</td>
					<td>${goods.goodsCost}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<!-- ログアウト -->
	<div style="text-align:center; margin-top:1em;">
		<form method="post" action="Logout" style="display:inline-block; width:40%;">
			<button type="submit">ログアウト</button>
		</form>
	</div>

</body>
</html>
