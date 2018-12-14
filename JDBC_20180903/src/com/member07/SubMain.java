package com.member07;

import java.util.*;

public class SubMain {

	MemberDAO dao = new MemberDAO();
	
	//1.회원정보입력
	public void menu1(Scanner sc) {
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
	public void menu2() {
		
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
	
	//3. 회원정보검색
	public void menu3(Scanner sc) {
		
		System.out.println();
		System.out.println("** 회원관리 v2.3 ** > 3.회원정보검색");
		System.out.println("1.회원아이디  2.이름  3.전화번호  4.이메일  5.등록일  6.종료");
		
		int selectNum = sc.nextInt();
		sc.nextLine();
		
		switch(selectNum) {
		case 1: this.menu3_sub1(sc);
			break;
		case 2: this.menu3_sub2(sc);
			break;
		case 3: this.menu3_sub3(sc);
			break;
		case 4: this.menu3_sub4(sc);
			break;
		case 5: this.menu3_sub5(sc);
			break;
		}
		
		
	}
	
	
	public void menu3_sub1(Scanner sc) {
		System.out.print("회원아이디?> ");
		
		String value = sc.nextLine();
		
		
		List<Member> members = null;

		members = this.dao.memberSearch(1, value);
		
		if(members.size() != 0) {
			
			System.out.println("회원아이디 / 이름 / 전화번호 / 이메일 / 등록일");
			System.out.println("---------------------------------------------------");
			
			for(Member m : members) {
				System.out.println(m);
			}
			
			System.out.println("---------------------------------------------------");
			System.out.printf("총 %s건%n", members.size());
			
		} else {
			System.out.println("검색정보가 없습니다!");
		}
		System.out.println();
	}
	
	public void menu3_sub2(Scanner sc) {
		System.out.print("이름?> ");
		
		String value = sc.nextLine();
		
		
		List<Member> members = null;

		members = this.dao.memberSearch(2, value);
		
		if(members.size() != 0) {
			
			System.out.println("회원아이디 / 이름 / 전화번호 / 이메일 / 등록일");
			System.out.println("---------------------------------------------------");
			
			for(Member m : members) {
				System.out.println(m);
			}
			
			System.out.println("---------------------------------------------------");
			System.out.printf("총 %s건%n", members.size());
			
		} else {
			System.out.println("검색정보가 없습니다!");
		}
		System.out.println();
		
	}
	public void menu3_sub3(Scanner sc) {
		System.out.print("전화번호?> ");
		
		String value = sc.nextLine();
		
		
		List<Member> members = null;

		members = this.dao.memberSearch(3, value);
		
		if(members.size() != 0) {
			
			System.out.println("회원아이디 / 이름 / 전화번호 / 이메일 / 등록일");
			System.out.println("---------------------------------------------------");
			
			for(Member m : members) {
				System.out.println(m);
			}
			
			System.out.println("---------------------------------------------------");
			System.out.printf("총 %s건%n", members.size());
			
		} else {
			System.out.println("검색정보가 없습니다!");
		}
		System.out.println();
		
	}
	public void menu3_sub4(Scanner sc) {
		System.out.print("이메일?> ");
		
		String value = sc.nextLine();
		
		
		List<Member> members = null;

		members = this.dao.memberSearch(4, value);
		
		if(members.size() != 0) {
			
			System.out.println("회원아이디 / 이름 / 전화번호 / 이메일 / 등록일");
			System.out.println("---------------------------------------------------");
			
			for(Member m : members) {
				System.out.println(m);
			}
			
			System.out.println("---------------------------------------------------");
			System.out.printf("총 %s건%n", members.size());
			
		} else {
			System.out.println("검색정보가 없습니다!");
		}
		System.out.println();
		
	}
	public void menu3_sub5(Scanner sc) {
		System.out.print("등록일?> ");
		
		String value = sc.nextLine();
		
		
		List<Member> members = null;

		members = this.dao.memberSearch(5, value);
		
		if(members.size() != 0) {
			
			System.out.println("회원아이디 / 이름 / 전화번호 / 이메일 / 등록일");
			System.out.println("---------------------------------------------------");
			
			for(Member m : members) {
				System.out.println(m);
			}
			
			System.out.println("---------------------------------------------------");
			System.out.printf("총 %s건%n", members.size());
			
		} else {
			System.out.println("검색정보가 없습니다!");
		}
		System.out.println();
		
	}
	
	
	// 4. 회원정보 삭제
	public void menu4(Scanner sc) {
	
		// 1.외부에서 회원번호를 입력 받는다.
		
		System.out.println("삭제할 데이터 검색 기준을 선택하세요.");
		System.out.println("1. 회원아이디 2.이름 3. 전화번호 4. 이메일 5.등록일");
		System.out.print("선택> ");
		int selectNum = sc.nextInt();
		sc.nextLine();
		String value = null;
		System.out.println();
		
		switch(selectNum) {
		case 1: System.out.print("회원아이디?> ");
			break;
		case 2: System.out.print("이름?> ");
			break;
		case 3: System.out.print("전화번호?> ");
			break;
		case 4: System.out.print("이메일?> ");
			break;
		case 5: System.out.println("등록일?> ");
			break;
		}
		
		value = sc.nextLine();
		
		// 2. 회원번호를 기준으로 회원정보 검색 및 출력
		System.out.println("회원아이디 / 이름 / 전화번호 / 이메일 / 등록일");
		System.out.println("---------------------------------------------------");

		List<Member> members = this.dao.memberSearch(selectNum, value);

		
		for(Member m : members) {
			System.out.println(m);
		}
		
		System.out.println("---------------------------------------------------");
		System.out.printf("총 %s건%n", members.size());
		
		// 3. 삭제 여부 확인
		if(members.size() != 0) {
			
			System.out.println("현재 회원을 삭제할까요? (0: 취소 /1: 삭제)> ");
			
			int select = sc.nextInt();
			sc.nextLine();
			
			int result = 0;
			
			// 4. 삭제 진행
			// 5. 삭제 메시지 출력
			if(select == 1) {
				result = this.dao.memberDelete(selectNum, value);
				
				System.out.println(result);
				System.out.printf("총 %s건 삭제가 완료되었습니다. %n", result);	
			} else if(select == 0) {
				System.out.println("취소 되었습니다.");
			}
			
			
		} else {
			System.out.println("검색 대상이 없습니다.");
			System.out.println();
		}
		System.out.println();
	}
	
	
}
