<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
 /**
  * @Class Name : ChangeTrsInfoInqire.jsp
  * @Description : ChangeTrsInfo Inqire 화면
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
<c:set var="registerFlag" value="${0 eq changeSttemntCntrwkVO.changeInfoId ? '등록' : '수정'}"/>

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

<script type="text/javascript">
<c:if test="${changeSttemntCntrwkVO.changeInfoId != 0}">   
	$( document ).ready(function() {
		// 지도이동
		Proj4js.defs["EPSG:5186"] = "+proj=tmerc +lat_0=38 +lon_0=127 +k=1 +x_0=200000 +y_0=600000 +ellps=GRS80 +units=m +no_defs";
		Proj4js.defs["EPSG:5179"] = "+proj=tmerc +lat_0=38 +lon_0=127.5 +k=0.9996 +x_0=1000000 +y_0=2000000 +ellps=GRS80 +units=m +no_defs";		
		
		parent.setPointOfMove( <c:out value="${changeSttemntCntrwkVO.XCrdntLo}"/>,<c:out value="${changeSttemntCntrwkVO.YCrdntLa}"/>);
		// 팝업 생성,, 화면에 벡터 그림.
		var vectors = $('input[name="vectorList"]').val();
		if(vectors.length>0) {
			var itrf2000 = new OpenLayers.Projection("EPSG:5179");
			var externalLonLat = new OpenLayers.Projection("EPSG:5186");
			
			var list = vectors.split('|');
			for(var index=0;index<list.length;index++) {
				shpWkt = list[index];

				var wktFomat = new OpenLayers.Format.WKT();

				wktFomat.internalProjection = itrf2000;
				wktFomat.externalProjection = externalLonLat;

				createNewPopup2(wktFomat.read(shpWkt), false);
			}
		}
	});
</c:if>

<!--
/* 글 목록 화면 function */
function fn_egov_selectList() {
	parent.resetVectorList();	// 도형정보 초기화
   	document.getElementById("detailForm").action = "<c:url value='/ngi/chg/ChangeTrsInfoList.do'/>";
   	document.getElementById("detailForm").submit();		
}

/* 글 등록 취소 function */
function fn_egov_cancel() {
	parent.resetVectorList();	// 도형정보 초기화
	document.getElementById("detailForm").action = "<c:url value='/ngi/chg/ChangeTrsInfoList.do'/>";
   	document.getElementById("detailForm").submit();	
	//location.href = "<c:url value='/'/>ngi/chg/ChangeTrsInfoList.do";
}

-->
</script>
<script type="text/javascript">

/* 첨부파일 다운로드*/
function att_downFile(nums) {
	
	if(nums != undefined  ) {
		
		url = "<c:url value='/ngi/chg/trsinfoFileDownloadView.do'/>";
		window.open(url ,"file_blank_ch", "toolbar=no,width=500,height=367,directories=no,status=no,scrollbars=no,resizable=no");
		
		var form = document.createElement("form");
        form.setAttribute("method", "post");
        form.setAttribute("action", url);
        form.setAttribute("target", "file_blank_ch" );
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
</head>
<body>

<form:form commandName="changeSttemntCntrwkVO" name="detailForm" id="detailForm"  enctype="multipart/form-data" >
<input type="hidden" name="changeInfoId" value="<c:out value='${changeSttemntCntrwkVO.changeInfoId}'/>"  />
<input type="hidden" name="registerId" value="<c:out value='${changeSttemntCntrwkVO.registerId}'/>" />
<input type="hidden" name="changeCl" value="<c:out value='${changeSttemntCntrwkVO.changeCl}'/>"  />
<input type="hidden" name="XCrdntLo" value="<c:out value='${changeSttemntCntrwkVO.XCrdntLo}'/>"  />
<input type="hidden" name="YCrdntLa" value="<c:out value='${changeSttemntCntrwkVO.YCrdntLa}'/>"  />
<input type="hidden" name="pnuCd" value="<c:out value='${changeSttemntCntrwkVO.pnuCd}'/>"  />
<input type="hidden" name="vectorList" value="<c:out value='${updateOperationVO.vectorList}'/>"  />

<input type="hidden" name="posblAtchFileNumber" value="<c:out value='${bdMstr.posblAtchFileNumber}'/>" />
<input type="hidden" name="posblAtchFileSize" value="<c:out value='${bdMstr.posblAtchFileSize}'/>" />

<div id="content_pop">
	<div id="table">
		<table align="center" cellpadding="3" cellspacing="3" class="tabSwrite">
		<colgroup>
			<col width=""/>
		</colgroup>
		<tr>
			<th style="padding:8px;">변화정보 등록 내용</th>
		</tr>
		</table>	
	
	<table align="center" cellpadding="3" cellspacing="3" class="tabSwrite">
		<colgroup>
			<col width="70"/>
			<col width=""/>
			<col width="70"/>
			<col width=""/>			
		</colgroup>
		<tr>
			<th>신고자명</th>
			<td colspan="3"><c:out value="${changeSttemntCntrwkVO.registerNm}" />
			</td>
		</tr>	
		<c:if test="${changeSttemntCntrwkVO.changeCl == '01'}">
			<tr>
			<th>전화번호</th>
			<td><c:out value="${changeSttemntCntrwkVO.telno}" />
			</td>
			
			<th>전자우편</th>
			<td><c:out value="${changeSttemntCntrwkVO.email}" />
			</td>	
			</tr>	
		</c:if>		
		</table>	
		<table align="center" cellpadding="3" cellspacing="3"  class="tabSwrite">
		<colgroup>
			<col width="70"/>
			<col width=""/>
			<col width="70"/>
			<col width=""/>			
		</colgroup>
		<c:choose>
		<c:when test="${changeSttemntCntrwkVO.changeCl == '01'}">
		<tr>
			<th>신고제목</th>
			<td><c:out value="${changeSttemntCntrwkVO.changeSj}" />
			</td>
			<th>유형</th>
			<td>
	            <c:forEach var="changeTy_result" items="${changeTy_result}" varStatus="status">
	                <c:if test="${changeSttemntCntrwkVO.sttemntTy == changeTy_result.code}"><c:out value="${changeTy_result.codeNm}"/></c:if>
	            </c:forEach>   			
		
			</td>			
		</tr>			

		<tr>
			<th>변동지 주소</th>
			<td>(도로명)<c:out value="${changeSttemntCntrwkVO.changeRnAdresCn}" /><br /><br />
			(지번)<c:out value="${changeSttemntCntrwkVO.changeLnmAdresCn}" /> 
			</td>
			<th>위치</th>
			<td>			
			<c:if test="${changeSttemntCntrwkVO.changeInfoId != 0}">
			<c:choose>
            	<c:when test="${empty changeSttemntCntrwkVO.XCrdntLo}">
            	<a href="#"><img src="<c:url value='/'/>images/map/mark_blue_off.gif" width="12" height="15" alt="위치표시" onClick="javascript:alert('위치정보가 없습니다.');"></a>
            	</c:when>
            	<c:otherwise>
            	<a href="#"><img src="<c:url value='/'/>images/map/mark_blue.gif" width="12" height="15" alt="위치표시" onClick='javascript:parent.setPointOfMove(<c:out value="${changeSttemntCntrwkVO.XCrdntLo}"/>,<c:out value="${changeSttemntCntrwkVO.YCrdntLa}"/>)' ></a>
            	</c:otherwise>
            </c:choose>
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
			<td><c:out value="${changeSttemntCntrwkVO.sttemntCn}" escapeXml="false" />

			</td>
		</tr>

				      <c:if test="${not empty changeSttemntCntrwkVO.atchFileId}">
				          <tr> 
				            <td class="td_width">첨부파일 목록</td>
				            <td class="td_content" colspan="5">
				                <c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
				                    <c:param name="param_atchFileId" value="${changeSttemntCntrwkVO.atchFileId}" />
				                </c:import>
				            </td>
				          </tr>
				      </c:if>		
		</c:when>
		<c:when test="${changeSttemntCntrwkVO.changeCl == '02' || changeSttemntCntrwkVO.changeCl == '03'}">
	
		<tr>
			<th>소속기관</th>
			<td>
	            <c:forEach var="psitnEngn_result" items="${psitnEngn_result}" varStatus="status">
	                <c:if test="${changeSttemntCntrwkVO.psitnEngnNo == psitnEngn_result.code}"><c:out value="${psitnEngn_result.codeNm}"/></c:if>
	            </c:forEach>  
			</td>
			<th>계획기관</th>
			<td>
	            <c:forEach var="planEngn_result" items="${planEngn_result}" varStatus="status">
	                <c:if test="${changeSttemntCntrwkVO.planEngnNo == planEngn_result.code}"><c:out value="${planEngn_result.codeNm}"/></c:if>
	            </c:forEach> 			
		
			</td>			
		</tr>			
		<tr>
			<th>감독기관</th>
			<td>
	            <c:forEach var="planEngn_result" items="${planEngn_result}" varStatus="status">
	                <c:if test="${changeSttemntCntrwkVO.planEngnNo == planEngn_result.code}"><c:out value="${planEngn_result.codeNm}"/></c:if>
	            </c:forEach> 
			</td>
			<th>변동유형</th>
			<td>
	            <c:forEach var="changeTy_result" items="${changeTy_result}" varStatus="status">
	                <c:if test="${changeSttemntCntrwkVO.changeTy == changeTy_result.code}"><c:out value="${changeTy_result.codeNm}"/></c:if>
	            </c:forEach>   				
		
			</td>			
		</tr>			
		<tr>
			<th>공사명</th>
			<td colspan="3"><c:out value="${changeSttemntCntrwkVO.changeSj}" escapeXml="false"/>
			</td>
		</tr>			
		<tr>
			<th>대상지역</th>
			<td colspan="3"><c:out value="${changeSttemntCntrwkVO.trgetAreaNm}" />
			<c:choose>
            	<c:when test="${empty changeSttemntCntrwkVO.XCrdntLo}">
            	<a href="#"><img src="<c:url value='/'/>images/map/mark_blue_off.gif" width="12" height="15" alt="위치표시" onClick="javascript:alert('위치정보가 없습니다.');"></a>
            	</c:when>
            	<c:otherwise>
            	<a href="#"><img src="<c:url value='/'/>images/map/mark_blue.gif" width="12" height="15" alt="위치표시" onClick='javascript:parent.setPointOfMove(<c:out value="${changeSttemntCntrwkVO.XCrdntLo}"/>,<c:out value="${changeSttemntCntrwkVO.YCrdntLa}"/>)' ></a>
            	</c:otherwise>
            </c:choose>
			</td>
		</tr>	
		<tr>
			<th>공사시점</th>
			<td><c:out value="${changeSttemntCntrwkVO.cntrwkPnttm}" />
			</td>
			<th>공사종점</th>
			<td><c:out value="${changeSttemntCntrwkVO.cntrwkTmnl}" />
			</td>			
		</tr>	
		<tr>
			<th>면적</th>
			<td><c:out value="${changeSttemntCntrwkVO.ar}" /> ㎡
			</td>
			<th>연장</th>
			<td><c:out value="${changeSttemntCntrwkVO.extn}" /> ㎞
			</td>			
		</tr>			
		<tr>
			<th>대상변경전</th>
			<td><c:out value="${changeSttemntCntrwkVO.trgetBfchgCn}" />
			</td>
			<th>대상변경후</th>
			<td><c:out value="${changeSttemntCntrwkVO.trgetAftchCn}" />
			</td>			
		</tr>			
		<tr>
			<th>착공일</th>
			<td><fmt:formatDate value="${changeSttemntCntrwkVO.strwrkDe}" pattern="yyyy-MM-dd" />
			</td>
			<th>완공예정일</th>
			<td><fmt:formatDate value="${changeSttemntCntrwkVO.competPrearngeDe}" pattern="yyyy-MM-dd" />
			</td>			
		</tr>	
		<tr>
			<th>최종완공일</th>
			<td colspan="3"><fmt:formatDate value="${changeSttemntCntrwkVO.lastCompetDe}" pattern="yyyy-MM-dd"/>
			</td>
		</tr>	
		<tr>
			<th>담당자명</th>
			<td><c:out value="${changeSttemntCntrwkVO.chargerNm}" />
			</td>
			<th>담당부서</th>
			<td><c:out value="${changeSttemntCntrwkVO.chrgDeptNm}" />
			</td>			
		</tr>	
		<tr>
			<th>연락처</th>
			<td><c:out value="${changeSttemntCntrwkVO.chargerTlphonNo}" />
			</td>
			<th>이메일</th>
			<td><c:out value="${changeSttemntCntrwkVO.chargerEmail}" />
			</td>			
		</tr>		
		<tr>
			<th>좌표계</th>
			<td>
	            <c:forEach var="cntm_result" items="${cntm_result}" varStatus="status">
	                <c:if test="${changeSttemntCntrwkVO.cntm == cntm_result.code}"><c:out value="${cntm_result.codeNm}"/></c:if>
	            </c:forEach>
	        </td>
			<th>상태</th>
			<td>
	            <c:forEach var="process_result" items="${process_result}" varStatus="status">
	                <c:if test="${changeSttemntCntrwkVO.sttusTy == process_result.code}"><c:out value="${process_result.codeNm}"/></c:if>
	            </c:forEach> 

			</td>			
		</tr>			
		<tr>
			<th>비고</th>
			<td colspan="3"><c:out value="${changeSttemntCntrwkVO.rm}" escapeXml="false"/>
			</td>
		</tr>			
		<tr>
			<th>신고일자</th>
			<td><fmt:formatDate value="${changeSttemntCntrwkVO.registDe}" pattern="yyyy-MM-dd" />

			</td>
			<th>취득방법</th>
			<td>
	            <c:forEach var="acqsMthTy_result" items="${acqsMthTy_result}" varStatus="status">
	                <c:if test="${changeSttemntCntrwkVO.acqsMthTy == acqsMthTy_result.code}"><c:out value="${acqsMthTy_result.codeNm}"/></c:if>
	            </c:forEach>
			</td>			
		</tr>
		
		
		</c:when>
		</c:choose>	

		</table>
		<c:if test="${not empty updateOperationVO}">
		
		<table align="center" cellpadding="3" cellspacing="3" class="tabSwrite">
		<colgroup>
			<col width=""/>
		</colgroup>
		<tr>
			<th style="padding:8px;">성과등록 내용</th>
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
				<th>제목</th>
				<td><c:out value="${updateOperationVO.opertNm}" /></td>
				<th>사업지구</th>
				<td><c:out value="${updateOperationVO.bsnsDstrc}" /></td>	
			</tr>	
			<tr>
				<th scope="row">용역사</th>
				<td><c:out value="${updateOperationVO.servcExcprofsCode}" /></td>
				<th scope="row">등록자</th>
				<td><c:out value="${updateOperationVO.wrter}" /></td>		
			</tr>	


		</table>
			<table class="tabSwrite" align="center" summary="이 표는 성과 등록 상세보기에 대한 유형, 제원, 좌표계, 타원체, 투영법, 원점 정보 입니다.">
				<caption>성과 등록 상세보기</caption>
				<colgroup>
					<col width="70"/>
					<col width=""/>
					<col width="70"/>
					<col width=""/>	
				</colgroup>
				<tbody>
				
					<tr>
						<th scope="row">유형</th>
						<td><c:out value="${updateOperationVO.changeTy}" /></td>
						<th scope="row">제원</th>
						<td><c:out value="${updateOperationVO.manp}" /><c:out value="${updateOperationVO.unit}" /></td>
					</tr>
					<tr>
						<th scope="row">좌표계</th>
						<td><c:out value="${updateOperationVO.cntm}" /></td>
						<th scope="row">타원체</th>
						<td><c:out value="${updateOperationVO.oval}" /></td>
					</tr>
					<tr>
						<th scope="row">투영법</th>
						<td><c:out value="${updateOperationVO.trnsprclaw}" /></td>
						<th scope="row">원점</th>
						<td><c:out value="${updateOperationVO.trgnpt}" /></td>
					</tr>
					
				</tbody>
			</table>		
			<table class="tabSwrite" align="center" summary="이 표는 성과 등록 상세보기에 대한 촬영년도, 조사년도, 수정년월 정보 입니다.">
				<caption>성과 등록 상세보기</caption>
				<colgroup>
					<col width="70" />
					<col width="" />
					<col width="70" />
					<col width="" />
					<col width="70" />
					<col width="" />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><label for="select01">촬영년도</label></th>
						<td><c:out value="${updateOperationVO.phtogrfYear}" />년	</td>
						<th scope="row"><label for="select02">조사년도</label></th>
						<td><c:out value="${updateOperationVO.examinYear}" />년</td>
						<th scope="row"><label for="select03">수정년월</label></th>
						<td><c:out value="${updateOperationVO.updtYear}" />년 <c:out value="${updateOperationVO.updtMt}" />월</td>
					</tr>
				</tbody>
			</table>		


		<div class="titleBtn">
		<!-- 
			<p class="tit">객체성과</p>
			<span class="titEx">※ SHP파일, SHX파일, DBF파일이 포함되어 있는 폴더입니다.</span> 
			-->
			<div class="btnArea">
			<%
	        if(sUserId != null){
	        %>  
				<button type="button" class="AXButtonSmall Blue" onclick="javascript:att_downFile('<c:out value='${changeSttemntCntrwkVO.changeInfoId}'/>');"><i class="fa fa-arrow-circle-o-down fa-lg"></i> 첨부파일다운로드</button>
	        <% } else { %>
				<button type="button" class="AXButtonSmall Blue" onclick="javascript:alert('로그인을 해야합니다.');"><i class="fa fa-arrow-circle-o-down fa-lg"></i> 첨부파일다운로드</button>
	        <% } %>			
			</div><!-- //btnArea -->
		</div>
		
		</c:if>	

		<table align="center" cellpadding="3" cellspacing="3"  class="tabSwrite">
		<colgroup>
			<col width="70"/>
			<col width=""/>
		</colgroup>


			

		</table>


    
  </div>
	<div class="MapMarg_T30">
		<div id="BtnAreaMap">
			<div id="StyleButtonDivMap">

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

