package it.nexus.enterprise.baseinfo.service;

import it.nexus.enterprise.baseinfo.dao.BaseInfoDAO;
import it.nexus.enterprise.baseinfo.model.BaseInfo;
import it.nexus.core.service.BaseService;

@SuppressWarnings("unchecked")
public class BaseInfoService<T extends BaseInfo, K extends BaseInfoDAO>
    extends BaseService<T, K> {
  public BaseInfoService() {
  }


}
