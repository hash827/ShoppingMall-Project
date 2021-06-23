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
#auctionStateRadio {
	float : right;
}
</style>
<%@ include file="IncludeTop.jsp"%>
<div id="wrap">
	<div id="aside">
		<%@ include file="includeSideAuction.jsp"%>
	</div>
		<div id="contents">
		<form id="fAuctionState"
			action="<c:url value="/shop/auction/myBidList.do" />">
			<table id="auctionStateRadio">
				<tr>
					<!-- 전체:ALL/결제대기:(PAYWAIT/AFTER)/결제완료:PAID/낙찰포기:GIVEUP) -->
					<td><label><input type="radio" name='auctionState'
							value='ALL' />전체</label> <!--  checked='checked' -->
					<td><label><input type="radio" name='auctionState'
							value='PAYWAIT' />결제대기</label>
					<td><label><input type="radio" name='auctionState'
							value='PAID' />결제완료</label>
					<td><label><input type="radio" name='auctionState'
							value='GIVEUP' />낙찰포기</label> 
					<input type="submit" class="btn btn-primary" value="정렬">
				</tr>
			</table>
		</form>
		<input type="hidden" id="nowAuctionState" value="${auctionState}" />
		<p>
			<font size="4"><b>나의 낙찰 리스트 </b></font>	<br>
		</p>
		<table class="table" id="auctionListTable">
			<thead>
				<tr>
					<th>상품명</th>
					<th>결제금액</th>
					<th>은행</th>
					<th>카드번호</th>
					<th>결제일시</th>
					<th>결제상태</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="auction" items="${auctionList.pageList}"
					varStatus="atSt">
					<input type="hidden" value="${auction.auctionId}">
					<tr id="${auction.auctionId}">
						<c:set var="doneLoop" value="false" />
						<c:set var="isRight" value="false" />
						<c:forEach var="item"
								items="${auctionListName}">
								<c:if test="${not doneLoop}">
									<c:if test="${auction.auctionId == item.auctionId}"
										var="isRight"></c:if>
									<c:if test="${isRight}">
										<c:set var="doneLoop" value="true" />
										<c:set var="auctionName" value="${item.auctionName}"/> 
									</c:if>
								</c:if>
						</c:forEach> 
						<td><a href="<c:url value="/shop/auction/bidView.do?auctionId=${auction.auctionId}" />">
						${auctionName}</a></td>
						<td>${auction.price}</td>
						<c:set var="bank" value="${auction.bank}"/> 
						<c:if test="${auction.bank == null}">
							<c:set var="bank" value=" - - -"/> 
						</c:if>
						<td>${bank}</td>
						<c:set var="cardNum" value="${auction.cardNumber}"/> 
						<c:if test="${auction.cardNumber == null}">
							<c:set var="cardNum" value=" - - -"/> 
						</c:if>
						<td>${cardNum}</td>
						<td>
						<c:if test="${auction.paidDate == null}">
							<%-- <c:set var="date" value=" - - -"/>  --%>
							<c:out value=' - - - '/>
						</c:if>
						<c:if test="${auction.paidDate != null}">
							<fmt:formatDate value="${auction.paidDate}" 
								pattern="yyyy-MM-dd HH:mm"/>
						</c:if>
						</td>
						<c:choose>
							<c:when test="${(auction.state eq 'PAYWAIT')||(auction.state eq 'AFTER')}">
								<c:set var="s" value="결제대기" />
							</c:when>
							<c:when test="${auction.state eq 'PAID'}">
								<c:set var="s" value="결제완료" />
							</c:when>
							<c:when test="${auction.state eq 'GIVEUP'}">
								<c:set var="s" value="낙찰포기" />
							</c:when>
						</c:choose>
						<td><c:out value='${s}'/></td>
					</tr>
				</c:forEach>
				<tr>
					<td><c:if test="${!auctionList.firstPage}">
							<a
								href='<c:url value="/shop/auction/myBidList2.do">
           								 <c:param name="page" value="previous"/></c:url>'>
								<font><B>&lt;&lt; Prev</B></font>
							</a>

						</c:if> <c:if test="${!auctionList.lastPage}">
							<a
								href='<c:url value="/shop/auction/myBidList2.do">
           								 <c:param name="page" value="next"/></c:url>'>
								<font><B>&gt;&gt; next</B></font>
							</a>
						</c:if></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
		//선택된 state 확인 후 라디오 버튼에 checked 넣기
		var nowState = $('#nowAuctionState').val();

		$('input:radio[name="auctionState"]').each(function() {
			if ($(this).val() === nowState) {
				$(this).attr('checked', true);
			}
		})
	});
</script>
