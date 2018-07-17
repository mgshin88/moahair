<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="view point" content="width=device-width,initial-scale=1.0">
<script src="/moahair/js/jquery-3.1.1.js"></script>

<meta charset="UTF-8">
<title>디자이너 일정 입력</title>

<script type="text/javascript">

window.onload = function() {
	document.getElementById("btnShow").addEventListener("click", func, false);
	document.getElementById("btnShow2").addEventListener("click", func2, false);
	document.getElementById("btnShow3").addEventListener("click", func4, false);

}
function func2(){
	document.getElementById("disp").innerHTML = "";
	document.getElementById("disp2").innerHTML = "";
}


function func() {
	var now = new Date();
	var year = now.getFullYear();
	var month = now.getMonth();
	var day = now.getDate();
	
	var today = new Date(year, month, day);
	var firstDay = today.getDate();
	var today_yoil = today.getDay();

	
	var setDate = new Date(year, month, 1);
	var firstDay = setDate.getDate();
	var yoil = setDate.getDay();
	
	var setDate2 = new Date(year, month+1, 1);
	var firstDay2= setDate2.getDate();
	var yoil2 = setDate2.getDay();
	
	
	var nalsu = new Array(31,28,31,30,31,30,31,31,30,31,30,31);
	
	if(year % 4 === 0 % year % 100 !== 0 || year % 400 === 0 ){
		nalsu[1] = 29;
	}
	
	makeCalendar(yoil, nalsu[month],year,month+1, day)
	document.getElementById("disp").innerHTML = str;
	makeCalendar2(yoil2, nalsu[month+1],year,month+2, day)
	document.getElementById("disp2").innerHTML = str2;
	
	
}
var str= "";
function makeCalendar(yoil, nalsu, year, month, day) {
	str = "<table border ='0'>";
	str += "<tr><th colspan='7' width='400'>" + year + "년" + month + "월</th></tr>";
	str += "<tr>";
	var week = new Array("일", "월", "화", "수", "목", "금", "토");
	for(var i = 0; i < week.length; i++){
		str += "<th>" + week[i] + "</th>";
	}
	str += "</tr>";
	
	var no = 1;
	var currentCell = 0;
	var ju = Math.ceil((nalsu + yoil) / 7);
	for(var r=0; r < ju; r++){
		str += "<tr style='text-align:center'>";
		for(var col=0; col < 7; col++){
			if(currentCell < yoil || no > nalsu){
				str += "<td>&nbsp;</td>";
				currentCell++;
			}else{
				day = year+"/"+month+"/"+no;
				str +="<td onclick=func3('"+day+"');>" + no + "</td>";
				no++;
			}
		}
		str += "<td>&nbsp;</td>";
		str += "</tr>";
	}
	str += "</table>";
}
var str2= "";
function makeCalendar2(yoil, nalsu, year, month, day) {
	str2 = "<table border ='0'>";
	str2 += "<tr><th colspan='7' width='400'>" + year + "년" + month + "월</th></tr>";
	str2 += "<tr>";
	var week = new Array("일", "월", "화", "수", "목", "금", "토");
	for(var i = 0; i < week.length; i++){
		str2 += "<th>" + week[i] + "</th>";
	}
	str2 += "</tr>";
	
	var no = 1;
	var currentCell = 0;
	var ju = Math.ceil((nalsu + yoil) / 7);
	
	for(var r=0; r < ju; r++){
		str2 += "<tr style='text-align:center'>";
		for(var col=0; col < 7; col++){
			if(currentCell < yoil || no > nalsu){
				str2 += "<td>&nbsp;</td>";
				currentCell++;
			}else{
				day = year+"/"+month+"/"+no;
				str2 += "<td onclick=func3('"+day+"'); >" + no + "</td>";
				no++;
			}
		}
		str2 += "<td>&nbsp;</td>";
		str2 += "</tr>";
	}
	str2 += "</table>";
}

var daylist = new Array();
function func3(no) {
	var result = 0;
	for(i = 0 ; i < daylist.length ; i++){
		n = daylist[i];
		if(n == no){
			$(event.target).css("background-color","white");
			daylist.splice(i, 1);
			result = 1;
	
		}
	}
	if(result == 0){
		daylist.push(no);
		$(event.target).css("background-color","red");	
	}
}
	
function func4(){
	for(var i=0; i<daylist.length; i++){
		alert(daylist[i]);
	$("#schedule").append("<input type='hidden' name='s_annualleave' value='"+daylist[i]+ "'>");
	}
}


</script>

<h2>디자이너 일정 입력</h2>

<form action="/moahair/booking/schedulePro.do" method="post" id="schedule" enctype="multipart/form-data">
  
	디자이너이름<input type="text" name="s_name"><br />
	직함<input type="text" name="s_title"><br/>
	프로필사진<input type="file" name="s_profile" /><br />
	배경사진<input type="file" name="s_background" /><br />
	 정기휴일<select name="s_holiday">
		<option value="0">일요일</option>
		<option value="1">월요일</option>
		<option value="2">화요일</option>
		<option value="3">수요일</option>
		<option value="4">목요일</option>
		<option value="5">금요일</option>
		<option value="6">토요일</option>
	</select><br /> 
	
	임시휴일<input type="button" value="달력보기" id="btnShow">
	<input type="button" value="달력숨기기" id="btnShow2">
	<input type="button" value="휴일 최종입력" id="btnShow3">
	<div id="disp"></div>
	<div id="disp2"></div>
	 <br /> 
	영업시작시간<select name="s_open">
	<c:forEach var="time" items="${timetable}" >
		<option value="${time.num}">${time.athirty}</option>
	</c:forEach>
	</select>
	 영업종료시간<select name="s_close">
	<c:forEach var="time" items="${timetable}" >
		<option value="${time.num}">${time.athirty}</option>
	</c:forEach>
	</select>
	<br />
	<input type="submit" value="transfer">
	<input type="reset" value="취소">
</form>
