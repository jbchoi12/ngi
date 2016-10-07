<%--
  Class Name : EgovLoginUsr.jsp
  Description : Login 인증 화면
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2009.03.03  박지욱          최초 생성
     2011.08.31  JJY       경량환경 버전 생성
 
    author   : 공통서비스개발팀 박지욱
    since    : 2009.03.03
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngicommon.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngimain.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXJ.min.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXInput.css">
<style type="text/css">
.loginC {width: 332px;height: 407px;position: relative;margin-left: auto;margin-right: auto;top: 100px;}
.td_width{width:59px;}
</style>
<script type="text/javascript" src="<c:url value='/'/>js/default.js"></script>
<script type="text/javascript" src="<c:url value='/'/>js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<c:url value='/'/>js/gnb_main.js"></script>

<title>국토변화정보포털서비스</title>
<script type="text/javascript">
<!--
function actionLogin() {

    if (document.loginForm.id.value =="") {
        alert("아이디를 입력하세요");
    } else if (document.loginForm.pssword.value =="") {
        alert("비밀번호를 입력하세요");
    } else {
        document.loginForm.action="<c:url value='/uat/uia/actionSecurityLogin.do'/>";
        //document.loginForm.j_username.value = document.loginForm.userSe.value + document.loginForm.username.value;
        //document.loginForm.action="<c:url value='/j_spring_security_check'/>";
        document.loginForm.submit();
    }
}


function goRegiUsr() {
    var userSe = document.loginForm.userSe.value;
    // 일반회원
    if (userSe == "GNR") {
        document.loginForm.action="<c:url value='/uss/umt/cmm/EgovMberSbscrbView.do'/>";
        document.loginForm.submit();
    }else{
        alert("일반회원 가입만 허용됩니다.");
    }
}

function setCookie (name, value, expires) {
    document.cookie = name + "=" + escape (value) + "; path=/; expires=" + expires.toGMTString();
}

function getCookie(Name) {
    var search = Name + "="
    if (document.cookie.length > 0) { // 쿠키가 설정되어 있다면
        offset = document.cookie.indexOf(search)
        if (offset != -1) { // 쿠키가 존재하면
            offset += search.length
            // set index of beginning of value
            end = document.cookie.indexOf(";", offset)
            // 쿠키 값의 마지막 위치 인덱스 번호 설정
            if (end == -1)
                end = document.cookie.length
            return unescape(document.cookie.substring(offset, end))
        }
    }
    return "";
}

function saveid(form) {
    var expdate = new Date();
    // 기본적으로 30일동안 기억하게 함. 일수를 조절하려면 * 30에서 숫자를 조절하면 됨
    if (form.checkId.checked)
        expdate.setTime(expdate.getTime() + 1000 * 3600 * 24 * 30); // 30일
    else
        expdate.setTime(expdate.getTime() - 1); // 쿠키 삭제조건
    setCookie("saveid", form.id.value, expdate);
}

function getid(form) {
    form.checkId.checked = ((form.id.value = getCookie("saveid")) != "");
}

function fnInit() {
    var message = document.loginForm.message.value;
    if (message != "") {
  		 alert(message);
    }
    getid(document.loginForm);
}
//-->
</script>
</head>
<body onLoad="fnInit();">

<!-- wrap start -->
<div id="wrap"> 
    <!-- header start -->

	<div id="header"><c:import url="/sym/mms/NgiMainMenuHead.do?flag=MAIN" /></div>	
	
    <div id="container">

        <div class="McontainerIn">
            <div class="loginC">
	            <h2  style="margin: 11px 96px;">로그인</h2>
	            <div class="search_service">
	                <div class="search_top_table">  
		                <form name="loginForm" action ="<c:url value='/uat/uia/actionSecurityLogin.do'/>" method="post"> 
		                <div style="visibility:hidden;display:none;">
			                <input name="iptSubmit1" type="submit" value="전송" title="전송" />
			            </div> 
			            <input type="hidden" name="message" value="${message}" />
		
			            <table>
			            	<caption>로그인 테이블</caption>
			               <tr style="height: 33px;">
			                 <td class="td_width">아이디</td>
			                 <td class="td_content">
			                     <input type="text" name="id" id="id" title="아이디" class="AXInput" maxlength="10" />
			                     <input type="checkbox" name="checkId" id="checkId" title="암호저장여부" onClick="javascript:saveid(document.loginForm);" style="position: relative;top: 4px;left: 6px;" />
			                     <span style="position: relative;top: 2px;left: 10px;">ID저장</span>
			                 </td>
			               </tr>
			               <tr style="height: 33px;">
			                 <td class="td_width">비밀번호</td>
			                 <td class="td_content">
			                     <input type="password" name="pssword" id="pssword" title="비밀번호" class="AXInput"  onKeyDown="javascript:if (event.keyCode == 13) { actionLogin(); }" />
			                 </td>
			               </tr>
			               <tr style="height: 33px;">
				               	<td class="td_width"></td>
				               	<td class="td_content"><input type="button" class="AXButton Blue" id="input" style="float: right;right: 59px;" value="로그인" onclick="javascript:actionLogin();"/></td>
			               </tr>
			             </table>
			             <input name="userSe" type="hidden" value="GNR"/>
			             <input name="j_username" type="hidden"/>
				         </form>
		             </div>
                 </div>
            </div>
         </div>
    </div> 
    <!-- 하단 유틸메뉴 -->
    <div class="MBotUtill"><c:import url="/EgovPageLink.do?link=main/inc/NgiIncUtil" /></div>
    <!-- //하단 유틸메뉴 -->

    <!-- 하단 배너 -->
    <div class="MBanner"><c:import url="/EgovPageLink.do?link=main/inc/NgiIncBanner" /></div>
    <!-- //하단 배너 -->        
        
    <!-- footer 시작 -->
    <div id="footer"><c:import url="/EgovPageLink.do?link=main/inc/NgiIncFooter" /></div>
    <!-- //footer 끝 -->
</div>

</body>
</html>


