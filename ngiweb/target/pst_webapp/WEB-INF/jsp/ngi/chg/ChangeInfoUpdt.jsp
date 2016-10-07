<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import ="egovframework.com.cmm.LoginVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : TnChangeInfoRegister.jsp
  * @Description : TnChangeInfo Register 화면
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
<%
//String sUserId = (String)session.getAttribute("sUserId");
String sUserId = "jbchoi";
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<c:set var="registerFlag" value="수정"/>

<title> <c:out value="${registerFlag}"/> </title>

<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngicommon.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngimap.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXJ.min.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXInput.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXSelect.css">
<link href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">

<script src="<c:url value='/'/>js/map/apikey.js"></script>
<script src="<c:url value='/'/>js/openlayers/OpenLayers.js"></script>
<script src="<c:url value='/'/>js/map/lib/proj4js-compressed.js"></script>
<script src="<c:url value='/'/>js/axisj/jquery/jquery.min.js"></script>
<script src="<c:url value='/'/>js/map/changeinforegister.js"></script>

<script type="text/javascript" src="<c:url value='/'/>js/EgovMultiFile.js" ></script>
<!--For Commons Validator Client Side
<script type="text/javascript" src="<c:url value='/cmmn/validator.do'/>"></script>
<validator:javascript formName="ChangeSttemntInfoVO" staticJavascript="false" xhtml="true" cdata="false"/>
-->
<script type="text/javascript">
<c:if test="${changeSttemntInfoVO.changeInfoId != 0}">
	$( document ).ready(function() {
		
		// 지도이동
		parent.setPointOfMove( <c:out value="${changeSttemntInfoVO.XCrdntLo}"/>,<c:out value="${changeSttemntInfoVO.YCrdntLa}"/>);

		// 팝업 생성,, 화면에 벡터 그림.
		var vectors = $('input[name="vectorList"]').val();
		if(vectors.length>0) {
			var list = vectors.split('|');
			for(var index=0;index<list.length;index++) {
				wkt = list[index];
				createNewPopup(new OpenLayers.Format.WKT().read(wkt), true);
			}
		}
		
	});
	
</c:if>
</script>
<script type="text/javascript">

/* 글 목록 화면 function */
function fn_egov_selectList() {
	parent.resetVectorList();	// 도형정보 초기화
   	document.getElementById("detailForm").action = "<c:url value='/ngi/chg/ChangeInfoList.do'/>";
   	document.getElementById("detailForm").submit();		
}

/* 글 삭제 function */
function fn_egov_delete() {
	parent.resetVectorList();	// 도형정보 초기화
   	document.getElementById("detailForm").action = "<c:url value='/ngi/chg/deleteChangeInfo.do'/>";
   	document.getElementById("detailForm").submit();		
}

/* 글 등록 취소 function */
function fn_egov_cancel() {
	parent.resetVectorList();	// 도형정보 초기화
	location.href = "<c:url value='/'/>ngi/chg/ChangeInfoList.do";
}

/* 글 등록 function */
function fn_egov_save() {	

	parent.saveVectorList('#iframeChgSttemntinfo');	// 화면 폼에 벡터정보 설정.
	parent.resetVectorList();	// 도형정보 초기화
	
	frm = document.getElementById("detailForm");

	/* TODO Validation기능 보완 */

	var pstr = document.detailForm.telno.value;
	
	pstr = pstr.replace(/n/g,"");
	pstr = pstr.replace(/\u/g,"");
	pstr = pstr.replace(/\l/g,"");
	pstr = pstr.replace(/\-/g,"");

	document.detailForm.telno.value = pstr;	
	
	
	var enctelno=Encrypt(document.detailForm.telno.value);
	var encemail=Encrypt(document.detailForm.email.value);
	
	var urltelno=encodeURL(enctelno);	
	var urlemail=encodeURL(encemail);
	
	document.detailForm.telno.value=urltelno;
	document.detailForm.email.value=urlemail;
	
	
    if (document.detailForm.changeSj.value =="") {
        alert("제목을 입력하세요");
    } else if (document.detailForm.sttemntTy.value =="") {
        alert("유형을 선택하세요");        
    } else if (document.detailForm.changeRnAdresCn.value =="") {
        alert("주소를 입력하세요");
    } else if (document.detailForm.sttemntCn.value =="") {
        alert("내용을 입력하세요");
    } else {	
    	
   	// 제목/신고 내용 의 특수문자 치환(from mutipart로 인해 filter 사용 x)
   	//var subject = htmlReplaceText(jQuery('[name="changeSj"]').val());
   	//jQuery('[name="changeSj"]').val(subject);	
   	
   	//var sttemntCn = htmlReplaceText(jQuery('[name="sttemntCn"]').val());
   	//jQuery('[name="sttemntCn"]').val(sttemntCn);	
	
  	frm.action = "<c:url value="${registerFlag == '등록' ? '/ngi/chg/addChangeInfo.do' : '/ngi/chg/updateChangeInfo.do'}"/>";
    frm.submit();
    }
}

//암호화
function Encrypt(theText){ 
            output = new String; 
            Temp = new Array(); 
            Temp2 = new Array(); 
            TextSize = theText.length; 
            
            for (i = 0; i < TextSize; i++) {  
                      rnd = Math.round(Math.random() * 122) + 68;  
                      Temp[i] = theText.charCodeAt(i) + rnd;  
                      Temp2[i] = rnd; 
            } 
            
            for (i = 0; i < TextSize; i++) {  
                      output += String.fromCharCode(Temp[i], Temp2[i]); 
            } 
            
            return output;
 }

//url 인코딩
function encodeURL(str){
     var s0, i, s, u;
     s0 = "";                // encoded str
     for (i = 0; i < str.length; i++){   // scan the source
         s = str.charAt(i);
         u = str.charCodeAt(i);          // get unicode of the char
         if (s == " "){s0 += "+";}       // SP should be converted to "+"
         else {
             if ( u == 0x2a || u == 0x2d || u == 0x2e || u == 0x5f || ((u >= 0x30) && (u <= 0x39)) || ((u >= 0x41) && (u <= 0x5a)) || ((u >= 0x61) && (u <= 0x7a))){       // check for escape
                 s0 = s0 + s;            // don't escape
             }
             else {                  // escape
                 if ((u >= 0x0) && (u <= 0x7f)){     // single byte format
                     s = "0"+u.toString(16);
                     s0 += "%"+ s.substr(s.length-2);
                 }
                 else if (u > 0x1fffff){     // quaternary byte format (extended)
                     s0 += "%" + (0xf0 + ((u & 0x1c0000) >> 18)).toString(16);
                     s0 += "%" + (0x80 + ((u & 0x3f000) >> 12)).toString(16);
                     s0 += "%" + (0x80 + ((u & 0xfc0) >> 6)).toString(16);
                     s0 += "%" + (0x80 + (u & 0x3f)).toString(16);
                 }
                 else if (u > 0x7ff){        // triple byte format
                     s0 += "%" + (0xe0 + ((u & 0xf000) >> 12)).toString(16);
                     s0 += "%" + (0x80 + ((u & 0xfc0) >> 6)).toString(16);
                     s0 += "%" + (0x80 + (u & 0x3f)).toString(16);
                 }
                 else {                      // double byte format
                     s0 += "%" + (0xc0 + ((u & 0x7c0) >> 6)).toString(16);
                     s0 += "%" + (0x80 + (u & 0x3f)).toString(16);
                 }
             }
         }
     }
     return s0;
 }

function htmlReplaceText(text){
	
	if(text != null || text !=""){
		text = text.replace(/&/g,"&amp;");
		text = text.replace(/</g,"&lt;");
		text = text.replace(/>/g,"&gt;");
		text = text.replace(/\\/g,"&quot;");
	}
	return text;
}


</script>
</head>
<body>

<form:form commandName="changeSttemntInfoVO" name="detailForm" id="detailForm"  enctype="multipart/form-data" >
<input type="hidden" name="changeInfoId" value="<c:out value='${changeSttemntInfoVO.changeInfoId}'/>"  />
<input type="hidden" name="registerId" value="<c:out value='${changeSttemntInfoVO.registerId}'/>" />
<input type="hidden" name="changeCl" value="01"  />
<input type="hidden" name="XCrdntLo" value="<c:out value='${changeSttemntInfoVO.XCrdntLo}'/>"  />
<input type="hidden" name="YCrdntLa" value="<c:out value='${changeSttemntInfoVO.YCrdntLa}'/>"  />
<input type="hidden" name="pnuCd" value="<c:out value='${changeSttemntInfoVO.pnuCd}'/>"  />
<input type="hidden" name="addrTy" value="<c:out value='${changeSttemntInfoVO.addrTy}'/>"  />
<input type="hidden" name="vectorList" value="<c:out value='${changeSttemntInfoVO.vectorList}'/>"  />

<input type="hidden" name="posblAtchFileNumber" value="<c:out value='${bdMstr.posblAtchFileNumber}'/>" />
<input type="hidden" name="posblAtchFileSize" value="<c:out value='${bdMstr.posblAtchFileSize}'/>" />

<c:choose>
<%-- <c:when test="${changeSttemntInfoVO.registerId eq sUserId}"> --%>
<c:when test="${1 eq 1}">
<div id="content_pop">
	<div id="table">
	<table align="center" cellpadding="3" cellspacing="3" class="tabSwrite">
		<colgroup>
			<col width="70"/>
			<col width=""/>
			<col width="70"/>
			<col width=""/>			
		</colgroup>
		<tr>
			<th>신고자명</th>
			<td colspan="3">
				<form:input path="registerNm" class="AXInput W100"/>
				&nbsp;<form:errors path="registerNm" />
			</td>
		</tr>	
		<tr>
			<th>전화번호</th>
			<td>
				<form:input path="telno" class="AXInput W100"/>
				&nbsp;<form:errors path="telno" />
			</td>
			
			<th>전자우편</th>
			<td>
				<form:input path="email" class="AXInput W100"/>
				&nbsp;<form:errors path="email" />
			</td>			
		</tr>	
		<tr>

		</tr>	
		</table>	
		<table align="center"cellpadding="3" cellspacing="3" class="tabSwrite">
		<colgroup>
			<col width="70"/>
			<col width=""/>
			<col width="70"/>
			<col width=""/>			
		</colgroup>				
		<tr>
			<th>신고제목</th>
			<td>
				<form:input path="changeSj" class="AXInput W100"/>
				&nbsp;<form:errors path="changeSj" />
			</td>
			<th>유형</th>
			<td>
	           <form:select path="sttemntTy" id="sttemntTy" class="AXSelectSmall">
                   <form:option value="">-선택-</form:option>
                   <form:options items="${sttemntty_result}" itemValue="code" itemLabel="codeNm"/>
               </form:select>			
			</td>			
		</tr>			

		<tr>
			<th>변동지 주소</th>
			<td colspan="3">
 				<form:input path="changeRnAdresCn" class="AXInput W300" />(도로명)
				&nbsp;<form:errors path="changeRnAdresCn" /><br /><br />
				<form:input path="changeLnmAdresCn" class="AXInput W300" />(지번)
				&nbsp;<form:errors path="changeLnmAdresCn" />	
				<c:if test="${changeSttemntInfoVO.changeInfoId != 0}" />
				<c:choose>
	            	<c:when test="${empty changeSttemntInfoVO.XCrdntLo}">
	            	<a href="#"><img src="<c:url value='/'/>images/map/mark_blue_off.gif" width="12" height="15" alt="위치표시" onClick="javascript:alert('위치정보가 없습니다.');"></a>
	            	</c:when>
	            	<c:otherwise>
	            	<a href="#"><img src="<c:url value='/'/>images/map/mark_blue.gif" width="12" height="15" alt="위치표시" onClick='javascript:parent.setPointOfMove(<c:out value="${changeSttemntInfoVO.XCrdntLo}"/>,<c:out value="${changeSttemntInfoVO.YCrdntLa}"/>)' ></a>
	            	</c:otherwise>
	            </c:choose>
				<br />

					<div id="BtnAreaMap">
						<div id="StyleButtonDivMap" style="padding-right:0pt;margin:2pt;">
						
							<a href="#" id="regAddrAddLine"><img src="<c:url value='/'/>images/map/mmenu_line.png" alt="선그리기" title="선그리기" ></a>
							<a href="#" id="regAddrAddPolygon"><img src="<c:url value='/'/>images/map/mmenu_side.png" alt="면그리기" title="면그리기" ></a>
							&nbsp;&nbsp;
							<a href="javascript:regAddrSearch();" id="regAddrSearch" class="BtnDiv"><span>주소검색</span></a>
							<a href="javascript:regAddrPoint();" id="regAddrPoint" class="BtnDiv"><span>위치입력</span></a>
							<div id="regAddrPoint_pop">지도에 위치를 클릭하면</br>주소가 입력됩니다.</div>
						</div>
					</div>
			</td>			
		</tr>	
		<!-- 
		<tr>
			<th>상세주소</th>
			<td colspan="3">
				<form:input path="changeRnAdresDetailCn" class="AXInput W200"/>
				&nbsp;<form:errors path="changeRnAdresDetailCn" />
			</td>
		</tr>	
		 -->
		</table>
		<table align="center" cellpadding="3" cellspacing="3" class="tabSwrite">
		<colgroup>
			<col width="70"/>
			<col width=""/>
		</colgroup>
		<tr>
			<th>신고내용</th>
			<td><textarea id="sttemntCn" name="sttemntCn" rows="5" cols="40"><c:out value="${changeSttemntInfoVO.sttemntCn}" escapeXml="false" /></textarea>

			</td>
		</tr>	
        <tr>
          <td class="td_width" ><label for="egovComFileUploader"><spring:message code="cop.atchFile" /></label></td>
          <td class="td_content" colspan="3">
                      <input name="file_1" id="egovComFileUploader" type="file" />
                          <div id="egovComFileList"></div>
                          
            <div id="file_upload_imposbl"  style="display:none;" >
                <spring:message code="common.imposbl.fileupload" />
            </div>
            <c:if test="${empty changeSttemntInfoVO.atchFileId}">
	            <input type="hidden" id="fileListCnt" name="fileListCnt" value="0" />
	        </c:if> 
          </td>
        </tr>
      <c:if test="${not empty changeSttemntInfoVO.atchFileId}">
          <tr> 
            <td class="td_width"><spring:message code="cop.atchFileList" /></td>
            <td class="td_content">
                <c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
                    <c:param name="param_atchFileId" value="${changeSttemntInfoVO.atchFileId}" />
                </c:import>
            </td>
          </tr>
      </c:if> 
	</table>
<c:if test="${empty changeSttemntInfoVO.atchFileId}">
    <script type="text/javascript">
        var maxFileNum = document.detailForm.posblAtchFileNumber.value;
        if(maxFileNum==null || maxFileNum==""){
            maxFileNum = 3;
        } 
        var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );
        multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );         
    </script>	
</c:if>    
<c:if test="${not empty changeSttemntInfoVO.atchFileId}">
      <script type="text/javascript">
        var existFileNum = document.detailForm.fileListCnt.value;        
        var maxFileNum = 3;

        if (existFileNum=="undefined" || existFileNum ==null) {
            existFileNum = 0;
        }
        if (maxFileNum=="undefined" || maxFileNum ==null) {
            maxFileNum = 0;
        }       
        var uploadableFileNum = maxFileNum - existFileNum;
        if (uploadableFileNum<0) {
            uploadableFileNum = 0;
        }               
        if (uploadableFileNum != 0) {
            fn_egov_check_file('Y');
            var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), uploadableFileNum );
            multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
        } else {
            fn_egov_check_file('N');
        }           
     </script>    
</c:if>
    
  </div>
	<div class="MapMarg_T30">
		<div id="BtnAreaMap">
			<div id="StyleButtonDivMap">
			<button type="button" class="AXButtonSmall Blue" OnClick="javascript:fn_egov_save();"><i class="fa fa-pencil fa-lg"></i> 완료</button>				
			<button type="button" class="AXButtonSmall Blue" OnClick="javascript:fn_egov_cancel();"><i class="fa fa-align-justify fa-lg"></i> 목록</button>
			</div>
		</div>
	</div>
</div>
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>

</c:when>
<c:otherwise>

<script type="text/javascript">

    alert('잘못된 접근입니다.');
    history.back();
    
</script>

</c:otherwise>
</c:choose>

</form:form>
</body>
</html>

