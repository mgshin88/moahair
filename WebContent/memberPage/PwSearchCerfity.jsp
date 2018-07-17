<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${result }">
<script>
	alert("입력하신 메일 주소로 인증 메일을 보냈습니다.");
	$("#si_cerfity").prop('disabled', false);
</script>
</c:if>

<c:if test="${!result  }">
<script>
	alert("입력하신 정보를 찾을 수 없습니다.");
</script>
</c:if>