<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<c:if test="${m_level != 10 }">
		<script>
			alert("접근이 불가능한 페이지 입니다.");
			window.location = "/moahair/main/main.do";
		</script>
	</c:if>

<div class="logregHeader">
	<div class="logregTitle">판매자관리</div>
</div>

<script>

function AdminPageNumMember(pageNumber) {
	var pageNum = pageNumber;
	
	$.ajax({
		type : "post", url : "/moahair/supervisor/Seller.do",
		data : { pageNum : pageNum },
		success : function(data) {
			$("#view1").html(data);
		}
	})
}
	
function sellersearch(pageNumber) {
	var pageNum = pageNumber;
	$.ajax({
		type : "post" ,  url : "/moahair/supervisor/sellerSearch.do",
		data : {bs_name : $("#bs_name").val(), pageNum : pageNum},
		success : function(data){
			$("#view1").html(data);
			$("#bs_name").val("");
			$("#bs_name").focus();
		}
	});
	
}
$('#user_id').keypress(function(event){
    if ( event.which == 13 ) {
    	usersearch();
        return false;
    }
});


function shopDelete(bs_num){
	$.ajax({
		type : "post" ,  url : "/moahair/supervisor/sellerDelete.do?bs_num="+bs_num,
		success : function(data){
			alert("삭제 되었습니다..!!");
			$.ajax({
				type : "post" ,  url : "/moahair/supervisor/Seller.do",
				success : function(data){
					$("#view1").html(data);
				}
			});
		}
	});
}

function shopUpdate(bs_num, ba_num) {
	
		$.ajax({
			type : "post",	url : "/moahair/supervisor/sellerUpdateForm.do?bs_num=" + bs_num,
			success : function(data) {
				$("#view1").html(data);
				
			}
		});

	}
</script>



<table class="table table-hover" style="text-align:center;">
	<thead>
		<tr>
			<th class="checkhv" style="text-align:center;"></th>
			<th style="text-align:center;">no.</th>
			<th style="text-align:center;">상호명</th>
			<th style="text-align:center;">컨디션</th>
			<th style="text-align:center;">판매자번호</th>
			<th style="text-align:center;">오픈시간</th>
			<th style="text-align:center;">마감시간</th>
			<th style="text-align:center;">주소</th>
			<th style="text-align:center;">수정</th>
			<th style="text-align:center;">삭제</th>
			
		</tr>
	</thead>
	
	<c:forEach var ="list" items="${list }" varStatus="number">
		<tr>
			<td></td>
			<td>${list.bs_num}</td>
			<td>${list.bs_name}</td>
			<td>
				<c:if test ="${list.bs_condition == 1 }">활성상태</c:if>
				<c:if test ="${list.bs_condition == 0 }">비활성상태</c:if>
			</td>
			<td>${list.bs_number}</td>
			<td>${opening[number.index]}</td>
			<td>${closing[number.index]}</td>
			<td>${list.ba_state } ${list.ba_city } ${list.ba_surburb } ${list.ba_street } ${list.ba_rest }</td>			
			<td><input type="button" value="수정" onclick="shopUpdate('${list.bs_num} ')"/></td>
			<td><input type="button" value="삭제"  onclick="shopDelete('${list.bs_num} ')"/></td>
			
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
		<input type="text" name ="bs_name" id="bs_name" />
		<input type="button" value ="검색" onclick ="sellersearch(${pageNum})"/> 
		</div>