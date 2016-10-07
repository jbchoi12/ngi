<%--
  Class Name : EgovIncSubHeader.jsp
  Description : 서브페이지의 상단화면(include)
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2011.08.31   JJY       경량환경 버전 생성

    author   : 실행환경개발팀 JJY
    since    : 2011.08.31
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="egovframework.com.cmm.LoginVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="skipNav" class="invisible">
    <dl>
        <dt>콘텐츠 바로가기</dt>
        <dd><a href="#middle_content">컨텐츠 바로가기</a></dd>
        <dd><a href="#topmenu_nav">메인메뉴 바로가기</a></dd>
        <dd><a href="#leftmenu_div">좌메뉴 바로가기</a></dd>
    </dl>
</div>
<div class="headerin">
<!-- 로고 -->
	<h1 class="logo">
	<a href="<c:url value='/'/>"><img src="<c:url value='/'/>images/common/logo_symbol.gif" alt="국토지리정보원" title="국토지리정보원"></a><a href="http://www.ngii.go.kr/" target="_blank"></a><a href="#"></a>
	<a href="<c:url value='/'/>"><img src="<c:url value='/'/>images/common/logo_wordmark.gif" alt="지형지물변동관리시스템" title="지형지물변동관리시스템"></a></h1>
	<!-- //로고 -->
	<div class="uti_menu">
		<ul>
                <li><a href="<c:url value='/'/>">처음으로</a></li>
                <li class="noneline">ㅣ</li>
        <%
        String sUserId = (String)session.getAttribute("sUserId");
        if(sUserId != null){
        %>
               <li><a href="<c:url value='/'/>uat/sso/globalLogout.do">로그아웃</a></li>
                
        <%
           } else {
        %>        
               <li><a href="<c:url value='/'/>redirect.jsp">로그인</a></li>
        <%
           } 
        %>  
                <li class="noneline">ㅣ</li>
                <li><a href="http://www.ngii.go.kr/kor/member/agreement.do?rbsIdx=2">회원가입</a></li>
             	<li class="noneline">ㅣ</li>
                <li><a href="<c:url value='/'/>ngi/info/NgiProvideService.do">서비스안내</a></li>
		</ul>
	</div>


		<!-- tnb -->
	  <div id="tnb">
			<!-- tnb_r -->
			<ul id="tnbMenu" class="tnb_r">
			<li id="menu1" onmouseover="menuOverAction(this);" onmouseout="menuOutAction(this);" onfocus="return true;" onblur="return true;"><a href="<c:url value='/'/>ngi/info/NgiAboutSite.do">시스템안내</a>
			  <div id="subMenu1" class="sub">
				  <ul class="subMenu11">
                    <li><a href="<c:url value='/'/>ngi/info/NgiAboutSite.do">국토변화포털서비스</a></li>
                    <li><a href="<c:url value='/'/>ngi/info/NgiProvideService.do">제공서비스</a></li>
                    <li><a href="<c:url value='/'/>ngi/info/NgiDirection.do">사용안내</a></li>
					</ul>
			  </div>
			</li>
			<li id="menu2" onmouseover="menuOverAction(this);" onmouseout="menuOutAction(this);" onfocus="return true;" onblur="return true;"><a href="<c:url value='/'/>ngi/mng/NgiMngView.do">지형지물변동관리</a>
			  <div id="subMenu2" class="sub">
				  <ul class="subMenu22">
                    <li><a href="<c:url value='/'/>ngi/mng/NgiMngView.do?tabNum=1">변경신고</a></li>
                    <li><a href="<c:url value='/'/>ngi/mng/NgiMngView.do?tabNum=2">변동보고</a></li>
                    <li><a href="<c:url value='/'/>ngi/mng/NgiMngView.do?tabNum=3">변화정보</a></li>
                    <li><a href="<c:url value='/'/>ngi/mng/NgiMngView.do?tabNum=4">새주소/세움터 정보</a></li>
					</ul>
				</div>
			</li>
			<li id="menu3" onmouseover="menuOverAction(this);" onmouseout="menuOutAction(this);" onfocus="return true;" onblur="return true;"><a href="<c:url value='/'/>ngi/ntc/NgiNoticeService.do">알림서비스</a>
			  <div id="subMenu3" class="sub">
					<ul class="subMenu33">
					<li><a href="<c:url value='/'/>ngi/ntc/NgiNoticeService.do">알림서비스란?</a></li>
                    <li><a href="<c:url value='/'/>ngi/ntc/addTnNtcnSrvcView.do">알림서비스등록</a></li>
                    <li><a href="<c:url value='/'/>ngi/ntc/NgiGeoNtcnList.do">지형고시 열람</a></li>
					</ul>
			  </div>
			</li>
            <li id="menu4" onmouseover="menuOverAction(this);" onmouseout="menuOutAction(this);" onfocus="return true;" onblur="return true;"><a href="<c:url value='/'/>cop/bbs/selectBoardList.do?bbsId=BBSMSTR_AAAAAAAAAAAA">커뮤니티</a>
              <div id="subMenu4" class="sub">
					<ul class="subMenu44">
					<li><a href="<c:url value='/'/>cop/bbs/selectBoardList.do?bbsId=BBSMSTR_AAAAAAAAAAAA">공지사항</a></li>
                    <li><a href="<c:url value='/'/>uss/olh/faq/FaqListInqire.do">FAQ</a></li>
                    <li><a href="<c:url value='/'/>uss/olh/qna/QnaListInqire.do">Q&amp;A</a></li>
					</ul>
			  </div>
			</li>
            <li id="menu5" onmouseover="menuOverAction(this);" onmouseout="menuOutAction(this);" onfocus="return true;" onblur="return true;"><a href="<c:url value='/'/>ngi/api/NgiApiIntroduce.do">연계서비스</a>
              <div id="subMenu5" class="sub">
					<ul class="subMenu55">
					<li><a href="<c:url value='/'/>ngi/api/NgiApiIntroduce.do">Open API</a></li>
                    <li><a href="<c:url value='/'/>ngi/trs/NgiTrsInfo.do">세움터/새주소</a></li>
					</ul>
			  </div>
			</li>
		  </ul>
         <div class="menuAll">
				<!-- <a href="#"><span>서비스전체보기 <img src="<c:url value='/'/>images/common/top_menu_all.gif" alt="서비스전체보기" ></span></a>  -->
			</div>
          <div class="allView">
				<div class="allMenu1">
					<ul>
                    <li><a href="<c:url value='/'/>ngi/info/NgiAboutSite.do">국토변화포털서비스</a></li>
                    <li><a href="<c:url value='/'/>ngi/info/NgiProvideService.do">제공서비스</a></li>
                    <li><a href="<c:url value='/'/>ngi/info/NgiDirection.do">사용안내</a></li>
					</ul>
				</div>
				<div class="allMenu2">
					<ul>
                    <li><a href="<c:url value='/'/>ngi/mng/NgiMngView.do?tabNum=1">변경신고</a></li>
                    <li><a href="<c:url value='/'/>ngi/mng/NgiMngView.do?tabNum=2">변동보고</a></li>
                    <li><a href="<c:url value='/'/>ngi/mng/NgiMngView.do?tabNum=3">변화정보</a></li>
                    <li><a href="<c:url value='/'/>ngi/mng/NgiMngView.do?tabNum=4">새주소/세움터 정보</a></li>
					</ul>
				</div>
				<div class="allMenu3">
					<ul>
					<li><a href="<c:url value='/'/>ngi/ntc/NgiNoticeService.do">알림서비스란?</a></li>
                    <li><a href="<c:url value='/'/>ngi/ntc/addTnNtcnSrvcView.do">알림서비스등록</a></li>
                    <li><a href="<c:url value='/'/>ngi/ntc/NgiGeoNtcnList.do">지형고시 열람</a></li>
					</ul>
				</div>
				<div class="allMenu4">
					<ul>
					<li><a href="<c:url value='/'/>cop/bbs/selectBoardList.do?bbsId=BBSMSTR_AAAAAAAAAAAA">공지사항</a></li>
                    <li><a href="<c:url value='/'/>uss/olh/faq/FaqListInqire.do">FAQ</a></li>
                    <li><a href="<c:url value='/'/>uss/olh/qna/QnaListInqire.do">Q&amp;A</a></li>
					</ul>
				</div>
				<div class="allMenu5">
					<ul>
					<li><a href="<c:url value='/'/>ngi/api/NgiApiIntroduce.do">Open API</a></li>
                    <li><a href="<c:url value='/'/>ngi/trs/NgiTrsInfo.do">세움터/새주소</a></li>
					</ul>
				</div>
			</div>		  
		  
	<!-- //tnb_r -->
		  <script type="text/javascript">//<![CDATA[
			menuNone();menuCurrent();
			//]]></script>
	  </div>
	<!-- //tnb -->        
        
		<!-- gnb 
        <div id="gnb">
            <ul>
              <li class="Menu_1"><a href="<c:url value='/'/>ngi/info/NgiAboutSite.do">시스템안내</a></li>
              <li class="Menu_1"><a href="<c:url value='/'/>ngi/mng/NgiMngView.do">지형지물변동관리</a></li>
              <li class="Menu_1"><a href="<c:url value='/'/>ngi/ntc/NgiNoticeService.do">알림서비스</a></li>
              <li class="Menu_1"><a href="<c:url value='/'/>cop/bbs/selectBoardList.do?bbsId=BBSMSTR_AAAAAAAAAAAA">커뮤니티</a></li>
              <li class="Menu_1"><a href="<c:url value='/'/>ngi/api/NgiApiIntroduce.do">연계서비스</a></li>
              <li class="Menu_1"><span><a href="#">서비스전체보기<img src="<c:url value='/'/>images/common/top_menu_all.gif" alt="서비스전체보기"></a></span></li>
            </ul>
		</div>
		 //gnb -->

	
	<!-- gnb
	<div id="gnb">
		<ul id='subGnb' class="gnbAni">
		  <li class="Menu_1"><a href="<c:url value='/'/>ngi/info/NgiAboutSite.do">시스템안내</a></li>
		  <li class="Menu_1"><a href="<c:url value='/'/>ngi/mng/NgiMngView.do">지형지물변동관리</a></li>
		  <li class="Menu_1"><a href="<c:url value='/'/>ngi/ntc/NgiNoticeService.do">알림서비스</a></li>
		  <li class="Menu_1"><a href="<c:url value='/'/>cop/bbs/selectBoardList.do?bbsId=BBSMSTR_AAAAAAAAAAAA">커뮤니티</a></li>
		  <li class="Menu_1"><a href="<c:url value='/'/>ngi/api/NgiApiIntroduce.do">연계서비스</a></li>
		  <li class="Menu_1"><span><a href="#">서비스전체보기<img src="<c:url value='/'/>images/common/top_menu_all.gif" alt="서비스전체보기" ></a></span></li>
		</ul>
	</div>
 //gnb -->

</div>

