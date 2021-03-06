<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionScope.memId == null }">
		<script>
			alert("로그인 후 가능합니다.");
			window.location = "/moahair/member/loginForm.do";
		</script>
	</c:if>

<c:forEach var="list" items="${mdto }" varStatus="qnalist">
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