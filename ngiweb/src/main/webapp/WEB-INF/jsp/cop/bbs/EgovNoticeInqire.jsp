<%--
  Class Name : EgovNoticeInqire.jsp
  Description : 게시물 조회 화면
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2009.03.23   이삼섭          최초 생성
     2009.06.26   한성곤          2단계 기능 추가 (댓글관리, 만족도조사)
     2011.08.31  JJY       경량환경 버전 생성
 
    author   : 공통서비스 개발팀 이삼섭
    since    : 2009.03.23
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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

<script type="text/javascript" src="<c:url value='/js/EgovBBSMng.js' />"></script>
<c:if test="${anonymous == 'true'}"><c:set var="prefix" value="/anonymous"/></c:if>
<script type="text/javascript">
	function onloading() {
		if ("<c:out value='${msg}'/>" != "") {
			alert("<c:out value='${msg}'/>");
		}
	}
	
	function fn_egov_select_noticeList(pageNo) {
		document.frm.pageIndex.value = pageNo; 
		document.frm.action = "<c:url value='/cop/bbs${prefix}/selectBoardList.do'/>";
		document.frm.submit();	
	}
	
	function fn_egov_delete_notice() {
		if ("<c:out value='${anonymous}'/>" == "true" && document.frm.password.value == '') {
			alert('등록시 사용한 패스워드를 입력해 주세요.');
			document.frm.password.focus();
			return;
		}
		
		if (confirm('<spring:message code="common.delete.msg" />')) {
			document.frm.action = "<c:url value='/cop/bbs${prefix}/deleteBoardArticle.do'/>";
			document.frm.submit();
		}	
	}
	
	function fn_egov_moveUpdt_notice() {
		if ("<c:out value='${anonymous}'/>" == "true" && document.frm.password.value == '') {
			alert('등록시 사용한 패스워드를 입력해 주세요.');
			document.frm.password.focus();
			return;
		}

		document.frm.action = "<c:url value='/cop/bbs${prefix}/forUpdateBoardArticle.do'/>";
		document.frm.submit();			
	}
	
	function fn_egov_addReply() {
		document.frm.action = "<c:url value='/cop/bbs${prefix}/addReplyBoardArticle.do'/>";
		document.frm.submit();			
	}	
</script>
<!-- 2009.06.29 : 2단계 기능 추가  -->
<c:if test="${useComment == 'true'}">
<c:import url="/cop/bbs/selectCommentList.do" charEncoding="utf-8">
	<c:param name="type" value="head" />
</c:import>
</c:if>
<c:if test="${useSatisfaction == 'true'}">
<c:import url="/cop/bbs/selectSatisfactionList.do" charEncoding="utf-8">
	<c:param name="type" value="head" />
</c:import>
</c:if>
<c:if test="${useScrap == 'true'}">
<script type="text/javascript">
	function fn_egov_addScrap() {
		document.frm.action = "<c:url value='/cop/bbs/addScrap.do'/>";
		document.frm.submit();			
	}
</script>
</c:if>
<!-- 2009.06.29 : 2단계 기능 추가  -->
<title>국토변화정보포털서비스</title>

</head>
<body onload="onloading();">
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
            <!-- 현재위치 네비게이션 시작 -->
		    <div class="ConMTitle">
		        <!-- history -->
		        <div class="History">
		            <a href="#" class="his1">처음으로</a><a href="#" class="History1"></a> &gt;
		            <a href="#" class="History2">커뮤니티</a> &gt; <a href="#" class="History3">공지사항</a>
		        </div>
		        <!-- //history -->
		        <h3>공지사항</h3>         
		    </div>


			<form name="frm" method="post" action="<c:url value='/cop/bbs${prefix}/selectBoardList.do'/>">
			<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
			<input type="hidden" name="bbsId" value="<c:out value='${result.bbsId}'/>" >
			<input type="hidden" name="nttId" value="<c:out value='${result.nttId}'/>" >
			<input type="hidden" name="parnts" value="<c:out value='${result.parnts}'/>" >
			<input type="hidden" name="sortOrdr" value="<c:out value='${result.sortOrdr}'/>" >
			<input type="hidden" name="replyLc" value="<c:out value='${result.replyLc}'/>" >
			<input type="hidden" name="nttSj" value="<c:out value='${result.nttSj}'/>" >
			<input type="submit" id="invisible" class="invisible"/>
            
            <!--detail area start -->
            <div class="search_service">
                <div class="search_top_table">
                    <table class="tabwrite">
				      <tr> 
				        <th class="td_width">제목</th>
				        <td class="td_content" colspan="5"><c:out value="${result.nttSj}" />
				        </td>
				      </tr>
				      <tr> 
				        <th class="td_width">작성자</th>
				        <td class="td_width">
				            <c:out value="${result.frstRegisterNm}" />
				            <!-- 
					        <c:choose>
					            <c:when test="${anonymous == 'true'}">
					                ******
					            </c:when>
					            <c:when test="${result.ntcrNm == ''}">
					                <c:out value="${result.frstRegisterNm}" />
					            </c:when>
					            <c:otherwise>
					                <c:out value="${result.ntcrNm}" />
					            </c:otherwise>
					        </c:choose>
				         -->
				        </td>
				        </tr>
				        <tr>
				        <th class="td_width">작성일자</th>
				        <td class="td_width"><c:out value="${result.frstRegisterPnttm}" />
				        </td>

				      </tr>    
				      <tr> 
				        <th class="td_width">글내용</th>
				        <td class="td_width" colspan="5">
				        <textarea id="nttCn" name="nttCn" class="textarea" cols="75" rows="28" readonly="readonly" title="글내용"><c:out value="${result.nttCn}" escapeXml="false" /></textarea>
				        </td>
				      </tr>
				      <c:if test="${not empty result.atchFileId}">
				          <c:if test="${result.bbsAttrbCode == 'BBSA02'}">
				          <tr> 
				            <th class="td_width">첨부이미지</th>
				            <td class="td_content" colspan="5">
				                    <c:import url="/cmm/fms/selectImageFileInfs.do" charEncoding="utf-8">
				                        <c:param name="atchFileId" value="${result.atchFileId}" />
				                    </c:import>
				            </td>
				          </tr>
				          </c:if>
				          <tr> 
				            <th class="td_width">첨부파일<br/> 목록</th>
				            <td class="td_content" colspan="5">
				                <c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
				                    <c:param name="param_atchFileId" value="${result.atchFileId}" />
				                </c:import>
				            </td>
				          </tr>
				      </c:if>
				      <c:if test="${anonymous == 'true'}">
				      <tr> 
				        <th class="td_width"><label for="password"><spring:message code="cop.password" /></label></th>
				        <td class="td_content" colspan="5">
				            <input name="password" title="암호" type="password" size="20" value="" maxlength="20" >
				        </td>
				      </tr>
				      </c:if>   
				    </table>
                </div>
            </div>
            <!--detail area end -->
            
            <!-- 목록/저장버튼  시작-->
                <div class="Marg_T10">
			        <div id="BtnAreaSub">
			            <div id="StyleButtonDivSub">
			            	<button type="button" class="AXButtonSmall Blue" OnClick="javascript:fn_egov_select_noticeList('1');"><i class="fa fa-align-justify fa-lg"></i> 목록</button>
			          
			            </div>
			        </div>
			    </div>
            <!-- 목록/저장버튼  끝-->
            
            </form>

            </div><!-- contents end -->
        </div>
    <!-- 하단 유틸메뉴 -->
    <div class="MBotUtill"><c:import url="/EgovPageLink.do?link=main/inc/NgiIncUtil" /></div>
    <!-- //하단 유틸메뉴 -->


    <!-- 하단 배너 -->
    <div class="MBanner"><c:import url="/EgovPageLink.do?link=main/inc/NgiIncBanner" /></div>
    <!-- //하단 배너 -->        
        
    </div>
    <!-- footer 시작 -->
    <div id="footer"><c:import url="/EgovPageLink.do?link=main/inc/NgiIncFooter" /></div>
    <!-- //footer 끝 -->
</div>
<!-- //wrap end -->

</body>
</html>