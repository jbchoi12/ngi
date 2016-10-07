<%--
  Class Name : EgovNormalCalPopup.jsp
  Description : EgovNormalCalPopup 화면(system)
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2009.04.01  이중호          최초 생성
     2011.08.31  JJY       경량환경 버전 생성
 
    author   : 공통서비스개발팀 이중호
    since    : 2009.04.01
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>일반달력</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<script type="text/javascript">
</script>
<style TYPE="text/css">
form {
margin :0px 0px 0px 0px;
}
iframe {
margin :0px 0px 0px 0px;
}
</style>
</head>
<body style="margin :0px;">
<iframe name="ifcal" src="<c:url value='/sym/cmm/EgovselectNormalCalendar.do'/>" style="width:275px; height:200px;border:0px;overflow:hidden;" title="달력호출"></iframe>
</body>
</html>