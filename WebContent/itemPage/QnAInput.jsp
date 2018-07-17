<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>

<script>
	function cancleForm() {
		$("#view").html("");
	}
</script>

<h2>QnA ${m_condition}</h2>

<%--상품등록하고나서 판매자의 상품리스트를 보여주자 --%>
<form id="QnAInputPro" name="QnAInputPro" method="post" >
	
	<div>
		<div>작성자 : ${memId }</div>
		<div>매장명 : ${shopname } </div>
		<textarea name="bd_contents" id="textAreaContent" style="width: 80%" rows="10" cols="80"></textarea>
		
	</div>
		
	<input type="hidden" name ="i_num" value="${i_num }"/>		
	<input type="hidden" name ="bs_num" value="${bs_num }"/>		
	<input type="hidden" name ="m_id" value="${memId }"/>	
		
	<input class="pageOtherButton" type="button" onclick="submitContents(this);" value="등록" />
	<input class="pageOtherButton" type="reset" value="취소" onclick="cancleForm()" />
	
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
    	var formData = $("#QnAInputPro").serialize();

    	$.ajax({
    		type : "post", 
    		url : "/moahair/product/item/QnAInputPro.do",
    		data : formData,
    		success : function(data) {
    			$("#view").html(data);
    		}
    	})
    } catch(e) {
     
    }
}
 
// textArea에 이미지 첨부
function pasteHTML(filepath){
    var sHTML = '<img src="<%=request.getContextPath()%>/sellerPage/se_editor/tmp/'+filepath+'">';
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