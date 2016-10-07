<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
 /**
  * @Class Name : ChangeCntrwkInqire.jsp
  * @Description : ChangeCntrwk Inqire 화면
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
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<c:set var="registerFlag" value="${0 eq changeCntrwkInfoVO.changeInfoId ? '등록' : '수정'}"/>

<title>엑셀일괄등록</title>

<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngicommon.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngimap.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXJ.min.css">
<link href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">

<script type="text/javascript" src="<c:url value='/'/>js/axisj/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<c:url value='/'/>js/axisj/dist/AXJ.min.js"></script>
<script type="text/javascript" src="<c:url value='/'/>js/EgovMultiFile.js" ></script>
<!--For Commons Validator Client Side-->
<validator:javascript formName="ChangeCntrwkInfoVO" staticJavascript="false" xhtml="true" cdata="false"/>
<style type="text/css">
.AXprogressLoadedText {display:none;}
.AXprogressTitle{width:140px !important;}
.AXCircleProgress .AXprogressTitle{color: #F0F0F0;}
.AXMask{opacity: 0.5;}
</style>
<script type="text/javascript">
var myProgress = new AXProgress();	
AXUtil.setCookie("AXISTHEME", 'bulldog');
var fnObj = {
	pageStart: function(){
		myProgress.setConfig({
			theme:"AXlineProgress",
			width:400, 
			top:100, 
			title:"AXProgress BAR",
			duration:100 
		});
	},
	progress: {
		start: function(){
			mask.open();
			myProgress.start(function(){
					if(this.isEnd){
						myProgress.close();
					 	mask.close();
						fnObj.progress.start();
					}else{
						myProgress.update();
					}
			}, 
			{
				totalCount:20,
				width:66,
				top:300,
				title:"처리중입니다",
				theme:"AXCircleProgress"
			});
		}
	}
};
jQuery(document).ready(fnObj.pageStart.delay(0.1));
window.focus();
</script>

<script type="text/javascript">

/* 글 등록 취소 function */
function fn_egov_cancel() {
	//window.opener.document.location.href = window.opener.document.URL;
	window.close();
}

/* 글 등록 function */
function fn_egov_save() {	

	frm = document.getElementById("detailForm");

	/* TODO Validation기능 보완 */

    if (document.detailForm.fileAttach.value =="") {
        alert("파일 추가를 해주세요.");
    } else {	
    	fnObj.progress.start();
	  	frm.action = "<c:url value="${registerFlag == '등록' ? '/ngi/chg/addChangeCntrwkExcelInfo.do' : '/ngi/chg/updateChangeCntrwkInfo.do'}"/>";
	    frm.submit();
    }
}


window.opener.document.location.href = window.opener.document.URL;

</script>

</head>
<body>

<form:form commandName="changeCntrwkInfoVO" name="detailForm" id="detailForm"  enctype="multipart/form-data" >
<input type="hidden" name="changeInfoId" value="<c:out value='${changeCntrwkInfoVO.changeInfoId}'/>"  />
<input type="hidden" name="registerId" value="<c:out value='${changeCntrwkInfoVO.registerId}'/>" />
<input type="hidden" name="changeCl" value="02"  />
<input type="hidden" name="registTmpCd" value="1"  />

<div id="content_pop">
	<div id="table">
		<table align="center" cellpadding="3" cellspacing="3" class="exInfo">
			<colgroup>
				<col width="170"/>
				<col width=""/>
			</colgroup>
<%--  			<tr>
				<th>신고자명</th>
				<td><c:out value="${loginVO.name}"/></td>
			</tr>   --%>
			<tr>
				<th>등록방법</th>
				<td><img src="<c:url value='/'/>images/03103_help.jpg" width="400" alt="일괄등록방법"></img></td>
			</tr>	
			<tr>
				<th>등록안내</th>
				<td>
					① 아래에 변동정보양식 샘플을 다운로드 받고 엑셀파일을 엽니다. <br/>
					② 하단의 시트선택 탭에서 '작성설명' 시트를 선택합니다. <br/>
					③ '작성설명' 시트 내용에 따라 '공사정보' 시트를 작성합니다. <br/>
					④ 입력 항목 중 선택 값은 풀다운목록에서 선택하여 입력합니다. <br/> 
				</td>
			</tr>	
			<tr>
				<th>엑셀파일</th>
				<td><a href="<c:url value='/'/>change_data_sample.xls"><img src="<c:url value='/'/>images/pop_btn_sample.gif" alt="엑셀 샘플파일 다운받기"></img></a>  - 엑셀파일로 공사정보를 일괄 추가하세요. </td>
			</tr>
			<tr>
				<th>파일추가</th>
				<td>
						<input type="file" name="fileAttach" value=""  />
						<a href="javascript:fn_egov_save();"><img src="<c:url value='/'/>images/btn_add01.gif" alt="추가하기" align="absmiddle"></a>
				</td>
			</tr>
			<tr>
				<th>유의사항</th>
				<td>
					- 셀 데이터의 항목 순서가 맞아야 합니다. </br>
					- 엑셀 샘플파일을 다운로드 받아서 참조하시기 바랍니다.
				</td>
			</tr>
		</table>	
    
	  </div>
		<div class="MapMarg_T10">
			<div id="BtnAreaMap">
				<div id="StyleButtonDivMap">
				<!-- <button type="button" class="AXButton Blue" OnClick="javascript:fn_egov_save();"><i class="fa fa-pencil fa-lg"></i> 완료</button>	 -->			
				<button type="button" class="AXButton Blue" OnClick="javascript:fn_egov_cancel();"><i class="fa fa-align-justify fa-lg"></i> 닫기</button>
				</div>
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

