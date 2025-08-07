<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<!-- エラーメッセージ表示領域 -->
	<div class="errorMsg">
		<c:choose>
			<c:when test="${errorMsgList.size() > 1}">
				<c:forEach var="errorMsg" items="${errorMsgList}">
					${errorMsg}<br>
				</c:forEach>
			</c:when>
			<c:when test="${errorMsgList.size() == 1}">
				${errorMsgList[0]}
			</c:when>
			<c:otherwise>
				${errorMsg}
			</c:otherwise>
		</c:choose>
	</div>
