<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<c:set var="registerFlag" value="${0 eq changeCntrwkInfoVO.changeInfoId ? '등록' : '수정'}"/>

<title>엑셀일괄등록</title>

<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngicommon.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngimap.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXJ.min.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXInput.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXSelect.css">
<link href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">

<script type="text/javascript" src="<c:url value='/'/>js/axisj/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<c:url value='/'/>js/axisj/dist/AXJ.min.js"></script>
<script type="text/javascript" src="<c:url value='/'/>js/axisj/lib/AXInput.js"></script>

<script type="text/javascript" src="<c:url value='/'/>js/EgovMultiFile.js" ></script>
<!--For Commons Validator Client Side-->
<script type="text/javascript" src="<c:url value='/cmmn/validator.do'/>"></script>
<validator:javascript formName="ChangeCntrwkInfoVO" staticJavascript="false" xhtml="true" cdata="false"/>


<script type="text/javascript">
<!--
/* 글 등록 취소 function */
function fn_egov_cancel() {
	window.opener.document.location.href = window.opener.document.URL;
	window.close();
}

window.opener.document.location.href = window.opener.document.URL;
// -->
$().ready(function(){
	window.resizeTo(420, 180);
});
</script>

</head>
<body>

<form:form commandName="changeCntrwkInfoVO" name="detailForm" id="detailForm"  enctype="multipart/form-data" >
<input type="hidden" name="changeInfoId" value="<c:out value='${changeCntrwkInfoVO.changeInfoId}'/>"  />
<input type="hidden" name="registerId" value="<c:out value='${changeCntrwkInfoVO.registerId}'/>" />
<input type="hidden" name="changeCl" value="02"  />
<input type="hidden" name="registTmpCd" value="1"  />

<div id="content_pop">
	<div id="table">
		<table align="center" cellpadding="3" cellspacing="3" class="exInfo">
			<colgroup>
				<col width="170"/>
				<col width=""/>
			</colgroup>
			
<%-- 			<tr>
				<th>신고자명</th>
				<td><c:out value="${changeCntrwkInfoVO.registerNm}" /></td>
			</tr>
			<tr>
				<th>filetype</th>
				<td><c:out value="${filetype}" /></td>
			</tr>	
			<tr>
				<th>filename</th>
				<td><c:out value="${filename}" /></td>
			</tr>	
 			<tr>
				<th>change_info_id</th>
				<td><c:out value="${change_info_id}" /></td>
			</tr>	  --%>
			<c:choose>
			<c:when test="${not empty exception}">
			<tr>
				<th>업로드</th>
				<td>잘못된 데이터가 있습니다.</td>
			</tr>
			<tr>
				<th>결과</th>
				<td><c:out value="${message}" /></td>
			</tr>
			<tr>
				<th>exception</th>
				<td><c:out value="${exception}" /></td>
			</tr>
			</c:when>
			<c:otherwise>
			<tr>
				<th>업로드</th>
				<td>엑셀파일 업로드를 하였습니다.</td>
			</tr>
			<tr>
				<th>결과</th>
				<td><c:out value="${message}" />개의 행을 처리하였습니다.</td>
			</tr>
			</c:otherwise>
			</c:choose>
			
		</table>	
    
	  </div>
		<div class="MapMarg_T10">
			<div id="BtnAreaMap">
				<div id="StyleButtonDivMap">
				<!-- <button type="button" class="AXButton Blue" OnClick="javascript:fn_egov_save();"><i class="fa fa-pencil fa-lg"></i> 완료</button>	 -->			
				<button type="button" class="AXButton Blue" OnClick="javascript:fn_egov_cancel();"><i class="fa fa-align-justify fa-lg"></i> 닫기</button>
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

