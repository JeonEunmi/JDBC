package com.emp.main;

import java.util.Scanner;

import com.emp.service.EmpService;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		while (true) {
/*			
			--------------
			�������� v2.0
			--------------
			1.�α���
			����(0/1)>1
*/
			
			System.out.println();

			System.out.println("** �������� v2.0 **");
			System.out.println("1. �α���");
			System.out.print("����(0/1)> ");

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
				System.out.println("�߸��� �����Դϴ�.");
			}

		}

		sc.close();
		System.out.println("���α׷� ����.");

	}
}
