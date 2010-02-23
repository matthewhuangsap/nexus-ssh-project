package it.nexus.enterprise.system.dept.service;

import it.nexus.core.service.BaseInfoService;
import it.nexus.enterprise.system.dept.dao.DeptDAO;
import it.nexus.enterprise.system.dept.model.Dept;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DeptService extends BaseInfoService<Dept,DeptDAO> {
	
	@Resource
	public void setDao(DeptDAO dao){
        this.dao=dao;
    }
	
}
