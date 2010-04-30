package it.nexus.enterprise.system.employee.dao;

import it.nexus.enterprise.baseinfo.dao.IBaseInfoDAO;
import it.nexus.enterprise.system.employee.model.Employee;

public interface IEmployeeDAO<T extends Employee> extends IBaseInfoDAO<T> {
	
}
