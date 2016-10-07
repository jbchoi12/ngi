<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import ="egovframework.com.cmm.LoginVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
 /**
  * @Class Name : ChangeCntrwkInqire.jsp
  * @Description : ChangeCntrwk Inqire 화면
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
<c:set var="registerFlag" value="${0 eq changeCntrwkInfoVO.changeInfoId ? '등록' : '수정'}"/>

<title> <c:out value="${registerFlag}"/> </title>

<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngicommon.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngimap.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXJ.min.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXInput.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXSelect.css">
<link href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">

<script type="text/javascript" src="<c:url value='/'/>js/axisj/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<c:url value='/'/>js/axisj/dist/AXJ.min.js"></script>
<script type="text/javascript" src="<c:url value='/'/>js/axisj/lib/AXInput.js"></script>

<script src="<c:url value='/'/>js/map/apikey.js"></script>
<script src="<c:url value='/'/>js/openlayers/OpenLayers.js"></script>
<script src="<c:url value='/'/>js/map/lib/proj4js-compressed.js"></script>
<script src="<c:url value='/'/>js/map/changeinforegister.js"></script>


<script type="text/javascript" src="<c:url value='/'/>js/EgovMultiFile.js" ></script>
<!--For Commons Validator Client Side-->
<script type="text/javascript" src="<c:url value='/cmmn/validator.do'/>"></script>
<validator:javascript formName="ChangeCntrwkInfoVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">

function fn_egov_select(changeInfoId) {
	document.getElementById("listForm").changeInfoId.value = changeInfoId;
   	document.getElementById("listForm").action = "<c:url value='/ngi/chg/updateChangeCntrwkRegView.do'/>";
   	document.getElementById("listForm").submit();
}

/* 글 목록 화면 function */
function fn_egov_selectList() {
   	document.getElementById("listForm").action = "<c:url value='/ngi/chg/ChangeCntrwkList.do'/>";
   	document.getElementById("listForm").submit();		
}

/* 글 수정 화면 function */
function fn_egov_direct() {
	frm = document.getElementById("listForm");
   	//window.open("<c:url value='/ngi/chg/addChangeCntrwkView.do'/>","_blank","width=500,height=700,scrollbars=no");
   	frm.action = "<c:url value='/ngi/chg/addChangeCntrwkView.do'/>";
   	frm.submit();	
}

/* 엑셀등록 function */
function fn_egov_excel() {
	window.open("<c:url value='/ngi/chg/addChangeCntrwkExcelView.do'/>","excel_blank","width=500,height=800,scrollbars=no");
}

/* 글 삭제 function */
function fn_egov_delete(changeInfoId) {
	if(confirm("삭제하시겠습니까?")){
	frm = document.getElementById("listForm");
	frm.changeInfoId.value = changeInfoId;
	frm.action = "<c:url value='/ngi/chg/deleteChangeRegList.do'/>";
	frm.submit();
	}
}

function fn_egov_downFile(filename){
	location.href=filename;
}

/* 글 등록 취소 function */
function fn_egov_cancel() {

	location.href = "<c:url value='/'/>ngi/chg/ChangeCntrwkList.do";
}

/* 글 등록 function */
function fn_egov_comsave() {	

	var checkLocation = false;
	frm = document.getElementById("listForm");
	frm.selChangeInfoIds.value="";
	
	$("input[name=item_checkbox]:checkbox").each(function(){
		
  		if ( $(this).is(":checked") == true ) {
  			
            if(frm.selChangeInfoIds.value != "") {
            	frm.selChangeInfoIds.value += ",";
            }
  			frm.selChangeInfoIds.value+=$(this).val(); 
  			var position = $(this).parent().find("input[name=location]").val() ;
			if( position == "" ) {
				checkLocation = true;
			}
		}
	});
	
	if(checkLocation) {
		alert('위치정보가 없는건이 있습니다.\n위치정보를 입력해야 등록할 수 있습니다.');
		return;
	}
	
	/* TODO Validation기능 보완 */	
    if(frm.selChangeInfoIds.value != "") {
    	frm.action = "<c:url value='/ngi/chg/updateChangeCntrwkRegComInfo.do'/>";
	    frm.submit();  
    } else {
    	alert("체크된 항목이 없습니다.");
    }
}
/* 파일 다운로드 */
function fn_egov_fileUpm(id, type) {
	url = "<c:url value='/ngi/chg/cntrwkFileVirtualManageView.do'/>?changeInfoId="+id+"&fileType="+type;
	window.open(url ,"file_blank", "toolbar=no,width=500,height=367,directories=no,status=no,scrollbars=no,resizable=no");
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
		url = "<c:url value='/ngi/chg/cntrwkExcelDownloadView.do'/>";
		window.open("" ,"file_blank_ex", "toolbar=no,width=1100,height=363,directories=no,status=no,scrollbars=no,resizable=no");
		
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
		$("#AXInputDate1").bindDate({align:"left", valign:"middle", onchange:function(){

        }});
		
		$("#AXInputDate2").bindDate({align:"left", valign:"middle", onchange:function(){

        }});
		
		$("#AXInputDate3").bindDate({align:"left", valign:"middle", onchange:function(){

        }});		
	}
};
jQuery(document).ready(fnObj.pageStart.delay(0.1));
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
<style>
.CategDl li {line-height:20px;}
.tdheight  {height:15px;}
.paging_align li {float:left;}
</style>
</head>
<body>

<form:form commandName="searchVO" name="listForm" id="listForm" method="post"  enctype="multipart/form-data" >
<input type="hidden" name="changeInfoId" />
<input type="hidden" name="selChangeInfoIds" />

<div id="content_pop">
	<div id="table">
		<table align="center" cellpadding="3" cellspacing="3" class="tabSwrite">
			<colgroup>
				<col width="70"/>
				<col width=""/>
				<col width="70"/>
				<col width=""/>			
			</colgroup>
			<tbody>
				<tr>
					<th>신고자명</th>
					<td colspan="3"><%=session.getAttribute("sUserNm")%></td>
				</tr>	
			</tbody>
		</table>	
    <table width="95%" border="0" cellspacing="0" cellpadding="0" align="tabSwrite">
    <tr><td align="right">
		<div>
			<button type="button" class="AXButtonSmall Blue" OnClick="javascript:fn_egov_excel();"><i class="fa fa-th fa-lg"></i> 엑셀등록</button>				
			<button type="button" class="AXButtonSmall Blue" OnClick="javascript:fn_egov_direct();"><i class="fa fa-pencil fa-lg"></i> 직접입력</button>
		</div></td></tr>
    </table>		
		
		<table align="center" cellpadding="3" cellspacing="3"  class="tabSwrite" style="margin-top:19px;width: 100%;">
			<colgroup>
				<col width="10"/>
				<col width="60"/>
				<col width="130"/>
				<col width="70"/>
				<col width="100"/>
				<col width="20"/>
				<col width="30"/>
			</colgroup>		
			<tbody>
			    <tr align="center" style="height:35px;">
				    <!-- 
				    <td width="50">구분</td>
				     -->
					<th><input type="checkbox" id="allcheck"></th>
				    <th>변동유형</th>
				    <th>공사명</th>
				    <th>보고월</th>
					<th>첨부파일</th>
					<th>위치</th>
				    <th>삭제</th>
			    </tr>		
        <c:forEach var="result" items="${resultList}" varStatus="status">
          <tr>
            <td class="tdheight">
            	<input type="checkbox" class="checkbox" name="item_checkbox" value='<c:out value="${result.changeInfoId}"/>'>
            	<input type="hidden" name="location" value='<c:out value="${result.location}"/>'>
            </td>
            <td>	            
            	<c:forEach var="changeTy_result" items="${changeTy_result}" varStatus="status">
	                <c:if test="${result.changeTy == changeTy_result.code}"><c:out value="${changeTy_result.codeNm}"/></c:if>
	            </c:forEach>  
	        </td>
            <td><a href="javascript:fn_egov_select('<c:out value="${result.changeInfoId}"/>')"><c:out value="${fn:substring(result.changeSj, 0, 10)}"/></a>&nbsp;</td>
            <td><c:out value="${fn:substring(result.registDe, 0, 7)}"/></td>
            <td>
            <c:choose>
            	<c:when test="${empty result.filecode01}">
            	<a href="javascript:fn_egov_fileUpm('<c:out value="${result.changeInfoId}"/>','01');"><img src="<c:url value='/'/>images/btn/btn_position_grey.jpg" alt="위치도 첨부파일 다운로드" title="위치도 첨부파일 다운로드" /></a>
            	</c:when>
            	<c:otherwise>
            	<a href="javascript:fn_egov_fileUpm('<c:out value="${result.changeInfoId}"/>','01');"><img src="<c:url value='/'/>images/btn/btn_position.jpg" alt="위치도 첨부파일 다운로드" title="위치도 첨부파일 다운로드" /></a>
            	</c:otherwise>
            </c:choose>            
			<c:choose>
            	<c:when test="${empty result.filecode02}">
            	<a href="javascript:fn_egov_fileUpm('<c:out value="${result.changeInfoId}"/>','02');"><img src="<c:url value='/'/>images/btn/btn_start_grey.jpg" alt="착공도 첨부파일 다운로드" title="착공도 첨부파일 다운로드" /></a>
            	</c:when>
            	<c:otherwise>
            	<a href="javascript:fn_egov_fileUpm('<c:out value="${result.changeInfoId}"/>','02');"><img src="<c:url value='/'/>images/btn/btn_start.jpg" alt="착공도 첨부파일 다운로드" title="착공도 첨부파일 다운로드" /></a>
            	</c:otherwise>
            </c:choose>
            <c:choose>
            	<c:when test="${empty result.filecode03}">
            	<a href="javascript:fn_egov_fileUpm('<c:out value="${result.changeInfoId}"/>','03');"><img src="<c:url value='/'/>images/btn/btn_com_grey.jpg" alt="준공도 첨부파일 다운로드" title="준공도 첨부파일 다운로드" /></a>
            	</c:when>
            	<c:otherwise>
            	<a href="javascript:fn_egov_fileUpm('<c:out value="${result.changeInfoId}"/>','03');"><img src="<c:url value='/'/>images/btn/btn_com.jpg" alt="준공도 첨부파일 다운로드" title="준공도 첨부파일 다운로드" /></a>
            	</c:otherwise>
            </c:choose>

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
			
            <td>
	            <button type="button" class="AXButtonSmall Red" OnClick="javascript:fn_egov_delete('<c:out value="${result.changeInfoId}"/>');"><i class="fa fa-times fa-1"></i></button>	
            </td>
           </tr>
		 </c:forEach>
		</tbody>
		</table>
 	<!-- /List -->
	<div id="paging" class="page_paging MapMarg_T10">
		<div class="page_center" style="margin-left:70px;">
			<ul class="paging_align">
			<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_egov_link_page" />
			</ul><form:hidden path="pageIndex" />
		</div>
	</div>   
	  </div>
		<div class="MapMarg_T30">
			<div id="BtnAreaMap">
				<div id="StyleButtonDivMap">
				<button type="button" class="AXButtonSmall Blue" OnClick="javascript:excel_downFile();"><i class="fa fa-arrow-circle-o-down fa-lg"></i> 엑셀다운로드</button>
				<button type="button" class="AXButtonSmall Blue" OnClick="javascript:fn_egov_comsave();"><i class="fa fa-pencil fa-lg"></i> 완료</button>				
				<button type="button" class="AXButtonSmall Blue" OnClick="javascript:fn_egov_cancel();"><i class="fa fa-align-justify fa-lg"></i> 닫기</button>
				</div>
			</div>
		</div>
	</div>
<!-- 검색조건 유지 -->

</form:form>
</body>
</html>

