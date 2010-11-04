package ch.qos.logback.demo.statii;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.qos.logback.classic.util.ContextSelectorStaticBinder;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.demo.Constants;
import ch.qos.logback.demo.util.EnhancedStatusPrinter;

public class ChooseContextAction extends Action {

  Logger logger = LoggerFactory.getLogger(ChooseModuleAction.class);

  public ActionForward execute(ActionMapping actionMapping,
      ActionForm actionForm, HttpServletRequest request,
      HttpServletResponse response) throws Exception {

    StringBuffer buf = new StringBuffer();
    
    if (actionForm != null) {
      ChooseContextForm form = (ChooseContextForm) actionForm;

      String contextName = form.getContextName();
      LoggerContext context = ContextSelectorStaticBinder.getSingleton().getContextSelector().getLoggerContext(contextName);
      EnhancedStatusPrinter.print(buf, context.getStatusManager());
      request.setAttribute(Constants.STATUS, buf.toString());
    }
    
    List<String> contextNames = ContextSelectorStaticBinder.getSingleton().getContextSelector().getContextNames();
    request.setAttribute(Constants.CONTEXT_LIST, contextNames);
    
    return actionMapping.findForward("next");
  }
}