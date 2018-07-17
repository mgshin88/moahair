<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
	alert("수정 되었습니다.");
	
	var bs_number = ${bs_num};
	var i_number = ${i_num};

	$.ajax({
		type : "post", url : "/moahair/mypage/MyQnA.do",
		data : { bs_num : bs_number, i_num : i_number},
		success : function(data) {
			$(".contentMyPage").html(data);
		}
	})
</script>