<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<HTML>
<head>
	<LINK REL="StyleSheet" HREF="css/pk.css" />
</head>
<TITLE>Logout</TITLE>
<BODY>
<h2>Logout</h2>
<%@include file="includes/menu.jsp"%>

<%
String user = (String)request.getAttribute(Constants.USERID_SESSION_KEY);
String display;
if (user == null || user.trim().length() == 0) {
  display = "No user were connected";
} else {
  display = "User " + user + " was logged out successfully";
}
%>

<div class="content">
<p><%=display %></p>
</div>
</BODY>
</HTML>
