<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<HTML>
<head>
	<LINK REL="StyleSheet" HREF="css/pk.css" />
</head>
<TITLE>Prime Results</TITLE>
<BODY>
<h2>Prime Results</h2>
<%@include file="includes/menu.jsp"%>

<div class="content">
<p>Number entered was: <bean:write name="<%=Constants.PRIME_NUMBER %>" />.</p>
<p>Results computed in <bean:write name="<%=Constants.PRIME_DURATION %>" /> ms.</p>

<table>
<logic:iterate id="result" name="<%=Constants.PRIME_RESULTS %>" scope="request">
<tr><td><bean:write name="result" /></td></tr>
</logic:iterate>
</table>

</div>
</BODY>
</HTML>
