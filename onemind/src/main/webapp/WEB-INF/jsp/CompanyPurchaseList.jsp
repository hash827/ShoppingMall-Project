<%@ include file="IncludeTop.jsp"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

#companyPurchaseListTable th, #companyPurchaseListTable td {
	width: 230px;
	text-align: center;
}
</style>

<div id="wrap">
	<div id="aside">
		<%@ include file="IncludeSideCompany.jsp"%>
	</div>
	<div id="contents">
		<div align="center">
			<b><font size="4"><c:out
						value="${userSession.account.firstName}회원님의 주문 리스트입니다." /></font></b>
			<table class="table" id="companyPurchaseListTable">
				<tr>
					<td><b>주문 아이디</b></td>
					<td><b>주문일</b></td>
					<td><b>Total Cost</b></td>
				</tr>
				<c:forEach var="companyOrder" items="${orderCompanyList.pageList}">
					<tr>
						<td><b><a
								href='<c:url value="/shop/company/viewOrder2.do">
              <c:param name="orderId" value="${companyOrder.orderId}"/></c:url>'>
									<font color="black"><c:out
											value="${companyOrder.orderId}" /></font>
							</a></b></td>
						<td><fmt:formatDate value="${companyOrder.orderDate}"
								pattern="yyyy/MM/dd hh:mm:ss" /></td>
						<td><fmt:formatNumber value="${companyOrder.totalPrice}"
								pattern="$#,##0.00" /></td>
					</tr>
				</c:forEach>
				<tr>
					<td><c:if test="${!orderCompanyList.firstPage}">
							<a
								href='<c:url value="/shop/companyViewPurchaseList2.do">
            <c:param name="page" value="previous"/></c:url>'>
								<font><B>&lt;&lt; Prev</B></font>
							</a>
						</c:if> <c:if test="${!orderCompanyList.lastPage}">
							<a
								href='<c:url value="/shop/companyViewPurchaseList2.do">
            <c:param name="page" value="next"/></c:url>'>
								<font><B>Next &gt;&gt;</B></font>
							</a>
						</c:if></td>
				</tr>
			</table>
		</div>
	</div>
</div>