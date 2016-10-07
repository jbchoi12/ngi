<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>사이트소개</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngicommon.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngimap.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngichart.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXJ.min.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXSelect.css">

<script type="text/javascript" src="<c:url value='/'/>js/axisj/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<c:url value='/'/>js/default.js"></script>
<script type="text/javascript" src="<c:url value='/'/>js/axisj/dist/AXJ.min.js"></script>
<script type="text/javascript" src="<c:url value='/'/>js/axisj/dist/AXSelect.min.js"></script>
<script type="text/javascript" src="<c:url value='/'/>js/highcharts/highcharts.js"></script>

<script type="text/javascript">
</script>
<script src="${pageContext.request.contextPath}/js/map/ngimngchartview.js"></script>
</head>
<body>
<!-- wrap start -->
<div id="Mapwrap">	
	<!-- header start -->
	<div id="header"><c:import url="/sym/mms/NgiMainMenuHead.do" /></div>	
	<!-- //header end -->
	<!-- 내용 -->
    <div id="container">
		<!-- 내용 -->
		<div id="Mapcontainer">
			<div class="MapLeftBu"><img src="<c:url value='/'/>images/map/main_left_top_b.png" alt="" ></div>
		    <div class="mapTop">
		    <div class="mapTopL">
				<!-- 검색 -->
				<div class="Search">
				</div>
		        <!-- //검색 -->
		        <div class="Mmenu">
		            <ul>
		                <li><a href="#"> </a></li> 
		                <li><a href="#"> </a></li> 
		                <li><a href="#"> </a></li> 
		                <li><a href="#"> </a></li> 
		            </ul>
		        </div>
			</div>
		    <div class="mapTopR">
		    </div>
		</div>
		    
		<!-- 왼쪽 메뉴 -->     
		<div class="MapLeft" style="">
		   	
		    <ul class="MapLL">
			    <li><a href="<c:url value='/'/>ngi/mng/NgiMngView.do" title="지도"><div class="map"></div></a></li>
			    <li><a href="#" title="통계"><div class="stat active"></div></a></li>
		    </ul>
		    <div class="MapLR" id="MapLR">
			    <div class="chartsMenuLR">
			    	<div class="chartHeader"><span>통계보기</span></div>
			    	<div class="chartYearSelect">
						<select name="chart_year" class="AXSelect" id="chart_year" style="width:180px;">
						<c:forEach items="${yearList}" var="data">
							<option value="${data.name}">${data.name}년</option>
						</c:forEach>
						</select>
					</div>
				    <div class="charts">
				    	<ul class="charts_ul">
				    		<li class="on"><input type="button" class="AXButton" style="width:160px;" value="변경 신고 통계" /></li>
				    		<li><input type="button" class="AXButton" style="width:160px;" value="변동 보고 통계" /></li>
				    		<li><input type="button" class="AXButton" style="width:160px;" value="변화 정보 통계" /></li>
				    	</ul>
					</div>
				</div>
			</div>
		</div>
		<!-- //오른쪽 메뉴 -->  
		<div class="MapRight" style="width:0px;"> </div>
		<div id="mapContent" style="margin: 1px;"><!-- 차트영역. -->
			<ul class="charts_content">
	    		<li>
	    			<div class="chartContent on">
	    				<div id="chart0" class="charts"></div>
	    				<div id="chart1" class="charts"></div>
	    				<div id="chart2" class="charts"></div>
	    				<div id="chart3" class="charts"></div>
					</div>
	    		</li>
	    	</ul>
		</div>
	</div>
	<!-- //내용 -->
	<!-- footer 시작 -->
	<div id="footer"><c:import url="/EgovPageLink.do?link=main/inc/NgiIncFooter" /></div>
	<!-- //footer 끝 -->
</div>
</div>
<!-- //wrap end -->
</body>
</html>