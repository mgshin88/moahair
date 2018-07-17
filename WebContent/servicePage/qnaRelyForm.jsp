<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>판매자 상품 등록</title>
 
</head>
<body>

<h2>QnA 답변</h2>

<%--상품등록하고나서 판매자의 상품리스트를 보여주자 --%>
<form action="/moahair/mypage/QnARelyPro.do" method="post" >
	
<input type="hidden" name="bd_num" value="${bd_num }"/>
<input type="hidden" name="bd_ref" value="${bd_ref }"/>
<input type="hidden" name="i_num" value="${i_num }"/>


	
	<table width="100%">
		<tr>
			<td>*작성자</td><%-- db에서 가져와서 value 로 넣어주기  --%>
			<td>${shopname }</td>
		</tr>
		
		
		<tr>
			<td>*제목</td>
			<td>
				<input type = "text" name="bd_subject" value="[re] ${dto.bd_subject }" style="width: 100%">
			</td>
		</tr>
		<tr>
			<td>*글 내용</td>
			<td>
				<textarea name="bd_contents" id="textAreaContent" style="width: 100%" rows="10" cols="80">
				
				${dto.bd_contents }
				</textarea>
			</td>
		</tr>
	
	
	</table>

	<input type="submit" value="등록" />
	<input type="reset" onclick="window.location='/moahair/mypage/QnAList.do'"   value="취소" />
	
</form>



</body>
</html>