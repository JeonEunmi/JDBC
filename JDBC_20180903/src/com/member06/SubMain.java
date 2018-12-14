package com.member06;

import java.util.*;

public class SubMain {

	MemberDAO dao = new MemberDAO();
	
	//1.ȸ�������Է�
	public void memberAdd(Scanner sc) {
		System.out.print("�̸�> ");
		String name = sc.nextLine();
		
		System.out.print("��ȭ��ȣ> ");
		String phone = sc.nextLine();
		
		System.out.print("�̸���> ");
		String email = sc.nextLine();
		
		
		Member m = new Member(null, name, phone, email, null);
		
		this.dao.memberAdd(m);
		
		
		System.out.println("ȸ�� ����� �Ϸ�Ǿ����ϴ�.");
		
		System.out.println();
		
		
	}
	
	//2. ȸ��������ü���
	public void memberPrintAll() {
		
		System.out.println("ȸ�����̵� / �̸� / ��ȭ��ȣ / �̸��� / �����");
		System.out.println("---------------------------------------------------");
		
		List<Member> m = this.dao.memberPrintAll();
		
		for(Member member : m) {
			System.out.println(member);
		}
		
		System.out.println("---------------------------------------------------");
		
		System.out.printf("�� %s��%n", m.size());
		
		System.out.println();
	}
	
	
	
}
