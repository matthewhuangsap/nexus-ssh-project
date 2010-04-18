package it.nexus.webcontrol.views.freemaker.tags;

import com.opensymphony.xwork2.util.ValueStack;
import it.nexus.webcontrol.components.LayoutPanel;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.freemarker.tags.TagModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-4-17
 * Time: 15:07:16
 * To change this template use File | Settings | File Templates.
 */
public class LayoutPanelModel  extends TagModel {
    public LayoutPanelModel(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        super(stack, req, res);
    }

    protected Component getBean() {
        return new LayoutPanel(stack, req, res);
    }
}
