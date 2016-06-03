package main.java.testcases;

import org.junit.Test;

import main.java.wrapper.LinkedinWrappers;

public class TestCase5 extends LinkedinWrappers{
	@Test
	public void runTest5() throws InterruptedException{
		String browser = "chrome";
		String email = "rajkamal.veerasamy@gmail.com";
		String pass = "l1nk3d1n@";
		String name = "rajkamal";
		
		login(browser, email, pass, name);
		Thread.sleep(5000);
		clickById("advanced-search");
		enterById("advs-company","HCL Tech");
		selectById("advs-locationType","I");
		Thread.sleep(2000);
		selectById("advs-countryCode","in");
		Thread.sleep(2000);
		clickByXpath("//*[@id='peopleSearchForm']/div[1]/input[2]");
		Thread.sleep(10000);
		if(isTextboxEmpty("advs-company")){
			System.out.println("Text box cleared");
		}else{
			System.out.println("Text box not cleared");
		}
		if(clickById("adv-F-N-ffs")){
			Thread.sleep(10000);
			System.out.println("point 1");
			clickByXpath("//*[@id='peopleSearchForm']/div[1]/input[1]");
			System.out.println("Successful");
		}
		quitBrowser();	

	}
}
