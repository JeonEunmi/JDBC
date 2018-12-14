package com.test009;

import java.util.*;

public class test {

	public static void main(String[] args) {
		
		List<Integer> tri = Arrays.asList(60, 60, 60);
		
		Collections.sort(tri, new Comparator<Integer>(){
			
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		});
		
		
	}

}
