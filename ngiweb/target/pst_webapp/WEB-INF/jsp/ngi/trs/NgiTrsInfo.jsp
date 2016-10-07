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
                <li class="side2_li"><a href="<c:url value='/'/>ngi/api/NgiApiIntroduce.do" class="side2_a">Open API 소개</a></li>
                <li class="side2_li"><a href="<c:url value='/'/>ngi/api/tnCntcInfoView.do" class="side2_a">Open API 발급/관리</a></li>
                <li class="side2_li"><a href="<c:url value='/'/>ngi/api/NgiApiReperence.do" class="side2_a">Open API 레퍼런스</a></li>
                <li class="side2_li"><a href="<c:url value='/'/>ngi/api/NgiApiExample.do" class="side2_a">Open API 예제</a></li>
                </ul>
            </div>
        </li>
        <li  class="side1_li"><a href="<c:url value='/'/>" class="side1_a">세움터/새주소</a>
            <div class="side2_div">
                <ul class="side2_ul">
                <li class="side2_li"><a href="<c:url value='/'/>ngi/trs/NgiTrsInfo.do" class="side2_aon">세움터/새주소 소개</a></li>
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
            <a href="#" class="History2">연계서비스</a> &gt; <a href="#" class="History3">세움터/새주소</a>&gt; <a href="#" class="History3">세움터/새주소 소개</a>
        </div>
        <!-- //history -->
        <h3>세움터/새주소 소개</h3>         
    </div>


	<div class="ConMView">
 
     <h4 class="top_mar_0">세움터 및 새주소 변화 정보 제공 </h4>
	<p class="h4Txt top_mar_0">세움터(건축행정시스템)와 새주소(도로명주소) 시스템에서 주기적으로 추출한 국토 변화 정보를 제공해 드립니다.</p>
	<br/>
	<h5 class="Marg_T20">제공 정보</h5>
    <p class="h5Txt Marg_B10">- 세움터 / 새주소 : 건물명, 용도, 행정구역, 좌표 등의 변화 정보</p>

	<h5 class="Marg_T20">변화 정보 제공 주기</h5>
    <p class="h5Txt Marg_B10">- 세움터 : 1주일</p>
 	<p class="h5Txt Marg_B10">- 새주소 : 1개월</p>
 	
 	<h5 class="Marg_T20">세움터(건축행정시스템)</h5>
    <p class="h5Txt Marg_B10">대한민국의 대표적인 복합민원인 건축행정 업무 전반의 전자화를 통해 국민은 관청 방문없이 인터넷으로 편리하게 인허가 신청을 하고, 공무원은 건축행정(인허가-->착공-->분양-->준공(사용승인)-->철거 등) 업무 전반을 전자적으로 One Stop 처리하게 하는 국가표준정보시스템입니다.</p>	
 	<p class="h5Txt Marg_B10">http://www.eais.go.kr/ </p>
 	
 	<h5 class="Marg_T20">새주소(도로명주소)</h5>
    <p class="h5Txt Marg_B10">"새주소(도로명주소)”란 부여된 도로명, 기초번호, 건물번호, 상세주소에 의하여 건물의 주소를 표기하는 방식으로, 도로에는 도로명을 부여하고, 건물에는 도로에 따라 규칙적으로 건물번호를 부여하여 도로명과 건물번호 및 상세주소(동·층·호)로 표기하는 주소제도입니다. </p>	
 	<p class="h5Txt Marg_B10">http://www.juso.go.kr/ </p> 	
 	
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