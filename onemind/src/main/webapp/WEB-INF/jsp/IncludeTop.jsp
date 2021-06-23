<%@ page contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
<title>OneMind</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Cache-Control" content="max-age=0">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="Expires" content="Tue, 01 Jan 1980 1:00:00 GMT">
<meta http-equiv="Pragma" content="no-cache">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/style/petstore.css"
	type="text/css" />
</head>

<body bgcolor="white">
	<table class="top">
		<tr>
			<td><a href="<c:url value="/shop/index.do"/>"> <img
					border="0"
					src="${pageContext.request.contextPath}/images/logo2.png" style="height:38px; margin-left:20px" /></a>
			</td>
			<td style="text-align: right; float:left; margin-left:400px">
			<a href="<c:url value="/shop/company/list.do"/>"><img
					border="0" name="img_help"
					src="${pageContext.request.contextPath}/images/i5.png" style="height:29px"/></a>
					  <a href="<c:url value="/shop/main/auctionList.do"/>"><img
					border="0" name="img_help"
					src="${pageContext.request.contextPath}/images/i6.png"style="height:29px" />
										</a>
										
				<a href="<c:url value="/shop/main/secondhand.do"/>"><img
					border="0" name="img_help"
					src="${pageContext.request.contextPath}/images/i4.png" style="height:29px" /></a>

			<a
				href="<c:url value="/shop/company/viewCart.do"/>"> <img border="0"
					name="img_cart"
					src="${pageContext.request.contextPath}/images/cart2.png" style="height:29px"/></a> <img
				border="0"
				src="${pageContext.request.contextPath}/images/separator.gif" /> 
			
				
				
				<c:if
					test="${empty userSession.account}">
					<a href="<c:url value="/shop/signonForm.do"/>"> <img border="0"
						name="img_signin"
						src="${pageContext.request.contextPath}/images/sign-in.gif" /></a>
				</c:if> <c:if test="${!empty userSession.account}">
					<a href="<c:url value="/shop/signoff.do"/>"> <img border="0"
						name="img_signout"
						src="${pageContext.request.contextPath}/images/sign-out.gif" /></a>
					<img border="0"
						src="${pageContext.request.contextPath}/images/separator.gif" />
					<a href="<c:url value="/shop/editAccount.do"/>"> <img
						border="0" name="img_myaccount"
						src="${pageContext.request.contextPath}/images/my_account.gif" /></a>
				</c:if> 
		</tr>
	</table>

	<%@ include file="IncludeQuickHeader.jsp"%>