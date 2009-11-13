package ch.qos.logback.demo.reload;

import org.apache.struts.action.ActionForm;

import ch.qos.logback.demo.Constants;


public class ReloadConfigForm extends ActionForm {

  private static final long serialVersionUID = 0L;

  private String moduleName = Constants.CLASSIC;

  public String getModuleName() {
    return moduleName;
  }

  public void setModuleName(String moduleName) {
    this.moduleName = moduleName;
  }
  
  
}
