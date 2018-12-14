package com.emp.domain;

public class Regions {

	private String regId, regName;
	private int count;

	public Regions() {

	}
	
	public Regions(String regId, String regName) {
		this.regId = regId;
		this.regName = regName;
	}

	public Regions(String regId, String regName, int count) {
		this.regId = regId;
		this.regName = regName;
		this.count = count;
	}

	public String getregId() {
		return regId;
	}

	public void setregId(String regId) {
		this.regId = regId;
	}

	public String getRegName() {
		return regName;
	}

	public void setRegName(String regName) {
		this.regName = regName;
	}
	

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String print1() {
		return String.format("%s / %s", this.getregId(), this.getRegName());
	}
	
	public String print2() {
		return String.format("%s / %s / %s", this.getregId(), this.getRegName()
				, this.getCount() == 0 ? "O" : "X");
	}
		
}
