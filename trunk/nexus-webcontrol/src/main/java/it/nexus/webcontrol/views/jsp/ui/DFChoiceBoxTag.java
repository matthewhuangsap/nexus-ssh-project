package it.nexus.webcontrol.views.jsp.ui;

import it.nexus.webcontrol.components.DFChoiceBox;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractUITag;

import com.opensymphony.xwork2.util.ValueStack;

public class DFChoiceBoxTag extends AbstractUITag {
	private static final long serialVersionUID = 1L;
	private String action;
	private String value;
	private String displayValue;
	private String datakind;

	public String getDatakind() {
		return datakind;
	}

	public void setDatakind(String datakind) {
		this.datakind = datakind;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req,
			HttpServletResponse res) {
		return new DFChoiceBox(stack, req, res);
	}

	@Override
	protected void populateParams() {
		super.populateParams();
		DFChoiceBox choiceBox = (DFChoiceBox) component;
		choiceBox.setId(id);
		choiceBox.setLabel(label);
		choiceBox.setName(name);
		choiceBox.setAction(action);
		choiceBox.setDatakind(datakind);
		choiceBox.setValue(value);
		choiceBox.setDisplayValue(displayValue);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

}
