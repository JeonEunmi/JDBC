package com.membership;

import java.util.List;
import java.util.Scanner;

public class MembershipService {

	MembershipDAO dao = new MembershipDAO();

	// ȸ�� �Է�
	public void menu1(Scanner sc) {

		System.out.print("�̸�> ");
		String name = sc.nextLine();

		System.out.print("��ȭ��ȣ> ");
		String phone = sc.nextLine();

		System.out.print("�̸���> ");
		String email = sc.nextLine();

		int tmp = this.dao.membershipAdd(new Member(name, phone, email));

		System.out.printf(" %s ���� ȸ���� ��ϵǾ����ϴ�.%n", tmp);

		System.out.println();

	}

	// ��üȸ�����
	public void menu2() {

		System.out.println();
		System.out.println("ȸ�����̵� / �̸� / ��ȭ��ȣ / �̸��� / ����� / ȸ�񳳺��Ѿ� / ȸ�񳳺ΰǼ�");
		System.out.println("==============================================================================");
		List<Member> m = this.dao.membershipPrint();

		for (Member member : m) {
			System.out.println(member.print1());
		}

		System.out.println("==============================================================================");

		System.out.printf("�� %s��", m.size());

		System.out.println();
	}

	public void menu3(Scanner sc) {

		System.out.println();
		System.out.println("** ȸ������ v2.4 ** > 3.ȸ�������˻�");
		System.out.println("1.ȸ�����̵�  2.�̸�  3.��ȭ��ȣ  4.�̸���  5.�����  6.����");

		System.out.print("����> ");

		int key = sc.nextInt();
		sc.nextLine();

		System.out.print("�˻������Է�> ");

		String value = sc.nextLine();
		List<Member> m = this.dao.membershipSearch(key, value);

		if(m.size() != 0) {
			
		System.out.println();
		System.out.println("ȸ�����̵� / �̸� / ��ȭ��ȣ / �̸��� / ����� / ȸ�񳳺��Ѿ� / ȸ�񳳺ΰǼ�");
		System.out.println("==============================================================================");


		for (Member member : m) {
			System.out.println(member.print1());
		}

		System.out.println("==============================================================================");

		System.out.printf("�� %s��", m.size());

		} else {
			System.out.println("�˻������ �����ϴ�.");
		}
		System.out.println();

	}

	public void menu4(Scanner sc) {

		System.out.print("ȸ�����̵�> ");
		String mid = sc.nextLine();

		List<Member> m = this.dao.membershipSearch(1, mid);

		if (m.size() != 0) {

			System.out.println();
			System.out.println("ȸ�����̵� / �̸� / ��ȭ��ȣ / �̸��� / ����� / ȸ�񳳺��Ѿ� / ȸ�񳳺ΰǼ�");
			System.out.println("==============================================================================");

			for (Member member : m) {
				System.out.println(member.print1());
			}

			System.out.println("==============================================================================");
			System.out.printf("�� %s��", m.size());

			System.out.println();
			List<Member> m2 = this.dao.memberFeePrint(mid);
			
			if(m2.size() != 0) {
				
			System.out.println("ȸ�����̵� / ȸ��ݾ� / ȸ�񳳺���");
			System.out.println("====================================");


			for (Member member : m2) {
				System.out.println(member.print3());
			}
			System.out.println("====================================");
			System.out.printf("�� %s��", m2.size());
			System.out.println();
			} else {
				System.out.println();
				System.out.println("ȸ�񳳺γ����� �����ϴ�.");
				System.out.println();
			}

			System.out.print("ȸ���� ȸ�� ���� ������ ���� �����˴ϴ�. ���� ȸ���� �����ұ��(0/1)?  ");
			int select = sc.nextInt();
			sc.nextLine();

			if (select == 1) {
				this.dao.membershipDelete(mid);
				System.out.println("ȸ������ �� ȸ�񳳺γ����� �����Ǿ����ϴ�.");
			} else {
				System.out.println("��� �Ǿ����ϴ�.");
			}
			

		}else {
			System.out.println("�˻������ �����ϴ�.");
		}
	}

	public void menu5(Scanner sc) {

		while (true) {

			System.out.println();
			System.out.println("** ȸ������ v2.4 ** > 5.ȸ�����");
			System.out.println("1.ȸ����ȸ�񳳺�  2.ȸ����ȸ�񳳺γ������  3.��üȸ��ȸ�񳳺γ������ 4.����");

			System.out.print("����> ");
			int selectNum = sc.nextInt();
			sc.nextLine();

			if (selectNum == 4) {
				break;
			}

			switch (selectNum) {

			case 1:
				this.menu5_sub1(sc);
				break;
			case 2:
				this.menu5_sub2(sc);
				break;
			case 3:
				this.menu5_sub3();
				break;
			default:
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
			}

		}
	}

	public void menu5_sub1(Scanner sc) {

		System.out.print("ȸ�����̵�> ");
		String mid = sc.nextLine();

		List<Member> m = this.dao.membershipSearch(1, mid);
		if (m.size() != 0) {

			System.out.println();
			System.out.println("ȸ�����̵� / �̸� / ��ȭ��ȣ / �̸��� / ����� / ȸ�񳳺��Ѿ� / ȸ�񳳺ΰǼ�");
			System.out.println("==============================================================================");

			for (Member member1 : m) {
				System.out.println(member1.print1());
			}

			System.out.println("==============================================================================");
			System.out.printf("�� %s��", m.size());

			System.out.println();
			System.out.print("ȸ�� �ݾ�> ");
			int pay = sc.nextInt();
			sc.nextLine();
			
			// ȸ�񳳺�
			this.dao.membershipFee(new Member(mid, pay));
			
			System.out.printf(" %,d���� ȸ�� ���εǾ����ϴ�.", pay);
			System.out.println();

		} else {
			System.out.println("�˻������ �����ϴ�.");
		}
	}

	public void menu5_sub2(Scanner sc) {

		System.out.print("ȸ�����̵�> ");
		String mid = sc.nextLine();

		System.out.println();

		List<Member> m = this.dao.membershipSearch(1, mid);

		if (m.size() != 0) {
			System.out.println("ȸ�����̵� / �̸� / ��ȭ��ȣ / �̸��� / ����� / ȸ�񳳺��Ѿ� / ȸ�񳳺ΰǼ�");
			System.out.println("==============================================================================");

			for (Member member : m) {
				System.out.println(member.print1());
			}

			System.out.println("==============================================================================");
			System.out.printf("�� %s��", m.size());

			List<Member> m2 = this.dao.memberFeePrint(mid);
			
			if(m2.size() != 0) {
				
			System.out.println();
			System.out.println("ȸ�����̵� / ȸ��ݾ� / ȸ�񳳺���");
			System.out.println("====================================");


			for (Member member2 : m2) {
				System.out.println(member2.print3());
			}
			System.out.println("====================================");
			System.out.printf("�� %s��", m2.size());
			} else {
				System.out.println("*");
				System.out.println("ȸ�� ���� ������ �����ϴ�.!");
			}
		} else {
			System.out.println("�˻������ �����ϴ�.");
		}
		System.out.println();
	}

	public void menu5_sub3() {

		System.out.println();
		List<Member> m = this.dao.memberFeePrintAll();

		if (m.size() != 0) {

			System.out.println("ȸ�����̵� / �̸� / ��ȭ��ȣ / ȸ��ݾ� / ȸ�񳳺���");
			System.out.println("==============================================================================");

			for (Member member : m) {
				System.out.println(member.print2());
			}

			System.out.println("==============================================================================");
			System.out.printf("�� %s��", m.size());
			System.out.println();
		} else {
			System.out.println("�˻������ �����ϴ�.");
		}

	}

}
