package it.nexus.core.service;

import it.nexus.core.dao.BaseTreeDAO;
import it.nexus.core.models.BaseTree;

@SuppressWarnings("unchecked")
public class BaseTreeService<T extends BaseTree, K extends BaseTreeDAO> extends
		BaseInfoService<T, K> {
	/**
	 * Dec 14, 2009 BaseTreeService.java Administrator
	 */
	public BaseTreeService() {
	}
}
