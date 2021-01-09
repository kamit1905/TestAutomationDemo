package com.app;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNG_XML_Ex {
	
	@Test(priority=0)
	public void show() {
		System.out.println("show method");
	}
	
	@Test(priority=-2)
	public void print() {
		System.out.println("print method");
	}
	
	@Test(priority=0)
	public void details() {
		System.out.println("details method");
	}
	
	//@BeforeMethod
	@Test(priority=-20)
	public void add() {
		System.out.println("add before method");
	}
	
	//@AfterMethod
	public void abbb() {
		System.out.println("execution completed");
	}

}
