package it.nexus.enterprise.system.employee.model;

import it.nexus.core.annotation.LogicName;
import it.nexus.enterprise.baseinfo.model.WithCodeBaseInfo;
import it.nexus.enterprise.system.dept.model.Dept;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SYS_Employee")
@LogicName("员工")
@NamedQueries( { 
       @NamedQuery(name = "Employee.findAll", 
               query = "SELECT id,name FROM Employee employee ORDER BY id") }) 
public class Employee extends WithCodeBaseInfo implements Cloneable  {
	@LogicName("部门")
	@OneToOne(fetch = FetchType.LAZY)
	private Dept dept;
	@LogicName("性别")
	private String gender;
	@LogicName("出生日期")
	private Date birthday;
	@LogicName("职位")
	private String post;
	@LogicName("身份证号")
	private String idCardNo;
	@LogicName("户籍地址")
	private String residenceAddress;
	@LogicName("居住地址")
	private String address;
	@LogicName("学历")
	private String educationalLevel;
	@LogicName("联系方式")
	private String contact;
	@LogicName("毕业院校")
	private String graduatedFrom;
	@LogicName("专业")
	private String major;
	@LogicName("邮编")
	private String postCode;

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getResidenceAddress() {
		return residenceAddress;
	}

	public void setResidenceAddress(String residenceAddress) {
		this.residenceAddress = residenceAddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEducationalLevel() {
		return educationalLevel;
	}

	public void setEducationalLevel(String educationalLevel) {
		this.educationalLevel = educationalLevel;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getGraduatedFrom() {
		return graduatedFrom;
	}

	public void setGraduatedFrom(String graduatedFrom) {
		this.graduatedFrom = graduatedFrom;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	
	/**
     * @return 创建并返回此对象的一个副本。
     * @throws CloneNotSupportedException
     */
    public Employee clone() throws CloneNotSupportedException {
        //直接调用父类的clone()方法,返回克隆副本
        return (Employee)super.clone();
    }
}
