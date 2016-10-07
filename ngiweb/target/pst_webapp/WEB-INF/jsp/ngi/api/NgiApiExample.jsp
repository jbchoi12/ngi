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
                <li class="side2_li"><a href="<c:url value='/'/>ngi/api/NgiApiExample.do" class="side2_aon">Open API 예제</a></li>
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
            <a href="#" class="History2">연계서비스</a> &gt; <a href="#" class="History3">Open API</a>&gt; <a href="#" class="History3">Open API 예제</a>
        </div>
        <!-- //history -->
        <h3>Open API 예제</h3>         
    </div>


	<div class="ConMView">
		<div>
	 		<h2 style="margin-bottom:16px;">1. 지형변화정보 조회 요청 예제</h2>
	 		<div class="int_openapi">
	 			<div class="int_s">
					<p class="int_s_t">요청 URL</p>
					<p class="int_s_s">
						<c:choose>
						<c:when test="${pageContext.request.serverPort != '80'}">
						<span> - <c:out value="${pageContext.request.scheme}"/>://<c:out value="${pageContext.request.serverName}"/>:<c:out value="${pageContext.request.serverPort}" /><c:out value="${pageContext.request.contextPath}" />/ngi/api/search.do?</span>
						</c:when>
						<c:otherwise>
						<span> - <c:out value="${pageContext.request.scheme}"/>://<c:out value="${pageContext.request.serverName}" /><c:out value="${pageContext.request.contextPath}" />/ngi/api/search.do?</span>
						</c:otherwise>
						</c:choose>		
					</p>
					<p class="int_s_t">파라미터</p>
					<p class="int_s_s"> - key=발급API키&type=list&pageUnit=2	 </p>
					
					<p class="int_s_t">요청결과</p>
					<p class="int_s_s" style="border: 1px solid #B0A9A9;padding: 5px 20px;">
					<span style="font-family:monospace; white-space:pre;">{
   "data": {
      "pageInfo": {
         "pageUnit": 2,
         "pageNum": 1,
         "pageFirstNum": 1,
         "pageLastNum": 18,
         "pageTotalCnt": 18,
         "unitTotalCnt": 36
      },
      "listData": [
         {
            "cntrwkNo": "2014000096",
            "changeSj": "마동지구 다목적농촌용수개발사업",
            "changeTy": "매립",
            "processSttusSe": "접수중",
            "psitnEngnNo": "경상남도",
            "geom": {
               "proj": "EPSG:4326",
               "lines": [],
               "points": [],
               "polygons": []
            }
         },
         {
            "cntrwkNo": "2014000095",
            "changeSj": "성서5차첨단산업단지조성공사",
            "changeTy": "산업",
            "processSttusSe": "접수중",
            "psitnEngnNo": "대구광역시",
            "geom": {
               "proj": "EPSG:4326",
               "lines": [],
               "points": [],
               "polygons": []
            }
         }
      ]
   }
}
					</span>
					</p>
				</div>
			</div>
 		</div>
		<div style="margin-top:40px;">
	 		<h2 style="margin-bottom:16px;">2. 지형변화정보 상세조회 요청 예제</h2>
	 		<div class="int_openapi">
	 			<div class="int_s">
					<p class="int_s_t">요청 URL</p>
					<p class="int_s_s">
						<c:choose>
						<c:when test="${pageContext.request.serverPort != '80'}">
						<span> - <c:out value="${pageContext.request.scheme}"/>://<c:out value="${pageContext.request.serverName}"/>:<c:out value="${pageContext.request.serverPort}" /><c:out value="${pageContext.request.contextPath}" />/ngi/api/search.do?</span>
						</c:when>
						<c:otherwise>
						<span> - <c:out value="${pageContext.request.scheme}"/>://<c:out value="${pageContext.request.serverName}" /><c:out value="${pageContext.request.contextPath}" />/ngi/api/search.do?</span>
						</c:otherwise>
						</c:choose>		
					</p>
					<p class="int_s_t">파라미터</p>
					<p class="int_s_s"> - key=발급API키&type=detail&cntrwkNo=2014000096	 </p>
					
					<p class="int_s_t">요청결과</p>
					<p class="int_s_s" style="border: 1px solid #B0A9A9;padding: 5px 20px;">
					<span style="font-family:monospace; white-space:pre;">{
   "data": {
      "detailData": {
         "cntrwkNo": "2014000096",
         "changeSj": "마동지구 다목적농촌용수개발사업",
         "changeTy": "매립",
         "processSttusSe": "접수중",
         "psitnEngnNo": "경상남도",
         "planEngnNo": "충청남도 아산시",
         "mngEngnNo": "한국농어촌공사",
         "strwrkDe": "2002-12-17",
         "competPrearngeDe": "2012-12-10",
         "lastCompetDe": "2012-12-10",
         "chargerNm": "손옥석",
         "chrgDeptNm": "사업관리팀",
         "chargerTlphonNo": "018-646-9860",
         "chargerEmail": "dsge@sgd.co.kr",
         "cntm": "세계측지계(중부)",
         "rm": "",
         "changeRnAdresCn": "",
         "cntrwkPnttm": "경남 고성군 마암면 일원",
         "cntrwkTmnl": "경남 고성군 동해면 일원",
         "ar": "4080000.0",
         "extn": "0.83",
         "trgetBfchgCn": "미존재",
         "trgetAftchCn": "담수호,방조제",
         "geom": {
            "proj": "EPSG:4326",
            "lines": [],
            "points": [],
            "polygons": []
         }
      }
   }
}
					</span>
					</p>
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