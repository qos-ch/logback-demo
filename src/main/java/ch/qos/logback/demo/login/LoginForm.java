package ch.qos.logback.demo.login;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;


public class LoginForm extends ActionForm {

  private static final long serialVersionUID = 0L;

  private String username;
  private String email;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  
  public ActionErrors validate ( 
      ActionMapping mapping, HttpServletRequest request ) {
      ActionErrors errors = new ActionErrors();
      
      if( getUsername() == null || getUsername().trim().length() == 0 ) {
        errors.add("username",new ActionMessage("errors.username.invalid", getUsername()));
      }
      if( getEmail() == null || getEmail().trim().length() == 0 ) {
        errors.add("email",new ActionMessage("errors.email.invalid", getUsername()));
      }
      return errors;
  }

}
