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
<link rel="stylesheet" type="text/css" href="/moahair/css/kyuCSS.css?ver=20">
<link rel="stylesheet" href="/moahair/css/master.css" type="text/css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="/moahair/js/jquery-ui.js"></script>
<script src="/moahair/js/regForm.js?ver=4"></script>

<style>

.Designerbg_bk {
	background-image: linear-gradient(to bottom, rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), 
	url(/moahair/img/seller/business/${bpa.bs_background});
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


	<jsp:include page="../mainPage/header2.jsp" flush="true" />

	
	<div class="container">
		<div class="contentSellerBanner">
			<ul class="Designerbg_bk">
				<li>
					<img class="Sellerthumbnail" src="/moahair/img/seller/staff/${s_dto.s_profile }"
					 alt="프로필" title="프로필"/>
				</li>
				<li class="nameSellerProfile">${s_dto.s_name }<span class="s_title">(${s_dto.s_title })</span> </li>
				<li class="btnSellerProfile">
					<span id="fastaffclick" class="pointerCursor" onclick="addStaffWishList()" > 
					<c:if test="${sdwl == 1 }">
					<i class="fa fa-heart fared" aria-hidden="true"></i>
					찜하기
					</c:if>
					<c:if test="${sdwl == 0 }">
					<i id="fastaff" class="fa fa-heart" aria-hidden="true"></i>
					찜하기
					</c:if>
					
					
					</span>
					<div id="addalert"></div>
				</li>
				
				<li class="s_holidayAndopenclose">
					출근시간 : ${tdto[0].athirty } <br>
					퇴근시간 : ${tdto[1].athirty } <br>
				</li>
			</ul>
		</div>
	</div>
	
	<div class="container">
		<div class="contentSellerItem">
			<c:forEach var="DesigneritemList" items="${list }">
			<div class="containerItemwidth">
				<a href="/moahair/seller/product.do?i_num=${DesigneritemList.i_num }"> 
					<img class="sellerfloat" src="/moahair/img/item/thumbmnail/${DesigneritemList.i_thumbnail }">
					<div>${DesigneritemList.i_name }</div>
					<div> $ <fmt:formatNumber
							value="${DesigneritemList.i_price}" pattern="#,###원" /></div>
				</a>
			</div>
			</c:forEach>
		</div>
	</div>

			<jsp:include page="../mainPage/footer.jsp" flush="true" />
	

	<script>
	$("#fastaffclick").click(function() {
		$("#fastaff").css("color", "red");
	});
	
	function addStaffWishList() {
		var sessionId = "${sessionScope.memId}";
		if(!sessionId) {
			alert("로그인 후 이용 가능합니다.");
			window.location = "/moahair/member/loginForm.do";
		}
		else {
			var wish_s_num = ${s_num};
			
			$.ajax({
				type : "post", url : "/moahair/product/item/AddStaffWishList.do",
				data : {w_s_num : wish_s_num},
				success : function(data) {
					$("#addalert").html(data);
				}
			});
		}
	}
	</script>
	
</body>
</html>