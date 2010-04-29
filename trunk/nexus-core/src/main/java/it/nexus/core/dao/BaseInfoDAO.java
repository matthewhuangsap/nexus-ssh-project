package it.nexus.core.dao;

import it.nexus.core.models.BaseInfo;

import java.io.Serializable;

public class BaseInfoDAO<T extends BaseInfo, PK extends Serializable> extends EntityDAO<T, PK> implements
    IBaseInfoDAO<T> {

  public void start(T entity) {
    entity.setAvailable(true);
    getSession().saveOrUpdate(entity);
  }

  public void stop(T entity) {
    entity.setAvailable(false);
    getSession().saveOrUpdate(entity);
  }

}
