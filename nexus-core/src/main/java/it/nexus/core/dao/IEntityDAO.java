package it.nexus.core.dao;

import it.nexus.core.models.Entity;

public interface IEntityDAO<T extends Entity> extends IBaseDAO<T> {
  void lock(T entity);

  void unLock(T entity);
}
