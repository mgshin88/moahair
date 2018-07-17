<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${m_level != 10 }">
		<script>
			alert("접근이 불가능한 페이지 입니다.");
			window.location = "/moahair/main/main.do";
		</script>
	</c:if>

<div class="logregHeader">
	<div class="logregTitle">사용자 페이지</div>
</div>

<script>
function userList(pageNumber) {
	var pageNum = pageNumber;
	$.ajax({
		type : "post", url : "/moahair/supervisor/userPage.do",
		data : { pageNum : pageNum },
		success : function(data) {
			$("#view1").html(data);
		}
	})
}
</script>

<c:if test="${dto == null }">
<script type="text/javascript">

alert("등록되지 않은 아이디입니다.");

$.ajax({
	type : "post", url : "/moahair/supervisor/userPage.do",
	data : { pageNum : '${pageNum}' },
	success : function(data) {
		$("#view1").html(data);
	}
})


</script>
</c:if>
<c:if test="${dto != null }">
<div class="userAddBtn">
<input type="button" value ="리스트 보기" class="AddBtn" onclick="userList(${pageNum})"/>

<!-- 
<div  style="width:500px; float:left ; "  id="user" >
	
</div>

<div style="width:500px; float:left ;" id ="user_content"></div>
-->
</div>

<table class="table table-hover" style="text-align:center;">
	<thead>
		<tr>
			<th class="checkhv" style="text-align:center;"></th>
			<th style="text-align:center;">번호</th>
			<th style="text-align:center;">아이디</th>
			<th style="text-align:center;">이름</th>
			<th style="text-align:center;">등급</th>
			<th style="text-align:center;">활성상태</th>
			<th style="text-align:center;">가입일자</th>
		</tr>
	</thead>
	
		<tr onclick="userUpdateForm('${dto.m_id}', ${pageNum })" style="cursor:pointer;">
			<td></td>
			<td>${dto.m_num }</td>
			<td>${dto.m_id }</td>
			<td>${dto.m_name }</td>
			<td>
				<c:if test ="${dto.m_level == 3}"> 일반 </c:if>
				<c:if test ="${dto.m_level == 6}"> 판매자 </c:if>
				<c:if test ="${dto.m_level == 10}"> 관리자 </c:if>
			</td>
			<td>
				<c:if test ="${dto.m_condition == 1 }">활성상태</c:if>
				<c:if test ="${dto.m_condition == 0 }">비활성상태</c:if>
			</td>
			<td>${dto.m_regdate }</td>
		</tr>
		
	</table>


</c:if>
	<div class="searchBox">
		<input type="text" name ="user_id" id="user_id" />
		<input type="button" value ="검색" onclick ="usersearch(${pageNum})"/> 
	</div>

</body>
</html>