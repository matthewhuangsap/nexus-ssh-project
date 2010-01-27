package it.nexus.core.dao;

import it.nexus.core.models.BaseInfo;

public class BaseInfoDAO<T extends BaseInfo> extends EntityDAO<T> implements
		IBaseInfoDAO<T> {
	
	public void start(T entity) {
		entity.setAvailable(true);
		getHibernateTemplate().saveOrUpdate(entity);
	}

	public void stop(T entity) {
		entity.setAvailable(false);
		getHibernateTemplate().saveOrUpdate(entity);
	}

}
