package com.members;

import java.util.*;

//����� � Ŭ����
public class MemberDAO {

	// ����� ������ �ϰ� �� List �÷��� ��ü �غ�
	// �÷��� -> List, Set, Map
	// List -> ���� ����, ���� ���� ����, �ߺ� ���� ����, �ε��� ����
	// Set -> ���� ����, �ߺ� ���� �Ұ�, ���� ���� ���� �Ұ�, �ε��� ���� �Ұ�(Iterator ��ü�� ���� ���� ����)
	// Map -> {Ű, ��}�� ����, Ű�� �ε��� ����, Ű�� �ߺ� ���� �Ұ�
	// Map���� �� ���� ��, Ű���� ������ �� Ű�� �̿��ؼ� �� ����. get(Ű), keySet()
	
	// List �������̽� -> Ŭ������ �߻��� ǥ��. Ŭ������ ���赵 ����.
	// ArrayList Ŭ������ ��ü�� List �������̽� �ڷ��� ������ ����
	// -> ������(�ϳ��� �ڷ����� ���� ������ �������� �ڷ����� ���� ������ �� �ִ� ����)
	// Vector(Thread Safe), ArrayList -> List �������̽� ���� Ŭ����
	// 
	private List<Member> members = new ArrayList<Member>();
	private List<History> historys = new ArrayList<History>();
	
	
	public MemberDAO() {

		// ���� �ڷ� �غ�
		// M01 / ȫ�浿 / 010-1234-1234 / 2018-08-29
		this.members.add(new Member("M01", "ȫ�浿", "010-3020-3938", "2018-08-28"));
		// H001 / M01 / Java & Python ��� ���� SW ������ �缺 ���� / 2018-08-29
		this.historys.add(new History("H001", "M01", "Java & Python ��� ���� SW ������ �缺 ����", "2018-08-29"));

	}
	
	// ȸ�� ��ȣ �ڵ� ���� �޼ҵ�
	public String autoMid() {
		
		String tmp = String.format("M%02d", members.size() + 1);
	
		return tmp;
	}
	
	// ���� ��ȣ �ڵ� ���� �޼ҵ�
	public String autoHid() {
		
		String tmp = String.format("H%03d", historys.size() + 1);
		
		return tmp;
	}


	// ȸ�� ��� �޼ҵ�
	public void memberAdd(Member m) {
		
		this.members.add(m);
		
	}
	
	// ȸ�� ��� �޼ҵ�
	public List<Member> memberList(){
		
		List<Member> list = new ArrayList<Member>();
		
		for(Member m : this.members) {
			list.add(m);
		}
		
		
		return list;
		
	}
	
	// ȸ�� �˻� �޼ҵ�
	public List<Member> memberSearch(String mid){
		
		List<Member> member = new ArrayList<Member>();
		
		for(Member m : this.members) {
			if(m.getMid().equals(mid)) {
				member.add(m);
			}
		}
		
		
		return member;
		
	}
	
	// ���� ��� �޼ҵ�
	public void historyAdd(History h) {
		
		this.historys.add(h);
		
	}
	
	// ���� ��� �޼ҵ�
	// ��� ���� �ڷ��� Ŭ���� �غ�
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
