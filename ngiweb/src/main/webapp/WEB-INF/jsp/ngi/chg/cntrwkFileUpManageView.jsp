<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<title>파일업로드</title>

<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngicommon.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngimap.css">
<style type="text/css">
.uploadContainer{margin:20px;}
</style>

<script type="text/javascript" src="<c:url value='/'/>js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<c:url value='/'/>DEXTUploadNX/DEXTUploadNX.js"></script>
<script type="text/javascript">
<!--
/* 글 등록 취소 function */
function fn_egov_cancel() {
	window.close();
}
// -->
window.onload = function () {
			CreateDEXTUploadNX_Operator(
				{
					ContainerID: "dextuploadnx_container"
					, ElementID: "dextuploadnx_instance"

					//*/
					, Operator_Type: "UPLOAD_MANAGER"
					/*/
					, Operator_Type: "UPLOAD_MONITOR"
					//*/

					, CodePage: "65001"
					, ListViewNameColumnWidth: "0"
					, ListViewSizeColumnWidth: "110"
					, ListViewStatusColumnWidth: "100"
					, EnableUploadEmptyFolder: "TRUE"
					, ListViewShowOnlyTitle: "TRUE"

					, NXEventFunctions: {
						OnNXOperatorEventCreateComplete: function (varElementID, varOperatorType) {
							//NXAlert(varElementID, "OnNXOperatorEventCreateComplete");
							return true;
						}
						, OnNXOperatorEventError: function (varElementID, varOperatorType, varErrInfo) {
							NXAlert(varElementID, varElementID + " " + varErrInfo);
							return true;
						}
						, OnNXOperatorEventPrevAddItem: function (varElementID, varOperatorType, varFileCnt, varFolderCnt, varVirtualFileCnt) {
							return true;
						}
						, OnNXOperatorEventAddItem: function (varElementID, varOperatorType, varAddItemType, varItemIndex) {
							//NXAlert(varElementID, "OnNXOperatorEventAddItem(" + varElementID + ", " + varOperatorType + ", " + varAddItemType + ", " + varItemIndex + ")");
							return true;
						}
						, OnNXOperatorEventDeleteItem: function (varElementID, varOperatorType, varDeleteItemType, varItemIndex) {
							//NXAlert(varElementID, "OnNXOperatorEventDeleteItem(" + varElementID + ", " + varOperatorType + ", " + varDeleteItemType + ", " + varItemIndex + ")");
							return true;
						}  
						, OnNXOperatorEventUploadStartTotal: function (varElementID, varOperatorType) {
							//NXAlert(varElementID, "OnNXOperatorEventUploadStartTotal(" + varElementID + ", " + varOperatorType + ")");
							return false;
						}
						, OnNXOperatorEventUploadCompleteTotal: function (varElementID, varOperatorType) {
							//NXAlert(varElementID, "OnNXOperatorEventUploadCompleteTotal(" + varElementID + ", " + varOperatorType + ")");
							NXAlert(varElementID, "업로드가 완료되었습니다.");
							OnCallUploadClose();
							return true;
						}
						, OnNXOperatorEventUploadCancel: function (varElementID, varOperatorType) {
							//NXAlert(varElementID, "OnNXOperatorEventUploadCancel(" + varElementID + ", " + varOperatorType + ")");
							return true;
						}
						, OnNXOperatorEventUploadStartFile: function (varElementID, varOperatorType, varItemIndex) {
							//NXAlert(varElementID, "OnNXOperatorEventUploadStartFile(" + varElementID + ", " + varOperatorType + ", " + varItemIndex + ")");
							return true;
						}
						, OnNXOperatorEventUploadCompleteFile: function (varElementID, varOperatorType, varItemIndex) {
							//NXAlert(varElementID, "OnNXOperatorEventUploadCompleteFile(" + varElementID + ", " + varOperatorType + ", " + varItemIndex + ")");
							return true;
						}
					}
				}
			);
		}

		function OnCallEnableDragDrop(varEnable) {
			NXOperators["dextuploadnx_instance"].ListViewEnableDragDrop = varEnable;
		}

		function OnCallOpenFileDialog() {
			NXOperators["dextuploadnx_instance"].OpenFileDialog();
		}

		function OnCallOpenFolderDialog() {
			NXOperators["dextuploadnx_instance"].OpenFolderDialog();
		}

		function OnCallUploadStart() {
			var varMessage = " 전송하시겠습니까?";
			if (NXConfirm("dextuploadnx_instance", varMessage) == true) {
				NXOperators["dextuploadnx_instance"].UploadURL = _getNxURL("<c:url value='/'/>cntrwkFileUpManage_upload.up?changeInfoId=${changeInfoId}&fileType=${fileType}");
				NXOperators["dextuploadnx_instance"].UploadStart();
			} 	
		}

		function OnCallUploadStop() {
			NXOperators["dextuploadnx_instance"].UploadStop();
		}
		
		function OnCallDeleteFileItem() {
			for(var index=NXOperators["dextuploadnx_instance"].ItemCntFile-1;index>=0;index--) {
				if (NXOperators["dextuploadnx_instance"].IsSelectedItem(index) == true) {
					NXOperators["dextuploadnx_instance"].DeleteItem(index);
				}
			}
		}
		
		function OnCallUploadClose() {
			//window.opener.location.close();
			window.close();
		}
		window.focus();
</script>

</head>
<body>
	<div class="uploadContainer">
		<h1>${textTitle} 파일 업로드</h1>
		<div style="margin-top:8px;">
		</div>
		<div id="dextuploadnx_container" style="width:460px;height:200px;margin-top:8px;"></div>
		<p>&nbsp;</p>
		<p class="option" style="text-align:right;">
			<input class="btn_normal" type="button" value="파일추가" onclick="OnCallOpenFileDialog()" style="width: 90px; height: 30px;" />
			<input class="btn_normal" type="button" value="선택삭제" onclick="OnCallDeleteFileItem()" style="width: 90px; height: 30px;" />
			<input class="btn_normal" type="button" value="업로드 시작" onclick="OnCallUploadStart()" style="width: 90px; height: 30px;" />
			<input class="btn_normal" type="button" value="닫기" onclick="OnCallUploadClose()" style="width: 90px; height: 30px;" />
		</p>
	</div>
	
</body>
</html>


