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

import ch.qos.logback.access.jetty.RequestLogImpl;
import ch.qos.logback.access.jetty.RequestLogRegistry;
import ch.qos.logback.demo.Constants;
import ch.qos.logback.demo.util.EnhancedStatusPrinter;

public class ChooseModuleAction extends Action {

  Logger logger = LoggerFactory.getLogger(ChooseModuleAction.class);

  public ActionForward execute(ActionMapping actionMapping,
      ActionForm actionForm, HttpServletRequest request,
      HttpServletResponse response) throws Exception {

    StringBuffer buf = new StringBuffer();

    if (actionForm != null) {
      ChooseModuleForm form = (ChooseModuleForm) actionForm;

      String sm = form.getModuleName();
      if (sm != null && sm.equals(Constants.ACCESS)) {
//        try {
          RequestLogImpl requestLog = RequestLogRegistry.get(Constants.REQUESTLOG_NAME);
          if (requestLog != null) {
            EnhancedStatusPrinter.print(buf, requestLog.getStatusManager());
          } else {
            buf.append("Could not get " + Constants.REQUESTLOG_NAME);
          }
//        } catch(NoClassDefFoundError error) {
//          //in case the RequestLogRegistry is not available
//          buf.append("Could not get " + Constants.REQUESTLOG_NAME);
//        }
        
        request.setAttribute(Constants.STATUS, buf.toString());

      } else {
        List<String> contextNames = ContextSelectorStaticBinder.getSingleton().getContextSelector().getContextNames();
        request.setAttribute(Constants.CONTEXT_LIST, contextNames);
      }
    }

    return actionMapping.findForward("next");
  }
}