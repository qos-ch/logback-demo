<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html>
<head>
	<LINK REL=StyleSheet HREF="css/pk.css" />
	<SCRIPT language="javascript" type="text/javascript">
		function moduleSubmit() {
		  document.chooseModuleForm.submit();
		}
  </SCRIPT>
</head>
<body>
<h2>Statii</h2>
<%@include file="includes/mini-menu.jsp" %>



<div class="statiiMenus">
<html:form action="/ChooseModule">
<html:errors /> 
<html:select property="moduleName" onchange="moduleSubmit();">
<html:option value="<%=Constants.ACCESS %>"><%=Constants.ACCESS %></html:option>
<html:option value="<%=Constants.CLASSIC %>"><%=Constants.CLASSIC %></html:option>
</html:select>
</html:form>

<logic:notEmpty name="<%=Constants.CONTEXT_LIST %>">
<html:form action="/ChooseContext">
<html:errors /> 
<html:select property="contextName">
<html:options name="<%=Constants.CONTEXT_LIST %>"  ></html:options>
</html:select>
<html:submit>Display</html:submit>
</html:form>
</logic:notEmpty>
</div>

<div class="content_full">

<logic:notEmpty name="<%=Constants.STATUS %>">
<pre>
<bean:write filter="false" name="<%=Constants.STATUS %>"/>
</pre>
</logic:notEmpty>

</div>
</body>
</html>
