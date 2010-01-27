package it.nexus.webcontrol.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts2.components.UIBean;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;

public class DFTitle extends UIBean {
	private String dmoID;
	public String getDmoID() {
		return dmoID;
	}
	
	@StrutsTagAttribute(name="dmoID", description="", type="String", defaultValue="")
	public void setDmoID(String dmoID) {
		this.dmoID = dmoID;
	}


	public DFTitle(ValueStack stack, HttpServletRequest req,
			HttpServletResponse res) {
		super(stack, req, res);
	}

	final public static String TEMPLATE = "dftitle";

	@Override
	protected String getDefaultTemplate() {
		return TEMPLATE;
	}
	
	@Override
	protected void evaluateExtraParams() {
		super.evaluateExtraParams();
		if (null != id)
            addParameter("id", findString(id));
        if (null != name)
        	addParameter("name", findString(name));
        if (null != label)
        	addParameter("label", findString(label));
        if (null != dmoID)
        	addParameter("dmoID", findString(dmoID));
        if (null != dmoID){
        	Long dmo_id = (Long)findValue(dmoID,Long.class);
        	addParameter("dmoValue",dmo_id==null?0:dmo_id);
        }
        	
	}


}
