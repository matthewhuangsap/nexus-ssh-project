package it.nexus.enterprise.system.employee.controller;

import it.nexus.enterprise.system.employee.model.Employee;
import it.nexus.enterprise.system.employee.service.EmployeeService;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.util.StrutsTypeConverter;

@SuppressWarnings("unchecked")
public class EmployeeConverter extends StrutsTypeConverter {
	
	@Resource
	EmployeeService employeeService;
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		Long id = Long.valueOf(values[0]);
		Employee dmo = employeeService.load(id);
		System.out.println("传入的值："+values[0]);
		System.out.println("toClass："+toClass.getName());
		
		return dmo;
	}

	@Override
	public String convertToString(Map arg0, Object arg1) {
		return null;
	}

}
