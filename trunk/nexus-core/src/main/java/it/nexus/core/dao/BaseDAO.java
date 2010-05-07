package it.nexus.core.dao;

import it.nexus.core.models.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unchecked")
public class BaseDAO<T extends Base, PK extends Serializable> extends HibernateDao<T,PK> implements
    IBaseDAO<T> {
  protected Logger logger = LoggerFactory.getLogger(getClass());

  @Override
  public T load(Serializable id) {
    return get((PK) id);
  }

  @Override
  public void update(T entity) {
    super.save(entity);
  }

  @Override
  public List<T> getAll(Page page) {
    return null;  
  }
}
