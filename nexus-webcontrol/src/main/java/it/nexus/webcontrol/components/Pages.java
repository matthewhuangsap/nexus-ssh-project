package it.nexus.webcontrol.components;

import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.components.UIBean;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;


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

//  @Override
//  public boolean start(Writer writer) {
//    boolean result = super.start(writer);
//    try {
//      StringBuilder str = new StringBuilder();
//      boolean isValid = true;
//
//      //从ValueStack中取出数值
//      if (isValid) {
//        if (total.startsWith("%{") && total.endsWith("}")) {
//          total = total.substring(2, total.length() - 1);
//          total = (String) this.getStack().findValue(total);
//          isValid = total == null ? false : true;
//        } else {
//          isValid = false;
//        }
//      }
//      if (isValid) {
//        if (cpage.startsWith("%{") && cpage.endsWith("}")) {
//          cpage = cpage.substring(2, cpage.length() - 1);
//          cpage = (String) this.getStack().findValue(cpage);
//          isValid = cpage == null ? false : true;
//        } else {
//          isValid = false;
//        }
//      }
//      if (isValid) {
//        if (url.startsWith("%{") && url.endsWith("}")) {
//          url = url.substring(2, url.length() - 1);
//          url = (String) this.getStack().findValue(url);
//          isValid = url == null ? false : true;
//        } else {
//          isValid = false;
//        }
//      }
//
//      if (isValid) {
//        Integer cpageInt = Integer.valueOf(cpage);
//        //当前页与总页数相等
//        if (cpage.equals(total)) {
//          //如果total = 1，则无需分页，显示“[第1页] [共1页]”
//          if ("1".equals(total)) {
//            str.append("[第 " + cpage + " 页]");
//            str.append(" [共 " + total + " 页]");
//          } else {
//            //到达最后一页,显示“[首页] [上一页] [末页]”
//            str.append("<a href='");
//            str.append(url);
//            str.append("?cpage=1&total=" + total + "&url=" + url);
//            str.append("'>[首页]</a> <a href='");
//            str.append(url);
//            str.append("?cpage=" + (cpageInt - 1) + "&total=" + total + "&url=" + url);
//            str.append("'>[上一页]</a> <a href='");
//            str.append(url);
//            str.append("?cpage=" + total + "&total=" + total + "&url=" + url);
//            str.append("'>[末页]</a>");
//          }
//        } else {
//          //当前页与总页数不相同
//          if ("1".equals(cpage)) {
//            //第一页，显示“[首页] [下一页] [末页]”
//            str.append("<a href='");
//            str.append(url);
//            str.append("?cpage=1&total=" + total + "&url=" + url);
//            str.append("'>[首页]</a> <a href='");
//            str.append(url);
//            str.append("?cpage=" + (cpageInt + 1) + "&total=" + total + "&url=" + url);
//            str.append("'>[下一页]</a> <a href='");
//            str.append(url);
//            str.append("?cpage=" + total + "&total=" + total + "&url=" + url);
//            str.append("'>[末页]</a>");
//          } else {
//            //不是第一页，显示“[首页] [上一页] [下一页] [末页]”
//            str.append("<a href='");
//            str.append(url);
//            str.append("?cpage=1&total=" + total + "&url=" + url);
//            str.append("'>[首页]</a> <a href='");
//            str.append(url);
//            str.append("?cpage=" + (cpageInt - 1) + "&total=" + total + "&url=" + url);
//            str.append("'>[上一页]</a> <a href='");
//            str.append(url);
//            str.append("?cpage=" + (cpageInt + 1) + "&total=" + total + "&url=" + url);
//            str.append("'>[下一页]</a> <a href='");
//            str.append(url);
//            str.append("?cpage=" + total + "&total=" + total + "&url=" + url);
//            str.append("'>[末页]</a>");
//          }
//        }
//      }
//
//      writer.write(str.toString());
//
//    } catch (IOException ex) {
//      Logger.getLogger(Pages.class.getName()).log(Level.SEVERE, null, ex);
//    }
//    return result;
//
//  }
}
