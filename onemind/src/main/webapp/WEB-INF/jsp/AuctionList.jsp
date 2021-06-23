<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
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

table {
	/* border: 1px solid #444444;
	border-collapse: collapse; */
}

th, td {
	/* border: 1px solid #444444; */
}
#auctionListTable th, #auctionListTable td {
	width: 230px;
	text-align: center;
}
</style>
<%@ include file="IncludeTop.jsp"%>
<div id="wrap">
	<div id="aside">
		<%@ include file="includeSideAuction.jsp"%>
	</div>
		<div id="contents">
		<p>
			<font size="4"><b>나의 경매 리스트 </b></font>	<br>
		</p>
		<table class="table" id="auctionListTable">
			<thead>
				<tr>
					<th>상품명</th>
					<th>경매시작일</th>
					<th>경매시간</th>
					<th>경매상태</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="auction" items="${auctionList.pageList}"
					varStatus="atSt">
					<tr id="${auction.auctionId}">
						<td><a
							href="<c:url value="/shop/auction/view.do?auctionId=${auction.auctionId}" />">${auction.auctionName}</a>
						</td>
						<%-- <td>${auction.auctionDate}</td> --%>
						<td><fmt:formatDate value="${auction.auctionDate}"
								pattern="yyyy-MM-dd HH:mm" /></td>
						<td>${auction.auctionTime}</td>
						<c:choose>
							<c:when test="${auction.auctionState eq 'NOW'}">
								<c:set var="s" value="경매중" />
							</c:when>
							<c:when test="${auction.auctionState eq 'BEFORE'}">
								<c:set var="s" value="경매예정" />
							</c:when>
							<c:when test="${auction.auctionState eq 'AFTER'}">
								<c:set var="s" value="경매완료" />
							</c:when>
							<c:when test="${auction.auctionState eq 'PAID'}">
								<c:set var="s" value="결제완료" />
							</c:when>
							<c:when test="${auction.auctionState eq 'FAIL'}">
								<c:set var="s" value="경매유찰" />
							</c:when>
							<c:when test="${auction.auctionState eq 'GIVEUP'}">
								<c:set var="s" value="낙찰포기" />
							</c:when>
						</c:choose>
						<td><c:out value='${s}' /></td>
					</tr>
				</c:forEach>
				<tr>
					<td><c:if test="${!auctionList.firstPage}">
							<a
								href='<c:url value="/shop/auction/list2.do">
           								 <c:param name="page" value="previous"/></c:url>'>
								<font><B>&lt;&lt; Prev</B></font>
							</a>

						</c:if> <c:if test="${!auctionList.lastPage}">
							<a
								href='<c:url value="/shop/auction/list2.do">
           								 <c:param name="page" value="next"/></c:url>'>
								<font><B>&gt;&gt; next</B></font>
							</a>
						</c:if></td>
				</tr>
			</tbody>
		</table>
		<input type="hidden" id="isBlackUser" value="${isBlackUser}" />
		<a href="<c:url value="/shop/auction/createForm.do"/>" onclick="return checkBlackList();"
			style="margin-top: 70px">
			<button id="bt-create-auction"type="button" class="btn btn-primary" style="float:right">새
				경매 생성</button>
			<!-- <button id="bt-create-auction"
				class="btn" type="button" style="margin-top: 216px; float: right">새
				경매 생성</button> --></a>	
	</div>
</div>
<script type="text/javascript">
	function checkBlackList() {
		var isBlackUser = $('#isBlackUser').val();
		
		if (isBlackUser == "true") {
			alert("경매를 생성할 수 없는 사용자입니다.");
			return false;
		} else {
			return true;
		}
	}
</script>

