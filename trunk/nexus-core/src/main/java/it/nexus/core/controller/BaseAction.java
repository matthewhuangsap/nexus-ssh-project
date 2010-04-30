package it.nexus.core.controller;

import it.nexus.core.annotation.LogicName;
import it.nexus.core.models.Base;
import it.nexus.core.tools.ClassUtils;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unchecked")
public abstract class BaseAction<T extends Base> extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	protected T dmo;
	protected Map<String,Object> getSession(){
		return ActionContext.getContext().getSession();
	}
	
	protected ActionContext getContext(){
		return ActionContext.getContext();
	}
	
	protected Class<?> getEntityClass(){
		return ClassUtils.getGenericType(getClass(), 0);
	}
	
//	protected void getRole(){
//		Class<?> entityClass = getEntityClass();
//		AccessGroup aGroup = entityClass.getAnnotation(AccessGroup.class);
//		String access_group_name = aGroup.name();
//
//	}
	
	@SuppressWarnings("unchecked")
	protected Long getRoleBits(String access_group_name){
		Map<String, Long>  roles= (Map<String, Long>) getSession().get("roles");
		if(roles.containsKey(access_group_name)){
			return roles.get(access_group_name);
		}
		return null;
	}
	
	@SuppressWarnings("unused")
	private String title;

	public String getTitle() {
		Class<?> entityClass = getEntityClass();
		LogicName lName = entityClass.getAnnotation(LogicName.class);
		return lName.value();
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	protected StringBuilder sb_button = new StringBuilder();
	private String buttongroup="";
	public String getButtongroup() {
		buttongroup = initButtonGroup();
		return buttongroup;
	}

	public void setButtongroup(String buttongroup) {
		this.buttongroup = buttongroup;
	}
	
	protected String beforeButtonGroup(){
		return "";
	}
	protected String afterButtonGroup(){
		return "";
	}
	protected StringBuilder button_group= new StringBuilder();
	protected String initButtonGroup(){
		beforeButtonGroup();
		button_group.append("<input id='btn_save' value='保存' type='submit'>");
		afterButtonGroup();
		return button_group.toString();
	}
	
}
