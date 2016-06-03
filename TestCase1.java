package main.java.testcases;

import org.junit.Test;
import main.java.wrapper.LinkedinWrappers;



public class TestCase1  extends LinkedinWrappers {


	@Test
	public void runTest() throws InterruptedException{
		
		//Login
		login("chrome", "rajkamal.veerasamy@gmail.com", "l1nk3d1n@","rajkamal");
		Thread.sleep(5000);
		// Advanced search
		clickById("advanced-search");
		Thread.sleep(5000);
		
		enterById("advs-keywords","selenium");
		clickByName("submit");
		Thread.sleep(5000);
		
		//print the number of search results
		String num = getTextByXpath("//*[@id='results_count']/div/p");
		System.out.println(stringToIntConversion(num));
		
		//print the first connection name		
		String print = getTextByXpath("//*[@id='results']/li[1]/div/h3/a");			
		System.out.println("The First connection Name is :"+print);
		
		//print the connection		
		String connection = getTextByXpath("//*[@id='results']/li[1]/div/h3/span/span/abbr");		
		System.out.println("The First connection is :"+connection);
		
		closeApp();

	}
}