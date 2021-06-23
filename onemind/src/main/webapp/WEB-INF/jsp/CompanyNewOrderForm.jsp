<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
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

table {
	/* border: 1px solid #444444;
	border-collapse: collapse; */
	
}

th, td {
	/* border: 1px solid #444444; */
	
}
</style>
<%@ include file="IncludeTop.jsp"%>
<c:set var="targetUrl">
	<c:url value="/shop/company/newOrderSubmitted.do" />
</c:set>

<div id="wrap">
	<div id="contents">
		<h2 style="margin-bottom: 26px">주문하기</h2>
		<form:form modelAttribute="companyOrderForm" action="${targetUrl}"
			method="post">
			<form:errors cssClass="error" />
			<br>
			<br>

			<table class="table">
				<tr>
					<td colspan="2"><font color="green" size="4"><b>결제 정보</b></font></td>
				</tr>
				<tr>
					<td>카드 종류:</td>
					<td><form:select path="companyOrder.cardType"
							items="${creditCardTypes}" /> <form:errors
							path="companyOrder.cardType" /></td>
				</tr>
				<tr>
					<td>카드 번호:</td>
					<td><form:input path="companyOrder.creditCard" /> <font
						color="red" size="2">* Use a fake number!</font> <form:errors
							path="companyOrder.creditCard" /></td>
				</tr>
				<tr>
					<td>만료일 (월/연도):</td>
					<td><form:input path="companyOrder.expiryDate" /> <form:errors
							path="companyOrder.expiryDate" /></td>
				</tr>
				<tr>
					<td colspan="2"><font color="green" size="4"><b>청구 주소</b></font></td>
				</tr>
				<tr>
					<td>이름:</td>
					<td><form:input path="companyOrder.billToFirstName" /> <form:errors
							path="companyOrder.billToFirstName" /></td>
				</tr>
				<tr>
					<td>성:</td>
					<td><form:input path="companyOrder.billToLastName" /> <form:errors
							path="companyOrder.billToLastName" /></td>
				</tr>
				<tr>
					<td>주소 1:</td>
					<td><form:input path="companyOrder.billAddress1" /> <form:errors
							path="companyOrder.billAddress1" /></td>
				</tr>
				<tr>
					<td>주소 2:</td>
					<td><form:input path="companyOrder.billAddress2" /> <form:errors
							path="companyOrder.billAddress2" /></td>
				</tr>
				<tr>
					<td>도시:</td>
					<td><form:input path="companyOrder.billCity" /> <form:errors
							path="companyOrder.billCity" /></td>
				</tr>
				<tr>
					<td>지역:</td>
					<td><form:input path="companyOrder.billState" /> <form:errors
							path="companyOrder.billState" /></td>
				</tr>
				<tr>
					<td>우편번호:</td>
					<td><form:input path="companyOrder.billZip" /> <form:errors
							path="companyOrder.billZip" /></td>
				</tr>
				<tr>
					<td>나라:</td>
					<td><form:input path="companyOrder.billCountry" /> <form:errors
							path="companyOrder.billCountry" /></td>
				</tr>
				<tr>
					<td colspan="2"><form:checkbox path="shippingAddressRequired"
							label="새로운 배송지 입력" /></td>
				</tr>
			</table>
			<br />
			<div style="text-align: center">
				<a href='<c:url value="/shop/company/checkout.do"/>'>
					<button type="button" class="btn btn-success btn-lg"
						style="margin-bottom: 70px; float: left;">뒤로가기</button>
				</a> <a href='<c:url value="/shop/company/newOrderSubmitted.do"/>'>
					<button type="submit" class="btn btn-success btn-lg"
						style="margin-bottom: 70px; float: right;">계속</button>
				</a>
			</div>
			<!-- <p>
				<input type="image" src="../images/button_submit.gif">
			</p> -->
		</form:form>
	</div>
</div>