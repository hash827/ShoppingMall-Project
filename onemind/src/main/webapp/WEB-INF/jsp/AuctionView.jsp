<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!DOCTYPE html>
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

dt {
	float: left;
}

dd {
	padding-left: 135px;
}

#contents {
	margin-top: 50px;
	margin-bottom: 100px;
	width: 950px;
	height: 700px;
	float: left;
}
</style>
<%@ include file="IncludeTop.jsp"%>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.0.min.js"></script>
<div id="wrap">
	<div id="aside">
		<%@ include file="includeSideAuction.jsp"%>
	</div>
	<div id="contents">
		<input type="hidden" id="auctionId" value="${auction.auctionId}" />
		<h2>경매 정보<button id="bt-delete-auction" type="button" class="btn btn-danger" style="margin-left: 24px">경매
				삭제</button></h2>
		<img border="0"
			src="${pageContext.request.contextPath}/images/auction/${auction.auctionImage}"
			style="width: 350px; margin-top: 10px; margin-bottom: 30px" alt=""
			width="100" />
		<table class="table">
			<thead>
				<tr>
					<th></th>
					<th>내용</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>상품명</td>
					<td>${auction.auctionName}</td>
				</tr>
				<tr>
					<td>색상</td>
					<td>${auction.auctionColor}</td>
				</tr>
				<tr>
					<td>사이즈</td>
					<td>${auction.shoeSize}</td>
				</tr>
				<tr>
					<td>착용기간</td>
					<td>${auction.wearingPeriod}</td>
				</tr>
				<tr>
					<td>상태</td>
					<td>${auction.shoesState}</td>
				</tr>
				<tr>
					<td>경매날짜</td>
					<td><fmt:formatDate value="${auction.auctionDate}"
							pattern="yyyy-MM-dd HH:mm" /> <input type="hidden"
						id="auctionDate"
						value="<fmt:formatDate value="${auction.auctionDate}" pattern="yyyy-MM-dd HH:mm" />" /></td>
				</tr>
				<tr>
					<td>경매진행시간</td>
					<td>${auction.auctionTime}분</td>
				</tr>
				<tr>
					<td>경매시작금액</td>
					<td>${auction.startPrice}</td>
				</tr>
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
				<tr>
					<td>경매상태</td>
					<td><c:out value='${s}' /></td>
				</tr>
			</tbody>
		</table>
		<h2 style="margin-top: 58px">경매 결과</h2>
		<div class="box_wrap" style="margin-top: 25px; height:150px">
			<c:choose>
				<c:when
					test="${auction.auctionState eq 'NOW' or auction.auctionState eq 'BEFORE'}">
					<h3>경매 결과가 없습니다.</h3>
				</c:when>
				<c:when test="${auction.auctionState eq 'AFTER'}">
					<h3>
						최종금액 : ${fi}<br> 낙찰자의 결제를 기다리는 중입니다.
					</h3>
				</c:when>
				<c:when test="${auction.auctionState eq 'PAID'}">
					<h3>
						최종금액 : ${fi}<br> 결제완료.
					</h3>
				</c:when>
				<c:when test="${auction.auctionState eq 'FAIL'}">
					<h4>경매유찰</h4>
				</c:when>
				<c:when test="${auction.auctionState eq 'GIVEUP'}">
					<h3>
						최종금액 : ${fi}<br> 낙찰자가 낙찰을 포기했습니다.
					</h3>
				</c:when>
			</c:choose>
		</div>
	</div>

</div>

<script type="text/javascript">
	$(function() {
		$('#bt-delete-auction')
				.click(
						function() {
							var auctionId = $('#auctionId').val();
							var auctionDate = $('#auctionDate').val();
							var year = auctionDate.substring(0, 4);
							var month = auctionDate.substring(5, 7);
							var day = auctionDate.substring(8, 10);
							var hour = auctionDate.substring(11, 13);
							var minute = auctionDate.substring(14, 16);
							var now = new Date();
							var auction = new Date(year, (month - 1), day,
									hour, (minute - 10), 0);
							if (now >= auction) {
								alert('경매 시작 10분 전까지만 삭제 가능합니다.')
								console.log('');
							} else {

								$
										.ajax({
											url : "<c:url value="/shop/auction/delete.do" />",
											type : "GET",
											data : {
												"auctionId" : auctionId
											},
											contentType : "application/json",
											success : function() {
												window.location.href = "<c:url value="/shop/auction/list.do" />";

												alert("삭제 완료");
											},
											error : function() {
												alert("삭제 실패");
											}
										});
							}

						});

	});
</script>
