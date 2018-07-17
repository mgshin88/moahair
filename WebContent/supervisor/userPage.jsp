<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:if test="${m_level != 10 }">
		<script>
			alert("접근이 불가능한 페이지 입니다.");
			window.location = "/moahair/main/main.do";
		</script>
	</c:if>

<div class="logregHeader">
	<div class="logregTitle">사용자 페이지</div>
</div>
<script type="text/javascript">
// 사용자 검색 버튼 클릭 시
function usersearch(pageNumber) {
	var pageNum = pageNumber;
	$.ajax({
		type : "post" ,  url : "/moahair/supervisor/userSearch.do",
		data : {user_id : $("#user_id").val(), pageNum : pageNum },
		success : function(data){
			$("#view1").html(data);
			$("#user_id").val("");
			$("#user_id").focus();
		}
	});
	
}

// 엔터키로도 가능
$('#user_id').keypress(function(event){
    if ( event.which == 13 ) {
    	usersearch();
        return false;
    }
});

function userInputForm(pageNumber) {
	var pageNum = pageNumber;
	$.ajax({
		type : "post", url : "/moahair/supervisor/userInputForm.do",
		data : {pageNum : pageNum},
		success : function(data) {
			$("#view1").html(data);
		}
	});
}

function userUpdateForm(m_idx, pageNumber) {
	var m_id = m_idx;
	var pageNum = pageNumber;
	
	$.ajax({
		type : "post", url : "/moahair/supervisor/userUpdateForm.do",
		data : {m_id : m_id, pageNum : pageNum},
		success : function(data) {
			$("#view1").html(data);
		}
	})
}

function AdminPageNumMember(pageNumber) {
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

<div class="userAddBtn">
<input type="button" value ="사용자 추가" class="AddBtn" onclick="userInputForm(${pageNum})"/>

<!-- 
<div  style="width:500px; float:left ; "  id="user" >
	
</div>

<div style="width:500px; float:left ;" id ="user_content"></div>
-->
</div>

<table class="table table-hover" style="text-align:center;">
	<thead>
		<tr>
			<th style="text-align:center;">번호</th>
			<th style="text-align:center;">아이디</th>
			<th style="text-align:center;">이름</th>
			<th style="text-align:center;">등급</th>
			<th style="text-align:center;">활성상태</th>
			<th style="text-align:center;">가입일자</th>
		</tr>
	</thead>
	
	<c:forEach var ="list" items="${list }">
		<tr onclick="userUpdateForm('${list.m_id}', ${pageNum })" style="cursor:pointer;">
			<td>${list.m_num }</td>
			<td>${list.m_id }</td>
			<td>${list.m_name }</td>
			<td>
				<c:if test ="${list.m_level == 3}"> 일반 </c:if>
				<c:if test ="${list.m_level == 6}"> 판매자 </c:if>
				<c:if test ="${list.m_level == 10}"> 관리자 </c:if>
			</td>
			<td>
				<c:if test ="${list.m_condition == 1 }">활성상태</c:if>
				<c:if test ="${list.m_condition == 0 }">비활성상태</c:if>
			</td>
			<td>${list.m_regdate }</td>
		</tr>
	</c:forEach>
		
	</table>
	
	<c:if test="${count > 0}">
   		<c:set var="pageCount" value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}"/>
   		<c:set var="pageBlock" value="${10}"/>
   		<fmt:parseNumber var="result" value="${currentPage / 10}" integerOnly="true" />
   		<c:if test="${currentPage % 10 == 0 }">
   			<c:set var="startPage" value="${(result-1) * 10 + 1}" />	
   		</c:if>
   		
   		<c:if test="${currentPage % 10 != 0 }">
   			<c:set var="startPage" value="${result * 10 + 1}" />	
   		</c:if>
   		<c:set var="endPage" value="${startPage + pageBlock-1}"/>
   		<c:if test="${endPage > pageCount}">
        	<c:set var="endPage" value="${pageCount}"/>
   		</c:if> 
          
   		
		<div id="board_list">
   			<c:if test="${startPage > 10}">
   				<input class="pagePrev" type="button" value="이전" onclick="AdminPageNumMember(${startPage - 10})">
   			</c:if>
   			
   			<c:forEach var="i" begin="${startPage}" end="${endPage}">
       			<c:if test="${i == pageNum }">
       				<input class="pageButton addbackground" type="button" onclick="AdminPageNumMember(${i})" value="${i }">  
       			</c:if>
       			
       			<c:if test="${i != pageNum }">
       			<input class="pageButton" type="button" onclick="AdminPageNumMember(${i})" value="${i }">
       			</c:if>
   			</c:forEach>
   			
   			<c:if test="${endPage < pageCount}">
	   			<input class="pageNext" type="button" value="다음" onclick="AdminPageNumMember(${startPage + 10})">
   			</c:if>
	
   		</div>

   	</c:if>
	
	<div class="searchBox">
		<input type="text" name ="user_id" id="user_id" />
		<input type="button" value ="검색" onclick ="usersearch(${pageNum})"/> 
	</div>
	
</body>
</html>