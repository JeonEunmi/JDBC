package com.member;

import java.sql.Date;

public class Member {

	//필드 구성
	//->출력, 입력에 관여하는 모든 항목
	//->회원아이디, 이름, 전화번호, 이메일, 등록일
	//->동일 자료, 동일 식별자
	private String mid_, name_, phone, email;
	private Date regDate;
	
	//매개변수 있는 생성자
	public Member(String mid_, String name_, String phone, String email, Date regDate) {
		this.mid_ = mid_;
		this.name_ = name_;
		this.phone = phone;
		this.email = email;
		this.regDate = regDate;
	}

	//getter, setter
	public String getMid_() {
		return mid_;
	}

	public void setMid_(String mid_) {
		this.mid_ = mid_;
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

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	//toString()
	@Override
	public String toString() {
		return String.format("%s / %s / %s / %s / %tF"
				, this.getMid_(), this.getName_(), this.getPhone(), this.getEmail()
				, this.getRegDate());
	}
}
