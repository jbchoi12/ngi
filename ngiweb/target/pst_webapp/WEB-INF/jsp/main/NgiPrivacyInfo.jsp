<%--
  Class Name : NgiAboutSite.jsp
  Description : 사이트소개
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2011.08.31   JJY       경량환경 버전 생성
 
    author   : 실행환경개발팀 JJY
    since    : 2011.08.31 
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>국토변화정보포털서비스</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngicommon.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngimain.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngistyle.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngitable.css">
</head>
<body>
<!-- wrap start -->
<div id="wrap">	
	<!-- header start -->
	<div id="header"><c:import url="/sym/mms/NgiMainMenuHead.do" /></div>	
	<!-- //header end -->
<!-- 내용 -->
    <div id="container">



<div class="Subcontainer">
	<!-- lnb -->
    <div class="LeftMenu">
    <h2>서비스</h2>
        <ul class="side1_ul">
        <li  class="side1_li"><a href="<c:url value='/'/>main/NgiNotification.do" class="side1_a">법적고지</a></li>
        <li  class="side1_li"><a href="<c:url value='/'/>main/NgiServiceTerms.do" class="side1_a">이용약관</a></li>
        <li  class="side1_li"><a href="<c:url value='/'/>main/NgiPrivacyInfo.do" class="side1_aon">개인정보처리방침</a></li>
        </ul>
		<c:import url="/EgovPageLink.do?link=main/inc/NgiIncFBtn" />
    </div>
	<!-- //lnb -->

<div id="Content">
    <div class="ConMTitle">
        <!-- history -->
        <div class="History">
            <a href="#" class="his1">처음으로</a><a href="#" class="History1"></a> &gt;
            <a href="#" class="History2">개인정보처리방침</a>
        </div>
        <!-- //history -->
        <h3>개인정보처리방침</h3>         
    </div>


	<div class="ConMView">
 <p class="h4Txt top_mar_0">
국토지리정보원이 취급하는 모든 개인정보는 개인정보 보호법 등 관련 법령상의 개인정보보호 규정을 준수하여 수집·보유·처리되고 있습니다.
국토지리정보원은 개인정보보호법에 따라 이용자의 개인정보 보호 및 권익을 보호하고 개인정보와 관련한 이용자의 고충을 원활하게 처리할 수 있도록 아래와 같은 처리 방침을 두고 있습니다.
</p>

<ol class="pipp">
	<li>
		<span class="txt_sbj">개인정보의 처리목적, 개인정보의 처리 및 보유기간, 처리하는 개인정보의 항목</span>
		<span>개인정보보호법 제32조에 따라 등록․공개하는 개인정보파일의 처리목적, 처리 및 보유기간, 처리하는 개인정보의 항목은 다음과 같습니다.</span>
		<ul>
			<li>국토지리정보원 개인정보파일 보유 현황 ☞ <a href="http://www.ngii.go.kr/rbs/data/files/commonFiles/ngii_privacy_stat.zip" title="">보러가기</a></li>
			<li>좀 더 상세한 <국토지리정보원>의 개인정보파일 등록사항 공개는 개인정보보호 종합지원포털(<a href="http://www.privacy.go.kr" title="개인정보보호 종합지원포털" target="_blank">www.privacy.go.kr</a>) ▷ 개인정보민원 ▷ 개인정보의 열람 등 요구 ▷ 개인정보파일 목록 검색 ▷ 기관명에 “국토지리정보원” 입력 후 조회하실 수 있습니다.</li>
		</ul>
	</li>

	<li>
		<span class="txt_sbj">개인정보의 제3자 제공</span>
		<span>국토지리정보원은 원칙적으로 정보주체의 개인정보를 수집·이용 목적으로 명시한 범위 내에서 처리하며, 아래의 경우를 제외하고는 정보주체의 사전 동의 없이는 본래의 목적 범위를 초과하여 처리하거나 제3자에게 제공하지 않습니다.</span>
		<ol>
			<li>정보주체로부터 별도의 동의를 받는 경우</li>
			<li>법률에 특별한 규정이 있는 경우</li>
			<li>정보주체 또는 법정대리인이 의사표시를 할 수 없는 상태에 있거나 주소불명 등으로 사전 동의를 받을 수 없는 경우로서 명백히 정보주체 또는 제3자의 급박한 생명, 신체, 재산의 이익을 위하여 필요하다고 인정되는 경우</li>
			<li>통계작성 및 학술연구 등의 목적을 위하여 필요한 경우로서 특정 개인을 알아 볼 수 없는 형태로 개인정보를 제공하는 경우</li>
			<li>개인정보를 목적 외의 용도로 이용하거나 이를 제3자에게 제공하지 아니하면 다른 법률에서 정하는 소관 업무를 수행할 수 없는 경우로서 보호위원회의 심의·의결을 거친 경우</li>
			<li>조약, 그 밖의 국제협정의 이행을 위하여 외국정보 또는 국제기구에 제공하기 위하여 필요한 경우</li>
			<li>범죄의 수사와 공소의 제기 및 유지를 위하여 필요한 경우</li>
			<li>법원의 재판업무 수행을 위하여 필요한 경우</li>
			<li>형 및 감호, 보호처분의 집행을 위하여 필요한 경우</li>
		</ol>
	</li>
	
	<li>
		<span class="txt_sbj">개인정보 처리 위탁</span>
		<span>국토지리정보원이 법령 및 기타 개별법에 근거하여 위탁하고 있는 개인정보 현황은 다음과 같습니다.

		<table border="1" class="table_basic" summary="개인정보 처리 위탁 현황">
		<caption class="skip">개인정보 처리 위탁 현황</caption>
		<thead>
			<tr>
				<th scope="col">수탁업체</th>
				<th scope="col">위탁항목</th>
				<th scope="col">위탁업무내용</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td rowspan="2">(주)이에스이</th>
				<td>홈페이지 회원명단</th>
				<td>홈페이지 운영 및 관리</th>
			</tr>
			<tr>
				<td>공공측량성과관리시스템 회원명단</th>
				<td>공공측량성과관리시스템 운영 및 관리</th>
			</tr>
			<tr>
				<td>(주)엔컴즈</th>
				<td>위성측량장애알림 서비스 회원명단</th>
				<td>국토지리정보원 GNSS서비스 운영 및 관리</th>
			</tr>
		</tbody>
		</table>

		국토지리정보원은 개인정보의 처리업무를 위탁하는 경우 아래의 내용이 포함된 문서에 의하여 처리하고 있습니다.</span>

		<ol>
			<li>위탁업무 수행 목적 외 개인정보의 처리 금지에 관한 사항</li>
			<li>개인정보의 관리적·기술적 보호조치에 관한 사항</li>
			<li>개인정보의 안전관리에 관한 사항 
				<ul>
					<li>위탁업무의 목적 및 범위, 재위탁 제한에 관한 사항, 개인정보 안전성 확보 조치에 관한 사항, 위탁업무와 관련하여 보유하고 있는 개인정보의 관리현황점검 등 감독에 관한 사항, 수탁자가 준수하여야할 의무를 위반한 경우의 손해배상책임에 관한 사항</li>
					<li>또한, 위탁하는 업무의 내용과 개인정보 처리업무를 위탁받아 처리하는 자(“수탁자”)에 대하여 해당 홈페이지에 공개하고 있습니다.</li>
				</ul>
			</li>
		</ol>
	</li>

	<li>
		<span class="txt_sbj">정보주체의 권리·의무 및 그 행사 방법</span>
		<span>정보주체는 아래와 같은 권리를 행사 할 수 있으며, 만14세 미만 아동의 법정대리인은 그 아동의 개인정보에 대한 열람, 정정·삭제, 처리정지를 요구할 수 있습니다.</span>
		<ul>
			<li>개인정보 열람 요구<br />
				국토지리정보원에서 보유하고 있는 개인정보파일은「개인정보보호법」제35조(개인정보의 열람)에 따라 자신의 개인정보에 대한 열람을 요구할 수 있습니다. 다만, 개인정보 열람 요구는 법 제35조 5항에 의하여 아래와 같이 제한될 수 있습니다. 
				<ol style="padding-left:20px;">
					<li>법률에 따라 열람이 금지되거나 제한되는 경우</li>
					<li>다른 사람의 생명·신체를 해할 우려가 있거나 다른 사람의 재산과 그 밖의 이익을 부당하게 침해할 우려가 있는 경우</li>
					<li>공공기관이 아래 각 목의 어느 하나에 해당하는 업무를 수행할 때 중대한 지장을 초래하는 경우
						<ol style="list-style-type:none;">
							<li>가. 조세의 부과·징수 또는 환급에 관한 업무</li>
							<li>나. 학력·기능 및 채용에 관한 시험, 자격 심사에 관한 업무</li>
							<li>다. 보상금·급부금 산정 등에 대하여 진행 중인 평가 또는 판단에 관한 업무</li>
							<li>라. 다른 법률에 따라 진행 중인 감사 및 조사에 관한 업무</li>
						</ol>
					</li>
				</ol>
			</li>
			<li>개인정보 정정·삭제 요구<br />
				국토지리정보원에서 보유하고 있는 개인정보파일은「개인정보보호법」 제36조(개인정보의 정정·삭제)에 따라 정정·삭제를 요구할 수 있습니다. 다만, 다른 법령에서 그 개인정보가 수집 대상으로 명시되어 있는 경우에는 그 삭제를 요구할 수 없습니다.
			</li>
			<li>개인정보 처리정지 요구<br />
				국토지리정보원에서 보유하고 있는 개인정보파일은 「개인정보보호법」 제37조(개인정보의 처리정지 등)에 따라 처리정지를 요구할 수 있습니다. 다만, 개인정보 처리정지 요구 시 법 제37조 2항에 의하여 처리정지 요구가 거절될 수 있습니다.
				<ol style="padding-left:20px;">
					<li>법률에 특별한 규정이 있거나 법령상 의무를 준수하기 위하여 불가피한 경우</li>
					<li>다른 사람의 생명·신체를 해할 우려가 있거나 다른 사람의 재산과 그 밖의 이익을 부당하게 침해할 우려가 있는 경우</li>
					<li>공공기관이 개인정보를 처리하지 아니하면 다른 법률에서 정하는 소관 업무를 수행할 수 없는 경우</li>
					<li>개인정보를 처리하지 아니하면 정보주체와 약정한 서비스를 제공하지 못하는 등 계약의 이행이 곤란한 경우로서 정보주체가 그 계약의 해지 의사를 명확하게 밝히지 아니한 경우</li>
				</ol>
			</li>
		</ul>
	</li>

	<li>
		<span class="txt_sbj">개인정보의 파기</span>
		<span>국토지리정보원은 원칙적으로 개인정보 처리목적이 달성된 개인정보는 지체없이 파기합니다. 파기의 절차, 기한 및 방법은 아래와 같습니다.</span>
		<ul>
			<li>파기 절차<br />
				개인정보는 목적 달성 후 즉시 또는 별도의 공간에 옮겨져 내부 방침 및 기타 관련법령에 따라 일정기간 저장된 후 파기됩니다. 별도의 공간으로 옮겨진 개인정보는 법률에 의한 경우가 아니고서는 다른 목적으로 이용되지 않습니다.
			</li>
			<li>파기 기한 및 파기 방법<br />
				보유기간이 만료되었거나 개인정보의 처리목적달성, 해당 업무의 폐지 등 그 개인정보가 불필요하게 되었을 때에는 지체없이 파기합니다. 전자적 파일형태의 정보는 기록을 재생할 수 없는 기술적 방법을 사용합니다. 종이에 출력된 대인정보는 분쇄기로 분쇄하거나 소각을 통하여 파기합니다.
			</li>
		</ul>
	</li>

	<li>
		<span class="txt_sbj">개인정보의 안정성 확보 조치</span>
		<ul>
			<li>개인정보 취급직원의 최소화 및 교육<br />
				개인정보를 취급하는 직원은 반드시 필요한 인원에 한하여 지정 · 관리하고 있으며 취급직원을 대상으로 안전한 관리를 위한 교육을 실시하고 있습니다.</li>
			<li>개인정보에 대한 접근 제한<br />
				개인정보를 처리하는 데이터베이스시스템에 대한 접근권한의 부여·변경·말소를 통하여 개인정보에 대한 접근통제를 위한 필요한 조치를 하고 있으며 침입차단시스템을 이용하여 외부로부터의 무단 접근을 통제하고 있습니다.</li>
			<li>접속기록의 보관<br />
				개인정보처리시스템에 접속한 기록(웹 로그, 요약정보 등)을 최소 6개월 이상 보관·관리하고 있습니다.</li>
			<li>개인정보의 암호화<br />
				개인정보는 암호화 등을 통해 안전하게 저장 및 관리되고 있습니다. 또한 중요한 데이터는 저장 및 전송 시 암호화하여 사용하는 등의 별도 보안기능을 사용하고 있습니다.</li>
			<li>보안프로그램 설치 및 주기적 점검·갱신<br />
				해킹이나 컴퓨터 바이러스 등에 의한 개인정보 유출 및 훼손을 막기 위하여 보안프로그램을 설치하고 주기적으로 갱신·점검하고 있습니다.</li>
			<li>비인가자에 대한 출입 통제<br />
				개인정보를 보관하고 있는 개인정보시스템의 물리적 보관 장소를 별도로 두고 이에 대해 출입통제 절차를 수립, 운영하고 있습니다.</li>
		</ul>
	</li>
	
	<li>
		<span class="txt_sbj">권익침해 구제 방법</span>
		<span>개인정보주체는 개인정보침해로 인한 피해를 구제 받기 위하여 개인정보 분쟁조정위원회, 한국인터넷진흥원 개인정보 침해신고센터 등에 분쟁 해결이나 상담 등을 신청할 수 있습니다.</span>
		<div class="cbox2 tp02">
			<ul style="list-style-type:none;">
				<li>∙개인정보 분쟁조정위원회 : (국번없이) 118 (<a href="http://privacy.kisa.or.kr" title="" target="_blank">privacy.kisa.or.kr</a>)</li>
				<li>∙개인정보 침해신고센터 : (국번없이) 118 (<a href="http://privacy.kisa.or.kr" title="" target="_blank">privacy.kisa.or.kr</a>)</li>
				<li>∙대검찰청 사이버범죄수사단 : 02-3480-3571 (cybercid@spo.go.kr)</li>
				<li>∙경찰청 사이버테러대응센터 : 1566-0112 (<a href="http://www.netan.go.kr" title="" target="_blank">www.netan.go.kr</a>)</li>
			</ul>
		</div>
		<span>또한, 개인정보의 열람, 정정·삭제, 처리정지 등에 대한 정보주체자의 요구에 대하여 공공기관의 장이 행한 처분 또는 부작위로 인하여 권리 또는 이익을 침해 받은 자는 행정심판법이 정하는 바에 따라 행정심판을 청구할 수 있습니다.<br /><br />
		 ☞ 행정심판에 대한 자세한 사항은 국민권익위원회(<a href="http://www.acrc.go.kr" title="">www.acrc.go.kr</a>) 홈페이지를 참고하시기 바랍니다.</span>
		
	</li>

	<li>
		<span class="txt_sbj">개인정보보호 책임자 및 담당자</span>
		<ul>
			<li>국토지리정보원 개인정보보호책임자 : 국토조사과장 권상대</li>
			<li>국토지리정보원 개인정보보호담당자 : 국토조사과 김보성</li>
		</ul>
		<ul style="list-style-type:none;">
			<li>∙ 이메일 : bosungkim@korea.kr</li>
			<li>∙ 전화번호 : 031-210-2703, Fax : 031-210-2707</li>
			<li>∙ 주소 : 우)443-772 경기도 수원시 영통구 월드컵로 92 국토지리정보원</li>
		</ul>
		
	</li>

	<li>
		<span class="txt_sbj">개인정보처리방침 변경</span>
		<span>이 개인정보처리방침은 2013. 8. 12.부터 적용됩니다.<br />
 이전의 개인정보처리방침은 아래에서 확인할 수 있습니다.</span>
		<ul style="list-style-type:none;">
			<li><a href="http://www.ngii.go.kr/rbs/data/files/commonFiles/privacy_20130614.zip" title="">· 2013. 6. 14 ～ 2013. 8. 12. ☞ 적용지침 </a><li>  
			<li><a href="http://www.ngii.go.kr/rbs/data/files/commonFiles/privacy_20130110.zip" title="">· 2013. 1. 10 ～ 2013. 6. 14. ☞ 적용지침 </a><li>
			<li><a href="http://www.ngii.go.kr/rbs/data/files/commonFiles/privacy_20120525.zip" title="">· 2012. 5. 25 ～ 2013. 1. 10. ☞ 적용지침 </a><li>
		</ul>

	</li>
</ol>
 
 
 
 
	</div>
	</div>    
		</div>

    <!-- 하단 유틸메뉴 -->
    <div class="MBotUtill"><c:import url="/EgovPageLink.do?link=main/inc/NgiIncUtil" /></div>
    <!-- //하단 유틸메뉴 -->


    <!-- 하단 배너 -->
    <div class="MBanner"><c:import url="/EgovPageLink.do?link=main/inc/NgiIncBanner" /></div>
    <!-- //하단 배너 -->

  </div>
<!-- //내용 -->
	<!-- footer 시작 -->
	<div id="footer"><c:import url="/EgovPageLink.do?link=main/inc/NgiIncFooter" /></div>
	<!-- //footer 끝 -->
</div>
<!-- //wrap end -->
</body>
</html>