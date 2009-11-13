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
<h2>Too bad...</h2>
<%@include file="includes/menu.jsp"%>

<div class="content">
<p>Oops, <bean:write name="<%=Constants.GUESSED_NUMBER %>"/> is not a winning number...</p>
<table><tr><td class="sexy"><a href="lottery.jsp" class="sexy">Play again!</a></td></tr></table>
</div>
</BODY>
</HTML>