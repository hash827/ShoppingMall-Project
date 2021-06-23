<%@ page pageEncoding="UTF-8" %>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.0.min.js"></script>
  <div class="container">
      <div class="row">
        <div class="col-12">
          <h1>경매</h1>
          <div class="list-group">
            <a class="list-group-item list-group-item-action ${category eq 'main' ? 'active' : ''}" href="<c:url value="/shop/main/auctionList.do"/>"> 경매리스트 </a>
         	 <a class="list-group-item list-group-item-action ${category eq 'list' ? 'active' : ''}" href="<c:url value="/shop/auction/list.do"/>">나의 경매 리스트</a>
         	  <a class="list-group-item list-group-item-action ${category eq 'favorite' ? 'active' : ''}" href="<c:url value="/shop/auction/favorite/list.do"/>">나의 찜목록</a>
         	   <a class="list-group-item list-group-item-action ${category eq 'bid' ? 'active' : ''}" href="<c:url value="/shop/auction/myBidList.do"/>">나의 낙찰리스트</a>
          </div>

        </div>
      </div>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>