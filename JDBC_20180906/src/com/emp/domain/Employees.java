package com.emp.domain;

public class Employees {

	private String empId, name, ssn, hireDate, phone, regionName, depName, jobName;
	private int basicPay, extraPay, pay;
	
	
	public Employees() {

	}


	public Employees(String empId, String name, String ssn, String hireDate, String phone, String regionName,
			String depName, String jobName, int basicPay, int extraPay, int pay) {
		this.empId = empId;
		this.name = name;
		this.ssn = ssn;
		this.hireDate = hireDate;
		this.phone = phone;
		this.regionName = regionName;
		this.depName = depName;
		this.jobName = jobName;
		this.basicPay = basicPay;
		this.extraPay = extraPay;
		this.pay = pay;
	}


	public String getEmpId() {
		return empId;
	}


	public void setEmpId(String empId) {
		this.empId = empId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSsn() {
		return ssn;
	}


	public void setSsn(String ssn) {
		this.ssn = ssn;
	}


	public String getHireDate() {
		return hireDate;
	}


	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getRegionName() {
		return regionName;
	}


	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}


	public String getDepName() {
		return depName;
	}


	public void setDepName(String depName) {
		this.depName = depName;
	}


	public String getJobName() {
		return jobName;
	}


	public void setJobName(String jobName) {
		this.jobName = jobName;
	}


	public int getBasicPay() {
		return basicPay;
	}


	public void setBasicPay(int basicPay) {
		this.basicPay = basicPay;
	}


	public int getExtraPay() {
		return extraPay;
	}


	public void setExtraPay(int extraPay) {
		this.extraPay = extraPay;
	}


	public int getPay() {
		return pay;
	}

	
	public void setPay(int pay) {
		this.pay = pay;
	}


	@Override
	public String toString() {
		return String.format("%s / %s / %s / %s / %s / %s / %s / %s / %,d / %,d / %,d "
				, this.getEmpId(), this.getName(), this.getSsn(), this.getHireDate()
				, this.getPhone(), this.getRegionName(), this.getDepName()
				, this.getJobName(), this.getBasicPay(), this.getExtraPay(), this.getPay());
	}

	
	
	
	
	
	
	
}
