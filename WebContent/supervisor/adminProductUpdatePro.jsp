<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
</head>
<body>
<c:if test="${m_level != 10 }">
		<script>
			alert("접근이 불가능한 페이지 입니다.");
			window.location = "/moahair/main/main.do";
		</script>
	</c:if>

	<script type="text/javascript">
		alert("상품정보가 수정되었습니다");
		window.location = "/moahair/supervisor/SupervisorMain.do?type=4";
	</script>
</body>
</html>