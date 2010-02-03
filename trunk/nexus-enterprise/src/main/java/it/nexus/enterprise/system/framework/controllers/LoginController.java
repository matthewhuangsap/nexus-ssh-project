package it.nexus.enterprise.system.framework.controllers;

import it.nexus.core.annotation.Access;
import it.nexus.core.controller.BaseAction;
import it.nexus.core.tools.FileUtils;
import it.nexus.enterprise.system.framework.services.SystemAssistService;
import it.nexus.enterprise.system.user.model.User;
import it.nexus.enterprise.system.user.service.UserService;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("unchecked")
@Controller
@Scope("prototype")
@ParentPackage(value = "struts-default")
public class LoginController extends BaseAction implements ServletRequestAware,
		SessionAware
{
	private static final long	serialVersionUID	= 1788765307656307005L;
	/**
	 * Dec 17, 2009 LoingController.java Administrator
	 */
	private User				dmo;

	public User getDmo() {
		return dmo;
	}

	public void setDmo(User dmo) {
		this.dmo = dmo;
	}

	@Resource
	UserService			userService;

	@Resource
	SystemAssistService	systemAssistService;

	@Action(value = "show", results = { @Result(type = "freemarker", location = "/login.ftl", name = "success") })
	public String show() throws Exception {
		return super.execute();
	}

	@Access("访问")
	@Action(value = "login", results = { @Result(type = "redirect", location = "default", name = "success") })
	public String login() throws Exception {
		if (userService.isLogin(dmo.getUsername(), dmo.getPassword())) {
			getSession().put("userinfo", dmo);
			dmo = userService.getUser(dmo.getUsername(), dmo.getPassword());
			Map<String, Long> roleActions = userService.getRoleActions(dmo);
			getSession().put("roles", roleActions);

			getSession().put("access_group_map",
					systemAssistService.getAccess_group_map());
			getSession().put("plugin_info",
					systemAssistService.getPlugin_info());
		} else {
			ActionContext.getContext().getSession().put("userinfo", null);
			ActionContext.getContext().getSession().put("roles", null);
		}
		getSecurityMap();
		return super.execute();
	}

	private Map<String, Object>	session;

	@Override
	public void setSession(Map<String, Object> sesssion) {
		this.session = sesssion;
	}

	private void getSecurityMap() {
		// TODO 目前这里应该是从各个模块jar中读取配置文件
		String real_path = request.getSession().getServletContext()
				.getRealPath("/");
		String config_path = real_path + "//config";
		List<String> list = FileUtils.searchFileFromFolder(config_path,
				".*\\.plugin.xml");
		for (String string : list) {
			System.out.println(">>>>>>>" + string);
		}
	}

	private HttpServletRequest	request;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

}
