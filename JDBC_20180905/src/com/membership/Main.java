package com.membership;

import java.util.*;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		MembershipService sub = new MembershipService();

		while (true) {

			System.out.println("** ȸ������ v2.4**");
			System.out.println("1.ȸ�������Է�  2.ȸ��������ü���  3.ȸ�������˻�  4.ȸ����������  5.ȸ�����  6.����");
			System.out.print("����> ");

			int selectNum = sc.nextInt();
			sc.nextLine();

			if (selectNum == 6) {
				break;
			}

			switch (selectNum) {

			case 1:
				sub.menu1(sc);
				break;
			case 2:
				sub.menu2();
				break;
			case 3:
				sub.menu3(sc);
				break;
			case 4:
				sub.menu4(sc);
				break;
			case 5:
				sub.menu5(sc);
				break;
			default:
				System.out.println("�߸��� �����Դϴ�.");
				break;

			}

		}

		sc.close();
		System.out.println("���α׷� ����.");

	}
}
