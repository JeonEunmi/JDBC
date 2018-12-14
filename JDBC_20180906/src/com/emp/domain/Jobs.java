package com.emp.domain;

public class Jobs {

	private String jobId, jobTitle;
	private int minPay;
	
	
	public Jobs() {

	}


	public Jobs(String jobId, String jobTitle, int minPay) {
		this.jobId = jobId;
		this.jobTitle = jobTitle;
		this.minPay = minPay;
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


	@Override
	public String toString() {
		return String.format("%s / %s / %,d", jobId, jobTitle, minPay);
	}
	
	
	
	
	
	
}
