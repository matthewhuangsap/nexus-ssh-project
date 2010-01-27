package it.nexus.core.dao;

import it.nexus.core.models.Base;

import java.io.Serializable;
import java.util.List;

public interface IBaseDAO<T extends Base> {
	public T load(Serializable id);
	public void save(T entity);
	public void delete(T entity);
	public void update(T entity);
	public List<T> getAll();
}
