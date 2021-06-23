<%@ include file="IncludeTop.jsp"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

<style>
#wrap {
	width: 1300px;
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
	width: 1050px;
	height: 700px;
	float: left;
}

 #secondhandMyListTable td {
	width: 260px;
	text-align: center;
}
#secondhandMyListTable th {
	width: 100px;
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
						value="등록한 상품 리스트" /></font></b>
			<table class="table" id="secondhandMyListTable">
				<tr>
					<!-- <td><b>상품 아이디</b></td> -->
					<td><b>상품명</b></td>
					<td><b>가격</b></td>
					<td><b>등록일</b></td>
					<td><b>거래상태</b></td>
					<td><b>구매자</b></td>
					<td><b>구매일</b></td>
					<th>&nbsp;</th>
					<th>&nbsp;</th>
				</tr>
				<c:forEach var="secondhand" items="${secondhandList.pageList}">
					<tr><%--
						<td><c:choose>
								<c:when test="${fn:length(secondhand.secondhandId) gt 10}">
									<c:out value="${fn:substring(secondhand.secondhandId, 0, 9)}" />...
											</c:when>
								<c:otherwise>
									<c:out value="${secondhand.secondhandId}">
									</c:out>
								</c:otherwise>
							</c:choose></td>
							--%>
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
							</c:choose></td>
						<td><c:out value="${secondhand.price}" /></td>
						<td><c:set var="secondhandDate"
								value="${secondhand.secondhandDate}" /> <fmt:formatDate
								value="${secondhandDate}" pattern="yyyy-MM-dd HH:mm" /></td>
						<td><c:choose>
								<c:when test="${secondhand.secondhandState eq 'COMPLETED'}">
									<c:out value="거래완료" />
								</c:when>
								<c:when test="${secondhand.secondhandState eq 'NOW'}">
									<c:out value="판매중" />
								</c:when>
								<c:otherwise>
									<c:out value="예약중" />
								</c:otherwise>
							</c:choose></td>
						
						<td> <c:choose>
							<c:when test="${empty secondhand.buyer}">
								<c:out value="----" />
							</c:when>
							<c:otherwise>
								<c:out value="${secondhand.buyer}" />
							</c:otherwise>
							</c:choose>
						</td>
						<td>
							 <c:choose>
							<c:when test="${empty secondhand.pDate}">
								<c:out value="----" />
							</c:when>
							<c:otherwise>
								<c:set var="pDate"
								value="${secondhand.pDate}" /> <fmt:formatDate
								value="${pDate}" pattern="yyyy-MM-dd HH:mm" />
							</c:otherwise>
							</c:choose>
						</td>

						<th><c:choose>
								<c:when test="${secondhand.secondhandState ne 'COMPLETED'}">
									<b><a
										href='<c:url value="/shop/secondhandEdit.do">
            <c:param name="secondhandId" value="${secondhand.secondhandId}"/></c:url>'>
											수정 </a></b>
								</c:when>
							</c:choose>
						</th>
						<th>
						<c:choose>
							<c:when test="${secondhand.secondhandState ne 'COMPLETED'}">
							<a
							href='<c:url value="/shop/secondhandDelete.do">
            <c:param name="secondhandId" value="${secondhand.secondhandId}"/></c:url>'>
								삭제 </a>
								</c:when>
							</c:choose>
						</th>
					</tr>
				</c:forEach>
				<tr>
					<td><c:if test="${!secondhandList.firstPage}">
							<a
								href='<c:url value="/shop/secondhandViewMyList2.do">
            <c:param name="page" value="previous"/></c:url>'>
								<font><B>&lt;&lt; Prev</B></font>
							</a>
						</c:if> <c:if test="${!secondhandList.lastPage}">
							<a
								href='<c:url value="/shop/secondhandViewMyList2.do">
            <c:param name="page" value="next"/></c:url>'>
								<font><B>Next &gt;&gt;</B></font>
							</a>
						</c:if></td>
				</tr>
			</table>
		</div>
	</div>
</div>