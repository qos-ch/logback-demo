package ch.qos.logback.demo.lottery;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.demo.Constants;

public class LotteryAction extends Action {

  Logger logger = LoggerFactory.getLogger(LotteryAction.class);

  public ActionForward execute(ActionMapping actionMapping,
      ActionForm actionForm, HttpServletRequest request,
      HttpServletResponse response) throws Exception {

    if (actionForm != null) {
      LotteryForm form = (LotteryForm) actionForm;

      Integer guessedNumber = form.getGuessed_number();

      logger.info("Number: " + guessedNumber + " was tried.");
      if (guessedNumber.equals(Constants.WINNING_NUMBER)) {
        logger.info("The Grand Prize was won!");
        return actionMapping.findForward("win");
      } else {
        request.setAttribute(Constants.GUESSED_NUMBER, guessedNumber);
        return actionMapping.findForward("loose");
      }
    }
    
    return actionMapping.findForward("loose");
  }
}