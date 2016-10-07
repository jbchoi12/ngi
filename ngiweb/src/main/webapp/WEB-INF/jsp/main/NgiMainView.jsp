<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import ="egovframework.com.cmm.LoginVO" %>

<%@ page import="java.net.URL"%>
<%@ page import="java.net.URLConnection"%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.InputStreamReader"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="org.springframework.util.FileCopyUtils"%>
<%@ page import="org.springframework.util.xml.DomUtils"%>
<%@ page import="org.springframework.web.util.HtmlUtils"%>
<%@ page import="org.jdom2.Attribute"%>
<%@ page import="org.jdom2.DocType"%>
<%@ page import="org.jdom2.Document"%>
<%@ page import="org.jdom2.Element"%>
<%@ page import="org.jdom2.JDOMException"%>
<%@ page import="org.jdom2.input.SAXBuilder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>국토변화정보포털서비스</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/ngicommon.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/ngimain.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/js/jqplot/jquery.jqplot.min.css'/>">

<script type="text/javascript" src="<c:url value='/js/default.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-1.7.2.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqplot/jquery.jqplot.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqplot/plugins/jqplot.pieRenderer.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/gnb_main.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/mapmain.js'/>"></script>
</head>
<body>
<!-- wrap start -->
<div id="wrap">
	<!-- header start -->
	<div id="header"><c:import url="/sym/mms/NgiMainMenuHead.do?flag=MAIN" /></div>
	<!-- //header end -->
	
	
	<div id="container">
		<!-- 비주얼영역 -->   
		<div class="ViSpace">
		<div class="ViSImg">
		
		<!-- 4개중요버튼 -->
		<div class="BBbt">
		    <ul>
		        <li><a href="<c:url value='/ngi/mng/NgiMngView.do?tabNum=3'/>"><img src="<c:url value='/images/main/main_4bt_1.png'/>" alt="지형변화정보조회" title="지형변화정보조회"></a></li>
		        <li><a href="<c:url value='/ngi/mng/NgiMngView.do?tabNum=1&method=input'/>"><img src="<c:url value='/images/main/main_4bt_2.png'/>" alt="변경신고" title="변경신고"></a></li>
		        <li><a href="<c:url value='/ngi/api/NgiApiIntroduce.do'/>"><img src="<c:url value='/images/main/main_4bt_3.png'/>" alt="외부연계서비스" title="외부연계서비스"></a></li>
		        <li><a href="<c:url value='/ngi/mng/NgiMngView.do?tabNum=1'/>"><img src="<c:url value='/images/main/main_4bt_4.png'/>" alt="변경신고조회" title="변경신고조회"></a></li>
		    </ul>
		</div>
		<!-- //4개중요버튼 -->
		
		  <div class="mainTxt"><img src="<c:url value='/images/main/main_txt_1.png'/>" alt="국토변화정보의 수집 및 활용"><br>
		<img src="<c:url value='/images/main/main_txt_2.png'/>" alt="지형지물변동관리시스템이 만들겠습니다."></div>
		
		</div>
		</div>
		<!-- //비주얼영역 -->
		
		
		<div class="McontainerIn">
		
		<!-- step 1 -->  
		<div class="ConBox marginR10">
			<div class="stepTitle">
		        <h2><img src="<c:url value='/images/main/step_1.png'/>" alt="step1"><br>
		        전국</h2>
			</div>
			<div class="stepMent">원하시는 지역을<br>클릭하세요</div>
			<p style=" margin:10px 0 0 60px;"><img src="<c:url value='/images/main/step1_all.png'/>" alt="샘플지도" usemap="#mainMap" id="step1"></p>
		    </div>
			<map name="mainMap" id="mainMap">
			  <area shape="poly" coords="40,118,27,125,32,132,24,141,28,145,21,151,28,155,35,149,44,151,46,149,48,156,63,154,67,156,69,148,68,140,77,130,79,123,68,120,63,124,58,119" href="javascript:;" onClick="mapAction('junbuk','45');" alt="junbuk" />
			  <area shape="poly" coords="25,49,31,49,33,48,36,50,37,53,38,57,34,62,30,61,26,55" href="javascript:;" onClick="mapAction('inchon','28')" alt="inchon" />
			  <area shape="poly" coords="37,51,42,47,47,46,50,49,51,53,51,58,47,59,42,58,38,55" href="javascript:;" onClick="mapAction('seoul','11')" alt="seoul" />
			  <area shape="poly" coords="26,49,19,48,16,40,22,38,30,40,33,32,47,19,54,24,60,26,66,32,66,37,64,40,65,48,75,52,72,67,54,80,44,81,36,79,36,71,32,70,35,64,33,62,39,57,45,59,51,59,52,53,50,49,46,45,41,47,37,51,34,48" href="javascript:;" onClick="mapAction('gyeonggi','41')" alt="gyeonggi" />
			  <area shape="poly" coords="129,152,120,159,116,159,113,163,115,168,124,167,130,162,134,154" href="javascript:;" onClick="mapAction('busan','26')" alt="busan" />
			  <area shape="poly" coords="53,81,59,88,56,91,58,96,60,101,63,103,65,109,64,114,67,120,78,122,83,112,76,111,77,99,74,96,82,90,85,85,91,84,97,85,104,73,95,70,93,66,84,67,81,64,75,70,72,68,64,75" href="javascript:;" onClick="mapAction('chungbuk','43')" alt="chungbuk" />
			  <area shape="poly" coords="49,89,56,90,59,88,52,81,41,81,32,77,26,74,19,76,17,81,11,88,17,95,19,106,22,109,27,112,26,117,30,122,40,118,47,117,57,119,63,123,67,121,64,115,60,116,58,115,55,116,53,114,52,108,54,105,50,104" href="javascript:;" onClick="mapAction('chungnam','44')" alt="chungnam" />
			  <area shape="poly" coords="109,122,101,125,97,129,98,134,97,139,99,142,104,140,106,136,109,137,111,131,113,126" href="javascript:;" onClick="mapAction('daegu','27')" alt="daegu" />
			  <area shape="poly" coords="54,104,60,102,64,105,64,109,63,114,61,116,58,114,55,116,53,111" href="javascript:;" onClick="mapAction('daejeon','30')" alt="daejeon" />
			  <area shape="poly" coords="47,18,54,15,86,16,96,4,110,30,114,33,125,49,125,54,134,70,127,72,123,71,116,72,110,72,104,74,95,69,95,66,86,66,81,64,76,69,73,68,76,52,67,48,65,40,67,33,63,28,59,24,53,22" href="javascript:;" onClick="mapAction('gangwon','42')" alt="gangwon" />
			  <area shape="poly" coords="31,159,40,158,46,162,46,166,43,168,39,168,37,169,34,166,31,166" href="javascript:;" onClick="mapAction('gwangju','29')" alt="gwangju" />
			  <area shape="poly" coords="133,71,119,73,104,75,98,85,86,86,78,94,76,96,78,99,78,105,77,111,83,111,83,116,79,122,80,128,87,131,90,136,91,139,96,139,96,130,99,125,108,122,114,126,111,136,107,138,106,141,113,141,118,139,121,139,126,135,130,136,138,138,142,125,143,120,138,118,136,114,138,101,136,95,139,88,137,75" href="javascript:;" onClick="mapAction('gyeongbuk','47')" alt="gyeongbuk" />
			  <area shape="poly" coords="114,168,113,163,116,159,120,158,128,152,123,147,119,145,121,140,110,143,103,142,99,143,95,140,90,140,85,132,80,130,73,136,70,141,70,149,68,156,75,168,74,174,80,172,83,171,86,175,94,175,97,177,101,177,103,166,108,165" href="javascript:;" onClick="mapAction('gyeongnam','48')" alt="gyeongnam " />
			  <area shape="poly" coords="2,222,12,215,26,213,31,214,33,219,31,223,23,228,12,229,5,229,1,226" href="javascript:;" onClick="mapAction('jeju','50')" alt="jeju" />
			  <area shape="poly" coords="50,90,50,97,51,104,60,102,58,96,56,91" href="javascript:;" onClick="mapAction('sejong','36')" alt="sejong" />
			  <area shape="poly" coords="138,139,132,138,126,137,122,140,120,144,124,147,128,151,133,153,136,148,138,146" href="javascript:;" onClick="mapAction('ulsan','31')" alt="ulsan" />
			  <area shape="poly" coords="20,152,18,160,20,166,15,175,18,177,15,185,19,190,12,192,8,197,12,200,23,195,24,201,26,204,35,199,43,195,47,187,53,184,54,187,49,190,47,194,52,197,58,195,61,190,60,185,64,190,74,182,73,174,74,168,69,161,65,156,60,155,50,157,46,151,39,151,36,150,37,158,42,158,44,161,47,163,47,166,44,169,40,169,36,169,33,168,30,167,30,162,31,158,35,157,34,153,28,155" href="javascript:;" onClick="mapAction('junnam','46')" alt="junnam" />
			</map>		    

		<!-- //step 1 --> 
		
		<!-- step 2 -->    
		<div class="ConBoxC marginR20">
			<div class="stepTitle">
		        <h2><img src="<c:url value='/images/main/step_2.png'/>" alt="step1"><br>
				그래프 및 범례</h2>
				<div id="chart1" style="height: 202px;width: 373px;"></div>
		    </div>

		</div>
		<!-- //step 2 --> 
		
		<!-- step 3 -->     
		<div class="ConBoxR">
		  <div class="step3List">
		  <h3>전체신고순위</h3>
		            <ul>
			        <c:forEach var="result" items="${scoreList}" varStatus="status">
		                <li class="mnum1"><b><c:out value="${result.userId}" /></b>
		                <em><c:out value="${result.score}" /> 점</em>
		                </li>			        
			        </c:forEach>			            
		            </ul>
		<p>*신고 포인트 순위입니다. </p>
		    </div>
		</div>
		<!-- //step 3 --> 
		
		
		
		
		<!-- 공지사항 -->
		<div class="Notice">
		    <h3>공지사항</h3>
		        <ul>
			        <c:forEach var="result" items="${notiList}" varStatus="status">
			            <li>
			            <a href="<c:url value='/cop/bbs/selectBoardList.do?bbsId=BBSMSTR_AAAAAAAAAAAA'/>">
				            <c:if test="${result.replyLc!=0}">
                                <c:forEach begin="0" end="${result.replyLc}" step="1">
                                    &nbsp;
                                </c:forEach>
                                <img src="<c:url value='/images/reply_arrow.gif'/>" alt="reply arrow"/>
                            </c:if>
                            <c:choose>
                                <c:when test="${result.isExpired=='Y' || result.useAt == 'N'}">
                                    <c:out value="${result.nttSj}" />
                                </c:when>
                                <c:otherwise>
                                    <c:out value="${result.nttSj}" />
                                </c:otherwise>
                            </c:choose>
				            </a>
			            <em><c:out value="${result.frstRegisterPnttm}"/></em>
			            </li>
			        </c:forEach>
		        </ul>
				<p class="bmore"><a href="<c:url value='/cop/bbs/selectBoardList.do?bbsId=BBSMSTR_AAAAAAAAAAAA'/>">+더보기</a></p>
		</div>
		<!-- //공지사항 -->
		    
		<!-- 신고게시글 -->
		<div class="Report">
		    <h3>신고게시글</h3>
				<ul>
			        <c:forEach var="result" items="${sttemntList}" varStatus="status">
			            <li>
			            <a href="<c:url value='/ngi/mng/NgiMngView.do?tabNum=1&changeInfoId=${result.changeInfoId}'/>">
                                    <c:out value="${result.changeSj}" />
			            </a>
			            <em><c:out value="${result.registDe}"/></em>
			            </li>
			        </c:forEach>				
		    	</ul>
				<p class="bmore"><a href="<c:url value='/ngi/mng/NgiMngView.do'/>">+더보기</a></p>
		</div>
		<!-- //신고게시글 -->
		
		<!-- qna -->
		    <div class="qna">
		        <h3>Q&amp;A</h3>
		        <ul>
			        <c:forEach var="result" items="${qnaList}" varStatus="status">
			            <li>
			            <a href = "<c:url value='/uss/olh/qna/QnaListInqire.do'/>" >
			               <c:out value="${result.qestnSj}"/>
			            </a>
			            <em><c:out value='${fn:substring(result.writngDe, 0,4)}'/>-<c:out value='${fn:substring(result.writngDe, 4,6)}'/>-<c:out value='${fn:substring(result.writngDe, 6,8)}'/></em>
			            </li>
			        </c:forEach>
		        </ul>
		    <p class="bmore"><a href="<c:url value='/uss/olh/qna/QnaListInqire.do'/>">+더보기</a></p>
		    </div>
		<!-- //qna -->
		
		
		
		
		</div>
		
    <!-- 하단 유틸메뉴 -->
    <div class="MBotUtill"><c:import url="/EgovPageLink.do?link=main/inc/NgiIncUtil" /></div>
    <!-- //하단 유틸메뉴 -->


    <!-- 하단 배너 -->
    <div class="MBanner"><c:import url="/EgovPageLink.do?link=main/inc/NgiIncBanner" /></div>
    <!-- //하단 배너 -->
	
	
	</div>
	<!-- footer 시작 -->
	<div id="footer"><c:import url="/EgovPageLink.do?link=main/inc/NgiIncFooter" /></div>
	<!-- //footer 끝 -->

</div>
<!-- //wrap end -->
</body>
</html>
<%

%>