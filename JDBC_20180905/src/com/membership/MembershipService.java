package com.membership;

import java.util.List;
import java.util.Scanner;

public class MembershipService {

	MembershipDAO dao = new MembershipDAO();

	// 회원 입력
	public void menu1(Scanner sc) {

		System.out.print("이름> ");
		String name = sc.nextLine();

		System.out.print("전화번호> ");
		String phone = sc.nextLine();

		System.out.print("이메일> ");
		String email = sc.nextLine();

		int tmp = this.dao.membershipAdd(new Member(name, phone, email));

		System.out.printf(" %s 명의 회원이 등록되었습니다.%n", tmp);

		System.out.println();

	}

	// 전체회원출력
	public void menu2() {

		System.out.println();
		System.out.println("회원아이디 / 이름 / 전화번호 / 이메일 / 등록일 / 회비납부총액 / 회비납부건수");
		System.out.println("==============================================================================");
		List<Member> m = this.dao.membershipPrint();

		for (Member member : m) {
			System.out.println(member.print1());
		}

		System.out.println("==============================================================================");

		System.out.printf("총 %s건", m.size());

		System.out.println();
	}

	public void menu3(Scanner sc) {

		System.out.println();
		System.out.println("** 회원관리 v2.4 ** > 3.회원정보검색");
		System.out.println("1.회원아이디  2.이름  3.전화번호  4.이메일  5.등록일  6.종료");

		System.out.print("선택> ");

		int key = sc.nextInt();
		sc.nextLine();

		System.out.print("검색정보입력> ");

		String value = sc.nextLine();
		List<Member> m = this.dao.membershipSearch(key, value);

		if(m.size() != 0) {
			
		System.out.println();
		System.out.println("회원아이디 / 이름 / 전화번호 / 이메일 / 등록일 / 회비납부총액 / 회비납부건수");
		System.out.println("==============================================================================");


		for (Member member : m) {
			System.out.println(member.print1());
		}

		System.out.println("==============================================================================");

		System.out.printf("총 %s건", m.size());

		} else {
			System.out.println("검색결과가 없습니다.");
		}
		System.out.println();

	}

	public void menu4(Scanner sc) {

		System.out.print("회원아이디> ");
		String mid = sc.nextLine();

		List<Member> m = this.dao.membershipSearch(1, mid);

		if (m.size() != 0) {

			System.out.println();
			System.out.println("회원아이디 / 이름 / 전화번호 / 이메일 / 등록일 / 회비납부총액 / 회비납부건수");
			System.out.println("==============================================================================");

			for (Member member : m) {
				System.out.println(member.print1());
			}

			System.out.println("==============================================================================");
			System.out.printf("총 %s건", m.size());

			System.out.println();
			List<Member> m2 = this.dao.memberFeePrint(mid);
			
			if(m2.size() != 0) {
				
			System.out.println("회원아이디 / 회비금액 / 회비납부일");
			System.out.println("====================================");


			for (Member member : m2) {
				System.out.println(member.print3());
			}
			System.out.println("====================================");
			System.out.printf("총 %s건", m2.size());
			System.out.println();
			} else {
				System.out.println();
				System.out.println("회비납부내역이 없습니다.");
				System.out.println();
			}

			System.out.print("회원의 회비 납부 내역이 같이 삭제됩니다. 현재 회원을 삭제할까요(0/1)?  ");
			int select = sc.nextInt();
			sc.nextLine();

			if (select == 1) {
				this.dao.membershipDelete(mid);
				System.out.println("회원정보 및 회비납부내역이 삭제되었습니다.");
			} else {
				System.out.println("취소 되었습니다.");
			}
			

		}else {
			System.out.println("검색결과가 없습니다.");
		}
	}

	public void menu5(Scanner sc) {

		while (true) {

			System.out.println();
			System.out.println("** 회원관리 v2.4 ** > 5.회비관리");
			System.out.println("1.회원별회비납부  2.회원별회비납부내역출력  3.전체회원회비납부내역출력 4.종료");

			System.out.print("선택> ");
			int selectNum = sc.nextInt();
			sc.nextLine();

			if (selectNum == 4) {
				break;
			}

			switch (selectNum) {

			case 1:
				this.menu5_sub1(sc);
				break;
			case 2:
				this.menu5_sub2(sc);
				break;
			case 3:
				this.menu5_sub3();
				break;
			default:
				System.out.println("잘못된 입력입니다.");
				break;
			}

		}
	}

	public void menu5_sub1(Scanner sc) {

		System.out.print("회원아이디> ");
		String mid = sc.nextLine();

		List<Member> m = this.dao.membershipSearch(1, mid);
		if (m.size() != 0) {

			System.out.println();
			System.out.println("회원아이디 / 이름 / 전화번호 / 이메일 / 등록일 / 회비납부총액 / 회비납부건수");
			System.out.println("==============================================================================");

			for (Member member1 : m) {
				System.out.println(member1.print1());
			}

			System.out.println("==============================================================================");
			System.out.printf("총 %s건", m.size());

			System.out.println();
			System.out.print("회비 금액> ");
			int pay = sc.nextInt();
			sc.nextLine();
			
			// 회비납부
			this.dao.membershipFee(new Member(mid, pay));
			
			System.out.printf(" %,d원의 회비가 납부되었습니다.", pay);
			System.out.println();

		} else {
			System.out.println("검색결과가 없습니다.");
		}
	}

	public void menu5_sub2(Scanner sc) {

		System.out.print("회원아이디> ");
		String mid = sc.nextLine();

		System.out.println();

		List<Member> m = this.dao.membershipSearch(1, mid);

		if (m.size() != 0) {
			System.out.println("회원아이디 / 이름 / 전화번호 / 이메일 / 등록일 / 회비납부총액 / 회비납부건수");
			System.out.println("==============================================================================");

			for (Member member : m) {
				System.out.println(member.print1());
			}

			System.out.println("==============================================================================");
			System.out.printf("총 %s건", m.size());

			List<Member> m2 = this.dao.memberFeePrint(mid);
			
			if(m2.size() != 0) {
				
			System.out.println();
			System.out.println("회원아이디 / 회비금액 / 회비납부일");
			System.out.println("====================================");


			for (Member member2 : m2) {
				System.out.println(member2.print3());
			}
			System.out.println("====================================");
			System.out.printf("총 %s건", m2.size());
			} else {
				System.out.println("*");
				System.out.println("회비 납부 내역이 없습니다.!");
			}
		} else {
			System.out.println("검색결과가 없습니다.");
		}
		System.out.println();
	}

	public void menu5_sub3() {

		System.out.println();
		List<Member> m = this.dao.memberFeePrintAll();

		if (m.size() != 0) {

			System.out.println("회원아이디 / 이름 / 전화번호 / 회비금액 / 회비납부일");
			System.out.println("==============================================================================");

			for (Member member : m) {
				System.out.println(member.print2());
			}

			System.out.println("==============================================================================");
			System.out.printf("총 %s건", m.size());
			System.out.println();
		} else {
			System.out.println("검색결과가 없습니다.");
		}

	}

}
