<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:if test="${sessionScope.memId == null }">
		<script>
			alert("로그인 후 가능합니다.");
			window.location = "/moahair/member/loginForm.do";
		</script>
	</c:if>

<script>
function qnaitemSelecter(num){
	$.ajax ({
		type : "post",
		url : "/moahair/mypage/QnASelecter.do",
		data : {num : num},
		success : function(data) {
			$("#mypageQnAView").html(data);
		}
	})		
}

function QnAupdate(bd_num, count, i_num, bs_num){
	var sessionId = "${sessionScope.memId}";
	if(!sessionId) {
		alert("로그인 후 이용 가능합니다.");
		window.location = "/moahair/member/loginForm.do";
	}
	
	var bs_number = bs_num;
	var i_number = i_num;
	$.ajax({
		type : "post", url : "/moahair/mypage/QnAUpdate.do",
		data : { bs_num : bs_number, i_num : i_number, bd_num : bd_num, count : count },
		success : function(data) {
			$("#QnAUpdate"+count).html(data);
		}
	})
}
function QnAdelete(bd_num, i_num, bs_num){
	var sessionId = "${sessionScope.memId}";
	if(!sessionId) {
		alert("로그인 후 이용 가능합니다.");
		window.location = "/moahair/member/loginForm.do";
	}
	
	if(confirm("정말로 삭제 하시겠습니까?")) {
	var bs_number = bs_num;
	var i_number = i_num;
	$.ajax({
		type : "post" ,  url : "/moahair/mypage/QnADelete.do",
		data : { bs_num : bs_number, i_num : i_number, bd_num : bd_num },
		success : function(data){
			$("#view").html(data);
		}
	});
	}
}

function QnAContentsLink() {
	$("#QnAcontentsForm").submit();
}

</script>

<div class="logregHeader">
	<div class="logregTitle">MyQnA</div>
</div>

<div id="mypageQnA" class="qna_all">
	

	<c:if test="${count == 0 }">
 		작성된 QnA가 없습니다.
 	</c:if>

	<c:if test="${count > 0 }">
		<div class="qnaSelecterline">
		<select class="qnabutton_select" name="qnabutton_select" onchange="qnaitemSelecter(this.value)">
			<option value="0">전체보기</option>
			<c:forEach var="bdi_list" items="${bdi_list }">
				<option value="${bdi_list.i_num }">${bdi_list.i_name } ( ${bdi_list.bs_name } )</option>
			</c:forEach>
		</select>
		</div>
		
		
		<div id="mypageQnAView">
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
					<form id="QnAcontentsForm" action="/moahair/product/item/ProductView.do">
						<div class="QnAcontentsName" onclick="QnAContentsLink()"> 
					${list.bd_contents } 
					</div>
						<input type="hidden" name="i_num" value="${list.bd_i_num }">
						<input type="hidden" name="number" value="3"> 
					</form>
					
					<c:if test="${check && list.bd_ref_count == 0}">
						<div class="QnAbutton">
						<input class="pageOtherButton" type="button" onclick="QnARefly(${list.bd_num} , ${list.bd_ref}, ${qnalist.count } )" value="답변등록" />
						</div>
					</c:if>
					<c:if test="${list.bd_writer  == sessionScope.memId }">
					<div class="QnAbutton">
						<input class="pageOtherButton" type="button" onclick="QnAupdate( ${list.bd_num}, ${qnalist.count }, ${list.bd_i_num }, ${list.bd_bs_num } )" value="수정" />
						<input class="pageOtherButton" type="button" onclick="QnAdelete( ${list.bd_num}, ${list.bd_i_num }, ${list.bd_bs_num } )" value="삭제" />
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
					<form id="QnAcontentsForm" action="/moahair/product/item/ProductView.do">
						<div class="QnAcontentsName" onclick="QnAContentsLink()"> 
					${list.bd_contents } 
					</div>
						<input type="hidden" name="i_num" value="${list.bd_i_num }">
						<input type="hidden" name="number" value="3"> 
					</form>
					
					<c:if test="${list.bd_writer  == sessionScope.memId }">
					<div class="QnAbutton">
						<input class="pageOtherButton" type="button" onclick="QnAupdate( ${list.bd_num}, ${qnalist.count }, ${list.bd_i_num }, ${list.bd_bs_num } )" value="수정" />
						<input class="pageOtherButton" type="button" onclick="QnAdelete( ${list.bd_num}, ${list.bd_i_num }, ${list.bd_bs_num } )" value="삭제" />
					</div>
					</c:if>
					
			<div id="QnAUpdate${qnalist.count }"></div>
					<div id="QnAReply${qnalist.count }"></div>
				</li>
			
			
			</ul>
			
			</c:if>
		</c:forEach>
		</div>
		</c:if>
</div>
<div id = "view"></div>
