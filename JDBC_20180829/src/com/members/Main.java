package com.members;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		MemberService sub = new MemberService();
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			
			System.out.println();
			System.out.println("** ������û v1.0 **");
			
			System.out.println("1. ȸ����� 2. ȸ����� 3. ������û 4. ������û��Ȳ 5.ȸ���� ������û ��Ȳ 0.����");
			
			System.out.print("���� > ");
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
				
			default : System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է��ϼ���");
				break;
			
			}
			
		}
		

		sc.close();
		System.out.println("���α׷� ����");
	}

}
