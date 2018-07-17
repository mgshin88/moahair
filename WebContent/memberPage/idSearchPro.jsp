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
<link rel="stylesheet" type="text/css" href="/moahair/css/kyuCSS.css?ver=4">
<link rel="stylesheet" href="/moahair/css/master.css" type="text/css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="/moahair/js/jquery-ui.js"></script>
<script src="/moahair/js/regForm.js?ver=1"></script>
<script src="/moahair/js/modalView.js"></script>

</head>
<body>
<!-- header -->
	<jsp:include page="../mainPage/header.jsp" flush="true" />

<c:if test="${m_id != null }">
	<div class="logregHeader">
		<div class="logregTitle borderBottom">아이디 찾기</div>
	</div>
	
	<div class="logregCheckView simpleForm">
		<div class="resultBox">
			<p>고객님의 아이디는 다음과 같습니다.</p>
			<div id="text_id" class="resultIdMsg">
				${m_id }
			</div>
		</div>
		<hr/>
		<div class="btns">
			<input type="button" id="goLogin" class="btnBlack width200" value="로그인" 
			onclick="location.href='/moahair/member/loginForm.do'" />
			<input type="button" id="goPwcheck" class="btnWhite width200" value="비밀번호 찾기"
			onclick="location.href='/moahair/member/PwSearchCheck.do'" />			
		</div>
	</div>
</c:if>

<c:if test="${m_id == null }">
	<script>
		alert("인증번호가 틀립니다.");
		location.href = "/moahair/member/idSearchCheck.do";
	</script>
</c:if>

	<!-- footer -->
	
		<jsp:include page="../mainPage/footer.jsp" flush="true" />
</body>
</html>