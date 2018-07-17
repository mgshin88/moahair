<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:if test="${m_level != 10 }">
		<script>
			alert("접근이 불가능한 페이지 입니다.");
			window.location = "/moahair/main/main.do";
		</script>
	</c:if>

<div class="logregHeader">
	<div class="logregTitle">
		아이템별 판매현황 <br>
		( ${time1 }~${time2 } )
	</div>
	
</div>

<c:set var="sum" value="0"/>

<table class="table table-hover" style="text-align:center;">
	<thead>
		<tr>
			<th style="text-align:center;">순위</th>
			<th style="text-align:center;">상품명</th>
			<th style="text-align:center;">총매출</th>
		</tr>
	</thead>

<c:forEach var = "list" items="${item_total }" varStatus="number">
	<tr id="sellerRank${number.count }">
		<td>${number.count }</td>
		<td>${list.i_name }</td>
		<td><fmt:formatNumber value="${list.total }" pattern="#,###원"/></td>
	</tr>
<c:set var="sum" value="${sum+list.total }"/>
</c:forEach>
</table>
			
<div class="profitView">
	<span class="profitSpan"> 총 수익 : 
<fmt:formatNumber value="${sum }" pattern="#,###원"/> </span> <br/> 
	<span class="profitSpan"> 
	모아헤어 수익 : <fmt:formatNumber value="${sum * 0.03 }" pattern="#,###원"/>
	 </span>
</div>


