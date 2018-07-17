<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>샵 정보관리 :: 폼</title>
</head>
<body>
<c:if test="${m_level != 10 }">
		<script>
			alert("접근이 불가능한 페이지 입니다.");
			window.location = "/moahair/main/main.do";
		</script>
	</c:if>

<div class="logregHeader">
	<div class="logregTitle">샵 정보 수정</div>
</div>

<form method="post" action="/moahair/supervisor/sellerUpdatePro.do" name="businessUpdateForm" id="businessUpdateForm" enctype="multipart/form-data" onsubmit="return writeSave()">
<input type="hidden" name="bs_num" value="${dto.bs_num}"/>
<input type="hidden" name="ba_num" value="${dto.ba_num}"/>
<input type="hidden" name="orgProfileSys" value="${dto.bs_profile_org}"/>
<input type="hidden" name="orgBackgroundSys" value="${dto.bs_background_org}"/>

<table width="400" border="1" cellspacing="0" cellpadding="0"   align="center" style = "margin:auto";>
   <tr>
    <td align="right" colspan="2">
	    
   </td>
   </tr>
   <tr>
    <td  width="70" align="center">상 호</td>
    <td  width="330">
      <input type="text" value="${dto.bs_name}" name="bs_name"/>
  </tr>
  <tr>
    <td  width="70" align="center" >사업자번호</td>
    <td  width="330">
    
     <input type="text" value="${dto.bs_number }" name="bs_number"/>
 
  </tr>
 
     
     <tr>
			<td rowspan="2">샵 프로필사진</td>
			<td>현재이미지	<img src="/moahair/sellerPage/se_editor/upload/${dto.bs_profile}" width="50" /></td>
			
		</tr>
		<tr>
			<td>
				<input type="file" name="bs_profile" />
				<input type="hidden" name="orgProfile" value="${dto.bs_profile}" />
			</td>
		</tr>
     
     <tr>
			<td rowspan="2">배경 이미지</td>
			<td>현재이미지	<img src="/moahair/sellerPage/se_editor/upload/${dto.bs_background}" width="50" /></td>
			
		</tr>
		<tr>
			<td>
				<input type="file" name="bs_background" />
				<input type="hidden" name="orgBackground" value="${dto.bs_background}" />
			</td>
		</tr>
  
   
<tr>
    <td  width="70" align="center">샵 오픈시간</td>
    <td  width="330">
       <select name="bs_open" id="bs_open">
       
       <c:forEach var="timetb" items="${timetable }">
       		<option value="${timetb.num}" ${timetb.num == dto.bs_open ? 'selected' : ''}>${timetb.athirty   }</option>
       </c:forEach>
      </select>
  </tr>
    <tr>
    <td  width="70" align="center">샵 마감시간</td>
    <td  width="330">
    <select name="bs_close" id="bs_close">
    	<c:forEach var="timetb" items="${timetable }">
       		<option value="${timetb.num }" ${timetb.num == dto.bs_close ? 'selected' : ''}>${timetb.athirty }</option>
       </c:forEach>
       </select>
  </tr>
  
     <tr>
    <td  width="70" align="center">샵 주소(도)</td>
    <td  width="330">
       <input type="text" size="40" name="ba_state"  value="${dto.ba_state}"></td>
  </tr>
    <td  width="70" align="center">샵 주소(시)</td>
    <td  width="330">
       <input type="text" size="40" name="ba_city"  value="${ dto.ba_city}"></td>
  </tr>
    <td  width="70" align="center">샵 주소(구)</td>
    <td  width="330">
       <input type="text" size="40" name="ba_surburb" value="${dto.ba_surburb }"></td>
  </tr>
    <td  width="70" align="center">샵 주소(동)</td>
    <td  width="330">
       <input type="text" size="40" name="ba_street" value="${dto.ba_street }" ></td>
  </tr>
    <td  width="70" align="center">샵 주소(나머지)</td>
    <td  width="330">
       <input type="text" size="40" name="ba_rest" value="${dto.ba_rest}"></td>
  </tr>
  <tr>
  <td>샵 상태</td>
  <c:if test="${dto.bs_condition == 1  }">
  <td><input type="radio" value="1" checked="checked" name="shop_condition"/>활성화
  <input type="radio" value="0" name="shop_condition"/>비활성화</td>
  </c:if>
  <c:if test="${dto.bs_condition == 0  }">
  <td><input type="radio" value="1" name="shop_condition"/>활성화
  <input type="radio" value="0" checked="checked" name="shop_condition"/>비활성화</td>
  </c:if>
  
  </tr>
<tr>      
 <td colspan=2 align="center"> 
  <input type="reset" value="다시작성" />
  <input type="submit" value="정보수정" />  
 
</td></tr></table>    
   
</form>      

</body>
</html>