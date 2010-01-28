package it.nexus.enterprise.system.dept.dao;

import it.nexus.core.dao.BaseInfoDAO;
import it.nexus.enterprise.system.dept.model.Dept;

import org.springframework.stereotype.Repository;

@Repository
public class DeptDAO extends BaseInfoDAO<Dept,Long> implements IDeptDAO<Dept> {
	@Override
	public void save(Dept entity) {
		super.saveOrUpdate(entity);
		setLevel(entity);
	}
	
	public void setLevel(Dept entity){
		Dept parent = entity.getParent();
		if (parent!=null &&(parent.getId()!=entity.getId())) {
			System.out.println("Has A Parent");
			entity.setLevel(parent.getLevel() + Dept.LEVEL_SPLIT + entity.getId());
		} else {
			System.out.println("No Parent");
			entity.setLevel(entity.getId().toString());
//			entity.setParent(entity);
		}
		super.update(entity);
	}
}
