package it.nexus.enterprise.system.framework.controllers;

import it.nexus.core.annotation.Access;
import it.nexus.core.annotation.AccessGroup;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import plugin.system.PluginClass;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@ParentPackage(value = "coolie-default")
@AccessGroup(name="首页",pluginClass=PluginClass.class)
public class ContainerController extends ActionSupport {
	private static final long serialVersionUID = 6197691600769339053L;
	/**
	 * Dec 21, 2009 ContainerController.java Administrator
	 * @throws Exception 
	 */
	
	@Access("访问")
	@Action(value = "/container", results = { @Result(type = "freemarker", location = "/container.ftl", name = "success") })
	public String container() throws Exception{
		return super.execute();
	}
}
