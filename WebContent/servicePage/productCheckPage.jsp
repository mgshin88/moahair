<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

선택한 상품정보

상품명 : ${cart.c_name }
상품가격 : ${cart.c_price }
사진 :

<img src="${cart.c_thumnail}"/>

기장 : ${cart.c_option }
옵션1 : ${cart.c_option_sel1 }
옵션 2 : ${cart.c_option_sel2 }



</body>
</html>