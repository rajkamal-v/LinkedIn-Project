package main.java.testcases;

import org.junit.Test;

import main.java.wrapper.LinkedinWrappers;

public class TestCase3 extends LinkedinWrappers{
	@Test
	public void runTest3() throws InterruptedException{
		String browser = "firefox";
		String email = "rajkamal.veerasamy@gmail.com";
		String pass = "l1nk3d1n@";
		String skill = "Melenium";
		String name = "rajkamal";
		
		//Login with Email, Pass
		login(browser,email,pass,name);
		Thread.sleep(3000);
		
		//click the Profile link
		clickByLink("Profile");
		
		//Verify company name
		verifyTextContainsByXpath("//*[@id='headline']/div[1]/p", "hcl tech");
		
		//Verify the skill already exist in the Skill list
		if(!isSkillExist("//*[@class='endorse-item-name-text']", skill.toLowerCase())){
			
			//mouseOverByXpath("(//button[@title='Click to add a new education'])[2]");
			
			//scrollPageUp();
			
			// Click Add Skill
			clickByXpathSendKeys("(//button[@title='Add skill'])[2]");
			
			//enter the skill in textbox
			enterById("edit-skills-add-ta",skill);
			
			// click add button
			clickByIdSendKeys("edit-skills-add-btn");
			Thread.sleep(3000);
			
			//Click the save button
			clickByXpathSendKeys("//input[@value='Save']");
			Thread.sleep(3000);
			
			// Verify the skill has been added to the skill list
			if(isSkillExist("//*[@class='endorse-item-name-text']", skill.toLowerCase())){
				System.out.println("Successfully added skill "+skill);
				
			}else{
				System.out.println("Failed to add skill "+skill);
				
			}
		}else{
			System.out.println("Skill "+ skill+ " already exist");
			
		}
		//Quit Driver
		quitBrowser();
	}	

}
