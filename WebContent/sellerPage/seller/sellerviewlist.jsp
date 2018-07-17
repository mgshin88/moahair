<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
</head>
<body>
<script type="text/javascript">
function movePage(pageNum){
	   $.ajax({
	      type : "post" ,  url : "/moahair/seller/sellerviewlist.do",
	      data : {
	    	  pageNum : pageNum  ,
	    	  bs_num : '${ bs_num}'   
	      },
	      success : function(data){
	         $("#sellerContents").html(data);
	      }
	   });
}
function inputItem(pageNum){
		   $.ajax({
		      type : "post" ,  url : "/moahair/seller/sellerproductaddform.do",
		      data : {
		    	  pageNum : pageNum  ,
		    	  bs_num : '${ bs_num}'   
		      },
		      success : function(data){
		         $("#sellerContents").html(data);
		      }
		   });
	}		

function updateItem(i_num, pageNum){
	   $.ajax({
	      type : "post" ,  url : "/moahair/seller/sellerproductupdateform.do",
	      data : {
	    	  bs_num : '${ bs_num}',
	    	  i_num : i_num,
	    	  pageNum : pageNum
	      },
	   	success : function(data){
	         $("#sellerContents").html(data);
	      }
	   });
}	
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
function deginerHome(s_num){
	   $.ajax({
	      type : "post" ,  url : "/moahair/seller/DesignerView.do",
	      data : {
	    	  s_num : s_num
	      },
	   	success : function(data){
	         $("#sellerContents").html(data);
	      }
	   });
}	
	
//판매자 상품리스트에서 글삭제 버튼 누를 시 작동 함수
function deleteItem(i_num, pageNum){
	if(confirm("정말 삭제하시겠습니까?")==true){
		$.ajax({
		      type : "post" ,  url : "/moahair/seller/sellerproductdeleteform.do?i_num="+i_num+"&pageNum="+pageNum,
		      success : function(data){
		    	  $.ajax({
				      url : "/moahair/seller/sellerviewlist.do",
				      data : {
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
function itemSearch(pageNum){
	 $.ajax({
		 type : "post" ,
	      url : "/moahair/seller/itemSearch.do",
	      data : {
	    	  bs_num : '${ bs_num}',
	    	  itemSearch : $("#itemSearch").val(),
	    	  pageNum : pageNum
	      },
	      success : function(data){
	         $("#sellerContents").html(data);
	         $("#itemSearch").val("");
	      }
	   });
}
function goItemList(){
	window.location="/moahair/seller/sellerviewmain.do?bs_num=${bs_num}&type=2";
}

</script>

	<c:if test="${count == 0 }">
	<form>
		<table style="margin:0 auto;">
			<tr>
				<td>상품을 등록해주세요!</td>
			</tr>
			<tr>
				<td><input type="button" class="AddBtn" value="상품등록" onclick="inputItem()"></td>
			</tr>
		</table>
	</form>
	</c:if>
	
	<c:if test="${searchCount == 0 }">
	<form>
		<table style="margin:0 auto;">
			<tr>
				<td>검색결과가 없습니다</td>
			</tr>
			<tr>
				<td><input type="button" class="AddBtn" value="돌아가기" onclick="goItemList()"></td>
			</tr>
		</table>
	</form>
	</c:if>

	<c:if test="${count > 0 || searchCount >0}">
	<c:if test="${searchCount >0}">
	<input type="button" class="AddBtn" value="돌아가기" onclick="goItemList()">
	</c:if>
				<input type="button" class="AddBtn" value="상품등록" onclick="inputItem()"><br/>
		<table width="700" style="margin:0 auto;" class="table table-hover">
	<thead>
      <tr>
         <th class="checkhv" style="text-align:center;"></th>
         <th style="text-align:center;">번호</th>
         <th style="text-align:center;">상품사진</th>
         <th style="text-align:center;">상품명</th>
         <th style="text-align:center;">판매가(원)</th>
         <th style="text-align:center;">디자이너</th>
         <th style="text-align:center;">등록일</th>
         <th style="text-align:center; width:15px;" colspan="2" >관리</th>
     
         
      </tr>
   </thead>
   
			
			<c:forEach var="item" items="${itemList}">
			<tr height="30">
			<td></td>
				<td>
					${number}
					<c:set var="number" value="${ number-1 }" />
				</td>
				<td width="250"><a href="#" onclick="itemHome(${item.i_num})"><img src="/moahair/img/item/thumbnail/${item.i_thumbnail}" width="100"></a></td>
				<td width="250"><a href="#" onclick="itemHome(${item.i_num})">${item.i_name}</a></td>
				<td width="50">${item.i_price}</td>
					<td width="100">
						<c:if test="${item.i_s_num == -1}">
							<c:forEach var="staff" items="${staffList}"><a href="#" onclick="deginerHome(${staff.s_num})">${staff.s_name} </a></c:forEach>
						</c:if>
						<c:if test="${item.i_s_num > 0}">
							<c:forEach var="staff" items="${staffList}">
								<c:if test="${item.i_s_num eq staff.s_num}"><a href="#" onclick="deginerHome(${staff.s_num})">${staff.s_name} </a></c:if>
							</c:forEach>
						</c:if>
				</td>
				<td width="150">${item.i_regdate}</td>
				<td>
					<input type="button" value="수정" class="AddBtn" onclick="updateItem(${item.i_num}, ${ pageNum })">	
       			</td>
       			<td>				
					<input type="button" value="삭제"class="AddBtn"  onclick="deleteItem(${item.i_num}, ${ pageNum })">	
				</td>
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
					<input type="button" value="이전" class="AddBtn" onclick="movePage(${startPage - 10})" />
					
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1" >
					<input type="button" value="${i }" class="AddBtn" onclick="movePage(${i})" />
				</c:forEach>
				<c:if test="${endPage < pageCount }">
					<input type="button" value="다음" class="AddBtn" onclick="movePage(${startPage + 10})" />
					
				</c:if>
			</c:if>
			</div>
			<br/>
			
				<input type="text" id="itemSearch" name="itemSearch" />
				<input type="button" class="AddBtn" id="itemSearchBtn" value="검색" onclick="itemSearch('${pageNum}')"/>
			
	
	</c:if>
	
	
	
</body>
</html>