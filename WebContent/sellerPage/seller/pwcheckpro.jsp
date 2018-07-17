<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>내 정보관리 :: 비밀번호확인 되었다</title>
</head>
<body>
<script type="text/javascript">
function inputSelInfo(){
	$.ajax({
	      url : "/moahair/seller/businessinputform.do",
	      data : {
	    	  bs_num : '${bs_num}'
	      },
	      success : function(data){
	         $("#sellerContents").html(data);
	      }
	   });
}
function updateSelInfo(){
	$.ajax({
	      url : "/moahair/seller/businessupdateform.do",
	      data : {
	    	  bs_num : '${bs_num}'
	      },
	      success : function(data){
	         $("#sellerContents").html(data);
	      }
	   });
}

function deleteSelInfo(){
	if(confirm("정말 삭제하시겠습니까?")==true){
		$.ajax({
		      type : "post" ,  url : "/moahair/seller/businessdeleteform.do?bs_num="+${bs_num},
		      success : function(data){
		    	  location.href="/moahair/seller/sellersel.do"
		      }
		   });
	
	}
}

</script>

	<c:if test="${check == 1 }"	>
			<input type="button" class="AddBtn" value="정보 수정" onclick="updateSelInfo()" />
			<input type="button" class="AddBtn" value="정보 삭제" onclick="deleteSelInfo()" />
		
	</c:if>	
	<c:if test="${check <= 0 }" >
		<script type="text/javascript">
			alert("비밀번호가 틀렸습니다. 다시 입력해주세요.");
			window.location="/moahair/seller/sellerviewmain.do?bs_num=${bs_num}&type=4";
		</script>
	</c:if>
</body>
</html>