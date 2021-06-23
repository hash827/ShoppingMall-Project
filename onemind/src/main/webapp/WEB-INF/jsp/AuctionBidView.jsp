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

#payAlert {
	margin-top:50px;
	margin-bottom: 50px;
	text-align: center;
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
		<input type="hidden" id="auctionId" value="${auctionBuyer.auctionId}" />
		<h2>결제 정보</h2>
		<c:choose>
			<c:when test="${(auction.auctionState eq 'PAYWAIT')||(auction.auctionState eq 'AFTER')||(auction.auctionState eq 'GIVEUP')}">
				<div id="payAlert"><c:out value='해당되는 결제 정보가 없습니다. '/></div>
			</c:when>
			<c:when test="${auction.auctionState eq 'PAID'}">
				<table class="table">
			<thead>
				<tr>
					<th></th>
					<th>결제내용</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>은행정보</td>
					<td>${auctionBuyer.bank}</td>
				</tr>
				<tr>
					<td>결제금액</td>
					<td>${auctionBuyer.price}</td>
				</tr>
				<tr>
					<td>카드번호</td>
					<td>${auctionBuyer.cardNumber}</td>
				</tr>
				<tr>
					<td>결제일시</td>
					<td><fmt:formatDate value="${auctionBuyer.paidDate}"
							pattern="yyyy-MM-dd HH:mm" /></td>
				</tr>
				<tr>
					<td>결제상태</td>
					<c:choose>
						<c:when test="${(auctionBuyer.state eq 'PAYWAIT')||(auctionBuyer.state eq 'AFTER')}">
							<c:set var="s" value="결제대기" />
						</c:when>
						<c:when test="${auctionBuyer.state eq 'PAID'}">
							<c:set var="s" value="결제완료" />
						</c:when>
						<c:when test="${auctionBuyer.state eq 'GIVEUP'}">
							<c:set var="s" value="낙찰포기" />
						</c:when>
					</c:choose>
					<td><c:out value='${s}'/></td>
				</tr>
			</tbody>
		</table>
			</c:when>
		</c:choose>
		
		<h2>경매 정보
		<c:if test="${!(auction.auctionState eq 'PAID')&&!(auction.auctionState eq 'GIVEUP')}">
			<a href="<c:url value="/shop/auction/auctionGiveUp.do?auctionId=${auction.auctionId}"/>" onclick="return confirm('낙찰을 포기할 경우 앞으로의 경매 참여가 제한될 수 있습니다. \n포기하시겠습니까?');">
				<button id="bt-giveup-auction" class="btn btn-danger  btn-primary" type="button" style="margin-left: 24px">낙찰포기</button>
			</a>
		</c:if>
		<c:if test="${!(auction.auctionState eq 'PAID')&&!(auction.auctionState eq 'GIVEUP')}">
			<a href="<c:url value="/shop/auction/paymentView.do?auctionId=${auction.auctionId}"/>"><button id="bt-payment-auction"  class="btn btn-primary" type="button" style="margin-left: 24px">결제하기</button></a>
		</c:if>
		
		</h2>	
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
			</tbody>
		</table>
		
		
		
	</div>

</div>

<script type="text/javascript">

</script>
