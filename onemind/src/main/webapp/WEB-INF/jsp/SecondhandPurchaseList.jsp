<%@ include file="IncludeTop.jsp"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

<style>
#wrap {
	width: 1200px;
	height: 700px;
	margin: 0 auto;
}

#aside {
	width: 250px;
	height: 700px;
	float: left;
}

#contents {
	margin-top: 50px;
	margin-bottom: 100px;
	width: 950px;
	height: 700px;
	float: left;
}

#secondhandPurchaseListTable th, #secondhandPurchaseListTable td {
	width: 230px;
	text-align: center;
}
</style>

<div id="wrap">
	<div id="aside">
		<%@ include file="IncludeSideSecondhand.jsp"%>
	</div>
	<div id="contents">
		<div align="center">
			<b><font size="4"><c:out
						value="구매 상품 리스트" /></font></b>
			<table class="table" id="secondhandPurchaseListTable">
				<tr>
					<td><b>상품 아이디</b></td>
					<td><b>상품명</b></td>
					<td><b>가격</b></td>
					<td><b>구매일</b></td>
				</tr>
				<c:forEach var="secondhand" items="${secondhandList.pageList}">
					<tr>
						<td><c:choose>
								<c:when test="${fn:length(secondhand.secondhandId) gt 10}">
									<c:out value="${fn:substring(secondhand.secondhandId, 0, 9)}" />...
											</c:when>
								<c:otherwise>
									<c:out value="${secondhand.secondhandId}">
									</c:out>
								</c:otherwise>
							</c:choose>
						</td>
						
						<td><c:choose>
								<c:when test="${fn:length(secondhand.title) gt 10}">
									<b><a
										href='<c:url value="/shop/secondhandViewDetail.do">
            <c:param name="secondhandId" value="${secondhand.secondhandId}"/></c:url>'>
											<c:out value="${fn:substring(secondhand.title, 0, 9)}" />...
									</a></b>
								</c:when>
								<c:otherwise>
									<b><a
										href='<c:url value="/shop/secondhandViewDetail.do">
            <c:param name="secondhandId" value="${secondhand.secondhandId}"/></c:url>'>
											<c:out value="${secondhand.title}" />
									</a></b>
								</c:otherwise>
							</c:choose>
						</td>
						
						<td><c:out value="${secondhand.price}" /></td>
						<td><c:set var="pDate"
								value="${secondhand.pDate}" /> <fmt:formatDate
								value="${pDate}" pattern="yyyy-MM-dd HH:mm" /></td>
					</tr>
				</c:forEach>
				<tr>
					<td><c:if test="${!secondhandList.firstPage}">
							<a
								href='<c:url value="/shop/secondhandViewPurchaseList2.do">
            <c:param name="page" value="previous"/></c:url>'>
								<font><B>&lt;&lt; Prev</B></font>
							</a>
						</c:if> <c:if test="${!secondhandList.lastPage}">
							<a
								href='<c:url value="/shop/secondhandViewPurchaseList2.do">
            <c:param name="page" value="next"/></c:url>'>
								<font><B>Next &gt;&gt;</B></font>
							</a>
						</c:if></td>
				</tr>
			</table>
		</div>
	</div>
</div>