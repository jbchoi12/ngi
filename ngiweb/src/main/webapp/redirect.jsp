<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>

	
	<% 
	if(request.getParameter("token") == null){
		//response.sendRedirect("http://www.ngii.go.kr/auth/authLogin.do");
		
		String url = request.getRequestURL().toString();
		if ( request.getQueryString() != null )
			url = url + "?" + request.getQueryString();

		String authLoginUrl = "http://www.ngii.go.kr/kor/logIn/logIn.do"
				+ "?rbsIdx=3"
				+ "&toUrl=http%3A%2F%2Fwww.ngii.go.kr%2Fkor%2Fmain%2Fredirect.do%3FrbsIdx=1%26path="
				+ URLEncoder.encode( URLEncoder.encode( url, "UTF-8"),"UTF-8");
		
		response.sendRedirect(authLoginUrl);

	}else{		
		response.sendRedirect("uat/sso/loginPostProcess.do?token="+request.getParameter("token"));
	}
	%>	
		

	