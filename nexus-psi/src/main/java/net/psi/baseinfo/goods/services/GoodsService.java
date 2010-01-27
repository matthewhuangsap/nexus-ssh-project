package net.psi.baseinfo.goods.services;

import it.nexus.core.service.BaseInfoService;

import javax.annotation.Resource;

import net.psi.baseinfo.goods.dao.GoodsDAO;
import net.psi.baseinfo.goods.model.Goods;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GoodsService extends BaseInfoService<Goods, GoodsDAO> {
	/**
	 * Dec 25, 2009 GoodsService.java Administrator
	 */
	@Resource
	public void setDao(GoodsDAO dao){
		super.dao = dao;
	}
}
