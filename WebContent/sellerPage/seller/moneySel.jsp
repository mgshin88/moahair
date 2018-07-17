<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>

</head>
<body>
	
	
	
	<c:if test="${(fn:length(businesses)) >= 1}">
	<div class="moneyRst" >
		<dl>
			<dt>전체 매장 수익</dt>
			<dd><fmt:formatNumber value="${allmoney}" pattern="#,###" />원</dd>
		</dl>
		<dl>
			<dt>매장별 수익</dt>
			<c:if test ="${allmoney != 0}">
		
			<c:forEach var="business" items="${businesses}">
				<dd>
				${business.value}
					<c:forEach var="money" items="${todayMoney}">
						<c:if test="${business.key == money.key}">
						<fmt:formatNumber value="${money.value}" pattern="#,###" />
							<c:set var="tmp" value="${money.value}"></c:set>
						</c:if>
					</c:forEach>
				원       ${tmp/allmoney*100}%
				</dd>
			</c:forEach>
			</c:if>
			
			<c:if test ="${allmoney == 0}">
		
			<c:forEach var="business" items="${businesses}">
				<dd>
				${business.value}
					<c:forEach var="money" items="${todayMoney}">
						<c:if test="${business.key == money.key}">
						<fmt:formatNumber value="${money.value}" pattern="#,###" />
							<c:set var="tmp" value="${money.value}"></c:set>
						</c:if>
					</c:forEach>
				원       ${0*100}%
				</dd>
			</c:forEach>
	
		</c:if>
			
		</dl>
	</div>

	
	</c:if>

</body>
</html>