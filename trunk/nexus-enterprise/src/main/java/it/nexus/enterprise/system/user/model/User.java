package it.nexus.enterprise.system.user.model;

import it.nexus.core.annotation.LogicName;
import it.nexus.core.models.Base;
import it.nexus.enterprise.system.authorize.model.Role;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "SYS_USER")
@LogicName("系统用户")
public class User extends Base {
	@ManyToOne(fetch = FetchType.LAZY)
	private Role role;
	private String username;
	private String password;
	private String remark;
	private String spell;
	private Boolean stoped;

	private Boolean isAdmin = false;
	@Version
	private Integer version;

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	public Boolean getStoped() {
		return stoped;
	}

	public void setStoped(Boolean stoped) {
		this.stoped = stoped;
	}

	public Boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
