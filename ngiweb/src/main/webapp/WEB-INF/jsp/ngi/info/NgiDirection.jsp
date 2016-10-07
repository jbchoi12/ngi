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
    <h2>시스템안내</h2>
        <ul class="side1_ul">
        <li  class="side1_li"><a href="<c:url value='/'/>ngi/info/NgiAboutSite.do" class="side1_a">국토변화포털서비스</a></li>
        <li  class="side1_li"><a href="<c:url value='/'/>ngi/info/NgiProvideService.do" class="side1_a">제공서비스</a></li>
        <li  class="side1_li"><a href="<c:url value='/'/>ngi/info/NgiDirection.do" class="side1_aon">사용안내</a></li>
        </ul>
		<c:import url="/EgovPageLink.do?link=main/inc/NgiIncFBtn" />
    </div>
	<!-- //lnb --> 

<div id="Content">
    <div class="ConMTitle">
        <!-- history -->
        <div class="History">
            <a href="#" class="his1">처음으로</a><a href="#" class="History1"></a> &gt;
            <a href="#" class="History2">시스템안내</a> &gt; <a href="#" class="History3">사용안내</a>
        </div>
        <!-- //history -->
        <h3>사용안내</h3>         
    </div>


	<div class="ConMView">
 
       <h4 class="top_mar_0">변경신고</h4>
	<p class="h4Txt top_mar_0">자신의 주변에 지도에 반영되어야 할 일들이 생겼을시 직접 신고하는 서비스입니다.</p>
	<h5 class="Marg_T40">변경신고 방법</h5>
	    <p class="h5Txt Marg_B10">위치검색을 통한 이동이나 직접 지도컨트롤을 통하여 신고할 위치로 이동을 하여 임미 신고가 되있는지 범례에 따른 표기로 확인이 가능합니다.</p>	
    <p class="Marg_L20"><img src="<c:url value='/'/>images/sub/info_comm_direction_img01.jpg" alt="변경신고 방법" title="변경신고 방법"></p>
	<p class="TxtPo Marg_L20  Marg_B10">* 로그인된 사용자만 신고가 가능합니다.</p>
	<h5 class="Marg_T40">변경신고 등록</h5>
    <p class="h5Txt Marg_B10">신고의 제목과 위치를 입력합니다.<br />위치의 입력은 주소 검색이나 지도상의 위치를 바로 찍어 자동으로 입력할 수 도 있습니다.</p>
    <p class="h5Txt Marg_B10">변경된 모양으로 도형을 그리는 것도 가능합니다.<br />이미지를 첨부하여 신고 하면 더욱 빠른 처리가 가능합니다.</p>		
	
 	<p class="Marg_L20"><img src="<c:url value='/'/>images/sub/info_comm_direction_img02.jpg" alt="변경신고 등록" title="변경신고 등록"></p>
 
 
 
 
 
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