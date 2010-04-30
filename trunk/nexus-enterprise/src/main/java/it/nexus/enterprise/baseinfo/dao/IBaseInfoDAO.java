package it.nexus.enterprise.baseinfo.dao;

import it.nexus.core.dao.IEntityDAO;
import it.nexus.enterprise.baseinfo.model.BaseInfo;

public interface IBaseInfoDAO<T extends BaseInfo> extends IEntityDAO<T> {
  void start(T entity);
  void stop(T entity);
}
