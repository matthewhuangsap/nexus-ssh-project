package it.nexus.webcontrol.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.UIBean;

import com.opensymphony.xwork2.util.ValueStack;

public class DFChoiceBox extends UIBean {
	final public static String TEMPLATE = "dfchoicebox";
	private String action;
	private String datakind;
	private String value;
	private String displayValue;
	
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

	public DFChoiceBox(ValueStack stack, HttpServletRequest request,
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
		if (this.action != null)
			addParameter("action", findString(this.action));
		if (this.datakind != null)
			addParameter("datakind", findString(this.datakind));
		if (this.value!= null){
			String value = (String)findValue(this.value);
			if(value!=null)
				addParameter("value", value.toString());
			else
				addParameter("value", "");
				
		}
		if (this.displayValue != null)
			addParameter("displayValue", (String)findValue(this.displayValue));
		
	}
}
