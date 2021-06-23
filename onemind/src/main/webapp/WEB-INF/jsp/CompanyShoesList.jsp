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
#auctionListTable th, #auctionListTable td {
   width: 230px;
   text-align: center;
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
<div id="wrap">
   <div id="aside">
      <%@ include file="IncludeSideCompany.jsp"%>
   </div>
   <div id="contents">
      <p>
         <font size="4"><b>신발 </b></font> <br>
      </p>
      <table class="table" id="auctionListTable">
         <thead>
            <tr>
               <th>상품코드</th>
               <th>상품명</th>
               <th>색상</th>
               <th>가격</th>
            </tr>
         </thead>
         <tbody>
            <c:forEach var="company" items="${shoesList.pageList}"
               varStatus="atSt">
               <tr id="${company.shoesId}">
               <td>${company.shoesId}</td>
                  <td><a
                     href="<c:url value="/shop/company/view.do?shoesId=${company.shoesId}" />">${company.shoesName}</a>
                  </td>
                  <%-- <td>${auction.auctionDate}</td> --%>
                  <td>${company.shoesColor}</td>
                  <td>${company.price}</td>
               </tr>
            </c:forEach>
            <tr>
               <td><c:if test="${!shoesList.firstPage}">
                     <a
                        href='<c:url value="/shop/company/list2.do">
                                    <c:param name="page" value="previous"/></c:url>'>
                        <font><B>&lt;&lt; Prev</B></font>
                     </a>

                  </c:if> <c:if test="${!shoesList.lastPage}">
                     <a
                        href='<c:url value="/shop/company/list2.do">
                                    <c:param name="page" value="next"/></c:url>'>
                        <font><B>&gt;&gt; next</B></font>
                     </a>
                  </c:if></td>
            </tr>
         </tbody>
      </table>
   </div>
</div>
