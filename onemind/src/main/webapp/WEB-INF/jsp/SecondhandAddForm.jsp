<%@ include file="IncludeTop.jsp"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.0.min.js"></script>

<script type="text/javascript">
	function add() {
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
		} else if (!report) {
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
		<h2 style="margin-bottom: 26px">상품 등록</h2>
		<form:form modelAttribute="secondhand" method="post"
			id="secondhandAddForm" enctype="multipart/form-data"
			name="secondhandForm" onSubmit="return add()">
			<div class="box_wrap" style="width: 100%">
				<dl>
					<dt>상품명</dt>
					<dd>
						<input id="title" name="title" class="txt" type="text" value="" />
						<form:errors path="title" />
					</dd>
				</dl>

				<dl>
					<dt>상품 이미지</dt>
					<dd>
						<input id = "report" type="file" name="report" />
					</dd>
				</dl>

				<dl>
					<dt>가격</dt>
					<dd>

						<input id="price" name="price" class="txt" type="number" value="" />
						<form:errors path="price" />
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
				<form:hidden path="secondhandState" value="NOW" />
				<button type="submit" id="bt-create-auction" class="btn btn-primary"
					style="margin-top: 70px; float:right;">상품 등록</button>
				<!--  <input
					type="submit" style="margin-top: 70px" /> -->
			</div>
			<%-- <table id="secondhand" >
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
						<td>상품 설명: <form:textarea path="secondhandDescription"
								rows="5" cols="20" name="secondhandDescription" />
						</td>
					</tr>
					<tr>
						<td><form:hidden path="secondhandDate" /></td>
						<td><form:hidden path="secondhandState" value="NOW" /></td>
					</tr>
				</table> --%>
		</form:form>
	</div>
</div>