<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<style>
#wrap {
		width: 1200px;
		height: 700px;
		margin: 0 auto;
	}
#aside{
	width: 250px;
	height: 700px;
	float: left;
}
#contents{
	margin-top: 50px;
	margin-bottom: 100px;
	width: 950px;
	height: 700px;
	float: left;
}
#auctionListTable th, #auctionListTable td {
	width: 230px;
	text-align: center;
}
.btn.btn-primary {
	height: 30px;
	font-size : 13px;
}
	
table {
	/* border: 1px solid #444444;
	border-collapse: collapse; */
}

th, td {
	/* border: 1px solid #444444; */
}
</style>
<%@ include file="IncludeTop.jsp"%>
<div id="wrap">
<div id="aside" >
	<%@ include file="includeSideAuction.jsp"%>
</div>
<div id="contents">
	<p>
		<font size="4"><b>찜 목록 </b></font>	<br>
	</p>
	<table class="table" id="auctionListTable">
		<thead>
			<tr>
				<th>상품명</th>
				<th>경매시작일</th>
				<th>경매시간</th>
				<th>경매상태</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="auction" items="${auctionList.pageList}" varStatus="atSt">
				<tr id="${auction.auctionId}">
					<td><c:set var="state" value="${auction.auctionState}" /> 
					<c:if test="${state eq 'NOW'}" var="checkSt">
						<a href="<c:url value="/shop/auction/mainView.do?auctionId=${auction.auctionId}"/>">${auction.auctionName}</a>
					</c:if> 
					<c:if test="${!checkSt}">
						${auction.auctionName}
					</c:if>
					</td>
					<td><fmt:formatDate value="${auction.auctionDate}"
							pattern="yyyy-MM-dd HH:mm" /></td>
					<td>${auction.auctionTime}</td>
					<td>
						<c:set var="state" value="${auction.auctionState}" />
						<c:choose>
							<c:when test="${state eq 'BEFORE'}">
								경매예정
							</c:when>
							<c:when test="${state eq 'NOW'}">
								경매중
							</c:when>
							<c:when test="${state eq 'AFTER' || state eq 'PAID' || state eq 'FAIL' || state eq 'GIVEUP' || state eq 'PAYWAIT'}">
								경매완료
							</c:when>
						</c:choose>
					</td>
					<td><a href="<c:url value="/shop/auction/favorite/delete.do?auctionId=${auction.auctionId}" />">
						<button type="button" class="btn btn-danger btn-sm">찜해제</button></a></td>
				</tr>
			</c:forEach>
			<tr>
					<td><c:if test="${!auctionList.firstPage}">
							<a
								href='<c:url value="/shop/auction/favorite/list2.do">
           								 <c:param name="page" value="previous"/>
           							</c:url>'>
								<font><B>&lt;&lt; Prev</B></font>
							</a>

						</c:if> <c:if test="${!auctionList.lastPage}">
							<a
								href='<c:url value="/shop/auction/favorite/list2.do">
           								 <c:param name="page" value="next"/>
           							</c:url>'>
								<font><B>&gt;&gt; next</B></font>
							</a>
						</c:if></td>
				</tr>
		</tbody>
	</table>

</div>
</div>
