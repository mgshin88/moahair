<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/moahair/css/master.css" type="text/css">
<c:if test="${ItemList!=null }">
	<c:forEach var="i" items="${ItemList }">
		<ul>
			<li class="contentBox"><a
				href="/moahair/product/item/ProductView.do?i_num=${i.i_num }"><img
					src="/moahair/img/item/thumbnail/${i.i_thumbnail}"></a></li>
			<li class="textOverFlowHidden"><a
				href="/moahair/product/item/ProductView.do?i_num=${i.i_num }">${i.i_name}</a></li>
			<li class="priceOverFlowHidden">${i.i_price}&nbsp;<span>원</span></li>
			<li class="businessEmblem"><a
				href="/moahair/seller/SellerView.do?bs_num=${i.i_bs_num }"><img
					src="/moahair/img/seller/business/${i.bs_profile }"
					title=""><br>
					<p>&nbsp;${i.bs_name }&nbsp;</p></a></li>
		</ul>
	</c:forEach>
	
			<div id="list_add"></div>
	
</c:if>

<c:if test="${ItemList==null }">
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<h3 class="warnContentNull">검색 결과를 찾을 수 없습니다. 다시 검색해 주세요.</h3>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>

</c:if>

