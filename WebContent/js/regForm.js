function checkItlogin() {
	var myform = eval("document.myform");
	if($("#m_id").val() == "") {
		alert("아이디를 입력하세요");
		$("#m_id").focus();
		return false;
	}

	if($("#m_pw").val() == "") {
		alert("비밀번호를 입력하세요");
		$("#m_pw").focus();
		return false;
	}
	/*
	if(!myform.email.value){
		alert("아이디를 입력하세요");
		myform.email.focus();
		return false;
	}
	if(!myform.pw.value){
		alert("비밀번호를 입력하세요.");
		myform.pw.focus();
		return false;
	}
	 */
}

//inputForm.jsp 유효성 검사
function checkIt() {
	var userinput = eval("document.userinput");
	if($("#rg_id").val() == "") {
		alert("아이디를 입력하세요");
		$("#rg_id").focus();
		return false;
	}
	
	if(userinput.idDuplication.value != "idCheck"){
        alert("아이디 중복체크를 해주세요.");
        return false;
    }

	if($("#rg_pw").val() == "") {
		alert("비밀번호를 입력하세요");
		$("#rg_pw").focus();
		return false;
	}

	if($("#rg_pw").val() != $("#rg_pw2").val()) {
		alert("비밀번호를 동일하게 입력하세요");
		$("#rg_pw").focus();
		return false;
	}

	if($("#m_name").val() == "") {
		alert("이름을 입력하세요.");
		$("#m_name").focus();
		return false;
	}

	if($("#m_phone").val() == "") {
		alert("핸드폰 번호를 입력하세요.");
		$("#m_phone").focus();
		return false;
	}

	if($("#m_address").val() == "") {
		alert("주소를 입력하세요.");
		$("#m_address").focus();
		return false;
	}
}

//아이디 중복 여부를 판단
function openConfirmid(userinput) {
	var userinput = eval("document.userinput");
	// 아이디를 입력했는지 검사
	if($("#rg_id").val() == "") {
		alert("아이디를 입력하세요!!!!!!");
		$("#rg_id").focus();
		return;
	}
	/*
	if (userinput.email.value == "") {
		alert("아이디를 입력하세요");
		return;
	}
	 */
	// url과 사용자 입력 id를 조합합니다.
	url = "/moahair/member/ConfirmId.do?rg_id="+userinput.rg_id.value;

	// 새로운 윈도우를 엽니다.
	open(url, "confirm",  "toolbar=no, location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=300, height=200");
}

function checkItmodify() {
	var userinput = eval("document.userinput");
	if($("#m_pw").val() == "") {
		alert("비밀번호를 입력하세요");
		$("#m_pw").focus();
		return false;
	}

	if($("#m_pw").val() != $("#m_pw2").val()) {
		alert("비밀번호를 동일하게 입력하세요");
		$("#m_pw").focus();
		return false;
	}

	if($("#m_name").val() == "") {
		alert("이름을 입력하세요.");
		$("#m_name").focus();
		return false;
	}

	if($("#m_phone").val() == "") {
		alert("핸드폰 번호를 입력하세요.");
		$("#m_phone").focus();
		return false;
	}

	if($("#m_address").val() == "") {
		alert("주소를 입력하세요.");
		$("#m_address").focus();
		return false;
	}
	
	if($("#m_email").val() == "") {
		alert("주소를 입력하세요.");
		$("#m_email").focus();
		return false;
	}
}

function checkCerfity() {
	if($("#si_name").val() == "") {
		alert("이름을 입력하세요.");
		$("#si_name").focus();
		return false;
	}

	if($("#si_email").val() == "") {
		alert("이메일 주소를 입력하세요.");
		$("#si_email").focus();
		return false;
	}
	
	$.ajax({
		type : "post", url : "/moahair/member/idSearchCerfity.do",
		data : {si_name : $('#si_name').val(), si_email : $('#si_email').val()},
		success : function(data) {
			$("#emailResult").html(data);
		}
	});
}

function checkItIdSearch() {
	var myform = eval("document.myform");
	if($("#si_name").val() == "") {
		alert("이름을 입력하세요.");
		$("#si_name").focus();
		return false;
	}

	if($("#si_email").val() == "") {
		alert("이메일 주소를 입력하세요.");
		$("#si_email").focus();
		return false;
	}
	
	if($("#si_cerfity").val() == "") {
		alert("인증 번호를 입력하세요.");
		$("#si_cerfity").focus();
		return false;
	}
}

function checkPwCerfity() {
	if($("#si_id").val() == "") {
		alert("아이디를 입력하세요.");
		$("#si_id").focus();
		return false;
	}
	
	if($("#si_name").val() == "") {
		alert("이름을 입력하세요.");
		$("#si_name").focus();
		return false;
	}

	if($("#si_email").val() == "") {
		alert("이메일 주소를 입력하세요.");
		$("#si_email").focus();
		return false;
	}
	
	$.ajax({
		type : "post", url : "/moahair/member/PwSearchCerfity.do",
		data : {si_id : $('#si_id').val(), si_name : $('#si_name').val(), si_email : $('#si_email').val()},
		success : function(data) {
			$("#emailResult").html(data);
		}
	});
}


function checkItPwSearch() {
	var myform = eval("document.myform");
	if($("#si_id").val() == "") {
		alert("아이디를 입력하세요.");
		$("#si_id").focus();
		return false;
	}
	
	if($("#si_name").val() == "") {
		alert("이름을 입력하세요.");
		$("#si_name").focus();
		return false;
	}

	if($("#si_email").val() == "") {
		alert("이메일 주소를 입력하세요.");
		$("#si_email").focus();
		return false;
	}
	
	if($("#si_cerfity").val() == "") {
		alert("인증 번호를 입력하세요.");
		$("#si_cerfity").focus();
		return false;
	}
}

function checkItNewPwForm() {
	var myform = eval("document.myform");
	
	if($("#si_pw").val() == "") {
		alert("비밀번호를 입력하세요.");
		$("#si_pw").focus();
		return false;
	}
	
	if($("#si_pw").val() != $("#si_pw2").val()) {
		alert("비밀번호를 동일하게 입력하세요");
		$("#si_pw").focus();
		return false;
	}
}