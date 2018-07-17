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
<script src="/moahair/js/jquery-ui.js"></script>
<script src="/moahair/js/regForm.js"></script>
</head>
<body>
<c:if test="${m_level != 10 }">
		<script>
			alert("접근이 불가능한 페이지 입니다.");
			window.location = "/moahair/main/main.do";
		</script>
	</c:if>

	<div class="logregHeader">
		<div class="logregTitle">사용자 추가</div>
	</div>
		<div class="logregFormView">
		<form id="formReg" class="regForm simpleForm" action="/moahair/supervisor/userInputPro.do" method="post" name="userinput"
		onSubmit="return checkIt()">
		<ul class="borderRight">
			<li>
			<input type="hidden" name="pageNum" value="${pageNum }">
			<input type="text" id="rg_id" name="rg_id"
				maxlength="24" placeholder="아이디 입력" onkeydown="inputIdChk()"> 
			
			<input type="button" id="rg_confirm" class="btnWhite" name="confirm_id" value="ID중복확인"
				onclick="openConfirmid(this.form)"> 
			<input type="hidden" id="idDuplication" name="idDuplication" value="idUncheck">
			</li>
			
		<li>
			<input type="password" id="rg_pw" name="rg_pw"
				maxlength="12" placeholder="비밀번호 입력">
		</li>
		<li>
			<input type="password" id="rg_pw2" name="rg_pw2"
				maxlength="12" placeholder="비밀번호 재 입력">
		</li>
		<li>
			<input type="text" id="m_name" name="m_name"
				maxlength="10" placeholder="사용자 이름 입력">
		</li>
		<li>
			<input type="text" id="m_address" name="m_address" 
			maxlength="50" placeholder="주소 입력">
		</li>
		<li>
			<input type="text" id="m_phone" name="m_phone" 
			maxlength="15" placeholder="핸드폰 번호 입력">
		</li>
		
		<li>
			<input type="text" id="m_email" name="m_email" 
			maxlength="40" placeholder="이메일 주소 입력">
		</li>
		
		<li>
			<input type="submit" id="btnReg" class="btnBlack" name="confirm" value="등   록"> 
			<input type="reset" name="reset" class="btnWhite" value="다시입력">
		</li>
		</ul>
		</form>
		</div>
		
</body>
</html>
