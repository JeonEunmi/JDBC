package com.test.scores;

import java.util.*;


public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		ScoreService sub = new ScoreService();

		while (true) {
			
			System.out.println("** 성적관리 v2.2**");
			System.out.println("1.성적정보입력  2.성적정보출력  3.성적정보검색  4.종료");
			System.out.print("선택> ");

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
				System.out.println("잘못된 접근입니다.");
				break;

			}

		}

		sc.close();
		System.out.println("프로그램 종료.");

	}
}
