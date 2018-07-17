<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionScope.memId == null }">
		<script>
			alert("로그인 후 가능합니다.");
			window.location = "/moahair/member/loginForm.do";
		</script>
	</c:if>

<script>
	function wishDelete() {
		var w_num = $("#w_num").val();
		$.ajax ({
			type : "post",
			url : "/moahair/mypage/WishDelete.do",
			data : {w_num : w_num},
			success : function(data) {
				$("#result").html(data);
			}
			
		})
	}
	function wishSelecter(num){
		$.ajax ({
			type : "post",
			url : "/moahair/mypage/WishSelecter.do",
			data : {num : num},
			success : function(data) {
				$("#myPageWishlist").html(data);
			}
		})		
		
		

	}
</script>

<div class="logregHeader">
	<div class="logregTitle">찜 목록</div>
</div>


<div class="wishlist_all">
	<div class="wishlist_linebutton">
		<select class="linebutton_select" name="linebutton_select" onchange="wishSelecter(this.value)">
			<option value="1">전체 보기</option>
			<option value="2">디자이너만 보기</option>
			<option value="3">판매자만 보기</option>
			<option value="4">상품만 보기</option>
		</select>
	</div>
	<ul id="myPageWishlist" class="wishListGroup">
		<li class="headerInfo">
			<label class="wishcell2">번호</label>
			<label class="wishcell3">이미지</label>
			<label class="wishcell4">이름</label>
			<label class="wishcell5">구분</label>
			<label class="wishcell1" for="seller0">삭제</label>
			
		</li>
		
		<c:forEach var = "mdto" items="${mdto }" varStatus="number">
			<c:if test="${mdto.designer_name != null }">
			
		<li class="itemInfo designerWish">
			<input id="w_num" type="hidden" value="${mdto.w_num }">
			
			<div class="wishcell2">${number.count }</div>
			<div class="wishcell3">
				<a href="/moahair/seller/DesignerView.do?s_num=${mdto.designer_num }"> 
					<img class="wishimg" src="/moahair/img/seller/staff/${mdto.designer_profile }">
				</a>
			</div>
			<div class="wishcell4">
				<a href="/moahair/seller/DesignerView.do?s_num=${mdto.designer_num }"> 
					${mdto.designer_name }
				</a>
			</div>
			<div class="wishcell5">디자이너 찜 목록</div>
			<div class="wishcell1">
				<span class="deletebutton" onclick="wishDelete()"> X </span>
			</div>
		</li>
		</c:if>
		
			<c:if test="${mdto.design_name != null }">
			
		<li class="itemInfo itemWish">
		<input id="w_num" type="hidden" value="${mdto.w_num }">
			
			<div class="wishcell2">${number.count }</div>
			<div class="wishcell3">
				<a href="/moahair/product/item/ProductView.do?i_num=${mdto.design_num }">
					<img class="wishimg" src="/moahair/img/item/thumbnail/${mdto.designer_profile }">
				</a>
			</div>
			
			<div class="wishcell4">
				<a href="/moahair/product/item/ProductView.do?i_num=${mdto.design_num }">
					${mdto.design_name }
				</a>
				</div>
			<div class="wishcell5">상품 찜 목록</div>
			<div class="wishcell1">
				<span class="deletebutton" onclick="wishDelete()"> X </span>
			</div>
		</li>
		</c:if>
		
		<c:if test="${mdto.shop_name != null }">
			
		<li class="itemInfo SellerWish">
		<input id="w_num" type="hidden" value="${mdto.w_num }">
			
			<div class="wishcell2">${number.count }</div>
			<div class="wishcell3">
				<a href="/moahair/seller/SellerView.do?bs_num=${mdto.shop_num }">
					<img class="wishimg" src="/moahair/img/seller/business/thumbnail/${mdto.shop_profile }">		
				</a>
			</div>
			<div class="wishcell4">
				<a href="/moahair/seller/SellerView.do?bs_num=${mdto.shop_num }">
					${mdto.shop_name }
				</a>
			</div>
			<div class="wishcell5">샵 찜 목록</div>
			<div class="wishcell1">
				<span class="deletebutton" onclick="wishDelete()"> X </span>
			</div>
		</li>
		</c:if>
		</c:forEach>
		
	</ul>
	
	<div id="result"></div>
</div>
