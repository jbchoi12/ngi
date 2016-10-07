<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import ="egovframework.com.cmm.LoginVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
<%
//String sUserId = (String)session.getAttribute("sUserId");
String sUserId = "jbchoi";
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
/* 글 수정 화면 function */


function fn_egov_select(changeInfoId) {
	parent.resetVectorList();	// 도형정보 초기화
	mapBoxDrawReset();
	document.getElementById("listForm").changeInfoId.value = changeInfoId;
   	//document.getElementById("listForm").action = "<c:url value='/ngi/chg/updateChangeInfoView.do'/>";
   	document.getElementById("listForm").action = "<c:url value='/ngi/chg/ChangeInfoInqire.do'/>";
   	document.getElementById("listForm").submit();
}

/* 글 검색 화면 function */
function fn_egov_search() {
	$("#mapBBOX").val(getMapBBOX());	// 위치기반조회.
	parent.resetVectorList();
	
	//var subject = htmlReplaceText(jQuery('[name="changeSj"]').val());
	//jQuery('[name="changeSj"]').val(subject);
	
   	document.getElementById("listForm").action = "<c:url value='/ngi/chg/ChangeInfoList.do'/>";
   	document.getElementById("listForm").submit();		
}

/* 검색 초기화 function */
function fn_egov_init() {
	mapBoxDrawReset();
	parent.resetVectorList();
	location.href="<c:url value='/'/>ngi/chg/ChangeInfoList.do";
}


/* 글 등록 화면 function */
function fn_egov_addView() {
	mapBoxDrawReset();
	parent.resetVectorList();	// 도형정보 초기화
   	document.getElementById("listForm").action = "<c:url value='/ngi/chg/addChangeInfoView.do'/>";
   	document.getElementById("listForm").submit();		
}

/* pagination 페이지 링크 function */
function fn_egov_link_page(pageNo){
	document.getElementById("listForm").pageIndex.value = pageNo;
	document.getElementById("listForm").action = "<c:url value='/ngi/chg/ChangeInfoList.do'/>";
   	document.getElementById("listForm").submit();
}

//excel다운로드.
function excel_downFile() {
	var selNums = [];
	var listEle = $("input[name=item_checkbox]");
	$.each(listEle, function(index,obj){
		if( $(obj).is(":checked") ) {
			selNums.push($(obj).val());
		} 
	});
	
	if(selNums.length == 0 ) {
		alert("선택된 항목이 없습니다.");
	} else {
		
		var nums = "";
		for(var i=0;i<selNums.length;i++) {
			if(i+1==selNums.length) {
				nums += selNums[i];
			} else {
				nums += selNums[i] + ",";
			}
		}
		url = "<c:url value='/ngi/chg/changeInfoExcelDownloadView.do'/>";
		window.open("" ,"file_blank_ex", "toolbar=no,width=980,height=315,directories=no,status=no,scrollbars=no,resizable=no");
		
		var form = document.createElement("form");
        form.setAttribute("method", "post");
        form.setAttribute("action", url);
        form.setAttribute("target", "file_blank_ex" );
        var input = document.createElement("input");
        input.type = "hidden";
        input.name = "idNums";
        input.value = nums;
        form.appendChild(input);
        document.body.appendChild(form);
        form.submit();
        document.body.removeChild(form);
	}
}

function fn_egov_downFile(){
	
	var checkFile = true;
	var checkList = true;
	frm = document.getElementById("listForm");
	$("input[name=item_checkbox]:checkbox").each(function(){
		
  		if ( $(this).is(":checked") == true ) {
  			checkList = false;
  			var fileId = $(this).parent().find("input[name=atchFileId]").val() ;
			if( fileId == "" ) {
				//checkFile = true;
			} else {
				checkFile = false;
				fn_egov_downFile2(fileId,'0');
				
			}
		}
  		
	});
	
		if(checkList){
  			alert('선택된 항목이 없습니다.');
  			return;
			
		} else{
			if(checkFile) {
	  			alert('업로드된 파일이 없습니다.');
	  			return;
	  		} 
		}
}

function fn_egov_downFile2(atchFileId, fileSn){
	window.open("<c:url value='/cmm/fms/FileDown.do?atchFileId='/>"+atchFileId+"&fileSn="+fileSn+"");
}

function htmlReplaceText(text){
	
	if(text != null || text !=""){
		text = text.replace(/&/g,"&amp;");
		text = text.replace(/</g,"&lt;");
		text = text.replace(/>/g,"&gt;");
		text = text.replace(/\\/g,"&quot;");
	}
	return text;
}

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
		$("#AXInputDate_earlier").bindDate({align:"right", valign:"bottom", onChange:{ earlierThan:"AXInputDate_later", err:"종료일보다 빠른 날짜를 선택하세요"}});
		$("#AXInputDate_later").bindDate({align:"right", valign:"bottom", onChange:{ laterThan:"AXInputDate_earlier", err:"시작일보다 느린 날짜를 선택하세요"  } });
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
});
</script>

<style type="text/css">
.CategDl li {line-height:20px;}

.search02_table {height:95px;top: -3px;left: 1px;position: relative;}
.search02_table label {float: left;font-size: 11px;color: #000;font-weight: bold;padding-bottom:5px;display: block;width: 55px;}
#search01 .text {font-size: 16px;height: 53px;margin-left: 46px;color: #545767;}
#search01 .text span {top: 15px;position: relative;}
</style>

</head>
<body>
<form:form commandName="searchVO" name="listForm" id="listForm" method="post"  enctype="multipart/form-data" >
	<input type="hidden" name="changeInfoId" />
	<input type="hidden" name="cal_url" value="<c:url value='/sym/cmm/EgovNormalCalPopup.do'/>" />
	<div class="Categ" style="border:2px solid rgba(101, 165, 218, 1);margin:5px;padding:10px;overflow:hidden;background-color:#eeeeee;">
	    <ul class="CategDl">
	      <li class="Ca1">
	        <label for="searchCondition">검색방법</label>
	        <form:select path="searchCondition" id="searchCondition" class="AXSelectSmall" style="width:325px">
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
		   				<label for="dokak_select">축&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;척</label>
				    	<form:select path="dokak_select" class="AXSelectSmall" id="dokak_select" style="width:68px;" >
				    		<form:option value="----------">----------</form:option>
				    		<form:option value="5k-dokak">1:5000</form:option>
							<form:option value="25k-dokak">1:25000</form:option>
							<form:option value="50k-dokak">1:50000</form:option>
				    	</form:select>
				    </li>
				    <li class="Ca" style="width: 267px;">
						<label for="dokak_select_sub0">도&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;엽</label> 
						<form:select path="dokak_select_sub0" class="AXSelectSmall" id="dokak_select_sub0" style="width:68px;" ></form:select>
						<form:select path="dokak_select_sub1" class="AXSelectSmall" id="dokak_select_sub1" style="width:105px;" ></form:select>
					</li>
				</ul>
	         </li>
		      <li class="Ca1" style="display:none;">
		        <label for="changeCl">신고분류</label>
		       	<select name="changeCl" id="changeCl" class="AXSelectSmall">
			      <option value="01">변경신고</option>
			    </select>
		      </li>
		      <li class="Ca1">
		        <label for="processSttusSe">상&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;태</label>
		           <form:select path="processSttusSe" id="processSttusSe" class="AXSelectSmall" style="width:105px">
	                   <form:option value="">-선택-</form:option>
	                   <form:options items="${process_result}" itemValue="code" itemLabel="codeNm"/>
	               </form:select>   
		        </li>
		      <li class="Ca">
		        <label for="changeSj">제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목</label>
		        <form:input path="changeSj" class="AXInput W100" />
		      </li>
		      <li class="Ca">
		        <label for="registerNm">신&nbsp;&nbsp;고&nbsp;&nbsp;자</label>
		        <form:input path="registerNm" class="AXInput W100" />
		      </li>
		      <li class="Calog">
		        <label>신고일자</label>
		        <form:input path="ntceBgnde" class="AXInput W100" id="AXInputDate_earlier" title="신고일자 시작일"/> ~ <form:input path="ntceEndde" class="AXInput W100" id="AXInputDate_later" title="신고일자 종료일"/>
		      </li>
		      <!-- <li class="Ca">
		        <label>변경전</label>
		        <input name="textfield" type="text" class="AXInput W100" >
		      </li>
		      <li class="Ca">
		        <label>변경후</label>
		        <input name="textfield" type="text" class="AXInput W100" >
		      </li> -->
	      <li style="float:right;">
	      <button type="button" class="AXButtonSmall Blue" onClick="javascript:fn_egov_search();"><i class="fa fa-search fa-1"></i> 검색</button>
	      <button type="button" class="AXButtonSmall Blue" onClick="javascript:fn_egov_init();"><i class="fa fa-repeat fa-lg"></i> 초기화</button>
	      
	      
	      
	      </li>
	    </ul>
	</div>


	<div class="MapMarg_T20">
		<div id="BtnAreaMap">
			<div id="StyleButtonDivMap">
		        <button type="button" class="AXButtonSmall Blue" OnClick="javascript:fn_egov_downFile();"><i class="fa fa-arrow-circle-o-down fa-lg"></i> 첨부파일다운로드</button>
		        <button type="button" class="AXButtonSmall Blue" OnClick="javascript:excel_downFile();"><i class="fa fa-arrow-circle-o-down fa-lg"></i> 엑셀다운로드</button>
        <%
        	if(sUserId != null){
        %>		        
		        <button type="button" class="AXButtonSmall Blue" OnClick="javascript:fn_egov_addView();"><i class="fa fa-pencil fa-lg"></i> 신고등록</button>
        <% } else { %>
        		<button type="button" class="AXButtonSmall Blue" OnClick="javascript:alert('로그인을 해야합니다.');"><i class="fa fa-pencil fa-lg"></i> 신고등록</button>
        <% } %>  		        
			</div>
		</div>
    </div>	

<div id="content_pop" class="MapMarg_T10">

	<!-- List -->
	<table class="table_list">
		<caption>테이블 목록</caption>
        <colgroup>
            <col style="width:5%;">
            <col style="width:5%;">
            <col style="width:15%;">
            <col style="width:8%;">
            <col style="width:12%;">
            <col style="width:15%;">
            <col style="width:7%;">
            <col style="width:7%;">
            <col style="width:7%;">
        </colgroup>
        <thead>
          <tr>
            <th scope="col"><input type="checkbox" id="allcheck"></th>
            <th scope="col">번호</th>
            <th scope="col">제목</th>
            <th scope="col">신고자</th>
            <th scope="col">신고일</th>
            <th scope="col">대상지역</th>
            <th scope="col">&nbsp;</th>
            <th scope="col">상태</th>
            <th scope="col">위치</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="result" items="${resultList}" varStatus="status">
          <tr>
            <td class="tdheight"><input type="checkbox" name="item_checkbox" class="checkbox" value="${result.changeInfoId}"  title="선택"/>
				<input type="hidden" name="atchFileId" value="${result.atchFileId}" />
     
            </td>
            <td><c:out value="${result.changeInfoId}"/></td>
            <td><a href="javascript:fn_egov_select('<c:out value="${result.changeInfoId}"/>')"><c:out value="${fn:substring(result.changeSj, 0, 7)}" escapeXml="false" /></a>&nbsp;</td>
            <td><c:out value="${result.registerNm}"/></td>
            <td><c:out value="${result.registDe}"/></td>
            <td><c:out value="${fn:substring(result.changeRnAdresCn, 0,7)}"/></td>
            <td>
            	<c:if test="${not empty result.atchFileId}">
            	<a href="#" onClick="fn_egov_downFile2('<c:out value="${result.atchFileId}"/>','0');"><img src="<c:url value='/'/>images/btn/btn_addfile.jpg" alt="파일"></a>
            	</c:if>
           	</td>            
            
            <td>
	            <c:forEach var="process_result" items="${process_result}" varStatus="status">
	                <c:if test="${result.processSttusSe == process_result.code}"><img src="<c:url value='/'/>images/icon/<c:out value="${process_result.code}"/>.png" ></c:if>
	            </c:forEach>              
			</td>
            <td>
            <c:choose>
            	<c:when test="${empty result.xCrdntLo}">
            	<a href="#"><img src="<c:url value='/'/>images/map/mark_blue_off.gif" width="12" height="15" alt="위치표시" onClick="javascript:alert('위치정보가 없습니다.');"></a>
            	</c:when>
            	<c:otherwise>
            	<a href="#"><img src="<c:url value='/'/>images/map/mark_blue.gif" width="12" height="15" alt="위치표시" onClick='javascript:parent.setPointOfMove(<c:out value="${result.xCrdntLo}"/>,<c:out value="${result.yCrdntLa}"/>)' ></a>
            	</c:otherwise>
            </c:choose>
           	</td>
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
