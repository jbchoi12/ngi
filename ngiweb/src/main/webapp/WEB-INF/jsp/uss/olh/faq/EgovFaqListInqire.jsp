<%--
  Class Name : EgovFaqListInqire.jsp
  Description : EgovFaqListInqire 화면
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2009.02.01   박정규          최초 생성
     2011.08.31  JJY       경량환경 버전 생성
 
    author   : 공통서비스개발팀 박정규
    since    : 2009.02.01
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>국토변화정보포털서비스</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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

<script type="text/javascript">

/*********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_initl_faqlist(){

	// 첫 입력란에 포커스..
	document.FaqListForm.searchKeyword.focus();
	
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_select_linkPage(pageNo){
	
	document.FaqListForm.pageIndex.value = pageNo;
	document.FaqListForm.action = "<c:url value='/uss/olh/faq/FaqListInqire.do'/>";
   	document.FaqListForm.submit();
   	
}

/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_egov_search_faq(){

	document.FaqListForm.pageIndex.value = 1;
	document.FaqListForm.submit();
	
}

/*********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist_faq(){

	document.FaqListForm.action = "<c:url value='/uss/olh/faq/FaqCnRegistView.do'/>";
	document.FaqListForm.submit();	
	
}

/*********************************************************
 * 수정 처리 함수
 ******************************************************** */
function fn_egov_updt_faqlist(){

	document.FaqListForm.action = "<c:url value='/uss/olh/faq/FaqCnUpdtView.do'/>";
	document.FaqListForm.submit();	

}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_inquire_faqlistdetail(faqId) {		

	// Faqid
	document.FaqListForm.faqId.value = faqId;	
  	document.FaqListForm.action = "<c:url value='/uss/olh/faq/FaqInqireCoUpdt.do'/>";  	
  	document.FaqListForm.submit();	
	   	   		
}


</script>
</head>
<body onLoad="fn_egov_initl_faqlist();">

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
        <li  class="side1_li"><a href="<c:url value='/'/>cop/bbs/selectBoardList.do?bbsId=BBSMSTR_AAAAAAAAAAAA" class="side1_a">공지사항</a></li>
        <li  class="side1_li"><a href="<c:url value='/'/>uss/olh/faq/FaqListInqire.do" class="side1_aon">FAQ</a></li>
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
            <a href="#" class="History2">커뮤니티</a> &gt; <a href="#" class="History3">FAQ</a>
        </div>
        <!-- //history -->
        <h3>FAQ</h3>         
    </div>
            <div class="ConMView"><!--contents start-->
            
            <form name="FaqListForm" action="<c:url value='/uss/olh/faq/FaqListInqire.do'/>" method="post">
  
            

	<!-- 페이징 및 검색영역 -->
    <div class="remark">
        <div class="remark_right">
                    <select name="searchCondition"  class="AXSelectSmall" title="조회조건 선택">
			          <!-- <option selected value=''>선택하세요</option>  --> 
			           <option value="qestnSj"  <c:if test="${searchVO.searchCondition == 'qestnSj'}">selected="selected"</c:if> >질문제목</option>            
			       </select>
			       <input name="searchKeyword" type="text" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="35" title="검색어 입력"  class="AXInput W200" > 
            		<button type="button" onclick="fn_egov_search_faq();"  class="AXButtonSmall Blue" ><i class="fa fa-search fa-1"></i> 검색</button>

        </div>
    </div>
	<!-- //페이징 및 검색영역 -->            
            
            
            <input name="faqId" type="hidden" value="">
			<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
			

            <!-- search result start -->
            <div class="search_result_div">
            
                <table class="table_list">
                <caption>약관목록</caption>
                
                <colgroup>
                    <col style="width:15%;" > 
                    <col style="width:55%;" >
                    <col style="width:15%;" >
                    <col style="width:15%;" >
                </colgroup>
                
                <thead>
                <tr>      
                    <th>순번</th>        
				    <th>질문제목</th>                   
				    <th>조회수</th>        
				    <th>등록일자</th>               
                </tr>
                </thead>
                
                <tbody>
                <c:if test="${fn:length(resultList) == 0}">
                <tr> 
                      <td class="lt_text3" colspan="4">
                          <spring:message code="common.nodata.msg" />
                      </td>
                </tr>                                                 
                </c:if>
                <c:forEach items="${resultList}" var="resultInfo" varStatus="status">
                <tr>
			        <td class="lt_text3"><c:out value="${paginationInfo.totalRecordCount+1 - ((searchVO.pageIndex-1) * searchVO.pageSize + status.count)}"/></td>         
			        <td class="lt_text3" style="text-align:left;">
			            <a href="<c:url value='/uss/olh/faq/FaqInqireCoUpdt.do?faqId=${resultInfo.faqId}&amp;pageIndex=${searchVO.pageIndex}'/>" onclick="fn_egov_inquire_faqlistdetail('<c:url value='${resultInfo.faqId}'/>'); return false;">
			                <c:out value="${resultInfo.qestnSj}"/>
			            </a>
			        </td>       
			        <td class="lt_text3"><c:out value="${resultInfo.inqireCo}"/></td>       
			        <td class="lt_text3"><fmt:formatDate value="${resultInfo.lastUpdtPnttm}"  pattern="yyyy-MM-dd"/></td>         
			    </tr>   
                </c:forEach>
                </tbody>
                  
                </table>
            </div>
            <!-- search result end -->
  <!-- 페이징 -->

			<div  class="page_paging Marg_T20"><div class="page_center">
				<ul class="paging_align">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_linkPage" /></ul>
				</div>
			</div>
</form>
<!-- //페이징 -->          


           </div><!-- contents end -->
           </div>
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