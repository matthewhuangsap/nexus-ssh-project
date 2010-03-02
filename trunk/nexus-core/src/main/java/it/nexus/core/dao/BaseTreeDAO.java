package it.nexus.core.dao;

import it.nexus.core.models.BaseTree;

import java.io.Serializable;

import org.hibernate.FlushMode;
import org.hibernate.Session;

public class BaseTreeDAO<T extends BaseTree, PK extends Serializable> extends
		BaseInfoDAO<T,PK>
{
	/**
	 * Dec 14, 2009 BaseTreeDAO.java Administrator
	 */

	@Override
	public void save(T entity) {
		Session session = getSession();
		session.setFlushMode(FlushMode.COMMIT);
		session.saveOrUpdate(entity);
		update_level(entity);
        session.flush();
	}

	private void update_level(T entity) {
		BaseTree parent = entity.getParent();
		if (parent != null && (parent.getId() != entity.getId())) {
			System.out.println("Has A Parent");
			entity.setLevel(parent.getLevel() + BaseTree.LEVEL_SPLIT
					+ entity.getId());
		} else {
			System.out.println("No Parent");
			entity.setLevel(entity.getId().toString());
		}
		super.update(entity);
	}

	@Override
	public void delete(T entity) {
        if(entity.getParent()!=null){
            entity.getParent().setChilds(null);
            entity.setParent(null);
        }
		getSession().delete(entity);
        getSession().flush();
	}
}
