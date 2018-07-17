<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/moahair/css/jquery-ui.css">
<link rel="stylesheet" type="text/css"
	href="/moahair/css/fontawesome_463.css">
<link rel="stylesheet" type="text/css" href="/moahair/css/kyuCSS.css">
<link rel="stylesheet" href="/moahair/css/master.css" type="text/css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="/moahair/js/jquery-ui.js"></script>
<script src="/moahair/js/regForm.js?ver=4"></script>

<style>

.Sellerbg_bk {
	background-image: linear-gradient(to bottom, rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), 
	url(/moahair/images/${bpa.bs_background});
	width : 100%;
	margin : 0px;
	overflow : hidden;
	padding : 40px 0px 50px;
	color : #fff;
	background-size : cover;
}

</style>
</head>
<body>

	<!-- header -->
	<jsp:include page="../mainPage/header.jsp" flush="true" />
	
	<div class="container">
		<div class="contentSellerBanner">
			<ul class="Sellerbg_bk">
				<li>
					<img class="Sellerthumbnail" src="/moahair/img/seller/business/${bpa.bs_profile }"
					 alt="오잉?" title="끄에엥"/>
				</li>
				<li class="nameSellerProfile">${bpa.bs_name }</li>
				<li class="btnSellerProfile">
					<span id="fabsclick" class="pointerCursor" onclick="addBsWishList()" > 
					<c:if test="${sbwl == 1 }">
					<i class="fa fa-heart fared" aria-hidden="true"></i>
					찜하기
					</c:if>
					<c:if test="${sbwl == 0 }">
					<i id="fabs" class="fa fa-heart" aria-hidden="true"></i>
					찜하기
					</c:if>
					</span>
					<div id="addalert"></div>
				</li>
				<li class="addressSellerProfile">
					${bpa.ba_city } 
					${bpa.ba_surburb } 
					${bpa.ba_street } 
					${bpa.ba_rest }
				</li>
				<li class="holidayAndopenclose">
					오픈시간 : ${tdto[0].athirty } <br>
					마감시간 : ${tdto[1].athirty } <br>
				</li>
			</ul>
		</div>
	</div>
	
	<div class="container">
		<div class="contentDetail">
			<span class="SellerItem MenuHover"> 스타일 </span>
			
			<span class="SellerDesigner MenuHover"> 디자이너 정보 </span>
		</div>
		
	</div>
	
	<div class="container">
		<div class="contentSellerItem">
			<c:forEach var="bsitemList" items="${list }">
			<div class="containerItemwidth">
				<a href="/moahair/product/item/ProductView.do?i_num=${bsitemList.i_num }"> 
					<img class="sellerfloat" src="/moahair/img/item/thumbnail/${bsitemList.i_thumbnail }">
					<div>${bsitemList.i_name }</div>
					<div> $ <fmt:formatNumber
							value="${bsitemList.i_price}" pattern="#,###원" /></div>
				</a>
			</div>
			</c:forEach>
		</div>
		
		<div class="contentDesignerView">
			<c:forEach var="slist" items="${slist }">
				<div class="containerDesignerwidth">
					<a href="/moahair/seller/DesignerView.do?s_num=${slist.s_num }"> <img src="/moahair/img/seller/staff/${slist.s_profile }"
						class="float" />
						<div>
							<span class="s_title">${slist.s_title }</span> <span
								class="s_name">${slist.s_name }</span><br />
						</div>
					</a>
				</div>
			</c:forEach>
		</div>
	</div>
		<!-- footer -->
	
		<jsp:include page="../mainPage/footer.jsp" flush="true" />
	<script>
		
		$("#fabsclick").click(function() {
			$("#fabs").css("color", "red");
		});
	
		$(".SellerItem").css("font-weight", "bold");
		
		$(".SellerItem").click(function() {
			$(".SellerItem").css("font-weight", "bold");
			$(".SellerDesigner").css("font-weight", "");
			$(".contentDesignerView").css("display", "none");
			$(".contentSellerItem").css("display", "table");
			
			
		})
		
		$(".SellerDesigner").click(function() {
			$(".SellerItem").css("font-weight", "");
			$(".SellerDesigner").css("font-weight", "bold");
			$(".contentSellerItem").css("display", "none");
			$(".contentDesignerView").css("display", "table");
		})
		
		function addBsWishList() {
		var sessionId = "${sessionScope.memId}";
		if(!sessionId) {
			alert("로그인 후 이용 가능합니다.");
			window.location = "/moahair/member/loginForm.do";
		}
		else {
			var wish_bs_num = ${bs_num};
			
			$.ajax({
				type : "post", url : "/moahair/product/item/AddBsWishList.do",
				data : {w_bs_num : wish_bs_num},
				success : function(data) {
					$("#addalert").html(data);
				}
			});
		}
	}
	</script>
</body>
</html>