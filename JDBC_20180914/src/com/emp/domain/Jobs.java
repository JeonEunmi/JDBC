package com.emp.domain;

public class Jobs {

	private String jobId, jobTitle;
	private int minPay, count;
	
	
	public Jobs() {

	}


	public Jobs(String jobId, String jobTitle, int minPay) {
		this.jobId = jobId;
		this.jobTitle = jobTitle;
		this.minPay = minPay;
	}
	
	public Jobs(String jobId, String jobTitle, int minPay, int count) {
		this.jobId = jobId;
		this.jobTitle = jobTitle;
		this.minPay = minPay;
		this.count = count;
	}


	public String getJobId() {
		return jobId;
	}


	public void setJobId(String jobId) {
		this.jobId = jobId;
	}


	public String getJobTitle() {
		return jobTitle;
	}


	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}


	public int getMinPay() {
		return minPay;
	}


	public void setMinPay(int minPay) {
		this.minPay = minPay;
	}


	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String print1() {
		return String.format("%s / %s / %,d", this.getJobId(), this.getJobTitle(), this.getMinPay());
	}
	
	
	public String print2() {
		return String.format("%s / %s / %,d / %s", this.getJobId(), this.getJobTitle(), this.getMinPay()
				, this.getCount() == 0 ? "O" : "X");
	}
		
	
	
	
	
	
}
