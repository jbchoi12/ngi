<%--
  Class Name : NgiAboutSite.jsp
  Description : 지형지물변동관리
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
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngimap.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXInput.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXSelect.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXJ.min.css">
<link href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">

<script type="text/javascript" src="<c:url value='/'/>js/axisj/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<c:url value='/'/>js/axisj/dist/AXJ.min.js"></script>
<script type="text/javascript" src="<c:url value='/'/>js/axisj/lib/AXInput.js"></script>
<script type="text/javascript" src="<c:url value='/'/>js/defaultmap.js"></script>
<script type='text/javascript'>  

function tabs(idx){   
	
	if(!(document.getElementById('tab'+idx).className == "active")) {
		parent.resetVectorList();	// 도형정보 초기화
		if(idx==1) {
			parent.frames["iframeChgSttemntinfo"].location.href = "<c:url value='/'/>ngi/chg/ChangeInfoList.do";
		} else if(idx==2) {
			parent.frames["iframeChgCntrwkinfo"].location.href = "<c:url value='/'/>ngi/chg/ChangeCntrwkList.do";
		} else if(idx==3) {
			parent.frames["iframeTransinfo"].location.href = "<c:url value='/'/>ngi/chg/ChangeTrsInfoList.do";
		} else if(idx==4) {
			parent.frames["iframeAisinfo"].location.href = "<c:url value='/'/>ngi/chg/ChangeEaisInfoList.do";
		} else {
			
		}		
	}
	
    for(i = 1; i <= 4 ; i++ ){   
        document.getElementById('tab'+i).className = "";
        document.getElementById('ChangeView'+i).style.display = "none"; 
    }   
    document.getElementById('tab'+idx).className = "active";  
    document.getElementById('ChangeView'+idx).style.display = "block";
}   

window.onload = function() {
	contentHeight();
	$(".MapLL li")[0].click();
}
window.onresize = function() {
	contentHeight();
}
function contentHeight() {
	if (document.getElementById) {
		var windowHeight = getWindowHeight();
		var headerHeight = document.getElementById('header').offsetHeight;
		var footerHeight = document.getElementById('footer').offsetHeight;
		var contentElement = document.getElementById('Mapcontainer');
		//alert("windowHeight:"+windowHeight+",\nheaderHeight: "+headerHeight+", \nfooterHeight:"+footerHeight);
		contentElement.style.height = (windowHeight - (headerHeight + footerHeight)) + 'px';
		$('#mapContent').height( $('#Mapcontainer').height() - $('.mapTop').height());
		$('#mapContent').width( $('#Mapcontainer').width() - $('.MapLL').width() - $('.MapLR').width() - $('.MapRight').width());
		$('#mapContent').css('left', $('.MapLL').width() + $('.MapLR').width());
	}
	if (map != null)
		map.updateSize();
}
function getWindowHeight() {
	var windowHeight = 0;
	if (typeof (window.innerHeight) == 'number') {
		windowHeight = window.innerHeight;
	} else {
		if (document.documentElement
				&& document.documentElement.clientHeight) {
			windowHeight = document.documentElement.clientHeight;
		} else {
			if (document.body && document.body.clientHeight) {
				windowHeight = document.body.clientHeight;
			}
		}
	}
	return windowHeight;
}

//====================================================================================================================
//지형지물 도움말
//====================================================================================================================
function landchg_help (helpType) {
	var url = "";
	switch (helpType) {
		case "org": // 기관사용자 도움말
			url = "<c:url value='/'/>help/org_user/index.html";
			break;
		case "gen": // 일반사용자 도움말
			url = "<c:url value='/'/>help/gen_user/index.html";
			break;			
	}
	if (!url) return;
	
	var wnd = launchPopupCenter(url, "winPop", 850, 700);
	if (wnd != null) {
		wnd.focus ();
	}			
	
}

function launchPopupCenter(url, name, width, height) {
	var str = "height=" + height + ",innerHeight=" + height;
	str += ",width=" + width + ",innerWidth=" + width;
	str += ",menubar=0";
	if (window.screen) {
		var ah = screen.availHeight - 30;
		var aw = screen.availWidth - 10;

		var xc = (aw - width) / 2;
		var yc = (ah - height) / 2;

		str += ",left=" + xc + ",screenX=" + xc;
		str += ",top=" + yc + ",screenY=" + yc;
	}

	var popup = window.open(url, name, str);
	// popup.focus();
	return popup;
}

function p_search_reset() {
	$("#p_search").val("");
	$("#SearchResultLi_s").empty();
	parent.resetVectorList();	// 도형정보 초기화
}

function p_search() {
		
		var param = $("#p_search").val();
		if(param.length>8) {
			$.ajax({
				url : "<c:url value='/'/>ngi/chg/selectChangeCntrwkListJson.do?p_id="+param,
				type : "get",
				dataType : "json",
				success : function(result){
					
					$("#SearchResultLi_s").empty();
					$.each(result.resultList, function(k,data){
						var link = '<img src="<c:url value='/'/>images/map/mark_blue.gif" width="12" height="15" alt="위치표시">';
						$("#SearchResultLi_s").append('<p style="margin:5px;font-size:12pt;text-align: center;"><a href="#" onclick="javascript:parent.setPointOfMove('+  data.xCrdntLo + "," + data.yCrdntLa+  ')"><span style="margin-right:5px;">'+ data.changeSj +'</span>' + link + '</a></p>');
						$("#SearchResultLi_s").append('<p style="margin:29px 5px;text-align: right;"><input type="button" onclick="javascript:p_search_reset();" value="초기화" class="AXButton"></input></p>');
					});
				}
			});
		}
}
	
</script>

<script src="${pageContext.request.contextPath}/js/map/lib/proj4js-compressed.js"></script>
<!-- <script src="http://map.vworld.kr/js/apis.do?type=Base&apiKey=4A3E388D-8337-3E8E-B5DB-8D4A4BBAC464"></script>
-->
<script src="${pageContext.request.contextPath}/js/map/apikey.js"></script>
<script src="${pageContext.request.contextPath}/js/openlayers/OpenLayers.js"></script>
<script src="${pageContext.request.contextPath}/js/openlayers/HeatmapLayer.js"></script>
<script src="${pageContext.request.contextPath}/js/openlayers/FramedCloud.js"></script> 
<script src="${pageContext.request.contextPath}/js/openlayers/WMS_AIR.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/openlayers/theme/default/style.tidy.css" type="text/css" />
<script type="text/javascript">
$(function() {
	contentHeight();
});

var pageID = "Date";
var fnObj = {
	pageStart: function(){
		$("#AXInputDate_earlier").bindDate({align:"right", valign:"bottom", onChange:{ earlierThan:"AXInputDate_later", err:"종료일보다 빠른 날짜를 선택하세요"}});
		$("#AXInputDate_later").bindDate({align:"right", valign:"bottom", onChange:{ laterThan:"AXInputDate_earlier", err:"시작일보다 느린 날짜를 선택하세요"  } });
	}
};
jQuery(document).ready(function(){
	fnObj.pageStart.delay(0.1);
});
	
jQuery(window).load(function(){	
	tabNum = document.getElementById("detailForm").tabNum.value;
	method = document.getElementById("detailForm").method.value;
	changeInfoId = document.getElementById("detailForm").changeInfoId.value;
	
	if(tabNum=="1") {
		document.getElementById('tab'+tabNum).className = "active"; 
		document.getElementById('ChangeView'+tabNum).style.display = "block";
		if(changeInfoId == ""){
			if(method == "input"){
				parent.frames["iframeChgSttemntinfo"].location.href = "<c:url value='/'/>ngi/chg/addChangeInfoView.do";
			} else {
				parent.frames["iframeChgSttemntinfo"].location.href = "<c:url value='/'/>ngi/chg/ChangeInfoList.do";
			}
		} else {
			parent.frames["iframeChgSttemntinfo"].location.href = "<c:url value='/'/>ngi/chg/ChangeInfoInqire.do?changeInfoId="+changeInfoId;			
		}
	} else if(tabNum=="2") {
		document.getElementById('tab'+tabNum).className = "active";
		document.getElementById('ChangeView'+tabNum).style.display = "block";
		if(changeInfoId == ""){
			parent.frames["iframeChgCntrwkinfo"].location.href = "<c:url value='/'/>ngi/chg/ChangeCntrwkList.do";	
		} else {
			parent.frames["iframeChgCntrwkinfo"].location.href = "<c:url value='/'/>ngi/chg/ChangeCntrwkInqire.do?changeInfoId="+changeInfoId;			
		}		
	} else if(tabNum=="3") {
		document.getElementById('tab'+tabNum).className = "active";
		document.getElementById('ChangeView'+tabNum).style.display = "block";
		if(changeInfoId == ""){
			parent.frames["iframeTransinfo"].location.href = "<c:url value='/'/>ngi/chg/ChangeTrsInfoList.do";	
		} else {
			parent.frames["iframeTransinfo"].location.href = "<c:url value='/'/>ngi/chg/ChangeTrsInfoInqire.do?changeInfoId="+changeInfoId;			
		}		
	} else if(tabNum=="4") {
		document.getElementById('tab'+tabNum).className = "active";
		document.getElementById('ChangeView'+tabNum).style.display = "block";
		parent.frames["iframeAisinfo"].location.href = "<c:url value='/'/>ngi/chg/ChangeEaisInfoList.do";
	} else {
		document.getElementById('tab1').className = "active";
		document.getElementById('ChangeView1').style.display = "block";
		parent.frames["iframeChgSttemntinfo"].location.href = "<c:url value='/'/>ngi/chg/ChangeInfoList.do";
	}	
	
	
});


OpenLayers.ImgPath = "${pageContext.request.contextPath}/js/openlayers/imgs/";
OpenLayers.ProxyHost = "${pageContext.request.contextPath}/proxy.jsp?resourceUrl=";
</script>
<script src="${pageContext.request.contextPath}/js/map/map.v1.js"></script>
<script src="${pageContext.request.contextPath}/js/map/measure.js"></script>
<script src="${pageContext.request.contextPath}/js/map/ngimngview.js"></script>
</head>
<body>
<!-- wrap start -->
<div id="Mapwrap">	
	<!-- header start -->
	<div id="header"><c:import url="/sym/mms/NgiMainMenuHead.do?flag=MAP" /></div>	
	<!-- //header end -->
<!-- 내용 -->
    <div id="container">


<!-- 내용 -->
<div id="Mapcontainer">
    <div class="MapLeftBu"><img src="<c:url value='/'/>images/map/main_left_top_b.png" alt="" ></div>
    <div class="mapTop">
    <div class="mapTopL">
        
        <div class="Mmenu">
            <ul>
                <li><div id="navi"></div></li>
                <%-- <li><a href="#"><img src="<c:url value='/'/>images/map/mmenu_pre.png" alt="이전" ></a></li> 
                <li><a href="#"><img src="<c:url value='/'/>images/map/mmenu_next.png" alt="다음" ></a></li>--%>
                <li><a href="#" id="distance"><img src="<c:url value='/'/>images/map/mmenu_distance.png" alt="거리측정" ></a></li>
                <li><a href="#" id="area"><img src="<c:url value='/'/>images/map/mmenu_area.png" alt="면적측정" ></a></li>
                <li><a href="#" id="save"><img src="<c:url value='/'/>images/map/mmenu_save.png" alt="지도저장" ></a></li>
                <li><a href="#" id="print"><img src="<c:url value='/'/>images/map/mmenu_print.png" alt="지도인쇄" ></a></li>
            </ul>
        </div>
	</div>
    
    <div class="mapTopR">
        <ul>
            <li>
			<input type="text" name="" value="ON" id="AXInputSwitch2" style="width:100px;height:21px;border:0px none;" title="변동관리 on/off" />
			</li>
            <li>
            	<a href="#" id="sel_basemap"><img src="<c:url value='/'/>images/map/btn_top_right_mapselect.gif" alt="지도선택" ></a>
            	<div class="div_basemap">
            	  <div class="sub_head"><span>지도선택</span></div>
				  <ul>
				    <li><input type="radio" name="basemap" checked value="" title="수치지도"><span>수치지도</span></li>
				  </ul>
				  <ul>
				    <li><input type="checkbox" name="selmap" value="NGII_AIR" title="항공사진"><span>항공사진</span></li>
				  </ul>
				  <ul>
				    <li><input type="checkbox" name="selmap" value="5k-dokak" title="1:5000 도엽"><span>1:5000</span><li>
				    <li><input type="checkbox" name="selmap" value="25k-dokak" title="1:25000 도엽"><span>1:25000</span><li>
				    <li><input type="checkbox" name="selmap" value="50k-dokak" title="1:50000 도엽"><span>1:50000</span><li>
				  </ul>
				  <div class="sub_bottom"><button type="button" class="AXButton" id="sel_basemap_close">확인</button></div>
            	</div>
            </li>
            <li><button type="button" class="AXButton Red" onclick="landchg_help('gen');" ><i class="fa fa-info-circle fa-lg"></i> 도움말</button>
          <!--<a href="#"><img src="<c:url value='/'/>images/map/btn_top_right_help.gif" alt="도움말" onClick="landchg_help('gen');" ></a> --></li>
        </ul>
    </div>
    
    </div>
    
<!-- 왼쪽 메뉴 -->     
    <div class="MapLeft">
    	
    <div class="MapLeftWin" id="MapLeftWin">
    	<a href="#" title="닫기"><div class="MapLeftWin_close" id="MapLeftWin_close"></div></a>
    </div>
    <ul class="MapLL">
	    <li><a href="#" title="검색"><div class="search"></div></a></li>
	    <li><a href="#" title="범례"><div class="legend"></div></a></li>
	    <!-- <li><a href="<c:url value='/'/>ngi/mng/NgiMngChartView.do" title="통계"><div class="stat"></div></a></li> -->
	    <li><a href="javascript:heatmapOn();" title="핫스팟"><div class="hspot"></div></a></li>
	    <!-- <li><a href="#" title="즐겨찾기"><div class="bookmark"></div></a></li>  -->
    </ul>
    
    <div class="MapLR" id="MapLR">
    
    	<div class="searchTab" id="searchTab">
    		<div class="searchToolTab" id="searchToolTab"> <!--  style="width:142px;" -->
    			<ul>
    				<li><div class="searchTabBtn on" style="width:55px;"><span>주소/명칭</span></div></li>
    				<li><div class="searchTabBtn" style="width:43px;"><span>도엽</span></div></li>
    				<li><div class="searchTabBtn" style="width: 51px;"><span>공사번호</span></div></li>  
    				<!-- <li></li> -->
    			</ul>
    		</div>
    		<div class="searchSubTab" id="searchSubTab0">
    			<!-- 검색 -->
				<div class="Search11">
					<div>
					<input type="text" id="search-input" name="input" value="" class="AXInput" style="width: 125px;margin: 9px;" title="검색어" />
					<button type="button" id="search-go" class="AXButtonSmall Blue Blue" style="top: -1px;left: -7px;"><i class="fa fa-search fa-lg"></i> 검색</button>
					</div>
				</div>
				<div class="SearchList" id="SearchList">  <!-- 검색결과 -->
					<p class="SerarchTt" id="SerarchTt">검색 위치명을 입력하세요.</p>
					<div class="SearchResultLi">
						<ul class="MarT30"  id="SerarchLi"></ul>
						<div id="show_search_page"></div>
					</div>
				</div>
		        <!-- //검색 -->
    		</div>
    		<div class="searchSubTab" id="searchSubTab1" style="display:none;">
    			<div>
    				<table style="margin:10px;height:158px">
					  <tr>
					    <th>축척</th>
					    <td>
					    	<select name="dokak_select" class="AXSelectSmall" id="dokak_select" style="width:133px;" >
					    		<option value="5k-dokak">1:5000</option>
								<option value="25k-dokak">1:25000</option>
								<option value="50k-dokak">1:50000</option>
					    	</select>
					    </td>
					  </tr>
					  <tr>
					  	<th>도엽</th>
					    <td><select name="dokak_select_sub0" class="AXSelectSmall" id="dokak_select_sub0" style="width:133px;" ></select></td>
					  </tr>
					  <tr>
					  	<th></th>
					    <td><select name="dokak_select_sub1" class="AXSelectSmall" id="dokak_select_sub1" style="width:133px;" ></select></td>
					  </tr>
					  <tr>
					  	<td><input type="button" id="dokak_reset" value="초기화" class="AXButton"></td>
					    <td><input type="button" id="dokak_move" value="이동" class="AXButton" style="width: 119px;margin: 3px;"></td>
					  </tr>
					</table>
    			</div>
    			
    		</div>
    		<div class="searchSubTab" id="searchSubTab2" style="display:none;">
				<div class="Search11">
					<div>
					<input type="text" name="input" value="" id="p_search" class="AXInput" style="width: 125px;margin: 9px;" title="검색어" />
					<button type="button" class="AXButtonSmall Blue Blue" style="top: -1px;left: -7px;" onclick="javascript:p_search();"><i class="fa fa-search fa-lg"></i> 검색</button>
					</div>
				</div>
				<div class="SearchList" id="SearchList_s">  <!-- 검색결과 -->
					<p class="SerarchTt" id="SerarchTt_s">공사번호를 입력하세요.</p>
					<div class="SearchResultLi" id="SearchResultLi_s">
						<ul class="MarT30"  id="SerarchLi_s"></ul>
						<div id="show_search_page_s"></div>
					</div>
				</div>				
    		</div>
    		
    		<div class="searchSubTab" id="searchSubTab3" style="display:none;">
		
    		
    		</div>
		    
		</div>
    	<div id="show_legend">
	    	<div class="MapLRTit">
			    <h2>범례</h2>
			    <p>
				    <select name="legend_year" class="MapSearchSelect" id="Select">
				      <c:forEach items="${yearList}" var="data">
							<option value="${data.name}">${data.name}년</option>
						</c:forEach>
				    </select>
				    <input type="text" name="" value="OFF" id="legendSwitch" style="width:50px;height:21px;border:0px none;" title="범례 on/off" />
			    </p>
			    </div>
			    <div class="MapLRList">
					<ul>
			            <li><input type="checkbox" name="legend_check" class="checkbox" checked="checked" value="01" title="도로"><label><img src="<c:url value='/'/>images/map/mlist_bul_001.png" alt="도로" >도로</label></li>
			            <li><input type="checkbox" name="legend_check" class="checkbox" checked="checked" value="02" title="택지"><label><img src="<c:url value='/'/>images/map/mlist_bul_002.png" alt="택지" >택지</label></li>
			            <li><input type="checkbox" name="legend_check" class="checkbox" checked="checked" value="03" title="하천"><label><img src="<c:url value='/'/>images/map/mlist_bul_003.png" alt="하천" >하천</label></li>
			            <li><input type="checkbox" name="legend_check" class="checkbox" checked="checked" value="04" title="철도"><label><img src="<c:url value='/'/>images/map/mlist_bul_004.png" alt="철도" >철도</label></li>
			            <li><input type="checkbox" name="legend_check" class="checkbox" checked="checked" value="05" title="산업"><label><img src="<c:url value='/'/>images/map/mlist_bul_005.png" alt="산업" >산업</label></li>
			            <li><input type="checkbox" name="legend_check" class="checkbox" checked="checked" value="06" title="항만"><label><img src="<c:url value='/'/>images/map/mlist_bul_006.png" alt="항만" >항만</label></li>
			            <li><input type="checkbox" name="legend_check" class="checkbox" checked="checked" value="07" title="수자원"><label><img src="<c:url value='/'/>images/map/mlist_bul_007.png" alt="수자원" >수자원</label></li>
			            <li><input type="checkbox" name="legend_check" class="checkbox" checked="checked" value="08" title="공항"><label><img src="<c:url value='/'/>images/map/mlist_bul_008.png" alt="공항" >공항</label></li>
			            <li><input type="checkbox" name="legend_check" class="checkbox" checked="checked" value="09" title="매립"><label><img src="<c:url value='/'/>images/map/mlist_bul_009.png" alt="매립" >매립</label></li>
			            <li><input type="checkbox" name="legend_check" class="checkbox" checked="checked" value="10" title="관광"><label><img src="<c:url value='/'/>images/map/mlist_bul_010.png" alt="관광" >관광</label></li>
			            <li><input type="checkbox" name="legend_check" class="checkbox" checked="checked" value="11" title="특정"><label><img src="<c:url value='/'/>images/map/mlist_bul_011.png" alt="특정" >특정</label></li>
			            <li><input type="checkbox" name="legend_check" class="checkbox" checked="checked" value="12" title="체육"><label><img src="<c:url value='/'/>images/map/mlist_bul_012.png" alt="체육" >체육</label></li>
			            <li><input type="checkbox" name="legend_check" class="checkbox" checked="checked" value="13" title="폐기물"><label><img src="<c:url value='/'/>images/map/mlist_bul_013.png" alt="폐기물" >폐기물</label></li>
			            <li><input type="checkbox" name="legend_check" class="checkbox" checked="checked" value="14" title="주기"><label><img src="<c:url value='/'/>images/map/mlist_bul_014.png" alt="주기" >주기</label></li>
			            <!-- <li><input type="checkbox" name="legend_check" class="checkbox" checked="checked" value="15" title="건물"><label for="check015"><img src="<c:url value='/'/>images/map/mlist_bul_015.png" alt="건물" >건물</label></li> -->
			        </ul>
			    </div>
			    <div class="MapLRBottom"> </div>
			</div>
    	<div id="show_chart">
    		<div class="MapLRTit">
			    <h2>핫스팟</h2>
			    
			    </div>
			    <div class="MapLRList">
					<ul>
						<li>
							<p>
							    <select name="hspot_year" class="MapSearchSelect" id="Select">
							      <c:forEach items="${yearList}" var="data">
										<option value="${data.name}">${data.name}년</option>
									</c:forEach>
							    </select>
							    <input type="text" name="" value="OFF" id="hspotSwitch" style="width:50px;height:21px;border:0px none;" title="핫스팟 on/off" />
						    </p>
						</li>
			        </ul>
			    </div>
			    <div class="MapLRBottom"> </div>
			</div>
		<div id="show_fav">즐겨찾기</div>
		</div>
    </div>
<!-- //왼쪽 메뉴 -->  
    
    <div class="MapRight">

<!-- 탭영역 -->
<div class='tabs-area'>  
    <ul class='tabs'>  
		<li><a id='tab1' href="javascript:tabs('1');">변경신고</a></li>  
		<li><a id='tab2' href="javascript:tabs('2');">변동보고</a></li> 
		<li><a id='tab3' href="javascript:tabs('3');">변화정보</a></li> 
		<li><a id='tab4' href="javascript:tabs('4');">세움터/새주소</a></li>  
    </ul>  
</div>  
<div class='tabs-line'></div> 
<!-- //탭영역 -->
<div id="ChangeView1" class="ChangeView" style="display: none;">
	<iframe src="" class="chgsttemntinfo" style="width:100%;height:100%;overflow:hidden;border:0px;" id="iframeChgSttemntinfo" name="iframeChgSttemntinfo" title="iframeChgSttemntinfo"></iframe>
</div>
<div id="ChangeView2" class="ChangeView" style="display: none;">
	<iframe src="" class="chgcntrwkinfo" style="width:100%;height:100%;overflow:hidden;border:0px;" id="iframeChgCntrwkinfo" name="iframeChgCntrwkinfo" title="iframeChgCntrwkinfo"></iframe>
</div>
<div id="ChangeView3" class="ChangeView" style="display: none;">
	<iframe src="" class="transinfo" style="width:100%;height:100%;overflow:hidden;border:0px;" id="iframeTransinfo" name="iframeTransinfo" title="iframeTransinfo"></iframe>
</div>
<div id="ChangeView4" class="ChangeView" style="display: none;">
<iframe src="" class="aisinfo" style="width:100%;height:100%;overflow:hidden;border:0px;" id="iframeAisinfo" name="iframeAisinfo" title="iframeAisinfo"></iframe>
<!-- 	<div style="color:#333;line-height:60px;padding:26px;position:absolute;top:0px;bottom:0px;left:0px;right:0px;">
		<p style="height: 30px;">
		 
		</p>
		<div style="height: 30px;">	
			<input type="checkbox" name="layer_bldg_chg" value="kais_bldg_chg" title="새주소"/>
			<div id="kais_bldg_chg" class="bldg_chg"><label>  새주소</label></div>
		</div>
		<div style="height: 30px;">	
			<input type="checkbox" name="layer_bldg_chg" value="eais_bldg_chg" title="세움터"/>
			<div id="eais_bldg_chg" class="bldg_chg"><label>  세움터</label></div>
		</div>
		<p style="height: 30px;"><label>지도를 1:10000 이상 확대하시면 지도에 표출됩니다.</label></p>
	</div> -->
</div>
<!-- //페이징 -->
</div>
<div id="mapContent"><!-- 지도영역. -->
	<div id="zoomScale" class="zoomScale"></div>
	<div id="divOverviewMap" class="divOverviewMap"></div>
</div>
</div>
<!-- //내용 -->

	<!-- footer 시작 -->
		<div id="footer" style="display:none;"><c:import url="/EgovPageLink.do?link=main/inc/NgiIncFooter" /></div>
	<!-- //footer 끝 -->
</div>

</div>
<!-- //wrap end -->
<form name="detailForm" id="detailForm" method="post" style="display:none;">
<input type="hidden" name="changeInfoId" id="changeInfoId" value="${changeInfoId}" />
<input type="hidden" name="tabNum" id="tabNum" value="${tabNum}" />
<input type="hidden" name="method" id="method" value="${method}" />
</form>

</body>
</html>