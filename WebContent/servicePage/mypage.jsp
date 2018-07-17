<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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
<script
	src="<%=request.getContextPath()%>/sellerPage/se_editor/js/HuskyEZCreator.js"
	type="text/javascript" charset="UTF-8"></script>
<script
	src="<%=request.getContextPath()%>/sellerPage/se_editor/photo_uploader/plugin/hp_SE2M_AttachQuickPhoto.js"
	type="text/javascript" charset="UTF-8"></script>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="/moahair/js/jquery-ui.js"></script>
<script src="/moahair/js/regForm.js"></script>
</head>
<body>

<c:if test="${sessionScope.memId == null }">
		<script>
			alert("로그인 후 가능합니다.");
			window.location = "/moahair/member/loginForm.do";
		</script>
	</c:if>

<script type="text/javascript">
		function myinfo() {
		$("#myinfo").css("background-color", "#58D68D");
		$("#myinfo").css("color", "#ffffff");
		
		$("#wishlist").css("background-color", "");
		$("#wishlist").css("color", "");
		$("#cartshow").css("background-color", "");
		$("#cartshow").css("color", "");
		$("#purchased").css("background-color", "");
		$("#purchased").css("color", "");
		$("#booking").css("background-color", "");
		$("#booking").css("color", "");
		$("#qna").css("background-color", "");
		$("#qna").css("color", "");
			$.ajax({
				type : "post",
				url : "/moahair/mypage/PwCheckForm.do",
				success : function(data) {
					$(".contentMyPage").html(data);
				}
			});
		}
		function pwcheck() {
			$.ajax({
						type : "post",
						url : "/moahair/mypage/PwCheck.do",
						data : {
							m_pw : $("#m_pw").val()
						},
						success : function(data) {
							var t = data.trim();
							if (t == 'a') {
								$.ajax({
			            			type : "post",
			            			url : "/moahair/mypage/MyInformationForm.do",
			            			success : function(data) {
			            				$(".contentMyPage").html(data);	
			            			}
			            	   })
							} else {
								alert("비밀번호가 일치하지 않습니다.");
								$("#m_pw").val("");
								return false;
							}
						}
					});
		}

		function myqna() {
			$.ajax({
				type : "post",
				url : "/moahair/mypage/QnAList.do",
				success : function(data) {
					$(".contentMyPage").html(data);
				}
			});

		}

		function wishlist() {
			$("#wishlist").css("background-color", "#58D68D");
			$("#wishlist").css("color", "#ffffff");
			
			$("#myinfo").css("background-color", "");
			$("#myinfo").css("color", "");
			$("#cartshow").css("background-color", "");
			$("#cartshow").css("color", "");
			$("#purchased").css("background-color", "");
			$("#purchased").css("color", "");
			$("#booking").css("background-color", "");
			$("#booking").css("color", "");
			$("#qna").css("background-color", "");
			$("#qna").css("color", "");
			$.ajax({
				type : "post",
				url : "/moahair/mypage/WishList.do",
				success : function(data) {
					$(".contentMyPage").html(data);
				}
			});

		}

		function Cart() {
			$("#cartshow").css("background-color", "#58D68D");
			$("#cartshow").css("color", "#ffffff");
			
			$("#wishlist").css("background-color", "");
			$("#wishlist").css("color", "");
			$("#myinfo").css("background-color", "");
			$("#myinfo").css("color", "");
			$("#purchased").css("background-color", "");
			$("#purchased").css("color", "");
			$("#booking").css("background-color","");
			$("#booking").css("color", "");
			$("#qna").css("background-color", "");
			$("#qna").css("color", "");
			$.ajax({
				type : "post",
				url : "/moahair/mypage/myCart.do",
				success : function(data) {
					$(".contentMyPage").html(data);
				}
			});

		}

		function Purchased() {
			$("#purchased").css("background-color", "#58D68D");
			$("#purchased").css("color", "#ffffff");
			
			$("#wishlist").css("background-color", "");
			$("#wishlist").css("color", "");
			$("#cartshow").css("background-color", "");
			$("#cartshow").css("color", "");
			$("#myinfo").css("background-color", "");
			$("#myinfo").css("color", "");
			$("#booking").css("background-color", "");
			$("#booking").css("color", "");
			$("#qna").css("background-color", "");
			$("#qna").css("color", "");
			$.ajax({
				type : "post",
				url : "/moahair/mypage/Purchased.do",
				success : function(data) {
					$(".contentMyPage").html(data);
				}
			});

		}

		function Booking() {
			$("#booking").css("background-color", "#58D68D");
			$("#booking").css("color", "#ffffff");
			
			$("#wishlist").css("background-color", "");
			$("#wishlist").css("color", "");
			$("#cartshow").css("background-color", "");
			$("#cartshow").css("color", "");
			$("#purchased").css("background-color", "");
			$("#purchased").css("color", "");
			$("#myinfo").css("background-color", "");
			$("#myinfo").css("color", "");
			$("#qna").css("background-color", "");
			$("#qna").css("color", "");
			$.ajax({
				type : "post",
				url : "/moahair/mypage/BookingList.do",
				success : function(data) {
					$(".contentMyPage").html(data);
				}
			});

		}

		function QnA() {
			$("#qna").css("background-color", "#58D68D");
			$("#qna").css("color", "#ffffff");
			
			$("#wishlist").css("background-color", "");
			$("#wishlist").css("color", "");
			$("#cartshow").css("background-color", "");
			$("#cartshow").css("color", "");
			$("#purchased").css("background-color", "");
			$("#purchased").css("color", "");
			$("#booking").css("background-color", "");
			$("#booking").css("color", "");
			$("#myinfo").css("background-color", "");
			$("#myinfo").css("color", "");
			$.ajax({
				type : "post",
				url : "/moahair/mypage/MyQnA.do",
				success : function(data) {
					$(".contentMyPage").html(data);
				}
			});

		}
	</script>
	
	<jsp:include page="../mainPage/header.jsp" flush="false" />
	
	<div class="logregHeader">
		<div class="logregTitle">My Page ( ${ sessionScope.memId } )</div>
	</div>

	
	<div id="logoArea"></div>

	<div class="mypageContent">
		<div class="tabMyPage">
			<c:if test="${sessionScope.memId != null }">
				<span id="myinfo" class="myPageHover" onclick="myinfo();">내 정보관리</span>
				<br />
				<!-- 4/12 qna 추가함  -->
				<span id="qna" class="myPageHover" onclick="QnA();">QnA</span>
				<br />

				<span id="wishlist" class="myPageHover" onclick="wishlist()">위시리스트</span>
				<br />

				<span id="cartshow" class="myPageHover" onclick="Cart()">장바구니</span>
				<br />


				<span id="booking" class="myPageHover" onclick="Booking()">예약확인</span>
				<br />


				<span id="purchased" class="myPageHover" onclick="Purchased()">구매내역</span>
				<br />


			</c:if>
		</div>
	</div>

	<div class="mypageContent">
		<div class="contentMyPage">
		</div>
	</div>
	<jsp:include page="../mainPage/footer.jsp" flush="false" />

</body>
</html>