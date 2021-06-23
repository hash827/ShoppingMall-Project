<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page pageEncoding="UTF-8"%>
<link rel="stylesheet"
   href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!DOCTYPE html>
<style>
#wrap {
   width: 1600px;
   height: 700px;
   margin: 0 auto;
}

#aside {
   width: 250px;
   height: 700px;
   float: left;
}

dt {
   float: left;
}

dd {
   padding-left: 135px;
}

#contents {
   width: 1300px;
   height: 700px;
   float: left;
}
</style>
<%@ include file="IncludeTop.jsp"%>
<script type="text/javascript"
   src="https://code.jquery.com/jquery-3.2.0.min.js"></script>
<div id="wrap">
   <div id="aside">
      <%@ include file="IncludeSideCompany.jsp"%>
   </div>
   <div id="contents">
      <h2>상품 정보</h2>
      <img border="0"
         src="${pageContext.request.contextPath}/images/companyShoes/${company.shoesImage}"
         style="width: 350px; margin-top: 10px; margin-bottom: 30px" alt=""
         width="100" />
      
         <input type="hidden" name="shoesId" value="${company.shoesId}" id="shoesId" />
         <table class="table">
            <thead>
               <tr>
                  <th>항목</th>
                  <th>내용</th>
               </tr>
            </thead>
            <tbody>
               <tr>
                  <td>상품명</td>
                  <td>${company.shoesName}</td>
               </tr>
               <tr>
                  <td>색상</td>
                  <td>${company.shoesColor}</td>
               </tr>
               <tr>
                  <td>가격</td>
                  <td>${company.price}</td>
               </tr>
               <tr>
                  <td>설명</td>
                  <td>${company.shoesDescription}</td>
               </tr>
               <tr>
                  <td>사이즈</td>
                  <td><select id='size' name="shoeSize">
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
                  </select></td>
               </tr>
            </tbody>
         </table>
         

         
                        
            <button type="button" class="btn btn-success" id="add-item"
            style="margin-top: 30px; float:right;">장바구니 추가</button>
      
   
   </div>

</div>

<script type="text/javascript">
   $(function() {
      $('#add-item')
            .click(
                  function() {
                     var shoesId = $('#shoesId').val();
                     var size = $('#size').val();

                     $
                           .ajax({
                              url : "<c:url value="/shop/addItemToCart.do" />",
                              type : "GET",
                              data : {
                                 "shoesId" : shoesId,
                                 "size":size
                              },
                              contentType : "application/json",
                              success : function(result) {
                                 if  (result === 'yes'){
                                    alert("장바구니에 추가했습니다!");
                                 }else{
                                    alert("장바구니에 이미 있습니다.");
                                 }
                              },
                              error : function() {
                                 alert("삭제 실패");
                              }
                           });

                  });

   });
</script>