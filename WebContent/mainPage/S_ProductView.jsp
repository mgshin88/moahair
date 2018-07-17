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
<script src="/moahair/js/regForm.js"></script>

<script>
	$(function() {
		$("#i_option").selectmenu();
	});

	$(function() {
		$("#i_option_sel1").selectmenu();
	});

	$(function() {
		$("#i_option_sel2").selectmenu();
	});
</script>
</head>
<body>

	<jsp:include page="../mainPage/header2.jsp" flush="true" />

	<form id="Cart_Reserve" name="Cart_Reserve" method="post">
		<input type="hidden" name="i_num" value="${dto.i_num }"> <input
			type="hidden" name="i_thumbnail" value="${dto.i_thumbnail }">
		<input type="hidden" name="i_name" value="${dto.i_name }"> <input
			type="hidden" name="i_price" value="${dto.i_price}">

		<div class="content">
			<div class="containerItemDetail">
				<div class="ImgItemDetail">
					<img id="productThumnail"
						src="/moahair/img/item/thumbnail/${dto.i_thumbnail }">
					<!-- 
				<img id="productThumnail"
					src="https://image.brandi.me/cproduct/2018/03/21/3113599_1521609982_image1_L.jpg"
					alt="이미지입니다.">
				-->
				</div>
				<ul class="infoItemDetail">
					<li class="titleItem">${dto.i_name }</li>

					<li class="priceItem">$ <fmt:formatNumber
							value="${dto.i_price}" pattern="#,###원" /></li>

					<li class="productDetailInfoArea">
						<div class="productDesideOption">

							<label class="labelOption" for="speed">[필수옵션]</label> <select
								id="i_option" name="i_option">
								<c:forEach var="options" items="${option }" varStatus="i">
									<option value="${options }">${options}</option>
								</c:forEach>
							</select>

						</div>
						<div class="productAddOptionMain">
							<c:if test="${dtooption1 != null }">
								<div id="productAddOption1" class="productAddOption">
									<label class="labelOption" for="add1">추가옵션1</label> <select
										id="i_option_sel1" name="i_option_sel1">
										<c:forEach var="addoptions" items="${dtooption1 }"
											varStatus="i">
											<option value="${addoptions }">${addoptions }</option>
										</c:forEach>
									</select>
								</div>
							</c:if>

							<c:if test="${dtooption2 != null }">
								<div id="productAddOption2" class="productAddOption">
									<label class="labelOption" for="add2">추가옵션2</label> <select
										id="i_option_sel2" name="i_option_sel2">
										<c:forEach var="addoptions" items="${dtooption2 }"
											varStatus="i">
											<option value="${addoptions }">${addoptions }</option>
										</c:forEach>
									</select>
								</div>
							</c:if>
						</div>

					</li>

					<li>
						<div style="width: 100%; display: table;">
							<span id="faitemclick" class="btnFavorItem"
								onclick="addItemWishList()"> <c:if test="${siwl == 1 }">
									<i class="fa fa-heart fared" aria-hidden="true"></i> 찜하기
							</c:if> <c:if test="${siwl == 0 }">
									<i id="faitem" class="fa fa-heart" aria-hidden="true"></i> 찜하기
							</c:if>
							</span> <span class="btnCartItem" onclick="cartSubmit(1)">장바구니</span>

						</div> <span class="btnBuyItem" onclick="cartSubmit(2)">예약하기</span>
						<div id="addalert"></div>
					</li>

					<li class="marketInfo"><a
						href="/moahair/seller/store?bs_num=${dto.bs_num }"> <img
							id="marketImg"
							src="/moahair/img/seller/business/${dto.bs_profile }"
							alt="엔비룩" /> <span class="marketName">${dto.bs_name }</span>
					</a>
						<div class="marketContent">영업 시간 : ${tdto[0].athirty } ~
							${tdto[1].athirty }</div>
						<div>
							<span id="fabsclick" class="pointerCursor"
								onclick="addBsWishList()"> <c:if test="${sbwl == 1 }">
									<i class="fa fa-heart fared" aria-hidden="true"></i>
								</c:if> <c:if test="${sbwl == 0 }">
									<i id="fabs" class="fa fa-heart" aria-hidden="true"></i>
								</c:if>

							</span> <a href="/moahair/seller/store?bs_num=${dto.bs_num }">더
								보기 <i class="fa fa-angle-right" aria-hidden="true"></i>
							</a>
						</div></li>
				</ul>
			</div>
		</div>
	</form>
	<hr />

	<div class="content">
		<div class="containerDesignerDetail">
			<div class="containerDesignerProduce">
				<span class="DesignerText">디자이너 소개</span>
			</div>
			<c:forEach var="slist" items="${slist }">
				<div class="containerDesignerwidth">
					<a href="/moahair/seller/staff.do?s_num=${slist.s_num }">
						<img src="/moahair/img/seller/staff/${slist.s_profile }"
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

	<hr />

	<div class="content">
		<div class="contentProductDetail">
			<div class="ProductViewTop">상품정보</div>

			<div class="ProductViewContent">${dto.i_contents }</div>
		</div>
	</div>
		<jsp:include page="../mainPage/footer.jsp" flush="true" />


	<script>
		$("#faitemclick").click(function() {
			$("#faitem").css("color", "red");
		});

		$("#fabsclick").click(function() {
			$("#fabs").css("color", "red");
		});

		function cartSubmit(index) {
			var sessionId = "${sessionScope.memId}";
			if (!sessionId) {
				alert("로그인 후 이용 가능합니다.");
				window.location = "/moahair/member/loginForm.do";
			} else {
				var formData = $("#Cart_Reserve").serialize();
				if (index == 1) {
					$.ajax({
						type : "post",
						url : "/moahair/product/item/cartPro.do",
						data : formData,
						success : function(data) {
							$("#addalert").html(data);
						}
					});
				}

				if (index == 2) {
					$.ajax({
						type : "post",
						url : "/moahair/booking/bookingForm.do",
						data : formData,
						success : function(data) {
							alert(data);
							$(".infoItemDetail").html(data);
						}
					});
				}
			}

		}

		function addItemWishList() {
			var sessionId = "${sessionScope.memId}";
			if (!sessionId) {
				alert("로그인 후 이용 가능합니다.");
				window.location = "/moahair/member/loginForm.do";
			} else {
				var wish_i_num = $
				{
					i_num
				}
				;

				$.ajax({
					type : "post",
					url : "/moahair/product/item/AddItemWishList.do",
					data : {
						w_i_num : wish_i_num
					},
					success : function(data) {
						$("#addalert").html(data);
					}
				});
			}
		}

		function addBsWishList() {
			var sessionId = "${sessionScope.memId}";
			if (!sessionId) {
				alert("로그인 후 이용 가능합니다.");
				window.location = "/moahair/member/loginForm.do";
			} else {
				var wish_bs_num = $
				{
					bs_num
				}
				;

				$.ajax({
					type : "post",
					url : "/moahair/product/item/AddBsWishList.do",
					data : {
						w_bs_num : wish_bs_num
					},
					success : function(data) {
						$("#addalert").html(data);
					}
				});
			}
		}
	</script>

</body>
</html>