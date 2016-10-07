<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import ="egovframework.com.cmm.LoginVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
//String sUserId = (String)session.getAttribute("sUserId");
String sUserId = "jbchoi";
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

<script src="<c:url value='/'/>js/map/apikey.js"></script>
<script src="<c:url value='/'/>js/openlayers/OpenLayers.js"></script>
<script src="<c:url value='/'/>js/map/lib/proj4js-compressed.js"></script>
<script src="<c:url value='/'/>js/axisj/jquery/jquery.min.js"></script>
<script src="<c:url value='/'/>js/map/changeinforegister.js"></script>


<script type="text/javascript" src="<c:url value='/'/>js/EgovMultiFile.js" ></script>

<script type="text/javascript">
<c:if test="${changeSttemntInfoVO.changeInfoId != 0}">   
	$( document ).ready(function() {
		// 지도이동
		parent.setPointOfMove( <c:out value="${changeSttemntInfoVO.XCrdntLo}"/>,<c:out value="${changeSttemntInfoVO.YCrdntLa}"/>);
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

/* 글 목록 화면 function */
function fn_egov_selectList() {
	parent.resetVectorList();	// 도형정보 초기화
   	document.getElementById("detailForm").action = "<c:url value='/ngi/chg/ChangeInfoList.do'/>";
   	document.getElementById("detailForm").submit();		
}

/* 글 수정 화면 function */
function fn_egov_update() {
	parent.resetVectorList();	// 도형정보 초기화
   	document.getElementById("detailForm").action = "<c:url value='/ngi/chg/updateChangeInfoView.do'/>";
   	document.getElementById("detailForm").submit();
}

/* 글 삭제 function */
function fn_egov_delete() {
	if(confirm("삭제하시겠습니까?")){
		parent.resetVectorList();	// 도형정보 초기화
	   	document.getElementById("detailForm").action = "<c:url value='/ngi/chg/deleteChangeInfo.do'/>";
	   	document.getElementById("detailForm").submit();		
	}
}

/* 글 인쇄 function */
function fn_egov_cancel() {
	parent.resetVectorList();	// 도형정보 초기화
	//document.getElementById("detailForm").pageIndex.value = pageNo;
	document.getElementById("detailForm").action = "<c:url value='/ngi/chg/ChangeInfoList.do'/>";
   	document.getElementById("detailForm").submit();
	//location.href = "<c:url value='/'/>ngi/chg/ChangeInfoList.do";
}

/* 글 등록 취소 function */
function fn_egov_print(changeinfoid) {
	parent.printSaveImage(changeinfoid);
}

function launchPopupCenter(url, name, width, height) {
	var str = "height=" + height + ",innerHeight=" + height;
	str += ",width=" + width + ",innerWidth=" + width;
	str += ",menubar=0";
	if (window.screen) {
		var ah = screen.availHeight - 30;
		var aw = screen.availWidth - 10;

		var xc = (aw - width) / 2;
		var yc = (ah - height) / 2;

		str += ",left=" + xc + ",screenX=" + xc;
		str += ",top=" + yc + ",screenY=" + yc;
	}

	var popup = window.open(url, name, str);
	// popup.focus();
	return popup;
}

/* 글 등록 function */
function fn_egov_save() {	

	parent.saveVectorList('#iframeChgSttemntinfo');	// 화면 폼에 벡터정보 설정.
	parent.resetVectorList();	// 도형정보 초기화
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
	
  	frm.action = "<c:url value="${registerFlag == '등록' ? '/ngi/chg/addChangeInfo.do' : '/ngi/chg/updateChangeInfo.do'}"/>";
    frm.submit();
    }
}

</script>
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
			<td><c:out value="${changeSttemntInfoVO.changeSj}" escapeXml="false"/>
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
			<td>(도로명)<c:out value="${changeSttemntInfoVO.changeRnAdresCn}" /><br /><br /> 
			(지번)<c:out value="${changeSttemntInfoVO.changeLnmAdresCn}" /> 
			</td>
			<th>위치</th>
			<td>
			<c:if test="${changeSttemntInfoVO.changeInfoId != 0}">
			<c:choose>
            	<c:when test="${empty changeSttemntInfoVO.XCrdntLo}">
            	<a href="#"><img src="<c:url value='/'/>images/map/mark_blue_off.gif" width="12" height="15" alt="위치표시" onClick="javascript:alert('위치정보가 없습니다.');"></a>
            	</c:when>
            	<c:otherwise>
            	<a href="#"><img src="<c:url value='/'/>images/map/mark_blue.gif" width="12" height="15" alt="위치표시" onClick='javascript:parent.setPointOfMove(<c:out value="${changeSttemntInfoVO.XCrdntLo}"/>,<c:out value="${changeSttemntInfoVO.YCrdntLa}"/>)' ></a>
            	</c:otherwise>
            </c:choose>
            </a>
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
			<td><textarea id="sttemntCn" name="sttemntCn" rows="5" cols="40"><c:out value="${changeSttemntInfoVO.sttemntCn}" escapeXml="false" /></textarea>

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
			<c:if test="${changeSttemntInfoVO.registerId == aSessionId}">	
				<c:if test="${changeSttemntInfoVO.processSttusSe == '01'}">	
					<button type="button" class="AXButtonSmall Blue" OnClick="javascript:fn_egov_update();"><i class="fa fa-pencil fa-lg"></i> 수정</button>				
					<button type="button" class="AXButtonSmall Blue" OnClick="javascript:fn_egov_delete();"><i class="fa fa-trash-o fa-lg"></i> 삭제</button>
				</c:if>
			</c:if>
		<% } %> 
			<button type="button" class="AXButtonSmall Blue" OnClick="javascript:fn_egov_print(<c:out value='${changeSttemntInfoVO.changeInfoId}'/>);"><i class="fa fa-print fa-1"></i> 인쇄</button>
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

