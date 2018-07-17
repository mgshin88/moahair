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
<link rel="stylesheet" type="text/css" href="/moahair/css/kyuCSS.css?ver=3">
<link rel="stylesheet" href="/moahair/css/master.css" type="text/css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="/moahair/js/jquery-ui.js"></script>
<script src="/moahair/js/regForm.js?ver=1"></script>
<script src="/moahair/js/modalView.js"></script>

</head>
<body>
	<!-- header -->
	<jsp:include page="../mainPage/header.jsp" flush="true" />
	
	<div class="logregHeader">
		<div class="logregTitle">아이디 찾기</div>
	</div>
	
	<div class="logregFormView">
		<form action="/moahair/member/idSearchPro.do" id="formLogin" name="myform" class="searchId simpleForm"
		 method="post" onSubmit="return checkItIdSearch()">
			<ul class="borderRight">
				<li>
					<input type="text" id="si_name" name="si_name" placeholder="이름 입력" title="이름 입력"/>					
				</li>
				<li>
					<input type="text" id="si_email" class="inputText" name="si_email" placeholder="이메일 주소 입력" title="이메일 주소 입력"/>
				
					<input type="button" id="search_sub" class="btnWhite" value="인증" 
					onclick="return checkCerfity()">
					<div id="emailResult"></div>
				</li>
				<li>
					<p>가입 후 입력하신 이름과 이메일 주소를 정확하게 입력 하셔야 인증이 가능합니다.</p>
				</li>
				<li>
					<input type="text" id="si_cerfity" name="si_cerfity" placeholder="인증번호를 입력해주세요" title="인증번호 입력"
					disabled />		
				</li>
				<li class="marginTop15">
					<input type="submit" id="btnLogin" class="btnBlack" value="인증확인">
				</li>
				
			</ul>
		</form>
	</div>
	
	<!-- footer -->
	
		<jsp:include page="../mainPage/footer.jsp" flush="true" />
	
</body>
</html>