<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<link href='/moahair/mainPage/fullcalendar/lib/fullcalendar.min.css'
	rel='stylesheet' />
<link
	href='/moahair/mainPage/fullcalendar/lib/fullcalendar.print.min.css'
	rel='stylesheet' media='print' />
<link href='/moahair/mainPage/fullcalendar/scheduler.min.css'
	rel='stylesheet' />
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src='/moahair/mainPage/fullcalendar/lib/moment.min.js'></script>
<!-- <script src='/moahair/mainPage/fullcalendar/lib/jquery.min.js'></script>
 -->
<script src='/moahair/mainPage/fullcalendar/lib/fullcalendar.min.js'></script>
<script src='/moahair/mainPage/fullcalendar/scheduler.min.js'></script>
<script src='/moahair/mainPage/fullcalendar/locale/ko.js'></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	var staff = [];
	var bookingEvent = [];

	<c:forEach var ="sc" items="${BookingList}" varStatus="i">
	<c:if test="${sc.bk_condition==0}">
	na = {
		id : '${sc.bk_s_num}',
		title : '${sc.bk_s_name}'
	};
	staff.push(na);
	</c:if>
	<c:if test="${sc.bk_condition==1}">
	na = {
		id : '${sc.bk_s_num}',
		title : '${sc.bk_s_name}',
		eventColor : 'green'
	};
	staff.push(na);
	</c:if>
	<c:if test="${sc.bk_condition==2}">
	na = {
		id : '${sc.bk_s_num}',
		title : '${sc.bk_s_name}',
		eventColor : 'red'
	};
	staff.push(na);
	</c:if>
	<c:if test="${sc.bk_condition==3}">
	na = {
		id : '${sc.bk_s_num}',
		title : '${sc.bk_s_name}',
		eventColor : 'orange'
	};
	staff.push(na);
	</c:if>
	<c:if test="${sc.bk_condition==4}">
	na = {
		id : '${sc.bk_s_num}',
		title : '${sc.bk_s_name}',
		eventColor : 'yellow'
	};
	staff.push(na);
	</c:if>
	/* na = {id:'${sc.bk_s_num}', title:'${sc.bk_s_name}',eventColor:'yellow'};
	 staff.push(na); */
	ev = {
		id : '${i.count}',
		resourceId : '${sc.bk_s_num}',
		start : '${sc.startTime}',
		end : '${sc.endTime}',
		title : '${sc.bk_i_name}'
	}
	bookingEvent.push(ev);
	</c:forEach>

	$(function() { // document ready

		$('#calendar').fullCalendar({
			defaultView : 'agendaDay',
			defaultDate : new Date(),
			editable : true,
			droppable : true,
			selectable : true,
			eventLimit : true, // allow "more" link when too many events
			header : {
				left : 'today',
				center : 'prev,title,next',
				right : 'agendaDay,agendaTwoDay,agendaWeek,month'
			},
			views : {
				agendaTwoDay : {
					type : 'agenda',
					duration : {
						days : 2
					},

					groupByResource : true

				}
			},

			resources : staff,

			events : bookingEvent,

			select : function(start, end, jsEvent, view, resource) {

				$('#myModal').modal('show')

			},
			dayClick : function() {

			},
			drop : function() {
				alert("Dropped on ");
			},
			eventClick : function(event, element) {

				event.title = "CLICKED!";

				$('#calendar').fullCalendar('updateEvent', event);

			}
		});

/* 		$('#calendar').fullCalendar('addResource', {
			id : 'e',
			title : 'Room E'
		}); */

	});
</script>
<style>
.fc-center h2 {
	display: inline-block;
	margin: 0 100px;
}

body {
	margin: 0;
	padding: 0;
}

#calendar {
	width: 90%;
	margin: 50px auto;
}

.modal-body {
	text-align: center;
}

.modal-option {
	display: inline-block;
	margin: 20px;
}

select {
	border: none;
	width: 200px;
	height: 50px;
}

input {
	width: 200px;
	height: 50px;
	font-size: 20px;
}

.modal-option span {
	font-weight: normal;
}

.modal-op1 {
	
}

.modal-op2 {
	display: inline-block;
}
</style>
</head>
<body>

	<div class="container">
		<!--            <h2>Modal Example</h2>
            Trigger the modal with a button
            <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button> -->

		<!-- Modal -->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">예약 등록</h4>
					</div>
					<div class="modal-body">

						<div class="modal-op2">
							<div class="modal-option">
								<label for="customer"> <span>손님명&nbsp;&nbsp;&nbsp;&nbsp;</span>
									<input type="text" name="customer" id="customer"
									placeholder="..."></label>
							</div>
						</div>

						<div class="modal-op2">

							<div class="modal-op1">
								<div class="modal-option">
									시작시간 <select class="startTime" name="">
										<option value="1">00:00</option>
										<option value="2">00:30</option>
										<option value="3">01:00</option>
										<option value="4">01:30</option>
										<option value="5">02:00</option>
										<option value="6">02:30</option>
										<option value="7">03:00</option>
										<option value="8">03:30</option>
										<option value="9">04:00</option>
										<option value="10">04:30</option>
										<option value="11">05:00</option>
										<option value="12">05:30</option>
										<option value="13">06:00</option>
										<option value="14">06:30</option>
										<option value="15">07:00</option>
										<option value="16">07:30</option>
										<option value="17">08:00</option>
										<option value="18">08:30</option>
										<option value="19">09:00</option>
										<option value="20">09:30</option>
										<option value="21">10:00</option>
										<option value="22">10:30</option>
										<option value="23">11:00</option>
										<option value="24">11:30</option>
										<option value="25">12:00</option>
										<option value="26">12:30</option>
										<option value="27">13:00</option>
										<option value="28">13:30</option>
										<option value="29">14:00</option>
										<option value="30">14:30</option>
										<option value="31">15:00</option>
										<option value="32">15:30</option>
										<option value="33">16:00</option>
										<option value="34">16:30</option>
										<option value="35">17:00</option>
										<option value="36">17:30</option>
										<option value="37">18:00</option>
										<option value="38">18:30</option>
										<option value="39">19:00</option>
										<option value="40">19:30</option>
										<option value="41">20:00</option>
										<option value="42">20:30</option>
										<option value="43">21:00</option>
										<option value="44">21:30</option>
										<option value="45">22:00</option>
										<option value="46">22:30</option>
										<option value="47">23:00</option>
										<option value="48">23:30</option>
									</select>
								</div>
								<div class="modal-option">
									디자이너

									<!-- 디자이너 선택 시 ajax 실행  -->
									<select class="staff" name="" id="s_name">
										<c:forEach var="dg" items="${itemList }" varStatus="i">
											<option value="${i.count }">${dg}</option>
										</c:forEach>
									</select>

								</div>
							</div>

							<div class="modal-op1">
								<div class="modal-option">
									서비스 <select class="service" name="" id="i_name">
										<c:forEach var="sg" items="${itemList }" varStatus="i">

											<option value="${i.count }">${sg }</option>
										</c:forEach>
									</select>
								</div>

								<div class="modal-option">
									필수옵션 <select class="e_option" name="" id="op1">
										<c:forEach var="og" items="${itemList }" varStatus="i">

											<option value="${i.count }">aaa</option>
										</c:forEach>

									</select>
								</div>
							</div>

							<div class="modal-op1">
								<div class="modal-option">
									옵션1 <select class="option1" name=""> 필수옵션
										<c:forEach var="og1" items="${itemList }" varStatus="i">

											<option value="${i.count }">aaa</option>
											</c:forEach>

									</select>
								</div>
								<div class="modal-option">
									옵션2 <select class="option2" name="">
										<option value="1">00:00</option>
										<option value="2">00:30</option>
										<option value="3">01:00</option>
										<option value="4">01:30</option>
									</select>
								</div>
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">등록</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					</div>
				</div>

			</div>
		</div>

	</div>



	<div id='calendar'></div>

	<script>
		$('#s_name').change(function() {
			var c = 0;
			var s_name = $('#s_name option:selected').val();
			$.ajax({
				type : "post",
				url : "/moahair/seller/scheduler.do",
				data : {
					count : c,
					s_name : s_name
				},
				success : function(data) {
					$('#i_name').html(data);
				}
			});
		})

		$('#i_name').change(function() {
			var c = 1;
			var i_name = $('#i_name option:selected').val();
			$.ajax({
				type : "post",
				url : "/moahair/seller/scheduler.do",
				data : {
					count : c,
					i_name : i_name
				},
				success : function(data) {
					$('#op1').html(data);
				}
			});
		})
		$('#op1').change(function() {
			var c = 2;
			var dg_name = $('#op1 option:selected').val();
			$.ajax({
				type : "post",
				url : "/moahair/seller/scheduler.do",
				data : {
					count : c,
					dg_name : dg_name
				},
				success : function(data) {
					$('#op1').html(data);
				}
			});
		})
	</script>

</body>
</html>

