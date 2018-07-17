<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:if test="${sessionScope.memId==null}">
<script type="text/javascript">
alert("로그인후 사용해주세요.");
window.location="/moahair/main/main.do";
</script>
</c:if>


<div class="logregHeader">
	<div class="logregTitle">구매내역</div>
</div>

<div class="purchased_all">
	<ul id="myPagepurchased" class="purchasedGroup">
		<li class="headerInfo">
			<label class="purchasedcell1">번호</label>
			<label class="purchasedcell2">디자이너</label>
			<label class="purchasedcell3">상품명</label>
			<label class="purchasedcell4">가격</label>
			<label class="purchasedcell5">기장</label>	
			<label class="purchasedcell6">옵션1</label>
			<label class="purchasedcell7">옵션2</label>
			<label class="purchasedcell8">예약날짜/시간</label>
		</li>
		
			
		<c:forEach var="purchased" items="${purchased }" varStatus="number">
		
		<li class="itemInfo">
			<div class="purchasedcell1">${number.count }</div>
			<div class="purchasedcell2">${purchased.bk_s_name }</div>
			<div class="purchasedcell3">${purchased.bk_i_name }</div>
			<div class="purchasedcell4">${purchased.bk_price }</div>
			<div class="purchasedcell5">${purchased.bk_i_option } </div>
			<div class="purchasedcell6">
				${purchased.bk_i_option_sel1 }
			</div>
			<div class="purchasedcell7">${purchased.bk_i_option_sel2 }</div>
			<div class="purchasedcell8">${purchased.bk_date }<br>
			${athirtyTime[number.index] } (${bk_time[number.index]}분)
			</div>
			
		</li>
		</c:forEach>
	</ul>
	
	<div id="result"></div>
</div>





</body>
</html>