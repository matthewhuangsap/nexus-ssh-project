package it.nexus.enterprise.system.user.dao;

import it.nexus.core.dao.BaseDAO;
import it.nexus.enterprise.system.authorize.model.RoleAction;
import it.nexus.enterprise.system.user.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class UserDAO extends BaseDAO<User,String> implements IUserDAO<User> {
    @SuppressWarnings("unchecked")
	public boolean find(final String username,final String password){
        User user =  this.getUserByNameAndPwd(username,password);
        Assert.notNull(user, "用户不存在！");
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
		
		List list = query.list ( ) ;
		if(list.size()>0)
			return (User)list.get(0);
		return null;
	}

    Map<String, Map<String,Long>> ROLE_ACTION = new HashMap<String, Map<String,Long>>();

	public Map<String, Map<String,Long>> getRoleActions(User user){

		String hql="from RoleAction where role_id=?" ;
		if(user.getRole() == null)
			return new HashMap<String,Map<String,Long>>();
		Query query = createQuery(hql,user.getRole().getId());
		List<RoleAction> roles = query.list();
		for (Object role : roles) {
			RoleAction ra = (RoleAction)role;
			Map<String,Long> role_item = getRoleItem(((RoleAction) role).getPlugin());
            role_item.put(ra.getName(),ra.getRolebits());
		}
		return ROLE_ACTION;
	}

    private Map<String, Long> getRoleItem(String plugin) {
        if(!ROLE_ACTION.containsKey(plugin)){
            ROLE_ACTION.put(plugin,new HashMap<String,Long>()) ;
        }
        return ROLE_ACTION.get(plugin);
    }

}
