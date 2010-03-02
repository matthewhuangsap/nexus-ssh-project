package it.nexus.enterprise.system.authorize.model;

import it.nexus.core.models.Base;

import javax.persistence.*;

@Entity
@Table(name="SYS_ROLEACTION")
public class RoleAction extends Base {
	/**
	 * Dec 15, 2009 RoleAction.java Administrator
	 */
	@ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private Role role;

	private String name;
	private Long rolebits;
	private String plugin;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getRolebits() {
		return rolebits;
	}

	public void setRolebits(Long rolebits) {
		this.rolebits = rolebits;
	}

	public String getPlugin() {
		return plugin;
	}

	public void setPlugin(String plugin) {
		this.plugin = plugin;
	}

}
