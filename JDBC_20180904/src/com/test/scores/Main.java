package com.test.scores;

import java.util.*;


public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		ScoreService sub = new ScoreService();

		while (true) {
			
			System.out.println("** �������� v2.2**");
			System.out.println("1.���������Է�  2.�����������  3.���������˻�  4.����");
			System.out.print("����> ");

			int selectNum = sc.nextInt();
			sc.nextLine();

			if (selectNum == 4) {
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
			default:
				System.out.println("�߸��� �����Դϴ�.");
				break;

			}

		}

		sc.close();
		System.out.println("���α׷� ����.");

	}
}
