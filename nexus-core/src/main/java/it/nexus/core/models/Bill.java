package it.nexus.core.models;

import it.nexus.core.annotation.LogicName;
import it.nexus.core.models.enums.BILLSTATE;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public class Bill extends Entity {
	@LogicName("单据状态")
	private BILLSTATE billState = BILLSTATE.NOCHECK;
	@LogicName("审核日期")
	private Date checkDate;
//	@LogicName("审核人")
//	@OneToOne
//	@JoinColumn(name = "checkUser_id")
//	private User checkUser;
//	@LogicName("创建人")
//	@OneToOne
//	@JoinColumn(name = "createUser_id")
//	private User createUser;
	@LogicName("完成时间")
	private Date finishDate;
//	@LogicName("完成人")
//	@OneToOne
//	@JoinColumn(name = "finishUser_id")
//	private User finishUser;
//	@LogicName("部门")
//	@OneToOne
//	@JoinColumn(name = "dept_id")
//	private Dept dept;
	@LogicName("")
	private boolean closed;
	@LogicName("")
	private long modifyUser_ID;
	@LogicName("")
	private String modifyUser_Name;
	@LogicName("")
	private int printDegree;
	@LogicName("")
	private long staff_ID;
	@LogicName("")
	private String staff_Name;

	@Enumerated(EnumType.ORDINAL)
	public BILLSTATE getBillState() {
		return billState;
	}

	public void setBillState(BILLSTATE billState) {
		this.billState = billState;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

//	public Dept getDept() {
//		return dept;
//	}
//
//	public void setDept(Dept dept) {
//		this.dept = dept;
//	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public long getModifyUser_ID() {
		return modifyUser_ID;
	}

	public void setModifyUser_ID(long modifyUser_ID) {
		this.modifyUser_ID = modifyUser_ID;
	}

	public String getModifyUser_Name() {
		return modifyUser_Name;
	}

	public void setModifyUser_Name(String modifyUser_Name) {
		this.modifyUser_Name = modifyUser_Name;
	}

	public int getPrintDegree() {
		return printDegree;
	}

	public void setPrintDegree(int printDegree) {
		this.printDegree = printDegree;
	}

	public long getStaff_ID() {
		return staff_ID;
	}

	public void setStaff_ID(long staff_ID) {
		this.staff_ID = staff_ID;
	}

	public String getStaff_Name() {
		return staff_Name;
	}

	public void setStaff_Name(String staff_Name) {
		this.staff_Name = staff_Name;
	}

//	public User getCheckUser() {
//		return checkUser;
//	}
//
//	public void setCheckUser(User checkUser) {
//		this.checkUser = checkUser;
//	}
//
//	public User getCreateUser() {
//		return createUser;
//	}
//
//	public void setCreateUser(User createUser) {
//		this.createUser = createUser;
//	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

//	public User getFinishUser() {
//		return finishUser;
//	}
//
//	public void setFinishUser(User finishUser) {
//		this.finishUser = finishUser;
//	}

}
