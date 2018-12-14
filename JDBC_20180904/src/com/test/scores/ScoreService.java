package com.test.scores;

import java.util.*;

// 메인 메뉴별 액션 클래스
public class ScoreService {

	ScoreDAO dao = new ScoreDAO();
	
	public void menu1(Scanner sc) {

		System.out.println("--------------");
		System.out.println("성적 정보 입력");
		System.out.println("--------------");

		System.out.print("이름: ");
		String name = sc.nextLine();

		System.out.print("과목1: ");
		int score1 = sc.nextInt();
		sc.nextLine();

		System.out.print("과목1: ");
		int score2 = sc.nextInt();
		sc.nextLine();

		System.out.print("과목1: ");
		int score3 = sc.nextInt();
		sc.nextLine();
		
		// 성적정보입력
		int rs = this.dao.scoreAdd(new Score(null, name, score1, score2, score3, 0, 0.0, 0));
		
		
		System.out.printf("총 %s건의 성적 정보 입력이 성공했습니다.%n", rs);

		System.out.println();
	}

	public void menu2() {

		System.out.println("--------------");
		System.out.println("성적 정보 출력");
		System.out.println("--------------");
		
		System.out.println();
		
		System.out.println("============================================");
		System.out.println("번호 / 이름 / 과목1 / 과목2 / 과목3 / 총점 / 평균 / 석차");
		
		// 출력
		
		List<Score> temp = this.dao.scorePrint();
		
		for(Score s : temp) {
			System.out.println(s);
		}
		
		System.out.println("============================================");
		
		System.out.printf("총 : %s건%n", temp.size());
		System.out.println();
	}

	public void menu3(Scanner sc) {

		System.out.println("**성적관리 v2.0 ** > 3.성적정보검색");
		System.out.println("--------------------------------------------");
		System.out.println("1.번호기준  2.이름기준  3.종료");
		System.out.print("선택> ");
		
		int selectNum = sc.nextInt();
		sc.nextLine();
		
		switch(selectNum) {
		case 1 : this.menu3_sub1(sc);
			break;
		case 2 : this.menu3_sub2(sc);
			break;
		}
		
	}
	
	public void menu3_sub1(Scanner sc) {
		
		System.out.print("검색> ");
		String sid = sc.nextLine();
		
		// 검색
		List<Score> score = this.dao.scoreSearch(1, sid);
		
		System.out.println("--------------");
		System.out.println("성적 정보 검색");
		System.out.println("--------------");
		System.out.println();
		
		if(score.size() != 0) {
		System.out.println("============================================");
		System.out.println("번호 / 이름 / 과목1 / 과목2 / 과목3 / 총점 / 평균 / 석차");
		
		for(Score s : score) {
			System.out.println(s);
		}
		
		System.out.println("============================================");
		
		System.out.printf("총 : %s건%n", score.size());
		} else {
			
			System.out.println();
			System.out.println("검색결과가 없습니다!");
			
		}
		System.out.println();
	}
	
	public void menu3_sub2(Scanner sc) {
		
		System.out.print("검색> ");
		String name_ = sc.nextLine();
		
		// 검색
		List<Score> score = this.dao.scoreSearch(2, name_);
		
		System.out.println("--------------");
		System.out.println("성적 정보 검색");
		System.out.println("--------------");
		System.out.println();
		
		if(score.size() != 0) {
			System.out.println("============================================");
			System.out.println("번호 / 이름 / 과목1 / 과목2 / 과목3 / 총점 / 평균 / 석차");
			
			for(Score s : score) {
				System.out.println(s);
			}
			
			System.out.println("============================================");
			
			System.out.printf("총 : %s건%n", score.size());
			
		} else {
			System.out.println();
			System.out.println("검색결과가 없습니다!");
		}
		System.out.println();
	}

}
