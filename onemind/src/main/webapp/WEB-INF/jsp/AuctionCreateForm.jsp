<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<%@ page pageEncoding="UTF-8" %>
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
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
<%@ include file="IncludeTop.jsp"%>
<div id="wrap">
	<div id="aside">
		<%@ include file="includeSideAuction.jsp"%>
	</div>
	<div id="contents">
		<h2 style="margin-bottom: 26px">새 경매 생성</h2>
		<dl>
			<dt><label for="file1">이미지</label></dt>
			<dd>
				 <input type="file" id="file1"
					name="file1">

			</dd>
		</dl>
		<form:form method="post" id="auction-form" modelAttribute="auction"
			action="${pageContext.request.contextPath}/shop/auction/create.do"
			accept-charset="utf-8" enctype="multipart/form-data">
			<div class="box_wrap" style="width: 100%">
				<dl>
					<dt>상품명</dt>
					<dd>
						<input id="auctionName" name="auctionName" class="txt" type="text"
							value="" />
						<form:errors path="auctionName" />
					</dd>
				</dl>

				<dl>
					<dt>색상</dt>
					<dd>
						<input id="auctionColor" name="auctionColor" class="txt"
							type="text" value="" />
						<form:errors path="auctionColor" />
					</dd>
				</dl>

				<dl>
					<dt>사이즈</dt>
					<dd>
						<select id='size' name="shoeSize">
							<option value="220">220</option>
							<option value="225">225</option>
							<option value="230">230</option>
							<option value="235">235</option>
							<option value="240">240</option>
							<option value="245">245</option>
							<option value="250">250</option>
							<option value="255">255</option>
							<option value="260">260</option>
							<option value="265">265</option>
							<option value="260">270</option>
						</select>
					</dd>
				</dl>

				<dl>
					<dt>착용 기간</dt>
					<dd>
						<select id='wearingPeriod' name="wearingPeriod">
							<option value="미개봉">미개봉</option>
							<option value="개봉 새상품">개봉 새상품</option>
							<option value="1개월이내">1개월이내</option>
							<option value="6개월이내">6개월이내</option>
							<option value="1년이상">1년이상</option>
						</select>
					</dd>
				</dl>

				<dl>
					<dt>신발 상태</dt>
					<dd>
						<select id='shoesState' name="shoesState">
							<option value="A">A</option>
							<option value="B">B</option>
							<option value="C">C</option>
							<option value="D">D</option>
						</select>
					</dd>
				</dl>

				<dl>
					<dt>경매 날짜</dt>
					<dd>

						<input type="datetime-local" id="auctionDate" name="auctionDate">
						<form:errors path="auctionDate" />
					</dd>
				</dl>

				<dl>
					<dt>경매 진행시간</dt>
					<dd>
						<!-- <input id="auctionTime" name="auctionTime" class="txt" type="text"
							value="" /> 분 -->
						<select id='auctionTime' name="auctionTime">
							<option value="10">10</option>
							<option value="20">20</option>
							<option value="30">30</option>
							<option value="40">40</option>
							<option value="50">50</option>
							<option value="60">60</option>
						</select>
						<form:errors path="auctionTime" />
						<!-- <input type="datetime-local" id="auctionTime" name="auctionTime"/> -->
					</dd>
				</dl>

				<dl>
					<dt>경매 시작금액</dt>
					<dd>
						<input id="startPrice" name="startPrice" type="number" value=""
							required min="0" />

					</dd>
				</dl>
				<input type="hidden" id="auctionImage" name="auctionImage" />
				<button type="submit" class="btn btn-success btn-lg" style="margin-top: 70px; float:right;">생성</button>
				<!--  <input
					type="submit" style="margin-top: 70px" /> -->
			</div>
		</form:form>
		

	</div>
</div>

<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.0.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#file1").on("change", handleImgFileSelect);
	});

	function handleImgFileSelect() {
		var form = new FormData();
		form.append("file1", $("#file1")[0].files[0]);

		jQuery.ajax({
			url : "<c:url value="/shop/auction/upload.do" />",
			type : "POST",
			processData : false,
			contentType : false,
			data : form,
			success : function(response) {
				$("#auctionImage").val(response)

			},
			error : function(jqXHR) {
				alert(jqXHR.responseText);
			}
		});
	}
</script>