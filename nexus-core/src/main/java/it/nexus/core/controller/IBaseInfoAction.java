package it.nexus.core.controller;

import it.nexus.core.models.BaseInfo;

public interface IBaseInfoAction<T extends BaseInfo> extends IEntityAction<T> {
	String disable()throws Exception; //停用
	String start()throws Exception;   //启用
	
}
