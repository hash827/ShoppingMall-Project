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

#secondhandFavoriteListTable th, #secondhandFavoriteListTable td {
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
						value="찜한 상품 리스트" /></font></b>
			<table class="table" id="secondhandFavoriteListTable">
				<tr>
					<td><b>상품 아이디</b></td>
					<td><b>상품명</b></td>
					<td><b>가격</b></td>
					<td><b>거래상태</b></td>
					<td><b>해제</b></td>
				</tr>

				<c:forEach var="secondhandFavorite" items="${secondhand}"
					varStatus="status">
					<tr>
						<td><c:choose>
								<c:when
									test="${fn:length(secondhandFavorite['secondhandId']) gt 10}">
									<c:out
										value="${fn:substring(secondhandFavorite['secondhandId'], 0, 9)}" />
											</c:when>
								<c:otherwise>
									<c:out value="${secondhandFavorite['secondhandId']}" />
								</c:otherwise>
							</c:choose></td>
							
							
						<td><c:choose>
								<c:when test="${secondhandFavorite['secondhandState'] eq 'COMPLETED'}">
									<b><c:out value="${fn:substring(secondhandFavorite['title'], 0, 9)}" /></b>
								</c:when>
								<c:when test="${fn:length(secondhandFavorite['title']) gt 10}">
									<b><a
										href='<c:url value="/shop/secondhandViewDetail.do">
            <c:param name="secondhandId" value="${secondhandFavorite['secondhandId']}"/></c:url>'>
											<c:out
												value="${fn:substring(secondhandFavorite['title'], 0, 9)}" />...
									</a></b>
								</c:when>
								<c:otherwise>
									<b><a
										href='<c:url value="/shop/secondhandViewDetail.do">
            								<c:param name="secondhandId" value="${secondhandFavorite['secondhandId']}"/></c:url>'>
											<c:out value="${secondhandFavorite['title']}" />
									</a></b>
								</c:otherwise>
							</c:choose></td>
							
							
						<td><c:out value="${secondhandFavorite['price']}" /></td>

						<td><c:choose>
								<c:when test="${secondhandFavorite['secondhandState'] eq 'COMPLETED'}">
									<c:out value="거래완료" />
								</c:when>
								<c:when test="${secondhandFavorite['secondhandState'] eq 'NOW'}">
									<c:out value="판매중" />
								</c:when>
								<c:otherwise>
									<c:out value="예약중" />
								</c:otherwise>
							</c:choose>
							</td>
						<td><a
							href='<c:url value="/shop/secondhandFavoriteDelete.do">
            <c:param name="secondhandFavoriteId" value="${secondhandFavorite.secondhandFavoriteId}"/></c:url>'>
								찜 해제 </a></td>
					</tr>
				</c:forEach>

				<tr>
					<td><c:if test="${!secondhandFavoriteList.firstPage}">
							<a
								href='<c:url value="/shop/secondhandViewFavoriteList2.do">
            <c:param name="page" value="previous"/></c:url>'>
								<font><B>&lt;&lt; Prev</B></font>
							</a>
						</c:if> <c:if test="${!secondhandFavoriteList.lastPage}">
							<a
								href='<c:url value="/shop/secondhandViewFavoriteList2.do">
            <c:param name="page" value="next"/></c:url>'>
								<font><B>Next &gt;&gt;</B></font>
							</a>
						</c:if></td>
				</tr>
			</table>
		</div>
	</div>
</div>