package com.members;

//출력 전용 자료형 클래스
public class HistoryMember {
	
	//과정신청아이디, 과정명, 회원아이디, 이름, 전화번호, 신청일
	private String hid, curName, mid, name, phone, curRegDate;
	
	
	public HistoryMember() {

	}

	//매개변수 있는 생성자
	public HistoryMember(String hid, String curName, String mid, String name, String phone, String curRegDate) {
		this.hid = hid;
		this.curName = curName;
		this.mid = mid;
		this.name = name;
		this.phone = phone;
		this.curRegDate = curRegDate;
	}

	//getter
	public String getHid() {
		return hid;
	}

	public String getCurName() {
		return curName;
	}

	public String getMid() {
		return mid;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getCurRegDate() {
		return curRegDate;
	}

	@Override
	public String toString() {
		return String.format("%s / %s / %s / %s / %s / %s", this.getHid(), this.getCurName()
				,this.getMid(), this.getName(), this.getPhone(), this.getCurRegDate());
	}
	
	


	
	
}
