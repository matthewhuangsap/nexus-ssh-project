package it.nexus.core.dao;

import it.nexus.core.models.BaseInfo;

public interface IBaseInfoDAO<T extends BaseInfo> extends IEntityDAO<T> {
	void start(T entity);
    void stop(T entity);
}
