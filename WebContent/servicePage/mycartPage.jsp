<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:if test="${sessionScope.memId == null }">
		<script>
			alert("로그인 후 가능합니다.");
			window.location = "/moahair/member/loginForm.do";
		</script>
	</c:if>

<script>
$( function() {
	var clc = ${clc};
    $('#allcheck').click(function() {
        if( $(this).is(":checked") ) {
        	for(var i=0;i<clc;i++) {
            	$('#cartcheck'+i).prop("checked",true);
        	}
        }
        else {
        	for(var i=0;i<clc;i++) {
        		$('#cartcheck'+i).prop("checked",false);
    		}
        }
    });
})

function cartSelectDelete() {
	var clc = ${clc};
	for(var i=0;i<clc;i++) {
		if($("#cartcheck"+i).is(":checked")) {
			var c_num = $("#cartcheck"+i).val();
			
			$.ajax ({
				type : "post",
				url : "/moahair/mypage/cartDelete.do",
				data : {c_num : c_num},
				success : function(data) {
					$("#result").html(data);
				}
				
			})
		}
	}
}

function cartDelete() {
	var c_num = $("#c_num").val();
	$.ajax ({
		type : "post",
		url : "/moahair/mypage/cartDelete.do",
		data : {c_num : c_num},
		success : function(data) {
			$("#result").html(data);
		}
		
	})
}
</script>

<div class="logregHeader">
	<div class="logregTitle">장바구니</div>
</div>

<div class="wishlist_all">
	<ul id="myPageCart" class="cartGroup">
		<li class="headerInfo">
			<label class="cartcell1"><input type="checkbox" id="allcheck" ></label>
			<label class="cartcell2">번호</label>
			<label class="cartcell3">이미지</label>
			<label class="cartcell4">상품명</label>
			<label class="cartcell5">기장</label>
			<label class="cartcell6">옵션1</label>
			<label class="cartcell7">옵션2</label>	
			<label class="cartcell8">가격</label>
			<label class="cartcell9" for="seller0">삭제</label>
		</li>
		
		<c:forEach var = "cart" items="${cart }" varStatus="number">
			
		<li class="itemInfo">
			<input id="c_num" type="hidden" value="${cart.c_num }">
			<div class="cartcell1"><input type="checkbox" id="cartcheck${number.index }" value="${cart.c_num }"> </div>
			<div class="cartcell2">${number.count }</div>
			<div class="cartcell3"><a href="/moahair/product/item/ProductView.do?i_num=${cart.c_i_num }"> <img class="wishimg" src="/moahair/img/item/thumbnail/${cart.c_thumnail}"></a></div>
			<div class="cartcell4"><a href="/moahair/product/item/ProductView.do?i_num=${cart.c_i_num }"> ${cart.c_name }</a></div>
			<div class="cartcell5">${cart.c_option }</div>
			<div class="cartcell6">${cart.c_option_sel1 }</div>
			<div class="cartcell7">${cart.c_option_sel2 }</div>
			<div class="cartcell8">
			<fmt:formatNumber value="${cart.c_price }" pattern="#,###원"/>
			</div>
			<div class="cartcell9">
				<span class="deletebutton" onclick="cartDelete()"> X </span>
			</div>
			
		</li>
		
		
		</c:forEach>
		
	</ul>
	
	<div id="result"></div>
</div>

<input type="button" class="cartbtnWhite" value="선택삭제" onclick="cartSelectDelete()">

