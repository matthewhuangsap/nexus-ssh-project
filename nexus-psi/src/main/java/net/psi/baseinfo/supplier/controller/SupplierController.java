package net.psi.baseinfo.supplier.controller;

import it.nexus.core.annotation.Access;
import it.nexus.core.annotation.AccessGroup;
import it.nexus.core.controller.BaseAction;
import net.psi.baseinfo.supplier.model.Supplier;

import org.springframework.stereotype.Controller;

import plugin.psi.PluginClass;

@Controller
@AccessGroup(name = "��Ӧ��", pluginClass=PluginClass.class)
public class SupplierController extends BaseAction<Supplier> {
	/**
	 * Jan 6, 2010 SupplierController.java Administrator
	 */
	private static final long serialVersionUID = -8916475954087898625L;
	private Supplier dmo;
	@Access("�½�")
	public String create() throws Exception{
		return super.execute();
	}
	
	@Access("����")
	public String list() throws Exception{
		return super.execute();
	}
	
	@Access("�༭")
	public String edit() throws Exception{
		return super.execute();
	}
	
	@Access("ǰ��")
	public String prev() throws Exception{
		return super.execute();
	}
	
	@Access("��")
	public String next() throws Exception{
		return super.execute();
	}

	@Access("ɾ��")
	public String remove() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Access("����")
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Supplier getDmo() {
		return dmo;
	}

	public void setDmo(Supplier dmo) {
		this.dmo= dmo;
	}
}
