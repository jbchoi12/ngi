<%--
  Class Name : NgiAboutSite.jsp
  Description : 국토변화포털서비스
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
		        <li  class="side1_li"><a href="<c:url value='/'/>ngi/info/NgiAboutSite.do" class="side1_aon">국토변화포털서비스</a></li>
		        <li  class="side1_li"><a href="<c:url value='/'/>ngi/info/NgiProvideService.do" class="side1_a">제공서비스</a></li>
		        <li  class="side1_li"><a href="<c:url value='/'/>ngi/info/NgiDirection.do" class="side1_a">사용안내</a></li>
		        </ul>
				<c:import url="/EgovPageLink.do?link=main/inc/NgiIncFBtn" />
		    </div>
			<!-- //lnb -->  
			
			<div id="Content">
			    <div class="ConMTitle">
			        <!-- history -->
			        <div class="History">
			            <a href="#" class="his1">처음으로</a><a href="#" class="History1"></a> &gt;
			            <a href="#" class="History2">시스템안내</a> &gt; <a href="#" class="History3">국토변화포털서비스</a>
			        </div>
			        <!-- //history -->
			        <h3>국토변화포털서비스</h3>         
			    </div>
			
			
				<div class="ConMView">
				     <h4 class="top_mar_0">국토변화포털 서비스의 목적</h4>
					<p class="h4Txt top_mar_0">우리국토의 변화정보를 신속·정확하게 수집하고 이를 효과적으로 관리하여 다양한 분야에서 활용 가능하도록 서비스를 확대하는것을 목표로 하고있습니다</p>
					<h5 class="Marg_T40">서비스의 기대효과</h5>
				    <p class="h5Txt Marg_B10">우리국토변화정보의 신속·정확한 최신 전지지도 반영과 이를 기반으로한 다양한 사용자 환경의 서비스확대, 업무추진 환경개선 및 대국민 서비스 향상등의 효과를 기대합니다.</p>
				    <table class="Ctable Marg_L20">
				      <caption>
				        테이블
				        </caption>
				      <colgroup>
				        <col style="width:25%;">
				        <col style="width:65%;">
				        <col style="width:10%;">
				        </colgroup>
				      <thead>
				        <tr>
				          <th>기대효과</th>
				          <th class="t_end">내용</th>
			            </tr>
				      </thead>
				      <tbody>
				        <tr>
				          <td class="TxtCenter">국토변화정보 수집 활성화</td>
				          <td>공간정보 산업의 핵심인프라인 최신지도 수요증가에 따라 국가기본도 수시수정을 위한 변화정보 수집을 자동화하고 보다 편리한 변동신고 환경도입을 통한 이용 활성화</td>
			            </tr>
				        <tr>
				          <td class="TxtCenter">국토변화정보 관리환경 개선</td>
				          <td>변화정보의 추출 및 반영체계를 신속·정확하게 개선하고 첨단 GIS 기술을 기반으로 우리국토의 변화를 모니터링 할 수 있도록 관리환경의 체계화, 과학화</td>
			            </tr>
				        <tr>
				          <td class="TxtCenter">효율적인 국토변화정보 수집 및<br />활용환경 개선</td>
				          <td>수집, 반영된 국토변화 정보를 유관기관 및 일반국민에게 신속하고 효율적으로 제공함으로써 다양한 응용 활용환경 마련 및 대국민 서비스 향상</td>
			            </tr>
				        <tr>
				          <td class="TxtCenter">사용자 환경변화에 따른<br />능동적 대응</td>
				          <td>최근 주요 정보접근 수단으로 자리잡은 모바일 기반의 사용자 환경에 대응하기 위해 이용환경에 최적화된 모바일기반 국토변화 정보수집 및 활용서비스 구축</td>
			            </tr>                    
				      </tbody>
				    </table>
				</div>
			</div>    
		</div>
	
	    <!-- 하단 유틸메뉴 -->
	    <div class="MBotUtill"><c:import url="/EgovPageLink.do?link=main/inc/NgiIncUtil" /></div>
	    <!-- //하단 유틸메뉴 -->
	
	
	    <!-- 하단 배너 -->
	    <div class="MBanner"><c:import url="/EgovPageLink.do?link=main/inc/NgiIncBanner" /></div>
	    <!-- //하단 배너 -->
	
		<!-- //내용 -->
		<!-- footer 시작 -->
		<div id="footer"><c:import url="/EgovPageLink.do?link=main/inc/NgiIncFooter" /></div>
		<!-- //footer 끝 -->
	</div>
</div> <!-- //wrap end -->
</body>
</html>