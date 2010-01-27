package it.nexus.core.controller;

import it.nexus.core.models.BaseInfo;

public class BaseInfoAction<T extends BaseInfo> extends EntityAction<T> {
	private static final long	serialVersionUID	= -7998342067690487055L;
	protected String beforeButtonGroup(){
		button_group.append("<input type='submit' value='新建' id='btn_create'/>");
		return super.beforeButtonGroup();
	}
}
