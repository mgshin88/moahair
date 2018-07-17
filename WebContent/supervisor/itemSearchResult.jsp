<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${m_level != 10 }">
		<script>
			alert("접근이 불가능한 페이지 입니다.");
			window.location = "/moahair/main/main.do";
		</script>
	</c:if>
<c:forEach var="list" items="${item_search }">
상품 이름 : ${list.i_name }<br/>

<button onclick="item_delete('${list.i_num }', '${list.bs_num }')">아이템 삭제</button>
<button onclick="item_update('${list.i_num }', '${list.bs_num }')">아이템 수정</button><br/>

</c:forEach>



</body>
</html>