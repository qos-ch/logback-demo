package ch.qos.logback.demo;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.MDC;

public class UserServletFilter implements Filter {

  boolean userRegistered = false;

  public void destroy() {
  }

  public void doFilter(ServletRequest request, ServletResponse response,
                       FilterChain chain) throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) request;
    Principal principal = req.getUserPrincipal();
    // Please note that we could have also used a cookie to
    // retrieve the user name

    HttpSession session = req.getSession();
    MDC.put("sessionId", session.getId());
    updateMDCValues(session);

    try {
      // invoke subsequent filters
      chain.doFilter(request, response);
    } finally {
       // always clear the MDC at the end of the request
      MDC.clear();
    }
  }

  public void init(FilterConfig arg0) throws ServletException {
  }

  private void updateMDCValues(HttpSession session) {
    updateMDCSingleValue(session, Constants.USERID_SESSION_KEY, Constants.USERID_MDC_KEY);
    updateMDCSingleValue(session, Constants.EMAIL_SESSION_KEY, Constants.EMAIL_MDC_KEY);
  }

  private void updateMDCSingleValue(HttpSession session, String sessionKey, String mdcKey) {
    String val = (String)session.getAttribute(sessionKey);
    MDC.put(mdcKey, val);
  }

}
