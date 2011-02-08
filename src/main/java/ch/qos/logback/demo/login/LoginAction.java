package ch.qos.logback.demo.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.slf4j.MDC;
import ch.qos.logback.demo.Constants;

public class LoginAction extends Action {

  Logger logger = LoggerFactory.getLogger(LoginAction.class);

  public ActionForward execute(ActionMapping actionMapping,
      ActionForm actionForm, HttpServletRequest request,
      HttpServletResponse response) throws Exception {

    if (actionForm != null) {
      LoginForm form = (LoginForm) actionForm;

      String username = form.getUsername();
      if (username != null) {
        MDC.put(Constants.USERID_MDC_KEY, username);
        MDC.put(Constants.USERID_MDC_KEY, username+"+log@gmail.com");
        logger.info("Login: " + username + " just logged in.");
        request.getSession().setAttribute(Constants.USERID_SESSION_KEY, username);
      }
    }

    return actionMapping.findForward("next");
  }

}
