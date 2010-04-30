package it.nexus.enterprise.system.employee.dao;

import it.nexus.enterprise.baseinfo.dao.BaseInfoDAO;
import it.nexus.enterprise.system.employee.model.Employee;

import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAO extends BaseInfoDAO<Employee,String> implements IEmployeeDAO<Employee> {
	
}
