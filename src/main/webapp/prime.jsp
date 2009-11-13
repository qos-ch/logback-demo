<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<HTML>
<head>
	<LINK REL=StyleSheet HREF="css/pk.css" />
</head>
<TITLE>Prime</TITLE>
<BODY>
<h2>Prime numbers</h2>
<%@include file="includes/menu.jsp"%>


<div class="content">

<P>Please enter a number</P>
<html:form action="/Prime">
<html:errors /> 

<p><html:text size="15" property="number"></html:text></p>

<p><html:submit>Submit</html:submit></p>
</html:form>

<p>The following numbers are divisible by 2 and a rather large prime, 400000066, 4000000000006</p>
</div>
</BODY>
</HTML>
