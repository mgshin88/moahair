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

<script>
$(function (){
	$("#bookedcheck0").prop("checked",true);
})

function bkSelectUpdate() {
	var bk_num = $(".booked:checked").val();
	
	window.location = "/moahair/booking/modifybooking.do?bk_num="+bk_num;
}

function bkSelectDelete() {
	if(confirm("정말로 삭제 하시겠습니까? (예약시간 3시간 이내 취소시 페널티가 부과됩니다.)")) {
		if($(".booked").is(":checked")) {
			var bk_num = $(".booked:checked").val();		
		
		
		$.ajax ({
		type : "post",
		url : "/moahair/mypage/BookingDelete.do",
		data : {bk_num : bk_num},
		success : function(data) {
			$("#result").html(data);
		}	
		})
		}
	}
}
</script>

<div class="logregHeader">
	<div class="logregTitle">예약확인</div>
</div>

<div class="booked_all">
	<ul id="myPageBooked" class="bookedGroup">
		<li class="headerInfo">
			<label class="bookedcell1"><!-- 라디오 버튼 --></label>
			<label class="bookedcell2">번호</label>
			<label class="bookedcell3">디자이너</label>
			<label class="bookedcell4">상품명</label>
			<label class="bookedcell5">예약날짜/시간</label>
			<label class="bookedcell6">가격</label>
			<label class="bookedcell7">기장</label>	
			<label class="bookedcell8">옵션1</label>
			<label class="bookedcell9">옵션2</label>
		</li>
		
			
		<c:forEach var = "booked" items="${booked }" varStatus="number">
		
		<li class="itemInfo">
			<div class="bookedcell1"><input type="radio" id="bookedcheck${number.index }" class="booked" value="${booked.bk_num }" name="booked"></div>
			<div class="bookedcell2">${number.count }</div>
			<div class="bookedcell3">${booked.bk_s_name }</div>
			<div class="bookedcell4">${booked.bk_i_name }</div>
			<div class="bookedcell5">${booked.bk_date } <br> ${athirtyTime[number.index] } (${bk_time[number.index]}분)</div>
			<div class="bookedcell6">
			<fmt:formatNumber value="${booked.bk_price }" pattern="#,###원"/>
			</div>
			<div class="bookedcell7">${booked.bk_i_option }</div>
			<div class="bookedcell8">${booked.bk_i_option_sel1 }</div>
			<div class="bookedcell9">${booked.bk_i_option_sel2 }</div>
			
		</li>
		</c:forEach>
	</ul>
	
	<div id="result"></div>
</div>

<input type="button" class="cartbtnBlack" value="예약수정" onclick="bkSelectUpdate()">
<input type="button" class="cartbtnWhite" value="선택삭제" onclick="bkSelectDelete()">
