<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${result == 0 }">
	<script>
	alert("해당 예약건이 삭제 되었습니다.");
	
	$.ajax({
		type : "post",
		url : "/moahair/mypage/BookingList.do",
		success : function(data) {
			$(".contentMyPage").html(data);
		}
	});
</script>
</c:if>

<c:if test="${result == 1 }">
	<script>
	alert("해당 예약건이 삭제 되었습니다. <br> (3시간 이내 취소 건으로 페널티가 부과됩니다.)");
	
	$.ajax({
		type : "post",
		url : "/moahair/mypage/BookingList.do",
		success : function(data) {
			$(".contentMyPage").html(data);
		}
	});
	</script>
</c:if>

<c:if test="${result == 2 }">
	<script>
	alert("예약시간 초과로 취소가 불가능한 예약건입니다.");
	
	$.ajax({
		type : "post",
		url : "/moahair/mypage/BookingList.do",
		success : function(data) {
			$(".contentMyPage").html(data);
		}
	});
	</script>
</c:if>
    