<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=request.getContextPath()%>/sellerPage/se_editor/js/HuskyEZCreator.js" type="text/javascript" charset="UTF-8"></script>
<script src="<%=request.getContextPath()%>/sellerPage/se_editor/photo_uploader/plugin/hp_SE2M_AttachQuickPhoto.js" type="text/javascript" charset="UTF-8"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QnA List</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<h2>list!!!!!!!!!!!!!!!!!!!!!!!!!</h2>
전체글 : ${count }<br/>

<script>
$(document).ready(function() {
	
	$(".accordion_sub").css('display' , 'none');
	
    $(".accordion_banner > .accordion_title").click(function() {
        if($(this).next("div").is(":visible")){
        $(this).next("div").slideUp("fast");
        } else {
            $(".accordion_banner .accordion_sub").slideUp("fast");
            $(this).next("div").slideToggle("fast");
        }
    });
});


function QnAwrite(){	
	$("#view").load("/moahair/mypage/QnAInputForm.do");

}


function QnAupdate(bd_num){
	$("#view").load("/moahair/mypage/QnAUpdate.do?bd_num="+bd_num);
}
function QnAdelete(bd_num){
	$.ajax({
		type : "post" ,  url : "/moahair/mypage/QnADelete.do?bd_num="+ bd_num,
		success : function(data){
			$("#view").html(data);
		}
	});
}

function QnARefly(bd_num, bd_ref){
	$.ajax({
		type : "post" ,  url : "/moahair/mypage/QnARely.do?bd_num="+bd_num+"&bd_ref="+bd_ref,
		success : function(data){
			$("#view").html(data);
		}
	});
}

</script>

 <input type="button" value = "QnA작성" onclick="QnAwrite()"/><br/><br/><br/>
 
 
 
<c:if test="${count == 0 }">
 작성된 QnA가 없습니다.
 </c:if>
 
 <c:if test="${count > 0 }">
 
<c:forEach var="list" items='${list }'>

<div  class="accordion_banner">
<div class="accordion_title">
사용자 = ${list.bd_writer }///
제목 = ${list.bd_subject}///
글쓴 날짜 = ${list.bd_date}///</div>


<div class="accordion_sub">내용 !!!!!${list.bd_contents }
	<c:if test="${check == true }">
	<input type ="button" onclick="QnARefly(${list.bd_num} , ${list.bd_ref})" value="답변등록"/>
	</c:if>
	<c:if test="${list.bd_writer  == sessionScope.memId }">
	<input type ="button" onclick="QnAupdate(${list.bd_num})" value ="수정">
	<input type ="button" onclick="QnAdelete(${list.bd_num})"  value ="삭제">
	</c:if>
	</div>
</div>

</c:forEach>
</c:if>

<div id = "view"></div>

</body>
</html>