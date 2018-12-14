package com.test001;

import java.util.*;

//실행 클래스
public class Main {
	
	//글로벌변수
	private int a; //인스턴스 변수 : 객체 생성 시 메모리 적재
	private static int b; // 정적 변수 : 프로그램 시작 시 메모리 적재

	// main() 메소드는 (콘솔)프로그램 시작(진입)점 역할 메소드 
	public static void main(String[] args) {
		
		// new -> 인스턴스(객체) 생성 연산자
		// Sample() -> 기본 생성자
		// 객체 생성시 기본생성자 자동 호출
		// 변수 s -> 객체의 참조주소가 저장
		// 변수의 자료형 -> 기본자료형(정수형, 실수형, 문자형, 논리형)
		// 				  , 참조자료형(클래스, 배열, 인터페이스, 열거형)
		// 객체 자신은 메모리에서 heap 영역에 저장된다.
		// 변수는 메모리에서 stack 영역에 저장된다.
		// 변수 선언 위치에 따른 구분 -> 지역변수, 글로벌변수(인스턴스 변수, 정적 변수)
		Sample s = new Sample();
		
		System.out.println(s.toString());
	}

}
