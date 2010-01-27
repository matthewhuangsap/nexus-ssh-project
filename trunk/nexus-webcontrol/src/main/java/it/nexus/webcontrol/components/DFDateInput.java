package it.nexus.webcontrol.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.UIBean;

import com.opensymphony.xwork2.util.ValueStack;

public class DFDateInput extends UIBean {
	final public static String TEMPLATE = "dfdateinput";

	public DFDateInput(ValueStack stack, HttpServletRequest request,
			HttpServletResponse response) {
		super(stack, request, response);
	}

	@Override
	protected String getDefaultTemplate() {
		return TEMPLATE;
	}

	@Override
	protected void evaluateExtraParams() {
		super.evaluateExtraParams();
		if (this.name != null)
			addParameter("name", findString(this.name));
		if (this.id != null)
			addParameter("id", findString(this.id));
		if (this.label != null)
			addParameter("label", findString(this.label));
		Object currentValue = null;
		if (parameters.containsKey("nameValue")) {
			currentValue = parameters.get("nameValue");
		} else if (parameters.containsKey("name")) {
			currentValue = findValue((String) parameters.get("name"));
		}

		if (currentValue != null) {
			addParameter("displayValue", currentValue.toString());
		}
	}
}
