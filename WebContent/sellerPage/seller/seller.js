function writeDesignerSave(){
	
	var scheForm = eval("document.schedule");
	
	if(scheForm.s_name.value==""){
		  alert("이름을 등록해주세요!"); 
		  scheForm.s_name.focus();
		  return false;  
	}
	if(scheForm.s_title.value==""){
		  alert("직함을 등록해주세요!");
		  scheForm.s_title.focus(); 
		  return false;
	}
	if(scheForm.s_profile.value==""){
		  alert("프로필사진을 등록해주세요!");
		  scheForm.s_profile.focus();
		  return false;
	}
	if(scheForm.s_background.value==""){
		  alert("배경사진을 등록해주세요!");
		  scheForm.s_background.focus();
		  return false;
	}
	if(scheForm.s_holiday.value==""){
		  alert("정기휴일을 등록해주세요!");
		  scheForm.s_holiday.focus();
		  return false;
	}
}

function writeSave(){
	var writeform = eval("document.itemwriteform");
	
	if(writeform.i_name.value==""){
	  alert("상품명을 등록해주세요!");
	  writeform.i_name.focus();
	  return false;
	}
	
	if(writeform.i_thumbnail.value==""){
		alert("상품사진을 등록해주세요!");
		writeform.i_thumbnail.focus();
		return false;
	}
	
	if(writeform.i_s_num.value==""){
		alert("디자이너를 선택해주세요!");
		writeform.i_s_num.focus();
		return false;
	}
	
	
	if(writeform.i_price.value==""){
		alert("상품가격을 등록해주세요!");
		writeform.i_price.focus();
		return false;
	}
	
	submitContents(writeform);
}

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

//textArea에 이미지 첨부
function pasteHTML(filepath){
    var sHTML = '<img src="<%=request.getContextPath()%>/sellerPage/se_editor/tmp/'+filepath+'">';
    oEditors.getById["textAreaContent"].exec("PASTE_HTML", [sHTML]); 
}
