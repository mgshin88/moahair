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
	<div class="logregTitle">상품관리 페이지</div>
</div>

<script type="text/javascript">
function item_delete(i_num, bs_num) {
	$.ajax({
		type : "post" ,  url : "/moahair/seller/sellerproductdeleteform.do",
		data : {i_num : i_num, bs_num : bs_num},
		success : function(data){
			$("#view1").html(data);
		}
	});
}

function item_update(i_num, bs_num) {
	
	$.ajax({
		type : "post" ,  url : "/moahair/supervisor/AdminProductUpdateForm.do",
		data : {i_num : i_num, bs_num : bs_num, pageNum : '2'},
		success : function(data){
			$("#view1").html(data);
		}
	});
}

function item_search() {
	var item_search = $("#item_search").val();
	
	$.ajax({
		type : "post" ,  url : "/moahair/supervisor/ItemSearchResult.do",
		
		data : {item_search : item_search},
		success : function(data){
			$("#view1").html(data);
		}
	});
	
}

function AdminPageNumItem(pageNumber) {
	var pageNum = pageNumber;
	
	$.ajax({
		type : "post", url : "/moahair/supervisor/ItemList.do",
		data : { pageNum : pageNum },
		success : function(data) {
			$("#view1").html(data);
		}
	})
}

</script>

<table class="table table-hover" style="text-align:center;">
	<thead>
		<tr>
			<th style="text-align:center;">번호</th>
			<th style="text-align:center;">상품명</th>
			<th style="text-align:center;">상품가격</th>
			<th style="text-align:center;">샵이름</th>
			<th style="text-align:center;">상품 등록일자</th>
			<th style="text-align:center;">수정</th>
			<th style="text-align:center;">삭제</th>
			
		</tr>
	</thead>
	
	<c:forEach var ="list" items="${list }">
		<tr>
			<td>${list.i_num }</td>
			<td>${list.i_name }</td>
			<td><fmt:formatNumber value="${list.i_price }" pattern="#,###원"/></td>
			<td>${list.bs_name }</td>
			<td>${list.i_regdate }</td>
			<td>
				<button onclick="item_update('${list.i_num }', '${list.bs_num }')">아이템 수정</button><br/>
			</td>
			<td>
				<button onclick="item_delete('${list.i_num }', '${list.bs_num }')">아이템 삭제</button>
			</td>
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
   				<input class="pagePrev" type="button" value="이전" onclick="AdminPageNumItem(${startPage - 10})">
   			</c:if>
   			
   			<c:forEach var="i" begin="${startPage}" end="${endPage}">
       			<c:if test="${i == pageNum }">
       				<input class="pageButton addbackground" type="button" onclick="AdminPageNumItem(${i})" value="${i }">  
       			</c:if>
       			
       			<c:if test="${i != pageNum }">
       			<input class="pageButton" type="button" onclick="AdminPageNumItem(${i})" value="${i }">
       			</c:if>
   			</c:forEach>
   			
   			<c:if test="${endPage < pageCount}">
	   			<input class="pageNext" type="button" value="다음" onclick="AdminPageNumItem(${startPage + 10})">
   			</c:if>
	
   		</div>

   	</c:if>

<div class="searchBox">
	<input type="text" name="item_name" id ="item_search"/>
	<button onclick="item_search()">검색</button><br/>
</div>

