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

<h2>QnA ${m_condition}</h2>

<%--상품등록하고나서 판매자의 상품리스트를 보여주자 --%>
<form action="/moahair/mypage/QnAInputPro.do" method="post" >
	
	<c:if test="${m_condition == false }">
	<script type="text/javascript">
	alert("회원가입후 사용해 주세요");
	window.location='/moahair/main/main.do';
	</script>
	
	</c:if>
	
	
	<table width="100%">
		<tr>
			<td>*작성자</td><%-- db에서 가져와서 value 로 넣어주기  --%>
			<td>${name }</td>
		</tr>
		<tr>
			<td>*매장명</td><%-- db에서 가져와서 value 로 넣어주기  --%>
			<td>${shopname }</td>
		</tr>
		<input type="hidden" name ="i_num" value="${i_num }"/>		
		<input type="hidden" name ="bs_num" value="${bs_num }"/>		
		<input type="hidden" name ="m_id" value="${memId }"/>	
		
		<tr>
			<td>*제목</td>
			<td>
				<input type = "text" name="bd_subject" style="width: 100%">
			</td>
		</tr>
		<tr>
			<td>*글 내용</td>
			<td>
				<textarea name="bd_contents" id="textAreaContent" style="width: 100%" rows="10" cols="80"></textarea>
			</td>
		</tr>
	
	
	</table>

	
	<input type="submit" onclick="submitContents(this);" value="등록" />
	<input type="reset" value="취소" />
	
</form>


<script type="text/javascript">
 
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors,
    elPlaceHolder: "textAreaContent",
    sSkinURI: "<%=request.getContextPath()%>/sellerPage/se_editor/SmartEditor2Skin.html",
    fCreator: "createSEditor2"
});


//‘저장’ 버튼을 누르는 등 저장을 위한 액션을 했을 때 submitContents가 호출된다고 가정한다.
function submitContents(elClickedObj) {
    // 에디터의 내용이 textarea에 적용된다.
    oEditors.getById["textAreaContent"].exec("UPDATE_CONTENTS_FIELD", [ ]);
 
    // 에디터의 내용에 대한 값 검증은 이곳에서
    // document.getElementById("textAreaContent").value를 이용해서 처리한다.
  
    try {
        elClickedObj.form.submit();
    } catch(e) {
     
    }
}
 
// textArea에 이미지 첨부
function pasteHTML(filepath){
    var sHTML = '<img src="<%=request.getContextPath()%>/sellerPage/se_editor/upload/'+filepath+'">';
    oEditors.getById["textAreaContent"].exec("PASTE_HTML", [sHTML]); 
}

//추가 옵션 check시 text활성화 , 비활성화
function handleChange(checkbox) {
    if(checkbox.checked == true){
    	var td1 = checkbox.parentNode;
    	
    	var td2 = td1.nextSibling.nextSibling;
    	
    	var text = td2.firstChild;
    	
        text.removeAttribute("disabled");
    }else{
		var td1 = checkbox.parentNode;
    	
    	var td2 = td1.nextSibling.nextSibling;
    	
    	var text = td2.firstChild;
        text.setAttribute("disabled", "disabled");
   }
}

</script>

</body>
</html>