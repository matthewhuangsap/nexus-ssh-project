package it.nexus.webcontrol.components;

import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.components.ClosingUIBean;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-4-16
 * Time: 14:23:43
 * To change this template use File | Settings | File Templates.
 */
@StrutsTag(name = "layoutpanel", tldBodyContent = "JSP",
    tldTagClass = "it.nexus.webcontrol.views.jsp.ui.LayoutPanelTag",
    description = "Layout Panel For Form Layout", allowDynamicAttributes = true)
public class LayoutPanel extends ClosingUIBean {

  private String cols;
  private String height;
  private String fieldHeight;

  public String getFieldHeight() {
    return fieldHeight;
  }

  public void setFieldHeight(String fieldHeight) {
    this.fieldHeight = fieldHeight;
  }

  public String getHeight() {
    return height;
  }

  public void setHeight(String height) {
    this.height = height;
  }

  public String getCols() {
    return cols;
  }

  @StrutsTagAttribute(description = "Set Layout Panel's cols number attribute")
  public void setCols(String cols) {
    this.cols = cols;
  }

  public static final String TEMPLATE = "layoutpanel";
  public static final String TEMPLATE_CLOSE = "layoutpanel-close";
  public static final String COMPONENT_NAME = LayoutPanel.class.getName();

  public LayoutPanel(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
    super(stack, request, response);
  }

  @Override
  public String getDefaultOpenTemplate() {
    return TEMPLATE;
  }

  @Override
  protected String getDefaultTemplate() {
    return TEMPLATE_CLOSE;
  }

  @Override
  protected void evaluateExtraParams() {
    super.evaluateExtraParams();
    if (null != id)
      addParameter("id", findString(id));
    if (null != name)
      addParameter("name", findString(name));
    if (null != label)
      addParameter("label", findString(label));
    if (null != cols)
      addParameter("cols", findString(cols));
    if (null != height)
      addParameter("height", findString(height));
    if (null != fieldHeight)
      addParameter("fieldHeight", findString(fieldHeight));
  }
}
