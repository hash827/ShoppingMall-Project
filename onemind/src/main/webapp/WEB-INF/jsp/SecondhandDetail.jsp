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

<div id="wrap">
	<div id="aside">
		<%@ include file="IncludeSideSecondhand.jsp"%>
	</div>
	<div id="contents">
		<h2>
			상품 정보
		</h2>
		<img border="0"
			src="${pageContext.request.contextPath}${secondhandImage}"
			style="width: 350px; margin-top: 10px; margin-bottom: 30px" alt=""
			width="100" />
		<table class="table">
			<thead>
				<tr>
					<th></th>
					<th>내용</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>상품명</td>
					<td><c:out value="${secondhand.title}" /></td>
				</tr>
				<tr>
					<td>판매자</td>
					<td><c:out value="${secondhand.username}" /></td>
				</tr>
				<tr>
					<td>가격</td>
					<td><c:out value="${secondhand.price}" /></td>
				</tr>
				<tr>
					<td>거래상태</td>
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
				<tr>
					<td>상세설명</td>
					<td><c:choose>
							<c:when
								test="${fn:length(secondhand.secondhandDescription) gt 21}">
								<c:out
									value="${fn:substring(secondhand.secondhandDescription, 0, 20)}" />
								<br>
								<c:out
									value="${fn:substring(secondhand.secondhandDescription, 20, 50)}" />
								<br>
								<c:out
									value="${fn:substring(secondhand.secondhandDescription, 50, 80)}" />
							</c:when>
							<c:otherwise>
								<c:out value="${secondhand.secondhandDescription}">
								</c:out>
							</c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<td>등록일</td>
					<td><c:set var="secondhandDate"
								value="${secondhand.secondhandDate}" /> <fmt:formatDate
								value="${secondhandDate}" pattern="yyyy-MM-dd HH:mm" /></td>
					<td>
				</tr>
				<c:choose>
							<c:when test="${secondhand.username ne username}">
				
					
					<c:choose>
						<c:when test="${secondhand.secondhandState eq 'NOW'}">
						<tr>
					<td></td>
						<td>
							<a href='<c:url value="/shop/chatFromSecondhandDetail.do">	<!-- 채팅방 domain과 연결하기 -->
          						<c:param name="secondhandId" value="${secondhand.secondhandId}"/></c:url>'>
								채팅하기
							</a>
							</td></tr>
							</c:when>
					</c:choose>
				
				</c:when>
				</c:choose>
				
				<c:choose>
				<c:when test="${secondhand.username ne username}">
				<tr>
					<td></td>
					<td>
								<a href='<c:url value="/shop/secondhandFavoriteInsert.do">	<!-- 찜목록 domain과 연결하기 -->
         						 <c:param name="secondhandId" value="${secondhand.secondhandId}"/></c:url>'>
								<img border="0"
									src="${pageContext.request.contextPath}${secondhandFavoriteImage}"
									alt="" width="50" />
								</a>			
					</td>
				</tr>
				</c:when>
				</c:choose>
			</tbody>
		</table>
		<%-- <table>
				<tr>
					<td width="100%" bgcolor="#CCCCCC"><font size="4"><b>상품명:
								<c:out value="${secondhand.title}" />
						</b></font></td>
				</tr>
				<tr>
					<td><img border="0"
						src="${pageContext.request.contextPath}${secondhandImage}" alt=""
						width="200" height="200"/></td>
				</tr>
				<tr>
					<td>판매자 id: <c:out value="${secondhand.username}" />
					</td>
				</tr>
				<tr>
					<td>가격: <c:out value="${secondhand.price}" />
					</td>
				</tr>
				<tr>
					<td>거래 상태: <c:out value="${secondhand.secondhandState}" />
				</tr>
				<tr>
					<td>상품 상세 설명: <c:choose>
							<c:when
								test="${fn:length(secondhand.secondhandDescription) gt 21}">
								<c:out
									value="${fn:substring(secondhand.secondhandDescription, 0, 20)}" />
								<br>
								<c:out
									value="${fn:substring(secondhand.secondhandDescription, 20, 50)}" />
								<br>
								<c:out
									value="${fn:substring(secondhand.secondhandDescription, 50, 80)}" />
							</c:when>
							<c:otherwise>
								<c:out value="${secondhand.secondhandDescription}">
								</c:out>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td>
					<c:choose>
						<c:when test="${secondhand.username ne username}">
							<a href='<c:url value="/shop/chatFromSecondhandDetail.do">	<!-- 채팅방 domain과 연결하기 -->
          					<c:param name="secondhandId" value="${secondhand.secondhandId}"/></c:url>'>
							채팅하기 </a>
						</c:when>
					</c:choose>		
					</td>
				</tr>
				<tr>
					<td><a
						href='<c:url value="/shop/secondhandFavoriteInsert.do">	<!-- 찜목록 domain과 연결하기 -->
         		 <c:param name="secondhandId" value="${secondhand.secondhandId}"/></c:url>'>
							<img border="0"
							src="${pageContext.request.contextPath}${secondhandFavoriteImage}"
							alt="" width="50" />
					</a></td>
				</tr>
			</table> --%>
	</div>
</div>
