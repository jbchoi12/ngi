<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>국토변화정보포털서비스</title>
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


function fn_egov_select(updtInfoId) {
	parent.resetVectorList();	// 도형정보 초기화
	mapBoxDrawReset();
	document.getElementById("listForm").updtInfoId.value = updtInfoId;
   	document.getElementById("listForm").action = "<c:url value='/ngi/udt/updateOperationInqire.do'/>";
   	document.getElementById("listForm").submit();
}

/* 검색 초기화 function */
function fn_egov_init() {
	mapBoxDrawReset();
	parent.resetVectorList();
	location.href="<c:url value='/'/>ngi/udt/updateOperationList.do";
}

/* pagination 페이지 링크 function */
function fn_egov_link_page(pageNo){
	document.getElementById("listForm").pageIndex.value = pageNo;
	document.getElementById("listForm").action = "<c:url value='/ngi/udt/updateOperationList.do'/>";
   	document.getElementById("listForm").submit();
}

function fn_egov_downFile(filename){
	location.href=filename;
}

/* 글 검색 화면 function */
function fn_egov_search() {
	$("#mapBBOX").val(getMapBBOX());	// 위치기반조회.
	parent.resetVectorList();
   	document.getElementById("listForm").action = "<c:url value='/ngi/udt/updateOperationList.do'/>";
   	document.getElementById("listForm").submit();		
}

/* ********************************************************
 * 엔터키 처리 함수
 * kka / 2014.10.15
 ******************************************************** */
function press(event) {
	if (event.keyCode==13) {
		fn_egov_link_page('1');
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
var fnObj = {
	pageStart: function(){


		$("#AXInputDate_earlier").bindDate({align:"right", valign:"bottom", onChange:{
				earlierThan:"AXInputDate_later", err:"종료일보다 빠른 날짜를 선택하세요", 
				onChange:function(){
			
				}
			}
		});
		$("#AXInputDate_later").bindDate({align:"right", valign:"bottom",
               onChange:{
                   laterThan:"AXInputDate_earlier", err:"시작일보다 느린 날짜를 선택하세요"
               }
           });
	}
};
jQuery(document).ready(fnObj.pageStart.delay(0.1));
OpenLayers.ProxyHost = "${pageContext.request.contextPath}/proxy.jsp?resourceUrl=";
$(window).load(function(){
	$('#dokak_select_sub0 > option[value="${searchVO.dokak_select_sub0}"]').attr('selected', 'selected');
	setTimeout(function(){
	 	$("select[name=dokak_select_sub0]").change();
	}, 100);
});
</script>
<style>
.CategDl li {line-height:20px;}
.tdheight  {height:15px;}
.paging_align li {float:left;}
</style>

</head>
<body>
<form:form commandName="searchVO" name="listForm" id="listForm" method="post"  enctype="multipart/form-data" >
	<input type="hidden" name="updtInfoId"  />
	<input type="hidden" name="cal_url" value="<c:url value='/sym/cmm/EgovNormalCalPopup.do'/>" />
	<div class="Categ">
	    <ul class="CategDl">
	      <li class="Ca1">
	        <label for="searchCondition">검색방법</label>
	        <form:select path="searchCondition" id="searchCondition" class="AXSelectSmall">
                   <form:option value="00" label="일반검색"/>
                   <form:options items="${search_result}" itemValue="code" itemLabel="codeNm"/>	        
		    </form:select>
	        </li>
	        <li id="search01" class="searchType" style="display:none;">
		        <ul class="CategDl">
			        <li class="Ca1" style="margin-left: 60px;">
					    	<!-- 위치기반 -->
					    		<button type="button" class="AXButtonSmall" onClick="javascript:mapBoxDraw();" id="map_box_set"><i class="fa fa-search fa-1"></i> 영역지정</button>
					    		<button type="button" class="AXButtonSmall" onClick="javascript:mapBoxDrawReset();"><i class="fa fa-times fa-lg"></i> 초기화</button>
					    	<div id="map_box_pop">버튼을 선택하고 <br>지도에서 드래그하여<br> 영역을 설정합니다.</div>
					    	<form:input path="mapBBOX" id="mapBBOX" type="hidden" />
			        </li>
		        </ul>
	        </li>
	        <li id="search02" class="searchType" style="display:none;">
		        <ul class="CategDl">
			        <li class="Ca" style="width:140px">
				    	<!-- 도엽 -->
		   				<label for="dokak_select">축척</label>
				    	<form:select path="dokak_select" class="AXSelectSmall" id="dokak_select" style="width:68px;" >
				    		<form:option value="----------">----------</form:option>
				    		<form:option value="5k-dokak">1:5000</form:option>
							<form:option value="25k-dokak">1:25000</form:option>
							<form:option value="50k-dokak">1:50000</form:option>
				    	</form:select>
				    </li>
				    <li class="Ca" style="width: 267px;">
						<label for="dokak_select_sub0">도엽</label> 
						<form:select path="dokak_select_sub0" class="AXSelectSmall" id="dokak_select_sub0" style="width:68px;" ></form:select>
						<form:select path="dokak_select_sub1" class="AXSelectSmall" id="dokak_select_sub1" style="width:105px;" ></form:select>
					</li>
				</ul>
         </li>
     	 <li id="search03" class="searchType" style="display:none;">
	     	 <ul class="CategDl">
			      <li class="Ca">
			           <label for="cntrwkNo">공사번호</label>
		        		<form:input path="cntrwkNo" class="AXInput W100" id="cntrwkNo" />
			        </li>	  
		        </ul>
		  </li>  
  	      <li id="search04" class="searchType" style="display:none;">
  	     	 <ul class="CategDl">
			      <li class="Ca1">
			        <label for="changeTy">지형지물</label>
			           <form:select path="changeTy" id="changeTy" class="AXSelectSmall">
		                   <form:option value="">-선택-</form:option>
		                   <form:options items="${changeTy_result}" itemValue="code" itemLabel="codeNm"/>
		               </form:select>   
			        </li>	
		        </ul>  
		  </li>  
	      <li class="Ca1">
	        <label>사업지구</label>
	       	<select name="bsnsDstrc" id="bsnsDstrc" class="AXSelectSmall">
	       	  <option value="">-선택-</option>
		      <option value="01">서울지구</option>
		      <option value="02">인천지구</option>
		      <option value="03">광주지구</option>
		      <option value="04">부산지구</option>
		    </select>
     
	      </li>	    
			<li class="Ca1">
				<select name="searchCondition_sj" class="AXSelectSmall">
						<option value="01">제목</option>
						<option value="02">용역수행사</option>
				</select>&nbsp;&nbsp;<form:input path="searchKeyword" title="유형 검색어"  class="AXInput W300" onkeypress="press(event);" placeholder="검색어를 입력하세요"/>
			</li>
	      <li style="float:right;">
	      <button type="button" class="AXButtonSmall Blue" onClick="javascript:fn_egov_search();"><i class="fa fa-search fa-1"></i> 검색</button>&nbsp;
	      <button type="button" class="AXButtonSmall Blue" onClick="javascript:fn_egov_init();"><i class="fa fa-repeat fa-lg"></i> 초기화</button></li>
	    </ul>
	</div>




<div id="content_pop">

	<!-- List -->
	<table class="table_list">
		<caption>테이블 목록</caption>
			<colgroup>
				<col style="width:5%;" />
				<col style="width:10%;" />
				<col style="width:10%;" />
				<col style="width:8%;" />
				<col style="width:auto;" />
				<col style="width:15%;" />
				<col style="width:15%;" />

			</colgroup> 
			<thead>
				<tr>
					<th scope="col">&nbsp;</th>
					<th scope="col">번호</th>
					<th scope="col">사업지구</th>
					<th scope="col">유형</th>
					<th scope="col">제목</th>
					<th scope="col">용역수행사</th>
					<th scope="col">등록일자</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="result" items="${resultList}"  varStatus="status">	
				<c:set var="datano" value="${paginationInfo.totalRecordCount - ( (paginationInfo.currentPageNo-1) * paginationInfo.pageSize + status.index ) }" />
				<tr>
					<td class="tdheight"><input type="checkbox" class="checkbox" title="선택"></td>
					<td><c:out value="${result.changeInfoId}" /></td>
					<td><c:out value="${result.bsnsDstrc}" /></td>
					<td>
			            <c:forEach var="changeTy_result" items="${changeTy_result}" varStatus="status">
			                <c:if test="${result.changeTy == changeTy_result.code}"><c:out value="${changeTy_result.codeNm}"/></c:if>
			            </c:forEach>    					
					</td>
					<td><a href="#" onclick="javascript:fn_egov_select('<c:out value="${result.updtInfoId}" />')">${result.opertNm}</a></td>
					<td><c:out value="${result.servcExcprofsCode}"/></td>
					<td><fmt:formatDate value="${result.rgsde}" pattern="yyyy-MM-dd"/></td>
				</tr>
				</c:forEach>
				<c:if test="${empty resultList}">
				<tr>
					<td align="center" colspan="8"><spring:message code="info.nodata.msg" /></td>
				</tr>
				</c:if>
			</tbody>
	</table>	


	<!-- /List -->
	<div id="paging" class="page_paging MapMarg_T10">
		<div class="page_center" style="margin-left:230px;">
			<ul class="paging_align">
			<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_egov_link_page" />
			</ul><form:hidden path="pageIndex" />
		</div>
	</div>


	<div class="MapMarg_T40">
		<div id="BtnAreaMap">
			<div id="StyleButtonDivMap">
				<button type="button" class="AXButtonSmall Blue" OnClick="javascript:fn_egov_downFile('<c:url value='/'/>Change.zip');"><i class="fa fa-arrow-circle-o-down fa-lg"></i> 첨부파일다운로드</button>
		        <button type="button" class="AXButtonSmall Blue" OnClick="javascript:fn_egov_downFile('<c:url value='/'/>Change.xls');"><i class="fa fa-arrow-circle-o-down fa-lg"></i> 엑셀다운로드</button>
			</div>
		</div>
    </div>	

</div>
</form:form>
</body>
</html>
