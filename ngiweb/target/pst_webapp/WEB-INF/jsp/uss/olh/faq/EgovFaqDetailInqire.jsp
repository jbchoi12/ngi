<%--
  Class Name : EgovFaqDetailInqure.jsp
  Description : EgovFaqDetailInqure 화면
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

<script type="text/javascript" src="<c:url value='/js/EgovMultiFile.js'/>" ></script>
<script type="text/javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_initl_faq(){



}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_inqire_faqlist() {

	document.FaqManageForm.action = "<c:url value='/uss/olh/faq/FaqListInqire.do'/>";
	document.FaqManageForm.submit();
		
}

/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_egov_updt_faq(faqId){

	// Update하기 위한 키값(faqId)을 셋팅
	document.FaqManageForm.faqId.value = faqId;	
	document.FaqManageForm.action = "<c:url value='/uss/olh/faq/FaqCnUpdtView.do'/>";
	document.FaqManageForm.submit();	
	
}


function fn_egov_delete_faq(faqId){

	if	(confirm("<spring:message code="common.delete.msg" />"))	{	

		// Delete하기 위한 키값(faqId)을 셋팅
		document.FaqManageForm.faqId.value = faqId;	
		document.FaqManageForm.action = "<c:url value='/uss/olh/faq/FaqCnDelete.do'/>";
		document.FaqManageForm.submit();
			
	}
	
	
}

</script>
</head>

<body onLoad="fn_egov_initl_faq();">

<!-- wrap start -->
<div id="wrap"> 
    <!-- header start -->
    <div id="header"><c:import url="/sym/mms/NgiMainMenuHead.do" /></div>  
    <!-- //header end -->

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
		    <div class="ConMTitle">
		        <!-- history -->
		        <div class="History">
		            <a href="#" class="his1">처음으로</a><a href="#" class="History1"></a> &gt;
		            <a href="#" class="History2">커뮤니티</a> &gt; <a href="#" class="History3">FAQ</a>
		        </div>
		        <!-- //history -->
		        <h3>FAQ</h3>         
		    </div>
            
            <form name="FaqManageForm" action="${pageContext.request.contextPath}/uss/olh/faq/FaqCnUpdtView.do" method="post">
            
            <!--detail area start -->
            <div class="search_service">
                <div class="search_top_table">                  
					<table class="tabwrite">
					<caption>FAQ상세조회</caption>
					  <tr> 
					    <th class="td_width">질문제목&nbsp;&nbsp;</th>
					    <td class="td_content">
					        <c:out value="${result.qestnSj}"/>  
					    </td>
					  </tr>
					
					  <tr> 
					    <th class="td_width"><label>질문내용</label>&nbsp;&nbsp;</th>
					    <td class="td_content">
					      <textarea name="qestnCn" class="textarea"  cols="80" rows="15"  readonly="readonly" title="질문내용"><c:out value="${result.qestnCn}"/>
					      </textarea>
					    </td>
					  </tr>
					  
					  <tr> 
					    <th class="td_width"><label>답변내용</label>&nbsp;&nbsp;</th>
					    <td class="td_content">
					      <textarea name="answerCn" class="textarea"  cols="80" rows="15" readonly="readonly" title="답변내용"><c:out value="${result.answerCn}"/>
					      </textarea>
					    </td>
					  </tr>
					
					  <tr> 
					    <th class="td_width">조회수</th>
					    <td class="td_content">
					        <c:out value="${result.inqireCo}"/>
					    </td>
					  </tr>
					  
					  <c:if test="${result.atchFileId != ''}">
					    <tr> 
					        <th class="td_width">첨부파일<br/>목록&nbsp;&nbsp;</th>
					            <td class="td_content">
					                <c:import charEncoding="utf-8" url="/cmm/fms/selectFileInfs.do" >
					                    <c:param name="param_atchFileId" value="${result.atchFileId}" />
					                </c:import>                             
					            </td>
					    </tr>
					  </c:if>      
					  
					  <tr> 
					    <th class="td_width">등록일자</th>
					    <td class="td_content">
					        <c:out value="${result.lastUpdusrPnttm}"/>
					    </td>
					  </tr> 
					   
					</table>
                    
                </div>
            </div>
            <!--detail area end -->
            
            <!-- 목록/저장버튼  시작-->
            
                <div class="Marg_T10">
			        <div id="BtnAreaSub">
			            <div id="StyleButtonDivSub">
			           		<button type="button" class="AXButtonSmall Blue" OnClick="javascript:fn_egov_inqire_faqlist();"><i class="fa fa-align-justify fa-lg"></i> 목록</button>
	
			            </div>
			        </div>
			    </div>            
            
            <!-- 목록/저장버튼  끝-->
            
			<input name="faqId" type="hidden" value="">
			
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