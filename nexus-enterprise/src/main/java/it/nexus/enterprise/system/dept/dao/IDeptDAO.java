package it.nexus.enterprise.system.dept.dao;

import it.nexus.enterprise.baseinfo.dao.IBaseInfoDAO;
import it.nexus.enterprise.system.dept.model.Dept;

public interface IDeptDAO<T extends Dept> extends IBaseInfoDAO<T> {
	void setLevel(T dept);
}
