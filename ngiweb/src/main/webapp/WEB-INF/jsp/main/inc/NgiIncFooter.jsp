<%--
  Class Name : EgovIncFooter.jsp
  Description : 화면하단 Footer(include)
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2011.08.31   JJY       경량환경 버전 생성
 
    author   : 실행환경개발팀 JJY
    since    : 2011.08.31 
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<div class="footer">
        <div class="link01">
            <a href="#"><img src="<c:url value='/'/>images/common/copy_logo_symbol.gif" alt="지형지물변동관리시스템"></a>
        </div>
    
        <div class="link02"><img src="<c:url value='/'/>images/common/copy_ngii.gif" alt="국토지리정보원"></div>
        <div class="copyright">
            <p class="addr">(우)443-772 경기도 수원시 영통구 월드컵로 92 (원천동)    전화 031.210.2600    팩스 031.210.2644</p>
            <p class="copy">COPYRIGHT 2014 <span>NGII.</span> ALL RIGHTS RESERVED.</p>
        </div>
	</div>