<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="../mainPage/header.jsp" flush="false"/>


<c:forEach var="qdto" items='${dto }'>
제목 : ${qdto.bd_subject}<br/>
작성자 : ${qdto.bd_writer}<br/>
내용 : ${qdto.bd_contents}<br/>
작성 날짜 : ${qdto.bd_date}<br/>
</c:forEach>

<input type="button" value = "답글쓰기">
<input type="button" value = "수정하기">
<input type="button" value = "삭제하기">


	<jsp:include page="../mainPage/footer.jsp" flush="false"/>


</body>
</html>