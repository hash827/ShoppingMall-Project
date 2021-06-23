<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<form:form modelAttribute="accountForm" method="post">
		<form:errors cssClass="error" />
		<br>
		<br>

		<table id="table">
			<tr>
				<td>
					<h3>
						<font color="darkgreen">User Information</font>
					</h3>
					<table class="table">
						<tr>
							<td>User ID:</td>
							<td><c:if test="${accountForm.newAccount}">
									<form:input path="account.username" htmlEscape="false" />
									<B><form:errors path="account.username" cssClass="error" /></B>
								</c:if> <c:if test="${!accountForm.newAccount}">
									<c:out value="${accountForm.account.username}" />
								</c:if></td>
						</tr>
						<tr>
							<td>New password:</td>
							<td><form:password path="account.password" /> <B><form:errors
										path="account.password" cssClass="error" /></B></td>
						</tr>
						<tr>
							<td>Repeat password:</td>
							<td><form:password path="repeatedPassword" /> <B><form:errors
										path="repeatedPassword" cssClass="error" /></B></td>
						</tr>
					</table> <%@ include file="IncludeAccountFields.jsp"%>

				</td>
			</tr>
		</table>
		<br />
		<input type="image" src="../images/button_submit.gif" name="submit"
			value="Save Account Information" />
	</form:form>
	<p></p>

</div>
