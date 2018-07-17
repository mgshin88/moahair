<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/moahair/css/master.css" type="text/css">
<style>
.pageClick {
	cursor: pointer;
}
</style>

<div class="header">
	<div class="header_logo">
		<a href="/moahair/main/main.do">MOA
			<p>HAIR</p>
		</a>
	</div>
	<form class="search" style="margin-bottom: 0px;">
		<input type="search" name="listOpt" id="searchbar"
			placeholder="지역, 스타일 또는 상품">
		<button type="submit">
			<img src="/moahair/img/resource/button/search.jpg" />
		</button>
	</form>

	<div class="navi_bar_seller">
		<ul>
			<li class="navi_menu_seller" id="seller_home">
				<a href="#" onclick="home()">홈</a>

			</li>
			<li class="navi_menu_seller" id="seller_product">
				<a href="#" onclick="manageItem()">상품관리</a>

			</li>
			<li class="navi_menu_seller" id="seller_product">
				<a href="#" onclick="manageBooking()">예약관리</a>

			</li>
			<li class=navi_menu_seller id="seller_staff">
				<a href="#" onclick="manageStaff()">디자이너</a>


			</li>
			<li class="navi_menu_seller" id="seller_sales">
				<a href="#" onclick="manageAccount()">결산</a>

			</li>
			<li class="navi_menu_seller" id="seller_info">
				<a href="#" onclick="manageInfo()">내 정보</a>

			</li>
			<li class="navi_menu_seller" id="seller_board">
				<a href="#" onclick="manageQnA()">Q&A</a>

			</li>

			<li class="navi_menu_seller">
				<a href="/moahair/main/main.do" >나가기</a>

			</li>
		</ul>
	</div>


	<button type="button" name="button" id="mypage_seller">
		<img src="/moahair/img/resource/button/mypage.jpg" alt="">
	</button>

	<div id="member_modal">
		<c:if test="${sessionScope.memId!=null}">
			<div class="member">
				<div class="linker"></div>

				<a href="/moahair/member/logout.do">로그아웃 </a>


				<div class="informations">
					<p>
						${memId} 님 <br> <br>환영합니다!
					</p>
				</div>
				<a href="/moahair/mypage/MyPage.do" class="mypagetable"
					id="mypageinfo">마이페이지</a>
				<div class="informations">
					<a href="moahair/mypage/MyInformationForm.do" class="mypageinfo">정보수정</a>
					<a href="/moahair/mypage/BookingList.do" class="mypageinfo">예약확인</a>
					<a href="/moahair/mypage/myCart.do" class="mypageinfo">장바구니</a> <a
						href="/moahair/seller/sellersel.do" class="mypageinfo">매장관리</a>
					<div class="listbutton">
						<button type="button" name="button">전체보기</button>

					</div>
				</div>
				<a onclick="PageClick()" class="pageClick" id="cart">WishList</a>
				<div class="informations">

					<div id="salon" class="info">
						<p>매장</p>
						<a href="/moahair/seller/SellerView.do?bs_num=${w_bs_num }"><img
							src="/moahair/img/seller/business/thumbnail/${bs_profile }"
							alt="" align="middle"> <span>${bs_name }</span></a>
					</div>

					<div id="designer" class="info">
						<p>디자이너</p>
						<a href="/moahair/seller/DesignerView.do?s_num=${s_num }"><img
							src="/moahair/img/seller/staff/${s_profile }" alt=""
							align="middle"> <span>${s_name }</span></a>
					</div>

					<div id="product" class="info">
						<p>상품</p>
						<a href="/moahair/product/item/ProductView.do?i_num=${w_i_num }"><img
							src="/moahair/img/item/thumbnail/${i_thumbnail}" alt=""
							align="middle"> <span>${i_name}</span></a>
					</div>
					<div class="listbutton">
						<button type="button" name="button">전체보기</button>

					</div>
				</div>
			</div>
		</c:if>

		<c:if test="${sessionScope.memId==null}">
			<div class="member">

				<div class="linker"></div>

				<a href="/moahair/member/RegForm.do" class="regView" id="signUp"
					id="register">회원가입 </a> <a href="/moahair/member/loginForm.do"
					class="logInView" id="register">로그인 </a>
				<div class="informations">
					<h2>우리동네 잘하는 미용실은</h2>
					<h1>어디?</h1>

					<br> <br>
					<p>로그인 후 이용가능합니다!</p>
				</div>


			</div>
		</c:if>

		<%-- <c:if test="${sessionScope.memId==null}">
            <a href="/moahair/member/loginForm.do" class="logInView"
               id="register">로그인</a>
            <a href="/moahair/member/RegForm.do" class="regView" id="signUp">회원가입</a>
            <a onclick="PageClick()" class="pageClick" id="mypage">마이페이지</a>
            <a onclick="PageClick()" class="pageClick" id="cart">장바구니</a>
         </c:if>
         <c:if test="${sessionScope.memId!=null}">
            <c:if test="${memberLevel == 6 }">
               <a href="/moahair/seller/sellersel.do">관리</a>
               <a href="/moahair/member/logout.do">로그아웃</a>
               <a href="/moahair/mypage/MyPage.do" class="mypageView" id="mypage">마이페이지</a>
               <a href="/moahair/mypage/Cart.do" class="cartView" id="cart">장바구니</a>
            </c:if>
            <c:if test="${memberLevel != 6 }">
               <a href="/moahair/member/logout.do">로그아웃</a>
               <a href="/moahair/mypage/MyPage.do" class="mypageView" id="mypage">마이페이지</a>
               <a href="/moahair/mypage/Cart.do" class="cartView" id="cart">장바구니</a>
            </c:if>
         </c:if> --%>
	</div>

</div>


<script>
/* $('#seller_home, #seller_product, #seller_staff, #seller_sales, #seller_info, #seller_board').click(function() {
    $('#seller_home, #seller_product, #seller_staff, #seller_sales, #seller_info, #seller_board').toggle().css('font-weight', 'bold');
    $('[id^=model]').css('font-weight', 'normal'); */

	function PageClick() {
		var sessionId = "${sessionScope.memId}";
		if (!sessionId) {
			alert("로그인 후 이용 가능합니다.");
			window.location = "/moahair/member/loginForm.do";
		}

	}
	
	
<%-- MyPage Modal --%>
	var modal = document.getElementById('member_modal');

	var btn = document.getElementById("mypage_seller");

	$(document).ready(function() {
		$(btn).click(function() {
			var isActive = $(modal).hasClass('active');
			$(modal).slideUp(150).removeClass('active');
			if (!isActive) {
				$(modal).slideToggle(150).addClass('active');
				;
			}
		});
	});
<%-- fixed menu --%>
	$(document).ready(function() {

		var topBar = $(".header").offset();

		$(window).scroll(function() {

			var docScrollX = $(document).scrollTop()
			if (docScrollX > topBar.top) {
				$(".header").addClass("headerFixed");
			} else {
				$(".header").removeClass("headerFixed");
			}
		});
	})
</script>