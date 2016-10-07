<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%
 /**
  * @Class Name : TnCntcInfoRegister.jsp
  * @Description : TnCntcInfo Register 화면
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
<%
String sUserId = (String)session.getAttribute("sUserId");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<c:set var="registerFlag" value="${empty tnCntcInfoVO.cntcInfoId ? '등록' : '수정'}"/>

<title>국토변화정보포털서비스</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngicommon.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngimain.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngistyle.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngitable.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXJ.min.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXInput.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXSelect.css">

<script type="text/javascript" src="<c:url value='/'/>js/default.js"></script>
<script type="text/javascript" src="<c:url value='/'/>js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<c:url value='/'/>js/gnb_main.js"></script>

<!--For Commons Validator Client Side
<script type="text/javascript" src="<c:url value='/cmmn/validator.do'/>"></script>
<validator:javascript formName="tnCntcInfoVO" staticJavascript="false" xhtml="true" cdata="false"/>
-->
<script type="text/javascript">
<!--
/* 글 등록 function */
function fn_egov_save() {	
	frm = document.getElementById("detailForm1");

	/* TODO Validation기능 보완 */

	if (document.detailForm1.chargerNm.value =="") {
        alert("담당자명을 입력하세요.");
    } else if (document.detailForm1.telno.value =="") {
        alert("전화번호를 입력하세요.");        
    } else if (document.detailForm1.email.value =="") {
        alert("E-Mail주소를 입력하세요.");        
    } else if (document.detailForm1.applcDomnNm.value =="") {
        alert("적용URL을 입력하세요.");        
    } else {	
	  	frm.action = "<c:url value='/ngi/api/addTnCntcInfo.do'/>";
	    frm.submit();
    }
}
// -->
</script>
</head>
<body>
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
    <h2>연계서비스</h2>
        <ul class="side1_ul">
        <li  class="side1_li"><a href="<c:url value='/'/>ngi/api/NgiApiIntroduce.do" class="side1_aon">Open API</a>
            <div class="side2_div">
                <ul class="side2_ul">
                <li class="side2_li"><a href="<c:url value='/'/>ngi/api/NgiApiIntroduce.do" class="side2_a">Open API 소개</a></li>
                <li class="side2_li"><a href="<c:url value='/'/>ngi/api/tnCntcInfoView.do" class="side2_aon">Open API 발급/관리</a></li>
                <li class="side2_li"><a href="<c:url value='/'/>ngi/api/NgiApiReperence.do" class="side2_a">Open API 레퍼런스</a></li>
                <li class="side2_li"><a href="<c:url value='/'/>ngi/api/NgiApiExample.do" class="side2_a">Open API 예제</a></li>
                </ul>
            </div>
        </li>
        <li  class="side1_li"><a href="<c:url value='/'/>" class="side1_a">세움터/새주소</a>
            <div class="side2_div">
                <ul class="side2_ul">
                <li class="side2_li"><a href="<c:url value='/'/>ngi/trs/NgiTrsInfo.do" class="side2_a">세움터/새주소 소개</a></li>
                <li class="side2_li"><a href="<c:url value='/'/>ngi/trs/NgiTrsDownList.do" class="side2_a">세움터/새주소 변화정보</a></li>
                </ul>
            </div>        
        </li>
        </ul>
		<c:import url="/EgovPageLink.do?link=main/inc/NgiIncFBtn" />
    </div>
	<!-- //lnb --> 

		<div id="Content">
		    <div class="ConMTitle">
		        <!-- history -->
		        <div class="History">
		            <a href="#" class="his1">처음으로</a><a href="#" class="History1"></a> &gt;
		            <a href="#" class="History2">연계서비스</a> &gt; <a href="#" class="History3">Open API</a>&gt; <a href="#" class="History3">Open API 발급/관리</a>
		        </div>
		        <!-- //history -->
		        <h3>Open API 발급/관리</h3>         
		    </div>
			<div class="ConMView">
<c:choose>
 	<c:when test="${not empty sUserId}">

<form:form commandName="tnCntcInfoVO" name="detailForm1" id="detailForm1" >
<div id="content_pop">
	<div id="table">
	<table border="0" cellpadding="0" cellspacing="0" class="tabwrite2">
		<colgroup>
			<col width="250"/>
			<col width=""/>
		</colgroup>
		<tr>
			<th>담당자</th>
			<td>
				<form:input path="chargerNm" class="AXInput" value='<%=session.getAttribute("sUserNm")%>' readonly="true" />
				&nbsp;<form:errors path="chargerNm" />
			</td>
		</tr>	
		<tr>
			<th>E-mail</th>
			<td>
				<form:input path="email" class="AXInput" value='<%=session.getAttribute("sUserEmail")%>' readonly="true" />
				&nbsp;<form:errors path="email" />
			</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>
				<form:input path="telno" class="AXInput"  value='<%=session.getAttribute("sUserTel")%>' readonly="true"  />
				&nbsp;<form:errors path="telno" />
			</td>
		</tr>	
		<tr>
			<th>시스템명</th>
			<td>
				<form:input path="sysNm" class="AXInput W250"/>
				&nbsp;<form:errors path="sysNm" />
			</td>
		</tr>		
<%-- 		<tr>
			<th>API 종류</th>
			<td>
	           <form:select path="cntcTy" id="cntcTy" class="AXSelectSmall" >
                   <form:option value="">-선택-</form:option>
                   <form:options items="${cntc_result}" itemValue="code" itemLabel="codeNm"/>
               </form:select> 			
			</td>
		</tr> --%>	
		<tr>
			<th>적용 URL</th>
			<td>
				<form:input path="applcDomnNm" class="AXInput W200"/>
				&nbsp;<form:errors path="applcDomnNm" />
				<p style="color: #666; margin:5px;">예) 기본포트 사용시 - http://www.example.com </br>
				&nbsp;&nbsp;&nbsp;다른포트 사용시 - http://www.example.com:8080 </p>
			</td>
		</tr>	
		<tr>
			<th>사용용도</th>
			<td>
				<form:input path="useprps" class="AXInput W300"/>
				&nbsp;<form:errors path="useprps" />
			</td>
		</tr>	
	</table>
  </div>

</div>

                <div class="Marg_T10">
			        <div id="BtnAreaSub">
			            <div id="StyleButtonDivSub">
			                <a href="javascript:fn_egov_save();" title="" class="BtnDiv"><span>등록</span></a>
			                <a href="<c:url value="/ngi/api/tnCntcInfoView.do" />" title="" class="BtnDiv"><span>취소</span></a>
			            </div>
			        </div>
			    </div> 
<input type="hidden" name="userid" value="<%=sUserId%>" />
</form:form>

</c:when>
 	<c:otherwise>
 		<p>로그인이 필요합니다.</p>
 	</c:otherwise>
</c:choose>
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

