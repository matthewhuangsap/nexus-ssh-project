package it.nexus.enterprise.system.dept.controller;

import it.nexus.core.annotation.Access;
import it.nexus.core.annotation.AccessGroup;
import it.nexus.core.controller.BaseInfoAction;
import it.nexus.enterprise.system.dept.model.Dept;
import it.nexus.enterprise.system.dept.service.DeptService;

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
// 声明此类为控制层的类,且为prototype模式调用
@ParentPackage(value = "coolie-default")
@Namespace("/system/dept")
@AccessGroup(name = "部门", pluginClass = PluginClass.class)
public class DeptAction extends BaseInfoAction<Dept> {
	private static final long	serialVersionUID	= -5902936233600478352L;
	private final String		page_edit			= "dept_edit.ftl";
	private final String		page_list			= "dept_list.ftl";

	private List<Dept>			lists				= new ArrayList<Dept>();

	@Resource
	DeptService					deptService;

	@Access("删除")
	@Action(value = "/system/dept/remove", results = { @Result(name = "success", location = "/system/dept/list", type = "redirect") })
	public String remove() {
		dmo = deptService.load(dmo.getId());
		deptService.delete(dmo);
		return SUCCESS;
	}

	@Access("访问")
	@SuppressWarnings("unchecked")
	@Action(value = "/system/dept/list", results = { @Result(name = "success", location = page_list) })
	public String list() {
		lists = deptService.getAll();
		return SUCCESS;
	}

	@Access("保存")
	@Action(value = "/system/dept/save", results = {
			@Result(name = "success", location = "/system/dept/list", type = "redirect"),
			@Result(name = "input", location = page_edit) })
	public String save() {
		System.out.println("do save:" + dmo.getId() + dmo.getRemark());
		deptService.save(dmo);
		return SUCCESS;
	}

	@Access("新建")
	@Action(value = "/system/dept/create", results = { @Result(name = "success", location = page_edit) })
	public String create() throws Exception {
		dmo = new Dept();
		lists = deptService.getAll();
		System.out.println("dmo.id:" + dmo.getId());
		return super.SUCCESS;
	}

	@Access("编辑")
	@Action(value = "/system/dept/edit", results = { @Result(name = "success", location = page_edit) })
	public String edit() {
		System.out.println("dmo.id:" + dmo.getId());
		dmo = deptService.load(dmo.getId());
		lists = deptService.getAll();
		System.out.println("out put " + dmo.getLevel() + dmo.getRemark()
				+ dmo.getId());
		return SUCCESS;
	}

	public Dept getDept() {
		return dmo;
	}

	public void setDept(Dept dept) {
		this.dmo = dept;
	}

	public List<Dept> getLists() {
		return lists;
	}

	public void setLists(List<Dept> lists) {
		this.lists = lists;
	}

	public Dept getDmo() {
		return this.dmo;
	}

	public void setDmo(Dept dmo) {
		this.dmo = dmo;
	}
}
