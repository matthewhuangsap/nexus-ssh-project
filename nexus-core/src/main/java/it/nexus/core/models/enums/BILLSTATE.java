package it.nexus.core.models.enums;


public enum BILLSTATE implements IEnum<Long>{
	NOCHECK(0L,"未审核"),
	CHECKED(1L,"已审核"),
	COMPLETE(2L,"已完毕"),
	INVALID(3L,"已作废");
	
	
	private Long key;
	private String value;
	
	BILLSTATE(Long key,String value){
		this.key = key;
		this.value = value;
	}
	@Override
	public Long key() {
		return key;
	}
	@Override
	public String value() {
		return value;
	}
}
