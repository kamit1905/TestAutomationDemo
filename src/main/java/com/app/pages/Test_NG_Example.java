package com.app.pages;

import org.testng.annotations.Test;

public class Test_NG_Example {
	
	@Test(priority=0)
	public void print() {
		System.out.println("first priority");
	}
	
	@Test(priority=2)
	public void show() {
		System.out.println("second priority");
	}
	
	@Test(priority=2)
	public void amper() {
		System.out.println("third priority");
	}
	
	@Test
	public void harjai() {
		System.out.println("harjai");
	}
	
	@Test
	public void aharjaicomputer() {
		System.out.println("harjai computer");
	}

}
