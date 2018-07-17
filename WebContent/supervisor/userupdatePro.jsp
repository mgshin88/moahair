<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${m_level != 10 }">
		<script>
			alert("접근이 불가능한 페이지 입니다.");
			window.location = "/moahair/main/main.do";
		</script>
	</c:if>


<!-- type값을 1로 리턴(사용자 페이지 리스트 보여줄 수 있게) -->
<script>
function calls() {
	alert("수정 완료");
	
	var userSubmit = document.getElementById("typeSubmit");
	userSubmit.submit();
}

</script>

<body onload="calls()">
<form id="typeSubmit" method="post" action="/moahair/supervisor/SupervisorMain.do">
	<input type="hidden" id="type" name="type" value="2">
</form>
</body> 