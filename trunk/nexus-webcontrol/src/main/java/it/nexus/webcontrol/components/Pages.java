package it.nexus.webcontrol.components;

import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.components.UIBean;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-4-27
 * Time: 15:13:13
 */
@StrutsTag(name = "pages", description = ""
    , tldBodyContent = "empty"
    , tldTagClass = "it.nexus.webcontrol.views.jsp.ui.PageTag")
public class Pages extends UIBean {
  private String cpage;
  private String total;
  private String url;
  private static final String TEMPLETE = "pager.ftl";

  public String getCpage() {
    return cpage;
  }

  @StrutsTagAttribute(description = "Set Layout Panel's cols number attribute")
  public void setCpage(String cpage) {
    this.cpage = cpage;
  }

  public String getTotal() {
    return total;
  }

  @StrutsTagAttribute(description = "Set Layout Panel's cols number attribute")
  public void setTotal(String total) {
    this.total = total;
  }

  public String getUrl() {
    return url;
  }

  @StrutsTagAttribute(description = "Set Layout Panel's cols number attribute")
  public void setUrl(String url) {
    this.url = url;
  }

  public Pages(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
    super(stack, request, response);
  }

  @Override
  protected String getDefaultTemplate() {
    return TEMPLETE;
  }

  @Override
  protected void evaluateExtraParams() {
    super.evaluateExtraParams();
    if (this.name != null)
      addParameter("name", findString(this.name));
    if (this.id != null)
      addParameter("id", findString(this.id));
    if (this.cpage != null)
      addParameter("cpage", findString(this.cpage));
    if (this.total != null)
      addParameter("total", findString(this.total));
    if (this.url != null)
      addParameter("url", findString(this.url));

  }
}
