package it.nexus.core.controller;

import it.nexus.core.models.Base;

public interface IBaseAction<T extends Base> {
	String save() throws Exception; //保存
	String remove()throws Exception; //删除
	String list()throws Exception; //访问
	String create()throws Exception; //新建
	String edit()throws Exception; //编辑
}
