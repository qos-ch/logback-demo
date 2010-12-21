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
    MDC.put("txId", session.getId());
    if (principal != null) {
      String username = principal.getName();
      registerUsername(username);
    } else {
      String value = (String) session
              .getAttribute(Constants.USERID_SESSION_KEY);
      registerUsername(value);
    }

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

  private void registerUsername(String value) {
    System.out.println("in registerUsername " + Constants.USERID_MDC_KEY + "=" + value);
    if (value != null && value.trim().length() > 0) {
      MDC.put(Constants.USERID_MDC_KEY, value);
      MDC.put("txEmail", value+"+log@qos.ch");
      userRegistered = true;
    }
  }

}
