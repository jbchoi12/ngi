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
                <li class="side2_li"><a href="<c:url value='/'/>ngi/api/NgiApiReperence.do" class="side2_aon">Open API 레퍼런스</a></li>
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
            <a href="#" class="History2">연계서비스</a> &gt; <a href="#" class="History3">Open API</a>&gt; <a href="#" class="History3">Open API 레퍼런스</a>
        </div>
        <!-- //history -->
        <h3>Open API 레퍼런스</h3>         
    </div>

	<div class="ConMView">
 		<div>
			<h2 style="margin-bottom:16px;">1. 지형변화정보 조회 요청형식</h2>
			<div class="int_openapi">
				<div class="int_s">
					<p class="int_s_t">요청 URL</p>
					<p class="int_s_s">
						<c:choose>
						<c:when test="${pageContext.request.serverPort != '80'}">
						<span> - <c:out value="${pageContext.request.scheme}"/>://<c:out value="${pageContext.request.serverName}"/>:<c:out value="${pageContext.request.serverPort}" /><c:out value="${pageContext.request.contextPath}" />/ngi/api/search.do?[요청파라미터]</span>
						</c:when>
						<c:otherwise>
						<span> - <c:out value="${pageContext.request.scheme}"/>://<c:out value="${pageContext.request.serverName}" /><c:out value="${pageContext.request.contextPath}" />/ngi/api/search.do?[요청파라미터]</span>
						</c:otherwise>
						</c:choose>							
					</p>
				</div>
				<div class="int_s">
					<p class="int_s_t">설명</p>
					<p class="int_s_s">- 요청방식 : GET, POST</p>
					<p class="int_s_s">- 요청결과 : JSON</p>
					<p class="int_s_s">- 인코딩 : UTF-8</p>
					<p class="int_s_s">- GET방식의 경우 IE브라우저의 문자열 제한으로 2000자 까지만 요청 가능하므로 POST방식을 권장합니다.</p>
					<p class="int_s_s">- 요청변수의 필수항목 파라미터는 반드시 값을 설정해야합니다. </p>
					<p class="int_s_s">- 요청변수의 선택항목은 값을 설정하지 않은 경우 기본값으로 설정되어 요청됩니다. </p>
					<p class="int_s_s">- 좌표계는 WGS84 경위도(EPSG:4326) 좌표를 지원합니다. 추후 지원 좌표계를 확대하도록 하겠습니다.</p>
				</div>
			</div>
		</div>
		
		<div style="margin-top:40px;">
			<h2 style="margin-bottom:16px;">2. 지형변화정보 조회</h2>
			<h3>1) 요청 파라미터</h3>
			<table class="tb_openapi">
				<caption>1) 요청 파라미터</caption>
				<colgroup>
		            <col style="width:92px;">
		            <col style="width:52px;">
		            <col style="width:153px;">
		            <col style="width:170px;">
		            <col style="width:256px;">
		        </colgroup>
		        <thead style="font-family: monospace;">
			        <tr>
			            <th scope="col">요청변수</th>
			            <th scope="col">조건</th>
			            <th scope="col">설정값</th>
			            <th scope="col">기본값</th>
			            <th scope="col">설명</th>
			        </tr>
		        </thead>
				<tbody class="refer">
					<tr>
						<td><div>key</div></td>
						<td class="cen">필수</td>
						<td class="cen"></td>
						<td class="cen"></td>
						<td><div>API사용을 위해 발급받은 OpenAPI키</div></td>
					</tr>
					<tr>
						<td><div>type</div></td>
						<td class="cen">필수</td>
						<td class="cen">list</td>
						<td class="cen">list</td>
						<td><div>지형변화정보 조회 리스트를 받는다.</div></td>
					</tr>
					<tr>
						<td><div>changeTy</div></td>
						<td class="cen">선택</td>
						<td class="cen">
							<p>01: 도로 </p>
							<p>02: 택지 </p>
							<p>03: 하천 </p>
							<p>04: 철도 </p>
							<p>05: 산업 </p>
							<p>06: 항만 </p>
							<p>07: 수자원 </p>
							<p>08: 공항 </p>
							<p>09: 매립 </p>
							<p>10: 관광 </p>
							<p>11: 특정 </p>
							<p>12: 체육 </p>
							<p>13: 폐기물 </p>
							<p>14: 주기 </p>
						</td>
						<td class="cen"><div>예) 01</div></td>
						<td><div>지형변화정보 변동유형</div></td>
					</tr>
<!-- 					
					<tr>
						<td><div>processSttusSe</div></td>
						<td class="cen">선택</td>
						<td class="cen">
							<p>01: 접수중 </p>
							<p>02: 접수완료 </p>
							<p>03: 보완 </p>
							<p>04: 보류 </p>
							<p>05: 반려 </p>
							<p>06: 접수취소 </p>
							<p>07: 지도수정중 </p>
							<p>11: 지도수정완료 </p>
						</td>
						<td class="cen"><div>예) 01</div></td>
						<td><div>지형변화정보 처리상태</div></td>
					</tr>
					<tr>
						<td><div>psitnEngnNo</div></td>
						<td class="cen">선택</td>
						<td class="cen">
							<p>01: 기관명.</p>
							<p>01: 기관명.</p>
						</td>
						<td class="cen"><div>예) 01</div></td>
						<td><div>소속기관명</div></td>
					</tr> 
					<tr>
						<td><div>sido</div></td>
						<td class="cen">선택</td>
						<td class="cen"><div>XX</div></td>
						<td class="cen"></td>
						<td><div>시도 행정코드 (201x년도 기준)</div></td>
					</tr>
					<tr>
						<td><div>sigun</div></td>
						<td class="cen">선택</td>
						<td class="cen"><div>XXXXX</div></td>
						<td class="cen"></td>
						<td><div>시군구 행정코드 (201x년도 기준)</div></td>
					</tr>
					<tr>
						<td><div>dong</div></td>
						<td class="cen">선택</td>
						<td class="cen"><div>XXXXXXX</div></td>
						<td class="cen"></td>
						<td><div>읍면동 행정코드 (201x년도 기준)</div></td>
					</tr> 
					<tr>
						<td><div>cntrwkNo</div></td>
						<td class="cen">선택</td>
						<td class="cen"><div></div></td>
						<td class="cen">예) 2014000121</td>
						<td><div>공사번호</div></td>
					</tr>

					<tr>
						<td><div>const_before</div></td>
						<td class="cen">선택</td>
						<td class="cen"><div>YYYYMMDD</div></td>
						<td class="cen">예) 20130101</td>
						<td><div>착공시작일</div></td>
					</tr>
					<tr>
						<td><div>const_after</div></td>
						<td class="cen">선택</td>
						<td class="cen"><div>YYYYMMDD</div></td>
						<td class="cen">예) 20140101</td>
						<td><div>완공예정일</div></td>
					</tr> 
 					<tr>
						<td><div>mapindex</div></td>
						<td class="cen">선택</td>
						<td class="cen"><div>XXXXX</div></td>
						<td class="cen">예) 37709</td>
						<td>
							<div>
								<p>* 도엽번호별 조회시 입력</p>
								<p>1:50000 도엽 인덱스번호</p>
							</div>
						</td>
					</tr>
					<tr>
						<td><div>bbox</div></td>
						<td class="cen">선택</td>
						<td class="cen"><div>left,bottom,right,top</div></td>
						<td class="cen">예) 126.9460,37.3560<br>,126.9460,37.3560</td>
						<td>
							<div>
								<p>* 위치기반 조회시 입력</p>
								<p>bounding box</p>
							</div>
						</td>
					</tr> -->
					<tr>
						<td><div>pageUnit</div></td>
						<td class="cen">선택</td>
						<td class="cen"><div>XXX</div></td>
						<td class="cen">10</td>
						<td>
							<div>
								<p>검색결과 페이지당 출력건수</p>
								<p>최소: 10, 최대: 100</p>
							</div>
						</td>
					</tr>
					<tr>
						<td><div>pageNum</div></td>
						<td class="cen">선택</td>
						<td class="cen"><div>XXX</div></td>
						<td class="cen">1</td>
						<td><div>페이지번호</div></td>
					</tr>
					<tr>
						<td><div>lastCompetBgnde</div></td>
						<td class="cen">선택</td>
						<td class="cen"><div>YYYY-MM-DD</div></td>
						<td class="cen"></td>
						<td><div>조회시작일(완공일기준)</div></td>
					</tr>
					<tr>
						<td><div>lastCompetEndde</div></td>
						<td class="cen">선택</td>
						<td class="cen"><div>YYYY-MM-DD</div></td>
						<td class="cen"></td>
						<td><div>조회완료일(완공일기준)</div></td>
					</tr>
					
<!-- 					
					<tr>
						<td><div>pageSort</div></td>
						<td class="cen">선택</td>
						<td class="cen">
							<p>desc: 내림차순</p>
							<p>asc: 오름차순</p>
						</td>
						<td class="cen">desc</td>
						<td><div>조회결과 정렬</div></td>
					</tr>
 -->					
					
				</tbody>
			</table>
		</div>
		
		<div style="margin-top:40px;">
			<h3>2) 요청 결과 필드</h3>
			<table class="tb_openapi">
				<caption>2) 요청 결과 필드</caption>
				<colgroup>
		            <col>
		            <col style="width:167px;">
		            <col style="width:178px;">
		            <col style="width:277px;">
		        </colgroup>
		        <thead style="font-family: monospace;">
			        <tr>
			            <th scope="col">변수</th>
			            <th scope="col">데이터타입</th>
			            <th scope="col">반환값</th>
			            <th scope="col">설명</th>
			        </tr>
		        </thead>
				<tbody class="refer">
<!-- 					<tr>
						<td><div>changeid</div></td>
						<td class="cen">string</td>
						<td class="cen">예) 128</td>
						<td><div>지형변화정보 조회번호</div></td>
					</tr> -->
					<tr>
						<td><div>cntrwkNo</div></td>
						<td class="cen">string</td>
						<td class="cen"><div>예) 2014000121</div></td>
						<td><div>공사번호</div></td>
					</tr>
					<tr>
						<td><div>changeSj</div></td>
						<td class="cen">string</td>
						<td class="cen">예) OOOO 도로확장공사 </td>
						<td><div>지형변화정보 공사명</div></td>
					</tr>
					<tr>
						<td><div>changeTy</div></td>
						<td class="cen">string</td>
						<td class="cen">
							<p>도로 </p>
							<p>택지 </p>
							<p>하천 </p>
							<p>철도 </p>
							<p>산업 </p>
							<p>항만 </p>
							<p>수자원 </p>
							<p>공항 </p>
							<p>매립 </p>
							<p>관광 </p>
							<p>특정 </p>
							<p>체육 </p>
							<p>폐기물 </p>
							<p>주기 </p>
						</td>
						<td><div>지형변화정보 변동유형</div></td>
					</tr>
					<tr>
						<td><div>processSttusSe</div></td>
						<td class="cen">string</td>
						<td class="cen">
							<p>접수중 </p>
							<p>접수완료 </p>
							<p>보완 </p>
							<p>보류 </p>
							<p>반려 </p>
							<p>접수취소 </p>
							<p>지도수정중 </p>
							<p>지도수정완료 </p>
						</td>
						<td><div>지형변화정보 처리상태</div></td>
					</tr>
					<tr>
						<td><div>psitnEngnNo</div></td>
						<td class="cen">string</td>
						<td class="cen">
							<p>국토지리정보원</p>
							<p>국토해양부(국토청)</p>
							<p>서울지방국토관리청</p>
							<p>.......</p>
						</td>
						<td><div>소속기관명</div></td>
					</tr>
					<!-- <tr>
						<td><div>sido</div></td>
						<td class="cen">string</td>
						<td class="cen"><div>XX</div></td>
						<td><div>시도 행정코드 (201x년도 기준)</div></td>
					</tr>
					<tr>
						<td><div>sigun</div></td>
						<td class="cen">string</td>
						<td class="cen"><div>XXXXX</div></td>
						<td><div>시군구 행정코드 (201x년도 기준)</div></td>
					</tr>
					<tr>
						<td><div>dong</div></td>
						<td class="cen">string</td>
						<td class="cen"><div>XXXXXXX</div></td>
						<td><div>읍면동 행정코드 (201x년도 기준)</div></td>
					</tr> -->
					<!-- 
					<tr>
						<td><div>const_before</div></td>
						<td class="cen">string</td>
						<td class="cen"><div>YYYYMMDD</div></td>
						<td><div>착공시작일</div></td>
					</tr>
					<tr>
						<td><div>const_after</div></td>
						<td class="cen">string</td>
						<td class="cen"><div>YYYYMMDD</div></td>
						<td><div>완공예정일</div></td>
					</tr>
					<tr>
						<td><div>mapindex</div></td>
						<td class="cen">string</td>
						<td class="cen"><div>XXXXX</div></td>
						<td>
							<div>
								<p>1:50000 도엽 인덱스번호</p>
							</div>
						</td>
					</tr> -->
					<tr>
						<td><div>geom</div></td>
						<td  colspan="2">
							<span style="font-family:monospace; white-space:pre;">array : [ 
   {
      type:"POINT",  
      wkt:"POINT (30 10)"  
   },
   { 
      type:"LINESTRING",  
      wkt:"LINESTRING (30 10, 10 30, 40 40)" 
   },
   {  
      type:"POLYGON", 
      wkt:"POLYGON ((30 10, 40 40, 20 40, 10 20, 30 10))" 
   },
   {  
      type:"SHP", 
      wkt:"MULTIPOLYGON ((30 10, 40 40, 20 40, 10 20, 30 10))" 
   }
]
							</span> 
						</td>
						<td>
							<div>
								<p>지오메트리 정보</p>
								<p>WKT(Well-known text)</p>
							</div>
						</td>
					</tr>
					<tr>
						<td><div>pageUnit</div></td>
						<td class="cen">integer</td>
						<td class="cen"><div>XXX</div></td>
						<td>
							<div>
								<p>검색결과 페이지당 출력건수</p>
							</div>
						</td>
					</tr>
					<tr>
						<td><div>pageNum</div></td>
						<td class="cen">integer</td>
						<td class="cen"><div>XXX</div></td>
						<td><div>현재 페이지 번호</div></td>
					</tr>
					<tr>
						<td><div>pageFirstNum</div></td>
						<td class="cen">integer</td>
						<td class="cen"><div>X</div></td>
						<td><div>첫 페이지 번호</div></td>
					</tr>
					<tr>
						<td><div>pageLastNum</div></td>
						<td class="cen">integer</td>
						<td class="cen"><div>XXX</div></td>
						<td><div>마지막 페이지 번호</div></td>
					</tr>
					<tr>
						<td><div>pageTotalCnt</div></td>
						<td class="cen">integer</td>
						<td class="cen"><div>XXX</div></td>
						<td><div>총 페이지 수</div></td>
					</tr>
					<tr>
						<td><div>unitTotalCnt</div></td>
						<td class="cen">integer</td>
						<td class="cen"><div>XXX</div></td>
						<td><div>전체 레코드 수</div></td>
					</tr>
				</tbody>
			</table>
		</div>
 
		<div style="margin-top:40px;">
			<h2 style="margin-bottom:16px;">3. 지형변화정보 상세조회</h2>
			<h3>1) 요청 파라미터</h3>
			<table class="tb_openapi">
				<caption>1) 요청 파라미터</caption>
				<colgroup>
		            <col style="width:92px;">
		            <col style="width:52px;">
		            <col style="width:153px;">
		            <col style="width:170px;">
		            <col style="width:256px;">
		        </colgroup>
		        <thead style="font-family: monospace;">
			        <tr>
			            <th scope="col">요청변수</th>
			            <th scope="col">조건</th>
			            <th scope="col">설정값</th>
			            <th scope="col">기본값</th>
			            <th scope="col">설명</th>
			        </tr>
		        </thead>
				<tbody class="refer">
					<tr>
						<td><div>key</div></td>
						<td class="cen">필수</td>
						<td class="cen"></td>
						<td class="cen"></td>
						<td><div>API사용을 위해 발급받은 OpenAPI키</div></td>
					</tr>
					<tr>
						<td><div>type</div></td>
						<td class="cen">필수</td>
						<td class="cen">detail</td>
						<td class="cen">detail</td>
						<td><div>지형변화정보 상세조회 요청을 한다.</div></td>
					</tr>
					<tr>
						<td><div>cntrwkNo</div></td>
						<td class="cen">필수</td>
						<td class="cen">XXX</td>
						<td class="cen"><div>예) 2014000121</div></td>
						<td><div>공사번호</div></td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<div style="margin-top:40px;">
			<h3>2) 요청 결과 필드</h3>
			<table class="tb_openapi">
				<caption>2) 요청 결과 필드</caption>
				<colgroup>
		            <col style="width:92px;">
		            <col style="width:52px;">
		            <col style="width:153px;">
		            <col style="width:170px;">
		        </colgroup>
		        <thead style="font-family: monospace;">
			        <tr>
			            <th scope="col">변수</th>
			            <th scope="col">데이터타입</th>
			            <th scope="col">반환값</th>
			            <th scope="col">설명</th>
			        </tr>
		        </thead>
				<tbody class="refer">
<!-- 					<tr>
						<td><div>changeid</div></td>
						<td class="cen">string</td>
						<td class="cen">예) 128</td>
						<td><div>지형변화정보 조회번호</div></td>
					</tr> -->
					<tr>
						<td><div>cntrwkNo</div></td>
						<td class="cen">string</td>
						<td class="cen">예) 2014000121<div></div></td>
						<td><div>공사번호</div></td>
					</tr>
					<tr>
						<td><div>changeSj</div></td>
						<td class="cen">string</td>
						<td class="cen">예) OOOO 도로확장공사 </td>
						<td><div>지형변화정보 공사명</div></td>
					</tr>
					<tr>
						<td><div>changeTy</div></td>
						<td class="cen">string</td>
						<td class="cen">
							<p>도로 </p>
							<p>택지 </p>
							<p>하천 </p>
							<p>철도 </p>
							<p>산업 </p>
							<p>항만 </p>
							<p>수자원 </p>
							<p>공항 </p>
							<p>매립 </p>
							<p>관광 </p>
							<p>특정 </p>
							<p>체육 </p>
							<p>폐기물 </p>
							<p>주기 </p>
						</td>
						<td><div>지형변화정보 변동유형</div></td>
					</tr>
<!-- 					
					<tr>
						<td><div>processSttusSe</div></td>
						<td class="cen">string</td>
						<td class="cen">
							<p>접수중 </p>
							<p>접수완료 </p>
							<p>보완 </p>
							<p>보류 </p>
							<p>반려 </p>
							<p>접수취소 </p>
							<p>지도수정중 </p>
							<p>지도수정완료 </p>
						</td>
						<td><div>지형변화정보 처리상태</div></td>
					</tr>
 --> 					
					<tr>
						<td><div>psitnEngnNo</div></td>
						<td class="cen">string</td>
						<td class="cen">
							<p>국토지리정보원</p>
							<p>국토해양부(국토청)</p>
							<p>서울지방국토관리청</p>
							<p>.......</p>
						</td>
						<td><div>소속기관명</div></td>
					</tr>
					<tr>
						<td><div>planEngnNo</div></td>
						<td class="cen">string</td>
						<td class="cen">
							<p>국토지리정보원</p>
							<p>국토해양부(국토청)</p>
							<p>서울지방국토관리청</p>
							<p>.......</p>
						</td>
						<td><div>계획기관</div></td>
					</tr>
					<tr>
						<td><div>mngEngnNo</div></td>
						<td class="cen">string</td>
						<td class="cen">
							<p>국토지리정보원</p>
							<p>국토해양부(국토청)</p>
							<p>서울지방국토관리청</p>
							<p>.......</p>
						</td>
						<td><div>감독기관</div></td>
					</tr>
					<!-- <tr>
						<td><div>sido</div></td>
						<td class="cen">string</td>
						<td class="cen"><div>XX</div></td>
						<td><div>시도 행정코드 (201x년도 기준)</div></td>
					</tr>
					<tr>
						<td><div>sigun</div></td>
						<td class="cen">string</td>
						<td class="cen"><div>XXXXX</div></td>
						<td><div>시군구 행정코드 (201x년도 기준)</div></td>
					</tr>
					<tr>
						<td><div>dong</div></td>
						<td class="cen">string</td>
						<td class="cen"><div>XXXXXXX</div></td>
						<td><div>읍면동 행정코드 (201x년도 기준)</div></td>
					</tr> -->
					<tr>
						<td><div>strwrkDe</div></td>
						<td class="cen">string</td>
						<td class="cen"><div>YYYYMMDD</div></td>
						<td><div>착공시작일</div></td>
					</tr>
					<tr>
						<td><div>competPrearngeDe</div></td>
						<td class="cen">string</td>
						<td class="cen"><div>YYYYMMDD</div></td>
						<td><div>완공예정일</div></td>
					</tr>
					<tr>
						<td><div>lastCompetDe</div></td>
						<td class="cen">string</td>
						<td class="cen"><div>YYYYMMDD</div></td>
						<td><div>최종완공일</div></td>
					</tr>
					<tr>
						<td><div>chargerNm</div></td>
						<td class="cen">string</td>
						<td class="cen"><div>OOO</div></td>
						<td><div>담당자명</div></td>
					</tr>
					<tr>
						<td><div>chrgDeptNm</div></td>
						<td class="cen">string</td>
						<td class="cen"><div>OOO</div></td>
						<td><div>담당부서</div></td>
					</tr>
					<tr>
						<td><div>chargerTlphonNo</div></td>
						<td class="cen">string</td>
						<td class="cen"><div>OOO</div></td>
						<td><div>연락처</div></td>
					</tr>
					<tr>
						<td><div>chargerEmail</div></td>
						<td class="cen">string</td>
						<td class="cen"><div>OOO</div></td>
						<td><div>이메일</div></td>
					</tr>
					<tr>
						<td><div>cntm</div></td>
						<td class="cen">string</td>
						<td class="cen"><div>OOO</div></td>
						<td><div>좌표계</div></td>
					</tr>
					<tr>
						<td><div>rm</div></td>
						<td class="cen">string</td>
						<td class="cen"><div>OOO</div></td>
						<td><div>비고</div></td>
					</tr>
					<!-- <tr>
						<td><div>mapindex</div></td>
						<td class="cen">string</td>
						<td class="cen"><div>XXXXX</div></td>
						<td>
							<div>
								<p>1:50000 도엽 인덱스번호</p>
							</div>
						</td>
					</tr> -->
					<tr>
						<td><div>changeRnAdresCn</div></td>
						<td class="cen">string</td>
						<td class="cen"><div> </div></td>
						<td><div>대상지역</div></td>
					</tr>
					<tr>
						<td><div>cntrwkPnttm</div></td>
						<td class="cen">string</td>
						<td class="cen"><div>YYYYMMDD</div></td>
						<td><div>공사시점</div></td>
					</tr>
					<tr>
						<td><div>cntrwkTmnl</div></td>
						<td class="cen">string</td>
						<td class="cen"><div>YYYYMMDD</div></td>
						<td><div>공사종점</div></td>
					</tr>
					<tr>
						<td><div>ar</div></td>
						<td class="cen">string</td>
						<td class="cen"><div> </div></td>
						<td><div>면적</div></td>
					</tr>
					<tr>
						<td><div>extn</div></td>
						<td class="cen">string</td>
						<td class="cen"><div> </div></td>
						<td><div>연장</div></td>
					</tr>
					<tr>
						<td><div>trgetBfchgCn</div></td>
						<td class="cen">string</td>
						<td class="cen"><div> </div></td>
						<td><div>대상변경전</div></td>
					</tr>
					<tr>
						<td><div>trgetAftchCn</div></td>
						<td class="cen">string</td>
						<td class="cen"><div> </div></td>
						<td><div>대상변경후</div></td>
					</tr>
					<tr>
						<td><div>geom</div></td>
						<td  colspan="2">
							<span style="font-family:monospace; white-space:pre;">array : [ 
   {
      type:"POINT",  
      wkt:"POINT (30 10)"  
   },
   { 
      type:"LINESTRING",  
      wkt:"LINESTRING (30 10, 10 30, 40 40)" 
   },
   {  
      type:"POLYGON", 
      wkt:"POLYGON ((30 10, 40 40, 20 40, 10 20, 30 10))" 
   },
   {  
      type:"SHP", 
      wkt:"MULTIPOLYGON ((30 10, 40 40, 20 40, 10 20, 30 10))" 
   }
]
							</span> 
						</td>
						<td>
							<div>
								<p>지오메트리 정보</p>
								<p>WKT(Well-known text)</p>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
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