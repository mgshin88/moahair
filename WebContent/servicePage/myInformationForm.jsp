<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionScope.memId == null }">
		<script>
			alert("로그인 후 가능합니다.");
			window.location = "/moahair/member/loginForm.do";
		</script>
	</c:if>

<div class="logregHeader">
	<div class="logregTitle">내 정보 변경</div>
</div>

<div class="logregFormView">
<form class="simpleForm" action="/moahair/mypage/MemUpdate.do" method="post" name="userinput" 
onSubmit="return checkItmodify()">


	<ul class="borderRight">
		<li>
	<div class="myInfoId">${sessionScope.memId}</div>
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
		<input type="submit" class="btnBlack" value="수정" >
		<input type="button" class="btnWhite" value ="회원탈퇴" onclick="window.location='/moahair/mypage/MemDelete.do'">
	  </li>
</ul>
</form>
</div>
