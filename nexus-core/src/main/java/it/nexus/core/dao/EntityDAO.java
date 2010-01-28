package it.nexus.core.dao;

import java.io.Serializable;

import it.nexus.core.models.Entity;

public class EntityDAO<T extends Entity,PK extends Serializable> 
	extends BaseDAO<T,PK> 
	implements IBaseDAO<T> ,IEntityDAO<T>{

	public void lock(T entity) {
		entity.setIsLock(true);
		getSession().update(entity);
	}

	public void unLock(T entity) {
		entity.setIsLock(false);
		getSession().update(entity);
	}
	
	
}
