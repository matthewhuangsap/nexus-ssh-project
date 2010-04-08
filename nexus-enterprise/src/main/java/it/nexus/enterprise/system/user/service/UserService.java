package it.nexus.enterprise.system.user.service;

import it.nexus.enterprise.system.user.dao.UserDAO;
import it.nexus.enterprise.system.user.model.User;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserService {
	@Resource
	UserDAO userDAO;

	public void delete(User entity) {
		
		userDAO.delete(entity);
	}

	public List<User> getAll() {
		return userDAO.getAll();
	}

	public User load(Serializable id) {
		return userDAO.load(id);
	}

	public void save(User entity) {
		userDAO.saveOrUpdate(entity);
	}

	public void update(User entity) {
		userDAO.update(entity);
	}
	
	public boolean isLogin(String username,String password){		
		return userDAO.find(username, password);
	}
	
	public User getUser(String username ,String password){
		return userDAO.getUserByNameAndPwd(username, password);
	}
	
	public Map<String, Map<String,Long>> getRoleActions(User user){
		return userDAO.getRoleActions(user);
	}
}
