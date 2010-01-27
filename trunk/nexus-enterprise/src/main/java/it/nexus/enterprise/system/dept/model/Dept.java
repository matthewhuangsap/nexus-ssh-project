package it.nexus.enterprise.system.dept.model;

import it.nexus.core.annotation.LogicName;
import it.nexus.core.models.BaseInfo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SYS_DEPT")
@MappedSuperclass
@LogicName("部门") 
@NamedQueries( { 
       @NamedQuery(name = "Dept.findAll", 
               query = "SELECT id,name+'|'+remark FROM Dept dept ORDER BY level") }) 
public class Dept extends BaseInfo {
	private String time;
	
	/** Level分层标记 */  
    public static final String LEVEL_SPLIT = "|";  
    /** level */  
    private String level;  
	/** 下级的类别 */  
    @OneToMany(fetch = FetchType.LAZY, mappedBy="parent",cascade = CascadeType.ALL)  
//    @OrderBy("id")  
    private List<Dept> children = new ArrayList<Dept>();  
    
    /** 上级的类别 */  
    @ManyToOne  
    @JoinColumn(name = "category_id")  
    private Dept parent;

	public List<Dept> getChildren() {
		return children;
	}

	public void setChildren(List<Dept> children) {
		this.children = children;
	}

	public Dept getParent() {
		return parent;
	}

	public void setParent(Dept parent) {
		this.parent = parent;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
