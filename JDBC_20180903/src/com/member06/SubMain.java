package com.member06;

import java.util.*;

public class SubMain {

	MemberDAO dao = new MemberDAO();
	
	//1.회원정보입력
	public void memberAdd(Scanner sc) {
		System.out.print("이름> ");
		String name = sc.nextLine();
		
		System.out.print("전화번호> ");
		String phone = sc.nextLine();
		
		System.out.print("이메일> ");
		String email = sc.nextLine();
		
		
		Member m = new Member(null, name, phone, email, null);
		
		this.dao.memberAdd(m);
		
		
		System.out.println("회원 등록이 완료되었습니다.");
		
		System.out.println();
		
		
	}
	
	//2. 회원정보전체출력
	public void memberPrintAll() {
		
		System.out.println("회원아이디 / 이름 / 전화번호 / 이메일 / 등록일");
		System.out.println("---------------------------------------------------");
		
		List<Member> m = this.dao.memberPrintAll();
		
		for(Member member : m) {
			System.out.println(member);
		}
		
		System.out.println("---------------------------------------------------");
		
		System.out.printf("총 %s건%n", m.size());
		
		System.out.println();
	}
	
	
	
}
