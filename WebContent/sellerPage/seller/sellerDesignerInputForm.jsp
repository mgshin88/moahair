<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<script type="text/javascript">
function goDesignerList(pageNum){
	$.ajax({
	      type : "post" ,  url : "/moahair/seller/sellerDesignerList.do",
	    	 data : {
	    		 pageNum:pageNum,
		    	  bs_num : '${ bs_num}',
		    	  s_num : '${s_num}'
		      },
	      success : function(data){ 
	         $("#sellerContents").html(data);
	      }
	   });
}
</script>
</head>
<body>
	<h2>디자이너 등록</h2>
	
	<form action="/moahair/seller/sellerDesignerInputPro.do" method="post" id="schedule" enctype="multipart/form-data" name="schedule" onsubmit="return writeDesignerSave()">
  <input type="hidden" name="bs_num" value="${bs_num}"/>
	<table style="margin: auto">
	<tr>
	<td>디자이너이름</td><td><input type="text" name="s_name"></td>
	</tr>
	<tr>
	<td>직함</td><td><input type="text" name="s_title"></td>
	</tr>
	<tr>
	<td>프로필사진</td><td><input type="file" name="s_profile" /></td>
	</tr>
	
	<tr>
	<td>배경사진</td><td><input type="file" name="s_background" /></td>
	</tr>
	<tr>
	<td>정기휴일</td><td><select name="s_holiday">
		<option value="0">일요일</option>
		<option value="1">월요일</option>
		<option value="2">화요일</option>
		<option value="3">수요일</option>
		<option value="4">목요일</option>
		<option value="5">금요일</option>
		<option value="6">토요일</option>
	</select></td>
	</tr>
	<tr>
	<td>출근시간</td><td><select name="s_open">
	
	<c:forEach var="time" items="${timetable}" >
		<option value="${time.num}">${time.athirty}</option>
	</c:forEach>
	</select></td>
	</tr>
	<tr>
	<td>퇴근시간</td><td><select name="s_close">
	<c:forEach var="time" items="${timetable}" >
		<option value="${time.num}">${time.athirty}</option> 
	</c:forEach>
	</select></td>
	</tr>
	<tr>
	<td colspan="2">
	<input type="submit" class="AddBtn" value="등록" />
	<input type="button" class="AddBtn" value="목록" onclick="goDesignerList(${pageNum})" />
	<input type="reset" class="AddBtn" value="취소"></td>
	</tr>
	</table>
	
	
	
	
</form>
	
</body>
</html>