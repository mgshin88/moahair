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
<link rel="stylesheet" type="text/css" href="/moahair/css/kyuCSS.css?ver=1">
<link rel="stylesheet" href="/moahair/css/master.css" type="text/css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="/moahair/js/jquery-ui.js"></script>
<script src="/moahair/js/regForm.js?ver=4"></script>
<script src="/moahair/js/modalView.js"></script>

</head>
<body>
	<!-- header -->
	<jsp:include page="../mainPage/header.jsp" flush="true" />

	<c:if test="${sessionScope.memId == null }">
	<div class="logregHeader">
		<div class="logregTitle">로그인</div>
	</div>
	
	<div class="logregFormView">
		<form id="formLogin" name="myform" class="login simpleForm"
		action="/moahair/member/loginPro.do" method="post"
		onSubmit="return checkItlogin()">
			<ul class="borderRight">
				<li>
					<input type="text" id="m_id" name="m_id" placeholder="아이디" title="아이디"/>					
				</li>
				<li>
					<input type="password" id="m_pw" name="m_pw" placeholder="비밀번호" title="비밀번호"/>
				</li>
				<li class="marginTop15">
					<input type="submit" id="btnLogin" class="btnBlack" value="로그인">
				</li>
				<li>
					<input type="button" id="btnReg" class="btnWhite" value="회원가입" 
					onclick="window.location.href='/moahair/member/RegForm.do'">
				</li>
				<li class="addtional">
					<a href="/moahair/member/idSearchCheck.do" id="btnFindId" class="findId">아이디 찾기</a>
					|
					<a href="/moahair/member/PwSearchCheck.do" id="btnFindPassword" class="findPassword">비밀번호 찾기</a>
				</li>
			</ul>
		</form>
	</div>
</c:if>

<!-- 
<c:if test="${sessionScope.memId != null }">
	<script>
		alert("로그인 되어 있습니다.");
		window.location = "/moahair/ethan/index.do";
	</script>
</c:if>
-->

	<!-- footer -->
	
		<jsp:include page="../mainPage/footer.jsp" flush="true" />
</body>
</html>