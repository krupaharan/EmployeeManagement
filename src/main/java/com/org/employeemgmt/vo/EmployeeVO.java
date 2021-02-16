package com.org.employeemgmt.vo;

public class EmployeeVO extends BaseVO {

	private long empId;
	
	private String empName;
	
	private int age;
	
	public EmployeeVO(BaseVO baseVO) {
		super(baseVO.getStatus(), baseVO.getStatusMessage());
	}
	
	public EmployeeVO(BaseVO baseVO, long empId, String empName, int age) {
		super(baseVO.getStatus(), baseVO.getStatusMessage());
		this.empId = empId;
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
