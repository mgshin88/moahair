<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${m_level != 10 }">
		<script>
			alert("접근이 불가능한 페이지 입니다.");
			window.location = "/moahair/main/main.do";
		</script>
	</c:if>
<h2>사용자 검색</h2>

<script type="text/javascript">

function userserch() {
	$.ajax({
		type : "post" ,  url : "/moahair/supervisor/userSearch.do",
		data : {user_id : $("#user_id").val()},
		success : function(data){
			$("#user_content").html(data);
			$("#user_id").val("");
			$("#user_id").focus();
		}
	});
	
}

$('#user_id').keypress(function(event){
    if ( event.which == 13 ) {
    	userserch();
        return false;
    }
});

</script>

<div style="width:500px; float:left ; ">

<input type="text" name ="user_id" id="user_id" />
<input type="button" value ="검색" onclick ="userserch()"/> 

</div>


</body>
</html>