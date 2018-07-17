<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${check }"> 
		<script>
			window.location = "/moahair/main/main.do";
		</script>
	</c:when>
	
	<c:when test="${!check }">
		<script>
			alert("입력한 정보가 맞지 않습니다.");
			history.go(-1);
		</script>
	</c:when>
</c:choose>