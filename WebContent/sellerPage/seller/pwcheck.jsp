<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>내 정보관리 :: 비밀번호확인</title>
</head>
<body>
	
<script type="text/javascript">
	function checkPw(){
		
		$.ajax({
		      url : "/moahair/seller/pwcheckpro.do",
		      data : {
		    	  pw : $("#pw").val(),
		    	  bs_num : '${bs_num}'
		      },
		      success : function(data){
		         $("#sellerContents").html(data);
		      }
		   });
	}
	$("#pw").keypress(function(e) { 
			
		    if (e.keyCode == 13){
		    
		    	$.ajax({
				      url : "/moahair/seller/pwcheckpro.do",
				      data : {
				    	  pw : $("#pw").val(),
				    	  bs_num : '${bs_num}'
				      },
				      success : function(data){
				         $("#sellerContents").html(data);
				      },
				   });
		    }    
		});
		
	

	</script>

		비밀번호확인 : <input type="password" name="pw" id="pw"  />
		<input type="button" class="AddBtn" value="확인" onclick="checkPw()"/>

</body>
</html>