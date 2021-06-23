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
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
    $(function(){
        var responseMessage = "<c:out value="${message}" />";
        if(responseMessage != ""){
            alert(responseMessage);
        }
        var url = "<c:out value="${url}" />";
        if(url != ""){
        	location.href='<c:out value="${pageContext.request.contextPath}"/>${url}';
        }
    }) 
</script>
<div id="wrap">
	<div id="contents">
		<h2 style="margin-bottom: 26px">주문 내역</h2>
		<c:if test="${!empty message}">
			<b><c:out value="${message}" /></b>
		</c:if>

		<p></p>
		<table class="table">
			<tr>
				<td align="center" colspan="2"><font size="4"> <b>주문
							#<c:out value="${companyOrder.orderId}" />
					</b></font> <br /> <font size="3"><b> <fmt:formatDate
								value="${companyOrder.orderDate}" pattern="yyyy/MM/dd hh:mm:ss" /></b>
				</font></td>
			</tr>
			<tr>
				<td colspan="2"><font color="green" size="4"><b>결제
							정보</b></font></td>
			</tr>
			<tr>
				<td>카드 종류:</td>
				<td><c:out value="${companyOrder.cardType}" /></td>
			</tr>
			<tr>
				<td>카드 번호:</td>
				<td><c:out value="${companyOrder.creditCard}" /> <font
					color="red" size="2">* Fake number!</font></td>
			</tr>
			<tr>
				<td>만료일 (월/연도):</td>
				<td><c:out value="${companyOrder.expiryDate}" /></td>
			</tr>
			<tr>
				<td colspan="2"><font color="green" size="4"><b>청구
							주소</b></font></td>
			</tr>
			<tr>
				<td>이름:</td>
				<td><c:out value="${companyOrder.billToFirstName}" /></td>
			</tr>
			<tr>
				<td>성:</td>
				<td><c:out value="${companyOrder.billToLastName}" /></td>
			</tr>
			<tr>
				<td>주소 1:</td>
				<td><c:out value="${companyOrder.billAddress1}" /></td>
			</tr>
			<tr>
				<td>주소 2:</td>
				<td><c:out value="${companyOrder.billAddress2}" /></td>
			</tr>
			<tr>
				<td>도시:</td>
				<td><c:out value="${companyOrder.billCity}" /></td>
			</tr>
			<tr>
				<td>지역:</td>
				<td><c:out value="${companyOrder.billState}" /></td>
			</tr>
			<tr>
				<td>우편번호:</td>
				<td><c:out value="${companyOrder.billZip}" /></td>
			</tr>
			<tr>
				<td>나라:</td>
				<td><c:out value="${companyOrder.billCountry}" /></td>
			</tr>
			<tr>
				<td colspan="2"><font color="green" size="4"><b>배송
							주소</b></font></td>
			</tr>
			<tr>
				<td>이름:</td>
				<td><c:out value="${companyOrder.shipToFirstName}" /></td>
			</tr>
			<tr>
				<td>성:</td>
				<td><c:out value="${companyOrder.shipToLastName}" /></td>
			</tr>
			<tr>
				<td>주소 1:</td>
				<td><c:out value="${companyOrder.shipAddress1}" /></td>
			</tr>
			<tr>
				<td>주소 2:</td>
				<td><c:out value="${companyOrder.shipAddress2}" /></td>
			</tr>
			<tr>
				<td>도시:</td>
				<td><c:out value="${companyOrder.shipCity}" /></td>
			</tr>
			<tr>
				<td>지역:</td>
				<td><c:out value="${companyOrder.shipState}" /></td>
			</tr>
			<tr>
				<td>우편번호:</td>
				<td><c:out value="${companyOrder.shipZip}" /></td>
			</tr>
			<tr>
				<td>나라:</td>
				<td><c:out value="${companyOrder.shipCountry}" /></td>
			</tr>
			<tr>
				<td>항공사:</td>
				<td><c:out value="${companyOrder.courier}" /></td>
			</tr>
			<tr>
				<td colspan="2"><b><font color="green" size="4">주문
							상태:</font> <c:out value="${companyOrder.status}" /></b></td>
			</tr>
			<tr>
				<td colspan="2">
					<table class="table" style="width: 100%">
						<tr style="background-color: #CCCCCC;">
							<td><b>상품코드</b></td>
							<td><b>상품명</b></td>
							<td><b>색상</b></td>
							<td><b>사이즈</b></td>
							<td><b>가격</b></td>
							<td><b>수량</b></td>
							<td><b>Total Cost</b></td>
						</tr>
						<c:forEach var="lineCompanyItem"
							items="${companyOrder.lineCompanyItems}">
							<tr>
								<td><b><a
										href='<c:url value="/shop/company/view.do">
                  <c:param name="shoesId" value="${lineCompanyItem.shoesId}"/></c:url>'>
											<font color="black"><c:out
													value="${lineCompanyItem.shoesId}" /></font>
									</a></b></td>
								<td><c:out
										value="${lineCompanyItem.companyShoes.shoesName}" /></td>
								<td><c:out
										value="${lineCompanyItem.companyShoes.shoesColor}" /></td>
								<td><c:out
										value="${lineCompanyItem.companyShoes.shoesSize}" /></td>
								<td align="right"><fmt:formatNumber
										value="${lineCompanyItem.unitPrice}" pattern="$#,##0.00" /></td>
								<td><c:out value="${lineCompanyItem.quantity}" /></td>
								<td align="right"><fmt:formatNumber
										value="${lineCompanyItem.totalPrice}" pattern="$#,##0.00" /></td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="7" align="right"><b>Total: <fmt:formatNumber
										value="${companyOrder.totalPrice}" pattern="$#,##0.00" /></b></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</div>