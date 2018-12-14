package com.test2;

public class Main {

	public static void main(String[] args) {

		//int n = (int) (Math.random() * 1000000 + 1);

		int n = 10;
		int count = 0;
		
		boolean flag = true;
		
		System.out.println("입력 : " + n);
		
		while (flag) {
			

			if (n % 3 == 0) {
				n /= 3;
				++count;
				System.out.println(n);
			} else if (n % 2 == 0) {
				n /= 2;
				++count;
				System.out.println(n);
			} else {
				n -= 1;
				++count;
				System.out.println(n);
			}
			
			
			if(n == 1) {
				flag = false;
			}
		}
		
		System.out.println("결과 : " + n);
		
		System.out.println("연산최소값 : " + count);
	}

}
