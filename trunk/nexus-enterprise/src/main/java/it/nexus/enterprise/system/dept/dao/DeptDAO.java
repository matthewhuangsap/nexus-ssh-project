package it.nexus.enterprise.system.dept.dao;

import it.nexus.core.dao.BaseInfoDAO;
import it.nexus.core.dao.BaseTreeDAO;
import it.nexus.enterprise.system.dept.model.Dept;

import org.springframework.stereotype.Repository;

@Repository
public class DeptDAO extends BaseTreeDAO<Dept,String> {
    
}
