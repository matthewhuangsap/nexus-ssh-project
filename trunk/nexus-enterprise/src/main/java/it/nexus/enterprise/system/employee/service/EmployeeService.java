package it.nexus.enterprise.system.employee.service;

import it.nexus.enterprise.system.employee.dao.EmployeeDAO;
import it.nexus.enterprise.system.employee.model.Employee;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EmployeeService {
	
	@Resource
	EmployeeDAO employeeDAO;
	
	public void start(Employee entity) {
		employeeDAO.start(entity);
	}

	public void stop(Employee entity) {
		employeeDAO.stop(entity);
	}

	public void lock(Employee entity) {
		employeeDAO.lock(entity);
	}

	public void unLock(Employee entity) {
		employeeDAO.unLock(entity);
	}

	public void delete(Employee entity) {
		employeeDAO.delete(entity);
	}

	public List<Employee> getAll() {
		return employeeDAO.getAll();
	}

	public Employee load(Serializable id) {
		return employeeDAO.load(id);
	}

	public void save(Employee entity) {
			employeeDAO.saveOrUpdate(entity);
	}

	public void update(Employee entity) {
		employeeDAO.update(entity);
	}
	
	public void merge(Employee entity){
		employeeDAO.merge(entity);
	}
	
}
