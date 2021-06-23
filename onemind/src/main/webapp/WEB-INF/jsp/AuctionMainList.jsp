<%@ page pageEncoding="UTF-8" %>
<%@ include file="IncludeTop.jsp"%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
#auctionListTable th, #auctionListTable td {
	width: 230px;
	text-align: center;
}
#auctionStateRadio {
	float : right;
}
</style>
<div id="wrap">
	<div id="aside">
		<%@ include file="includeSideAuction.jsp"%>
	</div>
	<div id="contents">
		<form id="fAuctionState"
			action="<c:url value="/shop/main/auctionList.do" />">
			<table id="auctionStateRadio">
				<tr>
					<!-- 라디오 버튼을 통한 페이지 제어 -->
					<!-- https://m.blog.naver.com/PostView.nhn?blogId=bounce18&logNo=220706092723&proxyReferer=https:%2F%2Fwww.google.com%2F -->
					<!-- 전체:ALL/경매예정:BEFORE/경매중:NOW/경매완료:AFTER(결제완료:PAID/결제실패:FAIL/GIVEUP:경매포기/PAYWAIT:결제대기) -->
					<td><label><input type="radio" name='auctionState'
							value='ALL' />전체</label> <!--  checked='checked' -->
					<td><label><input type="radio" name='auctionState'
							value='BEFORE' />경매예정</label>
					<td><label><input type="radio" name='auctionState'
							value='NOW' />경매중</label>
					<td><label><input type="radio" name='auctionState'
							value='AFTER' />경매완료</label> <input type="submit" class="btn btn-primary" value="정렬">
				</tr>
			</table>
		</form>
		<input type="hidden" id="nowAuctionState" value="${auctionState}" />
		<p>
			<font size="4"><b>경매 리스트 </b></font>	<br>
		</p>
		<table  class="table" id="auctionListTable">
			<thead>
				<tr>
					<th>상품명</th>
					<th>경매시작일</th>
					<th>경매시간</th>
					<th>경매상태</th>
					<th>찜</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="auction" items="${auctionList.pageList}" varStatus="atSt">
					<tr id="${auction.auctionId}">
						<td><c:set var="state" value="${auction.auctionState}" /> <c:if
								test="${state eq 'BEFORE' || state eq 'AFTER' || state eq 'PAID' || state eq 'FAIL' || state eq 'GIVEUP' || state eq 'PAYWAIT'}"
								var="checkSt">
						${auction.auctionName}
					</c:if> <c:if test="${!checkSt}">
								<a
									href="<c:url value="/shop/auction/mainView.do?auctionId=${auction.auctionId}"/>">${auction.auctionName}</a>
							</c:if></td>

						<%-- <td>${auction.auctionDate}</td> --%>
						<td><fmt:formatDate value="${auction.auctionDate}"
								pattern="yyyy-MM-dd HH:mm" /></td>
						<td>${auction.auctionTime}분</td>
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

						<c:set var="doneLoop" value="false" />
						<c:set var="isFavorite" value="false" />
						<td><c:forEach var="myFavoriteAuction"
								items="${myFavoriteAuctions}">
								<c:if test="${not doneLoop}">
									<c:if test="${auction.auctionId == myFavoriteAuction}"
										var="isFavorite"></c:if>
									<c:if test="${isFavorite}">
										<c:set var="doneLoop" value="true" />
									</c:if>
								</c:if>
							</c:forEach> <a class="favoriteChange" href="#"> <input type="hidden"
								class="favAuctionId" value="${auction.auctionId}"> <input
								type="hidden" class="isFav" value="${isFavorite}"> <c:if
									test="${isFavorite}">
									<img border="0"
										src="${pageContext.request.contextPath}/images/fullheart.png"
										width="20" />
								</c:if> <c:if test="${!isFavorite}">
									<img border="0"
										src="${pageContext.request.contextPath}/images/emptyheart.png"
										width="20" />
								</c:if>
						</a></td>
					</tr>
				</c:forEach>
				<tr>
					<td><c:if test="${!auctionList.firstPage}">
							<a
								href='<c:url value="/shop/main/auctionList2.do">
           								 <c:param name="page" value="previous"/>
           								<c:param name="auctionState" value="${auctionState}"/>
           							</c:url>'>
								<font><B>&lt;&lt; Prev</B></font>
							</a>

						</c:if> <c:if test="${!auctionList.lastPage}">
							<a
								href='<c:url value="/shop/main/auctionList2.do">
           								 <c:param name="page" value="next"/>
           								 <c:param name="auctionState" value="${auctionState}"/>
           							</c:url>'>
								<font><B>&gt;&gt; next</B></font>
							</a>
						</c:if></td>
				</tr>
			</tbody>

		</table>

	</div>
</div>


<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.0.min.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('.favoriteChange')
								.click(
										function() {
											var auctionId = $(this).children(
													'.favAuctionId').val();
											var isFav = $(this).children(
													'.isFav').val();

											if (isFav === "true") { //favorite fullheart to emptyheart 
												$
														.ajax({
															url : "<c:url value="/shop/auction/favorite/delete.do"/>",
															type : "GET",
															data : {
																"auctionId" : auctionId
															},
															contentType : "application/json",
															success : function() {
																window.location.href = "<c:url value="/shop/auction/favorite/list.do" />";
																alert("찜 해제");
															},
															error : function() {
																alert("찜 해제 실패");
															}
														});
											} else { //favorite emptyheart to fullheart 
												$
														.ajax({
															url : "<c:url value="/shop/auction/favorite/add.do"/>",
															type : "GET",
															data : {
																"auctionId" : auctionId
															},
															contentType : "application/json",
															success : function() {
																window.location.href = "<c:url value="/shop/auction/favorite/list.do" />";
																alert("찜 추가");
															},
															error : function(
																	request,
																	error) {
																alert("찜 추가 실패");
																alert("code:"
																		+ request.status
																		+ "\n"
																		+ "message:"
																		+ request.responseText
																		+ "\n"
																		+ "error:"
																		+ error);
															}
														});
											}

										});
					});

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
