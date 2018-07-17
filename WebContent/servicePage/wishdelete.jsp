<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
	alert("해당 찜 목록이 삭제 되었습니다.");
	
	$.ajax({
		type : "post", url : "/moahair/mypage/WishList.do",
		success : function(data) {
			$(".contentMyPage").html(data);
		}
	})
</script>