package com.members;

//�ڷ��� Ŭ����
public class Member {

	//ȸ�����̵�(M01, M02, M03, ...), �̸�, ��ȭ��ȣ, �����
	// ������1, ������2, ...
	private String mid;
	private String name;
	private String phone;
	private String regDate;
	
		
	public Member() {

	}
	
		
	public Member(String mid, String name, String phone, String regDate) {
		this.mid = mid;
		this.name = name;
		this.phone = phone;
		this.regDate = regDate;
	}



	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getregDate() {
		return regDate;
	}
	public void setregDate(String regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return String.format("%s / %s / %s / %s", this.getMid(), this.getName(), this.getPhone(), this.getregDate());
	}
	
}
