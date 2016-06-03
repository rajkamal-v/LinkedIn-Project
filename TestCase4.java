package main.java.testcases;
 
import org.junit.Test;

import main.java.wrapper.LinkedinWrappers;

public class TestCase4 extends LinkedinWrappers {
	
	@Test
	public void tccount(){
		
		//Login to Linkedin
		login("chrome", "rajkamal.veerasamy@gmail.com", "l1nk3d1n@","rajkamal");
		
		//Click on Jobs link
		clickByLink("Jobs");
					
		//In the Search box search for Keyword 'Selenium'
		enterById("job-search-box", "Selenium");
			
		//Click on Search
		clickByName("jsearch");
			
		//Verify the color of the First View button is blue	
		verifyTextEquals(getBackgroundColorByXpath("//*[@id='results']/li[1]/div/div[3]/a"),"rgba(0, 140, 201, 1)");
		

		//Click View on the second Job
		clickByXpath("//*[@id='results']/li[2]/div/div[3]/a");
			
		//Print the company name of the Job Post using gettext
		String company = getTextByXpath("//span[@class='company']");
		System.out.println("Company Name is : "+company);
		
		
		//Click on the Company Name using clickByClassname
		clickByXpath("//span[@class='company']");
		
		//Print the number of employees count - need a method to print only count
		String employeesCount = replaceStringInNumberRange(getTextByXpath("//p[@class='company-size']"));
		System.out.println("Employees count range : "+employeesCount);
		
		//Close the application
		closeApp();
	}

	
	
	
	
}
