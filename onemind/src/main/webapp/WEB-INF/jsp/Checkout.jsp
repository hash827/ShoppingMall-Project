<%@ include file="IncludeTop.jsp"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.0.min.js"></script>

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

dt {
	float: left;
}

dd {
	padding-left: 135px;
}
</style>
<table>
  <tr>
    <td style="text-align: left; vertical-align: top; width: 20%">
      <table id="main-menu">
        <tr>
          <td><a href='<c:url value="/shop/viewCart.do"/>'><b>
            <font color="black" size="2">&lt;&lt; Shopping Cart</font></b></a></td>
        </tr>
      </table>
    </td>

    <td style="text-align: center; vertical-align: top">
      <h2>Checkout Summary</h2>
      <table class="n25">
        <tr bgcolor="#CCCCCC">
          <td><b>상품코드</b></td>
          <td><b>상품명</b></td>
          <td><b>색상</b></td>
          <td><b>사이즈</b></td>
          <td><b>가격</b></td>
          <td><b>수량</b></td>
          <td><b>총 가격</b></td>
        </tr>
        <c:forEach var="cartItem" items="${cart.cartItemList.pageList}">
          <tr bgcolor="#FFFF88">
            <td><b> 
              <a href='<c:url value="/shop/viewItem.do">
                <c:param name="itemId" value="${cartItem.item.itemId}"/></c:url>'>
                  <c:out value="${cartItem.item.itemId}" />
              </a></b>
            </td>
            <td><c:out value="${cartItem.item.productId}" /></td>
            <td>
              <c:out value="${cartItem.item.attribute1}" /> 
              <c:out value="${cartItem.item.attribute2}" /> 
              <c:out value="${cartItem.item.attribute3}" /> 
              <c:out value="${cartItem.item.attribute4}" /> 
              <c:out value="${cartItem.item.attribute5}" /> 
              <c:out value="${cartItem.item.product.name}" />
            </td>
            <td align="center"><c:out value="${cartItem.inStock}" /></td>
            <td align="center"><c:out value="${cartItem.quantity}" /></td>
            <td align="right"><fmt:formatNumber
                value="${cartItem.item.listPrice}" pattern="$#,##0.00" /></td>
            <td align="right"><fmt:formatNumber
                value="${cartItem.totalPrice}" pattern="$#,##0.00" /></td>
          </tr>
        </c:forEach>
        <tr bgcolor="#FFFF88">
          <td colspan="7" align="right"><b>Sub Total: <fmt:formatNumber
                value="${cart.subTotal}" pattern="$#,##0.00" /></b><br /></td>
        </tr>
      </table>

      <c:if test="${!cart.cartItemList.firstPage}">
        <a href="checkout.do?page=previousCart"><font color="green">
          <B>&lt;&lt; Prev</B></font></a>
      </c:if>
      <c:if test="${!cart.cartItemList.lastPage}">
        <a href="checkout.do?page=nextCart"><font color="green">
          <B>Next &gt;&gt;</B></font></a>
      </c:if>
      <br> 
      <a href='<c:url value="/shop/newOrder.do"/>'>
        <img border="0" src="../images/button_continue.gif" alt="" /></a>
    </td>
    <td style="text-align: right; vertical-align: top; width: 20%">&nbsp;</td>
  </tr>
</table>

<%@ include file="IncludeBottom.jsp"%>
