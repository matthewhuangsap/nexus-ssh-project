package it.nexus.core.service;

import it.nexus.core.dao.BaseInfoEntityDAO;
import it.nexus.core.model.BaseInfoEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-2-4
 * Time: 8:05:50
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class BaseInfoEntityService extends BaseInfoService<BaseInfoEntity, BaseInfoEntityDAO> {
    
}
