package com.members;

import java.util.*;

//저장소 운영 클래스
public class MemberDAO {

	// 저장소 역할을 하게 될 List 컬렉션 객체 준비
	// 컬렉션 -> List, Set, Map
	// List -> 값만 저장, 저장 순서 유지, 중복 저장 가능, 인덱스 제공
	// Set -> 값만 저장, 중복 저장 불가, 저장 순서 유지 불가, 인덱스 제공 불가(Iterator 객체에 의한 접근 가능)
	// Map -> {키, 값}을 저장, 키가 인덱스 역할, 키는 중복 저장 불가
	// Map에서 값 접근 시, 키부터 접근한 후 키를 이용해서 값 접근. get(키), keySet()
	
	// List 인터페이스 -> 클래스의 추상적 표현. 클래스의 설계도 역할.
	// ArrayList 클래스의 객체를 List 인터페이스 자료형 변수에 저장
	// -> 다형성(하나의 자료형을 가진 변수에 여러가지 자료형의 값을 저장할 수 있는 상태)
	// Vector(Thread Safe), ArrayList -> List 인터페이스 구현 클래스
	// 
	private List<Member> members = new ArrayList<Member>();
	private List<History> historys = new ArrayList<History>();
	
	
	public MemberDAO() {

		// 샘플 자료 준비
		// M01 / 홍길동 / 010-1234-1234 / 2018-08-29
		this.members.add(new Member("M01", "홍길동", "010-3020-3938", "2018-08-28"));
		// H001 / M01 / Java & Python 기반 응용 SW 개발자 양성 과정 / 2018-08-29
		this.historys.add(new History("H001", "M01", "Java & Python 기반 응용 SW 개발자 양성 과정", "2018-08-29"));

	}
	
	// 회원 번호 자동 증가 메소드
	public String autoMid() {
		
		String tmp = String.format("M%02d", members.size() + 1);
	
		return tmp;
	}
	
	// 과정 번호 자동 증가 메소드
	public String autoHid() {
		
		String tmp = String.format("H%03d", historys.size() + 1);
		
		return tmp;
	}


	// 회원 등록 메소드
	public void memberAdd(Member m) {
		
		this.members.add(m);
		
	}
	
	// 회원 출력 메소드
	public List<Member> memberList(){
		
		List<Member> list = new ArrayList<Member>();
		
		for(Member m : this.members) {
			list.add(m);
		}
		
		
		return list;
		
	}
	
	// 회원 검색 메소드
	public List<Member> memberSearch(String mid){
		
		List<Member> member = new ArrayList<Member>();
		
		for(Member m : this.members) {
			if(m.getMid().equals(mid)) {
				member.add(m);
			}
		}
		
		
		return member;
		
	}
	
	// 과정 등록 메소드
	public void historyAdd(History h) {
		
		this.historys.add(h);
		
	}
	
	// 과정 출력 메소드
	// 출력 전용 자료형 클래스 준비
	public List<HistoryMember> historyList(){
		
		List<HistoryMember> list = new ArrayList<HistoryMember>();

		for(History h : this.historys) {
			for(Member m : this.members) {
				if(m.getMid().equals(h.getmid())) {
					list.add(new HistoryMember(h.gethid(), h.getcurName(), h.getmid(), m.getName(), m.getPhone(),h.getcurRegDate()));
				}
			}
		}
		
		
		return list;
		
	}
	
	// --------------------------------------
	
	public List<HistoryMember> historySearchList(Member member){
		
		List<HistoryMember> list = new ArrayList<HistoryMember>();

		for(History h : this.historys) {
			for(Member m : this.members) {
				if(m.getMid().equals(h.getmid())) {
					list.add(new HistoryMember(h.gethid(), h.getcurName(), h.getmid(), m.getName(), m.getPhone(),h.getcurRegDate()));
				}
			}
		}
		
		
		return list;
		
	}
	
	
}
