package com.scores;

import java.util.*;

// ���� �޴��� �׼� Ŭ����
public class ScoreService {

	ScoreDAO dao = new ScoreDAO();

	public void menu1(Scanner sc) {

		System.out.println("--------------");
		System.out.println("���� ���� �Է�");
		System.out.println("--------------");

		System.out.print("�̸�: ");
		String name = sc.nextLine();

		System.out.print("����1: ");
		int score1 = sc.nextInt();
		sc.nextLine();

		System.out.print("����1: ");
		int score2 = sc.nextInt();
		sc.nextLine();

		System.out.print("����1: ");
		int score3 = sc.nextInt();
		sc.nextLine();

		System.out.println("---------------------------------");
		System.out.printf("�̸� : %s %n����1 : %d%n����2 : %d%n����3 : %d%n", name, score1, score2, score3);
		System.out.print("���� ������ �Է��ұ��? (0/1) > ");
		int select = sc.nextInt();
		sc.nextLine();

		if (select == 1) {
			// ���������Է�
			int rs = this.dao.scoreAdd(new Score(null, name, score1, score2, score3, 0, 0.0, 0));
			System.out.printf("%s �� ��(��) ���ԵǾ����ϴ�.%n", rs);
		} else if (select == 0) {
			System.out.println("�������� �Է��� ��ҵǾ����ϴ�.");
		} else {
			System.out.println("�߸� �����̽��ϴ�. �ٽ� �Է����ּ���.");
		}
	}

	public void menu2() {

		System.out.println("--------------");
		System.out.println("���� ���� ���");
		System.out.println("--------------");

		System.out.println();

		System.out.println("============================================");
		System.out.println("��ȣ / �̸� / ����1 / ����2 / ����3 / ���� / ��� / ����");

		// ���

		List<Score> temp = this.dao.scorePrint();

		for (Score s : temp) {
			System.out.println(s);
		}

		System.out.println("============================================");

		System.out.printf("�� : %s��%n", temp.size());
		System.out.println();
	}

	public void menu3(Scanner sc) {

		System.out.println("**�������� v2.0 ** > 3.���������˻�");
		System.out.println("--------------------------------------------");
		System.out.println("1.��ȣ����  2.�̸�����  3.����");
		System.out.print("����> ");

		int selectNum = sc.nextInt();
		sc.nextLine();

		switch (selectNum) {
		case 1:
			this.menu3_sub1(sc);
			break;
		case 2:
			this.menu3_sub2(sc);
			break;
		}

	}

	public void menu3_sub1(Scanner sc) {

		System.out.print("�˻�> ");
		String sid = sc.nextLine();

		// �˻�
		List<Score> score = this.dao.scoreSearch(1, sid);

		System.out.println("--------------");
		System.out.println("���� ���� �˻�");
		System.out.println("--------------");
		System.out.println();

		if (score.size() != 0) {
			System.out.println("============================================");
			System.out.println("��ȣ / �̸� / ����1 / ����2 / ����3 / ���� / ��� / ����");

			for (Score s : score) {
				System.out.println(s);
			}

			System.out.println("============================================");

			System.out.printf("�� : %s��%n", score.size());
		} else {

			System.out.println();
			System.out.println("�˻������ �����ϴ�!");

		}
		System.out.println();
	}

	public void menu3_sub2(Scanner sc) {

		System.out.print("�˻�> ");
		String name_ = sc.nextLine();

		// �˻�
		List<Score> score = this.dao.scoreSearch(2, name_);

		System.out.println("--------------");
		System.out.println("���� ���� �˻�");
		System.out.println("--------------");
		System.out.println();

		if (score.size() != 0) {
			System.out.println("============================================");
			System.out.println("��ȣ / �̸� / ����1 / ����2 / ����3 / ���� / ��� / ����");

			for (Score s : score) {
				System.out.println(s);
			}

			System.out.println("============================================");

			System.out.printf("�� : %s��%n", score.size());

		} else {
			System.out.println();
			System.out.println("�˻������ �����ϴ�!");
		}
		System.out.println();
	}

}
