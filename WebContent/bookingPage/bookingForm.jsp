<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="/moahair/js/jquery-3.1.1.js"></script>
  <link rel="stylesheet" href="/moahair/css/buttontd.css" type="text/css">

<style>
.table-wrapper {
    width: 600px; 
    overflow: auto;
   
}
</style>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자바스크립트 - 달력 만들기 예제</title>

<script type="text/javascript">
$(document).ready(function() {
	func();
})
	var str = "";
	function func() {
		var now = new Date(); 
		var year = now.getFullYear();
		var month = now.getMonth();
		var day = now.getDate();


	
		var today = new Date(year, month, day);
		var firstDay = today.getDate();
		var today_yoil = today.getDay();

		var nalsu = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);


		if (year % 4 === 0 % year % 100 !== 0 || year % 400 === 0) {
			nalsu[1] = 29;
		}

		makeCalendar(today_yoil, nalsu[month], year, month + 1, day)
		$("#disp").html(str);



	}
	
		
	function makeCalendar(yoil, nalsu, year, month, day) {
		var s_holiday = parseInt('${schedule.s_holiday}');

		str = "<table border=1 width=600 height=100 style='cursor:pointer'>";

		str += "<tr><th colspan='14' style='text-align:center'>" + year + "년" + month
				+ "월" + day + "일</th></tr>";
		str += "<tr style='text-align:center'>";
		var week = new Array("일", "월", "화", "수", "목", "금", "토");
		for (var i = 0; i < 14; i++) {
			if(s_holiday == ((yoil + i) % 7)){
				str += "<td>" + week[(yoil + i) % 7] + "</td>";
			}else{
				str += "<td>" + week[(yoil + i) % 7] + "</td>";
			}
		}
		str += "</tr>";
		
		
		var s_annualleave = '${schedule.s_annualleave}';
		arr1 = s_annualleave.split(" ");

		function sortDate(a,b){
			 var arr0 = a.split("/");
			 var arr1 = b.split("/");
			 var date_a = new Date(arr0[0],arr0[1]-1,arr0[2]);
			 var date_b = new Date(arr1[0],arr1[1]-1,arr1[2]);
			 if (date_a < date_b) return -1;
			 if (date_a > date_b) return 1;
			 return 0;
			}
		arr1.sort(sortDate);
		
		
		str += "<tr style='text-align:center'>";
		for (var i = 0; i < 14; i++) {
			if((day+i)>nalsu){
    	   	var today = year+"/"+(month+1)+"/"+(day + i - nalsu);
    	   	
    	   	for(p=0; p<arr1.length; p++){
    	   	 		 var arr2 = arr1[p].split("/");
					 var arr3 = today.split("/");
					 var date_a = new Date(arr2[0],arr2[1]-1,arr2[2]);
					 var date_b = new Date(arr3[0],arr3[1]-1,arr3[2]);
					 
				   	if(date_b > date_a) {
						arr1.splice(p, 1);
					}
    	 	  	} 		
    	   		if(s_holiday == ((yoil + i) % 7)){
					str+= "<td bgcolor='grey' onclick=alert('정기휴일입니다');>" + (day + i - nalsu) + "</td>";
    	   		}else{
    	   			if(arr1.length==0){str+= "<td onclick=test('"+today+"');>" + (day + i - nalsu) + "</td>";
    	   			}else{
    	   				for(var k=0; k < arr1.length; k++){
    	   					if(arr1[k]==today){
    	   						str+= "<td bgcolor='grey' onclick=alert('임시휴일입니다');>" + (day + i - nalsu) + "</td>";
    	   						arr1.splice(k, 1);
    	   						break;
    	   					}else{
    	   						str+= "<td onclick=test('"+today+"');>" + (day + i - nalsu) + "</td>";
    	   						break;
    	   					}
    	   				}
    	   			}
    	   		}
			}else{
				today = year+"/"+month+"/"+(day + i);
				
			 	for(p=0; p<arr1.length; p++){
	    	   		 var arr2 = arr1[p].split("/");
					 var arr3 = today.split("/");
					 var date_a = new Date(arr2[0],arr2[1]-1,arr2[2]);
					 var date_b = new Date(arr3[0],arr3[1]-1,arr3[2]);
					if(date_b > date_a) {
						arr1.splice(p, 1);
					}
	    	   	} 
			
				if(s_holiday == ((yoil + i) % 7)){
					str+= "<td bgcolor='grey' onclick=alert('정기휴일입니다');>" + (day + i) + "</td>";
    	   		}else{
    	   			if(arr1.length==0){str+= "<td onclick=test('"+today+"');>" + (day + i) + "</td>";
    	   			}else{
    	   				for(var k=0; k < arr1.length; k++){
    	   					if(arr1[k]==today){
    	   						str+= "<td bgcolor='grey' onclick=alert('임시휴일입니다');>" + (day + i) + "</td>";
    	   						arr1.splice(k, 1);
    	   						break;
    	   					}else{
    	   						str+= "<td onclick=test('"+today+"');>" + (day + i) + "</td>";
    	   						break;
    	   					}
    	   				}
    	   			}
    	   		}
			}
			
		}
		str += "</tr>";
		str += "</table>";
	}
	str2 = "";
	function test(today) {
		var query = {
			name8 : "${name}",
			today : today
		};
		$.ajax({
			type : "post",
			url : "/moahair/booking/booked.do",
			data : query,
			success : function(data) {
				bookingTime(today, data);
				document.getElementById("disp2").innerHTML = str2;
			}
		});
		
	}
	
	function bookingTime(today, data) {
		$("#disp4").html("");
		$("#disp5").html("");
		$("#disp6").html("");
		$("#disp7").html("");
		
		str2 = "";
		var s_timetable = '${timetable3}';
		arr2 = s_timetable.split(" ");

		arr3 = data.split(" ");
		

		var dr = '${dr}';


		var begining = parseInt('${begining}');
		var closing = parseInt('${closing}');	
		
		str2 += "<div class='table-wrapper'>";
		str2 += "<table style= 'cursor:pointer' width=600>";
		str2 += "<tr><th colspan='"+arr2.length+"' style='text-align:center'>" + today + "  예약 가능한 시간대</th></tr>";
		str2 += "<tr style='text-align:center'>";
		if (data == null || data == "") {//예약없음
			for (var i = 0; i < arr2.length; i++) {
				if (closing - (begining + i) >= dr) 
					str2 += "<td class='buttontd' onclick=pay('" + today + "','" + arr2[i] + "'); >" + arr2[i] + "</td>";
				
			}
		} else {//예약이 있음
			for (var i = 0; i < arr2.length; i++) {//영업시간
				if (arr3.length == 0) {
					if (closing - (begining + i) >= dr) 
						str2 += "<td  class='buttontd'  onclick=pay('" + today + "','" + arr2[i] + "'); >" + arr2[i] + "</td>";
					
				} else {
					for (var j = 0; j < arr3.length; j++) {
						if ((begining + i) == arr3[j]) {
							
							arr3.splice(j, 1);
							
							break;
						} else {
							if (arr3[j] - (begining + i) >= dr) {				
								str2 += "<td  class='buttontd'  onclick=pay('" + today + "','" + arr2[i] + "'); >" + arr2[i] + "</td>";
								break;
							} else {
								if (arr3.length > 1) {
									
									break;
								} else {
									if (closing - (begining + i) >= dr) 
										str2 += "<td class='buttontd' onclick=pay('" + today + "','" + arr2[i] + "'); >" + arr2[i] + "</td>";
										break;
								}
							}
						}
					}
				}
			}
		}
		str2 += "</tr>";
		str2 += "</table>";
		str2 += "<br/>";
		str2 += "<br/>";
		str2 += "</div>";
	}

	function pay(today, time) {
		$("#disp4").html("<h2>"+today + "  " + time +"</h2>");
		$("#disp5").html("<input type='hidden' name='bk_date' value='"+today+ "'>");
		$("#disp6").html("<input type='hidden' name='bk_time' value='"+time+ "'>");
		$("#disp7").html("<input type = 'submit' class='buttontd2' value='결제하기'>");

	}
</script>

</head>
<body>
<br>
	<br>
	<br>

	<div style="float:left; width:30%; margin-bottom: 20px;">
	<img src="/moahair/img/seller/staff/${schedule.s_profile}" width="150" height="100"/>
	</div>
	<div style="float:left; width:70%; margin-bottom: 20px;">
   	<b>디자이너  ${name}</b><br>
	<b>아이템 이름 : ${itemList.i_name}</b><br>
	<b>총소요시간 : ${du2} 소요예정</b><br>
	<b>총상품가격 : ${bk_price}원</b>  
	</div>
	
	<input type="button" class='buttontd2' value="디자이너 일정">
	<div id="disp">
	</div>
	<br>

	<div id="disp2"></div>
	
	<form action = "/moahair/booking/bank.do" method="post" id="disp3">
	
	
	<input type = 'hidden' name='bk_bs_num'  value='${itemList.i_bs_num}' >
	<input type = 'hidden' name='bk_s_num' value='${itemList.i_s_num}' >
	<input type='hidden' name='bk_s_name' value='${name}' />
	<input type = 'hidden' name='bk_i_num' value='${bk_i_num}' >
	<input type = 'hidden' name='bk_i_name' value='${itemList.i_name}' >
	<input type = 'hidden' name='bk_price' value='${bk_price}' >
	<input type = 'hidden' name='bk_i_option' value='${i_option}' >
	<input type = 'hidden' name='bk_i_option_sel1'  value='${i_option_sel1}' >
	<input type = 'hidden' name='bk_i_option_sel2'  value='${i_option_sel2}' >
	<input type = 'hidden' name='bk_i_duration' value='${dr}' >
	<div id="disp4"></div>
	<div id="disp5"></div>
	<div id="disp6"></div>
	<div id="disp7"></div>

	
	</form>

</body>
</html>