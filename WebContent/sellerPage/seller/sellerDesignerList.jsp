<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<html>
<head>
<link rel="stylesheet" type="text/css" href="/moahair/css/admin.css">
</head>
<body>
<script type="text/javascript">
function inputStaff(pageNum){
	$.ajax({
	      type : "post" ,  url : "/moahair/seller/sellerDesignerInputForm.do",
	      data : {
	    	  pageNum : pageNum  ,
	    	  bs_num : '${ bs_num}'   
	      },
	      success : function(data){
	         $("#sellerContents").html(data);
	      }
	   });
}

function updateStaff(s_num, pageNum){
	$.ajax({
	      type : "post" ,  url : "/moahair/seller/sellerDesignerUpdateForm.do",
	      data : {
	    	  s_num : s_num,
	    	  pageNum : pageNum  ,
	    	  bs_num : '${ bs_num}'   
	      },
	      success : function(data){
	         $("#sellerContents").html(data);
	      }
	   });
}

function movePage(pageNum){
	   $.ajax({
	      type : "post" ,  url : "/moahair/seller/sellerDesignerList.do",
	      data : {
	    	  pageNum : pageNum  ,
	    	  bs_num : '${ bs_num}'   
	      },
	      success : function(data){
	         $("#sellerContents").html(data);
	      }
	   });
}

function deleteStaff(s_num, pageNum){
	if(confirm("정말 삭제하시겠습니까?")==true){
		$.ajax({
		      type : "post" ,  url : "/moahair/seller/sellerDesignerDeleteForm.do?s_num="+s_num+"&pageNum="+pageNum,
		      success : function(data){
		    	  $.ajax({
				      url : "/moahair/seller/sellerDesignerList.do",
				      data : {
				    	  s_num : s_num,
				    	  bs_num : '${ bs_num}'   
				      },
				      success : function(data){
				         $("#sellerContents").html(data);
				      }
				   });
		      }
		   });
	}
}

function scheduleStaff(s_num, pageNum){
	$.ajax({
	      type : "post" ,  url : "/moahair/booking/scheduleForm.do",
	      data : {
	    	  s_num : s_num,
	    	  pageNum : pageNum  ,
	    	  bs_num : '${ bs_num}'   
	      },
	      success : function(data){
	         $("#sellerContents").html(data);
	      }
	   });
}
</script>

	<c:if test="${countStaff == 0 }">
	
	<form>
		<table style="margin:0 auto;">
			<tr>
				<td>디자이너를 등록해주세요!</td>
			</tr>
			<tr>
				<td><input type="button" class="AddBtn" value="디자이너등록" onclick="inputStaff()"></td>
			</tr>
		</table>
	</form>
	
	</c:if>
	<c:if test="${countStaff > 0 }">
	
	<form>
			<div class="userAddBtn" >	
			<input class="AddBtn" type="button" value="디자이너등록" onclick="inputStaff()">
			</div>
			<br/>
		<table width="700" class="table table-hover" style="margin:0 auto;" >
			
			<thead>
      <tr>
         <th class="checkhv" style="text-align:center;"></th>
         <th style="text-align:center;">프로필사진</th>
         <th style="text-align:center;">이름</th>
         <th style="text-align:center;">직함</th>
         <th style="text-align:center;">정기휴무</th>
         <th style="text-align:center; width:15px;" colspan="3" >관리</th>
     
         
      </tr>
   </thead>
			<c:forEach var="staff" items="${staffList}">
			<tr height="30">
			<td></td>
				<td ><img src="/moahair/img/seller/staff/${staff.s_profile}" width="100"></td>
				<td >${staff.s_name}</td>
				<td >${staff.s_title}</td>
				<td >
				<c:if test="${staff.s_holiday == 0}">일</c:if>
				<c:if test="${staff.s_holiday == 1}">월</c:if>
				<c:if test="${staff.s_holiday == 2}">화</c:if>
				<c:if test="${staff.s_holiday == 3}">수</c:if>
				<c:if test="${staff.s_holiday == 4}">목</c:if>
				<c:if test="${staff.s_holiday == 5}">금</c:if>
				<c:if test="${staff.s_holiday == 6}">토</c:if>
				</td>
       			<td style="width:1px;">				
					<input type="button" class="AddBtn" value="삭제" onclick="deleteStaff(${staff.s_num}, ${ pageNum })">	
				</td>
				<td style="width:1px;">
					<input type="button" class="AddBtn" value="수정" onclick="updateStaff(${staff.s_num}, ${ pageNum })">	
       			</td>
				<td style="width:1px;">				
					<input type="button" class="AddBtn" value="일정입력" onclick="scheduleStaff(${staff.s_num}, ${ pageNum })">	
				</td>
			</tr>
			</c:forEach>
			
			</table>
			<div>
			<c:if test="${countStaff>0}">
				<c:set var="pageCount" value="${ countStaff/pageSize + (countStaff % pageSize == 0 ? 0 : 1) }"/>
				<c:set var="startPage" value="${0 }"/>
				<c:set var="pageBlock" value="${10 }"/>
				
				<c:if test="${(currentPage % 10)==0 }">
					<fmt:parseNumber var="result" value="${ currentPage / 10}" integerOnly="true" />
					<c:set var="startPage" value="${(result -1)* 10 + 1 }" />
				</c:if>
				
				<c:if test="${ (currentPage % 10)!=0}">
					<fmt:parseNumber var="result" value="${currentPage /10 }" integerOnly="true"/>
 					<c:set var="startPage" value="${result * 10 + 1 }" />
				</c:if>	
   
		
				<c:set var="endPage" value="${ startPage + pageBlock - 1 }" />
		
				<c:if test="${endPage > pageCount }">
					<c:set var="endPage" value="${pageCount }" />
				</c:if>
				
				<c:if test="${ startPage > 10}">
					<input type="button" class="AddBtn" value="이전" onclick="movePage(${startPage - 10})" />
					
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1" >
					<input type="button" class="AddBtn" value="${i }" onclick="movePage(${i})" />
				</c:forEach>
				<c:if test="${endPage < pageCount }">
					<input type="button" class="AddBtn" value="다음" onclick="movePage(${startPage + 10})" />
					
				</c:if>
			</c:if>
			</div>
	
	</form>
	
	</c:if>
</body>
</html>