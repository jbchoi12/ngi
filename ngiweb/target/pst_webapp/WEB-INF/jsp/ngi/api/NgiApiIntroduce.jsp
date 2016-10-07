<%--
  Class Name : NgiAboutSite.jsp
  Description : 사이트소개
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2011.08.31   JJY       경량환경 버전 생성
 
    author   : 실행환경개발팀 JJY
    since    : 2011.08.31 
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>국토변화정보포털서비스</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngicommon.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngimain.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngistyle.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngitable.css">

<script type="text/javascript" src="<c:url value='/'/>js/default.js"></script>
<script type="text/javascript" src="<c:url value='/'/>js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<c:url value='/'/>js/gnb_main.js"></script>
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
                <li class="side2_li"><a href="<c:url value='/'/>ngi/api/NgiApiIntroduce.do" class="side2_aon">Open API 소개</a></li>
                <li class="side2_li"><a href="<c:url value='/'/>ngi/api/tnCntcInfoView.do" class="side2_a">Open API 발급/관리</a></li>
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
            <a href="#" class="History2">연계서비스</a> &gt; <a href="#" class="History3">Open API</a>&gt; <a href="#" class="History3">Open API 소개</a>
        </div>
        <!-- //history -->
        <h3>Open API 소개</h3>         
    </div>


	<div class="ConMView">
 
     <h4 class="top_mar_0">Open API란 무엇인가요? </h4>
	<p class="h4Txt top_mar_0">변동정보 관리를 통해 수집된 Data를 외부에 웹 서비스(WebService) 형태로 공개하여 사용자가 원하는 컨텐츠를 만들 수 있는 웹 서비스 입니다.</p>
	<br/>
	     <h5 class="Marg_T20">Open API 이용방법</h5>
    <p class="h5Txt Marg_B10">Open API 이용절차입니다. 아래와 같은 절차에 따라 적용하시면 쉽게 자신의 시스템에 응용할 수 있습니다.</p>
	<p style="text-align:center;" class="Marg_L20"><img src="<c:url value='/'/>images/sub/info_comm_api_img01.jpg" alt="openapi 발급절차"></p>
	
 
 
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