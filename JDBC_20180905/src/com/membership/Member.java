package com.membership;

import java.sql.*;

public class Member {

	private String mid, name, phone, email;
	private Date regDate, payDate;
	private int feeTotal, feeCount, fee;
	
	
	
	public Member() {

	}
	
	public Member(String mid, int fee) {
		this.mid = mid;
		this.fee = fee;
	}
	
	
	public Member(String name, String phone, String email) {
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	
	public Member(String mid, String name, String phone, String email, Date regDate
			,int feeTotal, int feeCount) {
		this.mid = mid;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.regDate = regDate;
		this.feeTotal = feeTotal;
		this.feeCount = feeCount;
	}
	
	
	public Member(String mid, String name, String phone, int fee, Date payDate) {
		this.mid = mid;
		this.name = name;
		this.phone = phone;
		this.fee = fee;
		this.payDate = payDate;
	}
	
	
	public Member(String mid, int fee, Date payDate) {
		this.mid = mid;
		this.fee = fee;
		this.payDate = payDate;
	}
	
	
	
	
	public String getMid() {
		return this.mid;
	}
	
	public void setMid(String mid) {
		this.mid = mid;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getRegDate() {
		return this.regDate;
	}
	
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	public int getfeeTotal() {
		return this.feeTotal;
	}
	
	public int getfeeCount() {
		return this.feeCount;
	}
	
	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}



	public String print1() {
		return String.format("%s / %s / %s / %s / %tF / %,d / %d", this.getMid()
				,this.getName(), this.getPhone(), this.getEmail(), this.getRegDate()
				, this.getfeeTotal(), this.getfeeCount());
	}
	
	
	public String print2() {
		return String.format("%s / %s / %s %,d / %tF", this.getMid()
				,this.getName(), this.getPhone(), this.getFee(), this.getPayDate());
	}
	
	
	public String print3() {
		return String.format("%s / %,d / %tF", this.getMid()
				,this.getFee(), this.getPayDate());
	}
	
	
	
}
