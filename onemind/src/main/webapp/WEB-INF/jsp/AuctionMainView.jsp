<%@ page pageEncoding="UTF-8" %>
<%@ include file="IncludeTop.jsp"%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.0.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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
	margin-bottom: 200px;
	width: 950px;
	height: 700px;
	float: left;
}
</style>

<div id="wrap">
	<div id="aside">
		<%@ include file="includeSideAuction.jsp"%>
	</div>
	<div id="contents">
		<p>
			<font size="4"><b>경매 진행</b></font>	<br>
		</p>
		<input type="hidden" id="auctionId" value="${auction.auctionId}" />
		<table class="table">
			<thead>
				<tr>
					<th>경매</th>
					<th>경매가</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>경매 시작 금액</td>
					<td><input type="text"  value="${auction.startPrice}" id="startPrice" readonly/></td>
				</tr>
				<tr>
					<td>나의 최고 금액</td>
					<td><input type="text"  value="${myBestPrice}" id="myBestPrice" readonly/></td>
				</tr>
				<tr>
					<td>경매 최고 금액</td>
					<td><input type="text" id="bestPrice" readonly/></td>
				</tr>
			</tbody>
		</table>	<br>
		
		<input type="hidden" id="timeRemaining" value="${timeRemaining}"/>
		<table class="table">
			<thead>
				<tr>
					<th>상품</th>
					<th>상품 정보</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>남은시간</td>
					<td><div id="timerPart"></div></td>
				</tr>
				<tr>
					<td>상품 사진</td>
					<td><img border="0" src="${pageContext.request.contextPath}/images/auction/${auction.auctionImage}" 
			alt="" width="100" /></td>
				</tr>
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
			</tbody>
		</table> <br>


		<form id="auction-view" action="#">	
			<input type="hidden" id="isBlackUser" value="${isBlackUser}" />
			<input type="hidden" id="auctionOwner" value="${auction.userId}" />
			<input type="hidden" id="userName" value="${userName}" />
			경매 금액 <input type="number" id="myPrice" placeholder="금액을 입력하세요." required/>
			<input type="button" id="priceBtn"  class="btn btn-primary" value="등록" onclick="checkPrice()"/>
		</form>
		

	</div>
</div>
<script type="text/javascript">
	$(function() {
		setInterval(function() {
			var auctionId = $('#auctionId').val();
			$.ajax({
				url : "<c:url value="/shop/auction/auctionResult/get.do" />",
				type : "GET",
				data : {
					"auctionId" : auctionId
				},
				contentType : "application/json",
				success : function(result) {
					$('#bestPrice').val(result);
				},
				error : function() {
					alert("정보 받아오기 실패");
				}
			});

		}, 1000);

		setInterval(
				function() {
					var auctionId = $('#auctionId').val();
					$
							.ajax({
								url : "<c:url value="/shop/auction/auctionState/get.do" />",
								type : "GET",
								data : {
									"auctionId" : auctionId
								},
								contentType : "application/json",
								success : function(result) {
									if (result !== 'NOW') {
										window.location.href = "<c:url value="/shop/main/auctionList.do" />";
										alert("경매 끝!!");
									}
								},
								error : function() {
									alert("오류");
								}
							});
				}, 1000);
		
		var time = $('#timeRemaining').val();
		var min = "";
		var sec = "";
		
		var x = setInterval(function() {
			min = parseInt(time/60);
			sec = time%60;
			
			document.getElementById("timerPart").innerHTML = min + "분" + sec + "초";
			time--;
			
			//timeout
			if (time < 0) {
				clearInterval(x);
				document.getElementById("timerPart").innerHTML = "시간초과";
			}
		}, 1000);
	});
	
	function checkPrice() {
		var myPrice = parseInt($('#myPrice').val());
		var bestPrice = parseInt($('#bestPrice').val());
		var timePart = $('#timePart').val();
		var auctionId = $('#auctionId').val();
		var isBlackUser = $('#isBlackUser').val();
		var auctionOwner = $('#auctionOwner').val();
		var userName = $('#userName').val();
		
		if (isBlackUser == "true" || auctionOwner == userName) {
			alert("입찰에 참가할 수 없는 사용자입니다.");
			$('#myPrice').val("");
		} else if (Number.isNaN(myPrice)) {
			$('#myPrice').val("");
			$('#myPrice').focus();
		} else if (myPrice < bestPrice) {
			alert("입찰가가 현재 최고 금액보다 낮습니다. ");
			$('#myPrice').val("");
			$('#myPrice').focus();
		} else {
			$.ajax({
				url : "<c:url value="/shop/auction/auctionProcess/add.do" />",
				type : "GET",
				data : {
					"auctionId" : auctionId,
					"myPrice" : myPrice 
				},
				contentType : "application/json",
				success : function() {
					alert("입찰가가 등록되었습니다.");
					window.location.href = "<c:url value="/shop/auction/mainView.do?auctionId=${auction.auctionId}" />";
				},
				error : function() {
					alert("제출에 실패했습니다.");
				}
			});
		}
			
	}
	
	
</script>