<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>eGovFrame 템플릿</title>

<link rel="stylesheet" href="<c:url value='/'/>css/default.css" type="text/css" >

<script type="text/javascript">
function fncGoAfterErrorPage(){
    history.back(-2);
}
</script>
</head>

<body>
<table style="width:100%;border:0px;border-spacing:0px;">
  <tr>
    <td><br />
    <br />
    <br />
    <table style="width:600px;border:0px;border-spacing:0px;background-image: url('er_images/blue_bg.jpg');">
      <tr>
        <td style="text-align:center;"><table style="width:100%;border:0px;border-spacing:0px;">
          <tr>
            <td style="background-color:#FFFFFF;"><table style="width:100%;border:0px;border-spacing:0px;">
              <tr>
                <td style="text-align:left;"><img src="<c:url value='/images/egovframework/com/cmm/er_logo.jpg' />" width="379" height="57" /></td>
              </tr>
              <tr>
                <td><br />
                  <br /></td>
              </tr>
              <tr>
                <td style="text-align:center;"><table style="width:520px;border:0px;border-spacing:0px;">
                  <tr>
                    <td style="width:74px;text-align:center;"><img src="<c:url value='/images/egovframework/com/cmm/danger.jpg' />" width="74" height="74" /></td>
                    <td style="width:399px;text-align:left;" class="lt_text2">수행중 오류가 발생하였습니다. </td>
                  </tr>
                </table>
                  <table style="width:500px;border:0;border-spacing:0;">
                                  </table></td>
              </tr>
              <tr>
                <td><br />
                  <br /></td>
              </tr>
              <tr>
                <td style="text-align:center;"><a href="javascript:fncGoAfterErrorPage();"><img src="<c:url value='/images/egovframework/com/cmm/go_history.jpg' />" width="90" height="29" alt="뒤로가기"/></a></td>
              </tr>
            </table>
              <br /></td>
          </tr>
        </table></td>
      </tr>
    </table>
    </td>
  </tr>
</table>
</body>
</html>
