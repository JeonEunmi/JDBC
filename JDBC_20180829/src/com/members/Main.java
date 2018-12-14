package com.members;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		MemberService sub = new MemberService();
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			
			System.out.println();
			System.out.println("** 수강신청 v1.0 **");
			
			System.out.println("1. 회원등록 2. 회원명단 3. 수강신청 4. 수강신청현황 5.회원의 수강신청 현황 0.종료");
			
			System.out.print("선택 > ");
			int selectNum = 0;
			
			selectNum = sc.nextInt();
			sc.nextLine();
			
			if(selectNum == 0) {
				break;
			}
			
			switch(selectNum) {
			
			case 1: sub.menu1(sc);
				break;
			
			case 2: sub.menu2();
				break;
			
			case 3: sub.menu3(sc);
				break;
			
			case 4: sub.menu4();
				break;
			
			case 5: sub.menu5(sc);
				
			default : System.out.println("잘못 입력하셨습니다. 다시 입력하세요");
				break;
			
			}
			
		}
		

		sc.close();
		System.out.println("프로그램 종료");
	}

}
