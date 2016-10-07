<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String img = null;
	if (request.getMethod().equals("POST")) {
		img = request.getParameter("imgdata") != null ? request.getParameter("imgdata") : "";
		img = img.replaceAll("<","&lt;");
		img = img.replaceAll(">","&gt;");
		img = img.replaceAll("&","&amp;");
		img = img.replaceAll("\"","&quot;");
	} else {
		return;
	}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="/js/2dmap/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
window.print();setTimeout("window.close();", 200);
</script>
<title>국토변화정보포털시스템</title>
</head>
<body>
<div><p id="image"><img src="data:image/png;base64,<%=img %>" alt="map_save" border="0"></p></div>
</body>
</html>