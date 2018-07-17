<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<html>
<head>
<script type="text/javascript">
function itemHome(i_num){
	$.ajax({
	      type : "post" ,  url : "/moahair/product/item/ProductView.do",
	      data : {
	    	  i_num : i_num
	      },
	   	success : function(data){
	         $("#sellerContents").html(data);
	      }
	   });
}
function lastComment(pageNum){
	
	$.ajax({
	      type : "post" ,  url : "/moahair/seller/sellerQnaLastList.do",
	      data : {
	    	  pageNum : pageNum  ,
	    	  bs_num : '${ bs_num}'  
	      },
	   	success : function(data){
	         $("#sellerContents").html(data);
	      }
	   });
}
function newComment(){
	$.ajax({
	      url : "/moahair/seller/sellerQnaList.do",
	      data : {
	    	  bs_num : '${ bs_num}'   
	      },
	      success : function(data){
	         $("#sellerContents").html(data);
	      }
	   });
}
</script>
</head>
<body>
	

<c:if test="${count == 0 }">
	<form>
		<table style="margin:0 auto;">
			<tr>
				<td>새로운 댓글이 없습니다</td>
			</tr>
		</table>
	</form>
	</c:if>

	<c:if test="${count > 0 }">
	<form>
			<div class="userAddBtn">
				<input type="button" class="AddBtn" value="새로달린댓글보기" onclick="newComment()">
				<input type="button" class="AddBtn" value="예전댓글보기" onclick="lastComment()">
			</div>
		<table width="700" style="margin:0 auto;" class="table table-hover">
			<thead>
      <tr class="checkhv" style="text-align:center;">
        
         <th style="text-align:center;">번호</th>
         <th style="text-align:center;">상품명</th>
         <th style="text-align:center;">내용</th>
         <th style="text-align:center;">작성자</th>
         <th style="text-align:center; width:15px;" colspan="3" >등록일</th>
     
         
      </tr>
   </thead>
			
			
			
			<c:forEach var="article" items="${articleList}">
			
			<tr height="30">
				<td>
					${number}
					<c:set var="number" value="${ number-1 }" />
				</td>
				<td width="250">
					<c:forEach var="one" items="${itemNameMap}">
						<c:if test="${one.key == article.bd_i_num}"><a href="#" onclick="itemHome(${article.bd_i_num})">${one.value}</a></c:if>
					</c:forEach>
				</td>
				<td width="250"><a href="#" onclick="itemHome(${article.bd_i_num})">${article.bd_contents }</a></td>
				<td width="50">${article.bd_writer}</td>
				<td width="150">${article.bd_date}</td>
			</tr>
			
			</c:forEach>
			</table>
			<div>
			<c:if test="${count>0}">
				<c:set var="pageCount" value="${ count/pageSize + (count % pageSize == 0 ? 0 : 1) }"/>
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