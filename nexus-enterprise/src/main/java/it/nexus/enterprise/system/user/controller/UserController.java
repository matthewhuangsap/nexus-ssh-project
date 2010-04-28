package it.nexus.enterprise.system.user.controller;

import it.nexus.core.annotation.Access;
import it.nexus.core.annotation.AccessGroup;
import it.nexus.core.controller.BaseAction;
import it.nexus.core.dao.Page;
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
@ParentPackage(value = "coolie-default")
@Namespace("/system/user")
@AccessGroup(name="用户管理",pluginClass=PluginClass.class)
public class UserController extends BaseAction<User> {
	private static final long serialVersionUID = 1L;
	private final String page_edit = "user_edit.ftl";
	private final String page_list = "user_list.ftl";
	private List<User> lists = new ArrayList<User>();
	private String reinput;

	@Resource
	UserService	userService;

	@Access("访问")
	@SuppressWarnings("unchecked")
	@Action(value = "/system/user/list", results = { @Result(name = "success", location = page_list) })
	public String list() {
        long begin_time = System.currentTimeMillis();
    Page page = new Page();
		lists = userService.getAll(page);
        long end_time = System.currentTimeMillis();
        long time = end_time - begin_time;
        System.out.println("List:>>>######>>>>>"+ time);
		return SUCCESS;
	}

	@Access("删除")
	@Action(value = "/system/user/remove", results = { @Result(name = "success", location = "/system/user/list", type = "redirect") })
	public String remove() {
         long begin_time = System.currentTimeMillis();
		dmo = userService.load(dmo.getId());
		userService.delete(dmo);
         long end_time = System.currentTimeMillis();
        long time = end_time - begin_time;
        System.out.println("Remove:>>>######>>>>>"+ time);
		return SUCCESS;
	}

	@Access("编辑")
	@Action(value = "/system/user/edit", results = {
			@Result(name = "success", location = page_edit),
			@Result(name = "input", location = page_edit) })
	public String edit() {
		dmo = userService.load(dmo.getId());
    Page page = new Page();
		lists = userService.getAll(page);
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
    Page page = new Page();
		lists = userService.getAll(page);
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