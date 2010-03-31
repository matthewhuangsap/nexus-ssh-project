package it.nexus.core.models.enums;

import org.apache.commons.lang.enums.ValuedEnum;

public class BaseOperation extends ValuedEnum {
	public static final BaseOperation 新建 = new BaseOperation("新建", 0);
	public static final BaseOperation 保存 = new BaseOperation("保存", 1);
	public static final BaseOperation 访问 = new BaseOperation("访问", 2);
	public static final BaseOperation 编辑 = new BaseOperation("编辑", 3);
	public static final BaseOperation 删除 = new BaseOperation("删除", 4);
	public static final BaseOperation 锁定 = new BaseOperation("锁定", 5);
	public static final BaseOperation 解锁 = new BaseOperation("解锁", 6);
	public static final BaseOperation 审核 = new BaseOperation("审核", 7);
	public static final BaseOperation 前单 = new BaseOperation("前单", 8);
	public static final BaseOperation 后单 = new BaseOperation("后单", 9);
	public static final BaseOperation 作废 = new BaseOperation("作废", 10);
	public static final BaseOperation 停用 = new BaseOperation("停用", 11);
	public static final BaseOperation 启用 = new BaseOperation("启用", 12);
	public static final BaseOperation 完毕 = new BaseOperation("完毕", 13);
	public static final BaseOperation 撤销 = new BaseOperation("撤销", 14);
	
	protected BaseOperation(String name, int value) {
		super(name, value);
	}

	private static final long serialVersionUID = -3884760431475638770L;
	
}
