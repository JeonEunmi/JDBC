package com.test.scores;

public class Score {

	String sid, name;
	int subject1, subject2, subject3, sumSub, rank;
	double avgSub;
	
	public Score() {

	}

	public Score(String sid, String name, int subject1, int subject2, int subject3, int sumSub,
			double avgSub, int rank) {
		this.sid = sid;
		this.name = name;
		this.subject1 = subject1;
		this.subject2 = subject2;
		this.subject3 = subject3;
		this.sumSub = sumSub;
		this.avgSub = avgSub;
		this.rank = rank;
	}

	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSubject1() {
		return this.subject1;
	}

	public void setSubject1(int subject1) {
		this.subject1 = subject1;
	}

	public int getSubject2() {
		return this.subject2;
	}

	public void setSubject2(int subject2) {
		this.subject2 = subject2;
	}

	public int getSubject3() {
		return this.subject3;
	}

	public void setSubject3(int subject3) {
		this.subject3 = subject3;
	}

	public int getSumSub() {
		return this.sumSub;
	}

	public double getAvgSub() {
		return this.avgSub;
	}

	public int getRank() {
		return this.rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		return String.format("%s / %s / %d / %d / %d / %d / %.1f / %d"
				, this.getSid(), this.getName(), this.getSubject1(), this.getSubject2()
				, this.getSubject3(), this.getSumSub(), this.getAvgSub(), this.getRank());
	}
	
	
	
	
	
}
