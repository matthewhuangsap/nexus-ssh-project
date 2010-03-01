package it.nexus.core.service;

import it.nexus.core.dao.ExtBaseDAO;
import it.nexus.core.model.ExtBase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 * User: dcriori
 * Date: 2010-3-1
 * Time: 10:15:28
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class ExtBaseService extends BaseInfoService<ExtBase, ExtBaseDAO> {
    @Resource
    public void setDao(ExtBaseDAO dao){
        this.dao = dao;
    }
}
