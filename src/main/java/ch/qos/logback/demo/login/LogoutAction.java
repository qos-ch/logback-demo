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

public class LogoutAction extends Action {

  Logger logger = LoggerFactory.getLogger(LogoutAction.class);

  public ActionForward execute(ActionMapping actionMapping,
      ActionForm actionForm, HttpServletRequest request,
      HttpServletResponse response) throws Exception {

    String username = (String)request.getSession().getAttribute(Constants.USERID_SESSION_KEY);
    
    MDC.remove(Constants.USERID_MDC_KEY);
    logger.info("User: " + username + " just logged out.");
    
    request.setAttribute(Constants.USERID_SESSION_KEY, username);
    request.getSession().removeAttribute(Constants.USERID_SESSION_KEY);
    
    return actionMapping.findForward("next");
  }

}
