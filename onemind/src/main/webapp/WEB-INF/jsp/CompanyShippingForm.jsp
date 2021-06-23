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
		<h2 style="margin-bottom: 26px">배송지 입력</h2>
		<form:form modelAttribute="companyOrderForm" action="${targetUrl}"
			method="post">
			<form:errors cssClass="error" />
			<br>
			<br>

			<table class="table">
				<tr>
					<td colspan="2"><font color="GREEN" size="4"><b>배송 주소</b></font></td>
				</tr>
				<tr>
					<td>이름:</td>
					<td><form:input path="companyOrder.shipToFirstName" /> <form:errors
							path="companyOrder.shipToFirstName" /></td>
				</tr>
				<tr>
					<td>성:</td>
					<td><form:input path="companyOrder.shipToLastName" /> <form:errors
							path="companyOrder.shipToLastName" /></td>
				</tr>
				<tr>
					<td>주소 1:</td>
					<td><form:input path="companyOrder.shipAddress1" /> <form:errors
							path="companyOrder.shipAddress1" /></td>
				</tr>
				<tr>
					<td>주소 2:</td>
					<td><form:input path="companyOrder.shipAddress2" /> <form:errors
							path="companyOrder.shipAddress2" /></td>
				</tr>
				<tr>
					<td>도시:</td>
					<td><form:input path="companyOrder.shipCity" /> <form:errors
							path="companyOrder.shipCity" /></td>
				</tr>
				<tr>
					<td>지역:</td>
					<td><form:input path="companyOrder.shipState" /> <form:errors
							path="companyOrder.shipState" /></td>
				</tr>
				<tr>
					<td>우편번호:</td>
					<td><form:input path="companyOrder.shipZip" /> <form:errors
							path="companyOrder.shipZip" /></td>
				</tr>
				<tr>
					<td>나라:</td>
					<td><form:input path="companyOrder.shipCountry" /> <form:errors
							path="companyOrder.shipCountry" /></td>
				</tr>
			</table>
			<br />
			<div style="text-align: center">
				<a href='<c:url value="/shop/company/newOrder.do"/>'>
					<button type="button" class="btn btn-success btn-lg"
						style="margin-bottom: 70px; float: left;">뒤로가기</button>
				</a> <a href='<c:url value="/shop/company/newOrderSubmitted.do"/>'>
					<button type="submit" class="btn btn-success btn-lg"
						style="margin-bottom: 70px; float: right;">계속</button>
				</a>
			</div>
		</form:form>
	</div>
</div>