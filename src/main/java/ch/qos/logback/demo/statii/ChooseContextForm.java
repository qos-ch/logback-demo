package ch.qos.logback.demo.statii;

import org.apache.struts.action.ActionForm;


public class ChooseContextForm extends ActionForm {

  private static final long serialVersionUID = 0L;

  private String contextName = "default";

  public String getContextName() {
    return contextName;
  }

  public void setContextName(String contextName) {
    this.contextName = contextName;
  }
}
