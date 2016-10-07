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
.contt{margin-top:8px;overflow: auto; width:928px;height: 180px;}
</style>
<script type="text/javascript" src="<c:url value='/'/>js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
function exdown() {
	url = "<c:url value='/ngi/chg/changeFileDownloadDone.do'/>";
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
			<th>신고자명</th>
			<th>전화번호</th>
			<th>전자우편</th>
			<th>신고일</th>
			<th>신고제목</th>
			<th>유형</th>
			<th>변동지 주소</th>
			<th>신고내용</th>
		</tr>
		<!-- 데이터. -->
	<c:forEach var="data" items="${changeInfoList}" varStatus="status">
		<tr>
			<td><c:out value="${data.changeSttemntInfoVO.registerNm}" escapeXml="false"/></td>
			<td><c:out value="${data.changeSttemntInfoVO.telno}" escapeXml="false"/></td>
			<td><c:out value="${data.changeSttemntInfoVO.email}" escapeXml="false"/></td>
			<td><fmt:formatDate value="${data.changeSttemntInfoVO.registDe}" pattern="yyyy-MM-dd" /></td>
			<td><c:out value="${data.changeSttemntInfoVO.changeSj}" escapeXml="false"/></td>
			<td><c:out value="${data.sttemntty_result}" escapeXml="false"/></td>
			<td><c:out value="${data.changeSttemntInfoVO.changeRnAdresCn}" escapeXml="false"/></td>
			<td><c:out value="${data.changeSttemntInfoVO.sttemntCn}" escapeXml="false"/></td> 
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
