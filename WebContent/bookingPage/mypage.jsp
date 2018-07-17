<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="/moahair/js/jquery-3.1.1.js"></script>
  <link rel="stylesheet" href="/moahair/css/buttontd.css" type="text/css">
  

  <div id="pur" style="text-align:center">
    <h1>예약 수정은 총3회, 예약시간 3시간 전까지만 허용됩니다</h1>
   
     
  예약자 id : ${dto.bk_m_id}
 예약자 고유번호 : ${dto.bk_m_num}
 예약날짜 : ${dto.bk_date}
 예약시간 : ${dto.bk_time}
<br>

  	 <c:if test="${dto.modify_limit !=0}">
  	 	<c:if test="${result ==0}">
			<input type="button" value="예약수정" id="purbutton" onclick="location.href='/moahair/booking/modifybooking.do'">
		</c:if>
		<c:if test="${result !=0}">
		<input type="button" value="예약수정" id="purbutton" onclick="alert('수정 가능 시간 초과')">
		</c:if>
	</c:if>
	 <c:if test="${dto.modify_limit == 0}">
		<input type="button" value="예약수정" id="purbutton" onclick="alert('변경횟수 초과')">
		</c:if>

</div>