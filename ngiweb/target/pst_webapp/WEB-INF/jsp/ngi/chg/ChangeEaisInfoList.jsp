<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : TnChangeInfoList.jsp
  * @Description : TnChangeInfo List 화면
  * @Modification Information
  * 
  * @author 이정연
  * @since 2014-09-29
  * @version 1.0
  * @see
  *  
  * Copyright (C) All right reserved.
  */
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>목록</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngicommon.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngimap.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXJ.min.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXInput.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXSelect.css">
<link href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">

<script type="text/javascript" src="<c:url value='/'/>js/axisj/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<c:url value='/'/>js/axisj/dist/AXJ.min.js"></script>
<script type="text/javascript" src="<c:url value='/'/>js/axisj/lib/AXInput.js"></script>
<script src="${pageContext.request.contextPath}/js/map/apikey.js"></script>
<script src="${pageContext.request.contextPath}/js/openlayers/OpenLayers.js"></script>
<script src="${pageContext.request.contextPath}/js/web/changeInfoList_dok.js"></script>
<script type="text/javascript" src="<c:url value='/js/EgovCalPopup.js'/>" ></script>
<script type="text/javascript">
<!--
/* 글 수정 화면 function */


/* 글 등록 화면 function */
function fn_egov_search() {
   	document.getElementById("listForm").action = "<c:url value='/ngi/chg/ChangeEaisInfoList.do'/>";
   	document.getElementById("listForm").submit();		
}

/* 검색 초기화 function */
function fn_egov_init() {
	mapBoxDrawReset();
	parent.resetVectorList();
	location.href="<c:url value='/'/>ngi/chg/ChangeEaisInfoList.do";
}


/* pagination 페이지 링크 function */
function fn_egov_link_page(pageNo){
	document.getElementById("listForm").pageIndex.value = pageNo;
	document.getElementById("listForm").action = "<c:url value='/ngi/chg/ChangeEaisInfoList.do'/>";
   	document.getElementById("listForm").submit();
}

function page_move(){
	if(document.getElementById("listForm").searchCondition.value == '02'){
		location.href="<c:url value='/'/>ngi/chg/ChangeKaisInfoList.do";
		
	}
}


 // -->
</script>
<script>
/**
 * Require Files for AXISJ UI Component...
 * Based		: jQuery
 * Javascript 	: AXJ.js, AXInput.js
 * CSS			: AXJ.css, AXInput.css
 */	
var pageID = "Date";
var layerSwitchOnOff = "off";

var fnObj = {
	pageStart: function(){

		$("#layerSwitch").bindSwitch({off:"ON", on:"OFF", onChange:function(){
			if(this.value=="OFF") {
				layerSwitchOnOff = "off";
				parent.map.getLayersByName("eais_bldg_chg")[0].setVisibility(false);
				parent.map.getLayersByName("kais_bldg_chg")[0].setVisibility(false);
			} else {
				layerSwitchOnOff = "on";
				var selVal = $("#searchCondition option:selected").val();
				if(selVal=='01') { // 세움터 - eais_bldg_chg
					parent.map.getLayersByName("eais_bldg_chg")[0].setVisibility(true);
					parent.map.getLayersByName("kais_bldg_chg")[0].setVisibility(false);
				} else {	// 새주소 - kais_bldg_chg
					parent.map.getLayersByName("eais_bldg_chg")[0].setVisibility(false);
					parent.map.getLayersByName("kais_bldg_chg")[0].setVisibility(true);
				}
				
			}
		}});
	

	}
};
jQuery(document).ready(fnObj.pageStart.delay(0.1));
OpenLayers.ProxyHost = "${pageContext.request.contextPath}/proxy.jsp?resourceUrl=";

$().ready(function(){
	$("#allcheck").click(function(){
		if($(this).is(":checked")) {
			$('input[name=item_checkbox]').each(function(){
				$(this).prop('checked', true);
			});
		} else {
			$('input[name=item_checkbox]').each(function(){
				$(this).prop('checked', false);
			});
		}
	});
	
	$("#searchCondition").change(function(){
		
		if(layerSwitchOnOff=="on") {
			var selVal = $("#searchCondition option:selected").val();
			if(selVal=='01') { // 세움터 - eais_bldg_chg
				parent.map.getLayersByName("eais_bldg_chg")[0].setVisibility(true);
				parent.map.getLayersByName("kais_bldg_chg")[0].setVisibility(false);
			} else {	// 새주소 - kais_bldg_chg
				parent.map.getLayersByName("eais_bldg_chg")[0].setVisibility(false);
				parent.map.getLayersByName("kais_bldg_chg")[0].setVisibility(true);
			}
		}
	});
});
</script>
<style>
.CategDl li {line-height:20px;}
</style>
</head>
<body>
<form:form commandName="searchVO" name="listForm" id="listForm" method="post"  enctype="multipart/form-data" >
	<input type="hidden" name="cal_url" value="<c:url value='/sym/cmm/EgovNormalCalPopup.do'/>" />
	<input type="hidden" name="changeInfoId" />
	
	<div class="Categ" style="border:2px solid rgba(101, 165, 218, 1);margin:5px;padding:10px;overflow:hidden;background-color:#eeeeee;">
	    <ul class="CategDl">
	      <li class="Ca1">
	        <label for="searchCondition">선택</label>
	        <form:select path="searchCondition" id="searchCondition" class="AXSelectSmall" style="width:265px" onChange="javascript:page_move();">
                   <form:option value="01" label="세움터"/>
                   <form:option value="02" label="새주소"/>
		    </form:select>
		    <input type="text" name="" value="OFF" id="layerSwitch" style="width:50px;height:21px;border:0px none;" title="on/off" />
	        </li>
	      <li class="Ca1">
	        (1:10000 이하의 축적에서만 보입니다.)
	      </li>	        
	      <li class="Ca">
	        <label for="chgDe">변&nbsp;경&nbsp;일</label>
	           <form:select path="chgDe" id="chgDe" class="AXSelectSmall" style="width:105px">
                   <form:option value="">-선택-</form:option>
                   <c:forEach var="result" items="${resultDateList}" varStatus="status">
                   <form:option value="${result.chgDe}"><c:out value="${result.chgDe}"/></form:option>
                   </c:forEach>
               </form:select>  	        
     
	      </li>
	      <li style="float:right;"><button type="button" class="AXButtonSmall Blue" onClick="javascript:fn_egov_search();"><i class="fa fa-search fa-1"></i> 검색</button>&nbsp;<button type="button" class="AXButtonSmall Blue" onClick="javascript:fn_egov_init();"><i class="fa fa-repeat fa-lg"></i> 초기화</button></li>
	    </ul>
	</div>




<div id="content_pop" class="MapMarg_T30">

	<!-- List -->
	<table class="table_list" summary="목록 테이블입니다.">
		<caption>테이블 목록</caption>
        <colgroup>
            <col style="width:5%;">
            <col style="width:15%;">
            <col style="width:7%;">
            <col style="width:15%;">
            <col style="width:10%;">
            <col style="width:10%;">
            <!-- <col style="width:13%;">
            <col style="width:7%;"> -->
        </colgroup>
        <thead>
          <tr>
            <th scope="col">변경일</th>
            <th scope="col">등록번호</th>
            <th scope="col">지역</th>
            <th scope="col">구분</th>
            <th scope="col">시설명</th>
            <th scope="col">상세이름</th>
            <!-- <th scope="col">X좌표</th>
            <th scope="col">Y좌표</th> -->
            </tr>
        </thead>
        <tbody>
        <c:forEach var="result" items="${resultList}" varStatus="status">
          <tr>
            <td><c:out value="${result.chgDe}"/></td>
            <td><c:out value="${result.bldrgstPk}"/></td>
            <td><c:out value="${result.dokakNm}"/></td>
            <td><c:out value="${fn:substring(result.purpsNm, 0,9)}"/></td>
            <td><c:out value="${fn:substring(result.bldNm, 0,7)}"/></td>
            <td><c:out value="${fn:substring(result.dongNm, 0,7)}"/></td>
          <!--  <td><c:out value="${result.locLon}"/></td>
            <td><c:out value="${result.locLat}"/></td> --> 
           </tr>
		 </c:forEach>
         </tbody>
	</table>	


	<!-- /List -->
	<div id="paging" class="page_paging MapMarg_T10">
		<div class="page_center">
			<ul class="paging_align">
			<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_egov_link_page" />
			</ul><form:hidden path="pageIndex" />
		</div>
	</div>


</div>
</form:form>
</body>
</html>
