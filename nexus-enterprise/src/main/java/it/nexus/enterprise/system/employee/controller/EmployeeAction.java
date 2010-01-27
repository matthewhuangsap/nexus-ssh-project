package it.nexus.enterprise.system.employee.controller;

import it.nexus.core.annotation.Access;
import it.nexus.core.annotation.AccessGroup;
import it.nexus.core.controller.BaseInfoAction;
import it.nexus.core.controller.IBaseInfoAction;
import it.nexus.enterprise.system.employee.model.Employee;
import it.nexus.enterprise.system.employee.service.EmployeeService;

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
//声明此类为控制层的类,且为prototype模式调用
@ParentPackage(value = "coolie-default")
@Namespace("/system/employee")
@AccessGroup(name="员工",pluginClass=PluginClass.class)
public class EmployeeAction extends BaseInfoAction<Employee> implements IBaseInfoAction<Employee> {
	private static final long	serialVersionUID	= 1265101496764007164L;
	private final String		page_edit			= "/it/coolie/system/employee/views/employee_edit.ftl";
	private final String		page_list			= "/it/coolie/system/employee/views/employee_list.ftl";
	private List<Employee> lists=new ArrayList<Employee>();
	
	@Resource
	EmployeeService employeeService;

	@Access("删除")
	@Action(value = "/system/employee/remove", results = { @Result(name = "success", location = "/system/employee/list", type = "redirect") })
	public String remove() {
		System.out.println("remove method=>"+dmo.getId()+"  "+ dmo.getRemark());
		dmo = employeeService.load(dmo.getId());
		employeeService.delete(dmo);
		return SUCCESS;
	}
	
	@Access("访问")
	@SuppressWarnings("unchecked")
	@Action(value = "/system/employee/list", results = { @Result(name = "success", location = page_list) })
	public String list() {
		lists =employeeService.getAll();
		return SUCCESS;
	}

	@Access("保存")
	@Action(value = "/system/employee/save", results = {
			@Result(name = "success", location = "/system/employee/list" ,type="redirect"),
			@Result(name = "input", location = page_edit) })
	public String save() {
		employeeService.save(dmo);
		return SUCCESS;
	}
	
	@Access("新建")
	@Action(value = "/system/employee/create", results = { @Result(name = "success", location = page_edit) })
	public String create() {
		dmo = new Employee();
		return SUCCESS;
	}
	
	@Access("编辑")
	@Action(value = "/system/employee/edit", results = { @Result(name = "success", location = page_edit) })
	public String edit() {
		dmo = employeeService.load(dmo.getId());
		lists=employeeService.getAll();
		return SUCCESS;
	}
	
	protected String beforeButtonGroup(){
		button_group.append("<input type='submit' value='OK' id='btn_ok'/>");
		return super.beforeButtonGroup();
	}
	
	public List<Employee> getLists() {
		return lists;
	}

	public void setLists(List<Employee> lists) {
		this.lists = lists;
	}

	public Employee getDmo() {
		return dmo;
	}

	public void setDmo(Employee dmo) {
		this.dmo = dmo;
	}
	
	@Override
	@Access("停用")
	public String disable() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Access("启用")
	public String start() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Access("锁定")
	public String lock() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Access("后单")
	public String next() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Access("前单")
	public String previous() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Access("解锁")
	public String unlock() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
