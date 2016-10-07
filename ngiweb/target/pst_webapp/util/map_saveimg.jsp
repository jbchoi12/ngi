<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="java.io.*"%>
<%@page import="javax.servlet.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%
	String img = null;
	if (request.getMethod().equals("POST")) {
		img = request.getParameter("imgdata") != null ? request.getParameter("imgdata") : "";
	} else {
		return;
	}
	response.setContentType("image/png");
	response.setHeader("Content-Disposition", "attachment;filename=map_save.png");
	response.setHeader("Content-Transfer-Encoding", "binary");
	byte[] decoded = Base64.decodeBase64(img.getBytes());
	
	try{  
		out.clear();
		out = pageContext.pushBody();
	    OutputStream outStream = response.getOutputStream();  
	    outStream.write(decoded);  
	    outStream.flush();   
	    outStream.close();    
	 }catch(IOException ioe){  
	   //throw ioe;//TODO: add logging and tracing...  
	 } 
	/* out.println(decoded); */
%>
<script>
opener.focus();
window.close();
</script>