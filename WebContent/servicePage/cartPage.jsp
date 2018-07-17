<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="../mainPage/header.jsp" flush="false"/>


장바구니

상품명 : ${cart.c_name }
상품가격 : ${cart.c_price }
사진 :

<img src="${cart.c_thumnail}"/>

기장 : ${cart.c_option }
옵션1 : ${cart.c_option_sel1 }
옵션 2 : ${cart.c_option_sel2 }

<input type="button" value="예약하기" onclick="window.location=/moahair/mypage/BookingForm.do">

	<jsp:include page="../mainPage/footer.jsp" flush="false"/>


</body>
</html>