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
      String email = form.getEmail();

      if (username != null) {
        MDC.put(Constants.USERID_MDC_KEY, username);
        MDC.put(Constants.EMAIL_MDC_KEY, email);
        logger.info("Login: user {} with email {} just logged in.", username, email);
        request.getSession().setAttribute(Constants.USERID_SESSION_KEY, username);
        request.getSession().setAttribute(Constants.EMAIL_SESSION_KEY, email);
      }
    }

    return actionMapping.findForward("next");
  }

}
