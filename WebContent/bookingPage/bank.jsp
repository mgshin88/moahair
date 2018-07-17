<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
   <script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
  

   <script>
   var IMP = window.IMP;
   IMP.init('imp14088793');
      IMP.request_pay({
       pg : 'inicis', // version 1.1.0부터 지원.
       pay_method : 'phone',
       merchant_uid : 'merchant_' + new Date().getTime(),
       name :'${i_name}',
       amount : 150,
       buyer_email : '${member.m_email}',
       buyer_name : '${member.m_name}',
       buyer_tel : '${member.m_phone}',
       buyer_addr : '${member.m_address}',
       buyer_postcode : '12345',
       m_redirect_url : 'https://www.yourdomain.com/payments/complete'},
       
       function(rsp) {
       if ( rsp.success ) {
           var msg = '결제가 완료되었습니다.';
           msg += '고유ID : ' + rsp.imp_uid;
           msg += '상점 거래ID : ' + rsp.merchant_uid;
           msg += '결제 금액 : ' + rsp.paid_amount;
           msg += '카드 승인번호 : ' + rsp.apply_num;
           
 
           window.location="/moahair/booking/purchased.do?bk_date="+'${bk_date}'+"&bk_time="+'${bk_time}';

       } else {
           var msg = '결제에 실패하였습니다.';
           msg += '에러내용 : ' + rsp.error_msg;
           window.location="/moahair/main/main.do";
       }
       alert(msg);
       
   });
  
 </script>