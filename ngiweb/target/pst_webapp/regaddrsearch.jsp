<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>주소검색</title>
<style type="text/css">
</style>
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngicommon.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>css/ngimap.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXJ.min.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/'/>js/axisj/ui/arongi/AXInput.css">
<link href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<script src="<c:url value='/'/>js/map/apikey.js"></script>
<script src="<c:url value='/'/>js/axisj/jquery/jquery.min.js"></script>
<script src="<c:url value='/'/>js/map/regaddrsearch.js"></script>
</head>
<body>
<div id="regAddrSearch_pop">
	<div style="margin:15px;">
		&nbsp;&nbsp;<input type="text" id="search-input"   class="AXInput W150" />
		<a href="#" id="search-go"><button type="button" class="AXButtonSmall Blue"><i class="fa fa-search fa-1"></i> Search</button></a>
	</div>
    <div class="SearchList" id="SearchList">  <!-- 검색결과 -->
	  <p class="SerarchTt" id="SerarchTt"></p>
	  <ul class="MarT30"  id="SerarchLi"></ul>
	</div>
   	<div id="show_search_page"></div>
</div>
</body>
</html>