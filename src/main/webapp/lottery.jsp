<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<HTML>
<head>
	<LINK REL="StyleSheet" HREF="css/pk.css" />
</head>
<TITLE>Lottery</TITLE>
<BODY>
<h2>Play the Lottery</h2>
<%@include file="includes/menu.jsp"%>
<div class="content">

<P>Please play a number.</P>
<html:form action="/Lottery">
<html:errors /> 

<p><html:text size="15" property="guessed_number"></html:text></p>

<p><html:submit>Submit</html:submit></p>
</html:form>
</div>
</BODY>
</HTML>
