package com.test001;

import java.util.*;

//라이브러리 클래스
//Object 상속
//Object 클래스의 주요 멤버 -> toString(), equals(), clone()
public class Sample {

	// 필드 -> 입출력에 관련된 모든 항목. private 권장.
	private int a;

	// 필드에 저장된 자료에 대한 외부 접근 -> getter, setter
	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	// 출력 전용 메소드 -> toString() 오버라이딩 메소드
	// super -> 현재 객체의 부모에 대한 정보를 저장한 변수
	// this -> 현재 객체의 자기 자신에 대한 정보를 저장한 변수
	@Override
	public String toString() {
		return String.format( "%s", this.a);
	}


}