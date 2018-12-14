package com.member05;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		SubMain sub = new SubMain();
		
		while(true) {
			System.out.println("** 회원관리 v2.0**");
			System.out.println("1.회원정보입력  2.회원정보전체출력  3.종료");
			System.out.print("선택> ");
			
			int selectNum = sc.nextInt();
			sc.nextLine();
			
			if(selectNum == 3) {
				break;
			}
			
			switch(selectNum) {
			
			case 1: sub.memberAdd(sc);
				break;
			case 2: sub.memberPrintAll();
				break;
			default : System.out.println("잘못된 접근입니다.");
					break;
			
			}
			
		}
		
		sc.close();
		System.out.println("프로그램 종료.");
	}

}
