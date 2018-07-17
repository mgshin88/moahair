<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:if test="${dto.modify_limit == 0 }">
	<script>
		alert("수정 횟수가 초과되어 수정이 불가능합니다.");
	</script>
</c:if>

<c:if test="${dto.modify_limit != 0 }">
	<c:if test="${re != 0 }">
		<script>
			alert("3시간 이내 예약건이므로 수정이 불가능합니다.");
		</script>
	</c:if>
	
	<c:if test="${re == 0 }">
		<script>
			window.location = "/moahair/booking/modifybooking.do?bk_num=${bk_num}";
		</script>
	</c:if>

</c:if>

<script>
	
</script>
    
    