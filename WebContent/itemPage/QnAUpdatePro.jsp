<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
	alert("수정 되었습니다.");
	
	$(".QnAViewTop").css("font-weight", "bold");
	$(".ProductViewTop").css("font-weight", "");
	$("#contentProductDetail").css("display", "none");
	$("#contentQnADetail").css("display", "block");
	var bs_number = ${bs_num};
	var i_number = ${i_num};

	$.ajax({
		type : "post", url : "/moahair/product/item/QnAList.do",
		data : { bs_num : bs_number, i_num : i_number},
		success : function(data) {
			$("#contentQnADetail").html(data);
		}
	})
</script>