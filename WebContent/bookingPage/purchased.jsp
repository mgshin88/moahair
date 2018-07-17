<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="/moahair/js/jquery-3.1.1.js"></script>
  <link rel="stylesheet" href="/moahair/css/buttontd.css" type="text/css">

    
    <div id="pur" style="text-align:center">
    <img src="/moahair/bookingPage/blackheart.png" width="150" /> 
    <h1>모아헤어를 이용해 주셔서 감사합니다.</h1>
	<h2>${today} ${time}에 매장으로 방문하세요</h2><br/>
	<input type="button" id="purbutton" value="계속쇼핑하기" onclick="location.href='/moahair/main/main.do'">
	<input type="button" value="로그아웃" id="purbutton" onclick="location.href='/moahair/member/logout.do'">
	</div>