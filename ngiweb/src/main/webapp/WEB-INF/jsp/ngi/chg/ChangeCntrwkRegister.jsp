<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import ="egovframework.com.cmm.LoginVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
<!--For Commons Validator Client Side
<script type="text/javascript" src="<c:url value='/cmmn/validator.do'/>"></script>
<validator:javascript formName="ChangeCntrwkInfoVO" staticJavascript="false" xhtml="true" cdata="false"/>
-->

<script type="text/javascript">
/* 글 목록 화면 function */
function fn_egov_selectList() {
	parent.resetVectorList();	// 도형정보 초기화
   	document.getElementById("detailForm").action = "<c:url value='/ngi/chg/ChangeCntrwkList.do'/>";
   	document.getElementById("detailForm").submit();		
}

/* 글 수정 화면 function */
function fn_egov_update() {
	parent.resetVectorList();	// 도형정보 초기화
   	document.getElementById("detailForm").action = "<c:url value='/ngi/chg/ChangeCntrwkUpdt.do'/>";
   	document.getElementById("detailForm").submit();
}

/* 글 삭제 function */
function fn_egov_delete() {
	parent.resetVectorList();	// 도형정보 초기화
   	document.getElementById("detailForm").action = "<c:url value='/ngi/chg/deleteChangeInfo.do'/>";
   	document.getElementById("detailForm").submit();		
}

/* 글 등록 취소 function */
function fn_egov_cancel() {
	parent.resetVectorList();	// 도형정보 초기화
   	document.getElementById("detailForm").action = "<c:url value='/ngi/chg/ChangeCntrwkRegList.do'/>";
   	document.getElementById("detailForm").submit();	
}

function fn_egov_fileUpm() {
	if (confirm("먼저 저장을 해야합니다.\n저장하시겠습니까?")) {
		parent.saveVectorList("#iframeChgCntrwkinfo");	// 화면 폼에 벡터정보 설정.
		parent.resetVectorList();	// 도형정보 초기화
		frm = document.getElementById("detailForm");

		/* TODO Validation기능 보완 */

	    if (document.detailForm.changeSj.value =="") {
	        alert("공사명을 입력하세요");
	    } else if (document.detailForm.changeTy.value =="") {
	        alert("변동유형을 선택하세요");        
	    } else if (document.detailForm.trgetAreaNm.value =="") {
	        alert("대상지역을 입력하세요");        
	    } else {	
	    	
	    	document.detailForm.changeRnAdresCn.value = document.detailForm.trgetAreaNm.value;
		  	frm.action = "<c:url value='/ngi/chg/addChangeCntrwkInfoPre.do'/>";
		    frm.submit();
	    }	
	
	}
}


/* 글 등록 function */
function fn_egov_save() {	
	parent.saveVectorList("#iframeChgCntrwkinfo");	// 화면 폼에 벡터정보 설정.
	parent.resetVectorList();	// 도형정보 초기화
	frm = document.getElementById("detailForm");

	/* TODO Validation기능 보완 */

    if (document.detailForm.changeSj.value =="") {
        alert("공사명을 입력하세요");
    } else if (document.detailForm.changeTy.value =="") {
        alert("변동유형을 선택하세요");        
    } else if (document.detailForm.trgetAreaNm.value =="") {
        alert("대상지역을 입력하세요");        
    } else {	
    	
    	// 공사명/비고 내용 의 특수문자 치환(from mutipart로 인해 filter 사용 x)
    	//var subject = htmlReplaceText(jQuery('[name="changeSj"]').val());
    	//jQuery('[name="changeSj"]').val(subject);	
    	
    	//var rm = htmlReplaceText(jQuery('[name="rm"]').val());
    	//jQuery('[name="rm"]').val(rm);
    	document.detailForm.changeRnAdresCn.value = document.detailForm.trgetAreaNm.value;
	  	frm.action = "<c:url value="${registerFlag == '등록' ? '/ngi/chg/addChangeCntrwkInfo.do' : '/ngi/chg/updateChangeCntrwkInfo.do'}"/>";
	    frm.submit();
    }
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



//window.opener.document.location.href = window.opener.document.URL;

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
		$("#AXInputDate1").bindDate({align:"left", valign:"middle", onchange:function(){ }});
		$("#AXInputDate2").bindDate({align:"left", valign:"middle", onchange:function(){ }});
		$("#AXInputDate3").bindDate({align:"left", valign:"middle", onchange:function(){ }});		
	}
};
jQuery(document).ready(fnObj.pageStart.delay(0.1));
</script>

</head>
<body>

<form:form commandName="changeCntrwkInfoVO" name="detailForm" id="detailForm"  enctype="multipart/form-data" >
<input type="hidden" name="changeInfoId" value="<c:out value='${changeCntrwkInfoVO.changeInfoId}'/>"  />
<input type="hidden" name="registerId" value="<%=sUserId%>" />
<input type="hidden" name="registerNm" value='<%=session.getAttribute("sUserNm")%>' />
<input type="hidden" name="changeCl" value="02"  />
<input type="hidden" name="changeRnAdresCn"  value="<c:out value='${changeCntrwkInfoVO.changeRnAdresCn}'/>" />
<input type="hidden" name="changeRnAdresDetailCn" value="<c:out value='${changeCntrwkInfoVO.changeRnAdresDetailCn}'/>"  />
<input type="hidden" name="XCrdntLo" value="<c:out value='${changeCntrwkInfoVO.XCrdntLo}'/>"  />
<input type="hidden" name="YCrdntLa" value="<c:out value='${changeCntrwkInfoVO.YCrdntLa}'/>"  />
<input type="hidden" name="pnuCd" value="<c:out value='${changeCntrwkInfoVO.pnuCd}'/>"  />
<input type="hidden" name="addrTy" value="<c:out value='${changeCntrwkInfoVO.addrTy}'/>"  />
<input type="hidden" name="vectorList" value="<c:out value='${changeCntrwkInfoVO.vectorList}'/>"  />
<input type="hidden" name="registTmpCd" value="1"  />

<div id="content_pop">
	<div id="table">
		<table align="center" cellpadding="3" cellspacing="3" class="tabSwrite3">
			<colgroup>
				<col width="70"/>
				<col width=""/>
				<col width="70"/>
				<col width=""/>			
			</colgroup>
			<tr>
				<th>신고자명</th>
				<%-- <td colspan="3"><%=session.getAttribute("sUserNm")%> --%>
				<td colspan="3">최정빈
				</td>
			</tr>	
	
			<tr>
	
			</tr>	
		</table>	
		<table align="center" cellpadding="3" cellspacing="3"  class="tabSwrite3">
			<colgroup>
				<col width="70"/>
				<col width=""/>
				<col width="70"/>
				<col width=""/>			
			</colgroup>		
			<tr>
				<th>소속기관</th>
				<td colspan="3">
		           <form:select path="psitnEngnNo" id="psitnEngnNo" class="AXSelectSmall">
	                   <form:option value="">-선택-</form:option>
	                   <form:options items="${psitnEngn_result}" itemValue="code" itemLabel="codeNm"/>
	               </form:select> 			
				</td>
			</tr>
			<tr>
				<th>계획기관</th>
				<td colspan="3">
		           <form:select path="planEngnNo" id="planEngnNo" class="AXSelectSmall">
	                   <form:option value="">-선택-</form:option>
	                   <form:options items="${planEngn_result}" itemValue="code" itemLabel="codeNm"/>
	               </form:select> 			
				</td>			
			</tr>			
			<tr>
				<th>감독기관</th>
				<td colspan="3">
		           <form:select path="mngEngnNo" id="mngEngnNo" class="AXSelectSmall">
	                   <form:option value="">-선택-</form:option>
	                   <form:options items="${mngEngn_result}" itemValue="code" itemLabel="codeNm"/>
	               </form:select> 	
				</td>
			</tr>
			<tr>
				<th>변동유형(*)</th>
				<td colspan="3">
					<form:select path="changeTy" id="changeTy" class="AXSelectSmall">
	                   <form:option value="">-선택-</form:option>
	                   <form:options items="${changeTy_result}" itemValue="code" itemLabel="codeNm"/>
	               </form:select>	
				</td>			
			</tr>			
			<tr>
				<th>공사명(*)</th>
				<td colspan="3">
					<form:input path="changeSj" class="AXInput W200"/>
					&nbsp;<form:errors path="changeSj" />
				</td>
			</tr>			
			<tr>
				<th>대상지역(*)</th>
				<td colspan="3">
					<form:input path="trgetAreaNm" class="AXInput W200"/>
					&nbsp;<form:errors path="trgetAreaNm" />
					<div id="regAddrPoint_pop_b">지도에 위치를 클릭하면</br>지도에 마커가 표시됩니다.</div>
					
				<c:choose>
			<c:when test="${empty changeCntrwkInfoVO.XCrdntLo}">
			<a href="#"><img src="<c:url value='/'/>images/map/mark_blue_off.gif" width="12" height="15" alt="위치표시" onClick="javascript:alert('위치정보가 없습니다.');"></a>
			</c:when>
			<c:otherwise>
			<a href="#"><img src="<c:url value='/'/>images/map/mark_blue.gif" width="12" height="15" alt="위치표시" onClick='javascript:parent.setPointOfMove(<c:out value="${changeCntrwkInfoVO.XCrdntLo}"/>,<c:out value="${changeCntrwkInfoVO.YCrdntLa}"/>)' ></a>
			</c:otherwise>
		    </c:choose>
			&nbsp;
					<button type="button" class="AXButtonSmall Blue" id="regAddrPoint_b">위치입력</button> 
					<a href="#" id="regAddrAddLine"><img src="<c:url value='/'/>images/map/mmenu_line.png" alt="선그리기" title="선그리기" ></a>
					<a href="#" id="regAddrAddPolygon"><img src="<c:url value='/'/>images/map/mmenu_side.png" alt="면그리기" title="면그리기" ></a>
				</td>
			</tr>	
			<tr>
				<th>공사시점</th>
				<td>
					<form:input path="cntrwkPnttm" class="AXInput W100"/>
					&nbsp;<form:errors path="cntrwkPnttm" />
				</td>
				<th>공사종점</th>
				<td>
					<form:input path="cntrwkTmnl" class="AXInput W100"/>
					&nbsp;<form:errors path="cntrwkTmnl" />			
				</td>			
			</tr>	
			<tr>
				<th>면적</th>
				<td>
					<form:input path="ar" class="AXInput W100"/>
					&nbsp;<form:errors path="ar" />	 ㎡
				</td>
				<th>연장</th>
				<td>
					<form:input path="extn" class="AXInput W100"/>
					&nbsp;<form:errors path="extn" /> ㎞
				</td>			
			</tr>			
			<tr>
				<th>대상변경전</th>
				<td>
					<form:input path="trgetBfchgCn" class="AXInput W100"/>
					&nbsp;<form:errors path="trgetBfchgCn" />				
				</td>
				<th>대상변경후</th>
				<td>
					<form:input path="trgetAftchCn" class="AXInput W100"/>
					&nbsp;<form:errors path="trgetAftchCn" />			
				</td>			
			</tr>			
			<tr>
				<th>착공일</th>
				<td>
					<form:input path="strwrkDe" class="AXInput W100" id="AXInputDate1" />
				</td>
				<th>완공예정일</th>
				<td>
					<form:input path="competPrearngeDe" class="AXInput W100" id="AXInputDate2" />
				</td>			
			</tr>	
			<tr>
				<th>최종완공일</th>
				<td colspan="3">
					<form:input path="lastCompetDe" class="AXInput W100" id="AXInputDate3" />
				</td>
			</tr>	
			<tr>
				<th>담당자명</th>
				<td>
					<form:input path="chargerNm" class="AXInput W100"/>
					&nbsp;<form:errors path="chargerNm" />	
				</td>
				<th>담당부서</th>
				<td>
					<form:input path="chrgDeptNm" class="AXInput W100"/>
					&nbsp;<form:errors path="chrgDeptNm" />				
				</td>			
			</tr>	
			<tr>
				<th>연락처</th>
				<td>
					<form:input path="chargerTlphonNo" class="AXInput W100"/>
					&nbsp;<form:errors path="chargerTlphonNo" />				
				</td>
				<th>이메일</th>
				<td>
					<form:input path="chargerEmail" class="AXInput W100"/>
					&nbsp;<form:errors path="chargerEmail" />				
				</td>			
			</tr>		
			<tr>
				<th>좌표계</th>
				<td>
		           <form:select path="cntm" id="cntm" class="AXSelectSmall">
	                   <form:option value="">-선택-</form:option>
	                   <form:options items="${cntm_result}" itemValue="code" itemLabel="codeNm"/>
	               </form:select> 			
		        </td>
				<th>취득방법</th>
				<td>
		           <form:select path="acqsMthTy" id="acqsMthTy" class="AXSelectSmall">
	                   <form:option value="">-선택-</form:option>
	                   <form:options items="${acqsMthTy_result}" itemValue="code" itemLabel="codeNm"/>
	               </form:select> 				
				</td>		        
			</tr>			
			<tr>
				<th>비고</th>
				<td colspan="3">
					<form:input path="rm" class="AXInput W200"/>
					&nbsp;<form:errors path="rm" />				
				</td>
			</tr>	
			<tr>
				<th>첨부파일</th>
				<td colspan="3">
					<button type="button" class="AXButtonSmall Green" OnClick="javascript:fn_egov_fileUpm();"><i class="fa fa-share-square fa-1"></i> 위치도</button>
					<button type="button" class="AXButtonSmall Green" OnClick="javascript:fn_egov_fileUpm();"><i class="fa fa-share-square fa-1"></i> 착공도</button>
					<button type="button" class="AXButtonSmall Green" OnClick="javascript:fn_egov_fileUpm();"><i class="fa fa-share-square fa-1"></i> 준공도</button>
				</td>
			</tr>					
		</table>
    
	  </div>
		<div class="MapMarg_T30">
			<div id="BtnAreaMap">
				<div id="StyleButtonDivMap">
				<button type="button" class="AXButtonSmall Blue" OnClick="javascript:fn_egov_save();"><i class="fa fa-pencil fa-lg"></i> 완료</button>				
				<button type="button" class="AXButtonSmall Blue" OnClick="javascript:fn_egov_cancel();"><i class="fa fa-align-justify fa-lg"></i> 닫기</button>
				</div>
			</div>
		</div>
	</div>
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
</form:form>
</body>
</html>

