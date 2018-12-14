package com.members;

//��� ���� �ڷ��� Ŭ����
public class HistoryMember {
	
	//������û���̵�, ������, ȸ�����̵�, �̸�, ��ȭ��ȣ, ��û��
	private String hid, curName, mid, name, phone, curRegDate;
	
	
	public HistoryMember() {

	}

	//�Ű����� �ִ� ������
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
