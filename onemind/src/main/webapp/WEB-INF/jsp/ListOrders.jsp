<%@ page pageEncoding="UTF-8"%>
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

<div align="center">
	<div id="wrap">
		<div id="contents">
			<p>
				<font size="4"><b>My Orders</b></font>
			</p>
			<table class="table">
				<tr bgcolor="#CCCCCC">
					<td><b>Order ID</b></td>
					<td><b>Date</b></td>
					<td><b>Total Price</b></td>
				</tr>
				<c:forEach var="order" items="${orderList}">
					<tr>
						<td><b><a
								href='<c:url value="/shop/company/viewOrder.do">
              <c:param name="orderId" value="${order.orderId}"/></c:url>'>
									<font color="black"><c:out value="${order.orderId}" /></font>
							</a></b></td>
						<td><fmt:formatDate value="${order.orderDate}"
								pattern="yyyy/MM/dd hh:mm:ss" /></td>
						<td><fmt:formatNumber value="${order.totalPrice}"
								pattern="$#,##0.00" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
