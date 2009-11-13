<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<html>
<head>
	<LINK REL=StyleSheet HREF="css/pk.css" />
</head>
<body>

<h2>Logback demo center</h2>
<%@include file="includes/menu.jsp" %>

<div class="content">

<logic:notEmpty name="<%=Constants.USERID_SESSION_KEY %>">
<p>
	Hi there, <bean:write name="<%=Constants.USERID_SESSION_KEY %>" /> !
</p>
</logic:notEmpty>
<p>
	Welcome to the logback demo.
</p>
<p>
	The <b>Login</b> page lets you enter a username, which will be associated to the
	session. This can be used to test the filter functionnalities of logback.
	To tweak logback classic's configuration, modify the <em>/src/resources/logback.xml</em> file.
</p>
<p>
	The <b>ViewStatii</b> page lets you view the logback statuses. Status objects are logback's internal
	error reporting components. Viewing them allows you to see if logback's initialization has gone wrong, or
	verify that your configuration of logback behaves like you expect it to.
</p>
<p>
	<b>View logs</b> is a page that shows the last 512 events that were reported to logback classic.
	This number is configurable in the previously mentionned configuration file. The <b>View logs</b> page
	uses <code>HTMLLayout</code> to format the events in a easily readable manner.
</p>
<p>
	<b>Play the lottery</b> will allow you to enter a number and maybe win the logback's Grand Prize.
	Each time somebody enters the winning number, logback access catches the request url and
	sends an email according to its configuration file, <em>/src/etc/logback-access.xml</em>.
</p>
<p>
	The <b>Prime number</b> page lets you enter any number, and let the webapp search for its divisors.
</p>
<p>
	Use <b>Reload config</b> to reset logback and read again its configuration file, enabling
	your modifications.
</p>
</div>
</body>
</html>
