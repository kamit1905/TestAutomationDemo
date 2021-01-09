package com.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class maincategory {
	
	public static String ReplaceWhiteSpace(String str,String replace) {
		Pattern prt=Pattern.compile("\\s+");
		Matcher mtch=prt.matcher(str);
				
		return mtch.replaceAll(replace);
	}
	
	public static void main(String[] args) {
		String str="kishtr.kljik";
		
		String [] str1=str.split("\\.");
		
		System.out.println("str[1] "+str1[0]);
		
		System.out.println("str[1] "+str1[1]);
		
		String [] str2=str.split("s",5);
		System.out.println("str2 "+str2[0]);
		
		System.out.println("str2 "+str2[1]);
		
		
		String str3="kal  jkkl   kkklk    kljiii";
		String rplString=maincategory.ReplaceWhiteSpace(str3, " ");
		System.out.println("rplString "+rplString);
	}
}
