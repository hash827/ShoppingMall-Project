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
		<h2 style="margin-bottom: 26px">장바구니</h2>
		<form action='<c:url value="/shop/company/updateCartQuantities.do"/>'
			method="post">
			<table class="table">
				<tr>
					<td><b>상품코드</b></td>
					<td><b>상품명</b></td>
					<td><b>색상</b></td>
					<td><b>사이즈</b></td>
					<td><b>가격</b></td>
					<td><b>수량</b></td>
					<td><b>Total Cost</b></td>
					<td>&nbsp;</td>
				</tr>

				<c:if test="${cart.numberOfItems == 0}">
					<tr>
						<td colspan="8"><b>장바구니가 비어있습니다. </b></td>
					</tr>
				</c:if>

				<c:forEach var="cartItem" items="${cart.cartItemList.pageList}">
					<tr>
						<td><b> <a
								href='<c:url value="/shop/company/view.do?shoesId=${cartItem.companyShoes.shoesId}">
                  <c:param name="itemId" value="${cartItem.companyShoes.shoesId}"/></c:url>'>
									<c:out value="${cartItem.companyShoes.shoesId}" />
							</a></b></td>
						<td><c:out value="${cartItem.companyShoes.shoesName}" /></td>
						<td><c:out value="${cartItem.companyShoes.shoesColor}" /></td>
						<td><c:out value="${cartItem.companyShoes.shoesSize}" /></td>
						<td><c:out value="${cartItem.companyShoes.price}" /></td>

						<td style="text-align: center"><input type="text" size="3"
							name='<c:out value="${cartItem.companyShoes.shoesId}"/>'
							value='<c:out value="${cartItem.quantity}"/>' /></td>
						<td style="text-align: right"><fmt:formatNumber
								value="${cartItem.totalPrice}" pattern="$#" /></td>
						<td><a
							href='<c:url value="/shop/company/removeItemFromCart.do">
                    <c:param name="workingItemId" value="${cartItem.companyShoes.shoesId}"/></c:url>'>
								<button type="button" class="btn btn-danger btn-sm"
									style="float: right;">삭제</button>
						</a></td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="7" align="right"><b>총 가격: <fmt:formatNumber
								value="${cart.subTotal}" pattern="$#,##0.00" /></b> &nbsp;&nbsp;
						<button type="submit" class="btn btn-primary btn-sm"
							style="float: right;">업데이트</button></td>
					<td>&nbsp;</td>
				</tr>
			</table>
			<div style="text-align: center">
				<c:if test="${!cart.cartItemList.firstPage}">
					<a href='<c:url value="company/viewCart.do?page=previousCart"/>'>
						<font color="green"><B>&lt;&lt; Prev</B></font>
					</a>
				</c:if>
				<c:if test="${!cart.cartItemList.lastPage}">
					<a href='<c:url value="company/viewCart.do?page=nextCart"/>'> <font
						color="green"><B>Next &gt;&gt;</B></font></a>
				</c:if>
			</div>
		</form>

		<br />
		<c:if test="${cart.numberOfItems > 0}">
			<div style="text-align: center">
				<a href='<c:url value="/shop/company/checkout.do"/>'>
					<button type="button" class="btn btn-success btn-lg"
						style="margin-top: 70px; float: right;">결제</button>
				</a>
			</div>
		</c:if>
	</div>
</div>

