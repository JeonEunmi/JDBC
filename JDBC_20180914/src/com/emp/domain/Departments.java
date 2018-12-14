package com.emp.domain;

public class Departments {

	private String depId, depName;
	private int count;

	public Departments() {
		
	}

	public Departments(String depId, String depName) {
		this.depId = depId;
		this.depName = depName;
	}
	
	public Departments(String depId, String depName, int count) {
		this.depId = depId;
		this.depName = depName;
		this.count = count;
	}

	public String getDepId() {
		return depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String print1() {
		return String.format("%s / %s", this.getDepId(), this.getDepName());
	}
	
	public String print2() {
		return String.format("%s / %s / %s", this.getDepId(), this.getDepName()
				, this.getCount() == 0 ? "O" : "X");
	}
		
	
	
	
}
