package com.app.pages;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;

import javax.swing.text.DateFormatter;


class superclass{
	
	public void print()throws RuntimeException {
			System.out.println("In super class method");
	}
}


class subclass extends superclass{
	
	public void print()throws NumberFormatException {
		System.out.println("In sub class method");
	}
}

public class demo {
	
	/*public static <K,V extends Comparable<V>> Map<K,V> sortByValues(Map<K,V> map){
		Comparator<K> newcomp =new Comparator<K>() {
			@Override
			public int compare(K k1,K k2){
				int compare=map.get(k1).compareTo(map.get(k2));
					if(compare==0)
						return 1;
					else 
						return compare;
			}
		};
		Map<K, V> sortedByValues= new TreeMap<>(newcomp);
		sortedByValues.putAll(map);
		return sortedByValues;
		
	}*/
	
	public static void main(String[] args) {
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date=LocalDate.parse("2020-12-15",format);
		System.out.println("date "+date);
		
		DayOfWeek week =date.getDayOfWeek();
		System.out.println("value "+week.getValue());
		
		/*try {
			subclass su = new subclass();
			su.print();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}*/
		
		
		/*TreeMap<Integer,String> tr= new TreeMap<>();
		tr.put(5, "Five");
		tr.put(1, "One");
		tr.put(10, "Amit");
		tr.put(11, "Eleven");
		tr.put(12, "Ball");
		tr.put(20, "Zebra");
		
		
		System.out.println("tr "+tr);*/
		
		
		TreeMap<String,Integer> tr= new TreeMap<>();
		tr.put("Eleven", 11);
		tr.put("Two", 2);
		tr.put("One", 1);
		tr.put("Zero", 0);
		tr.put("Thirty", 30);
		//tr.put(null, null);
		
		System.out.println("tr "+tr);
		
		String yaml="S,T";
		List<String> lit1=Arrays.asList(yaml.split(","));
		boolean flag=lit1.contains("W");
		if(!flag) {
			System.out.println("today not is holiday");
		}else {
			System.out.println("today is holiday");
		}
		
		
		/*Map sortedMap=sortByValues(tr);
		
		Set set=sortedMap.entrySet();
		
		Iterator it =set.iterator();
		
		while(it.hasNext()){
			Map.Entry pair = (Entry) it.next();
			
			System.out.println("Key "+pair.getKey()+" "+ "Value "+pair.getValue());
			//System.out.println("Value "+pair.getValue());
		}*/
		
	
		
		
		
		/*List<Integer> v =new ArrayList<Integer>();
		v.add(20);
		v.add(30);
		v.add(10);
		v.add(50);
		v.add(5);
		*/
		/*Iterator<Integer> it =v.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}*/
		 
		
		/*ListIterator<Integer> li = v.listIterator();
		while (li.hasNext()) {
			Integer integer = (Integer) li.next();
			//int integer =li.next();
			if(integer%2!=0){
				li.remove();
			}
			else{
			System.out.println(integer);

			}
		}*/
		/*System.out.println("\n");
		v.forEach(n-> System.out.println(n));
		
		SortedSet<Integer> sort = new TreeSet<>();
		sort.add(50);
		sort.add(5);
		sort.add(2);
		sort.add(10);
		sort.add(45);
		sort.add(35);
		sort.add(15);
		
		System.out.println("sort "+sort);
		*/
		
		String str="25%";
		String [] str1=str.split("%");
		float per=Integer.parseInt(str1[0])/100;
		System.out.println("per "+per);
		
		
		
		Integer arr[] = {10, 20, 30, 40, 50}; 
		   
        System.out.println("Original Array : " + Arrays.toString(arr)); 
          
        Collections.rotate(Arrays.asList(arr), -1); 
           
        System.out.println("Modified Array : " + 
                                Arrays.toString(arr)); 
        
        
        double per34=5;
        double cal;double amt=100;
        
        cal=per34*amt/100;
        
        System.out.println("cal "+cal);
        
        
        List<Integer> lis = new LinkedList<Integer>();
        lis.add(20);
        lis.add(50);
        lis.add(10);
        lis.add(28);
        
        System.out.println(lis.get(2));
        
        String ba="amit";
        String ab1="amit";
        ba.concat(ab1);
        System.out.println("ba "+ba);
        
        //String am="kajlmjkak";
        //System.out.println(am.indexOf('k'));
        //System.out.println(am.indexOf('k',3));
        
        Map<String, String> keyvalues= new LinkedHashMap<String, String>();
		keyvalues.put("mrp","80");
		keyvalues.put("mrp1","40");
		keyvalues.put("mrp2","20");
		
		Map<String, String> sellingvalues = new LinkedHashMap<String, String>();
		sellingvalues.put("selling", "72");
		sellingvalues.put("selling1", "36");
		sellingvalues.put("selling2", "18");
		
		Map<String, String> stockvalues = new LinkedHashMap<String, String>();
		stockvalues.put("stock", "0");
		stockvalues.put("stock1", "0");
		stockvalues.put("stock2", "0");
		
		System.out.println("map "+keyvalues);
		for(Entry<String, String> mrpvalues : keyvalues.entrySet()) {
			System.out.println("values "+mrpvalues.getValue());
		}
		
		for(Entry<String, String> sellingvalue : sellingvalues.entrySet()) {
			System.out.println("values "+sellingvalue.getValue());
		}
		
		for(Entry<String, String> stockvalue : stockvalues.entrySet()) {
			System.out.println("values "+stockvalue.getValue());
		}
		
		
		List<String> chcexksiz= new ArrayList<String>();
		List<String> listwithnonempty = new ArrayList<String>();
		chcexksiz.add("amit");
		chcexksiz.add("Naveen");
		chcexksiz.add("");
		chcexksiz.add("");
		
		System.out.println("chcexksiz "+chcexksiz);
		System.out.println("size "+chcexksiz.size());
        
		
		Iterator<String> lit=chcexksiz.iterator();
		
		while(lit.hasNext()) {
			String name=lit.next();
			if(name.equals("")) {
				
			}else {
				listwithnonempty.add(name);
			}
		}
		
		System.out.println("listwithnonempty "+listwithnonempty);
		
		String getdata=GetData();
		System.out.println("getdata "+getdata);
        
	}

	private static String GetData() {
		String da="";
		try {
			da="amit";
			da=da.concat("khambad");
			return da;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return da;
	}

}
