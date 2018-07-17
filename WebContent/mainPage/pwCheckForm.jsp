<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="/moahair/css/kyuCSS.css">
<link rel="stylesheet" href="/moahair/css/master.css" type="text/css">

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script>
  
   
	function pwcheck() {
		$.ajax({
					type : "post",
					url : "/moahair/member/PwCheck.do",
					data : {
						m_pw : $("#m_pw").val()
					},
					success : function(data) {
						var t = data.trim();
						if (t == 'a') {
							$.ajax({
		            			type : "post",
		            			url : "/moahair/member/MyInformationForm.do",
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
   
</script>
<body>
	<jsp:include page="../mainPage/header.jsp" flush="false" />

<c:if test="${sessionScope.memId == null }">
		<script>
			alert("로그인 후 가능합니다.");
			window.location = "/moahair/member/loginForm.do";
		</script>
	</c:if>

<div class="logregHeader">
	<div class="logregTitle">비밀번호를 입력해 주세요</div>
</div>

<div style="text-align:center;">
비밀번호 : <input type="password" id="m_pw" name="m_pw" onkeydown="test1();" /> 
<input type="button" value = "확인" onclick="pwcheck()"  />


</div>

	<jsp:include page="../mainPage/footer.jsp" flush="false" />

</body>
</html>