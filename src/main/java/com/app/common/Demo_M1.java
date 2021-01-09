package com.app.common;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

class baseEx extends Exception{
	
	public baseEx(String msg) {
		super(msg);
	}
	
}

class derived {
	
	public String calculate(int a,int b) throws baseEx{
		int res = a/b;
		if(res==0) {
			throw new baseEx("Divided zero exception");
		}else {
			return "Number is non zero";
		}
	}
}


public class Demo_M1 {
	//checked,unchechked,throw,throws,error,exception
	public static void main(String[] args) {
	
		int a=4;
		try {
			if(a<5) {
				throw new baseEx("Number is lessa than 5");
			}
		} catch (baseEx e) {
			System.out.println(e.getMessage());
		}
		
		
		 
		List<Integer> li = new ArrayList<Integer>();
		li.add(20);
		li.add(30);
		li.add(40);
		li.add(10);
		
		li.toArray(new Integer[li.size()]);
		
		System.out.println("liarray "+li);
		
		Enumeration e = Collections.enumeration(li);
		while (e.hasMoreElements()) {
			System.out.println("e "+e.nextElement());
			
		}
		
		
		
		ConcurrentHashMap m = new ConcurrentHashMap(); 
        m.put(1, "Welcome"); 
        m.put(2, "to"); 
        m.put(3, "Edureka's");
        m.put(4, "Demo");
   
        System.out.println(m);
         
        // Here we cant add Hello because 101 key 
        // is already present in ConcurrentHashMap object 
        m.putIfAbsent(3, "Online"); 
        System.out.println("Checking if key 3 is already present in the ConcurrentHashMap object: "+ m);
   
        // We can remove entry because 101 key 
        // is associated with For value 
        m.remove(1, "Welcome");
        System.out.println("Removing the value of key 1: "+m);
   
        // Now we can add Hello 
        m.putIfAbsent(1, "Hello");
        System.out.println("Adding new value to the key 1: "+m);
   
        // We cant replace Hello with For 
        m.replace(1, "Hello", "Welcome"); 
        System.out.println("Replacing value of key 1 with Welcome: "+ m); 
		
		//li.forEach(x -> System.out.println(x));
		
		//List<Integer> liele = li.stream().sorted().collect(Collectors.toList());
		List<Integer> liele = li.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());	//Descending sorting
		
		//spring annotations,java 8,
		//Collections.reverse(li);
		//Collections.sort(li);
		//Collections.reverse(li);
		
		Iterator<Integer> itr = liele.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
		
		Map<String, Integer> mp = new HashMap<String, Integer>();
		mp.put("one", 1);
		mp.put("two", 2);
		mp.put("zebra", 3);
		mp.put("apple", 5);
		mp.put("four", 4);
		
		List<Map.Entry<String, Integer>> sortlist=new ArrayList<Map.Entry<String,Integer>>(mp.entrySet());

		Collections.sort(sortlist, new Comparator<Map.Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				//return o2.getKey().compareTo(o1.getKey());
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		
		HashMap<String, Integer> hm = new LinkedHashMap<String, Integer>();
		for(Map.Entry<String, Integer> mp1 : sortlist) {
			System.out.println("key "+mp1.getKey()+" value "+mp1.getValue());
		}
		
		//System.out.println("hm "+hm);
		//error and exception  
		//errors are not recoverable 
		for(int i=5;i>0;i--) {
			if(i%2==0) {
				for(int v1=i*2;v1>=0;v1--) {
					if(v1==i) {
						System.out.print("\n");
					}else {
						System.out.print("*");
					}			
				}
				System.out.println();
			}else {
			for(a=i;a>0;a--) {
				System.out.print("*");
			}
			System.out.println();
		  }
		}
		for(int i1=5;i1>0;i1--) {
			//int cnt=i1;
			for(int j=i1;j>0;j--) {
				System.out.print(j);
			}
			System.out.println("");
		}
		
		//count of each character in string 
		//hashmap and hashtable
		//hashmap & treemap
		String str="categorygroup";
		char [] ch= str.toCharArray();
	
		Map<Character, Integer> mp3 = new HashMap<Character, Integer>();
		for(char c : ch) {
			if(mp3.containsKey(c)) {
				
			}else {
				mp3.put(c, 1);
			}
		}
		
		System.out.println("mp3 "+mp3);
		
		
		String str4="yahoohymm";String duplicate="";
		 int l=str4.length();
		 char [] chy=str4.toCharArray();
		 for(int i=0;i<l;i++) {
			 for(int j=i+1;j<l;j++) {
				 if(chy[i]==chy[j]) {
					 System.out.print(chy[j]+" ");
					 break;
				 }
			 }
		 }
		 
		 
		final byte ab=15;
		 final byte ab1=12;
		 byte c = (ab+ab1);
		 System.out.println("c "+c);
		 
		 double d=10.60;
		 System.out.println("ceil "+Math.ceil(d));
		 System.out.println("floor "+Math.floor(d));
		 System.out.println("roundoff "+Math.round(d));
		 
		 /*<context:annotations-config>
		 <context:annotation-componentscan base-package="">
		 <bean id="User" class="com.app.sun.User">
		 	<property name="Name" value="Harish" />
		 	<propert name="age" value="26" />
		 	<property name="empId" value="13456">
		 </bean>
		 
		 //https://www.concretepage.com/spring/spring-bean-autowire-byname-bytype-constructor-and-default-example
		 
		 <bean id="addr" class="com.app.sun.Address"  autowire="byType">
		 	<property name="city" value="Pune" />
		 	<propert name="state" value="Maharashtra" />
		 	<property name="country" value="India">
		 </bean>*/
		NavigableSet<Integer> nav = new TreeSet<Integer>();
		nav.add(20);
		nav.add(30);
		nav.add(10);
		System.out.println("nav "+nav);
		 
		
	}
}

//Beanfactory factory = new Xmlbeanfactory(new FileSystemResource("spring.xml"));
//Address add = factory.getBean("address");
//add.getPrint();
				
class Address{
	
	private String city;
	private String state;
	private String country;
	
	public Address(String city,String state,String country) {
		this.city=city;
		this.state=state;
		this.country=country;
	}
	
	public void getPrint() {
		System.out.println("In getPrint Method");
	}
}