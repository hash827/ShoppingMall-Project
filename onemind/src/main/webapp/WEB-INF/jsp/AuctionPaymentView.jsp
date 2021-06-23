<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
		<h2>결제</h2>
		
		<form method="post" action="/shop/auction/auctionPay.do?auctionId=${auction.auctionId}" onSubmit="return edit()" id="auctionPaymentForm" name="auctionPaymentForm">
			<table id="auction"  class="table">
				<tr>
					<td>은행</td>
					<td><input type="text" name="bank" /></td>
				</tr>
				<tr>
					<td>카드번호</td>
					<td><input type="text" name="cardNumber" /></td>
				</tr>
				<tr>
					<td>cvc번호</td>
					<td><input type="text" name="cvc" /></td>
				</tr>
				<tr>
					<td>만료날짜</td>
					<td><input type="text" name="expiryDate" /></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="text" name="cardPassword" /></td>
				</tr>
			</table>
			
			<input type="submit" value="결제" />
		</form>
	</div>

</div>

<script type="text/javascript">
	function edit() {
		var auctionPaymentForm = document.auctionPaymentForm;
		var bank = auctionPaymentForm.bank.value;
		var cardNumber = auctionPaymentForm.cardNumber.value;
		var cvc = auctionPaymentForm.cvc.value;
		var expiryDate = auctionPaymentForm.expiryDate.value;
		var cardPassword = auctionPaymentForm.cardPassword.value;
		
		if (!bank) {
			alert("은행 정보를 입력해주세요.");
			document.auctionPaymentForm.bank.focus();
			return false;
		} else if (!cardNumber) {
			alert("카드번호를 입력해주세요.");
			document.auctionPaymentForm.cardNumber.focus();
			return false;
		} else if (!cvc) {
			alert("cvc번호를 입력해주세요.");
			document.auctionPaymentForm.cvc.focus();
			return false;
		} else if (!expiryDate) {
			alert("만료날짜를 입력해주세요.");
			document.auctionPaymentForm.expiryDate.focus();
			return false;
		} else if (!cardPassword) {
			alert("카드 비밀번호를 입력해주세요.");
			document.auctionPaymentForm.cardPassword.focus();
			return false;
		} else {
			return true;
		}
	}

</script>
