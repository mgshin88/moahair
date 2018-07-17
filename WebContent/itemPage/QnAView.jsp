<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>

function QnAwrite(){	
	var sessionId = "${sessionScope.memId}";
	if(!sessionId) {
		alert("로그인 후 이용 가능합니다.");
		window.location = "/moahair/member/loginForm.do";
	}
	
	var bs_number = ${bs_num};
	var i_number = ${i_num};
	$.ajax({
		type : "post", url : "/moahair/product/item/QnAInput.do",
		data : { bs_num : bs_number, i_num : i_number },
		success : function(data) {
			$("#view").html(data);
		}
	})
}


function QnAupdate(bd_num, count){
	var sessionId = "${sessionScope.memId}";
	if(!sessionId) {
		alert("로그인 후 이용 가능합니다.");
		window.location = "/moahair/member/loginForm.do";
	}
	
	var bs_number = ${bs_num};
	var i_number = ${i_num};
	$.ajax({
		type : "post", url : "/moahair/product/item/QnAUpdate.do",
		data : { bs_num : bs_number, i_num : i_number, bd_num : bd_num, count : count },
		success : function(data) {
			$("#QnAUpdate"+count).html(data);
		}
	})
}
function QnAdelete(bd_num){
	var sessionId = "${sessionScope.memId}";
	if(!sessionId) {
		alert("로그인 후 이용 가능합니다.");
		window.location = "/moahair/member/loginForm.do";
	}
	
	if(confirm("정말로 삭제 하시겠습니까?")) {
	var bs_number = '${bs_num}';
	var i_number = '${i_num}';
	$.ajax({
		type : "post" ,  url : "/moahair/product/item/QnADelete.do",
		data : { bs_num : bs_number, i_num : i_number, bd_num : bd_num },
		success : function(data){
			$("#view").html(data);
		}
	});
	}
}

function QnARefly(bd_num, bd_ref, count){
	var sessionId = "${sessionScope.memId}";
	if(!sessionId) {
		alert("로그인 후 이용 가능합니다.");
		window.location = "/moahair/member/loginForm.do";
	}
	
	var bs_number = '${bs_num}';
	var i_number = '${i_num}';
	$.ajax({
		type : "post" ,  url : "/moahair/product/item/QnAReply.do",
		data : { bs_num : bs_number, i_num : i_number, bd_num : bd_num, bd_ref : bd_ref, count : count },
		success : function(data){
			$("#QnAReply"+count).html(data);
		}
	});
}

function pageNumberQnA(pageNumber) {
	var bs_number = ${bs_num};
	var i_number = ${i_num};
	var pageNum = pageNumber;
	
	$.ajax({
		type : "post", url : "/moahair/product/item/QnAList.do",
		data : { bs_num : bs_number, i_num : i_number, pageNum : pageNum},
		success : function(data) {
			$("#contentQnADetail").html(data);
		}
	})
}

</script>

 <input class="pageInsertButton" type="button" value = "QnA작성" onclick="QnAwrite()"/><br/><br/><br/>

<div id = "view"></div>
 
 

<div id="itemQnA" class="ProductViewContent">
	
	<c:if test="${count == 0 }">
 					작성된 QnA가 없습니다.
 				</c:if>

	<c:if test="${count > 0 }">

		<c:forEach var="list" items='${b_list }' varStatus="qnalist">
			<c:if test="${list.bd_re_level != 1 }">
			
			<ul id="QnAsubject${qnalist.count }" class="pointerCursor">
				
				<li>
					<i class="fa fa-user" aria-hidden="true">
					</i>
					<span class="QnAwriterName"> ${list.bd_writer } </span>
					<span class="QnAregDate">${list.bd_date} </span>
				</li>
				<li id="QnAcontents">
					${list.bd_contents }
					
					<c:if test="${check && list.bd_ref_count == 0}">
						<div class="QnAbutton">
						<input class="pageOtherButton" type="button" onclick="QnARefly(${list.bd_num} , ${list.bd_ref}, ${qnalist.count } )" value="답변등록" />
						</div>
					</c:if>
					<c:if test="${list.bd_writer  == sessionScope.memId }">
					<div class="QnAbutton">
						<input class="pageOtherButton" type="button" onclick="QnAupdate( ${list.bd_num}, ${qnalist.count } )" value="수정" />
						<input class="pageOtherButton" type="button" onclick="QnAdelete( ${list.bd_num} )" value="삭제" />
					</div>
					</c:if>
				
			<div id="QnAUpdate${qnalist.count }"></div>
			<div id="QnAReply${qnalist.count }"></div>	
				</li>
			
			</ul>
			</c:if>
			
			<c:if test="${list.bd_re_level == 1 }">
				<ul id="QnAsubject${qnalist.count }" class="answer pointerCursor">
				
				<li>
					<i class="fa fa-user" aria-hidden="true">
					</i>
					<span class="QnAwriterName"> ${list.bd_writer } </span>
					<span class="QnAregDate">${list.bd_date} </span>
				</li>
				<li id="QnAcontents">
					${list.bd_contents }
					
					<c:if test="${list.bd_writer  == sessionScope.memId }">
					<div class="QnAbutton">
						<input class="pageOtherButton" type="button" onclick="QnAupdate( ${list.bd_num}, ${qnalist.count } )" value="수정" />
						<input class="pageOtherButton" type="button" onclick="QnAdelete( ${list.bd_num} )" value="삭제" />
					</div>
					</c:if>
					
			<div id="QnAUpdate${qnalist.count }"></div>
					<div id="QnAReply${qnalist.count }"></div>
				</li>
			
			
			</ul>
			
			</c:if>
		</c:forEach>
		<c:if test="${count > 0}">
   <c:set var="pageCount" value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}"/>
   <c:set var="pageBlock" value="${10}"/>
   <fmt:parseNumber var="result" value="${currentPage / 10}" integerOnly="true" />
   <c:set var="startPage" value="${result * 10 + 1}" />
   <c:set var="endPage" value="${startPage + pageBlock-1}"/>
   <c:if test="${endPage > pageCount}">
        <c:set var="endPage" value="${pageCount}"/>
   </c:if> 
          
   <c:if test="${startPage > 10}">
   		
   <!-- <a href="/jspkyu_mvc2/kyu/board/boardlist.do?pageNum=${startPage - 10 }">[이전]</a>
   -->
   </c:if>
	<div id="board_list">
   <c:forEach var="i" begin="${startPage}" end="${endPage}">
       <input class="pageButton" type="button" onclick="pageNumberQnA(${i})" value="${i }">
       
       <!--
       <a href="/jspkyu_mvc2/kyu/board/boardlist.do?pageNum=${i}">${i}</a>
       -->
   </c:forEach>
   </div>

   <c:if test="${endPage < pageCount}">
   <!-- 
        <a href="/jspkyu_mvc2/kyu/board/boardlist.do?pageNum=${startPage + 10}">[다음]</a>
   -->
   </c:if>
</c:if>
	</c:if>
	
</div>