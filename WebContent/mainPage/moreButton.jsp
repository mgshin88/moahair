<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/moahair/css/master.css" type="text/css">


<!DOCTYPE html>


<html dir="ltr">


<head>

<meta charset="utf-8">
<title></title>
<style media="screen">
.beans {
	display: flex;
	align-items: center;
	justify-content: center;
	height: 50vh;
	width: 100vw;
	cursor: pointer;
}

.coolBeans {
	border: 2px solid currentColor;
	border-radius: 3rem;
	color: #58D68D;
	font-size: 2rem;
	font-weight: 100;
	overflow: hidden;
	padding: 1rem 2rem;
	position: relative;
	text-decoration: none;
	transition: 0.2s -webkit-transform ease-in-out;
	transition: 0.2s transform ease-in-out;
	transition: 0.2s transform ease-in-out, 0.2s -webkit-transform
		ease-in-out;
	will-change: transform;
	z-index: 0;
}

.coolBeans::after {
	background-color: #ff0;
	border-radius: 3rem;
	content: '';
	display: block;
	height: 100%;
	width: 100%;
	position: absolute;
	left: 0;
	top: 0;
	-webkit-transform: translate(-100%, 0) rotate(10deg);
	transform: translate(-100%, 0) rotate(10deg);
	-webkit-transform-origin: top left;
	transform-origin: top left;
	transition: 0.2s -webkit-transform ease-out;
	transition: 0.2s transform ease-out;
	transition: 0.2s transform ease-out, 0.2s -webkit-transform ease-out;
	will-change: transform;
	z-index: -1;
}

.coolBeans:hover::after {
	-webkit-transform: translate(0, 0);
	transform: translate(0, 0);
}

.coolBeans:hover {
	border: 2px solid transparent;
	color: #58D68D;
	-webkit-transform: scale(1.05);
	transform: scale(1.05);
	will-change: transform;
}
</style>
</head>

<body>
	<div class="beans">
		<div class="coolBeans" style="color: #58D68D;" onclick="showMore()">더보기</div>
	</div>

</body>

</html>
