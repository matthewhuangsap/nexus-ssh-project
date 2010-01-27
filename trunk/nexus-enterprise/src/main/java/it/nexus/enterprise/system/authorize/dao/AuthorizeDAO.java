package it.nexus.enterprise.system.authorize.dao;

import it.nexus.core.dao.BaseTreeDAO;
import it.nexus.enterprise.system.authorize.model.Role;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
@Repository
@Transactional
public class AuthorizeDAO extends BaseTreeDAO<Role> {
	public void removeAction(final Role entity) {
		 this.getHibernateTemplate().execute(new   HibernateCallback()   {   
             public   Object   doInHibernate(Session   session)   throws   SQLException,   
                     HibernateException   {   
                 Long idLong = entity.getId();   
                 Query   query   =   session.createQuery(   
                         "delete  RoleAction  where   role_id = ?");   
                 query.setLong(0, idLong);   
                 query.executeUpdate();   
                 session.flush();
                 return   null;   
             }   
         });   
	}
}
