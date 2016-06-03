package main.java.testcases;

import main.java.wrapper.LinkedinWrappers;

import org.junit.After;
//import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCase6 extends LinkedinWrappers {

	@Before
	public void setUp() throws Exception {
		login("chrome", "rajkamal.veerasamy@gmail.com", "l1nk3d1n@","rajkamal");
		Thread.sleep(3000);
	}

	@After
	public void tearDown() throws Exception {
		quitBrowser();
	}

	@Test
	public void test() throws InterruptedException {
		
		clickByXpath("//*[@id='account-nav']/ul/li[1]/a");
		Thread.sleep(3000);
		clickById("compose-button");
		enterById("pillbox-input", "Saravanan R");
		enterById("compose-message", "When is your Joining Date");
		uncheckCheckbox("enter-to-send-checkbox");
		clickByClassName("message-submit");
		Thread.sleep(3000);
		if(verifyRequiredTextIsInListByXpath("//p[@class='summary']", "joining date")){
			System.out.println("Success");
		} else {
			System.out.println("Failed");
		}
	
	}

}
