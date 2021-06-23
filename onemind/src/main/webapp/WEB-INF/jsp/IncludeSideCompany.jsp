<%@ page pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.0.min.js"></script>
<div class="container">
	<div class="row">
		<div class="col-12">
			<h1>쇼핑몰</h1>
			<div class="list-group">
				<a
					class="list-group-item list-group-item-action ${category eq 'list' ? 'active' : ''}"
					href="<c:url value="/shop/company/list.do"/>">상품리스트</a> <a
					class="list-group-item list-group-item-action ${category eq 'purchase' ? 'active' : ''}"
					href="<c:url value="/shop/companyViewPurchaseList.do"/>"> 주문 내역 </a>
			</div>

		</div>
	</div>
</div>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>