package com.test001;

import java.util.*;

//���̺귯�� Ŭ����
//Object ���
//Object Ŭ������ �ֿ� ��� -> toString(), equals(), clone()
public class Sample {

	// �ʵ� -> ����¿� ���õ� ��� �׸�. private ����.
	private int a;

	// �ʵ忡 ����� �ڷῡ ���� �ܺ� ���� -> getter, setter
	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	// ��� ���� �޼ҵ� -> toString() �������̵� �޼ҵ�
	// super -> ���� ��ü�� �θ� ���� ������ ������ ����
	// this -> ���� ��ü�� �ڱ� �ڽſ� ���� ������ ������ ����
	@Override
	public String toString() {
		return String.format( "%s", this.a);
	}


}