package it.nexus.enterprise.system.authorize.model;

import it.nexus.core.annotation.LogicName;
import it.nexus.core.models.BaseTree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 权限 - 角色
 * 
 * @author dcriori
 * 
 */
@Entity
@Table(name = "SYS_ROLE")
@LogicName("角色")
@NamedQueries( { @NamedQuery(name = "Role.findAll", query = "SELECT id,name FROM Role role") })
public class Role extends BaseTree implements Serializable {
	private static final long serialVersionUID = 2994367232031808014L;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="role_id")
	private List<RoleAction> roleactions = new ArrayList<RoleAction>();
	private String plugin;
	private String action;

	public String getAction() {
		return action;
	}

	public void addRoleAction(RoleAction ra) {
		roleactions.add(ra);
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getPlugin() {
		return plugin;
	}

	public void setPlugin(String plugin) {
		this.plugin = plugin;
	}

	public List<RoleAction> getRoleactions() {
		return roleactions;
	}

	public void setRoleactions(List<RoleAction> roleactions) {
		this.roleactions = roleactions;
	}

	public Role getTemparent() {
		return (Role) getParent();
	}

	public void setTemparent(Role temparent) {
		this.parent = temparent;
	}

}
