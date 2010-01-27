package it.nexus.enterprise.system.dept.service;

import it.nexus.enterprise.system.dept.dao.DeptDAO;
import it.nexus.enterprise.system.dept.model.Dept;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DeptService {
	
	@Resource
	DeptDAO deptDAO;
	
	public void start(Dept entity) {
		deptDAO.start(entity);
	}

	public void stop(Dept entity) {
		deptDAO.stop(entity);
	}

	public void lock(Dept entity) {
		deptDAO.lock(entity);
	}

	public void unLock(Dept entity) {
		deptDAO.unLock(entity);
	}

	public void delete(Dept entity) {
		deptDAO.delete(entity);
	}

	public List<Dept> getAll() {
		return deptDAO.getAll();
	}

	public Dept load(Serializable id) {
		return deptDAO.load(id);
	}

	public void save(Dept entity) {
		deptDAO.save(entity);
	}

	public void update(Dept entity) {
		deptDAO.update(entity);
	}
	
}
