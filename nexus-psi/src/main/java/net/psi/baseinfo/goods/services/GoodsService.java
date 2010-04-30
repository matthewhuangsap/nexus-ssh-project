package net.psi.baseinfo.goods.services;

import it.nexus.core.dao.Page;

import javax.annotation.Resource;

import it.nexus.enterprise.baseinfo.service.BaseInfoService;
import net.psi.baseinfo.goods.dao.GoodsDAO;
import net.psi.baseinfo.goods.model.Goods;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
