<%@ include file="IncludeTop.jsp"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.0.min.js"></script>

<script type="text/javascript">
	function edit() {
		var secondhandForm = document.secondhandForm;
		var title = secondhandForm.title.value;
		var price = secondhandForm.price.value;
		var report = secondhandForm.report.value;
		var secondhandDescription = secondhandForm.secondhandDescription.value;
		var secondhandState = secondhandForm.secondhandState.value;
		if (!title) {
			alert("상품 제목을 입력해주세요.");
			document.secondhandForm.title.focus();
			return false;
		}  else if (!report) {
			alert("상품 이미지를 입력해주세요.");
			return false;
		} else if (!price) {
			alert("상품 가격을 입력해주세요.");
			document.secondhandForm.price.focus();
			return false;
		} else if (!secondhandDescription) {
			alert("상품 설명을 입력해주세요.");
			document.secondhandForm.secondhandDescription.focus();
			return false;
		} else {
			return true;
		}
	}
</script>

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
	padding-left: 123px;
}
</style>

<div id="wrap">
	<div id="aside">
		<%@ include file="IncludeSideSecondhand.jsp"%>
	</div>
	<div id="contents">
		<h2 style="margin-bottom: 26px">상품 수정</h2>
		<form:form modelAttribute="secondhand" method="post"
			id="secondhandEditForm" enctype="multipart/form-data"
			name="secondhandForm" onSubmit="return edit()">
			<%-- <table id="secondhand">
					<tr>
						<td width="100%" bgcolor="#CCCCCC"><font size="4"><b>상품명:
									<form:input path="title" name="title" />
							</b></font></td>
					</tr>
					<tr>
						<td>상품 이미지: <input type="file" name="report" />
						</td>
					</tr>
					<tr>
						<td>가격: <form:input path="price" name="price" />원
						</td>
					</tr>
					<tr>
						<td>거래 상태: <form:radiobutton path="secondhandState"
								value="NOW" label="판매중" />&nbsp;<!-- default=판매중--> <form:radiobutton
								path="secondhandState" value="RESERVE" label="예약중" />
					</tr>
					<tr>
						<td>상품 설명: <form:textarea path="secondhandDescription"
								rows="5" cols="20" name="secondhandDescription" />
						</td>
					</tr>
					<tr>
						<td><form:hidden path="secondhandDate" /></td>
					</tr>

				</table> --%>
			<!-- <input type="image" src="../images/button_submit.gif" value="상품 등록" /> -->
			<div class="box_wrap" style="width: 100%">
				<dl>
					<dt>상품명</dt>
					<dd>
						<form:input path="title" name="title" />
					</dd>
				</dl>

				<dl>
					<dt>상품 이미지</dt>
					<dd>
						<c:set var="fileName" value="${fileName}"/>
						<c:set var="len" value="${fn:length(fileName)}"/>

						현재 이미지: <c:out value="${fn:substring(fileName, 9, len)} "/>
						</dd><dd>
						<input type="file" name="report" />
					</dd>
				</dl>

				<dl>
					<dt>가격</dt>
					<dd>
						<form:input path="price" name="price" />
					</dd>
				</dl>

				<dl>
					<dt>거래 상태</dt>
					<dd>
						<form:radiobutton path="secondhandState" value="NOW" label="판매중" />
						&nbsp;
						<!-- default=판매중-->
						<form:radiobutton path="secondhandState" value="RESERVE"
							label="예약중" />
					</dd>
				</dl>


				<dl>
					<dt>상품 설명</dt>
					<dd>
						<form:textarea path="secondhandDescription" rows="5" cols="20"
							name="secondhandDescription" />

					</dd>
				</dl>

				<form:hidden path="secondhandDate" />
				<button type="submit" id="bt-create-auction" class="btn btn-primary"
					style="margin-top: 70px; float: right;">상품 등록</button>
			</div>
		</form:form>
	</div>
</div>

