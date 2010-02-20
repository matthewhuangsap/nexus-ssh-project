package it.nexus.enterprise.system.authorize.dao;

import it.nexus.core.dao.BaseTreeDAO;
import it.nexus.enterprise.system.authorize.model.Role;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.concurrent.atomic.AtomicReference;

@SuppressWarnings("unchecked")
@Repository
public class AuthorizeDAO extends BaseTreeDAO<Role, String> {
	public void removeAction(final Role entity) {
		String idLong = entity.getId();
		AtomicReference<Query> query = new AtomicReference<Query>(getSession().createQuery(
                "delete  RoleAction  where   role_id = ?"));
		query.get().setString(0,idLong);
		query.get().executeUpdate();
		getSession().flush();
	}
}
