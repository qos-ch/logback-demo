package ch.qos.logback.demo.prime;

import ch.qos.logback.demo.Constants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrimeAction extends Action {

  Logger logger = LoggerFactory.getLogger(PrimeAction.class);

  public ActionForward execute(ActionMapping actionMapping,
      ActionForm actionForm, HttpServletRequest request,
      HttpServletResponse response) throws Exception {

    if (actionForm != null) {
      PrimeForm form = (PrimeForm) actionForm;

      Long number = form.getNumber();
      if(number == 99) {
    	  logger.info("99 is not a valid value", new Exception("99 is invalid"));
      }
      
      System.out.println("*** userid="+MDC.get(Constants.USERID_MDC_KEY));
      
      NumberCruncher nc = new NumberCruncherImpl();
      Long start = System.currentTimeMillis();
      Long[] result = nc.factor(number);
      Long duration = System.currentTimeMillis() - start;
      logger.info("Results computed in {} ms", duration);

      request.setAttribute(Constants.PRIME_NUMBER, number);
      request.setAttribute(Constants.PRIME_DURATION, duration);
      request.setAttribute(Constants.PRIME_RESULTS, result);
    }

    return actionMapping.findForward("next");
  }
}