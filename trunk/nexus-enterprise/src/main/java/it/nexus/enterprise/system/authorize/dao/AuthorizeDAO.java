package it.nexus.enterprise.system.authorize.dao;

import it.nexus.core.dao.BaseTreeDAO;
import it.nexus.enterprise.system.authorize.model.Role;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository
public class AuthorizeDAO extends BaseTreeDAO<Role, Long> {
	public void removeAction(final Role entity) {
		Long idLong = entity.getId();
		Query query = getSession().createQuery(
				"delete  RoleAction  where   role_id = ?");
		query.setLong(0, idLong);
		query.executeUpdate();
		getSession().flush();
	}
}
