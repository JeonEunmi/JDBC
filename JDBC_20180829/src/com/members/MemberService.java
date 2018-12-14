package com.members;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Scanner;

// �׼� ���� Ŭ����
public class MemberService {

	// ����� � ��ü ���� �غ�
	private MemberDAO dao = new MemberDAO();
	
	// 1. ȸ�����
	public void menu1(Scanner sc) {
		// �̸�, ��ȭ��ȣ �Է� �ް� ����ҿ� �����ϴ� �׼�
		System.out.println();
		System.out.println("�ű�ȸ�� ��� �����Դϴ�.");
		
		System.out.print("�̸� > ");
		String name = sc.nextLine();
		
		System.out.print("��ȭ��ȣ > ");
		String phone = sc.nextLine();
		
		String regDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
		
		Member m = new Member(this.dao.autoMid(), name, phone, regDate);
		
		this.dao.memberAdd(m);
		
		System.out.println("�ű�ȸ���� ��ϵǾ����ϴ�.");
	}
	
	// 2. ȸ�����
	public void menu2() {
		// ����ҿ� ����� ȸ�� ���� ��ü ����ϴ� �׼�
		// ȸ�����̵� / �̸� / ��ȭ��ȣ / �����
		System.out.println();
		
		System.out.println("ȸ�����̵� / �̸� / ��ȭ��ȣ / �����");
		System.out.println("-------------------------------------------");
		List <Member> list = dao.memberList();
		
		for(Member m : list) {
			System.out.println(m);
		}
		
		System.out.println("-------------------------------------------");
		
		System.out.printf("�� %s��", this.dao.memberList().size());
		System.out.println();
	}
	
	// 3. ������û
	public void menu3(Scanner sc) {
		// ȸ�� �˻� �� ���� ��û�� �޴� �׼�.
		// ȸ�����̵�, ������, ��û�� ������ ����ҿ� ����.
		System.out.println();
		
		while(true) {
			
		System.out.println("�ű� ������û �����Դϴ�.");
		
		System.out.print("ȸ�� ���̵� > ");
		String mid = sc.nextLine();
		System.out.println();
		System.out.println("ȸ�����̵� / �̸� / ��ȭ��ȣ / �����");
		System.out.println("-------------------------------------");
		
		List<Member> member = this.dao.memberSearch(mid);
		
		for(Member m : member) {
			if(member.size() != 0) {
				System.out.println(m);
			} else {
				System.out.println("�˻������ �����ϴ�.");
				break;
			}
		}
		
		System.out.println("-------------------------------------");
		System.out.print("������ > ");
		
		String curName = sc.nextLine();
		
		String regDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
		
		History h = new History(this.dao.autoHid(), mid, curName, regDate);
		
		this.dao.historyAdd(h);
		
		System.out.println("����� �Ϸ�Ǿ����ϴ�!");
		
		}
	}
	
	// 4. ������û��Ȳ
	public void menu4() {
		// ����ҿ� ����� ���� ��û ���� ��ü ����ϴ� �׼�
		// ������û���̵� / ������ / ȸ�� ���̵� / �̸� / ��ȭ��ȣ / ��û��
		System.out.println();
		System.out.println("������û���̵� / ������ / ȸ�� ���̵� / �̸� / ��ȭ��ȣ / ��û��");
		System.out.println("------------------------------------------------------------------");
		
		List<HistoryMember> list = this.dao.historyList();
		
		for(HistoryMember m : list) {
			System.out.println(m);
		}
	}
	
	// --------------------
	// 5. ������û��Ȳ
	
	public void menu5(Scanner sc) {
		System.out.println();
		System.out.print("ȸ�����̵� > ");
		String id = sc.nextLine();
		
		System.out.println("ȸ�����̵� / �̸� / ��ȭ��ȣ / �����");
		System.out.println("-------------------------------------");

		List<Member> member = this.dao.memberSearch(id);

		for(Member m : member) {
			System.out.println(m);
		}
		System.out.println("-------------------------------------");
		
		System.out.println("������û���̵� / ������ / ��û��");
		
//		List<HistoryMember> list = this.dao.historySearchList(member);
//		
//		for(HistoryMember m : list) {
//			System.out.println(m);
//		}
//		
		
	}
	
}
