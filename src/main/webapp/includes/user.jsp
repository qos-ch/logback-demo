<%@page import="ch.qos.logback.demo.Constants"%>
<%@page import="org.slf4j.MDC"%>
<%
String username = (String)session.getAttribute(Constants.USERID_SESSION_KEY);
String text;
if (username == null) {
  text = "No user logged in.";
} else {
  text = "User: " + username;
}
%>


<%
String mdcUserid  = MDC.get(Constants.USERID_MDC_KEY);
String mdcText;
if (mdcUserid == null) {
  mdcText = "MDC userid is null";
} else {
  mdcText = "MDC userid is " + mdcUserid;
}
%>


<%=text %>
<br/>
<%=mdcText %>