<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
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

<!--For Commons Validator Client Side
<script type="text/javascript" src="<c:url value='/cmmn/validator.do'/>"></script>
<validator:javascript formName="tnCntcInfoVO" staticJavascript="false" xhtml="true" cdata="false"/>
-->
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


<div>
	<h3>발급 API</h3>
	<c:if test="${not empty tnCntcInfoVO.crtfcCodeSe}">	
	<table class="tb_openapi">
		<caption>발급 API</caption>
		<colgroup>
            <col style="width:154px;">
            <col style="width:318px;">
            <col style="width:240px;">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">시스템명</th>
            <th scope="col">발급정보</th>
            <th scope="col">사용용도</th>
        </tr>
        </thead>
 				<tbody style="text-align: center;">
					<tr>
						<td class="tb_openapi_st">${tnCntcInfoVO.sysNm}</td>
						<td>
							<dl style="overflow: hidden;">
								<dt style="float:left;width:45px;">URL</dt>
								<dd style="float:left;"><strong>${tnCntcInfoVO.applcDomnNm}</strong></dd>
							</dl>
							<dl>
								<dt style="float:left;width:45px;" >발급키</dt>
								<dd style="float:left;"><strong>${tnCntcInfoVO.crtfcCodeSe}</strong></dd>
							</dl>
						</td>
						<td class="tb_openapi_st">${tnCntcInfoVO.useprps}</td>
					</tr>
 				</tbody>
        </tbody>
	</table>
	</c:if>
	<div class="tb_openapi_mm"><strong>${message}</strong></div>
	<div id="BtnAreaSub">
	    <div id="StyleButtonDivSub">
	        <a href="<c:url value="/ngi/api/tnCntcInfoView.do" />" title="" class="BtnDiv"><span>확인</span></a>
	    </div>
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

  </div>
<!-- //내용 -->
	<!-- footer 시작 -->
	<div id="footer"><c:import url="/EgovPageLink.do?link=main/inc/NgiIncFooter" /></div>
	<!-- //footer 끝 -->
</div>
<!-- //wrap end -->

</body>
</html>

