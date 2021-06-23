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

#secondhandListTable th, #secondhandListTable td {
	width: 230px;
	text-align: center;
}
</style>

<div id="wrap">
	<div id="aside">
		<%@ include file="IncludeSideSecondhand.jsp"%>
	</div>
	<div id="contents">
		<div align="center">
			<form id="fSecondhandState"
				action="<c:url value="/shop/main/secondhand.do" />">
				<table>
					<tr>
						<td><label><input type="radio" name='secondhandState'
								value='ALL' />전체</label> <!--  checked='checked' -->
						<td><label><input type="radio" name='secondhandState'
								value='NOW' />판매중</label>
						<td><label><input type="radio" name='secondhandState'
								value='RESERVE' />예약중</label>
						<td><label><input type="radio" name='secondhandState'
								value='COMPLETED' />거래완료</label> <input type="submit" value="정렬">
					</tr>

				</table>
			</form>
			<input type="hidden" id="nowSecondhandState"
				value="${secondhandState}" />
			<table class="table" id="secondhandListTable">
				<tr>
					<td><b>상품 아이디</b></td>
					<td><b>상품명</b></td>
					<td><b>가격</b></td>
					<td><b>등록일</b></td>
					<td><b>거래 상태</b></td>
				</tr>
				<c:forEach var="secondhand" items="${secondhandList.pageList}">
					<tr>
						<td><c:choose>
								<c:when test="${fn:length(secondhand.secondhandId) gt 10}">
									<c:out value="${fn:substring(secondhand.secondhandId, 0, 9)}" />
											</c:when>
								<c:otherwise>
									<c:out value="${secondhand.secondhandId}">
									</c:out>
								</c:otherwise>
							</c:choose></td>
						<td><c:choose>
								<c:when test="${secondhand.secondhandState eq 'COMPLETED'}">
									<b><c:out value="${fn:substring(secondhand.title, 0, 9)}" /></b>
								</c:when>
								<c:when test="${fn:length(secondhand.title) gt 10}">
									<b><a
										href='<c:url value="/shop/secondhandViewDetail.do">
            <c:param name="secondhandId" value="${secondhand.secondhandId}"/></c:url>'>
											<c:out value="${fn:substring(secondhand.title, 0, 9)}" />
									</a></b>
								</c:when>
								<c:otherwise>
									<b><a
										href='<c:url value="/shop/secondhandViewDetail.do">
            <c:param name="secondhandId" value="${secondhand.secondhandId}"/></c:url>'>
											<c:out value="${secondhand.title}" />
									</a></b>
								</c:otherwise>
							</c:choose></td>
						<td><c:out value="${secondhand.price}" /></td>
						<td><c:set var="secondhandDate"
								value="${secondhand.secondhandDate}" /> <fmt:formatDate
								value="${secondhandDate}" pattern="yyyy-MM-dd HH:mm" /></td>
						<td><c:choose>
								<c:when test="${secondhand.secondhandState eq 'COMPLETED'}">
									<c:out value="거래완료" />
								</c:when>
								<c:when test="${secondhand.secondhandState eq 'NOW'}">
									<c:out value="판매중" />
								</c:when>
								<c:otherwise>
									<c:out value="예약중" />
								</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
				<tr>
					<td><c:if test="${!secondhandList.firstPage}">
							<a
								href='<c:url value="/shop/main/secondhand2.do">
            <c:param name="page" value="previous"/><c:param name="secondhandState" value="${secondhandState}"/></c:url>'>
								<font><B>&lt;&lt; Prev</B></font>
							</a>
						</c:if> <c:if test="${!secondhandList.lastPage}">
							<a
								href='<c:url value="/shop/main/secondhand2.do">
            <c:param name="page" value="next"/><c:param name="secondhandState" value="${secondhandState}"/></c:url>'>
								<font><B>Next &gt;&gt;</B></font>
							</a>
						</c:if></td>
				</tr>
			</table>
		</div>
	</div>
</div>

<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.0.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//선택된 state 확인 후 라디오 버튼에 checked 넣기
		var nowState = $('#nowSecondhandState').val();

		$('input:radio[name="secondhandState"]').each(function() {
			if ($(this).val() === nowState) {
				$(this).attr('checked', true);
			}
		})
	});
</script>
