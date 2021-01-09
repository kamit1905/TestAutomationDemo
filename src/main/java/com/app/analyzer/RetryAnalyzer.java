package com.app.analyzer;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer{
	int counter=0;
	int maxcnt=3;
	
	public boolean retry(ITestResult result) {
		if(counter<maxcnt) {
			counter++;
			return true;
		}
		return false;
	}

}
