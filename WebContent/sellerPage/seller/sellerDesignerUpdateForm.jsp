<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
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
<h2>디자이너 정보 수정</h2>
	<form action="/moahair/seller/sellerDesignerUpdatePro.do" method="post" id="schedule" enctype="multipart/form-data">
  <input type="hidden" name="s_num" value="${s_num }"	/>
  <input type="hidden" name="bs_num" value="${bs_num}"/>
  <table style="margin: auto">
  <tr>
	<td>디자이너이름</td><td><input type="text" name="s_name" value="${dto.s_name }"></td>
	</tr>
	<tr>
	<td>직함</td><td><input type="text" name="s_title" value="${dto.s_title }"></td>
	</tr>
	<tr>
	<td rowspan="2">
	프로필사진
	</td>
	<td>현재이미지	<img src="/moahair/img/seller/staff/${dto.s_profile}" width="50" /></td>
	</tr>
	<tr>
	<input type="hidden" name="orgProSys" value="${dto.s_profile}" />
	<input type="hidden" name="orgProOrg" value="${dto.s_profile_org}" />
	<td><input type="file" name="s_profile" /></td>
	</tr>
	<tr>
	<td rowspan="2">
	배경사진</td>
	<td>현재이미지 <img src="/moahair/img/seller/staff/${dto.s_background}" width="50" /></td>
	</tr>
	<tr>
	<input type="hidden" name="orgBgSys" value="${dto.s_background}" />
	<input type="hidden" name="orgBgOrg" value="${dto.s_background_org}" />
	<td><input type="file" name="s_background" /></td>
	</tr>
	<tr>
	<td>
	 정기휴일</td><td><select name="s_holiday">
		<option value="0" ${dto.s_holiday == 0 ? 'selected' : ''}>일요일</option>
		<option value="1" ${dto.s_holiday == 1 ? 'selected' : ''}>월요일</option>
		<option value="2" ${dto.s_holiday == 2 ? 'selected' : ''}>화요일</option>
		<option value="3" ${dto.s_holiday == 3 ? 'selected' : ''}>수요일</option>
		<option value="4" ${dto.s_holiday == 4 ? 'selected' : ''}>목요일</option>
		<option value="5" ${dto.s_holiday == 5 ? 'selected' : ''}>금요일</option>
		<option value="6" ${dto.s_holiday == 6 ? 'selected' : ''}>토요일</option>
	</select></td></tr>
	
	<tr>
	<td>출근시간</td><td><select name="s_open">
	<c:forEach var="time" items="${timetable}">
		<option value="${time.num }" ${dto.s_open eq time.num ? 'selected' : ''}>${time.athirty }</option>
	</c:forEach>
	
	</select></td>
	</tr>
	<tr>
	<td>
	퇴근시간</td><td><select name="s_close">
	<c:forEach var="time" items="${timetable }">
		<option value="${time.num }" ${dto.s_close eq time.num ? 'selected' : ''}>${time.athirty }</option>
	</c:forEach>
	
	</select>
	</td>
	</tr>
	<tr>
	<td colspan="3">
	<input type="submit" class="AddBtn" value="등록">
	<input type="button" class="AddBtn" value="목록" onclick="goDesignerList(${pageNum})" />
	<input type="reset" class="AddBtn" value="취소">
	</td>
	</tr>
	</table>
</form>
</body>
</html>