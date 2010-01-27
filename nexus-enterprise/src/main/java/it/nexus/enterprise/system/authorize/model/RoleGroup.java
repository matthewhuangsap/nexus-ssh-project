package it.nexus.enterprise.system.authorize.model;

import it.nexus.core.models.BaseInfo;

import java.util.List;

/**
 * 权限 - 角色组
 * 
 * @author dcriori
 * 
 */
public class RoleGroup extends BaseInfo {
	private List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
