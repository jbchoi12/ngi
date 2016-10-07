<%--
  Class Name : NgiAboutSite.jsp
  Description : 사이트소개
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2011.08.31   JJY       경량환경 버전 생성
 
    author   : 실행환경개발팀 JJY
    since    : 2011.08.31 
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="common" uri="/WEB-INF/tlds/common.tld"%><!-- 공통코드 커스텀태그 -->

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>국토변화정보포털서비스</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngicommon.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngimain.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngistyle.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngitable.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXJ.min.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXInput.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXSelect.css">
<link href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<script type="text/javascript" src="<c:url value='/'/>js/axisj/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<c:url value='/'/>js/axisj/dist/AXJ.min.js"></script>
<script type="text/javascript" src="<c:url value='/'/>js/axisj/lib/AXInput.js"></script>
<script type="text/javascript" src="<c:url value='/'/>js/default.js"></script>

<script type="text/javascript">
<!--

function fn_egov_select(tpgrphNtfcId) {
	document.listForm.tpgrphNtfcId.value = tpgrphNtfcId;
   	document.listForm.action = "<c:url value='/ngi/ntc/NgiGeoNtcnView.do'/>";
   	document.listForm.submit();
}


/* 글 검색 화면 function */
function fn_egov_search() {
	//document.listForm.chgDe.value=document.frm.chgDe.value.replace('-', '');
	//document.listForm.chgBgnde.value=document.frm.chgBgnde.value.replace('-', '');
	//document.listForm.chgEndde.value=document.frm.chgEndde.value.replace('-', '');
	document.listForm.action = "<c:url value='/ngi/ntc/NgiGeoNtcnList.do'/>";
	document.listForm.submit();		
}

function fn_egov_link_page(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/ngi/ntc/NgiGeoNtcnList.do'/>";
	document.listForm.submit();
}

/* 글 등록 취소 function */
function fn_egov_cancel() {
	location.href = "<c:url value='/'/>ngi/ntc/NgiGeoNtcnList.do";
}

 // -->
</script>
<script>
/**
 * Require Files for AXISJ UI Component...
 * Based		: jQuery
 * Javascript 	: AXJ.js, AXInput.js
 * CSS			: AXJ.css, AXInput.css

var pageID = "Date";
var fnObj = {
	pageStart: function(){
		$("#AXInputDate_earlier").bindDate({align:"right", valign:"bottom", onChange:{ earlierThan:"AXInputDate_later", err:"종료일보다 빠른 날짜를 선택하세요"}});
		$("#AXInputDate_later").bindDate({align:"right", valign:"bottom", onChange:{ laterThan:"AXInputDate_earlier", err:"시작일보다 느린 날짜를 선택하세요"  } });
	}
};
jQuery(document).ready(fnObj.pageStart.delay(0.1));
*/	
</script>
</head>
<body>
<!-- wrap start -->
<div id="wrap">	
	<!-- header start -->
	<div id="header"><c:import url="/sym/mms/NgiMainMenuHead.do" /></div>	
	<!-- //header end -->
<!-- 내용 -->
    <div id="container">



<div class="Subcontainer">
	<!-- lnb -->
    <div class="LeftMenu">
    <h2>알림서비스</h2>
        <ul class="side1_ul">
        <li  class="side1_li"><a href="<c:url value='/'/>ngi/ntc/NgiNoticeService.do" class="side1_a">알림서비스란?</a></li>
        <li  class="side1_li"><a href="<c:url value='/'/>ngi/ntc/addTnNtcnSrvcView.do" class="side1_a">알림서비스 등록</a></li>
        <li  class="side1_li"><a href="<c:url value='/'/>ngi/ntc/NgiGeoNtcnList.do" class="side1_aon">지형고시 열람</a></li>
        </ul>
		<c:import url="/EgovPageLink.do?link=main/inc/NgiIncFBtn" />
    </div>
	<!-- //lnb --> 

<div id="Content">
    <div class="ConMTitle">
        <!-- history -->
        <div class="History">
            <a href="#" class="his1">처음으로</a><a href="#" class="History1"></a> &gt;
            <a href="#" class="History2">알림서비스</a> &gt; <a href="#" class="History3">지형고시 열람</a>
        </div>
        <!-- //history -->
        <h3>지형고시 열람</h3>         
    </div>


	<div class="ConMView">
	
	
		<div class="boardList">
			<table  class="tabSwrite6" summary="이 표는 지형고시 상세보기에 대한 고시명, 고시번호, 담당부서, 전화번호, 1:5,000 정위치, 1:5,000 구조화, 1:5,000 지형도, 1:25,000 정위치 정보 입니다.">
				<caption>지형고시 상세보기</caption>
				<colgroup>
					<col style="width:120px;" />
					<col style="width:120px;" />
					<col style="width:120px;" />
					<col style="width:120px;" />
					<col style="width:120px;" />
					<col style="width:auto;" />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">고시명</th>
						<td colspan="5">${topographyNoticeVO.ntfcNm}</td>
					</tr>
					<tr>
						<th scope="row">기관</th>
						<td><c:out value="${topographyNoticeVO.servcExcprofsCode}" /></td>
						<th scope="row">부서</th>
						<td><c:out value="${topographyNoticeVO.deptCode}" /></td>
						<th scope="row">등록자</th>
						<td><c:out value="${topographyNoticeVO.updusr}" /></td>
					</tr>
<!-- 					
					<tr>
						<th scope="row">1:5,000 정위치<br />(도엽)</th>
						<td><c:out value="${topographyNoticeVO.mapdmcA}" /></td>
						<th scope="row">1:5,000 구조화<br />(도엽)</th>
						<td><c:out value="${topographyNoticeVO.mapdmcB}" /></td>
						<th scope="row">1:25,000 정위치<br />(도엽)</th>
						<td><c:out value="${topographyNoticeVO.mapdmcC}" /></td>
					</tr>
 -->					
				</tbody>
			</table>
		</div><!-- //boardList -->

		<div class="boardList">
			<table summary="이 표는 지형고시 상세보기에 대한 번호, 도엽명(1:5,000), 도엽번호(1:5,000), 도엽명(1:25,000), 도엽번호(1:25,000), 수정대상, 수정내용, 제원, 위치(시점) 정보 입니다. "  class="tabSwrite6" >
				<caption>지형고시 상세보기</caption>
				<colgroup>
					<col style="width:5%;" />
					<col style="width:8%;" />
					<col style="width:8%;" />
					<col style="width:8%;" />
					<col style="width:8%;" />
					<col style="width:8%;" />
					<col style="width:auto;" />
					<col style="width:8%;" />
					<col style="width:20%;" />
				</colgroup> 
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">도엽명<br>(1:5,000)</th>
						<th scope="col">도엽번호<br>(1:5,000)</th>
						<th scope="col">도엽명<br>(1:25,000)</th>
						<th scope="col">도엽번호<br>(1:25,000)</th>
						<th scope="col">변동유형</th>
						<th scope="col">수정내용</th>
						<th scope="col">제원</th>
						<th scope="col">위치(시점)</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="result" items="${topographyNoticeVO.updtInfoMappingList}" varStatus="status">
					<tr>
						<td><c:out value="${status.count}" /><input type="hidden" id="updtInfoIds" name="updtInfoIds" value="${result.updtInfoId }"></td>
						<td><c:out value="${result.mapdmcNmB}" /></td>
						<td><c:out value="${result.mapdmcB}" /></td>
						<td><c:out value="${result.mapdmcNmC}" /></td>
						<td><c:out value="${result.mapdmcC}" /></td>
						<td><common:cdVal codeType="CHG003" codeCd="${result.changeTy}" /></td>
						<td>${result.opertNm}</td>
						<td><c:out value="${result.manp}" /><common:cdVal codeType="MNG024" codeCd="${result.unit}" /></td>
						<td>${result.cntrwkPnttm}</td>
					</tr>
					</c:forEach>
					<c:if test="${empty topographyNoticeVO.updtInfoMappingList}">
					<tr>
						<td align="center" colspan="9"><spring:message code="info.nodata.msg" /></td>
					</tr>
					</c:if>
				</tbody>
			</table>
		</div><!-- //boardList -->

		<div class="selectList">
		
			<table  class="tabSwrite6" summary="이 표는 지형고시 상세보기에 대한 측량지역, 보관지역, 기타사항 정보 입니다.">
				<caption>지형고시 상세보기</caption>
				<colgroup>
					<col style="width:150px;" />
					<col style="width:auto;" />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">측량시기</th>
						<td><fmt:formatDate value="${topographyNoticeVO.mesrPdBegin}" pattern="yyyy-MM-dd" /> ~ <fmt:formatDate value="${topographyNoticeVO.mesrPdEnd}" pattern="yyyy-MM-dd"/></td>
					</tr>
					<tr>
						<th scope="row">고시일자</th>
						<td><fmt:formatDate value="${topographyNoticeVO.ntfcDe}" pattern="yyyy-MM-dd" /></td>
					</tr>
				</tbody>
			</table>		
		</div><!-- //selectList -->

		<div class="boardList">
			<table  class="tabSwrite6" summary="이 표는 지형고시 상세보기에 대한 측량지역, 보관지역, 기타사항 정보 입니다.">
				<caption>지형고시 상세보기</caption>
				<colgroup>
					<col style="width:150px;" />
					<col style="width:230px;" />
					<col style="width:150px;" />
					<col style="width:auto;" />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">측량지역</th>
						<td>${topographyNoticeVO.mesrArea}</td>
						<th scope="row">보관지역</th>
						<td>${topographyNoticeVO.cstdyPlace}</td>
					</tr>
					<tr>
						<th scope="row"><label for="txtInfo01">기타사항</label></th>
						<td colspan="3">
							<textarea rows="5" cols="auto" id="txtInfo01" class="txtArea">${topographyNoticeVO.etcMatter}</textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div><!-- //boardList -->

		<div class="boardList scoll_y">
			<table  class="tabSwrite6" summary="이 표는 지형고시 상세보기에 대한 의견 첨부파일 정보 입니다.">
				<caption>지형고시 상세보기</caption>
				<colgroup>
					<col style="width:200px;" />
					<col style="width:auto;" />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">첨부파일</th>
						<td class="cell_l20">
							<span class="mr10"><img src="<c:url value='/images/btn/btn_addfile.jpg' />" alt="첨부파일" /></span>
							<c:import url="../../../ngi/board/selectFileInfs.do" charEncoding="utf-8">
			                    <c:param name="param_atchFileId" value="${topographyNoticeVO.atchFileId}" /> 
			                </c:import>
						</td>
					</tr>
				</tbody>
			</table>
		</div><!-- //boardList -->
		
	<div class="MapMarg_T30">
		<div id="BtnAreaMap">
			<div id="StyleButtonDivMap" style="text-align:right;">

			<button type="button" class="AXButtonSmall Blue" OnClick="javascript:fn_egov_cancel();"><i class="fa fa-align-justify fa-lg"></i> 목록</button>

			</div>
		</div>
	</div>			
	</div>
   

	</div> 	
	
		</div>

    <!-- 하단 유틸메뉴 -->
    <div class="MBotUtill"><c:import url="/EgovPageLink.do?link=main/inc/NgiIncUtil" /></div>
    <!-- //하단 유틸메뉴 -->


    <!-- 하단 배너 -->
    <div class="MBanner"><c:import url="/EgovPageLink.do?link=main/inc/NgiIncBanner" /></div>
    <!-- //하단 배너 -->

  </div>
<!-- //내용 -->
	<!-- footer 시작 -->
	<div id="footer"><c:import url="/EgovPageLink.do?link=main/inc/NgiIncFooter" /></div>
	<!-- //footer 끝 -->
</div>
<!-- //wrap end -->
</body>
</html>