<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/moahair/css/jquery-ui.css">
<link rel="stylesheet" type="text/css"
	href="/moahair/css/fontawesome_463.css">
<link rel="stylesheet" type="text/css" href="/moahair/css/kyuCSS.css">
<link rel="stylesheet" type="text/css" href="/moahair/css/admin.css">
<link rel="stylesheet" href="/moahair/css/master.css" type="text/css">
<link rel="stylesheet" href="/moahair/css/sellerAdmin.css" type="text/css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="<%=request.getContextPath()%>/sellerPage/se_editor/js/HuskyEZCreator.js"
	type="text/javascript" charset="UTF-8"></script>
<script
	src="<%=request.getContextPath()%>/sellerPage/se_editor/photo_uploader/plugin/hp_SE2M_AttachQuickPhoto.js"
	type="text/javascript" charset="UTF-8"></script>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="/moahair/js/jquery-ui.js"></script>
<script src="/moahair/js/regForm.js"></script>


</head>
<body>
	<c:if test="${m_level != 10 }">
		<script>
			alert("접근이 불가능한 페이지 입니다.");
			window.location = "/moahair/main/main.do";
			</script>
	</c:if>

	<script type="text/javascript">

	$(document).ready(function() {	
	type();
		function type() {
			var type = parseInt('${type}');
			if (type == 1) {
				inputShop();
			}
	
			else if (type == 2) {
				user();
			}
			
			else if(type == 3) {
				seller();
			}
			
			else if (type == 4) {
				item();
			}
			
			else if (type == 5) {
				total();
			}
		}

		
		
			$("#inputshopshow").click(function() {
				$("#inputshopshow").css("background-color", "#58D68D");
				$("#inputshopshow").css("color", "#ffffff");

				$("#usershow").css("background-color", "");
				$("#usershow").css("color", "");
				$("#sellershow").css("background-color", "");
				$("#sellershow").css("color", "");
				$("#itemshow").css("background-color", "");
				$("#itemshow").css("color", "");
				$("#totalshow").css("background-color", "");
				$("#totalshow").css("color", "");

				$.ajax({
					type : "post",
					url : "/moahair/supervisor/BusinessInputForm.do",
					success : function(data) {
						$("#view1").html(data);
					}
				});
			})

			$("#usershow").click(function() {
				$("#usershow").css("background-color", "#58D68D");
				$("#usershow").css("color", "#ffffff");

				$("#inputshopshow").css("background-color", "");
				$("#inputshopshow").css("color", "");
				$("#sellershow").css("background-color", "");
				$("#sellershow").css("color", "");
				$("#itemshow").css("background-color", "");
				$("#itemshow").css("color", "");
				$("#totalshow").css("background-color", "");
				$("#totalshow").css("color", "");

				$.ajax({
					type : "post",
					url : "/moahair/supervisor/userPage.do",
					success : function(data) {
						$("#view1").html(data);
					}
				});
			})

			$("#sellershow").click(function() {
				$("#sellershow").css("background-color", "#58D68D");
				$("#sellershow").css("color", "#ffffff");

				$("#inputshopshow").css("background-color", "");
				$("#inputshopshow").css("color", "");
				$("#usershow").css("background-color", "");
				$("#usershow").css("color", "");
				$("#itemshow").css("background-color", "");
				$("#itemshow").css("color", "");
				$("#totalshow").css("background-color", "");
				$("#totalshow").css("color", "");

				$.ajax({
					type : "post",
					url : "/moahair/supervisor/Seller.do",
					success : function(data) {
						$("#view1").html(data);
					}
				});
			})

			$("#itemshow").click(function() {
				$("#itemshow").css("background-color", "#58D68D");
				$("#itemshow").css("color", "#ffffff");

				$("#inputshopshow").css("background-color", "");
				$("#inputshopshow").css("color", "");
				$("#usershow").css("background-color", "");
				$("#usershow").css("color", "");
				$("#sellershow").css("background-color", "");
				$("#sellershow").css("color", "");
				$("#totalshow").css("background-color", "");
				$("#totalshow").css("color", "");

				$.ajax({
					type : "post",
					url : "/moahair/supervisor/ItemList.do",
					success : function(data) {
						$("#view1").html(data);
					}
				});
			})

			$("#totalshow").click(function() {
				$("#totalshow").css("background-color", "#58D68D");
				$("#totalshow").css("color", "#ffffff");

				$("#inputshopshow").css("background-color", "");
				$("#inputshopshow").css("color", "");
				$("#usershow").css("background-color", "");
				$("#usershow").css("color", "");
				$("#itemshow").css("background-color", "");
				$("#itemshow").css("color", "");
				$("#sellershow").css("background-color", "");
				$("#sellershow").css("color", "");

				$.ajax({
					type : "post",
					url : "/moahair/supervisor/totalButton.do",
					success : function(data) {
						$("#view1").html(data);
					}
				});
			})
		
		

		function inputShop() {
	$("#inputshopshow").css("background-color", "#58D68D");
	$("#inputshopshow").css("color", "#ffffff");
	
	$("#usershow").css("background-color", "");
	$("#usershow").css("color", "");
	$("#sellershow").css("background-color", "");
	$("#sellershow").css("color", "");
	$("#itemshow").css("background-color", "");
	$("#itemshow").css("color", "");
	$("#totalshow").css("background-color", "");
	$("#totalshow").css("color", "");
	
	$.ajax({
		type : "post" ,  url : "/moahair/supervisor/BusinessInputForm.do",
		success : function(data){
			$("#view1").html(data);
		}
	});
	
}

function user() {
	$("#usershow").css("background-color", "#58D68D");
	$("#usershow").css("color", "#ffffff");
	
	$("#inputshopshow").css("background-color", "");
	$("#inputshopshow").css("color", "");
	$("#sellershow").css("background-color", "");
	$("#sellershow").css("color", "");
	$("#itemshow").css("background-color", "");
	$("#itemshow").css("color", "");
	$("#totalshow").css("background-color", "");
	$("#totalshow").css("color", "");
	
	$.ajax({
		type : "post" ,  url : "/moahair/supervisor/userPage.do",
		success : function(data){
			$("#view1").html(data);
		}
	});
	
}

function seller() {
	$("#sellershow").css("background-color", "#58D68D");
	$("#sellershow").css("color", "#ffffff");
	
	$("#inputshopshow").css("background-color", "");
	$("#inputshopshow").css("color", "");
	$("#usershow").css("background-color", "");
	$("#usershow").css("color", "");
	$("#itemshow").css("background-color", "");
	$("#itemshow").css("color", "");
	$("#totalshow").css("background-color", "");
	$("#totalshow").css("color", "");
	
	$.ajax({
		type : "post" ,  url : "/moahair/supervisor/Seller.do",
		success : function(data){
			$("#view1").html(data);
		}
	});
	
}

function item() {
	$("#itemshow").css("background-color", "#58D68D");
	$("#itemshow").css("color", "#ffffff");
	
	$("#inputshopshow").css("background-color", "");
	$("#inputshopshow").css("color", "");
	$("#usershow").css("background-color", "");
	$("#usershow").css("color", "");
	$("#sellershow").css("background-color", "");
	$("#sellershow").css("color", "");
	$("#totalshow").css("background-color", "");
	$("#totalshow").css("color", "");
	
	$.ajax({
		type : "post" ,  url : "/moahair/supervisor/ItemList.do",
		success : function(data){
			$("#view1").html(data);
		}
	});
}

function total() {
	$("#totalshow").css("background-color", "#58D68D");
	$("#totalshow").css("color", "#ffffff");
	
	$("#inputshopshow").css("background-color", "");
	$("#inputshopshow").css("color", "");
	$("#usershow").css("background-color", "");
	$("#usershow").css("color", "");
	$("#itemshow").css("background-color", "");
	$("#itemshow").css("color", "");
	$("#sellershow").css("background-color", "");
	$("#sellershow").css("color", "");
	
	$.ajax({
		type : "post" ,  url : "/moahair/supervisor/totalButton.do",
		success : function(data){
			$("#view1").html(data);
		}
	});
	
}
		})
		/* ajax 방식이라 뒤로가기 해결 하려 했으나 좋지 못하다고 해서 안함*/
		/* 
		function CheckForHash() {
			if (document.location.hash) {
				var HashLocationName = document.location.hash;
				HashLocationName = HashLocationName.replace("#", "");
				
				if(HashLocationName == ('Show1')) {
					inputShop();
				} 
				
				else if(HashLocationName == ('Show2')) {
					user();
				}
				
				else if(HashLocationName == ('Show3')) {
					seller();
				}
				
				else if(HashLocationName == ('Show4')) {
					item();
				}
				
				else if(HashLocationName == ('Show5')) {
					total();
				}
				
			} else {
				
			}
		}
		function RenameAnchor(anchorid, anchorname) {
			document.getElementById(anchorid).name = anchorname; //this renames the anchor
		}
		function RedirectLocation(anchorid, anchorname, HashName) {
			RenameAnchor(anchorid, anchorname);
			document.location = HashName;
			CheckForHash();
		}
		*/
	</script>

	
	<jsp:include page="/mainPage/header3.jsp" flush="false" />

	

	<div class="adminContent">
		<div id="view1"></div>
	</div>



</body>
</html>