package com.members;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Scanner;

// 액션 진행 클래스
public class MemberService {

	// 저장소 운영 객체 연결 준비
	private MemberDAO dao = new MemberDAO();
	
	// 1. 회원등록
	public void menu1(Scanner sc) {
		// 이름, 전화번호 입력 받고 저장소에 저장하는 액션
		System.out.println();
		System.out.println("신규회원 등록 과정입니다.");
		
		System.out.print("이름 > ");
		String name = sc.nextLine();
		
		System.out.print("전화번호 > ");
		String phone = sc.nextLine();
		
		String regDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
		
		Member m = new Member(this.dao.autoMid(), name, phone, regDate);
		
		this.dao.memberAdd(m);
		
		System.out.println("신규회원이 등록되었습니다.");
	}
	
	// 2. 회원명단
	public void menu2() {
		// 저장소에 저장된 회원 정보 전체 출력하는 액션
		// 회원아이디 / 이름 / 전화번호 / 등록일
		System.out.println();
		
		System.out.println("회원아이디 / 이름 / 전화번호 / 등록일");
		System.out.println("-------------------------------------------");
		List <Member> list = dao.memberList();
		
		for(Member m : list) {
			System.out.println(m);
		}
		
		System.out.println("-------------------------------------------");
		
		System.out.printf("총 %s명", this.dao.memberList().size());
		System.out.println();
	}
	
	// 3. 수강신청
	public void menu3(Scanner sc) {
		// 회원 검색 후 수강 신청을 받는 액션.
		// 회원아이디, 과정명, 신청일 정보를 저장소에 저장.
		System.out.println();
		
		while(true) {
			
		System.out.println("신규 수강신청 과정입니다.");
		
		System.out.print("회원 아이디 > ");
		String mid = sc.nextLine();
		System.out.println();
		System.out.println("회원아이디 / 이름 / 전화번호 / 등록일");
		System.out.println("-------------------------------------");
		
		List<Member> member = this.dao.memberSearch(mid);
		
		for(Member m : member) {
			if(member.size() != 0) {
				System.out.println(m);
			} else {
				System.out.println("검색결과가 없습니다.");
				break;
			}
		}
		
		System.out.println("-------------------------------------");
		System.out.print("과정명 > ");
		
		String curName = sc.nextLine();
		
		String regDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
		
		History h = new History(this.dao.autoHid(), mid, curName, regDate);
		
		this.dao.historyAdd(h);
		
		System.out.println("등록이 완료되었습니다!");
		
		}
	}
	
	// 4. 수강신청현황
	public void menu4() {
		// 저장소에 저장된 수강 신청 정보 전체 출력하는 액션
		// 과정신청아이디 / 과정명 / 회원 아이디 / 이름 / 전화번호 / 신청일
		System.out.println();
		System.out.println("과정신청아이디 / 과정명 / 회원 아이디 / 이름 / 전화번호 / 신청일");
		System.out.println("------------------------------------------------------------------");
		
		List<HistoryMember> list = this.dao.historyList();
		
		for(HistoryMember m : list) {
			System.out.println(m);
		}
	}
	
	// --------------------
	// 5. 수강신청현황
	
	public void menu5(Scanner sc) {
		System.out.println();
		System.out.print("회원아이디 > ");
		String id = sc.nextLine();
		
		System.out.println("회원아이디 / 이름 / 전화번호 / 등록일");
		System.out.println("-------------------------------------");

		List<Member> member = this.dao.memberSearch(id);

		for(Member m : member) {
			System.out.println(m);
		}
		System.out.println("-------------------------------------");
		
		System.out.println("과정신청아이디 / 과정명 / 신청일");
		
//		List<HistoryMember> list = this.dao.historySearchList(member);
//		
//		for(HistoryMember m : list) {
//			System.out.println(m);
//		}
//		
		
	}
	
}
