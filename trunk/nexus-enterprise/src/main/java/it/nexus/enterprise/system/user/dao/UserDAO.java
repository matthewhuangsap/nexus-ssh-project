package it.nexus.enterprise.system.user.dao;

import it.nexus.core.dao.BaseDAO;
import it.nexus.enterprise.system.authorize.model.RoleAction;
import it.nexus.enterprise.system.user.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends BaseDAO<User,String> implements IUserDAO<User> {
	@SuppressWarnings("unchecked")
	public boolean find(final String username,final String password){
        User user = this.findUniqueBy("username",username);
        if(user.getPassword().equalsIgnoreCase(password))
			return true;
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public User getUserByNameAndPwd(final String username,final String password){
		String sql = "FROM User AS u WHERE u.username = ? AND u.password = ?";
		Query query = getSession().createQuery (sql);
		query.setString(0, username);
		query.setString(1, password);
		
		List<User> list = query.list ( ) ;
		if(list.size()>0)
			return list.get(0);
		return null;
	}
	
	public Map<String, Long> getRoleActions(User user){
		Map<String, Long> roleAction = new HashMap<String, Long>();
		String hql="from RoleAction where role_id=?" ;
		if(user.getRole() == null)
			return new HashMap<String,Long>();
		Query query = createQuery(hql,user.getRole().getId());
		List<RoleAction> roles = query.list();
		for (Object role : roles) {
			RoleAction ra = (RoleAction)role;
			roleAction.put(ra.getName(), ra.getRolebits());
		}
		return roleAction;
	}
	
}
