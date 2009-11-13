<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html>
<head>
	<LINK REL=StyleSheet HREF="css/pk.css" />
	<!-- its more ergonomical to click on the submit button to trigger a reload -->
</head>
<body>

<h2>Reload configuration</h2>
<%@include file="includes/menu.jsp" %>


<div class="content_full">
<html:form action="/ReloadConfig">
<html:errors /> 

<p><html:select property="moduleName">
<html:option value="<%=Constants.ACCESS %>"><%=Constants.ACCESS %></html:option>
<html:option value="<%=Constants.CLASSIC %>"><%=Constants.CLASSIC %></html:option>
</html:select><html:submit>Reload</html:submit></p>
</html:form>

<p>
<logic:notEmpty name="<%=Constants.RELOAD_RESULT %>">
<p>
	Status of reloading: <bean:write name="<%=Constants.RELOAD_RESULT %>" />
</p>
</logic:notEmpty>

</div>
</body>
</html>
