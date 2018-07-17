<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
</head>
<body>
<c:if test="${m_level != 10 }">
		<script>
			alert("접근이 불가능한 페이지 입니다.");
			window.location = "/moahair/main/main.do";
			</script>
	</c:if>

	<script type="text/javascript">

//옵션에 선택지 추가 삭제 함수
var order = [6, 6, 6];
var sel = [1, 1, 1];

function addForm(index) {
    
   if (order[index] > 5) {
        alert("선택지는 5개까지만 이용가능합니다 ");
    } else {
        var addedFormTr = document.getElementById("option"+index);

        var str = "<td>";
        str += order[index] + " <input type='text' name='op"+index+"_" + order[index] + "_" + (sel[index]) + "' /></td>";
        str += "<td><input type='text' name='op"+index+"_" + order[index] + "_" + (++sel[index]) + "' /></td>";
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
function updateItemCel(pageNum){
      $.ajax({
         type : "post" ,  url : "/moahair/supervisor/ItemList.do",
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
<h2>상품수정</h2>

<%--상품등록하고나서 판매자의 상품리스트를 보여주자 --%>
<form action="/moahair/supervisor/AdminProductUpdatePro.do" name="itemwriteform" method="post" enctype="multipart/form-data">
   <input type="hidden" name="op0_io_num" value="${op0.io_num }"   />
   <input type="hidden" name="op1_io_num" value="${op1.io_num }"   />
   <input type="hidden" name="op2_io_num" value="${op2.io_num }"   />
   <input type="hidden" name="i_bs_num" value="${bs_num}"/>
   <input type="hidden" name="i_num" value="${i_num}"/>
   <input type="hidden" name="pageNum" value="${pageNum}"/>
   <table width="70%" style="margin:0 auto;">
      <tr>
         <td>*스타일종류</td>
         <td>
            <select name="i_type">
               <option value="" ${item.i_type eq null ? 'selected' : ''}>선택</option>
               <option value="커트" ${item.i_type eq '커트' ? 'selected' : ''}>커트</option>
               <option value="펌" ${item.i_type eq '펌' ? 'selected' : ''}>펌</option>
               <option value="염색" ${item.i_type eq '염색' ? 'selected' : ''}>염색</option>
               <option value="익스텐션" ${item.i_type eq '익스텐션' ? 'selected' : ''}>익스텐션</option>
               <option value="스타일링" ${item.i_type eq '스타일링' ? 'selected' : ''}>스타일링</option>
               <option value="케어" ${item.i_type eq '케어' ? 'selected' : ''}>케어</option>
            </select>
         </td>
      </tr>
      <tr>
         <td>*성별</td>
         <td>
            <select name="i_gender" >
               <option value="" ${item.i_gender == null ? 'selected' : ''}>선택</option>
               <option value="1" ${item.i_gender == 1 ? 'selected' : ''}>여자</option>
               <option value="2" ${item.i_gender == 2 ? 'selected' : ''}>남자</option>
            </select>
         </td>
      </tr>
      <tr>
         <td>*상품명</td>
         <td><input type="text" name="i_name" value="${item.i_name}"/></td>
      </tr>
      <tr>
         <td rowspan="2">*상품사진</td>
         <td>현재이미지   <img src="/moahair/img/item/thumbnail/${item.i_thumbnail}" width="50" /></td>
         
      </tr>
      <tr>
         <td>
            <input type="file" name="i_thumbnail" />
            <input type="hidden" name="orgImage" value="${item.i_thumbnail}" />
         </td>
      </tr>
      <tr>
         <td>*디자이너등록</td>
         <td>
            <select name="i_s_num" >
               <option value="" ${item.i_s_num == null ? 'selected' : ''}>디자이너선택</option>
               <option value="-1" ${item.i_s_num == -1 ? 'selected' : ''}>All</option>
               <c:forEach var="staff" items="${staffNameList }">
                  <option value="${staff.s_num}" ${item.i_s_num == staff.s_num ? 'selected' : ''}>${staff.s_name }</option>
               </c:forEach> 
            </select>
         </td>
      </tr>
      <tr>
         <td>*설명</td>
         <td>
            <textarea name="i_contents" id="textAreaContent" style="width: 100%" rows="10" cols="80" >${item.i_contents}</textarea>
         </td>
      </tr>
      <tr>
         <td>*상품가격</td>
         <td><input type="text" name="i_price" value="${item.i_price }"/></td>
      </tr>
      <tr>
            <td colspan="2"><br/><br/>아래는 <b>옵션 선택지</b> 입력부분입니다. <b>옵션</b>은 최대 <b>3개</b>, 각 옵션 당 <b>선택지</b>는 최대 <b>5개</b> 입니다.<br/><b>사용하지 않으시면 빈칸</b>으로 놔두시면 됩니다.</td>
      </tr>
      <tr>
         <td>*상품소요시간</td>
         <td>
            <select name="i_duration">
               <option value="0" ${item.i_duration == 0 ? 'selected' : ''}>선택</option>
               <option value="1" ${item.i_duration == 1 ? 'selected' : ''}>30</option>
               <option value="2" ${item.i_duration == 2 ? 'selected' : ''}>60</option>
               <option value="3" ${item.i_duration == 3 ? 'selected' : ''}>90</option>
               <option value="4" ${item.i_duration == 4 ? 'selected' : ''}>120</option>
            </select>분
         </td>
      </tr>
      <tr>
         <td>*필수옵션</td>
         <td>
               <table id="option0">
                  <tr>
                     <td>1 <input type="text" name="op0_1_1" value="${op0_name[0]}"></td>
                     <td><input type="text" name="op0_1_2" value="${op0_price[0]}"></td>
                     <td><select name="op0_1_3">
                           <option value="0" ${op0_dur[0] == 0 ? 'selected' : ''}>시간선택</option>
                           <option value="1" ${op0_dur[0] == 1 ? 'selected' : ''}>30</option>
                           <option value="2" ${op0_dur[0] == 2 ? 'selected' : ''}>60</option>
                           <option value="3" ${op0_dur[0] == 3 ? 'selected' : ''}>90</option>
                           <option value="4" ${op0_dur[0] == 4 ? 'selected' : ''}>120</option>
                     </select></td>
                  </tr>
                  <tr id="added_0_2">
                     <td>2 <input type="text" name="op0_2_1" value="${op0_name[1]}"></td>
                     <td><input type="text" name="op0_2_2" value="${op0_price[1]}"></td>
                     <td><select name="op0_2_3">
                        <option value="0" ${op0_dur[1] == 0 ? 'selected' : ''}>시간선택</option>
                        <option value="1" ${op0_dur[1] == 1 ? 'selected' : ''}>30</option>
                        <option value="2" ${op0_dur[1] == 2 ? 'selected' : ''}>60</option>
                        <option value="3" ${op0_dur[1] == 3 ? 'selected' : ''}>90</option>
                        <option value="4" ${op0_dur[1] == 4 ? 'selected' : ''}>120</option>
                     </select></td>
                  </tr>
                  <tr id="added_0_3">
                     <td>3 <input type="text" name="op0_3_1" value="${op0_name[2]}"></td>
                     <td><input type="text" name="op0_3_2" value="${op0_price[2]}"></td>
                     <td><select name="op0_3_3">
                        <option value="0" ${op0_dur[2] == 0 ? 'selected' : ''}>시간선택</option>
                        <option value="1" ${op0_dur[2] == 1 ? 'selected' : ''}>30</option>
                        <option value="2" ${op0_dur[2] == 2 ? 'selected' : ''}>60</option>
                        <option value="3" ${op0_dur[2] == 3 ? 'selected' : ''}>90</option>
                        <option value="4" ${op0_dur[2] == 4 ? 'selected' : ''}>120</option>
                     </select></td>
                  </tr>
                  <tr id="added_0_4">
                     <td>4 <input type="text" name="op0_4_1" value="${op0_name[3]}"></td>
                     <td><input type="text" name="op0_4_2" value="${op0_price[3]}"></td>
                     <td><select name="op0_4_3">
                        <option value="0" ${op0_dur[3] == 0 ? 'selected' : ''}>시간선택</option>
                        <option value="1" ${op0_dur[3] == 1 ? 'selected' : ''}>30</option>
                        <option value="2" ${op0_dur[3] == 2 ? 'selected' : ''}>60</option>
                        <option value="3" ${op0_dur[3] == 3 ? 'selected' : ''}>90</option>
                        <option value="4" ${op0_dur[3] == 4 ? 'selected' : ''}>120</option>
                     </select></td></tr>
                  <tr id="added_0_5">
                  <td>5 <input type="text" name="op0_5_1" value="${op0_name[4]}"></td>
                  <td><input type="text" name="op0_5_2" value="${op0_price[4]}"></td>
                  <td><select name="op0_5_3">
                     <option value="0" ${op0_dur[4] == 0 ? 'selected' : ''}>시간선택</option>
                     <option value="1" ${op0_dur[4] == 1 ? 'selected' : ''}>30</option>
                     <option value="2" ${op0_dur[4] == 2 ? 'selected' : ''}>60</option>
                     <option value="3" ${op0_dur[4] == 3 ? 'selected' : ''}>90</option>
                     <option value="4" ${op0_dur[4] == 4 ? 'selected' : ''}>120</option>
                  </select></td></tr></table> 
            </td>
      </tr>
      <tr>
         <td>*추가옵션1</td>
         <td>
            
            <table id="option1">
               <tr id="added_1_1">
                  <td>1 <input type="text" name="op1_1_1" value="${op1_name[0]}"></td>
                  <td><input type="text" name="op1_1_2" value="${op1_price[0]}"></td>
                  <td><select name="op1_1_3">
                     <option value="0" ${op1_dur[0] == 0 ? 'selected' : ''}>시간선택</option>
                     <option value="1" ${op1_dur[0] == 1 ? 'selected' : ''}>30</option>
                     <option value="2" ${op1_dur[0] == 2 ? 'selected' : ''}>60</option>
                     <option value="3" ${op1_dur[0] == 3 ? 'selected' : ''}>90</option>
                     <option value="4" ${op1_dur[0] == 4 ? 'selected' : ''}>120</option>
                     </select></td>
               </tr><tr id="added_1_2">
                  <td>2 <input type="text" name="op1_2_1" value="${op1_name[1]}"></td>
                  <td><input type="text" name="op1_2_2" value="${op1_price[1]}"></td>
                  <td><select name="op1_2_3">
                     <option value="0" ${op1_dur[1] == 0 ? 'selected' : ''}>시간선택</option>
                     <option value="1" ${op1_dur[1] == 1 ? 'selected' : ''}>30</option>
                     <option value="2" ${op1_dur[1] == 2 ? 'selected' : ''}>60</option>
                     <option value="3" ${op1_dur[1] == 3 ? 'selected' : ''}>90</option>
                     <option value="4" ${op1_dur[1] == 4 ? 'selected' : ''}>120</option>
                     </select></td></tr>
               <tr id="added_1_3">
                  <td>3 <input type="text" name="op1_3_1" value="${op1_name[2]}"></td>
                  <td><input type="text" name="op1_3_2" value="${op1_price[2]}"></td>
                  <td><select name="op1_3_3">
                     <option value="0" ${op1_dur[2] == 0 ? 'selected' : ''}>시간선택</option>
                     <option value="1" ${op1_dur[2] == 1 ? 'selected' : ''}>30</option>
                     <option value="2" ${op1_dur[2] == 2 ? 'selected' : ''}>60</option>
                     <option value="3" ${op1_dur[2] == 3 ? 'selected' : ''}>90</option>
                     <option value="4" ${op1_dur[2] == 4 ? 'selected' : ''}>120</option>
                     </select></td></tr>
               <tr id="added_1_4">
               <td>4 <input type="text" name="op1_4_1" value="${op1_name[3]}"></td>
               <td><input type="text" name="op1_4_2" value="${op1_price[3]}"></td>
               <td><select name="op1_4_3">
                  <option value="0" ${op1_dur[3] == 0 ? 'selected' : ''}>시간선택</option>
                  <option value="1" ${op1_dur[3] == 1 ? 'selected' : ''}>30</option>
                  <option value="2" ${op1_dur[3] == 2 ? 'selected' : ''}>60</option>
                  <option value="3" ${op1_dur[3] == 3 ? 'selected' : ''}>90</option>
                  <option value="4" ${op1_dur[3] == 4 ? 'selected' : ''}>120</option>
                  </select></td></tr>
               <tr id="added_1_5">
               <td>5 <input type="text" name="op1_5_1" value="${op1_name[4]}"></td>
               <td><input type="text" name="op1_5_2" value="${op1_price[4]}"></td>
               <td><select name="op1_5_3">
                  <option value="0" ${op1_dur[4] == 0 ? 'selected' : ''}>시간선택</option>
                  <option value="1" ${op1_dur[4] == 1 ? 'selected' : ''}>30</option>
                  <option value="2" ${op1_dur[4] == 2 ? 'selected' : ''}>60</option>
                  <option value="3" ${op1_dur[4] == 3 ? 'selected' : ''}>90</option>
                  <option value="4" ${op1_dur[4] == 4 ? 'selected' : ''}>120</option>
                  </select></td></tr></table></td>
      </tr>
      <tr>
         <td>*추가옵션2</td>
         <td>
            
            <table id="option2">
               <tr id="added_2_1">
               <td>1 <input type="text" name="op2_1_1" value="${op2_name[0]}"></td>
               <td><input type="text" name="op2_1_2" value="${op2_price[0]}"></td>
               <td><select name="op2_1_3">
                  <option value="0" ${op2_dur[0] == 0 ? 'selected' : ''}>시간선택</option>
                  <option value="1" ${op2_dur[0] == 1 ? 'selected' : ''}>30</option>
                  <option value="2" ${op2_dur[0] == 2 ? 'selected' : ''}>60</option>
                  <option value="3" ${op2_dur[0] == 3 ? 'selected' : ''}>90</option>
                  <option value="4" ${op2_dur[0] == 4 ? 'selected' : ''}>120</option>
                  </select></td></tr>
               <tr id="added_2_2">
               <td>2 <input type="text" name="op2_2_1" value="${op2_name[1]}"></td>
               <td><input type="text" name="op2_2_2" value="${op2_price[1]}"></td>
               <td><select name="op2_2_3">
                  <option value="0" ${op2_dur[1] == 0 ? 'selected' : ''}>시간선택</option>
                  <option value="1" ${op2_dur[1] == 1 ? 'selected' : ''}>30</option>
                  <option value="2" ${op2_dur[1] == 2 ? 'selected' : ''}>60</option>
                  <option value="3" ${op2_dur[1] == 3 ? 'selected' : ''}>90</option>
                  <option value="4" ${op2_dur[1] == 4 ? 'selected' : ''}>120</option>
                  </select></td></tr>
               <tr id="added_2_3">
               <td>3 <input type="text" name="op2_3_1" value="${op2_name[2]}"></td>
               <td><input type="text" name="op2_3_2" value="${op2_price[2]}"></td>
               <td><select name="op2_3_3">
                  <option value="0" ${op2_dur[2] == 0 ? 'selected' : ''}>시간선택</option>
                  <option value="1" ${op2_dur[2] == 1 ? 'selected' : ''}>30</option>
                  <option value="2" ${op2_dur[2] == 2 ? 'selected' : ''}>60</option>
                  <option value="3" ${op2_dur[2] == 3 ? 'selected' : ''}>90</option>
                  <option value="4" ${op2_dur[2] == 4 ? 'selected' : ''}>120</option>
                  </select></td></tr>
               <tr id="added_2_4">
               <td>4 <input type="text" name="op2_4_1" value="${op2_name[3]}"></td>
               <td><input type="text" name="op2_4_2" value="${op2_price[3]}"></td>
               <td><select name="op2_4_3">
                  <option value="0" ${op2_dur[3] == 0 ? 'selected' : ''}>시간선택</option>
                  <option value="1" ${op2_dur[3] == 1 ? 'selected' : ''}>30</option>
                  <option value="2" ${op2_dur[3] == 2 ? 'selected' : ''}>60</option>
                  <option value="3" ${op2_dur[3] == 3 ? 'selected' : ''}>90</option>
                  <option value="4" ${op2_dur[3] == 4 ? 'selected' : ''}>120</option>
                  </select></td></tr>
               <tr id="added_2_5">
               <td>5 <input type="text" name="op2_5_1" value="${op2_name[4]}"></td>
               <td><input type="text" name="op2_5_2" value="${op2_price[4]}"></td>
               <td><select name="op2_5_3">
                  <option value="0" ${op2_dur[4] == 0 ? 'selected' : ''}>시간선택</option>
                  <option value="1" ${op2_dur[4] == 1 ? 'selected' : ''}>30</option>
                  <option value="2" ${op2_dur[4] == 2 ? 'selected' : ''}>60</option>
                  <option value="3" ${op2_dur[4] == 3 ? 'selected' : ''}>90</option>
                  <option value="4" ${op2_dur[4] == 4 ? 'selected' : ''}>120</option>
                  </select></td></tr></table></td>
      </tr>
   </table>
   
   <input type="submit" value="수정" onclick="submitContents(this)"    />
   <input type="button" value="목록" onclick="updateItemCel(${pageNum})"/>
   <input type="reset" value="취소" />
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