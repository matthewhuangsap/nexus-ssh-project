package it.nexus.webcontrol.views.jsp.ui;

import com.opensymphony.xwork2.util.ValueStack;
import it.nexus.webcontrol.components.Pages;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-4-27
 * Time: 15:05:22
 * To change this template use File | Settings | File Templates.
 */
public class PageTag extends ComponentTagSupport {
    private String cpage;
    private String total;
    private String url;

    public String getCpage() {
        return cpage;
    }

    public void setCpage(String cpage) {
        this.cpage = cpage;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new Pages(stack,req,res);
    }

//获得参数

    protected void populateParams() {
        super.populateParams();

        Pages pages = (Pages) component;
        pages.setCpage(cpage);
        pages.setTotal(total);
        pages.setUrl(url);
    }

}
