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
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="/moahair/js/jquery-ui.js"></script>
<script src="/moahair/js/regForm.js"></script>
<script src="/moahair/js/modalView.js"></script>

</head>
<body>
<!-- header -->
	<jsp:include page="../mainPage/header.jsp" flush="true" />

	<c:if test="${result }">
		<div class="logregHeader">
		<div class="logregTitle">새 비밀번호 입력</div>
	</div>
	
	<div class="logregFormView">
		<form id="formLogin" name="myform" class="searchPw simpleForm"
		action="/moahair/member/PwSearchUpdatePro.do" method="post"
		onSubmit="return checkItNewPwForm()">
			<input type="hidden" name="si_id" value="${m_id }"/>
			<ul class="borderRight">
				<li>
					<input type="password" id="si_pw" name="si_pw" placeholder="새 비밀번호 입력" title="새 비밀번호"/>					
				</li>
				<li>
					<input type="password" id="si_pw2" name="si_pw2" placeholder="새 비밀번호 재입력" title="새 비밀번호2"/>
				</li>
				<li class="marginTop15">
					<input type="submit" id="btnLogin" class="btnBlack" value="변경하기">
				</li>
			</ul>
		</form>
	</div>
	</c:if>
	
	<c:if test="${!result }">
		<script>
			alert("인증번호가 틀립니다.");
			location.href="/moahair/member/PwSearchCheck.do";
		</script>
	</c:if>
	
		<!-- footer -->
	
		<jsp:include page="../mainPage/footer.jsp" flush="true" />
</body>
</html>