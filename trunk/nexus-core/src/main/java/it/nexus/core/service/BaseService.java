package it.nexus.core.service;

import java.io.Serializable;
import java.util.List;

import it.nexus.core.dao.BaseDAO;
import it.nexus.core.models.Base;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
@Transactional
public class BaseService<T extends Base,K extends BaseDAO> {
	public BaseService(){}
	protected K dao;
	public K getDao() {
		return dao;
	}
	public void setDao(K dao) {
		this.dao = dao;
	}
	
	public BaseService(K dao){
		this.dao = dao;
	}
	
	public void delete(T entity) {
		dao.delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return dao.getAll();
	}

	@SuppressWarnings("unchecked")
	public T load(Serializable id) {
		return (T) dao.load(id);
	}

    @Transactional
	public void save(T entity) {
		dao.save(entity);
	}
	
	public void saveOrUpdate(T entity) {
		dao.saveOrUpdate(entity);
	}

	public void merge(T entity){
		dao.merge(entity);
	}
	
	public void update(T entity) {
		dao.update(entity);
	}

}
