<%@ include file="IncludeTop.jsp"%>
<%@ page pageEncoding="UTF-8"%>
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<style>
/* Make the image fully responsive */
.carousel-inner img {
	width: 95%;
	height: 86%;
}
</style>
</head>
<div class="container mt-3">
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ul class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ul>
		<!-- The slideshow -->
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="../images/bn1.jpg" alt="Los Angeles" width="300"
					height="300">
			</div>
			<div class="carousel-item">
				<img src="../images/bn2.jpg" alt="Chicago" width="300" height="300">
			</div>
			<div class="carousel-item">
				<img src="../images/bn3.jpg" alt="Chicago" width="300" height="300">
			</div>

		</div>
		<!-- Left and right controls -->
		<a class="carousel-control-prev" href="#myCarousel" data-slide="prev">
			<span class="carousel-control-prev-icon"></span>
		</a> <a class="carousel-control-next" href="#myCarousel" data-slide="next">
			<span class="carousel-control-next-icon"></span>
		</a>
	</div>
</div>
<%-- <table style="border: none; border-collapse: collapse; width: 100%">
	<tr>
		<td style="text-align: left; vertical-align: top; width: 100%">
			<table style="border: none; border-collapse: collapse; width: 80%">
				<tr>
					<td valign="top">
						<!-- SIDEBAR -->
						<table id="index">
							<tr>
								<td><c:if test="${!empty userSession.account}">
										<b><i><font size="4" color="RED">Welcome
													${userSession.account.firstName}!</font></i></b>
									</c:if>&nbsp;</td>
							</tr>
							<tr>
								<td><a
									href="<c:url value="/shop/viewCategory.do?categoryId=FISH"/>">
										<img border="0" src="../images/fish_icon.gif" />
								</a></td>
							</tr>
							<tr>
								<td><a
									href="<c:url value="/shop/viewCategory.do?categoryId=DOGS"/>">
										<img border="0" src="../images/dogs_icon.gif" />
								</a></td>
							</tr>
							<tr>
								<td><a
									href="<c:url value="/shop/viewCategory.do?categoryId=CATS"/>">
										<img border="0" src="../images/cats_icon.gif" />
								</a></td>
							</tr>
							<tr>
								<td><a
									href="<c:url value="/shop/viewCategory.do?categoryId=REPTILES"/>">
										<img border="0" src="../images/reptiles_icon.gif" />
								</a></td>
							</tr>
							<tr>
								<td><a
									href="<c:url value="/shop/viewCategory.do?categoryId=BIRDS"/>">
										<img border="0" src="../images/birds_icon.gif" />
								</a></td>
							</tr>
							<tr>
								<td><a href="<c:url value="/shop/main/secondhand.do"/>">회원간거래</a></td>
							</tr>
							<tr>
								<td><a href="<c:url value="/shop/main/auctionList.do"/>">
										경매</a></td>
							</tr>
						</table>
					</td>
					<td
						style="text-align: center; background-color: white; height: 300; width: 100%">
						<!-- MAIN IMAGE --> <map name="estoremap">
							<area alt="Birds" coords="72,2,280,250"
								href="viewCategory.do?categoryId=BIRDS" shape="RECT" />
							<area alt="Fish" coords="2,180,72,250"
								href="viewCategory.do?categoryId=FISH" shape="RECT" />
							<area alt="Dogs" coords="60,250,130,320"
								href="viewCategory.do?categoryId=DOGS" shape="RECT" />
							<area alt="Reptiles" coords="140,270,210,340"
								href="viewCategory.do?categoryId=REPTILES" shape="RECT" />
							<area alt="Cats" coords="225,240,295,310"
								href="viewCategory.do?categoryId=CATS" shape="RECT" />
							<area alt="Birds" coords="280,180,350,250"
								href="viewCategory.do?categoryId=BIRDS" shape="RECT" />
						</map> <img src="../images/splash.gif" usemap="#estoremap"
						style="float: center; width: 350; height: 355; border: 0;" />
					</td>
				</tr>
			</table>
		</td>
	</tr> --%>
<%-- </table>

<%@ include file="IncludeBanner.jsp"%>

<%@ include file="IncludeBottom.jsp"%> --%>
