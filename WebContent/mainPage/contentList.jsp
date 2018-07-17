<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<c:forEach var="i" items="${ItemList }">
			<ul class = "contentList">
				<li class="contentBox"><a
					href="/moahair/product/item/ProductView.do?i_num=${i.i_num }"><img
						src="/moahair/img/item/thumbnail/${i.i_thumbnail}"></a></li>
				<li class="textOverFlowHidden"><a
					href="/moahair/product/item/ProductView.do?i_num=${i.i_num }">${i.i_name}</a></li>
				<li class="priceOverFlowHidden"><span>$</span>&nbsp;${i.i_price}</li>
				<li class="businessEmblem"><a
					href="/moahair/seller/SellerView.do?bs_num=${i.i_bs_num }"><img
						src="/moahair/img/seller/business/thumbnail/${i.bs_profile }"
						title=""><br>
						<p>&nbsp;${i.bs_name }&nbsp;</p></a></li>
			</ul>
		</c:forEach>

		<div id="list_add"></div>



</body>
</html>