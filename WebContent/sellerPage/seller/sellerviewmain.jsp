<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="/moahair/css/master.css" type="text/css">
<link rel="stylesheet" type="text/css" href="/moahair/css/kyuCSS.css">
<link rel="stylesheet" type="text/css" href="/moahair/css/admin.css">
<link rel="stylesheet" type="text/css" href="/moahair/css/master.css">
<link rel="stylesheet" type="text/css" href="/moahair/css/sellerAdmin.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>판매자 관리 페이지 :: 샵 관리</title>
<script src="${projectPath}/sellerPage/se_editor/js/HuskyEZCreator.js" type="text/javascript" charset="UTF-8"></script>
<script src="${projectPath}/sellerPage/se_editor/photo_uploader/plugin/hp_SE2M_AttachQuickPhoto.js" type="text/javascript" charset="UTF-8"></script>
<script src="/moahair/js/jquery-3.1.1.js"></script>
<script src="/moahair/sellerPage/seller/seller.js" type="text/javascript" charset="UTF-8"></script>
</head>
<body onload="home();">

<script type="text/javascript">
type();
function type(){
	var type = parseInt('${type}');
	if(type == 2){
		manageItem();
	}
	if(type == 3){
		manageStaff();
	}
	if(type == 4){
		manageInfo();
	}
	if(type == 5){
		var sel = parseInt('${sel}');
		if(sel == 2 ){
			goPCPro();
		}
	}
}
function goPCPro(){
	$.ajax({
	      url : "/moahair/seller/pwcheckpro.do",
	      data : {
	    	bs_num : '${bs_num}',
	    	check : '1'
	      },
	      success : function(data){
	         $("#sellerContents").html(data);
	      }
	   });
}
function home(){
/* 		$("#sellerContents").load("/moahair/seller/SellerView.do?bs_num=${ bs_num} .viewPager");
*/ 	 $.ajax({
	      url : "/moahair/seller/store.do",
	      data : {
	    	  bs_num : '${ bs_num}'
	      },
	      success : function(data){
	    	  
	         
	    	  $("#sellerContents").html(data);
	      }
	   });
	 
} 
function manageInfo(){
	$.ajax({
	      url : "/moahair/seller/pwcheck.do",
	      data : {
	    	  bs_num : '${ bs_num}',
	    	  id : '${m_id}'
	      },
	      success : function(data){
	         $("#sellerContents").html(data);
	      }
	   });
}

function manageItem(){
	   $.ajax({
	      url : "/moahair/seller/sellerviewlist.do",
	      data : {
	    	  bs_num : '${ bs_num}'   
	      },
	      success : function(data){
	         $("#sellerContents").html(data);
	      }
	   });
}
function manageAccount(){
	$.ajax({
	      url : "/moahair/seller/sellerDateSel.do",
	      data : {
	    	 id : '${ m_id}',
	    	 bs_num : '${ bs_num}'
	      },
	      success : function(data){
	         $("#sellerContents").html(data);
	      }
	   });
}
function manageBusiness(){
	$.ajax({
	      url : "/moahair/seller/sellerviewlist.do",
	      data : {
	    	  bs_num : '${ bs_num}'   
	      },
	      success : function(data){
	         $("#sellerContents").html(data);
	      }
	   });
}
function manageStaff(){
	$.ajax({
	      url : "/moahair/seller/sellerDesignerList.do",
	      data : {
	    	  bs_num : '${ bs_num}'   
	      },
	      success : function(data){
	         $("#sellerContents").html(data);
	      }
	   });
}
function manageBooking(){
	$.ajax({
	      url : "/moahair/seller/scheduler.do",
	      data : {
	    	  bs_num : '${ bs_num}'   
	      },
	      success : function(data){
	         $("#sellerContents").html(data);
	      }
	   });
}
function manageQnA(){
	$.ajax({
	      url : "/moahair/seller/sellerQnaList.do",
	      data : {
	    	  bs_num : '${ bs_num}'   
	      },
	      success : function(data){
	         $("#sellerContents").html(data);
	      }
	   });
}
function selectBusiness(){
	$.ajax({
	      url : "/moahair/seller/sellerList.do",
	      data : {
	    	  bs_num : '${ bs_num}'   
	      },
	      success : function(data){
	         $("#sellerContents").html(data);
	      }
	   });
}
</script>

	<jsp:include page="/mainPage/header2.jsp" flush="true" />

	
		<div id="sellerContents">
		
		</div>
		<jsp:include page="/mainPage/footer.jsp" flush="true" />

</body>
</html>