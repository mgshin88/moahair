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

	<div class="navi_bar">
		<ul>
			<li class="navi_menu" id="area1">
				<p>지역</p>

			</li>
			<li class="navi_menu" id="gender1">
				<p>성별</p>

			</li>
			<li class="navi_menu" id="serviceType1">
				<p>서비스</p>


			</li>
			<li class="navi_menu" id="price1">
				<p>가격</p>

			</li>
		</ul>
	</div>

	<div class="navi_list">
		<ul class="navi_hide" id="area2">
			<li><input type="checkbox" name="ch1" id="metro"
				class="checkbox" value="li.ba_state-'수도권'" onclick="filter();"><label
				for="metro" class="check_with_label">서울/수도권</label></li>
			<li><input type="checkbox" name="ch1" id="countryside1"
				class="checkbox" value="li.ba_state-'충청/강원'" onclick="filter();"><label
				for="countryside1" class="check_with_label">충청/강원</label></li>
			<li><input type="checkbox" name="ch1" id="countryside2"
				class="checkbox" value="li.ba_state-'영남'" onclick="filter();"><label
				for="countryside2" class="check_with_label">영남</label></li>
			<li><input type="checkbox" name="ch1" id="countryside3"
				class="checkbox" value="li.ba_state-'호남/제주'" onclick="filter();"><label
				for="countryside3" class="check_with_label">호남/제주</label></li>
		</ul>
		<ul class="navi_hide" id="gender2">
			<li><input type="checkbox" name="ch2" id="male" class="checkbox"
				value="li.i_gender-'1'" onclick="filter();"><label
				for="male" class="check_with_label">남</label></li>
			<li><input type="checkbox" name="ch2" id="female"
				class="checkbox" value="li.i_gender-'2'" onclick="filter();"><label
				for="female" class="check_with_label">여</label></li>
		</ul>
		<ul class="navi_hide" id="serviceType2">
			<li><input type="checkbox" name="ch3" id="cut" class="checkbox"
				value="li.i_type-'커트'" onclick="filter();"><label for="cut"
				class="check_with_label">커트</label></li>
			<li><input type="checkbox" name="ch3" id="perm" class="checkbox"
				value="li.i_type-'펌'" onclick="filter();"><label for="perm"
				class="check_with_label">펌</label></li>
			<li><input type="checkbox" name="ch3" id="dye" class="checkbox"
				value="li.i_type-'염색'" onclick="filter();"><label for="dye"
				class="check_with_label">염색</label></li>
			<li><input type="checkbox" name="ch3" id="extension"
				class="checkbox" value="li.i_type-'익스텐션'" onclick="filter();"><label
				for="extension" class="check_with_label">익스텐션</label></li>
			<li><input type="checkbox" name="ch3" id="styling"
				class="checkbox" value="li.i_type-'스타일링'" onclick="filter();"><label
				for="styling" class="check_with_label">스타일링</label></li>
			<li><input type="checkbox" name="ch3" id="caring"
				class="checkbox" value="li.i_type-'케어'" onclick="filter();"><label
				for="caring" class="check_with_label">케어</label></li>
			<li><input type="checkbox" name="ch3" id="product"
				class="checkbox" value="li.i_type-'헤어상품'" onclick="filter();"><label
				for="product" class="check_with_label">헤어상품</label></li>
		</ul>

		<ul class="navi_hide" id="price2">
			<li><input type="radio" name="ch4" id="price1" class="checkbox"
				value="li.i_price-50000" onclick="filter();"><label
				for="price1" class="check_with_label">50,000 이하</label></li>
			<li><input type="radio" name="ch4" id="price2" class="checkbox"
				value="li.i_price-100000" onclick="filter();"><label
				for="price2" class="check_with_label">100,000 이하</label></li>
			<li><input type="radio" name="ch4" id="price3" class="checkbox"
				value="li.i_price-200000" onclick="filter();"><label
				for="price3" class="check_with_label">200,000 이하</label></li>
			<li><input type="radio" name="ch4" id="price4" class="checkbox"
				value="li.i_price-200001" onclick="filter();"><label
				for="price4" class="check_with_label">200,000 이상</label></li>
		</ul>
	</div>

	<button type="button" name="button" id="mypage">
		<img src="/moahair/img/resource/button/mypage.jpg" alt="">
	</button>

	<div id="member_modal">
		<c:if test="${sessionScope.memId!=null}">
			<c:if test="${memberLevel != 6 && memberLevel!=10}">
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
						<a href="#" onClick="myinfo()" class="mypageinfo">정보수정</a> <a
							href="/moahair/member/bookingList.do" class="mypageinfo">예약확인</a>
						<a href="/moahair/member/cartList.do" class="mypageinfo">장바구니</a>
						<div class="listbutton">
							<a href="/moahair/mypage/MyPage.do" class="mypagetable"
								id="mypageinfo"><button type="button" name="button">전체보기</button></a>

						</div>
					</div>
					<a href="/moahair/mypage/WishList.do" class="wishlistButton"
						id="cart">WishList</a>
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
							<a href="/moahair/mypage/WishList.do"><button type="button"
									name="button">전체보기</button> </a>
						</div>
					</div>
				</div>
			</c:if>
			<c:if test="${memberLevel == 6 }">
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
						<a href="/moahair/mypage/PwCheckForm.do" class="mypageinfo">정보수정</a>
						<a href="/moahair/member/bookingList.do" class="mypageinfo">예약확인</a>
						<a href="/moahair/member/cartList.do" class="mypageinfo">장바구니</a>
						<a href="/moahair/seller/sellersel.do" class="mypageinfo">매장관리</a>
						<div class="listbutton">
							<a href="/moahair/mypage/MyPage.do" class="mypagetable"
								id="mypageinfo"><button type="button" name="button">전체보기</button></a>

						</div>
					</div>
					<a href="/moahair/mypage/WishList.do" class="wishlistButton"
						id="cart">WishList</a>
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
							<a href="/moahair/mypage/WishList.do"><button type="button"
									name="button">전체보기</button> </a>
						</div>
					</div>
				</div>
			</c:if>

			<c:if test="${memberLevel == 10 }">
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
						<a href="/moahair/mypage/PwCheckForm.do" class="mypageinfo">정보수정</a>
						<a href="/moahair/member/bookingList.do" class="mypageinfo">예약확인</a>
						<a href="/moahair/member/cartList.do" class="mypageinfo">장바구니</a>
						<a href="/moahair/supervisor/SupervisorMain.do" class="mypageinfo">사이트관리</a>
						<div class="listbutton">
							<a href="/moahair/mypage/MyPage.do" class="mypagetable"
								id="mypageinfo"><button type="button" name="button">전체보기</button></a>

						</div>
					</div>
					<a href="/moahair/mypage/WishList.do" class="wishlistButton"
						id="cart">WishList</a>
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
							<a href="/moahair/mypage/WishList.do"><button type="button"
									name="button">전체보기</button> </a>
						</div>
					</div>
				</div>
			</c:if>
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

	</div>

</div>


<script>
	function PageClick() {
		var sessionId = "${sessionScope.memId}";
		if (!sessionId) {
			alert("로그인 후 이용 가능합니다.");
			window.location = "/moahair/member/loginForm.do";
		}

	}
<%-- MyPage Modal --%>
	var modal = document.getElementById('member_modal');

	var btn = document.getElementById("mypage");

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
<%-- navigation --%>
	$(document).ready(function() {
		$(".navi_bar, .navi_hide").mouseover(function() {
			$(".navi_hide").stop().slideDown(400);
		}).mouseleave(function() {
			$(".navi_hide").stop().slideUp(400);
		})
	})
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

	/* 	function myinfo(){
	 this.click(function(){
	 location.h
	 })
	 } */
</script>