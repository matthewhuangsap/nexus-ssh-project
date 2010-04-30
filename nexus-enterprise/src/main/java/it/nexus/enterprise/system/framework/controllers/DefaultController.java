package it.nexus.enterprise.system.framework.controllers;

import it.nexus.enterprise.annotations.Access;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@ParentPackage(value = "struts-default")
public class DefaultController extends ActionSupport {
	private static final long serialVersionUID = -8096504557534631343L;
	/**
	 * Dec 21, 2009 DefaultController.java Administrator
	 * @throws Exception 
	 */
	@Access("访问")
	@Action(value = "/default", results = { @Result(type = "freemarker", location = "/main.ftl", name = "success") })
	public String execute() throws Exception{
		return super.execute();
	}
}
