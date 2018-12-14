package com.emp.main;

import java.util.Scanner;

import com.emp.service.EmpService;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		while (true) {
/*			
			--------------
			직원관리 v2.0
			--------------
			1.로그인
			선택(0/1)>1
*/
			
			System.out.println();

			System.out.println("** 직원관리 v2.0 **");
			System.out.println("1. 로그인");
			System.out.print("선택(0/1)> ");

			int selectNum = sc.nextInt();
			sc.nextLine();

			if (selectNum == 0) {
				break;
			}
			
			switch(selectNum) {
			case 1:
				new EmpService().login(sc);
				break;
			default: 
				System.out.println("잘못된 접근입니다.");
			}

		}

		sc.close();
		System.out.println("프로그램 종료.");

	}
}
