package com.org.employeemgmt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name="employees")
public class EmployeeEntity {

	@Id
	@GeneratedValue(generator = "SEQ_THREAD_ID", strategy= GenerationType.SEQUENCE)
	@SequenceGenerator(name= "SEQ_THREAD_ID", sequenceName = "EMPID_SEQ")
	@Column(name="emp_id", unique=true, nullable=false)
	private long empId;
	
	@Column(name="emp_name")
	private String empName;
	
	@Column(name="age")
	private int age;
	
	public EmployeeEntity() {
		
	}

	public EmployeeEntity(String empName, int age) {
		super();
		this.empName = empName;
		this.age = age;
	}

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}