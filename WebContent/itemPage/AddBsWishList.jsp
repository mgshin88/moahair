<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
	var result = ${result};
	if(result == 1) {
		alert("이미 찜목록에 추가 되어 있습니다.");
	} else {
		alert("찜목록에 추가 되었습니다.");
	}
</script>