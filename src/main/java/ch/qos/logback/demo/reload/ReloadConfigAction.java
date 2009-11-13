package ch.qos.logback.demo.reload;

import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.access.jetty.RequestLogImpl;
import ch.qos.logback.access.jetty.RequestLogRegistry;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.Loader;
import ch.qos.logback.demo.Constants;

public class ReloadConfigAction extends Action {

  final public static String CLASSIC_FILE = "logback.xml";
  
  Logger logger = LoggerFactory.getLogger(ReloadConfigAction.class);

  public ActionForward execute(ActionMapping actionMapping,
      ActionForm actionForm, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    
    boolean reload_successfull = false;
    
    if (actionForm != null) {
      ReloadConfigForm form = (ReloadConfigForm) actionForm;

      String moduleName = form.getModuleName();
      if (moduleName != null && moduleName.equals(Constants.ACCESS)) {
        logger.debug("Reloading access module");
        reload_successfull = reloadAccessConfiguration();
      } else {
        logger.debug("Reloading classic module");
        reload_successfull = reloadClassicConfiguration();
      }
    }
    
    if (reload_successfull) {
      request.setAttribute(Constants.RELOAD_RESULT, "Success");
    } else {
      request.setAttribute(Constants.RELOAD_RESULT, "Failure");
    }
    
    return actionMapping.findForward("next");
  }
  
  private boolean reloadClassicConfiguration() {
    try {
      LoggerContext lc = (LoggerContext)LoggerFactory.getILoggerFactory();
      URL url = Loader.getResource(CLASSIC_FILE, Loader.getTCL());
      if (url != null) {
        logger.debug("Found ressource at: " + url.getFile());
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(lc);
        logger.info("Shutting down active logging configuration.");

        lc.reset();
        configurator.doConfigure(url);
        lc.start();
        logger.info("Now using new configuration.");
        return true;
      } else {
        //error no config file found
        return false;
      }
    } catch (JoranException je) {
      je.printStackTrace();
      return false;
    }
  }
  
  private boolean reloadAccessConfiguration() {
    RequestLogImpl requestLog = RequestLogRegistry.get(Constants.REQUESTLOG_NAME);
    requestLog.stop();
    requestLog.start();
    if (requestLog.isStarted()) {
      return true;
    }
    return false;
  }
  
}