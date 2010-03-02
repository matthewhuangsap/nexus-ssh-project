package it.nexus.enterprise.system.dept.service;

import it.nexus.core.service.BaseInfoService;
import it.nexus.core.service.BaseTreeService;
import it.nexus.enterprise.system.dept.dao.DeptDAO;
import it.nexus.enterprise.system.dept.model.Dept;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeptService extends BaseTreeService<Dept, DeptDAO> {

    @Resource
	public void setDao(DeptDAO dao){
		super.dao = dao;
	}
//    @Resource
//    DeptDAO deptDAO;
//
//    public void save(Dept dept){
//        deptDAO.save(dept);
//    }
//
//    public void start(Dept entity) {
//		deptDAO.start(entity);
//	}
//
//	public void stop(Dept entity) {
//		deptDAO.stop(entity);
//	}
//
//	public void lock(Dept entity) {
//		deptDAO.lock(entity);
//	}
//
//	public void unLock(Dept entity) {
//		deptDAO.unLock(entity);
//	}
//
//	public void delete(Dept entity) {
//		deptDAO.delete(entity);
//	}
//
//	public List<Dept> getAll() {
//		return deptDAO.getAll();
//	}
//
//	public Dept load(Serializable id) {
//		return deptDAO.load(id);
//	}
//
//	public void update(Dept entity) {
//		deptDAO.update(entity);
//	}
//
//	public void merge(Dept entity){
//		deptDAO.merge(entity);
//	}
	
}
