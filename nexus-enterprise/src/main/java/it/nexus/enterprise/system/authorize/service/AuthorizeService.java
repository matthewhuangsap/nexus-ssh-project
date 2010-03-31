package it.nexus.enterprise.system.authorize.service;

import it.nexus.core.service.BaseTreeService;
import it.nexus.enterprise.system.authorize.dao.RoleDAO;
import it.nexus.enterprise.system.authorize.model.Role;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthorizeService extends BaseTreeService<Role, RoleDAO>{
	public AuthorizeService() {
	}

	@Resource
	public void setDao(RoleDAO dao){
		super.dao = dao;
	}
	
	public void removeAction(Role entity){
		dao.removeAction(entity);
	}

    public void getUserRoles(String user_id){
        
    }
}
