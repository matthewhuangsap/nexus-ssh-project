package it.nexus.enterprise.system.user.dao;

import it.nexus.core.dao.BaseDAO;
import it.nexus.enterprise.system.authorize.model.RoleAction;
import it.nexus.enterprise.system.user.model.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends BaseDAO<User> implements IUserDAO<User> {
	@SuppressWarnings("unchecked")
	public boolean find(final String username,final String password){
		List<User> lstList = (List<User>) getHibernateTemplate().execute(new HibernateCallback(){
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "FROM User AS u WHERE u.username = ? AND u.password = ?";
				Query query = session.createQuery (sql);
                query.setString(0, username);
                query.setString(1, password);
                
                List<User> list = query.list ( ) ;
                return list ;
			}
		});
		if(lstList.size()>0)
			return true;
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public User getUserByNameAndPwd(final String username,final String password){
		List<User> lstList = (List<User>) getHibernateTemplate().execute(new HibernateCallback(){
			@Override
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				String sql = "FROM User AS u WHERE u.username = ? AND u.password = ?";
				Query query = session.createQuery (sql);
				query.setString(0, username);
				query.setString(1, password);
				
				List<User> list = query.list ( ) ;
				return list ;
			}
		});
		if(lstList.size()>0)
			return lstList.get(0);
		return null;
	}
	
	public Map<String, Long> getRoleActions(User user){
		Map<String, Long> roleAction = new HashMap<String, Long>();
		String hql="from RoleAction where role_id=?" ;
		if(user.getRole() == null)
			return new HashMap<String,Long>();
		List<Class<?>> roles = executeHql(hql,user.getRole().getId());
		for (Object role : roles) {
			RoleAction ra = (RoleAction)role;
			roleAction.put(ra.getName(), ra.getRolebits());
		}
		return roleAction;
	}
	
}
