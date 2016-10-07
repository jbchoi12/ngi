<%--
  Class Name : EgovQnaDetailInqure.jsp
  Description : EgovQnaDetailInqure 화면
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

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_inqire_qnalist() {

	document.QnaManageForm.action = "<c:url value='/uss/olh/qna/QnaListInqire.do'/>";
	document.QnaManageForm.submit();
		
}

/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_egov_updt_qnacn(qaId){

	// Update하기 위한 키값을 셋팅
	document.QnaManageForm.qaId.value = qaId;	

	var url 	= "<c:url value='/uss/olh/qna/QnaPasswordConfirmView.do'/>";
	var	status 	= "dialogWidth=350px;dialogHeight=200px;resizable=no;center=yes";

	
	// 작성비밀번호 확인 화면을 호출한다.
	var returnValue = window.showModalDialog(url, self, status);

	// 결과값을 받아. 결과를 Submit한다.
 	if	(returnValue)	{
 		document.QnaManageForm.action = "<c:url value='/uss/olh/qna/QnaPasswordConfirm.do'/>"; 	 	
		document.QnaManageForm.submit();
 		
 	}
	
}

/**********************************************************
 * 삭제처리화면
 ******************************************************** */
function fn_egov_delete_qnacn(qaId){

	if	(confirm("<spring:message code="common.delete.msg" />"))	{	

		// Delete하기 위한 키값을 셋팅
		document.QnaManageForm.qaId.value = qaId;	
		
		var url 	= "<c:url value='/uss/olh/qna/QnaPasswordConfirmView.do'/>";
		var	status 	= "dialogWidth=350px;dialogHeight=200px;resizable=no;center=yes";

		
		// 작성비밀번호 확인 화면을 호출한다.
		var returnValue = window.showModalDialog(url, self, status);

		// 결과값을 받아. 결과를 Submit한다.
	 	if	(returnValue)	{		
			document.QnaManageForm.action = "<c:url value='/uss/olh/qna/QnaPasswordConfirm2.do'/>";
			document.QnaManageForm.submit();
	 	}
	}	
	
}

/*********************************************************
 * 작성비밀번호.체크..
 ******************************************************** */
function fn_egov_passwordConfirm(){

	alert("작성 비밀번호를 확인 바랍니다!");
	
}


</script>
</head>
 
<body>
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
		    <div class="ConMTitle">
		        <!-- history -->
		        <div class="History">
		            <a href="#" class="his1">처음으로</a><a href="#" class="History1"></a> &gt;
		            <a href="#" class="History2">커뮤니티</a> &gt; <a href="#" class="History3">Q&amp;A</a>
		        </div>
		        <!-- //history -->
		        <h3>Q&amp;A</h3>         
		    </div>
	<div class="ConMView">
		<!--contents start-->
            
			<!-- 상단타이틀 -->
			<form name="QnaManageForm" action="<c:url value='/uss/olh/qna/QnaPasswordConfirm.do'/>" method="post">
			<input name="qaId" type="hidden" value="<c:out value='${result.qaId}'/>">

           
            <!--detail area start -->
            <div class="search_service">
                <div class="search_top_table">                  
					<table summary="Q&amp;A에 대한 정보를 조회합니다." class="tabwrite">
					<caption>Q&amp;A상세조회</caption>  
					  <tr> 
					    <th class="td_width">작성자명&nbsp;&nbsp;</th>
					    <td class="td_content">
					        <c:out value="${result.wrterNm}"/>  
					    </td>
					  </tr>
					  <tr> 
					    <th class="td_width">작성일자&nbsp;&nbsp;</th>
					    <td class="td_content">
					        <c:out value='${fn:substring(result.writngDe, 0,4)}'/>-<c:out value='${fn:substring(result.writngDe, 4,6)}'/>-<c:out value='${fn:substring(result.writngDe, 6,8)}'/>  
					    </td>
					  </tr>
					
					  <tr> 
					    <th class="td_width">조회횟수&nbsp;&nbsp;</th>
					    <td class="td_content">
					        <c:out value="${result.inqireCo}"/>  
					    </td>
					  </tr>
					
					  <tr> 
					    <th class="td_width">질의응답처리상태&nbsp;&nbsp;</th>
					    <td class="td_content">
					        <c:out value="${result.qnaProcessSttusCodeNm}"/>  
					    </td>
					  </tr>
					
					  <tr> 
					    <th class="td_width">질문제목&nbsp;&nbsp;</th>
					    <td class="td_content">    
					      <c:out value="${result.qestnSj}"/>                 
					    </td>
					  </tr> 
					
					  <tr> 
					    <th class="td_width"><label for="qestnCn">질문내용</label>&nbsp;&nbsp;</th>
					    <td class="td_content">    
					      <textarea name="qestnCn" cols="75" rows="10"  readonly title="질문내용"><c:out value="${result.qestnCn}"/>
					      </textarea>                       
					    </td>
					  </tr> 
					
					<!-- 답변내용이 있을경우 Display... -->
					<c:if test="${result.qnaProcessSttusCode == '3'}">
					  <tr> 
					    <th class="td_width"><label for="answerCn">답변내용</label>&nbsp;&nbsp;</th>
					    <td class="td_content">    
					      <textarea name="answerCn" cols="75" rows="5"  readonly="readonly" title="답변내용"><c:out value="${result.answerCn}"/>
					      </textarea>                                        
					    </td>
					  </tr> 
					  <tr> 
                        <th class="td_width">답변일자&nbsp;&nbsp;</th>
                        <td class="td_content">
                            <c:if test="${result.answerDe != null}">
                                <c:out value='${fn:substring(result.answerDe, 0,4)}'/>-<c:out value='${fn:substring(result.answerDe, 4,6)}'/>-<c:out value='${fn:substring(result.answerDe, 6,8)}'/>                                                           
                            </c:if>
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
			            	<button type="button" class="AXButtonSmall Blue" onClick="javascript:fn_egov_updt_qnacn('<c:out value="${result.qaId}"/>');return false;"><i class="fa fa-pencil fa-lg"></i> 수정</button>
			            	<button type="button" class="AXButtonSmall Blue" onClick="javascript:fn_egov_delete_qnacn('<c:out value="${result.qaId}"/>');"><i class="fa fa-trash-o fa-lg"></i> 삭제</button>
			            	<button type="button" class="AXButtonSmall Blue" onClick="javascript:fn_egov_inqire_qnalist();"><i class="fa fa-align-justify fa-lg"></i> 목록</button>
			            </div>
			        </div>
			    </div>             
            
            <!-- 목록/저장버튼  끝-->
            
			<c:if test="${result.passwordConfirmAt == 'N,'}">
			  <tr> 
			  	<td class="lt_text3" colspan=10>  		
				<script type="text/javascript">
					fn_egov_passwordConfirm();
				</script>  		
			  	</td>
			  </tr>   	          				 			   
			</c:if>

			<input name="writngPssword" type="hidden" value="">
			<input name="passwordConfirmAt" type="hidden" value="">
			
			</form>

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