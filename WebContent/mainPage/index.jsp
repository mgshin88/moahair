<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="/moahair/css/master.css" type="text/css">
<link rel="stylesheet"
	href="/moahair/mainPage/flexslider/flexslider.css" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="/moahair/mainPage/flexslider/jquery.flexslider.js"></script>
<title>MOA Hair</title>
</head>
<body>




	<%-- Head --%>


	<jsp:include page="header.jsp" flush="false" />

	<div class="space"></div>


	<%-- Slider --%>


	<div class="flexslider">
		<ul class="slides">
			<li class="slider_content">
				<ul>
					<a href="/moahair/product/item/ProductView.do?i_num=3"> <img src="/moahair/img/slider/1.jpg"></a>
				</ul>
				<ul>
					<a href="/moahair/product/item/ProductView.do?i_num=4"> <img src="/moahair/img/slider/2.jpg"></a>
				</ul>
			</li>
			<li class="slider_content">
				<ul>
					<a href="/moahair/product/item/ProductView.do?i_num=5"> <img src="/moahair/img/slider/3.jpg"></a>
				</ul>
				<ul>
					<a href="/moahair/product/item/ProductView.do?i_num=6"> <img src="/moahair/img/slider/4.jpg"></a>
				</ul>
			</li>
			<li class="slider_content">
				<ul>
					<a href="/moahair/product/item/ProductView.do?i_num=7"> <img src="/moahair/img/slider/5.jpg"></a>
				</ul>
				<ul>
					<a href="/moahair/product/item/ProductView.do?i_num=8"> <img src="/moahair/img/slider/6.jpg"></a>
				</ul>
			</li>
			<!-- <li class="slider_content">
            <ul>
               <a href=""> <img src="/moahair/mainPage/slider/3.jpg"></a>
            </ul>
            <ul>
               <a href=""> <img src="/moahair/mainPage/slider/4.jpg"></a>
            </ul>
         </li>
         <li class="slider_content">
            <ul>
               <a href=""> <img src="/moahair/mainPage/slider/1.jpg"></a>
            </ul>
            <ul>
               <a href=""> <img src="/moahair/mainPage/slider/2.jpg"></a>
            </ul>
         </li>
         <li class="slider_content">
            <ul>
               <a href=""> <img src="/moahair/mainPage/slider/3.jpg"></a>
            </ul>
            <ul>
               <a href=""> <img src="/moahair/mainPage/slider/4.jpg"></a>
            </ul>
         </li> -->
		</ul>
	</div>




	<%-- Weekly Hot items --%>

	<form class="hotItem" action="index.html" method="post">
		<fieldset class="hotItemName">
			<legend>&nbsp; &nbsp; WEEKLY HOT ITEMS &nbsp; &nbsp; </legend>
			<div class="hotItemList">

				<c:forEach var="b" items="${BestList }">
					<ul>
						<li class="contentBox"><a
							href="/moahair/product/item/ProductView.do?i_num=${b.i_num }"><img
								src="/moahair/img/item/thumbnail/${b.i_thumbnail}"></a></li>
						<li class="textOverFlowHidden"><a
							href="/moahair/product/item/ProductView.do?i_num=${b.i_num }">${b.i_name }</a></li>
						<li class="priceOverFlowHidden"><span>$</span>&nbsp;${b.i_price}
						</li>
						<li class="businessEmblem"><a
							href="/moahair/seller/SellerView.do?bs_num=${b.i_bs_num }"><img
								src="/moahair/img/seller/business/${b.bs_profile}"><br>
								<p>&nbsp;${b.bs_name}&nbsp;</p></a></li>
					</ul>
				</c:forEach>

			</div>
			<br> <br> <br>
		</fieldset>
	</form>



	<%-- content list --%>


	<div class="itemList" id="itemList">


		<c:forEach var="i" items="${ItemList }">
			<ul class="contentList">
				<li class="contentBox"><a
					href="/moahair/product/item/ProductView.do?i_num=${i.i_num }"><img
						src="/moahair/img/item/thumbnail/${i.i_thumbnail}"></a></li>
				<li class="textOverFlowHidden"><a
					href="/moahair/product/item/ProductView.do?i_num=${i.i_num }">${i.i_name}</a></li>
				<li class="priceOverFlowHidden">${i.i_price}&nbsp;<span>원</span></li>
				<li class="businessEmblem"><a
					href="/moahair/seller/SellerView.do?bs_num=${i.i_bs_num }"><img
						src="/moahair/img/seller/business/${i.bs_profile }"
						title=""><br>
						<p>&nbsp;${i.bs_name }&nbsp;</p></a></li>
			</ul>
		</c:forEach>

		<div id="list_add"></div>


	</div>


	<jsp:include page="moreButton.jsp" flush="false" />


	<%-- Footer --%>


	<jsp:include page="footer.jsp" flush="false" />


	<%-- script --%>
	<script type="text/javascript">
		
	<%-- slider --%>
		$(window).load(function() {
			$('.flexslider').flexslider({
				animation : "slide"
			});
		});
	<%-- Filter --%>
		var pageNum = 2;
		var chValues1 = [];
		var chValues2 = [];
		var chValues3 = [];
		var chValues4 = [];
		function filter() {
			$("input[name='ch1']:checked").each(function(i) {
				chValues1.push($(this).val());
			});
			$("input[name='ch2']:checked").each(function(i) {
				chValues2.push($(this).val());
			});
			$("input[name='ch3']:checked").each(function(i) {
				chValues3.push($(this).val());
			});
			$("input[name='ch4']:checked").each(function(i) {
				chValues4.push($(this).val());
			});
			var allData = {
				ch1 : chValues1,
				ch2 : chValues2,
				ch3 : chValues3,
				ch4 : chValues4,
			};
			$.ajax({
				type : "post",
				url : "/moahair/main/filter.do",
				data : allData,
				success : function(data) {
					$("#itemList").html(data);
					chValues1 = [];
					chValues2 = [];
					chValues3 = [];
					chValues4 = [];
					pageNum = 2;
				}
			});
		}
	<%-- Page Loading --%>
		var idx = 2;
		var idx_p = 1;

		function showMore(){

					var newDiv = document.createElement('div');
					newDiv.innerHTML = '<a href="loading.jsp">';
					newDiv.setAttribute('id','more' + idx);
					setTimeout(function() {
						morediv = '#more'+ idx;
						morediv_p = '#more'+ idx_p;
						var reload_href_full = '/moahair/main/content.do?pageNum='+ pageNum+ '#itemList';
						$(newDiv).insertBefore('#list_add');
						$(morediv).load(reload_href_full).fadeIn(1000).delay(3000);
						pageNum++;
						idx++;
						idx_p++;
						}, 1000);
					};
			
				
	</script>
</body>
</html>
