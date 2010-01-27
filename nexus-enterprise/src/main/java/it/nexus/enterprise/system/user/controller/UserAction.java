package it.nexus.enterprise.system.user.controller;

import it.nexus.core.annotation.Access;
import it.nexus.core.annotation.AccessGroup;
import it.nexus.core.controller.BaseAction;
import it.nexus.enterprise.system.user.model.User;
import it.nexus.enterprise.system.user.service.UserService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import plugin.system.PluginClass;

@Controller
@Scope("prototype")
// spring
@ParentPackage(value = "coolie-default")
@Namespace("/system/user")
@AccessGroup(name="用户管理",pluginClass=PluginClass.class)
public class UserAction extends BaseAction<User> {
	private static final long serialVersionUID = 1L;
	private final String page_edit = "/it/coolie/system/user/views/user_edit.ftl";
	private final String page_list = "/it/coolie/system/user/views/user_list.ftl";
	private List<User> lists = new ArrayList<User>();
	private String reinput;

	@Resource
	UserService	userService;

	@Access("访问")
	@SuppressWarnings("unchecked")
	@Action(value = "/system/user/list", results = { @Result(name = "success", location = page_list) })
	public String list() {
		lists = userService.getAll();
		return SUCCESS;
	}

	@Access("删除")
	@Action(value = "/system/user/remove", results = { @Result(name = "success", location = "/system/user/list", type = "redirect") })
	public String remove() {
		dmo = userService.load(dmo.getId());
		userService.delete(dmo);
		return SUCCESS;
	}

	@Access("编辑")
	@Action(value = "/system/user/edit", results = {
			@Result(name = "success", location = page_edit),
			@Result(name = "input", location = page_edit) })
	public String edit() {
		dmo = userService.load(dmo.getId());
		lists = userService.getAll();
		return SUCCESS;
	}

	@Access("保存")
	@Action(value = "/system/user/save", results = {
			@Result(name = "success", location = "/system/user/list", type = "redirect"),
			@Result(name = "input", location = page_edit) })
	public String save() {
		if (dmo.getPassword().equalsIgnoreCase(reinput))
			userService.save(dmo);
		else {
			return "input";
		}
		return SUCCESS;
	}

	@Access("新建")
	@Action(value = "/system/user/create", results = { @Result(name = "success", location = page_edit) })
	public String create() {
		dmo = new User();
		lists = userService.getAll();
		return SUCCESS;
	}

	public User getDmo() {
		return dmo;
	}

	public void setDmo(User dmo) {
		this.dmo = dmo;
	}

	public List<User> getLists() {
		return lists;
	}

	public void setLists(List<User> lists) {
		this.lists = lists;
	}

	public String getReinput() {
		return reinput;
	}

	public void setReinput(String reinput) {
		this.reinput = reinput;
	}
}