<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : TnCntcInfoList.jsp
  * @Description : TnCntcInfo List 화면
  * @Modification Information
  * 
  * @author 이정연
  * @since 2014-09-19
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
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/sample.css'/>"/>
<script type="text/javascript">
<!--
/* 글 수정 화면 function */


function fn_egov_select(cntcInfoId) {
	document.getElementById("listForm").cntcInfoId.value = cntcInfoId;
   	document.getElementById("listForm").action = "<c:url value='/tnCntcInfo/updateTnCntcInfoView.do'/>";
   	document.getElementById("listForm").submit();
}

/* 글 등록 화면 function */
function fn_egov_addView() {
   	document.getElementById("listForm").action = "<c:url value='/tnCntcInfo/addTnCntcInfoView.do'/>";
   	document.getElementById("listForm").submit();		
}

/* pagination 페이지 링크 function */
function fn_egov_link_page(pageNo){
	document.getElementById("listForm").pageIndex.value = pageNo;
	document.getElementById("listForm").action = "<c:url value='/tnCntcInfo/TnCntcInfoList.do'/>";
   	document.getElementById("listForm").submit();
}

 // -->
</script>
</head>
<body>
<form:form commandName="searchVO" name="listForm" id="listForm" method="post">
	<input type="hidden" name="cntcInfoId" />
<div id="content_pop">
	<!-- 타이틀 -->
	<div id="title">
		<ul>
			<li><img src="<c:url value='/images/egovframework/rte/title_dot.gif'/>" alt="title" /> List </li>
		</ul>
	</div>
	<!-- // 타이틀 -->
	<!-- List -->
	<div id="table">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<colgroup>
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
							</colgroup>		  
			<tr>
								<th align="center">CntcInfoId</th>
								<th align="center">ChargerNm</th>
								<th align="center">SysNm</th>
								<th align="center">Telno</th>
								<th align="center">Email</th>
								<th align="center">CntcTy</th>
								<th align="center">ApplcDomnNm</th>
								<th align="center">Useprps</th>
								<th align="center">Rgsde</th>
								<th align="center">CrtfcCodeSe</th>
								<th align="center">IssuDe</th>
								<th align="center">ConfmAt</th>
							</tr>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			<tr>
													<td align="center" class="listtd"><a href="javascript:fn_egov_select('<c:out value="${result.cntcInfoId}"/>')"><c:out value="${result.cntcInfoId}"/></a>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.chargerNm}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.sysNm}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.telno}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.email}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.cntcTy}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.applcDomnNm}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.useprps}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.rgsde}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.crtfcCodeSe}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.issuDe}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.confmAt}"/>&nbsp;</td>
				    			</tr>
			</c:forEach>
		</table>
	</div>
	<!-- /List -->
	<div id="paging">
		<ui:pagination paginationInfo = "${paginationInfo}"
				   type="image"
				   jsFunction="fn_egov_link_page"
				   />
		<form:hidden path="pageIndex" />
	</div>
	<div id="sysbtn1">
		<ul>
			<li>
				<div id="sysbtn">
					<span class="btn_blue_l"><a href="javascript:fn_egov_addView();">등록</a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" alt="" />
					</span>
				</div>
			</li>
		</ul>
	</div>
</div>
</form:form>
</body>
</html>
