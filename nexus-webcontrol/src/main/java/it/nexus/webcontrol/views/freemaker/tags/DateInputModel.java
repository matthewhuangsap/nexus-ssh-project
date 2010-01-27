package it.nexus.webcontrol.views.freemaker.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.freemarker.tags.TagModel;

import com.opensymphony.xwork2.util.ValueStack;

public class DateInputModel extends TagModel {

	public DateInputModel(ValueStack stack, HttpServletRequest req,
			HttpServletResponse res) {
		super(stack, req, res);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Component getBean() {
        return new it.nexus.webcontrol.components.DFDateInput(stack, req, res);
	}

}
