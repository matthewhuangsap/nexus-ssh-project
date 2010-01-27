package net.psi.baseinfo.goods.controllers;

import it.nexus.core.annotation.Access;
import it.nexus.core.annotation.AccessGroup;
import it.nexus.core.controller.BaseInfoAction;
import it.nexus.core.controller.IBaseInfoAction;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.psi.baseinfo.goods.model.Goods;
import net.psi.baseinfo.goods.services.GoodsService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import plugin.psi.PluginClass;

@Controller
@Scope("prototype")
@ParentPackage(value = "coolie-default")
@Namespace("/baseinfo/goods")
@AccessGroup(name = "���", pluginClass = PluginClass.class)
public class GoodsController extends BaseInfoAction<Goods> implements IBaseInfoAction<Goods>{
	private static final long	serialVersionUID	= 541673655485555351L;
	private static final String	edit_page			= "/net/psi/baseinfo/goods/views/goods_edit.ftl";
	private static final String	list_page			= "/net/psi/baseinfo/goods/views/goods_list.ftl";
	/**
	 * Dec 25, 2009 GoodsController.java Administrator
	 */
	List<Goods>					lists				= new ArrayList<Goods>();
	@Resource
	GoodsService				goodsService;

	@Access("�½�")
	@SuppressWarnings("unchecked")
	@Action(value = "/baseinfo/goods/create", results = { @Result(name = "success", type = "freemarker", location = edit_page) })
	public String create() {
		dmo = new Goods();
		return SUCCESS;
	}

	@Access("����")
	@SuppressWarnings("unchecked")
	@Action(value = "/baseinfo/goods/list", results = { @Result(name = "success", type = "freemarker", location = list_page) })
	public String list() {
		lists = goodsService.getAll();
		return SUCCESS;
	}

	public List<Goods> getLists() {
		return lists;
	}

	public void setLists(List<Goods> lists) {
		this.lists = lists;
	}

	@Override
	@Access("ɾ��")
	@SuppressWarnings("unchecked")
	@Action(value = "/baseinfo/goods/remove", results = { @Result(name = "success", type = "freemarker", location = list_page) })
	public String remove() throws Exception {
		System.out.println("remove action ID:" + dmo.getId());
		return super.execute();
	}

	@Override
	@Access("����")
	@SuppressWarnings("unchecked")
	@Action(value = "/baseinfo/goods/save", results = { @Result(name = "success", type = "freemarker", location = list_page) })
	public String save() throws Exception {
		System.out.println("save action ID:" + dmo.getId());
		return super.execute();
	}

	@Override
	@Access("�༭")
	@SuppressWarnings("unchecked")
	@Action(value = "/baseinfo/goods/edit", results = { @Result(name = "success", type = "freemarker", location = list_page) })
	public String edit() throws Exception {
		return super.execute();
	}

	@Override
	public String disable() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String start() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String lock() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String next() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String previous() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String unlock() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Goods getDmo() {
		return dmo;
	}

	public void setDmo(Goods dmo) {
		this.dmo=dmo;
	}
}
