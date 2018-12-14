package com.member05;

public class Member {
	
	
	private String mid, name_, phone, email, regDate;

	public Member() {

	}

	public Member(String mid, String name_, String phone, String email, String regDate) {
		this.mid = mid;
		this.name_ = name_;
		this.phone = phone;
		this.email = email;
		this.regDate = regDate;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getName_() {
		return name_;
	}

	public void setName_(String name_) {
		this.name_ = name_;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return String.format("%s / %s / %s  / %s / %s", this.getMid(), this.getName_(), this.getPhone()	
				,this.getEmail(), this.getRegDate());
	}
	
	
	

}
