<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>판매자 관리 :: 샵 선택 </title>
<link rel="stylesheet" type="text/css" href="/moahair/css/sellerAdmin.css">
<link rel="stylesheet" href="/moahair/css/master.css" type="text/css">
</head>
<body>
	<%---사용자 아이디에 등록된 샵이 하나일때 --%>
	
	
	<div id="sellerSelContents">
	
	<c:if test="${(fn:length(businesses)) >= 1}">
		<b>매장을 선택해주세요</b><br/>
		<c:forEach var="business" items="${businesses }">
			<c:set var="bs_num" value="${business.key }" />
			<a class="sellerName" href="/moahair/seller/sellerviewmain.do?bs_num=${bs_num}">${business.value}</a><br/>
		</c:forEach>
	</c:if>
	</div>
	 
</body>
</html>