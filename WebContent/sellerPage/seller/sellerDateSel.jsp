<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<html>
<head>
<script type="text/javascript">

function getSettleResult(){
var startDate = $('#startDate').val();
var endDate = $('#endDate').val();
if(startDate == "" || endDate == ""){
	alert("조회할 날짜를 입력해주세요!");
}else{
	$.ajax({
		url : "/moahair/seller/moneySel.do",
		data : {
			startDate : startDate,
			endDate : endDate
		},
		success : function(data) {
			$("#accountResult").html(data);
		}
	});
}
}
function getSettleBusinessResult(){

	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	if(startDate == "" || endDate == ""){
		alert("조회할 날짜를 입력해주세요!");
	}else{
		$.ajax({
			url : "/moahair/seller/moneyBusiness.do",
			data : {
				bs_num : '${bs_num}',
				startDate : startDate,
				endDate : endDate
			},
			success : function(data) {
				$("#accountResult").html(data);
			}
		});
	}
}

</script>
</head>
<body>
		
		<input id="startDate" name="startDate" type="date" value="${yesterday }" max="${yesterday}"/> -
		<input id="endDate" name="endDate" type="date" value="${yesterday }" max="${yesterday}"/><br/><br/>
		<input type="button" class="AddBtn" onclick="getSettleResult()" value="전체 매장 조회하기"/>	<input type="button" class="AddBtn" onclick="getSettleBusinessResult()" value="내 매장 조회하기"/><br/>

	<br/>
	<div id="accountResult"></div>
</body>
</html>