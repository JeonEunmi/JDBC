package com.member;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// 회원관리 v2.0
				// 1.회원정보입력  2.회원정보전체출력  3.종료

				Scanner sc = new Scanner(System.in);
				MemberService s = new MemberService();

				while (true) {
					System.out.println();
					System.out.println("** 회원관리 v2.0 **");
					System.out.println("1.회원정보입력  2.회원정보전체출력  3.종료");
					System.out.print("선택>");
					int m = sc.nextInt();
					sc.nextLine();

					if (m == 0 || m == 3) {
						break;
					}
					switch (m) {
					case 1:
						s.menu1(sc);
						break;
					case 2:
						s.menu2();
						break;
					}
				}
				sc.close();
				System.out.println("프로그램 종료");
	}

}
