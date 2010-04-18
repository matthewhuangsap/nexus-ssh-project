package it.nexus.webcontrol.components;

import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.components.ClosingUIBean;
import org.apache.struts2.components.UIBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-4-16
 * Time: 14:23:43
 * To change this template use File | Settings | File Templates.
 */
public class LayoutPanel extends ClosingUIBean {
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
}
