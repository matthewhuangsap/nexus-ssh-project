package it.nexus.enterprise.system.authorize.controller;

import it.nexus.enterprise.system.authorize.model.Role;
import it.nexus.enterprise.system.authorize.service.AuthorizeService;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.util.StrutsTypeConverter;

@SuppressWarnings("unchecked")
public class RoleConverter extends StrutsTypeConverter {
	@Resource
	AuthorizeService authorizeService;
	@Override
	public Object convertFromString(Map arg0, String[] values, Class toClazz) {
		String role_id = values[0];
		Role role = authorizeService.load(role_id);
		return role;
	}

	@Override
	public String convertToString(Map arg0, Object arg1) {
		// TODO ROLE 权限converter 
		return null;
	}
	/**Dec 14, 2009
	 *RoleConverter.java
	 *Administrator
	 */
}
