package com.emp.domain;

// 자료형 클래스
public class Login {

	
	// 아이디, 패스워드
	private String id, pw;

	
	public Login() {

	}

	public Login(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}
	
	
	
	
	
}
