package it.nexus.core.dao;

import it.nexus.core.models.BaseTree;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BaseTreeDAO<T extends BaseTree> extends BaseInfoDAO<T> {
	/**Dec 14, 2009
	 *BaseTreeDAO.java
	 *Administrator
	 */
	
	@Override
	public void save(T entity){
		HibernateTemplate tmp= getHibernateTemplate();
		tmp.setFlushMode(HibernateTemplate.FLUSH_EAGER);
		tmp.saveOrUpdate(entity);
		update_level(entity);
		tmp.flush();
	}
	
	

	private void update_level(T entity){
		BaseTree parent = entity.getParent();
		if (parent!=null &&(parent.getId()!=entity.getId())) {
			System.out.println("Has A Parent");
			entity.setLevel(parent.getLevel() + BaseTree.LEVEL_SPLIT + entity.getId());
		} else {
			System.out.println("No Parent");
			entity.setLevel(entity.getId().toString());
		}
		super.update(entity);
	}
	
	@Override
	public void delete(T entity){
		super.delete(entity);
	}
}
