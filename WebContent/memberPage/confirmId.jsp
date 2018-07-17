<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import ="moahair.kyu.dao.KyuDAO" %>

<html>
<head><title>ID 중복확인</title>

<script src="/moahair/js/regForm.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<body>

<c:if test="${check }">
<table width="270" border="0" cellspacing="0" cellpadding="5">
  <tr> 
    <td height="39" >${rg_id }이미 사용중인 아이디입니다.</td>
  </tr>
</table>
<form name="checkForm" method="post" action="/moahair/member/ConfirmId.do">
<table width="270" border="0" cellspacing="0" cellpadding="5">
  <tr>
    <td align="center">
       다른 아이디를 선택하세요.<p>
       <input type="text" size="10" maxlength="12" name="id"> 
       <input type="submit" value="ID중복확인">
    </td>
  </tr>
</table>
</form>
</c:if>
<c:if test="${!check }">

<table width="270" border="0" cellspacing="0" cellpadding="5">
  <tr> 
    <td align="center"> 
      <p>입력하신 ${rg_id } 는 사용하실 수 있는 ID입니다. </p>
      <input type="button" value="닫기" onclick="setid()">
    </td>
  </tr>
</table>
</c:if>
</body>
</html>
<script language="javascript">
<!--
  function setid()
    {		
		opener.document.userinput.idDuplication.value = "idCheck";
		opener.document.userinput.rg_id.value="${rg_id}";
		self.close();
		}
		-->
</script>
