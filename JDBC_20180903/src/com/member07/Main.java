package com.member07;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		SubMain sub = new SubMain();
		
		while(true) {
			System.out.println("** 회원관리 v2.2**");
			System.out.println("1.회원정보입력  2.회원정보전체출력  3.회원정보검색 4.회원정보삭제 5.종료");
			System.out.print("선택> ");
			
			int selectNum = sc.nextInt();
			sc.nextLine();
			
			if(selectNum == 5) {
				break;
			}
			
			switch(selectNum) {
			
			case 1: sub.menu1(sc);
				break;
			case 2: sub.menu2();
				break;
			case 3: sub.menu3(sc);
				break;
			case 4: sub.menu4(sc);
				break;
			default : System.out.println("잘못된 접근입니다.");
					break;
			
			}
			
		}
		
		sc.close();
		System.out.println("프로그램 종료.");
	}

}
