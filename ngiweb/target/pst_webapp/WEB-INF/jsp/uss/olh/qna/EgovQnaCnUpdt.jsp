<%--
  Class Name : EgovQnaCnUpdt.jsp
  Description : EgovQnaCnUpdt 화면
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
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

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="qnaManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_initl_qnacn(){

	// 첫 입력란에 포커스..

	
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_updt_qnacn(form, qaId){

	if (!validateQnaManageVO(form)) {
			 			
		return;
			
	} else {

		form.qaId.value = qaId;
		form.action = "<c:url value='/uss/olh/qna/QnaCnUpdt.do'/>";
		form.submit();	
										
	}
			
}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_inqire_qnalist() {

	qnaManageVO.action = "<c:url value='/uss/olh/qna/QnaListInqire.do'/>";
	qnaManageVO.submit();
		
}


</script>
</head>
<body onLoad="fn_egov_initl_qnacn();">

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
        <li  class="side1_li"><a href="<c:url value='/'/>uss/olh/faq/FaqListInqire.do" class="side1_a">FAQ</a></li>
        <li  class="side1_li"><a href="<c:url value='/'/>uss/olh/qna/QnaListInqire.do" class="side1_aon">Q&amp;A</a></li>
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
            <a href="#" class="History2">커뮤니티</a> &gt; <a href="#" class="History3">QNA</a>
        </div>
        <!-- //history -->
        <h3>QNA</h3>         
    </div>
    <div class="ConMView">
            <!--contents start-->
  
  
            
			<!-- 상단타이틀 -->
			<form:form commandName="qnaManageVO" name="qnaManageVO" action="${pageContext.request.contextPath}/uss/olh/qna/QnaCnUpdt.do" method="post"> 	 
			<input name="qaId" type="hidden" value="<c:out value='${result.qaId}'/>">
			<input name="answerCn" type="hidden" value="Testing...">
            
            <!--detail area start -->
            <div class="search_service">
                <div class="search_top_table">                  
					<table summary="Q&amp;A에 대한 정보를 수정합니다." class="tabwrite">
					<caption>Q&amp;A내용수정</caption>
					  <tr> 
					    <th class="td_width"><label for="wrterNm">작성자명</label><img src="<c:url value='/'/>images/required.gif" width="15" height="15" alt="필수항목"></th>
					    <td class="td_content">
					        <form:input path="wrterNm" class="AXInput W100" maxlength="20" title="작성자명"/>
					        <div><form:errors path="wrterNm"/></div>                     
					    </td>
					  </tr>
					  
					  <tr> 
					    <th class="td_width"><label for="writngPassword">작성 비밀번호</label><img src="<c:url value='/'/>images/required.gif" width="15" height="15" alt="필수항목"></th>
					    <td class="td_content">
					        <input name="writngPassword" type="password" class="AXInput W100" value="<c:out value='${result.writngPassword}'/>"  maxlength="20" title="작성 비밀번호">                     
					    </td>
					  </tr>
					  
			
					  <tr> 
					    <th class="td_width"><label for="qestnSj">질문제목</label><img src="<c:url value='/'/>images/required.gif" width="15" height="15" alt="필수항목"></th>
					    <td class="td_content">    
					        <form:input path="qestnSj" class="AXInput W300" maxlength="70"  title="질문제목"/>
					        <div><form:errors path="qestnSj"/></div>                                                                                               
					    </td>
					  </tr> 
					
					  <tr> 
					    <th class="td_width"><label for="qestnCn">질문내용</label><img src="<c:url value='/'/>images/required.gif" width="15" height="15" alt="필수항목"></th>
					    <td class="td_content">    
					      <form:textarea path="qestnCn" cols="95" rows="20" cssClass="txaClass"  title="질문내용"/>   
					      <div><form:errors path="qestnCn"/></div>                                                                                                  
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
		                <button type="button" onclick="fn_egov_updt_qnacn(document.qnaManageVO,'<c:out value="${result.qaId}"/>');"  class="AXButtonSmall Blue" ><i class="fa fa-check-circle fa-lg"></i> 완료</button>
		                <button type="button" onclick="fn_egov_inqire_qnalist();"  class="AXButtonSmall Blue" ><i class="fa fa-align-justify fa-lg"></i> 목록</button>
            		</div>
            	</div>
            </div>
            <!-- 목록/저장버튼  끝-->
	  
            </form:form>

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