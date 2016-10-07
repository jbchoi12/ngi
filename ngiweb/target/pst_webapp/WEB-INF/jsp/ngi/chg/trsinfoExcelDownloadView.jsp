<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import ="egovframework.com.cmm.LoginVO" %>
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
<title>엑셀파일 다운로드</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngicommon.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngimap.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXJ.min.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXInput.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXSelect.css">
<link href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<style type="text/css">
.uploadContainer{margin:20px;}
.option_l {margin-bottom: 6px;width: 449px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;background: #F0F0F0;font-size: 1.1em;top: 11px;padding: 5px;color: #281E1E;}
.doBtn{text-align: center;margin: 23px;}
.contt{margin-top:8px;overflow: auto; width:1059px;height: 220px;}
</style>
<script type="text/javascript" src="<c:url value='/'/>js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
function exdown() {
	url = "<c:url value='/ngi/chg/trsinfoFileDownloadDone.do'/>";
//	window.open(url ,"file_blank_ex_down", "toolbar=no,width=500,height=367,directories=no,status=no,scrollbars=no,resizable=no");
	
	var form = document.createElement("form");
    form.setAttribute("method", "get");
    form.setAttribute("action", url);
 //   form.setAttribute("target", "file_blank_ex_down" );
    var input = document.createElement("input");
    input.type = "hidden";
    input.name = "idNums";
    input.value = '<c:url value="${idNums}" />';
    form.appendChild(input);
    document.body.appendChild(form);
    input = document.createElement("input");
    input.type = "hidden";
    input.name = "fileURL";
    input.value = "trsinfo_data_sample.xls";
    form.appendChild(input);
    document.body.appendChild(form);
    form.submit();
    document.body.removeChild(form);
    
    //window.close();	
}
</script>
</head>
<body>
	<div class="uploadContainer">
		<h1>엑셀파일 다운로드</h1>
		<div class="contt">
		<table align="center" cellpadding="3" cellspacing="3" class="tabSwrite5">
		<tr>
			<th rowspan="2">제목</th>
			<th rowspan="2">사업지구</th>
			<th rowspan="2">용역사</th>
			<th rowspan="2">등록자</th>
			<th rowspan="2">상태</th>
			<th colspan="3">1:5000 구조화</th>
			<th colspan="3">1:5000 정위치</th>
			<th colspan="3">1:25,000 정위치</th>
			<th rowspan="2">위치시점</th>
			<th rowspan="2">위치종점</th>
			<th rowspan="2">유형</th>
			<th rowspan="2">제원</th>
			<th rowspan="2">좌표계</th>
			<th rowspan="2">타원체</th>
			<th rowspan="2">투영법</th>
			<th rowspan="2">원점</th>
			<th rowspan="2">촬영년도</th>
			<th rowspan="2">조사년도</th>
			<th rowspan="2">수정년월</th>
<!-- 			<th rowspan="2">보완지시</th> -->
		</tr>
		<tr>
			<th>축척</th>
			<th>도엽</th>
			<th>도엽번호</th>
			<th>축척</th>
			<th>도엽</th>
			<th>도엽번호</th>
			<th>축척</th>
			<th>도엽</th>
			<th>도엽번호</th>
		</tr>
		<!-- 데이터. -->
	<c:forEach var="data" items="${trsInfoList}" varStatus="status">
		<tr>
			<td><c:out value="${data.updateOperationVO.opertNm}" /></td>
			<td><c:out value="${data.updateOperationVO.bsnsDstrc}" /></td>
			<td><c:out value="${data.updateOperationVO.servcExcprofsCode}" /></td>
			<td><c:out value="${data.updateOperationVO.updusr}" /></td>
			<td><c:out value="${data.updateOperationVO.sttus}" /></td>
			<td>1:5,000</td>
			<td><c:out value="${data.updateOperationVO.mapdmcNmA}" /></td>
			<td><c:out value="${data.updateOperationVO.mapdmcA}" /></td>
			<td>1:5,000</td>
			<td><c:out value="${data.updateOperationVO.mapdmcNmB}" /></td>
			<td><c:out value="${data.updateOperationVO.mapdmcB}" /></td>
			<td>1:25,000</td>
			<td><c:out value="${data.updateOperationVO.mapdmcNmC}" /></td>
			<td><c:out value="${data.updateOperationVO.mapdmcC}" /></td>
			<td><c:out value="${data.updateOperationVO.cntrwkPnttm}" /></td>
			<td><c:out value="${data.updateOperationVO.cntrwkTmnl}" /></td>
			<td><c:out value="${data.updateOperationVO.changeTy}" /></td>
			<td><c:out value="${data.updateOperationVO.manp}" /><c:out value="${data.updateOperationVO.unit}" /></td>
			<td><c:out value="${data.updateOperationVO.cntm}" /></td>
			<td><c:out value="${data.updateOperationVO.oval}" /></td>
			<td><c:out value="${data.updateOperationVO.trnsprclaw}" /></td>
			<td><c:out value="${data.updateOperationVO.trgnpt}" /></td>
			<td><c:out value="${data.updateOperationVO.phtogrfYear}" /></td>
			<td><c:out value="${data.updateOperationVO.examinYear}" /></td>
			<td><c:out value="${data.updateOperationVO.updtYear}" />-<c:out value="${data.updateOperationVO.updtMt}" /></td>
		<%-- 		
			<td><c:out value="${data.updateOperationVO.mapdmcD}" />bb</td>
			<td><c:out value="${data.updateOperationVO.mesrDe}" />cc</td>
			<td><c:out value="${data.updateOperationVO.mapdmcVO}" />dd</td>
			<td><c:out value="${data.updateOperationVO.tpgrphNtfcId}" /></td>
			<td><c:out value="${data.updateOperationVO.updateOperationFileList}" /></td>
			<td><c:out value="${data.updateOperationVO.dextuploadnx_instance1_path}" /></td>
			<td><c:out value="${data.updateOperationVO.dextuploadnx_instance2_path}" /></td>
			<td><c:out value="${data.updateOperationVO.dextuploadnx_instance3_path}" /></td>
			<td><c:out value="${data.updateOperationVO.dextuploadnx_instance4_path}" /></td>
			<td><c:out value="${data.updateOperationVO.fileId}" /></td>
			<td><c:out value="${data.updateOperationVO.fileOrgNm}" /></td>
			<td><c:out value="${data.updateOperationVO.XCrdntLo}" /></td>
			<td><c:out value="${data.updateOperationVO.YCrdntLa}" /></td>
			<td><c:out value="${data.updateOperationVO.vectorList}" /></td>
			<td><c:out value="${data.updateOperationVO.cntrwkNo}" /></td>
			<td><fmt:formatDate value="${data.updateOperationVO.rgsde}" pattern="yyyy-MM-dd" /></td>
			<td><fmt:formatDate value="${data.updateOperationVO.updde}" pattern="yyyy-MM-dd" /></td>
		 --%>
		</tr>
	</c:forEach>
		</table>
	</div>
	<div class="doBtn">
		<input type="button" value="엑셀다운로드" class="AXButton Blue" onclick="javascript:exdown();">
		<input type="button" value="닫기" class="AXButton Blue" onclick="javascript:window.close();">
	</div>
</div>
</body>
</html>
