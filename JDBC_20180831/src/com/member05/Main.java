package com.member05;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		SubMain sub = new SubMain();
		
		while(true) {
			System.out.println("** ȸ������ v2.0**");
			System.out.println("1.ȸ�������Է�  2.ȸ��������ü���  3.����");
			System.out.print("����> ");
			
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
			default : System.out.println("�߸��� �����Դϴ�.");
					break;
			
			}
			
		}
		
		sc.close();
		System.out.println("���α׷� ����.");
	}

}
