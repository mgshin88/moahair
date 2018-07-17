<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>샵 정보관리 :: 폼</title>
</head>
<body>

<h1>샵 정보 수정</h1>
<form method="post" action="/moahair/seller/businessupdatepro.do" name="businessUpdateForm" id="businessUpdateForm" enctype="multipart/form-data" onsubmit="return writeSave()">
<input type="hidden" name="bs_num" value="${bs_num}"/>
<input type="hidden" name="ba_num" value="${addInfo.ba_num}"/>
<input type="hidden" name="orgProfileSys" value="${info.bs_profile_org}"/>
<input type="hidden" name="orgBackgroundSys" value="${info.bs_background_org}"/>

<table width="400" cellspacing="0" cellpadding="0" style="margin:0 auto;">
   <tr>
    <td align="right" colspan="2">
	    
   </td>
   </tr>
   <tr>
    <td  width="70" align="center">상 호</td>
    <td  width="330">
       ${info.bs_name}
  </tr>
  <tr>
    <td  width="70" align="center" >사업자번호</td>
    <td  width="330">
    
      ${info.bs_number }
 
  </tr>
 
     
     <tr>
			<td rowspan="2">샵 프로필사진</td>
			<td>현재이미지	<img src="/moahair/img/seller/business/${info.bs_profile}" width="50" /></td>
			
		</tr>
		<tr>
			<td>
				<input type="file" name="bs_profile"/>
				<input type="hidden" name="orgProfile" value="${info.bs_profile}" />
			</td>
		</tr>
     
     <tr>
			<td rowspan="2">배경 이미지</td>
			<td>현재이미지	<img src="/moahair/img/seller/business/${info.bs_background}" width="50" /></td>
			
		</tr>
		<tr>
			<td>
				<input type="file" name="bs_background" />
				<input type="hidden" name="orgBackground" value="${info.bs_background}" />
			</td>
		</tr>
  
   
<tr>
    <td  width="70" align="center">샵 오픈시간</td>
    <td  width="330">
       <select name="bs_open" id="bs_open">
       
       <c:forEach var="timetb" items="${timetable }">
       		<option value="${timetb.num}" ${timetb.num == info.bs_open ? 'selected' : ''}>${timetb.athirty   }</option>
       </c:forEach>
      </select>
  </tr>
    <tr>
    <td  width="70" align="center">샵 마감시간</td>
    <td  width="330">
    <select name="bs_close" id="bs_close">
    	<c:forEach var="timetb" items="${timetable }">
       		<option value="${timetb.num }" ${timetb.num == info.bs_close ? 'selected' : ''}>${timetb.athirty }</option>
       </c:forEach>
       </select>
  </tr>
  
     <tr>
    <td  width="70" align="center">샵 주소(도)</td>
    <td  width="330">
       <input type="text" size="40" name="ba_state"  value="${addInfo.ba_state}"></td>
  </tr>
    <td  width="70" align="center">샵 주소(시)</td>
    <td  width="330">
       <input type="text" size="40" name="ba_city"  value="${ addInfo.ba_city}"></td>
  </tr>
    <td  width="70" align="center">샵 주소(구)</td>
    <td  width="330">
       <input type="text" size="40" name="ba_surburb" value="${addInfo.ba_surburb }"></td>
  </tr>
    <td  width="70" align="center">샵 주소(동)</td>
    <td  width="330">
       <input type="text" size="40" name="ba_street" value="${addInfo.ba_street }" ></td>
  </tr>
    <td  width="70" align="center">샵 주소(나머지)</td>
    <td  width="330">
       <input type="text" size="40" name="ba_rest" value="${addInfo.ba_rest}"></td>
  </tr>
  
<tr>      
 <td colspan=2 align="center"> 
  <input type="reset" value="다시작성" class="AddBtn"/>
  <input type="submit" value="정보수정" class="AddBtn"/>  
 
</td></tr></table>    
   
</form>      

</body>
</html>