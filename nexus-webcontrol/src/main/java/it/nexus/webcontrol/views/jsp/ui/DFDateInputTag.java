package it.nexus.webcontrol.views.jsp.ui;

import it.nexus.webcontrol.components.DFDateInput;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractUITag;

import com.opensymphony.xwork2.util.ValueStack;

public class DFDateInputTag extends AbstractUITag {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req,
			HttpServletResponse res) {
		return new DFDateInput(stack,req,res);
	}
	
	@Override
	protected void populateParams() {
		super.populateParams();
		DFDateInput dateInput = (DFDateInput)component;
		dateInput.setId(id);
		dateInput.setLabel(label);
		dateInput.setName(name);
	}
}
