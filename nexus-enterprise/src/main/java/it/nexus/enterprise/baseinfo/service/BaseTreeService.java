package it.nexus.enterprise.baseinfo.service;

import it.nexus.enterprise.baseinfo.dao.BaseTreeDAO;
import it.nexus.enterprise.baseinfo.model.BaseTree;
import it.nexus.enterprise.baseinfo.service.BaseInfoService;

@SuppressWarnings("unchecked")
public class BaseTreeService<T extends BaseTree, K extends BaseTreeDAO> extends
    BaseInfoService<T, K> {
  /**
   * Dec 14, 2009 BaseTreeService.java Administrator
   */
  public BaseTreeService() {
  }
}
