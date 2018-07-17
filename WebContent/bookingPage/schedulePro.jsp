<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




디자이너 이름  : ${dto.s_name} <br />
디자이너 직함 :${dto.s_title}<br />
디자이너 프로필사진 : <img src="/moahair/saveImg/${dto.s_background}"  width="150"/><br />
디자이너 배경사진 : <img src="/moahair/saveImg/${dto.s_profile}" width="150" /><br />
정기휴일:${dto.s_holiday}<br />
임시휴일 :${dto.s_annualleave}<br />
영업시간 :${timetable2}<br />

