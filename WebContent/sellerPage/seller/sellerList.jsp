<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<html>
<head>
</head>
<body>
	<c:if test="${(fn:length(businesses)) >= 1}">
		<c:forEach var="business" items="${businesses }">
			<c:set var="bs_num" value="${business.key }" />
			<a class="sellerName" href="/moahair/seller/sellerviewmain.do?bs_num=${bs_num}">${business.value}</a><br/>
		</c:forEach>
	</c:if>
</body>
</html>