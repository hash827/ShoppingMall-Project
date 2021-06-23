<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<div id="wrap">
	<div id="contents">
		<h2 style="margin-bottom: 26px">주문하기</h2>
		<p>
			<b>결제 정보를 확인하고 계속하세요</b>
		</p>
		<p></p>
		<table class="table">
			<tr>
				<td align="center" colspan="2"><font size="4"><b>주문</b></font><br />
					<font size="3"> <b><fmt:formatDate
								value="${companyOrderForm.companyOrder.orderDate}"
								pattern="yyyy/MM/dd hh:mm:ss" /></b></font></td>
			</tr>
			<tr>
				<td colspan="2"><font color="GREEN" size="4"><b>청구 주소</b></font></td>
			</tr>
			<tr>
				<td>이름:</td>
				<td>${companyOrderForm.companyOrder.billToFirstName}</td>
			</tr>
			<tr>
				<td>성:</td>
				<td>${companyOrderForm.companyOrder.billToLastName}</td>
			</tr>
			<tr>
				<td>주소 1:</td>
				<td>${companyOrderForm.companyOrder.billAddress1}</td>
			</tr>
			<tr>
				<td>주소 2:</td>
				<td>${companyOrderForm.companyOrder.billAddress2}</td>
			</tr>
			<tr>
				<td>도시:</td>
				<td>${companyOrderForm.companyOrder.billCity}</td>
			</tr>
			<tr>
				<td>지역:</td>
				<td>${companyOrderForm.companyOrder.billState}</td>
			</tr>
			<tr>
				<td>우편번호:</td>
				<td>${companyOrderForm.companyOrder.billZip}</td>
			</tr>
			<tr>
				<td>나라:</td>
				<td>${companyOrderForm.companyOrder.billCountry}</td>
			</tr>
			<tr>
				<td colspan="2"><font color="GREEN" size="4"><b>배송 주소</b></font></td>
			</tr>
			<tr>
				<td>성:</td>
				<td>${companyOrderForm.companyOrder.shipToFirstName}</td>
			</tr>
			<tr>
				<td>이름:</td>
				<td>${companyOrderForm.companyOrder.shipToLastName}</td>
			</tr>
			<tr>
				<td>주소 1:</td>
				<td>${companyOrderForm.companyOrder.shipAddress1}</td>
			</tr>
			<tr>
				<td>주소 2:</td>
				<td>${companyOrderForm.companyOrder.shipAddress2}</td>
			</tr>
			<tr>
				<td>도시:</td>
				<td>${companyOrderForm.companyOrder.shipCity}</td>
			</tr>
			<tr>
				<td>지역:</td>
				<td>${companyOrderForm.companyOrder.shipState}</td>
			</tr>
			<tr>
				<td>우편번호:</td>
				<td>${companyOrderForm.companyOrder.shipZip}</td>
			</tr>
			<tr>
				<td>나라:</td>
				<td>${companyOrderForm.companyOrder.shipCountry}</td>
			</tr>
		</table>
		<br />
		<div style="text-align: center">
			<a href='<c:url value="/shop/company/newOrder.do"/>'>
				<button type="button" class="btn btn-success btn-lg"
					style="margin-bottom: 70px; float: left;">뒤로가기</button>
			</a> <a href='<c:url value="/shop/company/confirmOrder.do"/>'>
				<button type="submit" class="btn btn-success btn-lg"
					style="margin-bottom: 70px; float: right;">계속</button>
			</a>
		</div>
	</div>
</div>