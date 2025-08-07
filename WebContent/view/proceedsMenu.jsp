<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
	<title>売上情報登録</title>
	
	<script>
		function confirmDelete(form) {
			if (confirm("選択した売上データを削除します。よろしいですか?")) {
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
		<h2>売上情報登録</h2>
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

	<!-- 登録フォーム -->
	<form method="post" action="${pageContext.request.contextPath}/ProceedsResisterServlet">
	  <div class="product-form">
	    <div class="input-row">
	      <div class="form-group">
	        <span>担当者名</span>
	        <input type="text" name="person">
	      </div>
	      <div class="form-group">
	        <span>売上日</span>
	        <input type="date" name="sales_date">
	      </div>
	      <div class="form-group">
	        <span>顧客ID</span>
	        <input type="text" name="customer_id">
	      </div>
	      <div class="form-group">
	        <span>商品ID</span>
	        <input type="text" name="goods_id">
	      </div>
	      <div class="form-group">
	        <span>数量</span>
	        <input type="text" name="pcs">
	      </div>
	      <div class="form-group">
	        <span>&nbsp;</span>
	        <input type="submit" value="登録" class="btn-register">
	      </div>
	    </div>
	  </div>
	</form>


	<!-- 検索フォーム -->
		<form method="post" action="${pageContext.request.contextPath}/ProceedsRegisterSearchServlet">
		  <div class="input-row">
		    <div class="form-group">
		      <span>売上ID検索</span>
		      <input type="text" name="searchID">
		    </div>
		    <div class="form-group">
		      <span>売上日検索</span>
		      <input type="date" name="searchData">
		    </div>
		    <div class="form-group">
		      <span>&nbsp;</span>
		      <input type="submit" value="検索" class="btn-search">
		    </div>
		  </div>
		</form>


	<!-- 売上一覧表示 -->
	<table class="product-table">
		<thead>
			<tr>
				<th>操作</th>
				<th>売上ID</th>
				<th>売上日</th>
				<th>商品名</th>
				<th>売上個数</th>
				<th>顧客名</th>
				<th>担当者</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="customer" items="${CustomerRegisterList}">
				<tr>
					<td>
					  <div class="btn-group-horizontal">
					    <form method="post" action="${pageContext.request.contextPath}/ProceedsDeleteServlet" onsubmit="return false;">
						    <input type="hidden" name="searchID" value="${customer.proceedsId}">
						    <button type="button" class="btn-action btn-delete" onclick="confirmDelete(this.form)">削除</button>
						</form>

					
					    <form method="post" action="${pageContext.request.contextPath}/ProceedsUpdateServlet">
					        <input type="hidden" name="searchID" value="${customer.proceedsId}">
					        <input type="hidden" name="searchID" value="${customer.goodsId}">
					        <input type="hidden" name="searchID" value="${customer.customerId}">
					        <button type="submit" class="btn-action btn-edit">編集</button>
					    </form>
					  </div>
					</td>
					<td>${customer.proceedsId}</td>
					<td>${customer.salesDate}</td>
					<td>${customer.goodsName}</td>
					<td>${customer.pcs}</td>
					<td>${customer.customerName}</td>
					<td>${customer.person}</td>
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
