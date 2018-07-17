<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
	var result = ${result};
	if(result == 1) {
		alert("이미 장바구니에 담긴 상품입니다.");
	} else {
		alert("장바구니에 담겼습니다.");
	}
</script>