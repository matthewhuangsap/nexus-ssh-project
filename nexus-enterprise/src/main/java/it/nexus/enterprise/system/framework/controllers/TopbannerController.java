package it.nexus.enterprise.system.framework.controllers;

import it.nexus.enterprise.system.user.model.User;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@ParentPackage(value = "coolie-default")
public class TopbannerController extends ActionSupport {
	private static final long serialVersionUID = 2991983523496337582L;
	/**
	 * Dec 18, 2009 TopbannerController.java Administrator
	 * @throws Exception 
	 */
	
	
	private String username;
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	@Action(value = "/header", results = { @Result(type = "freemarker", location = "/header.ftl", name = "success") })
	public String header() throws Exception{
		User user =(User)ActionContext.getContext().getSession().get("userinfo");
		setUsername(user.getUsername());
		return super.execute();
	}
	
	
	@Action(value = "/logout", results = { @Result(type = "freemarker", location = "/login.ftl", name = "success") })
	public String logout() throws Exception{
		ActionContext.getContext().getSession().put("userinfo", null);
		return super.execute();
	}
}
