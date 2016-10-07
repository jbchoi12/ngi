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
String sUserId = (String)session.getAttribute("sUserId");
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
<link href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">

<script src="<c:url value='/'/>js/map/apikey.js"></script>
<script src="<c:url value='/'/>js/openlayers/OpenLayers.js"></script>
<script src="<c:url value='/'/>js/map/lib/proj4js-compressed.js"></script>
<script src="<c:url value='/'/>js/axisj/jquery/jquery.min.js"></script>
<script src="<c:url value='/'/>js/map/changeinforegister.js"></script>


<script type="text/javascript" src="<c:url value='/'/>js/EgovMultiFile.js" ></script>
<!--For Commons Validator Client Side
<script type="text/javascript" src="<c:url value='/cmmn/validator.do'/>"></script>
<validator:javascript formName="ChangeCntrwkInfoVO" staticJavascript="false" xhtml="true" cdata="false"/>
-->
<script type="text/javascript">
<c:if test="${changeSttemntInfoVO.changeInfoId != 0}">   
	$( document ).ready(function() {
		// 지도이동
		parent.setPointOfMove( <c:out value="${changeCntrwkInfoVO.XCrdntLo}"/>,<c:out value="${changeCntrwkInfoVO.YCrdntLa}"/>);
		// 팝업 생성,, 화면에 벡터 그림.
		var vectors = $('input[name="vectorList"]').val();
		if(vectors.length>0) {
			var list = vectors.split('|');
			for(var index=0;index<list.length;index++) {
				wkt = list[index];
				createNewPopup(new OpenLayers.Format.WKT().read(wkt), false);
			}
		}
	});
</c:if>
</script>
<script type="text/javascript">
<!--
/* 글 목록 화면 function */
function fn_egov_selectList() {
	parent.resetVectorList();	// 도형정보 초기화
   	document.getElementById("detailForm").action = "<c:url value='/ngi/chg/ChangeCntrwkList.do'/>";
   	document.getElementById("detailForm").submit();		
}

/* 글 수정 화면 function */
function fn_egov_update() {
	parent.resetVectorList();	// 도형정보 초기화
   	document.getElementById("detailForm").action = "<c:url value='/ngi/chg/updateChangeCntrwkView.do'/>";
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
	document.getElementById("detailForm").action = "<c:url value='/ngi/chg/ChangeCntrwkList.do'/>";
   	document.getElementById("detailForm").submit();
	//location.href = "<c:url value='/'/>ngi/chg/ChangeCntrwkList.do";
}

/* 글 등록 function */
function fn_egov_save() {	

	frm = document.getElementById("detailForm");

	/* TODO Validation기능 보완 */

    if (document.detailForm.changeSj.value =="") {
        alert("제목을 입력하세요");
    } else if (document.detailForm.sttemntTy.value =="") {
        alert("유형을 선택하세요");        
    } else if (document.detailForm.changeRnAdresCn.value =="") {
        alert("주소를 입력하세요");
    } else if (document.detailForm.sttemntCn.value =="") {
        alert("내용을 입력하세요");
    } else {	
	
  	frm.action = "<c:url value="${registerFlag == '등록' ? '/ngi/chg/addChangeCntrwkInfo.do' : '/ngi/chg/updateChangeCntrwkInfo.do'}"/>";
    frm.submit();
    }
}

/* 파일 다운로드 */
function fn_egov_fileUpm(id, type) {
	url = "<c:url value='/ngi/chg/cntrwkFileVirtualManageView.do'/>?changeInfoId="+id+"&fileType="+type;
	window.open(url ,"file_blank", "toolbar=no,width=500,height=367,directories=no,status=no,scrollbars=no,resizable=no");
}
// -->
</script>
</head>
<body>

<form:form commandName="changeCntrwkInfoVO" name="detailForm" id="detailForm"  enctype="multipart/form-data" >
<input type="hidden" name="changeInfoId" value="<c:out value='${changeCntrwkInfoVO.changeInfoId}'/>"  />
<input type="hidden" name="registerId" value="<c:out value='${changeCntrwkInfoVO.registerId}'/>" />
<input type="hidden" name="changeCl" value="02"  />
<input type="hidden" name="changeRnAdresCn"  value="<c:out value='${changeCntrwkInfoVO.changeRnAdresCn}'/>" />
<input type="hidden" name="changeRnAdresDetailCn" value="<c:out value='${changeCntrwkInfoVO.changeRnAdresDetailCn}'/>"  />
<input type="hidden" name="XCrdntLo" value="<c:out value='${changeCntrwkInfoVO.XCrdntLo}'/>"  />
<input type="hidden" name="YCrdntLa" value="<c:out value='${changeCntrwkInfoVO.YCrdntLa}'/>"  />
<input type="hidden" name="pnuCd" value="<c:out value='${changeCntrwkInfoVO.pnuCd}'/>"  />
<input type="hidden" name="addrTy" value="<c:out value='${changeCntrwkInfoVO.addrTy}'/>"  />
<input type="hidden" name="vectorList" value="<c:out value='${changeCntrwkInfoVO.vectorList}'/>"  />

<input type="hidden" name="posblAtchFileNumber" value="<c:out value='${bdMstr.posblAtchFileNumber}'/>" />
<input type="hidden" name="posblAtchFileSize" value="<c:out value='${bdMstr.posblAtchFileSize}'/>" />

<div id="content_pop">
	<div id="table">
	<table align="center" cellpadding="3" cellspacing="3" class="tabSwrite">
		<colgroup>
			<col width="70"/>
			<col width=""/>
			<col width="70"/>
			<col width=""/>			
		</colgroup>
		<tr>
			<th>신고자명</th>
			<td colspan="3"><c:out value="${changeCntrwkInfoVO.registerNm}" />
			</td>
		</tr>	

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
			<th>소속기관</th>
			<td>
	            <c:forEach var="psitnEngn_result" items="${psitnEngn_result}" varStatus="status">
	                <c:if test="${changeCntrwkInfoVO.psitnEngnNo == psitnEngn_result.code}"><c:out value="${psitnEngn_result.codeNm}"/></c:if>
	            </c:forEach>  
			</td>
			<th>계획기관</th>
			<td>
	            <c:forEach var="planEngn_result" items="${planEngn_result}" varStatus="status">
	                <c:if test="${changeCntrwkInfoVO.planEngnNo == planEngn_result.code}"><c:out value="${planEngn_result.codeNm}"/></c:if>
	            </c:forEach> 			
		
			</td>			
		</tr>			
		<tr>
			<th>감독기관</th>
			<td>
	            <c:forEach var="mngEngn_result" items="${mngEngn_result}" varStatus="status">
	                <c:if test="${changeCntrwkInfoVO.mngEngnNo == mngEngn_result.code}"><c:out value="${mngEngn_result.codeNm}"/></c:if>
	            </c:forEach> 
			</td>
			<th>변동유형</th>
			<td>
	            <c:forEach var="changeTy_result" items="${changeTy_result}" varStatus="status">
	                <c:if test="${changeCntrwkInfoVO.changeTy == changeTy_result.code}"><c:out value="${changeTy_result.codeNm}"/></c:if>
	            </c:forEach>   				
		
			</td>			
		</tr>			
		<tr>
			<th>공사명</th>
			<td colspan="3"><c:out value="${changeCntrwkInfoVO.changeSj}" escapeXml="false"/>
			</td>
		</tr>			
		<tr>
			<th>대상지역</th>
			<td colspan="3"><c:out value="${changeCntrwkInfoVO.trgetAreaNm}" />
			<c:choose>
            	<c:when test="${empty changeCntrwkInfoVO.XCrdntLo}">
            	<a href="#"><img src="<c:url value='/'/>images/map/mark_blue_off.gif" width="12" height="15" alt="위치표시" onClick="javascript:alert('위치정보가 없습니다.');"></a>
            	</c:when>
            	<c:otherwise>
            	<a href="#"><img src="<c:url value='/'/>images/map/mark_blue.gif" width="12" height="15" alt="위치표시" onClick='javascript:parent.setPointOfMove(<c:out value="${changeCntrwkInfoVO.XCrdntLo}"/>,<c:out value="${changeCntrwkInfoVO.YCrdntLa}"/>)' ></a>
            	</c:otherwise>
            </c:choose>
			</td>
		</tr>	
		<tr>
			<th>공사시점</th>
			<td><c:out value="${changeCntrwkInfoVO.cntrwkPnttm}" />
			</td>
			<th>공사종점</th>
			<td><c:out value="${changeCntrwkInfoVO.cntrwkTmnl}" />
			</td>			
		</tr>	
		<tr>
			<th>면적</th>
			<td><c:out value="${changeCntrwkInfoVO.ar}" /> ㎡
			</td>
			<th>연장</th>
			<td><c:out value="${changeCntrwkInfoVO.extn}" /> ㎞
			</td>			
		</tr>			
		<tr>
			<th>대상변경전</th>
			<td><c:out value="${changeCntrwkInfoVO.trgetBfchgCn}" />
			</td>
			<th>대상변경후</th>
			<td><c:out value="${changeCntrwkInfoVO.trgetAftchCn}" />
			</td>			
		</tr>			
		<tr>
			<th>착공일</th>
			<td><fmt:formatDate value="${changeCntrwkInfoVO.strwrkDe}" pattern="yyyy-MM-dd" />
			</td>
			<th>완공예정일</th>
			<td><fmt:formatDate value="${changeCntrwkInfoVO.competPrearngeDe}" pattern="yyyy-MM-dd" />
			</td>			
		</tr>	
		<tr>
			<th>최종완공일</th>
			<td colspan="3"><fmt:formatDate value="${changeCntrwkInfoVO.lastCompetDe}" pattern="yyyy-MM-dd"/>
			</td>
		</tr>	
		<tr>
			<th>담당자명</th>
			<td><c:out value="${changeCntrwkInfoVO.chargerNm}" />
			</td>
			<th>담당부서</th>
			<td><c:out value="${changeCntrwkInfoVO.chrgDeptNm}" />
			</td>			
		</tr>	
		<tr>
			<th>연락처</th>
			<td><c:out value="${changeCntrwkInfoVO.chargerTlphonNo}" />
			</td>
			<th>이메일</th>
			<td><c:out value="${changeCntrwkInfoVO.chargerEmail}" />
			</td>			
		</tr>		
		<tr>
			<th>좌표계</th>
			<td>
	            <c:forEach var="cntm_result" items="${cntm_result}" varStatus="status">
	                <c:if test="${changeCntrwkInfoVO.cntm == cntm_result.code}"><c:out value="${cntm_result.codeNm}"/></c:if>
	            </c:forEach>
	        </td>
			<th>상태</th>
			<td>
	            <c:forEach var="process_result" items="${process_result}" varStatus="status">
	                <c:if test="${changeCntrwkInfoVO.sttusTy == process_result.code}"><c:out value="${process_result.codeNm}"/></c:if>
	            </c:forEach> 

			</td>			
		</tr>			
		<tr>
			<th>비고</th>
			<td colspan="3"><c:out value="${changeCntrwkInfoVO.rm}" escapeXml="false"/>
			</td>
		</tr>			
		<tr>
			<th>신고일자</th>
			<td><fmt:formatDate value="${changeCntrwkInfoVO.registDe}" pattern="yyyy-MM-dd" />

			</td>
			<th>취득방법</th>
			<td>
	            <c:forEach var="acqsMthTy_result" items="${acqsMthTy_result}" varStatus="status">
	                <c:if test="${changeCntrwkInfoVO.acqsMthTy == acqsMthTy_result.code}"><c:out value="${acqsMthTy_result.codeNm}"/></c:if>
	            </c:forEach>
			</td>			
		</tr>
		<tr>
			<th>공사번호</th>
			<td colspan="3"><c:out value="${changeCntrwkInfoVO.cntrwkNo}" />
			</td>
		</tr>	
		<tr>
			<th>첨부파일</th>
			<td colspan="3">
			<%
	        if(sUserId != null){
	        %>   
	            <c:choose>
	            	<c:when test="${empty changeCntrwkInfoVO.filecode01}">
	            	<a href="javascript:fn_egov_fileUpm('<c:out value="${changeCntrwkInfoVO.changeInfoId}"/>','01');"><img src="<c:url value='/'/>images/btn/btn_position_grey.jpg" alt="위치도 첨부파일 다운로드" title="위치도 첨부파일 다운로드" /></a>
	            	</c:when>
	            	<c:otherwise>
	            	<a href="javascript:fn_egov_fileUpm('<c:out value="${changeCntrwkInfoVO.changeInfoId}"/>','01');"><img src="<c:url value='/'/>images/btn/btn_position.jpg" alt="위치도 첨부파일 다운로드" title="위치도 첨부파일 다운로드" /></a>
	            	</c:otherwise>
	            </c:choose>			
				<c:choose>
	            	<c:when test="${empty changeCntrwkInfoVO.filecode02}">
	            	<a href="javascript:fn_egov_fileUpm('<c:out value="${changeCntrwkInfoVO.changeInfoId}"/>','02');"><img src="<c:url value='/'/>images/btn/btn_start_grey.jpg" alt="착공도 첨부파일 다운로드" title="착공도 첨부파일 다운로드" /></a>
	            	</c:when>
	            	<c:otherwise>
	            	<a href="javascript:fn_egov_fileUpm('<c:out value="${changeCntrwkInfoVO.changeInfoId}"/>','02');"><img src="<c:url value='/'/>images/btn/btn_start.jpg" alt="착공도 첨부파일 다운로드" title="착공도 첨부파일 다운로드" /></a>
	            	</c:otherwise>
	            </c:choose>
	            <c:choose>
	            	<c:when test="${empty changeCntrwkInfoVO.filecode03}">
	            	<a href="javascript:fn_egov_fileUpm('<c:out value="${changeCntrwkInfoVO.changeInfoId}"/>','03');"><img src="<c:url value='/'/>images/btn/btn_com_grey.jpg" alt="준공도 첨부파일 다운로드" title="준공도 첨부파일 다운로드" /></a>
	            	</c:when>
	            	<c:otherwise>
	            	<a href="javascript:fn_egov_fileUpm('<c:out value="${changeCntrwkInfoVO.changeInfoId}"/>','03');"><img src="<c:url value='/'/>images/btn/btn_com.jpg" alt="준공도 첨부파일 다운로드" title="준공도 첨부파일 다운로드" /></a>
	            	</c:otherwise>
	            </c:choose>
	        <% } else { %>
	            <c:choose>
	            	<c:when test="${empty changeCntrwkInfoVO.filecode01}">
	            	<a href="javascript:alert('로그인을 해야합니다.');"><img src="<c:url value='/'/>images/btn/btn_position_grey.jpg" alt="위치도 첨부파일 다운로드" title="위치도 첨부파일 다운로드" /></a>
	            	</c:when>
	            	<c:otherwise>
	            	<a href="javascript:alert('로그인을 해야합니다.');"><img src="<c:url value='/'/>images/btn/btn_position.jpg" alt="위치도 첨부파일 다운로드" title="위치도 첨부파일 다운로드" /></a>
	            	</c:otherwise>
	            </c:choose>			
				<c:choose>
	            	<c:when test="${empty changeCntrwkInfoVO.filecode02}">
	            	<a href="javascript:alert('로그인을 해야합니다.');"><img src="<c:url value='/'/>images/btn/btn_start_grey.jpg" alt="착공도 첨부파일 다운로드" title="착공도 첨부파일 다운로드" /></a>
	            	</c:when>
	            	<c:otherwise>
	            	<a href="javascript:alert('로그인을 해야합니다.');"><img src="<c:url value='/'/>images/btn/btn_start.jpg" alt="착공도 첨부파일 다운로드" title="착공도 첨부파일 다운로드" /></a>
	            	</c:otherwise>
	            </c:choose>
	            <c:choose>
	            	<c:when test="${empty changeCntrwkInfoVO.filecode03}">
	            	<a href="javascript:alert('로그인을 해야합니다.');"><img src="<c:url value='/'/>images/btn/btn_com_grey.jpg" alt="준공도 첨부파일 다운로드" title="준공도 첨부파일 다운로드" /></a>
	            	</c:when>
	            	<c:otherwise>
	            	<a href="javascript:alert('로그인을 해야합니다.');"><img src="<c:url value='/'/>images/btn/btn_com.jpg" alt="준공도 첨부파일 다운로드" title="준공도 첨부파일 다운로드" /></a>
	            	</c:otherwise>
	            </c:choose>       
	        <% } %>  
			</td>
		</tr>
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
		<td style="text-align:center;"><c:out value="${status.count}"/></td>
		<td style="text-align:center;">
	            <c:forEach var="processresult" items="${process_result}" varStatus="status">
	                <c:if test="${histresult.sttus == processresult.code}"><img src="<c:url value='/'/>images/icon/<c:out value="${processresult.code}"/>.png" ></c:if>
	            </c:forEach> 		
		</td>
		<td><c:out value="${histresult.opinion}"/></td>
		<td><c:out value="${histresult.rgsde}"/></td>
		<td><c:out value="${histresult.register}"/></td>
		</tr>
		</c:forEach>
		</table>


    
  </div>
	<div class="MapMarg_T30">
		<div id="BtnAreaMap">
			<div id="StyleButtonDivMap">
       		<% if(sUserId != null){ %>		
			<c:set var="aSessionId" value="<%=sUserId%>" />
			<c:if test="${changeCntrwkInfoVO.registerId == aSessionId}">	
				<c:if test="${changeCntrwkInfoVO.processSttusSe == '01'}">	
				<button type="button" class="AXButtonSmall Blue" OnClick="javascript:fn_egov_update();"><i class="fa fa-pencil fa-lg"></i> 수정</button>				
				<button type="button" class="AXButtonSmall Blue" OnClick="javascript:fn_egov_delete();"><i class="fa fa-trash-o fa-lg"></i> 삭제</button>
				</c:if>
			</c:if>
			<% } %> 
			<button type="button" class="AXButtonSmall Blue" OnClick="javascript:fn_egov_cancel();"><i class="fa fa-align-justify fa-lg"></i> 목록</button>

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

