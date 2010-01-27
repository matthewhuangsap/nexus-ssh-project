package it.nexus.core.controller;

import it.nexus.core.models.Entity;

public class EntityAction<T extends Entity> extends BaseAction<T> {
	private static final long	serialVersionUID	= -396481523092188753L;

	@Override
	protected String afterButtonGroup() {
		if(dmo!=null && !dmo.isNew())
			button_group.append("<input id='btn_remove' value='删除' type='submit'>");
		
		if(dmo.getIsLock()==null || !dmo.getIsLock())
			button_group.append("<input id='btn_lock' value='锁定' type='submit'>");
		else
			button_group.append("<input id='btn_unlock' value='解锁' type='submit'>");
		
		button_group.append("<input id='btn_prev' value='前单' type='submit'>");
		button_group.append("<input id='btn_next' value='后单' type='submit'>");
		return super.afterButtonGroup();
	}

	@Override
	protected String beforeButtonGroup() {
		return super.beforeButtonGroup();
	}
	
	
}
