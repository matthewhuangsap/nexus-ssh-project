package it.nexus.core.models;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class WithCodeBaseInfo extends BaseInfo implements IWithCodeBaseInfo {
	private String code;

	public String getCode() {
		return this.code;
	}

	public void setCode(String value) {
		this.code = value;
	}

}
