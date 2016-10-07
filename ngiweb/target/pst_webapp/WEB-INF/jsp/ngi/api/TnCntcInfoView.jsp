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

<script type="text/javascript">
<!--
/* 글 등록 function */
function deleteKey(num) {	
	if (confirm("정말 삭제하시겠습니까??") == true){    //확인
		document.form_delete_key.keynum.value = num;
	    document.form_delete_key.submit();
	}else{   //취소
	    return;
	}
}
function fnInit() {
    var message = document.form_delete_key.message.value;
    if (message != "") {
        alert(message);
    }
    document.form_delete_key.message.value = '';
    document.form_delete_key.keynum.value = '';
}
// -->
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
    <h2>연계서비스</h2>
        <ul class="side1_ul">
        <li  class="side1_li"><a href="<c:url value='/'/>ngi/api/NgiApiIntroduce.do" class="side1_aon">Open API</a>
            <div class="side2_div">
                <ul class="side2_ul">
                <li class="side2_li"><a href="<c:url value='/'/>ngi/api/NgiApiIntroduce.do" class="side2_a">Open API 소개</a></li>
                <li class="side2_li"><a href="<c:url value='/'/>/ngi/api/tnCntcInfoView.do" class="side2_aon">Open API 발급/관리</a></li>
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

<div>
	<h3>발급 API</h3>
	<table class="tb_openapi">
		<caption>발급 API</caption>
		<colgroup>
            <col style="width:68px;">
            <col style="width:142px;">
            <col style="width:290px;">
            <col style="width:141px;">
            <col style="width:45px;">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">발급일</th>
            <th scope="col">시스템명</th>
            <th scope="col">발급정보</th>
            <th scope="col">사용용도</th>
            <th scope="col">관리</th>
        </tr>
        </thead>
        <c:choose>
 			<c:when test="${fn:length(list) > 0}">
 				<tbody style="text-align: center;">
 					<c:forEach items="${list}" var="data">
 						<tr>
 							<td>${data.rgsde}</td>
 							<td class="tb_openapi_st">${data.sysNm}</td>
 							<td>
 								<dl style="overflow: hidden;">
 									<dt style="float:left;width:45px;">URL</dt>
 									<dd style="float:left;"><strong><a href="${data.applcDomnNm}" target="_nm">${data.applcDomnNm}</a></strong></dd>
 								</dl>
 								<dl>
 									<dt style="float:left;width:45px;" >발급키</dt>
 									<dd style="float:left;"><strong>${data.crtfcCodeSe}</strong></dd>
 								</dl>
 							</td>
 							<td class="tb_openapi_st">${data.useprps}</td>
 							<td><a href="javascript:deleteKey('${data.cntcInfoId}');">[삭제]</a></td>
 						</tr>
					</c:forEach>
 				</tbody>
 			</c:when>
 			<c:otherwise>
 				<tbody>
					<tr>
						<td></td>
						<td></td>
						<td style="text-align:center;">발급된 키가 없습니다.</td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
 			</c:otherwise>
		</c:choose>
        </tbody>
	</table>
</div>
<div id="BtnAreaSub">
    <div id="StyleButtonDivSub">
        <a href="<c:url value="/ngi/api/tnCntcInfoRegister.do"/>" title="" class="BtnDiv"><span>키발급</span></a>
    </div>
</div>
<form name="form_delete_key" action="<c:url value="/ngi/api/deleteTnCntcInfo.do" />" method="post">
<input type="hidden" name="keynum" value="" />
<input type="hidden" name="message" value="${message}" />
</form>
</c:when>
 	<c:otherwise>
 	
 	<script type="text/javascript">
 	alert("로그인이 필요합니다.");
 	</script>
 		<p class="TxtPo Marg_L20  Marg_B10" style="padding-top:100px;text-align:center;" >로그인이 필요합니다.</p>
 		
 		
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

