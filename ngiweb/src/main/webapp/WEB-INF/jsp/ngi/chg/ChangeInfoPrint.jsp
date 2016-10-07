<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import ="egovframework.com.cmm.LoginVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	String img = null;
	if (request.getMethod().equals("POST")) {
		img = request.getParameter("imgdata") != null ? request.getParameter("imgdata") : "";
		img = img.replaceAll("<","&lt;");
		img = img.replaceAll(">","&gt;");
		img = img.replaceAll("&","&amp;");
		img = img.replaceAll("\"","&quot;");
	} else {
		return;
	}
%>
<%
 /**
  * @Class Name : ChangeInfoInqire.jsp
  * @Description : ChangeInfo Inqire 화면
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
String sUserId = (String)session.getAttribute("sUserId");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<c:set var="registerFlag" value="${0 eq changeSttemntInfoVO.changeInfoId ? '등록' : '수정'}"/>

<title> <c:out value="${registerFlag}"/> </title>

<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngicommon.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngimap.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXJ.min.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXInput.css">
<link href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<style type="text/css">
@page { size: landscape; }
@media print { .noprint { display:none; } }
</style>
<script src="<c:url value='/'/>js/map/apikey.js"></script>
<script src="<c:url value='/'/>js/openlayers/OpenLayers.js"></script>
<script src="<c:url value='/'/>js/map/lib/proj4js-compressed.js"></script>
<script src="<c:url value='/'/>js/axisj/jquery/jquery.min.js"></script>
<script src="<c:url value='/'/>js/map/changeinforegister.js"></script>
<script type="text/javascript" src="<c:url value='/'/>js/EgovMultiFile.js" ></script>
</head>
<body>

<form:form commandName="changeSttemntInfoVO" name="detailForm" id="detailForm"  enctype="multipart/form-data" >
<input type="hidden" name="changeInfoId" value="<c:out value='${changeSttemntInfoVO.changeInfoId}'/>"  />
<input type="hidden" name="registerId" value="<c:out value='${changeSttemntInfoVO.registerId}'/>" />
<input type="hidden" name="changeCl" value="01"  />
<input type="hidden" name="XCrdntLo" value="<c:out value='${changeSttemntInfoVO.XCrdntLo}'/>"  />
<input type="hidden" name="YCrdntLa" value="<c:out value='${changeSttemntInfoVO.YCrdntLa}'/>"  />
<input type="hidden" name="pnuCd" value="<c:out value='${changeSttemntInfoVO.pnuCd}'/>"  />
<input type="hidden" name="addrTy" value="<c:out value='${changeSttemntInfoVO.addrTy}'/>"  />
<input type="hidden" name="vectorList" value="<c:out value='${changeSttemntInfoVO.vectorList}'/>"  />

<input type="hidden" name="posblAtchFileNumber" value="<c:out value='${bdMstr.posblAtchFileNumber}'/>" />
<input type="hidden" name="posblAtchFileSize" value="<c:out value='${bdMstr.posblAtchFileSize}'/>" />

<div id="content_pop">

	<div id="table">
<table align="center" cellpadding="3" cellspacing="3" width="1000" class="tabSwrite4">
		<colgroup>
			<col width="50%"/>
			<col width="50%"/>

		</colgroup>		
	<tr>
	<td>
		<img src="data:image/png;base64,<%=img %>" alt="map_save" style="border-spacing:0px;width:500px;height:497px;">
	</td>
	<td>
	
	
	<table align="center" cellpadding="3" cellspacing="3" class="tabSwrite">
		<colgroup>
			<col width="70"/>
			<col width=""/>
			<col width="70"/>
			<col width=""/>			
		</colgroup>
		<tr>
			<th>신고자명</th>
			<td colspan="3"><c:out value="${changeSttemntInfoVO.registerNm}" />
			</td>
		</tr>	
        <% if(sUserId != null){ %>		
			<c:set var="aSessionId" value="<%=sUserId%>" />
			<c:if test="${changeSttemntInfoVO.registerId == aSessionId}">			
		<tr>
			<th>전화번호</th>
			<td><c:out value="${changeSttemntInfoVO.telno}" />
			</td>
			
			<th>전자우편</th>
			<td><c:out value="${changeSttemntInfoVO.email}" />
			</td>			
		</tr>	
			</c:if>
		<% } %> 		
		<tr>

		</tr>	
		</table>	
		<table align="center" cellpadding="3" cellspacing="3"  class="tabSwrite">
		<colgroup>
			<col width="70"/>
			<col width=""/>
			<col width="70"/>
			<col width=""/>			
		</colgroup>				
		<tr>
			<th>신고제목</th>
			<td><c:out value="${changeSttemntInfoVO.changeSj}" />
			</td>
			<th>유형</th>
			<td>
	            <c:forEach var="sttemntty_result" items="${sttemntty_result}" varStatus="status">
	                <c:if test="${changeSttemntInfoVO.sttemntTy == sttemntty_result.code}"><c:out value="${sttemntty_result.codeNm}"/></c:if>
	            </c:forEach>   			
		
			</td>			
		</tr>			

		<tr>
			<th>변동지 주소</th>
			<td colspan="3">(도로명)<c:out value="${changeSttemntInfoVO.changeRnAdresCn}" /><br /><br />
			(지번)<c:out value="${changeSttemntInfoVO.changeLnmAdresCn}" />
			<c:if test="${changeSttemntInfoVO.changeInfoId != 0}">
			</c:if>
			</td>			
		</tr>	
		<!-- 
		<tr>
			<th>상세주소</th>
			<td colspan="3"><c:out value="${changeSttemntInfoVO.changeRnAdresDetailCn}" />&nbsp;
			</td>
		</tr>	
		 -->
		</table>
		<table align="center" cellpadding="3" cellspacing="3"  class="tabSwrite">
		<colgroup>
			<col width="70"/>
			<col width=""/>
		</colgroup>
		<tr>
			<th>신고내용</th>
			<td><textarea id="sttemntCn" name="sttemntCn" rows="5" cols="40" readonly><c:out value="${changeSttemntInfoVO.sttemntCn}" escapeXml="false" /></textarea>

			</td>
		</tr>

				      <c:if test="${not empty changeSttemntInfoVO.atchFileId}">
				          <tr> 
				            <td class="td_width">첨부파일 목록</td>
				            <td class="td_content" colspan="5">
				                <c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
				                    <c:param name="param_atchFileId" value="${changeSttemntInfoVO.atchFileId}" />
				                </c:import>
				            </td>
				          </tr>
				      </c:if>		
			

	</table>

		<table align="center" cellpadding="3" cellspacing="3"  class="tabSwrite">
		<colgroup>
			<col width="50"/>
			<col width="50"/>
			<col width="100"/>
			<col width="70"/>	
			<col width="50"/>
		</colgroup>	
		<tr style="height:30px;">
			<th>번호</th>
			<th>상태 </th>
			<th>의견 </th>
			<th>일자 </th>
			<th>작성자 </th>
		</tr>
		<c:forEach var="histresult" items="${hist_result}" varStatus="status">
		<tr>
		<td><c:out value="${status.count}"/></td>
		<td>
	            <c:forEach var="processresult" items="${process_result}" varStatus="status">
	                <c:if test="${histresult.sttus == processresult.code}"><c:out value="${processresult.codeNm}"/></c:if>
	            </c:forEach> 		
		</td>
		<td><c:out value="${histresult.opinion}"/></td>
		<td><c:out value="${histresult.rgsde}"/></td>
		<td><c:out value="${histresult.register}"/></td>
		</tr>
		</c:forEach>
		</table>
</td>
</tr>
</table>
    
  </div>
	<div class="MapMarg_T30">
		<div id="BtnAreaMap">
			<div id="StyleButtonDivMap2">
			<button type="button" class="AXButtonSmall Blue noprint" OnClick="javascript:window.print();"><i class="fa fa-print fa-1"></i> 인쇄</button>
			<button type="button" class="AXButtonSmall Blue noprint" OnClick="javascript:window.close();"><i class="fa fa-align-justify fa-lg"></i> 닫기</button>
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

