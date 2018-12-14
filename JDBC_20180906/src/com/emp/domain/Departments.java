package com.emp.domain;

public class Departments {

	private String depId, depName;

	public Departments() {
		
	}

	public Departments(String depId, String depName) {
		this.depId = depId;
		this.depName = depName;
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

	@Override
	public String toString() {
		return String.format("%s / %s", this.getDepId(), this.getDepName());
	}
	
	
	
	
	
}
