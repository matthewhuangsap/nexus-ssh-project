package it.nexus.enterprise.system.authorize.dao;

import it.nexus.enterprise.baseinfo.dao.BaseTreeDAO;
import it.nexus.enterprise.system.authorize.model.Role;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository
public class RoleDAO extends BaseTreeDAO<Role, String> {
	public void removeAction(final Role entity) {
        String idLong = entity.getId();
		Query query = getSession().createQuery(
				"delete RoleAction where role_id = ?");
		query.setString(0, idLong);
		query.executeUpdate();
		getSession().flush();
	}
}
