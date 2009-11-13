package ch.qos.logback.demo.prime;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;


public class PrimeForm extends ActionForm {

  private static final long serialVersionUID = 0L;

  Long number;

  public Long getNumber() {
    return number;
  }

  public void setNumber(Long number) {
    this.number = number;
  }
  
  public ActionErrors validate( 
      ActionMapping mapping, HttpServletRequest request ) {
      ActionErrors errors = new ActionErrors();
      
      if( getNumber() == null || ((getNumber().longValue()) != -1 &&  (getNumber().longValue() < 1))) {
        errors.add("number",new ActionMessage("errors.minNumber"));
      }

      return errors;
  }
}
