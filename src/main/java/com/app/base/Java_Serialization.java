package com.app.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class address implements Serializable{
	String city;
	String state;
	
	public address(String city,String state) {
		this.city=city;
		this.state=state;
	}

	@Override
	public String toString() {
		return "address [city=" + city + ", state=" + state + "]";
	}
	
	
}

class student implements Serializable{
	String name;
	address add;
	
	public student(String name,address add) {
		this.name=name;
		this.add=add;
	}

	@Override
	public String toString() {
		return "student [name=" + name + ", add=" + add + "]";
	}
	
	
}

public class Java_Serialization {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		address a1= new address("solapur","maharashtra");
		
		student s1= new student("amit",a1);
		
		FileOutputStream fout = new FileOutputStream("C:\\Users\\Amit\\Documents\\WiseDailyAutomation_06_01\\src\\main\\java\\com\\app\\base\\abc.txt");
		ObjectOutputStream out = new ObjectOutputStream(fout);
		out.writeObject(s1);
		out.flush();
		out.close();
		fout.close();
		
		
		try(FileInputStream fis = new FileInputStream("C:\\Users\\Amit\\Documents\\WiseDailyAutomation_06_01\\src\\main\\java\\com\\app\\base\\abc.txt")){
			ObjectInputStream in = new ObjectInputStream(fis);
			student s2=(student) in.readObject();
			System.out.println(s2.name);
			//System.out.println(s2.);
			System.out.println(s2.add.toString());
			
		}
	}

}
