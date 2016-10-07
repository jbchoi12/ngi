<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : TnNtcnSrvcRegister.jsp
  * @Description : TnNtcnSrvc Register 화면
  * @Modification Information
  * 
  * @author 이정연
  * @since 2014-09-19
  * @version 1.0
  * @see
  *  
  * Copyright (C) All right reserved.
  */
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<c:set var="registerFlag" value="${empty tnNtcnSrvcVO.ntcnSrvcId ? '등록' : '수정'}"/>

<title>국토변화정보포털서비스</title>

<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngicommon.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngimain.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngistyle.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngitable.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXJ.min.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXInput.css">

<script type="text/javascript" src="<c:url value='/'/>js/default.js"></script>
<script type="text/javascript" src="<c:url value='/'/>js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<c:url value='/'/>js/gnb_main.js"></script>

<!--For Commons Validator Client Side
<script type="text/javascript" src="<c:url value='/cmmn/validator.do'/>"></script>
<validator:javascript formName="tnNtcnSrvcVO" staticJavascript="false" xhtml="true" cdata="false"/>
-->
<script type="text/javascript">


/* 글 등록 function */
function fn_egov_save() {	
	frm = document.getElementById("detailForm1");

	/* TODO Validation기능 보완 */
	
	var pstr = jQuery("#telno").val();
	//document.detailForm.telno.value;
	
	pstr = pstr.replace(/n/g,"");
	pstr = pstr.replace(/\u/g,"");
	pstr = pstr.replace(/\l/g,"");
	pstr = pstr.replace(/\-/g,"");

	jQuery("#telno").val(pstr);
	//document.detailForm.telno.value = pstr;
	
	var enctelno=Encrypt(jQuery("#telno").val());
	var encemail=Encrypt(jQuery("#email").val());
	
	var urltelno=encodeURL(enctelno);	
	var urlemail=encodeURL(encemail);
	
	//document.detailForm.telno.value=urltelno;
	//document.detailForm.email.value=urlemail;	
	jQuery("#telno").val(urltelno);
	jQuery("#email").val(urlemail);
	
    if (document.detailForm1.userNm.value =="") {
        alert("사용자명을 입력하세요.");
    } else if (document.detailForm1.telno.value =="") {
        alert("전화번호를 입력하세요.");        
    } else if (document.detailForm1.email.value =="") {
        alert("E-Mail주소를 입력하세요.");        
    } else {		
	  	frm.action = "<c:url value='/ngi/ntc/addTnNtcnSrvc.do'/>";
	    frm.submit();
    }
}

//암호화
function Encrypt(theText){ 
            output = new String; 
            Temp = new Array(); 
            Temp2 = new Array(); 
            TextSize = theText.length; 
            
            for (i = 0; i < TextSize; i++) {  
                      rnd = Math.round(Math.random() * 122) + 68;  
                      Temp[i] = theText.charCodeAt(i) + rnd;  
                      Temp2[i] = rnd; 
            } 
            
            for (i = 0; i < TextSize; i++) {  
                      output += String.fromCharCode(Temp[i], Temp2[i]); 
            } 
            
            return output;
 }

//url 인코딩
function encodeURL(str){
     var s0, i, s, u;
     s0 = "";                // encoded str
     for (i = 0; i < str.length; i++){   // scan the source
         s = str.charAt(i);
         u = str.charCodeAt(i);          // get unicode of the char
         if (s == " "){s0 += "+";}       // SP should be converted to "+"
         else {
             if ( u == 0x2a || u == 0x2d || u == 0x2e || u == 0x5f || ((u >= 0x30) && (u <= 0x39)) || ((u >= 0x41) && (u <= 0x5a)) || ((u >= 0x61) && (u <= 0x7a))){       // check for escape
                 s0 = s0 + s;            // don't escape
             }
             else {                  // escape
                 if ((u >= 0x0) && (u <= 0x7f)){     // single byte format
                     s = "0"+u.toString(16);
                     s0 += "%"+ s.substr(s.length-2);
                 }
                 else if (u > 0x1fffff){     // quaternary byte format (extended)
                     s0 += "%" + (0xf0 + ((u & 0x1c0000) >> 18)).toString(16);
                     s0 += "%" + (0x80 + ((u & 0x3f000) >> 12)).toString(16);
                     s0 += "%" + (0x80 + ((u & 0xfc0) >> 6)).toString(16);
                     s0 += "%" + (0x80 + (u & 0x3f)).toString(16);
                 }
                 else if (u > 0x7ff){        // triple byte format
                     s0 += "%" + (0xe0 + ((u & 0xf000) >> 12)).toString(16);
                     s0 += "%" + (0x80 + ((u & 0xfc0) >> 6)).toString(16);
                     s0 += "%" + (0x80 + (u & 0x3f)).toString(16);
                 }
                 else {                      // double byte format
                     s0 += "%" + (0xc0 + ((u & 0x7c0) >> 6)).toString(16);
                     s0 += "%" + (0x80 + (u & 0x3f)).toString(16);
                 }
             }
         }
     }
     return s0;
 }



function fnInit() {
    var message = document.detailForm1.message.value;
    if (message != "") {
        alert(message);
    }
    jQuery("#telno").val("");
    jQuery("#email").val("");
    //document.detailForm1.reset();
}

</script>
</head>
<body onLoad="fnInit();">
<!-- wrap start -->
<div id="wrap">	
	<!-- header start -->
	<div id="header"><c:import url="/sym/mms/NgiMainMenuHead.do" /></div>	
	<!-- //header end -->
<!-- 내용 -->
    <div id="container">



<div class="Subcontainer">
	<!-- lnb -->
    <div class="LeftMenu">
    <h2>알림서비스</h2>
        <ul class="side1_ul">
        <li  class="side1_li"><a href="<c:url value='/'/>ngi/ntc/NgiNoticeService.do" class="side1_a">알림서비스란?</a></li>
        <li  class="side1_li"><a href="<c:url value='/'/>ngi/ntc/addTnNtcnSrvcView.do" class="side1_aon">알림서비스 등록</a></li>
        <li  class="side1_li"><a href="<c:url value='/'/>ngi/ntc/NgiGeoNtcnList.do" class="side1_a">지형고시 열람</a></li>
        </ul>
		<c:import url="/EgovPageLink.do?link=main/inc/NgiIncFBtn" />
    </div>
	<!-- //lnb --> 

		<div id="Content">
		    <div class="ConMTitle">
		        <!-- history -->
		        <div class="History">
		            <a href="#" class="his1">처음으로</a><a href="#" class="History1"></a> &gt;
		            <a href="#" class="History2">알림서비스</a> &gt; <a href="#" class="History3">알림서비스 등록</a>
		        </div>
		        <!-- //history -->
		        <h3>알림서비스 등록</h3>         
		    </div>
	
	
			<div class="ConMView">
			
<form:form commandName="tnNtcnSrvcVO" name="detailForm1" id="detailForm1" >
<input type="hidden" name="message" value="${message}" />
<div id="content_pop">
	<div id="table">
	<table class="tabwrite2" style="cellpadding:0px;cellspacing:0px;">
		<colgroup>
			<col style="width:150px;"/>
			<col />
		</colgroup>
		<tr>
			<th>사용자명</th>
			<td>
				<form:input path="userNm" class="AXInput" title="사용자명"/>
				&nbsp;<form:errors path="userNm" />
			</td>
		</tr>	
		<tr>
			<th>전화번호</th>
			<td>
				<form:input path="telno" class="AXInput" title="전화번호"/>
				&nbsp;<form:errors path="telno" />
			</td>
		</tr>	
		<tr>
			<th>E-mail</th>
			<td>
				<form:input path="email" class="AXInput" title="e-mail"/>
				&nbsp;<form:errors path="email" />
			</td>
		</tr>	

	</table>
  </div>
</div>

   <div class="Marg_T10">
       <div id="BtnAreaSub">
           <div id="StyleButtonDivSub">
			<button type="button" class="AXButtonSmall Blue" OnClick="javascript:fn_egov_save();"><i class="fa fa-check-circle fa-1"></i> 등록</button>				
			<button type="button" class="AXButtonSmall Blue" OnClick="javascript:document.detailForm.reset();"><i class="fa fa-align-justify fa-lg"></i> 취소</button>			            
           </div>
       </div>
   </div> 


</form:form>

			</div>
		</div>    
	</div>

    <!-- 하단 유틸메뉴 -->
    <div class="MBotUtill"><c:import url="/EgovPageLink.do?link=main/inc/NgiIncUtil" /></div>
    <!-- //하단 유틸메뉴 -->


    <!-- 하단 배너 -->
    <div class="MBanner"><c:import url="/EgovPageLink.do?link=main/inc/NgiIncBanner" /></div>
    <!-- //하단 배너 -->

  </div>
<!-- //내용 -->
	<!-- footer 시작 -->
	<div id="footer"><c:import url="/EgovPageLink.do?link=main/inc/NgiIncFooter" /></div>
	<!-- //footer 끝 -->
</div>
<!-- //wrap end -->

</body>
</html>

