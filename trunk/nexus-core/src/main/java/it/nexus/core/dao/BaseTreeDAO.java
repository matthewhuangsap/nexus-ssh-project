package it.nexus.core.dao;

import it.nexus.core.models.BaseTree;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.FlushMode;
import org.hibernate.Session;

import java.io.Serializable;

public class BaseTreeDAO<T extends BaseTree, PK extends Serializable> extends
    BaseInfoDAO<T, PK> {
  /**
   * Dec 14, 2009 BaseTreeDAO.java Administrator
   */
  private final Log log = LogFactory.getLog(BaseTreeDAO.class);

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
      log.debug("update_level method:has a parent");
      entity.setLevel(parent.getLevel() + BaseTree.LEVEL_SPLIT
          + entity.getId());
    } else {
      log.debug("update_level method:no parent");
      entity.setLevel(entity.getId().toString());
    }
    super.update(entity);
  }

  @Override
  public void delete(T entity) {
    entity.setParent(null);
    entity.setChilds(null);
    getSession().delete(entity);
    getSession().flush();
  }
}
