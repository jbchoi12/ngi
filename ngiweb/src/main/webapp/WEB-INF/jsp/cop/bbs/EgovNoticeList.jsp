<%--
  Class Name :
  Description :
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2009.03.19   이삼섭          최초 생성
     2011.08.31  JJY       경량환경 버전 생성

    author   : 공통서비스 개발팀 이삼섭
    since    : 2009.03.19
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ImgUrl" value="/images/egovframework/cop/bbs/"/>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngicommon.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngimain.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngistyle.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngitable.css">

<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXJ.min.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXInput.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXSelect.css">
<link href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">

<script type="text/javascript" src="<c:url value='/'/>js/default.js"></script>
<script type="text/javascript" src="<c:url value='/'/>js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<c:url value='/'/>js/gnb_main.js"></script>

<c:if test="${anonymous == 'true'}"><c:set var="prefix" value="/anonymous"/></c:if>
<script type="text/javascript" src="<c:url value='/js/EgovBBSMng.js' />" ></script>
<c:choose>
<c:when test="${preview == 'true'}">
<script type="text/javascript">
<!--
	function press(event) {
	}

	function fn_egov_addNotice() {
	}

	function fn_egov_select_noticeList(pageNo) {
	}

	function fn_egov_inqire_notice(nttId, bbsId) {
	}
//-->
</script>
</c:when>
<c:otherwise>
<script type="text/javascript">
<!--
	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_noticeList('1');
		}
	}

	function fn_egov_addNotice() {
		document.frm.action = "<c:url value='/cop/bbs${prefix}/addBoardArticle.do'/>";
		document.frm.submit();
	}

	function fn_egov_select_noticeList(pageNo) {
		document.frm.pageIndex.value = pageNo;
		document.frm.action = "<c:url value='/cop/bbs${prefix}/selectBoardList.do'/>";
		document.frm.submit();
	}

	function fn_egov_inqire_notice(nttId, bbsId) {
		//document.subForm.nttId.value = nttId;
		//document.subForm.bbsId.value = bbsId;
		//document.subForm.action = "<c:url value='/cop/bbs${prefix}/selectBoardArticle.do'/>";
		//document.subForm.submit();
	}
//-->
</script>
</c:otherwise>
</c:choose>

<title>국토변화정보포털서비스</title>

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
    <h2>커뮤니티</h2>
        <ul class="side1_ul">
        <li  class="side1_li"><a href="<c:url value='/'/>cop/bbs/selectBoardList.do?bbsId=BBSMSTR_AAAAAAAAAAAA" class="side1_aon">공지사항</a></li>
        <li  class="side1_li"><a href="<c:url value='/'/>uss/olh/faq/FaqListInqire.do" class="side1_a">FAQ</a></li>
        <li  class="side1_li"><a href="<c:url value='/'/>uss/olh/qna/QnaListInqire.do" class="side1_a">Q&amp;A</a></li>
        </ul>
		<c:import url="/EgovPageLink.do?link=main/inc/NgiIncFBtn" />
    </div>
	<!-- //lnb -->


<div id="Content">
    <div class="ConMTitle">
        <!-- history -->
        <div class="History">
            <a href="#" class="his1">처음으로</a><a href="#" class="History1"></a> &gt;
            <a href="#" class="History2">커뮤니티</a> &gt; <a href="#" class="History3">공지사항</a>
        </div>
        <!-- //history -->
        <h3>공지사항</h3>         
    </div>


	<div class="ConMView">
	<form name="frm" action ="<c:url value='/cop/bbs${prefix}/selectBoardList.do'/>" method="post">
	<input type="hidden" name="bbsId" value="<c:out value='${boardVO.bbsId}'/>" />
	<input type="hidden" name="nttId"  value="0" />
	<input type="hidden" name="bbsTyCode" value="<c:out value='${brdMstrVO.bbsTyCode}'/>" />
	<input type="hidden" name="bbsAttrbCode" value="<c:out value='${brdMstrVO.bbsAttrbCode}'/>" />
	<input type="hidden" name="authFlag" value="<c:out value='${brdMstrVO.authFlag}'/>" />
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
  
	<!-- 페이징 및 검색영역 -->
    <div class="remark">
        <div class="remark_right">
            <select name="searchCnd" class="AXSelectSmall"  title="검색조건 선택">
		           <option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> >제목</option>
		           <option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> >내용</option>
		           <option value="2" <c:if test="${searchVO.searchCnd == '2'}">selected="selected"</c:if> >작성자</option>
		    </select>
            <input name="searchWrd" type="text"  class="AXInput W200"  value='<c:out value="${searchVO.searchWrd}"/>' maxlength="35" onkeypress="press(event);" title="검색어 입력">
            <button type="button" onclick="fn_egov_select_noticeList('1');"  class="AXButtonSmall Blue" ><i class="fa fa-search fa-1"></i> 검색</button>
        </div>
    </div>

	<!-- //페이징 및 검색영역 -->
    
	<!-- 테이블 -->
	<table class="table_list">
		<caption>공지사항</caption>
                <colgroup>
                    <col style="width:10%;">
                    <col >
                    <c:if test="${brdMstrVO.bbsAttrbCode == 'BBSA01'}">
                      <col style="width:15%;">
                      <col style="width:15%;">
                    </c:if>
                    <c:if test="${anonymous != 'true'}">
                      <col style="width:10%;">
                    </c:if>
                    <col style="width:15%;">
                    <col style="width:10%;">
                </colgroup>
        <thead>
                <tr>
                    <th>번호</th>
				    <th>제목</th>
				    <c:if test="${brdMstrVO.bbsAttrbCode == 'BBSA01'}">
				        <th>게시시작일</th>
				        <th>게시종료일</th>
				    </c:if>
				    <c:if test="${anonymous != 'true'}">
				        <th>작성자</th>
				    </c:if>
				    <th>작성일</th>
				    <th>조회수</th>
                </tr>        
        </thead>
        <tbody>
        
				<c:if test="${fn:length(resultList) == 0}">
			        <tr>
			        <c:choose>
			            <c:when test="${brdMstrVO.bbsAttrbCode == 'BBSA01'}">
			                <td colspan="7" ><spring:message code="common.nodata.msg" /></td>
			            </c:when>
			            <c:otherwise>
			                <c:choose>
			                    <c:when test="${anonymous == 'true'}">
			                        <td colspan="4" ><spring:message code="common.nodata.msg" /></td>
			                    </c:when>
			                    <c:otherwise>
			                        <td colspan="5" ><spring:message code="common.nodata.msg" /></td>
			                    </c:otherwise>
			                </c:choose>
			            </c:otherwise>
			          </c:choose>
			          </tr>
			     </c:if>        
                 <c:forEach var="result" items="${resultList}" varStatus="status">
			     <tr>
			        <!--td class="lt_text3" nowrap="nowrap"><input type="checkbox" name="check1" class="check2"></td-->
			        <td ><c:out value="${paginationInfo.totalRecordCount+1 - ((searchVO.pageIndex-1) * searchVO.pageSize + status.count)}"/></td>
			        <td style="text-align:left;">

			            <c:if test="${result.replyLc!=0}">
			                <c:forEach begin="0" end="${result.replyLc}" step="1">
			                    &nbsp;
			                </c:forEach>
			                <img src="<c:url value='/images/reply_arrow.gif'/>" alt="reply arrow">
			            </c:if>

			                     <a href="selectBoardArticle.do?nttId=<c:out value="${result.nttId}"/>&bbsId=<c:out value='${result.bbsId}'/>"><c:out value="${result.nttSj}"/></a> 


			        </td>
			        <c:if test="${brdMstrVO.bbsAttrbCode == 'BBSA01'}">
			            <td ><c:out value="${result.ntceBgnde}"/></td>
			            <td ><c:out value="${result.ntceEndde}"/></td>
			        </c:if>
			        <c:if test="${anonymous != 'true'}">
			            <td ><c:out value="${result.frstRegisterNm}"/></td>
			        </c:if>
			        <td ><c:out value="${result.frstRegisterPnttm}"/></td>
			        <td ><c:out value="${result.inqireCo}"/></td>
			     </tr>
			     </c:forEach>        

          </tbody>
	</table>
	<!-- //테이블 -->
  
<!-- 페이징 -->

			<div id="paging" class="page_paging MapMarg_T10"><div class="page_center">
				<ul class="paging_align">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_noticeList" /></ul>
				</div>
			</div>
    </form>
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