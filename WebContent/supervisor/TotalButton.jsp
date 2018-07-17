<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${m_level != 10 }">
		<script>
			alert("접근이 불가능한 페이지 입니다.");
			window.location = "/moahair/main/main.do";
		</script>
	</c:if>

<script>
function seller_total() {
	
	var time1 = $("#time1").val();
	var time2 = $("#time2").val();
	
	if(time1 == ""){
		alert("시작일을 입력해주세요");
		return false;
	}else if(time2 == ""){
		alert("종료일을 입력해주세요");
		return false;
	}
	
	$.ajax({
		type : "post" ,  url : "/moahair/supervisor/SellertotalList.do",
		data : {time1 : time1, time2 : time2},
		success : function(data){
			$("#total_list").html(data);
		}
	});
	
}


function item_total() {
	
	var time1 = $("#time1").val();
	var time2 = $("#time2").val();
	
	if(time1 == ""){
		alert("시작일을 입력해주세요");
		return false;
	}else if(time2 == ""){
		alert("종료일을 입력해주세요");
		return false;
	}
	
	$.ajax({
		type : "post" ,  url : "/moahair/supervisor/Itemtotallist.do",
		data : {time1 : time1, time2 : time2},
		success : function(data){
			$("#total_list").html(data);
		}
	});
	
}

</script>

<div class="logregHeader">
	<div class="logregTitle">판매 수익</div>
</div>

<div class="totalInfo">
	<div class="totalday">
		시작 : <input id ="time1" type="date" name ="time1">
	</div>
	
	<div class="totalday">
		종료 : <input id ="time2" type="date" name ="time2">
	</div>
</div>

<div class="totalButton">
	<button class="sellertotalbtn" onclick="seller_total()">판매자별 판매현황</button>
	<button class="sellertotalbtn" onclick="item_total()">아이템별 판매현황</button>
</div>

<div id ="total_list"></div>

</body>
</html>