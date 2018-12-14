package com.members;

public class History {

	// 회원아이디, 과정명, 신청일
	private String hid;
	private String mid;
	private String curName;
	private String curRegDate;
	
	
	public History() {

	}
	
	
	
	public History(String hid, String mid, String curName, String curRegDate) {
		this.hid = hid;
		this.mid = mid;
		this.curName = curName;
		this.curRegDate = curRegDate;
	}




	public String gethid() {
		return hid;
	}
	public void sethid(String hid) {
		this.hid = hid;
	}
	public String getmid() {
		return mid;
	}
	public void setmid(String mid) {
		this.mid = mid;
	}
	public String getcurName() {
		return curName;
	}
	public void setcurName(String curName) {
		this.curName = curName;
	}
	public String getcurRegDate() {
		return curRegDate;
	}
	public void setcurRegDate(String curRegDate) {
		this.curRegDate = curRegDate;
	}


	@Override
	public String toString() {
		return String.format("%s / %s / %s / %s", this.gethid(), this.getmid(), this.getcurName(), this.getcurRegDate());
	}
	
	
	
	
}
