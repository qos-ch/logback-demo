package ch.qos.logback.demo.lottery;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;


public class LotteryForm extends ActionForm {

  private static final long serialVersionUID = 0L;

  private Integer guessed_number;

  public Integer getGuessed_number() {
    return guessed_number;
  }

  public void setGuessed_number(Integer guessed_number) {
    this.guessed_number = guessed_number;
  }
  
  public ActionErrors validate( 
      ActionMapping mapping, HttpServletRequest request ) {
      ActionErrors errors = new ActionErrors();
      
      if( getGuessed_number() == null || getGuessed_number().intValue() < 1 ) {
        errors.add("number",new ActionMessage("errors.minNumber"));
      }

      return errors;
  }
}
