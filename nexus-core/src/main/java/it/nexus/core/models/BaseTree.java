package it.nexus.core.models;

import it.nexus.core.models.BaseInfo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "SYS_BASETREE")
@Inheritance(strategy = InheritanceType.JOINED)
public class BaseTree extends BaseInfo implements Serializable {
	private static final long serialVersionUID = -6302515221641872592L;
	/**Dec 11, 2009
	 *BaseTree.java
	 * dcriori
	 */
	
	/** Level分层标记，用来记录树形的级别，方便取子一级的数据 */  
    public static final String LEVEL_SPLIT = "|";  
    /** level */  
    protected String level;  
    
	@ManyToOne(fetch = FetchType.LAZY)
	protected BaseTree parent;
	
	@OneToMany(fetch = FetchType.LAZY,
            mappedBy="parent",cascade = CascadeType.ALL)
	protected List<BaseTree> childs;

	public BaseTree getParent() {
		return parent;
	}

	public void setParent(BaseTree parent) {
		this.parent = parent;	
	}

	public List<BaseTree> getChilds() {
		return childs;
	}

	public void setChilds(List<BaseTree> childs) {
		this.childs = childs;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	
}
