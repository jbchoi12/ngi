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
	url = "<c:url value='/ngi/chg/cntrwkFileDownloadDone.do'/>";
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
    input.value = "change_data_sample.xls";
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
			<th rowspan="2">관리번호</th>
			<th rowspan="2">변경여부</th>
			<th colspan="3">기관</th>
			<th rowspan="2">변동유형</th>
			<th rowspan="2">공사명</th>
			<th rowspan="2">주소유형</th>
			<th colspan="3">대상지역</th>
			<th colspan="2">규모</th>
			<th colspan="2">변경사항</th>
			<th colspan="3">공사기간</th>
			<th colspan="4">공사감독</th>
			<th rowspan="2">좌표계</th>
			<th rowspan="2">비고</th>
		</tr>
		<tr>
			<th>광역시도 및 국토청</th>
			<th>계획기관</th>
			<th>감독기관</th>
			<th>대상위치</th>
			<th>공사시점</th>
			<th>공사종점</th>
			<th>면적(㎡)</th>
			<th>연장(㎞)</th>
			<th>대상변경전</th>
			<th>대상변경후</th>
			<th>착공일</th>
			<th>완공예정일</th>
			<th>최종완공일</th>
			<th>부서명</th>
			<th>담당자</th>
			<th>연락처</th>
			<th>e-mail</th>
		</tr>
		<!-- 데이터. -->
	<c:forEach var="data" items="${cntrwkInfoList}" varStatus="status">
		<tr>
			<td><c:out value="${data.changeCntrwkInfoVO.cntrwkNo}" /></td>
			<td></td>
			<td><c:out value="${data.psitnEngn_result}"/></td>
			<td><c:out value="${data.planEngn_result}"/></td>
			<td><c:out value="${data.mngEngn_result}"/></td>
			<td><c:out value="${data.changeTy_result}"/></td>
			<td><c:out value="${data.changeCntrwkInfoVO.changeSj}" /></td>
			<td><c:out value="${data.changeCntrwkInfoVO.addrTy}" /></td>
			<td><c:out value="${data.changeCntrwkInfoVO.trgetAreaNm}" /></td>
			<td><c:out value="${data.changeCntrwkInfoVO.cntrwkPnttm}" /></td>
			<td><c:out value="${data.changeCntrwkInfoVO.cntrwkTmnl}" /></td>
			<td><c:out value="${data.changeCntrwkInfoVO.ar}" /> ㎡</td>
			<td><c:out value="${data.changeCntrwkInfoVO.extn}" /> ㎞</td>
			<td><c:out value="${data.changeCntrwkInfoVO.trgetBfchgCn}" /></td>
			<td><c:out value="${data.changeCntrwkInfoVO.trgetAftchCn}" /></td>
			<td><fmt:formatDate value="${data.changeCntrwkInfoVO.strwrkDe}" pattern="yyyy-MM-dd" /></td>
			<td><fmt:formatDate value="${data.changeCntrwkInfoVO.competPrearngeDe}" pattern="yyyy-MM-dd" /></td>
			<td><fmt:formatDate value="${data.changeCntrwkInfoVO.lastCompetDe}" pattern="yyyy-MM-dd"/></td>
			<td><c:out value="${data.changeCntrwkInfoVO.chrgDeptNm}" /></td>
			<td><c:out value="${data.changeCntrwkInfoVO.chargerNm}" /></td>
			<td><c:out value="${data.changeCntrwkInfoVO.chargerTlphonNo}" /></td>
			<td><c:out value="${data.changeCntrwkInfoVO.chargerEmail}" /></td>
			<td><c:out value="${data.cntm_result}"/></td>
			<td><c:out value="${data.changeCntrwkInfoVO.rm}" /></td>
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
