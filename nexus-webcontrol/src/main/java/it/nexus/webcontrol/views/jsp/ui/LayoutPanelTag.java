package it.nexus.webcontrol.views.jsp.ui;

import com.opensymphony.xwork2.util.ValueStack;
import it.nexus.webcontrol.components.LayoutPanel;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractClosingTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-4-17
 * Time: 14:57:02
 * To change this template use File | Settings | File Templates.
 */
public class LayoutPanelTag extends AbstractClosingTag {

  @Override
  public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
    return new LayoutPanel(stack, req, res);
  }

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

  public void setCols(String cols) {
    this.cols = cols;
  }

  @Override
	protected void populateParams() {
		super.populateParams();
		LayoutPanel panel = (LayoutPanel) component;
		panel.setId(id);
		panel.setName(name);
		panel.setCols(cols);
    panel.setHeight(height);
    panel.setFieldHeight(fieldHeight);
	}
}
