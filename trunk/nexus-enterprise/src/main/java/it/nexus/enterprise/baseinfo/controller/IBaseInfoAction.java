package it.nexus.enterprise.baseinfo.controller;

import it.nexus.core.controller.IEntityAction;
import it.nexus.enterprise.baseinfo.model.BaseInfo;

public interface IBaseInfoAction<T extends BaseInfo> extends IEntityAction<T> {
	String disable()throws Exception; //停用
	String start()throws Exception;   //启用
	
}
