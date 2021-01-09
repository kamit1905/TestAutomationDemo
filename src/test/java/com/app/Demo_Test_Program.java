package com.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Demo_Test_Program {
	
	public static void main(String[] args) {
		List<Integer> li = new ArrayList<Integer>();
		li.add(20);
		li.add(30);
		li.add(50);
		li.add(5);
		li.add(60);
		
		System.out.println(li);
		//li.forEach(n -> System.out.println(n));
		System.out.println("\n");
		Collections.sort(li);
		System.out.println("\n");
		/*for(Integer i :li){
			System.out.println(i);
		}*/
		//li.forEach(n -> System.out.println(n));
		
		int sum=1;
		for(int i = 1;i <5 ; i++){
			for(int j= 0; j < i ; j++){
				//System.out.print(sum++);
				//System.out.print(i);
				//System.out.print(j);
				
			}
			System.out.println("\n");
		}
	}

}
