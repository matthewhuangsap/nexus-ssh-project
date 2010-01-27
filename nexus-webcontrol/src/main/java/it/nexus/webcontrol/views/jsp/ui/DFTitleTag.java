package it.nexus.webcontrol.views.jsp.ui;

import it.nexus.webcontrol.components.DFTitle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractUITag;

import com.opensymphony.xwork2.util.ValueStack;

public class DFTitleTag extends AbstractUITag {
	private static final long serialVersionUID = 1L;
	private String dmoID;

	public String getDmoID() {
		return dmoID;
	}

	public void setDmoID(String dmoID) {
		this.dmoID = dmoID;
	}

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req,
			HttpServletResponse res) {
		return new DFTitle(stack,req,res);
	}

	@Override
	protected void populateParams() {
		super.populateParams();
		DFTitle title = (DFTitle) component;
		title.setId(id);
		title.setName(name);
		title.setLabel(label);
		title.setDmoID(dmoID);
	}
}
