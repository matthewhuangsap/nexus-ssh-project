package it.nexus.enterprise.system.dept.model;

import it.nexus.core.annotation.LogicName;
import it.nexus.enterprise.baseinfo.model.BaseTree;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="SYS_DEPT")
@LogicName("部门") 
@NamedQueries( { 
       @NamedQuery(name = "Dept.findAll", 
               query = "SELECT id,name FROM Dept dept ORDER BY level") }) 
public class Dept extends BaseTree {
	private String time;
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

    public Dept getTemparent() {
		return (Dept) getParent();
	}

	public void setTemparent(Dept temparent) {
		this.parent = temparent;
	}
}
