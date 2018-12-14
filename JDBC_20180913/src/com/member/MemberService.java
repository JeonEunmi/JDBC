package com.member;

import java.util.List;
import java.util.Scanner;

public class MemberService {


	private MemberDAO dao = new MemberDAO();
	
	//1.ȸ�������Է�
	public void menu1(Scanner sc) {
		System.out.println("�ű� ȸ�� ������ �Է��ϴ� �����Դϴ�.");
		//ȸ�����̵� -> �ڵ� ����
		//�̸�, ��ȭ��ȣ, �̸��� -> �ܺ� �Է�
		//����� -> �ý����� ���� ��¥ 
		System.out.print("�̸�>");
		String name = sc.nextLine();
		System.out.print("��ȭ��ȣ>");
		String phone = sc.nextLine();
		System.out.print("�̸���>");
		String email = sc.nextLine();
		Member temp = new Member(null, name, phone, email, null);
		
		System.out.printf("�Է��Ͻ� ������ %n �̸� : %s , ��ȭ��ȣ : %s , �̸��� : %s �Դϴ�. %n",name,phone,email);
		System.out.print("�ű� ȸ�� ������ ����ұ��? (0/1) > ");
		int select = sc.nextInt();
		sc.nextLine();
		
		if(select == 1) {
			int result = this.dao.memberAdd(temp);
			System.out.printf("%s �� ��(��) ���ԵǾ����ϴ�.%n", result);
		} else if(select == 0) {
			System.out.println("ȸ������� ��ҵǾ����ϴ�.");
		} else {
			System.out.println("�߸� �����̽��ϴ�. �ٽ� �Է����ּ���.");
		}
	}
	
	
	//2.ȸ��������ü���
	public void menu2() {
		System.out.println();
		System.out.println("ȸ�����̵� / �̸� / ��ȭ��ȣ / �̸��� / �����");
		System.out.println("----------------------------------------------");
		List<Member> list = this.dao.memberList();
		for (Member m : list) {
			System.out.println(m.toString());
		}	
		System.out.println("----------");
		System.out.printf("�� %s��%n", list.size());		
	}
	
}
