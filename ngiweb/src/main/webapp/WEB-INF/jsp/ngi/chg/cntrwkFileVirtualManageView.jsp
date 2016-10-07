<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import ="egovframework.com.cmm.LoginVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="isYours" value="false" />
<%
String sUserId = (String)session.getAttribute("sUserId");
%>
<c:if test="${sUserId eq registerId}"> <c:set var="isYours" value="true" /> </c:if>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>첨부파일 다운로드</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngicommon.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngimap.css">
<style type="text/css">
.uploadContainer{margin:20px;}
.option_l {margin-bottom: 6px;width: 449px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;background: #F0F0F0;font-size: 1.1em;top: 11px;padding: 5px;color: #281E1E;}
</style>
<script type="text/javascript" src="<c:url value='/'/>js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<c:url value='/'/>DEXTUploadNX/DEXTUploadNX.js"></script>
<script type="text/javascript">
window.onload = function () {
	
	CreateDEXTUploadNX_Operator(
		{
			ContainerID: "dextuploadnx_container"
			, ElementID: "dextuploadnx_instance"

			//*/
			, Operator_Type: "DOWNLOAD_MANAGER"
			/*/
			, Operator_Type: "DOWNLOAD_MONITOR"
			//*/

			, ListViewNameColumnWidth: "0"
			, ListViewSizeColumnWidth: "110"
			, ListViewStatusColumnWidth: "100"
			, FolderDialogTitle: "다운로드 경로 선택"			// 다운로드 경로 선택 폴더 다이얼로그 타이틀 설정
			, ListViewShowOnlyTitle: "TRUE"
			, UseClientRegistry: "TRUE"							// 설정값 저장하기 옵션 설정

			, NXEventFunctions: {
				OnNXOperatorEventCreateComplete: function (varElementID, varOperatorType) {
					//NXAlert(varElementID, varElementID + " " + "OnNXOperatorEventCreateComplete");
					document.getElementById("downloadpath").innerHTML = NXOperators[varElementID].DownloadPath;
					<c:forEach items="${fileInfoList}" var="data"  varStatus="status"><c:choose><c:when test="${status.last eq true}">
							NXOperators[varElementID].AddDownloadItemFile(_getNxURL("<c:url value='/'/>behind.down?atchmnflId=${data.atchmnflId}&changeInfoId=${data.changeInfoId}"), ${data.fileMg}, "${data.fileNm}", "", true);
					</c:when><c:otherwise>
							NXOperators[varElementID].AddDownloadItemFile(_getNxURL("<c:url value='/'/>behind.down?atchmnflId=${data.atchmnflId}&changeInfoId=${data.changeInfoId}"), ${data.fileMg}, "${data.fileNm}", "", false);
					</c:otherwise></c:choose></c:forEach>
				
					return true;
				}
				, OnNXOperatorEventError: function (varElementID, varOperatorType, varErrInfo) {
					NXAlert(varElementID, varErrInfo);
					return true;
				}
				, OnNXOperatorEventDownloadCompleteTotal: function (varElementID, varOperatorType) {
					NXAlert(varElementID, "다운로드 완료 하였습니다.");
					OnCallUploadClose();
					return true;
				}
				, OnNXOperatorEventItemDbClick: function (varElementID, varOperatorType, varItemType, varItemIndex) {
                   // console.log("OnNXOperatorEventItemDbClick(" + varElementID + ", " + varOperatorType + ", " + varItemType + ", " + varItemIndex + ")");
                    var vItem = NXOperators["dextuploadnx_instance"].Item(varItemIndex);
                  //  console.log(vItem.FileTitle);
                    <c:out value="${fileInfoList[varItemIndex]}" />         
                    return true;
                }
			}
		}
	);
	
	var str = NXOperators["dextuploadnx_instance"].DownloadPath;
	NXOperators["dextuploadnx_instance"].DownloadPath = str.replace("\\DEXTUploadNX", "");
	
}


function OnCallSetDownloadPathDialog() {
	NXOperators["dextuploadnx_instance"].SetDownloadPathDialog();
	document.getElementById("downloadpath").innerHTML = NXOperators["dextuploadnx_instance"].DownloadPath;
}

function OnCallDownloadStart() {
	NXOperators["dextuploadnx_instance"].DownloadStart();
}

function OnCallOpenFileAdd() {
	window.open("" ,"fileup_blank", "toolbar=no,width=500,height=320,directories=no,status=no,scrollbars=no,resizable=no");
	frm = document.fileUp;
	frm.method = "post";
	frm.target = "fileup_blank";
	frm.action = "<c:url value='/ngi/chg/cntrwkFileUpManageView.do'/>";
    frm.submit();
    window.close();
}

function OnCallUploadClose() {
	window.close();
}

function OnCallDeleteFile() {
	
	if( NXOperators["dextuploadnx_instance"].SelectedCount == 0 ) {
		NXAlert("dextuploadnx_instance", "선택된 파일이 없습니다.");
	} else {
		varMessage = "선택파일을 서버에서 삭제합니까?";
		if (NXConfirm("dextuploadnx_instance", varMessage) == true) {
			var delURL = _getNxURL("<c:url value='/'/>cntrwkFileUpManage_delete.down");
			var mergeParam = "";
			for(var ItemIndex=NXOperators["dextuploadnx_instance"].Count-1;ItemIndex>=0;ItemIndex--) {
				if( NXOperators["dextuploadnx_instance"].IsSelectedItem(ItemIndex) == true ) {
					NXOperators["dextuploadnx_instance"].DeleteItem(ItemIndex);
				    $.ajax({
				    	  type: "POST",
				    	  url: delURL,
				    	  dataType: "html",
				    	  contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				    	  data: { 
				    		  "changeInfoId" : ${changeInfoId}
				    		  ,"fileType" : "${fileType}" 
				    		  ,"atchmnflId": fileInfo[ItemIndex].atchmnflId
				    		  ,"fileName" :  fileInfo[ItemIndex].fileNm
				    	  }
			    	});
				}
			}
		} else {
			return false;
		}
	}
	// location.reload();
}
var fileInfo = [ <c:forEach items="${fileInfoList}" var="data"  varStatus="status"><c:choose><c:when test="${status.last eq true}">
	{
		"changeInfoId" : ${data.changeInfoId},
		"atchmnflId" : ${data.atchmnflId},
		"fileMg" : ${data.fileMg},
		"fileNm" : "${data.fileNm}"
	} </c:when><c:otherwise>
	{
		"changeInfoId" : ${data.changeInfoId},
		"atchmnflId" : ${data.atchmnflId},
		"fileMg" : ${data.fileMg},
		"fileNm" : "${data.fileNm}"
	}, </c:otherwise></c:choose></c:forEach>
];
window.focus();
</script>
</head>
<body>
	<div class="uploadContainer">
		<h1>${textTitle} 파일 다운로드</h1>
		<div style="margin-top:8px;">
		</div>
		<div id="dextuploadnx_container" style="width:460px;height:200px;margin-top:8px;"></div>
		<p>&nbsp;</p>
		<p class="option_l">
			<input class="btn_normal" type="button" value="저장 위치" onclick="OnCallSetDownloadPathDialog()" style="width: 90px; height:30px;" />
			<label class="label_normal" id="downloadpath"></label>
		</p>
		<p class="option" style="text-align:right;padding: 5px;">
			<c:if test="${isYours eq true}">
			<input class="btn_normal" type="button" value="업로드" onclick="OnCallOpenFileAdd()" style="width: 90px; height: 30px;float: left;" />
			<input class="btn_normal" type="button" value="선택삭제" onclick="OnCallDeleteFile()" style="width: 90px; height: 30px;" />
			</c:if>
			<input class="btn_normal" type="button" value="다운로드" onclick="OnCallDownloadStart()" style="width: 90px; height: 30px;" />
			<input class="btn_normal" type="button" value="닫기" onclick="OnCallUploadClose()" style="width: 90px; height: 30px;" />
		</p>
	</div>
	<form name="fileUp" enctype="application/x-www-form-urlencoded"><input type="hidden" name="changeInfoId" value="${changeInfoId}" /><input type="hidden" name="fileType" value="${fileType}" /></form>
</body>
</html>
