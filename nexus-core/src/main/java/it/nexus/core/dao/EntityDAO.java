package it.nexus.core.dao;

import it.nexus.core.models.Entity;

public class EntityDAO<T extends Entity> 
	extends BaseDAO<T> 
	implements IBaseDAO<T> ,IEntityDAO<T>{

	public void lock(T entity) {
		entity.setIsLock(true);
		getHibernateTemplate().update(entity);
	}

	public void unLock(T entity) {
		entity.setIsLock(false);
		getHibernateTemplate().update(entity);
	}
	
	
}
