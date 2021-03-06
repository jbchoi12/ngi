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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

/* 글 검색 화면 function */
function fn_egov_search() {
	//document.frm.chgDe.value=document.frm.chgDe.value.replace('-', '');
	//document.frm.chgBgnde.value=document.frm.chgBgnde.value.replace('-', '');
	//document.frm.chgEndde.value=document.frm.chgEndde.value.replace('-', '');
	document.frm.action = "<c:url value='/ngi/trs/NgiTrsDownList.do'/>";
	document.frm.submit();		
}

function fn_egov_link_page(pageNo){
	document.frm.pageIndex.value = pageNo;
	document.frm.action = "<c:url value='/ngi/trs/NgiTrsDownList.do'/>";
	document.frm.submit();
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
    <h2>연계서비스</h2>
        <ul class="side1_ul">
        <li  class="side1_li"><a href="<c:url value='/'/>ngi/api/NgiApiIntroduce.do" class="side1_aon">Open API</a>
            <div class="side2_div">
                <ul class="side2_ul">
                <li class="side2_li"><a href="<c:url value='/'/>ngi/api/NgiApiIntroduce.do" class="side2_a">Open API 소개</a></li>
                <li class="side2_li"><a href="<c:url value='/'/>ngi/api/tnCntcInfoView.do" class="side2_a">Open API 발급/관리</a></li>
                <li class="side2_li"><a href="<c:url value='/'/>ngi/api/NgiApiReperence.do" class="side2_a">Open API 레퍼런스</a></li>
                <li class="side2_li"><a href="<c:url value='/'/>ngi/api/NgiApiExample.do" class="side2_a">Open API 예제</a></li>
                </ul>
            </div>
        </li>
        <li  class="side1_li"><a href="<c:url value='/'/>" class="side1_a">세움터/새주소</a>
            <div class="side2_div">
                <ul class="side2_ul">
                <li class="side2_li"><a href="<c:url value='/'/>ngi/trs/NgiTrsInfo.do" class="side2_a">세움터/새주소 소개</a></li>
                <li class="side2_li"><a href="<c:url value='/'/>ngi/trs/NgiTrsDownList.do" class="side2_aon">세움터/새주소 변화정보</a></li>
                </ul>
            </div>        
        </li>
        </ul>
		<c:import url="/EgovPageLink.do?link=main/inc/NgiIncFBtn" />
    </div>
	<!-- //lnb --> 

<div id="Content">
    <div class="ConMTitle">
        <!-- history -->
        <div class="History">
            <a href="#" class="his1">처음으로</a><a href="#" class="History1"></a> &gt;
            <a href="#" class="History2">연계서비스</a> &gt; <a href="#" class="History3">세움터/새주소</a>&gt; <a href="#" class="History3">세움터/새주소 변화정보</a>
        </div>
        <!-- //history -->
        <h3>세움터/새주소 변화정보</h3>         
    </div>


	<div class="ConMView">
	
	
 <form:form commandName="searchVO" name="frm" action ="<c:url value='/ngi/trs/NgiTrsDownList.do'/>" method="post">
 <form name="pageIndex" type="hidden" value="<c:out value="${searchVO.pageIndex}"/>"/>
    <div class="remark">
        <div class="remark_right">
	        <label for="searchCondition">선택</label>
	        <form:select path="searchCondition" name="searchCondition" id="searchCondition" class="AXSelectSmall">
                   <form:option value="00">세움터</form:option>
                   <form:option value="01">새주소</form:option>
		    </form:select>        
             <button type="button" class="AXButtonSmall Blue" onClick="javascript:fn_egov_search();"><i class="fa fa-search fa-1"></i> 검색</button>&nbsp;
        </div>
    </div>
 </form:form>

	<!-- List -->
	<table class="table_list">
		<caption>테이블 목록</caption>
        <colgroup>
            <col style="width:25%;">
            <col style="width:15%;">
            <col style="width:30%;">
            <col style="width:35%;">
        </colgroup>
        <thead>
          <tr>
            <th scope="col">변경일</th>
            <th scope="col">유형</th>
            <th scope="col">파일명</th>
            <th scope="col">다운로드</th>
            </tr>
        </thead>
        <tbody>	
        <c:forEach var="result" items="${resultList}" varStatus="status">
          <tr>
            <td><c:out value="${result.fileModDate}"/></td>
            <td><c:choose><c:when test="${result.typeDesc eq 'eais' }">세움터</c:when><c:otherwise>새주소</c:otherwise> </c:choose>     </td>
            <td><c:out value="${result.fileName}"/></td>
            <td><a href="<c:url value="/ngi/trs/NgiTrsDown.do"/>?type=<c:out value="${result.typeDesc}"/>&name=<c:out value="${result.fileName}"/>"><img src="<c:url value='/'/>images/btn/btn_addfile.jpg" alt="파일"></a></td>
           </tr>
         </c:forEach>
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