<%@ page pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.0.min.js"></script>
<div class="container">
	<div class="row">
		<div class="col-12">
			<h5>Welcome to Employee Market!</h5>
			<div class="list-group">
				<a
					class="list-group-item list-group-item-action ${category eq 'main' ? 'active' : ''}"
					href="<c:url value="/shop/main/secondhand.do"/>"> 회원간거래 메인페이지 </a> <a
					class="list-group-item list-group-item-action ${category eq 'add' ? 'active' : ''}"
					href="<c:url value="/shop/secondhandAdd.do"/>"> 상품 등록 </a> <a
					class="list-group-item list-group-item-action ${category eq 'myList' ? 'active' : ''}"
					href="<c:url value="/shop/secondhandViewMyList.do"/>">상품 관리</a> <a
					class="list-group-item list-group-item-action ${category eq 'purchase' ? 'active' : ''}"
					href="<c:url value="/shop/secondhandViewPurchaseList.do"/>">구매한
					상품</a> <a
					class="list-group-item list-group-item-action ${category eq 'favorite' ? 'active' : ''}"
					href="<c:url value="/shop/secondhandViewFavoriteList.do"/>">찜한
					상품</a> <a
					class="list-group-item list-group-item-action ${category eq 'chatting' ? 'active' : ''}"
					href="<c:url value="/shop/secondhandViewChattingList.do"/>">채팅
					목록</a>
			</div>

		</div>
	</div>
</div>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>