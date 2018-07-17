<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/moahair/css/jquery-ui.css">
<link rel="stylesheet" type="text/css"
	href="/moahair/css/fontawesome_463.css">
<link rel="stylesheet" type="text/css" href="/moahair/css/kyuCSS.css">
<link rel="stylesheet" href="/moahair/css/master.css" type="text/css">
<script src="/moahair/js/jquery-ui.js"></script>
<script src="/moahair/js/regForm.js"></script>

<c:if test="${m_level != 10 }">
		<script>
			alert("접근이 불가능한 페이지 입니다.");
			window.location = "/moahair/main/main.do";
		</script>
	</c:if>

<div class="logregHeader">
	<div class="logregTitle">회원 수정</div>
</div>

<div class="logregFormView">
<form class="simpleForm" action="/moahair/supervisor/userUpdatePro.do" method="post" name="userinput" 
onSubmit="return checkItmodify()">

	<ul class="borderRight">
		<li>
	<div class="myInfoId">${dto.m_id }</div>
	<input type="hidden" id="m_id" name="m_id" value="${dto.m_id }">
		</li>
	<li>
         <input type="password" id="m_pw" name="m_pw" size="15"
            maxlength="12" placeholder="비밀번호 입력">
      </li>
     <li>
         <input type="password" id="m_pw2" name="m_pw2" size="15"
            maxlength="12" placeholder="비밀번호 재 입력">
      </li>
      <li>
         <input type="text" id="m_name" name="m_name" size="15"
            maxlength="10" value="${dto.m_name }" placeholder="사용자 이름 입력">
      </li>
      <li>
         <input type="text" id="m_address" name="m_address" size="60" maxlength="50" value="${dto.m_address }" placeholder="주소 입력">
      </li>
      <li>
         <input type="text" id="m_phone" name="m_phone" size="20" value="${dto.m_phone }" placeholder="핸드폰 번호 입력">
      </li>
      <li>
      
      <div class="radio">
      <label for="con1">
      <input type="radio" id="con1" name="m_condition" value="1"
      <c:if test="${dto.m_condition == 1 }">
      	checked
      </c:if>
      />
             활성화 (일반회원전환)
      </label>
      
      </div>
      
      <div class="radio">
      <label for="con0">   
   	        <input type="radio" id="con0" name="m_condition" value="0"
      <c:if test="${dto.m_condition == 0 }">
      	 checked
      </c:if>
      />
   	     비활성화 (회원탈퇴)
      </label>
      
      </div>
   
      </li>
      <li>
		<input type="submit" class="btnBlack" value="수정" >
		<input type="button" class="btnWhite" value ="회원탈퇴" onclick="window.location='/moahair/supervisor/userDeletePro.do'">
	  </li>
</ul>
</form>
</div>
