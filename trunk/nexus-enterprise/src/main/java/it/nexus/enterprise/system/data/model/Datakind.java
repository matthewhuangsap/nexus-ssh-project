package it.nexus.enterprise.system.data.model;

public class Datakind {
	private String key;
	private String value;
	
	public Datakind(){}
	
	public Datakind(String key,String value){
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
