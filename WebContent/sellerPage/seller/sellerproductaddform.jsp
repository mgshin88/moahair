<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>판매자 관리 페이지 :: 상품등록</title>
</head>
<body>
	<script type="text/javascript">
	//옵션에 선택지 추가 삭제 함수
	var order = [2, 1, 1];
	var sel = [1, 1, 1];
	
    function addForm(index) {
        
    	if (order[index] > 5) {
            alert("선택지는 5개까지만 이용가능합니다 ");
        } else {
            var addedFormTr = document.getElementById("option"+index);

            var str = "<td>";
            str += "선택지 "+ order[index] + " <input type='text' name='op"+index+"_" + order[index] + "_" + (sel[index]) + "' placeholder='선택지이름'/></td>";
            str += "<td><input type='text' name='op"+index+"_" + order[index] + "_" + (++sel[index]) + "' placeholder='추가가격'/></td>";
            str += "<td><select name='op"+index+"_" + order[index] + "_" + (++sel[index]) + "'><option value='0'>시간선택</option><option value='1'>30</option><option value='2'>60</option><option value='3'>90</option><option value='4'>120</option></select></td><BR>";

            // 추가할 폼(에 들어갈 HTML)
            var addedTr = document.createElement("tr"); // 폼 생성
            addedTr.id = "added_"+index+"_"+ order[index]; // 폼 Div에 ID 부여 (삭제를 위해)
            addedTr.innerHTML = str; // 폼 Div안에 HTML삽입
            addedFormTr.appendChild(addedTr); // 삽입할 DIV에 생성한 폼 삽입
            
            order[index]++;
            sel[index] = 1;
            document.baseForm.order[index].value = order[index];
        }
    }

    function delForm(index) {
        console.log(order[index]);
        var addedFormTr = document.getElementById("option"+index);

        if (order[index] > 2) { // 현재 폼이 두개 이상이면
            var addedTr = document.getElementById("added_"+index+"_" + (--order[index]));
            // 마지막으로 생성된 폼의 ID를 통해 Div객체를 가져옴

            addedFormTr.removeChild(addedTr); // 폼 삭제 
        } else if (order[index] == 2) {
            alert("선택지는 최소 1개");
        }
    }
    function handleChange(checkbox) {
        if(checkbox.checked == true){
        	var td1 = checkbox.parentNode;
        	
        	var td2 = td1.nextSibling.nextSibling;
        	
        	var text = td2.firstChild;
        	
            text.removeAttribute("disabled");
           console.log(text);
            var text2 = text.nextSibling.nextSibling;
            text2.removeAttribute("disabled");
            console.log(text2);
            
        }else{
    		var td1 = checkbox.parentNode;
        	
        	var td2 = td1.nextSibling.nextSibling;
        	
        	var text = td2.firstChild;
            text.setAttribute("disabled", "disabled");
            console.log(text);
            var text2 = text.nextSibling.nextSibling;
            text2.setAttribute("disabled", "disabled");
            console.log(text2);
            var table = text2.nextSibling.nextSibling;
            $(table).empty();
			if(table.id == "option1"){
				order[1] = 1;
				sel[1] = 1;
			}
			if(table.id == "option2"){
				order[2] = 1;
				sel[2] = 1;
			}
          
       }
    }
    
    
function itemaddcel(bs_num){
	   $.ajax({
	      type : "post" ,  url : "/moahair/seller/sellerproductaddcel.do",
	      data : {
	    	  bs_num : '${ bs_num}'   
	      },
	      success : function(data){
	         $("#sellerContents").html(data);
	      }
	   });
}

function golist(pageNum){
	$.ajax({
	      type : "post" ,  url : "/moahair/seller/sellerviewlist.do",
	    	 data : {
	    		 pageNum:pageNum,
		    	  bs_num : '${ bs_num}',
		    	  i_num : '${i_num}'
		      },
	      success : function(data){ 
	         $("#sellerContents").html(data);
	      }
	   });
}

</script>

	<h2>상품등록</h2>

	<%--상품등록하고나서 판매자의 상품리스트를 보여주자 --%>
	<form action="/moahair/seller/sellerproductaddpro.do"
		name="itemwriteform" method="post" enctype="multipart/form-data"
		onsubmit="return writeSave()">
		<input type="hidden" name="m_id" value="${m_id }" /> <input
			type="hidden" name="i_bs_num" value="${bs_num}" />
		<table width="70%" style="margin:0 auto;">
			<tr>
				<td>*스타일종류</td>
				<td><select name="i_type">
						<option value="커트">커트</option>
						<option value="펌">펌</option>
						<option value="염색">염색</option>
						<option value="익스텐션">익스텐션</option>
						<option value="스타일링">스타일링</option>
						<option value="케어">케어</option>
				</select></td>
			</tr>
			<tr>
				<td>*성별</td>
				<td><select name="i_gender">
						<option value="">선택</option>
						<option value="1">여자</option>
						<option value="2">남자</option>
				</select></td>
			</tr>
			<tr>
				<td>*상품명</td>
				<td><input type="text" name="i_name" /></td>
			</tr>
			<tr>
				<td>*상품사진</td>
				<td><input type="file" name="i_thumbnail" /></td>
			</tr>
			<tr>
				<td>*디자이너등록</td>
				<td><select name="i_s_num">
						<option value="">디자이너선택</option>
						<option value="-1">All</option>
						<c:forEach var="item" items="${staffNameList }">
							<option value="${item.s_num}">${ item.s_name }</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>*설명</td>
				<td><textarea name="i_contents" id="textAreaContent"
						style="width: 100%" rows="10" cols="80"></textarea></td>
			</tr>
			<tr>
				<td>*상품가격</td>
				<td><input type="text" name="i_price" /></td>
			</tr>
			<tr>
				<td>*상품소요시간</td>
				<td><select name="i_duration">
						<option value="0">시간선택</option>
						<option value="1">30</option>
						<option value="2">60</option>
						<option value="3">90</option>
						<option value="4">120</option>
				</select>분</td>
			</tr>
			<tr>
				<td colspan="2"><br/><br/>아래는 <b>옵션 선택지</b> 입력부분입니다. <b>옵션</b>은 최대 <b>3개</b>, 각 옵션 당 <b>선택지</b>는 최대 <b>5개</b> 입니다. </td>
			</tr>
			<tr>
				<td>*필수옵션</td>
				<td>
					<input type="Button" class="AddBtn" value="추가" onclick="addForm(0)" />
					<input type="Button" class="AddBtn" value="삭제" onclick="delForm(0)" />
					<table id="option0">
						<tr>
							<td>선택지 1 <input type="text" name="op0_1_1" placeholder="선택지이름"/></td>
							<td><input type="text" name="op0_1_2" placeholder="추가가격"/></td>
							<td><select name="op0_1_3">
									<option value="0">시간선택</option>
									<option value="1">30</option>
									<option value="2">60</option>
									<option value="3">90</option>
									<option value="4">120</option>
							</select></td>
						</tr>
					</table> 
				</td>

			</tr>
			<tr>
				<td><input type="checkbox" onchange="handleChange(this);" />*추가옵션1</td>
				<td><input type="Button" class="AddBtn" value="추가" onclick="addForm(1)" disabled />
				<input type="Button" class="AddBtn" value="삭제" onclick="delForm(1)" disabled/>
				<table id="option1"></table></td>
			</tr>
			<tr>
				<td><input type="checkbox" onchange="handleChange(this);" />*추가옵션2</td>
				<td><input type="Button" class="AddBtn" value="추가" onclick="addForm(2)" disabled />
				<input type="Button" class="AddBtn" value="삭제" onclick="delForm(2)" disabled/>
				<table id="option2"></table></td>
			</tr>
		</table>

		<input type="submit" class="AddBtn" value="등록" /> <input type="button" value="목록" class="AddBtn" 
			onclick="golist(${pageNum})" /> <input type="button" value="취소" class="AddBtn" 
			onclick="itemaddcel(${businessNum})" />
	</form>


	<script type="text/javascript">
 
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors,
    elPlaceHolder: "textAreaContent",
    sSkinURI: "<%=request.getContextPath()%>/sellerPage/se_editor/SmartEditor2Skin.html",
    fCreator: "createSEditor2"
});

function submitContents(elClickedObj) {
    // 에디터의 내용이 textarea에 적용된다.
    oEditors.getById["textAreaContent"].exec("UPDATE_CONTENTS_FIELD", [ ]);
 
    // 에디터의 내용에 대한 값 검증은 이곳에서
    // document.getElementById("textAreaContent").value를 이용해서 처리한다.
  
    try {
        elClickedObj.form.submit();
    } catch(e) {
     
    }
   
}
 
// textArea에 이미지 첨부
function pasteHTML(filepath){
    var sHTML = '<img src="<%=request.getContextPath()%>/sellerPage/se_editor/tmp/'+filepath+'">';
    oEditors.getById["textAreaContent"].exec("PASTE_HTML", [sHTML]); 
}

</script>

</body>
</html>