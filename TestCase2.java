package main.java.testcases;

import org.junit.Test;
import main.java.wrapper.LinkedinWrappers;

public class TestCase2 extends LinkedinWrappers{
	
	@Test
	public void runTest() throws InterruptedException{

		login("chrome", "rajkamal.veerasamy@gmail.com", "l1nk3d1n@","rajkamal");
		clickByLink("Advanced");
		clickByXpath("//*[@id='advs']/div[1]/button");
		Thread.sleep(2000);
	
		String a=getTextByXpath("//*[@id='results_count']/div/p/strong");
		System.out.println(a);
		Thread.sleep(2000);
		
		clickByXpath("//*[@id='pivot-bar']/ul/li[2]/button");
		Thread.sleep(2000);
		clickByXpath("//*[@id='pivot-bar']/ul/li[2]/button");
		Thread.sleep(2000);

		String b=getTextByXpath("//*[@id='results_count']/div/p/strong");
		String c=getTextByXpath("//*[@id='facet-N']/fieldset/div/ol/li[2]/div/span");
		System.out.println(b);
		System.out.println(c);
	
		int d = stringToIntConversion(b);
		int e = stringToIntConversion(c);
		
		if(d==e){
			System.out.println("Count matched "+d);
			
		}else{
			System.out.println("Count Not matched "+d+"	"+e);

		}

		quitBrowser();

	}
}

