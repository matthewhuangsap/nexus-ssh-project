package it.nexus.webcontrol.views.jsp.ui;

import it.nexus.webcontrol.components.DFHead;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractUITag;

import com.opensymphony.xwork2.util.ValueStack;
@SuppressWarnings("unused")
public class DFHeadTag extends AbstractUITag {
	private static final long serialVersionUID = 1L;
	
	private String compressed;
    private String baseRelativePath;
    private String locale;
    private String uiTheme;
    
	public void setCompressed(String compressed) {
		this.compressed = compressed;
	}

	public void setBaseRelativePath(String baseRelativePath) {
		this.baseRelativePath = baseRelativePath;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public void setUiTheme(String uiTheme) {
		this.uiTheme = uiTheme;
	}

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req,
			HttpServletResponse res) {
		return new DFHead(stack,req,res);
	}
	
	protected void populateParams() {
        super.populateParams();
        
        DFHead head = (DFHead) component;
        head.setCompressed(compressed);
        head.setBaseRelativePath(baseRelativePath);
        head.setLocale(locale);
        head.setUiTheme(uiTheme);
    }

	

}
