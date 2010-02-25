package it.nexus.webcontrol.views;

import it.nexus.webcontrol.views.freemaker.tags.CoolieModels;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.views.TagLibrary;

import com.opensymphony.xwork2.util.ValueStack;

public class NexusTagLibrary implements TagLibrary {
	public Object getFreemarkerModels(ValueStack stack, HttpServletRequest req,
			HttpServletResponse res) {
		return new CoolieModels(stack,req,res);
	}

	@SuppressWarnings("unchecked")
	public List<Class> getVelocityDirectiveClasses() {
		Class[] directives = new Class[] {

		};
		return Arrays.asList(directives);
	}

}