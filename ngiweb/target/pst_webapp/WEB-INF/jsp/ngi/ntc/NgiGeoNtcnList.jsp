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
	
	
 <form name="listForm" action ="<c:url value='/ngi/ntc/NgiGeoNtcnList.do'/>" method="post">
 <input type="hidden" name="tpgrphNtfcId"  />
 <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
    <div class="remark">
        <div class="remark_right">
                    <select name="searchCondition"  class="AXSelectSmall"  title="조회조건 선택">
			           <!-- <option selected value=''>선택하세요</option> -->
			           <option value="01" <c:if test="${searchVO.searchCondition == '01'}">selected="selected"</c:if> >고시명</option>
			           <option value="02" <c:if test="${searchVO.searchCondition == '02'}">selected="selected"</c:if>>기관</option>			                       
			       </select>
			       <input name="searchKeyword" type="text" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="35" title="검색어 입력"  class="AXInput W200" >
             <button type="button" class="AXButtonSmall Blue" onClick="javascript:fn_egov_search();"><i class="fa fa-search fa-1"></i> 검색</button>&nbsp;
            			

        </div>
    </div>
 </form>

	<!-- List -->
	<table class="table_list" summary="이 표는 지형고시 조회에 대한 번호, 사업지구, 고시명, 용역사, 등록자, 등록일자 정보 입니다. ">
			<caption>지형고시 조회</caption>
			<colgroup>
				<col style="width:10%;" />
				<col style="width:auto;" />
				<col style="width:15%;" />
				<col style="width:12%;" />
				<col style="width:12%;" />
			</colgroup> 
			<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col">고시명</th>
					<th scope="col">기관</th>
					<th scope="col">등록자</th>
					<th scope="col">등록일자</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="result"  items="${resultList}"  varStatus="status">
				<c:set var="datano"  value="${paginationInfo.totalRecordCount - ( (paginationInfo.currentPageNo-1) * paginationInfo.pageSize + status.index ) }" />
				<tr>
					<td><c:out value="${datano}" /></td>
					<td><a href="#" onclick="fn_egov_select('<c:out value="${result.tpgrphNtfcId}" />')"><c:out value="${result.ntfcNm}" /></a></td>
					<td><c:out value="${result.servcExcprofsCode}" /></td>
					<td><c:out value="${result.updusr}" /></td>
					<td><fmt:formatDate value="${result.updde}" pattern="yyyy-MM-dd" /></td>
				</tr>
				</c:forEach>
				<c:if test="${empty resultList}">
				<tr>
					<td align="center" colspan="5"><spring:message code="info.nodata.msg" /></td>
				</tr>
				</c:if>
			</tbody>
	</table>	

<!-- 페이징 -->

			<div id="paging" class="page_paging MapMarg_T10"><div class="page_center">
				<ul class="paging_align">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_link_page" /></ul>
				</div>
			</div>

<!-- //페이징 -->




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