package com.member07;

import java.util.*;

public class SubMain {

	MemberDAO dao = new MemberDAO();
	
	//1.ȸ�������Է�
	public void menu1(Scanner sc) {
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
	public void menu2() {
		
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
	
	//3. ȸ�������˻�
	public void menu3(Scanner sc) {
		
		System.out.println();
		System.out.println("** ȸ������ v2.3 ** > 3.ȸ�������˻�");
		System.out.println("1.ȸ�����̵�  2.�̸�  3.��ȭ��ȣ  4.�̸���  5.�����  6.����");
		
		int selectNum = sc.nextInt();
		sc.nextLine();
		
		switch(selectNum) {
		case 1: this.menu3_sub1(sc);
			break;
		case 2: this.menu3_sub2(sc);
			break;
		case 3: this.menu3_sub3(sc);
			break;
		case 4: this.menu3_sub4(sc);
			break;
		case 5: this.menu3_sub5(sc);
			break;
		}
		
		
	}
	
	
	public void menu3_sub1(Scanner sc) {
		System.out.print("ȸ�����̵�?> ");
		
		String value = sc.nextLine();
		
		
		List<Member> members = null;

		members = this.dao.memberSearch(1, value);
		
		if(members.size() != 0) {
			
			System.out.println("ȸ�����̵� / �̸� / ��ȭ��ȣ / �̸��� / �����");
			System.out.println("---------------------------------------------------");
			
			for(Member m : members) {
				System.out.println(m);
			}
			
			System.out.println("---------------------------------------------------");
			System.out.printf("�� %s��%n", members.size());
			
		} else {
			System.out.println("�˻������� �����ϴ�!");
		}
		System.out.println();
	}
	
	public void menu3_sub2(Scanner sc) {
		System.out.print("�̸�?> ");
		
		String value = sc.nextLine();
		
		
		List<Member> members = null;

		members = this.dao.memberSearch(2, value);
		
		if(members.size() != 0) {
			
			System.out.println("ȸ�����̵� / �̸� / ��ȭ��ȣ / �̸��� / �����");
			System.out.println("---------------------------------------------------");
			
			for(Member m : members) {
				System.out.println(m);
			}
			
			System.out.println("---------------------------------------------------");
			System.out.printf("�� %s��%n", members.size());
			
		} else {
			System.out.println("�˻������� �����ϴ�!");
		}
		System.out.println();
		
	}
	public void menu3_sub3(Scanner sc) {
		System.out.print("��ȭ��ȣ?> ");
		
		String value = sc.nextLine();
		
		
		List<Member> members = null;

		members = this.dao.memberSearch(3, value);
		
		if(members.size() != 0) {
			
			System.out.println("ȸ�����̵� / �̸� / ��ȭ��ȣ / �̸��� / �����");
			System.out.println("---------------------------------------------------");
			
			for(Member m : members) {
				System.out.println(m);
			}
			
			System.out.println("---------------------------------------------------");
			System.out.printf("�� %s��%n", members.size());
			
		} else {
			System.out.println("�˻������� �����ϴ�!");
		}
		System.out.println();
		
	}
	public void menu3_sub4(Scanner sc) {
		System.out.print("�̸���?> ");
		
		String value = sc.nextLine();
		
		
		List<Member> members = null;

		members = this.dao.memberSearch(4, value);
		
		if(members.size() != 0) {
			
			System.out.println("ȸ�����̵� / �̸� / ��ȭ��ȣ / �̸��� / �����");
			System.out.println("---------------------------------------------------");
			
			for(Member m : members) {
				System.out.println(m);
			}
			
			System.out.println("---------------------------------------------------");
			System.out.printf("�� %s��%n", members.size());
			
		} else {
			System.out.println("�˻������� �����ϴ�!");
		}
		System.out.println();
		
	}
	public void menu3_sub5(Scanner sc) {
		System.out.print("�����?> ");
		
		String value = sc.nextLine();
		
		
		List<Member> members = null;

		members = this.dao.memberSearch(5, value);
		
		if(members.size() != 0) {
			
			System.out.println("ȸ�����̵� / �̸� / ��ȭ��ȣ / �̸��� / �����");
			System.out.println("---------------------------------------------------");
			
			for(Member m : members) {
				System.out.println(m);
			}
			
			System.out.println("---------------------------------------------------");
			System.out.printf("�� %s��%n", members.size());
			
		} else {
			System.out.println("�˻������� �����ϴ�!");
		}
		System.out.println();
		
	}
	
	
	// 4. ȸ������ ����
	public void menu4(Scanner sc) {
	
		// 1.�ܺο��� ȸ����ȣ�� �Է� �޴´�.
		
		System.out.println("������ ������ �˻� ������ �����ϼ���.");
		System.out.println("1. ȸ�����̵� 2.�̸� 3. ��ȭ��ȣ 4. �̸��� 5.�����");
		System.out.print("����> ");
		int selectNum = sc.nextInt();
		sc.nextLine();
		String value = null;
		System.out.println();
		
		switch(selectNum) {
		case 1: System.out.print("ȸ�����̵�?> ");
			break;
		case 2: System.out.print("�̸�?> ");
			break;
		case 3: System.out.print("��ȭ��ȣ?> ");
			break;
		case 4: System.out.print("�̸���?> ");
			break;
		case 5: System.out.println("�����?> ");
			break;
		}
		
		value = sc.nextLine();
		
		// 2. ȸ����ȣ�� �������� ȸ������ �˻� �� ���
		System.out.println("ȸ�����̵� / �̸� / ��ȭ��ȣ / �̸��� / �����");
		System.out.println("---------------------------------------------------");

		List<Member> members = this.dao.memberSearch(selectNum, value);

		
		for(Member m : members) {
			System.out.println(m);
		}
		
		System.out.println("---------------------------------------------------");
		System.out.printf("�� %s��%n", members.size());
		
		// 3. ���� ���� Ȯ��
		if(members.size() != 0) {
			
			System.out.println("���� ȸ���� �����ұ��? (0: ��� /1: ����)> ");
			
			int select = sc.nextInt();
			sc.nextLine();
			
			int result = 0;
			
			// 4. ���� ����
			// 5. ���� �޽��� ���
			if(select == 1) {
				result = this.dao.memberDelete(selectNum, value);
				
				System.out.println(result);
				System.out.printf("�� %s�� ������ �Ϸ�Ǿ����ϴ�. %n", result);	
			} else if(select == 0) {
				System.out.println("��� �Ǿ����ϴ�.");
			}
			
			
		} else {
			System.out.println("�˻� ����� �����ϴ�.");
			System.out.println();
		}
		System.out.println();
	}
	
	
}
