<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
 /**
  * @Class Name : ChangeTrsInfoInqire.jsp
  * @Description : ChangeTrsInfo Inqire 화면
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

<title>국토변화정보포털서비스</title>

<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngicommon.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngimap.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXJ.min.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXInput.css">
<link href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">

<script src="<c:url value='/'/>js/map/apikey.js"></script>
<script src="<c:url value='/'/>js/openlayers/OpenLayers.js"></script>
<script src="<c:url value='/'/>js/map/lib/proj4js-compressed.js"></script>
<script src="<c:url value='/'/>js/axisj/jquery/jquery.min.js"></script>
<script src="<c:url value='/'/>js/map/changeinforegister.js"></script>

<script type="text/javascript">
<c:if test="${changeSttemntInfoVO.changeInfoId != 0}">   
	$( document ).ready(function() {
		// 지도이동
		parent.setPointOfMove( <c:out value="${updateOperationVO.XCrdntLo}"/>,<c:out value="${updateOperationVO.YCrdntLa}"/>);
		// 팝업 생성,, 화면에 벡터 그림.
		var vectors = $('input[name="vectorList"]').val();
		if(vectors.length>0) {
			var internalLonLat = new OpenLayers.Projection("EPSG:5186");
			var externalLonLat = new OpenLayers.Projection("EPSG:4326");
			
			var list = vectors.split('|');
			for(var index=0;index<list.length;index++) {
				shpWkt = list[index];
				var wktFomat = new OpenLayers.Format.WKT();

				//wktFomat.internalProjection = itrf2000;
				wktFomat.internalProjection = itrf2000;
				wktFomat.externalProjection = externalLonLat;
				
				var featureLine = wktFomat.read(shpWkt);
				createNewPopup(featureLine, false);
			}
		}
	});
</c:if>
</script>
<script type="text/javascript" src="<c:url value='/'/>js/EgovMultiFile.js" ></script>

<script type="text/javascript">
<!--
/* 글 목록 화면 function */
function fn_egov_selectList() {
	parent.resetVectorList();	// 도형정보 초기화
   	document.getElementById("detailForm").action = "<c:url value='/ngi/udt/updateOperationList.do'/>";
   	document.getElementById("detailForm").submit();		
}

/* 글 등록 취소 function */
function fn_egov_cancel() {
	parent.resetVectorList();	// 도형정보 초기화
	location.href = "<c:url value='/'/>ngi/udt/updateOperationList.do";
}

function fn_select_file(fileIdx) {
	$("#idx").val(fileIdx[0]);
	$.ajax({
		url : "<c:url value='/dextfile/selectSubFileList.do' />",
		type : "post",
		data : $("#detailForm").serialize(),
		dataType : "json",
		success : function(result){
			for(var i=0; i<result.subFileList.length; i++) {
				OnCallAddDownloadItem("/dextfile/downloadFile/"+result.subFileList[i][1]+"/"+result.subFileList[i][2], result.subFileList[i][3], "\\성과관리\\<c:out value="${updateOperationVO.changeInfoId }"/>_<c:out value="${updateOperationVO.opertNm}" />\\"+result.subFileList[i][0]);
			}
			fileIdx.shift();
			if(fileIdx.length == 0) {
				OnCallDownloadStart();
			} else {
				fn_select_file(fileIdx);
			}
		},
		error : function(){
			alert('error selectSubFileList.do');
		}
	});
}


function fn_download_file() {
	var fileIdx = new Array();
	fileIdx.push("${Constants.FILE_IDX_CHANGEINFO_SHP}");
	fileIdx.push("${Constants.FILE_IDX_CHANGEINFO_NGI_NDA}");
	fileIdx.push("${Constants.FILE_IDX_CHANGEINFO_DWG_DXF_5}");
	fileIdx.push("${Constants.FILE_IDX_CHANGEINFO_DWG_DXF_25}");
	fn_select_file(fileIdx);
}


-->
</script>

</head>
<body>
<form:form commandName="updateOperationVO" name="detailForm" id="detailForm"  enctype="multipart/form-data" >
<form:hidden path="updtInfoId" />
<form:hidden path="menuType"/>
<input type="hidden" name="changeInfoId" id="changeInfoId" value="${updateOperationVO.changeInfoId }" />
<input type="hidden" name="idx" id="idx" value="" />
<input type="hidden" name="XCrdntLo" value="<c:out value='${updateOperationVO.XCrdntLo}'/>"  />
<input type="hidden" name="YCrdntLa" value="<c:out value='${updateOperationVO.YCrdntLa}'/>"  />
<input type="hidden" name="vectorList" value="<c:out value='${updateOperationVO.vectorList}'/>"  />
	<div id="table">
	<table align="center" cellpadding="3" cellspacing="3" class="tabSwrite">
		<colgroup>
			<col width="70"/>
			<col width=""/>
			<col width="70"/>
			<col width=""/>			
		</colgroup>
		<tr>
			<th scope="row">제목</th>
			<td>${updateOperationVO.opertNm}
			<c:choose>
            	<c:when test="${empty updateOperationVO.XCrdntLo}">
            	<a href="#"><img src="<c:url value='/'/>images/map/mark_blue_off.gif" width="12" height="15" alt="위치표시" onClick="javascript:alert('위치정보가 없습니다.');"></a>
            	</c:when>
            	<c:otherwise>
            	<a href="#"><img src="<c:url value='/'/>images/map/mark_blue.gif" width="12" height="15" alt="위치표시" onClick='javascript:parent.setPointOfMove(<c:out value="${updateOperationVO.XCrdntLo}"/>,<c:out value="${updateOperationVO.YCrdntLa}"/>)' ></a>
            	</c:otherwise>
            </c:choose>
			</td>
			<th scope="row">사업지구</th>
			<td><c:out value="${updateOperationVO.bsnsDstrc}" /></td>
		</tr>

		<tr>

		</tr>	
		</table>	
		<table align="center" cellpadding="3" cellspacing="3"  class="tabSwrite">
		<colgroup>
			<col width="70"/>
			<col width=""/>
			<col width="70"/>
			<col width=""/>			
		</colgroup>		
		<tr>
			<th scope="row">용역사</th>
			<td><c:out value="${updateOperationVO.servcExcprofsCode}" /></td>
			<th scope="row">등록자</th>
			<td><c:out value="${updateOperationVO.wrter}" /></td>		
		</tr>			
		<tr>
			<th scope="row">상태</th>
			<td colspan="3">지도수정완료</td>		
		</tr>			
		</table>

	<div class="titleBtn">
			<p class="tit mt10">도엽정보</p>
		</div>
		<div class="viewtabArea">
			<div class="viewtab viewtab01">
				<div class="viewtabTitle on">1:5,000 구조화</div>
				<div class="boardList">
					<table  class="tabSwrite2" align="center" summary="이 표는 성과 등록 상세보기 1:5,000 구조화에 대한 축척, 도엽명, 도엽번호 정보 입니다.">
						<caption>성과 등록 상세보기</caption>
						<colgroup>
							<col style="width:70px;" />
							<col style="width:70px;" />
							<col style="width:70px;" />
							<col style="width:70px;" />
							<col style="width:70px;" />
							<col style="width:auto;" />
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">축척</th>
								<td>1:5,000</td>
								<th scope="row"><label for="tab01_select01">도엽</label></th>
								<td>
									<select id="tab01_select01" class="AXSelectSmall">
										<option><c:out value="${updateOperationVO.mapdmcNmA}" /></option>
									</select>
								</td>
								<th scope="row"><label for="tab01_select02">도엽번호</label></th>
								<td>
									<select id="tab01_select02" class="AXSelectSmall">
										<option><c:out value="${updateOperationVO.mapdmcA}" /></option>
									</select>
								</td>
							</tr>
						</tbody>
					</table>
				</div><!-- //boardList -->
			</div><!-- //viewtab01 -->

			<div class="viewtab viewtab02">
				<div class="viewtabTitle">1:5,000 정위치</div>
				<div class="boardList">
					<table  class="tabSwrite"  align="center" summary="이 표는 성과 등록 상세보기 1:5,000 정위치에 대한 축척, 도엽명, 도엽번호 정보 입니다.">
						<caption>성과 등록 상세보기</caption>
						<colgroup>
							<col style="width:70px;" />
							<col style="width:70px;" />
							<col style="width:70px;" />
							<col style="width:70px;" />
							<col style="width:70px;" />
							<col style="width:auto;" />
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">축척</th>
								<td>1:5,000</td>
								<th scope="row"><label for="tab02_select01">도엽</label></th>
								<td>
									<select id="tab02_select01"  class="AXSelectSmall">
										<option><c:out value="${updateOperationVO.mapdmcNmB}" /></option>
									</select>
								</td>
								<th scope="row"><label for="tab02_select02">도엽번호</label></th>
								<td>
									<select id="tab02_select02"  class="AXSelectSmall">
										<option><c:out value="${updateOperationVO.mapdmcB}" /></option>
									</select>
								</td>
							</tr>
						</tbody>
					</table>
				</div><!-- //boardList -->
			</div><!-- //viewtab02 -->

			<div class="viewtab viewtab03">
				<div class="viewtabTitle">1:25,000 정위치</div>
				<div class="boardList">
					<table class="tabSwrite" align="center" summary="이 표는 성과 등록 상세보기 1:25,000 정위치에 대한 축척, 도엽명, 도엽번호 정보 입니다.">
						<caption>성과 등록 상세보기</caption>
						<colgroup>
							<col style="width:70px;" />
							<col style="width:70px;" />
							<col style="width:70px;" />
							<col style="width:70px;" />
							<col style="width:70px;" />
							<col style="width:auto;" />
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">축척</th>
								<td>1:5,000</td>
								<th scope="row"><label for="tab03_select01">도엽</label></th>
								<td>
									<select id="tab03_select01"  class="AXSelectSmall">
										<option><c:out value="${updateOperationVO.mapdmcNmC}" /></option>
									</select>
								</td>
								<th scope="row"><label for="tab03_select02">도엽번호</label></th>
								<td>
									<select id="tab03_select02"  class="AXSelectSmall">
										<option><c:out value="${updateOperationVO.mapdmcC}" /></option>
									</select>
								</td>
							</tr>
						</tbody>
					</table>
				</div><!-- //boardList -->
			</div><!-- //viewtab03 -->
		</div><!-- //viewtabArea -->		
		<div class="boardList">
			<table class="tabSwrite" align="center" summary="이 표는 성과 등록 상세보기에 대한 유형, 제원, 좌표계, 타원체, 투영법, 원점 정보 입니다.">
				<caption>성과 등록 상세보기</caption>
				<colgroup>
					<col width="70"/>
					<col width=""/>
					<col width="70"/>
					<col width=""/>	
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">유형</th>
						<td>도로</td>
						<th scope="row">제원</th>
						<td>214 ㎡</td>
					</tr>
					<tr>
						<th scope="row">좌표계</th>
						<td>동경측지계</td>
						<th scope="row">타원체</th>
						<td>GRS80</td>
					</tr>
					<tr>
						<th scope="row">투영법</th>
						<td>TM</td>
						<th scope="row">원점</th>
						<td>중부원점</td>
					</tr>
<!-- 				
					<tr>
						<th scope="row">유형</th>
						<td><common:cdVal codeType="CHG003" codeCd="${updateOperationVO.changeTy}" /></td>
						<th scope="row">제원</th>
						<td><c:out value="${updateOperationVO.manp}" /><common:cdVal codeType="MNG024" codeCd="${updateOperationVO.unit}" /></td>
					</tr>
					<tr>
						<th scope="row">좌표계</th>
						<td><common:cdVal codeType="MNG010" codeCd="${updateOperationVO.cntm}" /></td>
						<th scope="row">타원체</th>
						<td><common:cdVal codeType="MNG021" codeCd="${updateOperationVO.oval}" /></td>
					</tr>
					<tr>
						<th scope="row">투영법</th>
						<td><common:cdVal codeType="MNG022" codeCd="${updateOperationVO.trnsprclaw}" /></td>
						<th scope="row">원점</th>
						<td><common:cdVal codeType="MNG023" codeCd="${updateOperationVO.trgnpt}" /></td>
					</tr>
 -->					
				</tbody>
			</table>
		</div><!-- //boardList -->		
		<div class="boardList">
			<table class="tabSwrite" align="center" summary="이 표는 성과 등록 상세보기에 대한 촬영년도, 조사년도, 수정년월 정보 입니다.">
				<caption>성과 등록 상세보기</caption>
				<colgroup>
					<col style="width:92px;" />
					<col style="width:92px;" />
					<col style="width:92px;" />
					<col style="width:92px;" />
					<col style="width:92px;" />
					<col style="width:auto;" />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><label for="select01">촬영년도</label></th>
						<td>
							<select id="select01"  class="AXSelectSmall">
								<option><c:out value="${updateOperationVO.phtogrfYear}" />년</option>
							</select>
						</td>
						<th scope="row"><label for="select02">조사년도</label></th>
						<td>
							<select id="select02"  class="AXSelectSmall">
								<option><c:out value="${updateOperationVO.examinYear}" />년</option>
							</select>
						</td>
						<th scope="row"><label for="select03">수정년월</label></th>
						<td>
							<select id="select03" class="AXSelectSmall">
								<option><c:out value="${updateOperationVO.updtYear}" />년</option>
							</select>
							<select class="AXSelectSmall">
								<option><c:out value="${updateOperationVO.updtMt}" />월</option>
							</select>
						</td>
					</tr>
				</tbody>
			</table>
		</div><!-- //boardList -->

		<div class="titleBtn">
			<p class="tit">객체성과 등록</p>
			<span class="titEx">※ SHP파일, SHX파일, DBF파일이 포함되어 있는 폴더입니다.</span>
			<div class="btnArea">
				<a href="#" onclick="fn_download_file(); return false;" class="btn_gray btn_w110">다운로드</a>
			</div><!-- //btnArea -->
		</div>
		<div class="boardList">
			<table class="tabSwrite" align="center" summary="이 표는 성과 등록 상세보기에 대한 SHP 정보 입니다.">
				<caption>성과 등록 상세보기</caption>
				<colgroup>
					<col style="width:200px;" />
					<col style="width:auto;" />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">SHP</th>
						<td class="cell_l20">
							<span class="mr10"><img src="<c:url value='/images/btn/btn_addfile.jpg' />" alt="첨부파일" /></span>SHP
						</td>
					</tr>
				</tbody>
			</table>
		</div><!-- //boardList -->

		<div class="titleBtn">
			<p class="tit">도엽성과 등록</p>
			<span class="titEx">※ 1:5,000 구조화 및 정위치, 1:25,000 정위치의 성과가 포함되어 있는 폴더입니다.</span>
		</div>
		<div class="boardList">
			<table class="tabSwrite" align="center" summary="이 표는 성과 등록 상세보기에 대한 1:5,000 구조화, 1:5,000 정위치, 1:25,000 정위치 정보 입니다.">
				<caption>성과 등록 상세보기</caption>
				<colgroup>
					<col style="width:200px;" />
					<col style="width:auto;" />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">1:5,000 구조화<br />(NGI, NDA)</th>
						<td class="cell_l20">
							<span class="mr10"><img src="<c:url value='/images/btn/btn_addfile.jpg' />" alt="첨부파일" /></span>1:5,000 구조화 (NGI, NDA)
						</td>
					</tr>
					<tr>
						<th scope="row">1:5,000 정위치<br />(DWG, DXF)</th>
						<td class="cell_l20">
							<span class="mr10"><img src="<c:url value='/images/btn/btn_addfile.jpg' />" alt="첨부파일" /></span>1:5,000 정위치 (DWG, DXF)
						</td>
					</tr>
					<tr>
						<th scope="row">1:25,000 정위치<br />(DWG, DXF)</th>
						<td class="cell_l20 ">
							<span class="mr10"><img src="<c:url value='/images/btn/btn_addfile.jpg' />" alt="첨부파일" /></span>1:25,000 정위치 (DWG, DXF)
						</td>
					</tr>
				</tbody>
			</table>
		</div><!-- //boardList --><div id="dextdownloadnx_container" style="height:0px;"></div>


  </div>    

	<div class="MapMarg_T30">
		<div id="BtnAreaMap">
			<div id="StyleButtonDivMap">

			<button type="button" class="AXButtonSmall Blue" OnClick="javascript:fn_egov_cancel();"><i class="fa fa-align-justify fa-lg"></i> 목록</button>

			</div>
		</div>
	</div>
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input type="hidden" name="searchSttus" value="<c:out value='${searchVO.searchSttus}'/>"/>
<input type="hidden" name="searchChangeTy" value="<c:out value='${searchVO.searchChangeTy}'/>"/>
<input type="hidden" name="searchDateSRgsde" value="<fmt:formatDate value="${searchVO.searchDateSRgsde}" pattern="yyyy-MM-dd" />" />
<input type="hidden" name="searchDateERgsde" value="<fmt:formatDate value="${searchVO.searchDateERgsde}" pattern="yyyy-MM-dd" />" />
</form:form>
</body>
</html>

