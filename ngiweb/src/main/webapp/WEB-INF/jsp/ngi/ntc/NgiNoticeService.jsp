<%--
  Class Name : NgiNoticeService.jsp
  Description : 알림서비스란?
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
    <h2>알림서비스</h2>
        <ul class="side1_ul">
        <li  class="side1_li"><a href="<c:url value='/'/>ngi/ntc/NgiNoticeService.do" class="side1_aon">알림서비스란?</a></li>
        <li  class="side1_li"><a href="<c:url value='/'/>ngi/ntc/addTnNtcnSrvcView.do" class="side1_a">알림서비스 등록</a></li>
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
		            <a href="#" class="History2">알림서비스</a> &gt; <a href="#" class="History3">알림서비스란?</a>
		        </div>
		        <!-- //history -->
		        <h3>알림서비스란?</h3>         
		    </div>
	
	
			<div class="ConMView">
			
      <h4 class="top_mar_0">변화정보 알림서비스</h4>
	<p class="h4Txt top_mar_0">알림서비스에 등록하면 월별로 고시된 내용을 아래와 같은 Data의 파일을 E-MAil로 전송해드리며 동시에 SMS로 발송내용을 알려드립니다.</p>
	<h5 class="Marg_T40">Data의 형태(예시)</h5>
    <p class="Marg_L20"><img src="<c:url value='/'/>images/sub/info_comm_ntc_img01.jpg" alt="Data의 형태(예시)" title="Data의 형태(예시)"></p>			
			
			
		 
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