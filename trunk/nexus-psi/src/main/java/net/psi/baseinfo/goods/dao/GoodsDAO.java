package net.psi.baseinfo.goods.dao;

import it.nexus.core.dao.BaseInfoDAO;
import net.psi.baseinfo.goods.model.Goods;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dec 25, 2009 GoodsDAO.java Administrator
 */
@Repository
@Transactional
public class GoodsDAO extends BaseInfoDAO<Goods,Long> {

}
