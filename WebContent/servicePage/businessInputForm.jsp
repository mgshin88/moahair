<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${m_level != 10 }">
		<script>
			alert("접근이 불가능한 페이지 입니다.");
			window.location = "/moahair/main/main.do";
		</script>
	</c:if>

<style type="text/css">
    .img_wrap{
        width: 150px;
        margin-top: 50px;
    }
    .img_wrap img{
        max-width: 100%;
    }
</style>

<body>


<div class="logregHeader">
	<div class="logregTitle">샵 등록 페이지</div>
</div>

<script type="text/javascript">

function checkTime(){

   var sel = document.getElementById("bs_open");
   var val = sel.options[sel.selectedIndex].innerHTML;
   
   var sel = document.getElementById("bs_close");
   var val1 = sel.options[sel.selectedIndex].innerHTML;
   
   
   var confirmflag = confirm("오픈시간 : "+ val+"," + " 마감시간 : " + val1 +"가 맞습니까?");
      if(confirmflag){

         return true;

        }else{

          return false;

        }
}
function writeSave(){
   checkTime();
   if(document.businessInputForm.bs_name.value==""){
     alert("상호 이름을 입력하세요.");
     document.businessInputForm.bs_name.focus();
     return false;
   }
   if(document.businessInputForm.bs_number.value==""){
     alert("상호 번호을 입력하세요.");
     document.businessInputForm.bs_number.focus();
     return false;
   }
   
   if(document.businessInputForm.bs_profile.value==""){
     alert("프로필 사진을 등록하세요.");
     document.businessInputForm.bs_profile.focus();
     return false;
   }
        
   if(document.businessInputForm.bs_background.value==""){
     alert("배경 사진을 등록하세요.");
     document.businessInputForm.bs_background.focus();
     return false;
   }
   
   if(document.businessInputForm.ba_state.value==""){
        alert("샵 주소(도)를 입력해주세요.");
        document.businessInputForm.ba_state.focus();
        return false;
      }
   if(document.businessInputForm.ba_city.value==""){
        alert("샵 주소(시)를 입력해주세요.");
        document.businessInputForm.ba_city.focus();
        return false;
      }
   if(document.businessInputForm.ba_surburb.value==""){
        alert("샵 주소(구)를 입력해주세요.");
        document.businessInputForm.ba_surburb.focus();
        return false;
      }
   if(document.businessInputForm.ba_street.value==""){
        alert("샵 주소(동)를 입력해주세요.");
        document.businessInputForm.ba_street.focus();
        return false;
      }
   if(document.businessInputForm.ba_rest.value==""){
        alert("샵 주소(나머지)를 입력해주세요.");
        document.businessInputForm.ba_rest.focus();
        return false;
      }

   
 }    


</script>

<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" >
    
    var sel_file;
    $(document).ready(function(){
        $("#profile").on("change", handlerFileSelect);
        $("#background").on("change", handlerFileSelect2);
        
    });
    
    function handlerFileSelect(e){
        var files = e.target.files;
        
        var filesArr = Array.prototype.slice.call(files);
        
        filesArr.forEach(function(f){
                         if(!f.type.match("image.*")){
            alert("이미지 파일이 아닙니다.");
            return;
        }
        
        sel_file = f;
        var reader = new FileReader();
        reader.onload = function(e){
            $("#img").attr("src",e.target.result);
        }
            reader.readAsDataURL(f);
        });
    }
    
    function handlerFileSelect2(e){
        var files = e.target.files;
        
        var filesArr = Array.prototype.slice.call(files);
        
        filesArr.forEach(function(f){
                         if(!f.type.match("image.*")){
            alert("이미지 파일이 아닙니다.");
            return;
        }
        
        sel_file = f;
        var reader = new FileReader();
        reader.onload = function(e){
            $("#img2").attr("src",e.target.result);
        }
            reader.readAsDataURL(f);
        });
    }
    
</script>


<form method="post" name="businessInputForm" action="/moahair/seller/businessinputpro.do" 
enctype="multipart/form-data" onsubmit="return writeSave()">
<table width="400" border="1" cellspacing="0" cellpadding="0"   align="center"
style="margin:0 auto;">
   <tr>
    <td align="right" colspan="2">
       
   </td>
   </tr>
   <tr>
    <td  width="70" align="center">상 호</td>
    <td  width="330">
       <input type="text" size="20" maxlength="20" name="bs_name"></td>
  </tr>
   <tr>
    <td  width="70" align="center">판매자 ID</td>
    <td  width="330">
       <input type="text" size="20" maxlength="20" name="m_id"></td>
  </tr>
  <tr>
    <td  width="70" align="center" >사업자번호</td>
    <td  width="330">
    
       <input type="text" size="40" maxlength="50" name="bs_number"/></td>
 
  </tr>
 
     <tr>
    <td  width="70" align="center">샵 프로필사진</td>
    <td  width="330">
       <input type="file" size="40" maxlength="30" name="bs_profile" id ="profile" />
       <div  class="img_wrap">
       
       <img id="img"/>
       
       </div>
       
       </td>
  </tr>
   <tr>
    <td  width="70"   align="center">배경 이미지</td>
    <td  width="330">
       <input type="file" size="40" maxlength="30" name="bs_background"  id="background"/>
        <div  class="img_wrap">
       
       <img id="img2"/>
       
       </div>
       </td>
  </tr>
   
<tr>
    <td  width="70" align="center">샵 오픈시간</td>
    <td  width="330">
       <select name="bs_open" id="bs_open">
       
       <c:forEach var="timetb" items="${timetable }">
             <option value="${timetb.num}">${timetb.athirty   }</option>
       </c:forEach>
      </select>
  </tr>
    <tr>
    <td  width="70" align="center">샵 마감시간</td>
    <td  width="330">
    <select name="bs_close" id="bs_close">
       <c:forEach var="timetb" items="${timetable }">
             <option value="${timetb.num }">${timetb.athirty }</option>
       </c:forEach>
       </select>
  </tr>
  
     <tr>
    <td  width="70" align="center">샵 주소(도)</td>
    <td  width="330">
    	<select name="ba_state">
    		<option value="수도권">서울/수도권</option>
    		<option value="충청/강원">충청/강원</option>
    		<option value="영남">영남</option>
    		<option value="호남/제주">호남/제주</option>
    	</select> 
    </td>
  </tr>
    <td  width="70" align="center">샵 주소(시)</td>
    <td  width="330">
       <input type="text" size="40" name="ba_city"  ></td>
  </tr>
    <td  width="70" align="center">샵 주소(구)</td>
    <td  width="330">
       <input type="text" size="40" name="ba_surburb" ></td>
  </tr>
    <td  width="70" align="center">샵 주소(동)</td>
    <td  width="330">
       <input type="text" size="40" name="ba_street" ></td>
  </tr>
    <td  width="70" align="center">샵 주소(나머지)</td>
    <td  width="330">
       <input type="text" size="40" name="ba_rest" ></td>
  </tr>
  
<tr>      
 <td colspan=2 align="center"> 
  <input type="reset" value="다시작성">
  <input type="submit" value="글쓰기" >  
 
</td></tr></table>    
   
</form>      




</body>
</html>