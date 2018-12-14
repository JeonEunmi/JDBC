package com.member;

import java.util.List;
import java.util.Scanner;

public class MemberService {


	private MemberDAO dao = new MemberDAO();
	
	//1.회원정보입력
	public void menu1(Scanner sc) {
		System.out.println("신규 회원 정보를 입력하는 과정입니다.");
		//회원아이디 -> 자동 증가
		//이름, 전화번호, 이메일 -> 외부 입력
		//등록일 -> 시스템의 오늘 날짜 
		System.out.print("이름>");
		String name = sc.nextLine();
		System.out.print("전화번호>");
		String phone = sc.nextLine();
		System.out.print("이메일>");
		String email = sc.nextLine();
		Member temp = new Member(null, name, phone, email, null);
		
		System.out.printf("입력하신 내용은 %n 이름 : %s , 전화번호 : %s , 이메일 : %s 입니다. %n",name,phone,email);
		System.out.print("신규 회원 정보를 등록할까요? (0/1) > ");
		int select = sc.nextInt();
		sc.nextLine();
		
		if(select == 1) {
			int result = this.dao.memberAdd(temp);
			System.out.printf("%s 행 이(가) 삽입되었습니다.%n", result);
		} else if(select == 0) {
			System.out.println("회원등록이 취소되었습니다.");
		} else {
			System.out.println("잘못 누르셨습니다. 다시 입력해주세요.");
		}
	}
	
	
	//2.회원정보전체출력
	public void menu2() {
		System.out.println();
		System.out.println("회원아이디 / 이름 / 전화번호 / 이메일 / 등록일");
		System.out.println("----------------------------------------------");
		List<Member> list = this.dao.memberList();
		for (Member m : list) {
			System.out.println(m.toString());
		}	
		System.out.println("----------");
		System.out.printf("총 %s건%n", list.size());		
	}
	
}
