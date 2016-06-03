package main.java.wrapper;

//import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;

public class LinkedinWrappers extends WrapperMethods {
	

	public void login(String browser,String email,String password,String name){
		String actualName;
		try{
			invokeApp(browser);	
			getUrl("https://in.linkedin.com/");
			enterById("login-email",email);
			enterById("login-password",password);
			Thread.sleep(1000);
			
			clickByXpath("//*[@id='pagekey-uno-reg-guest-home']/div[1]/div/form/input[6]");
		
			//clickByXpath("//*[@id='account-sub-nav']/div/div[2]/ul/li[5]/a/span/span[3]");
			actualName=getTextByXpath("//*[@id='identity']/section/div/div/h3/a");
			
			if(actualName.toLowerCase().contains(name)){
				
				System.out.println("Login Successful with the user name : "+name);
			} else{
				
				System.out.println("Incorrect Login");
			}
			
		} catch(Exception e){
			System.out.println("InterruptedException");
		}
	}
	
	public boolean isSkillExist(String xpath, String value){
		return verifyRequiredTextIsInListByXpath(xpath, value);
	}
	
	public int stringToIntConversion(String val){
		
		int number=0;
		try{
			if(val!=null){
				number=Integer.parseInt(val.replaceAll("[^0-9]", ""));
			}
			
		} catch(Exception e) {
			System.out.println("Exception Encountered during Number Conversion "+e);
		}
		return number;
	}
	
	public String replaceStringInNumberRange(String val){
		
		String numberRange=null;
	
		try{
			if(val!=null){
				numberRange =val.replaceAll("[^0-9-]", "");
			}
			
		} catch(Exception e) {
			System.out.println("Exception Encountered during Number Conversion "+e);
		}
		return numberRange;
	}
	
	
	public void closeApp(){
		try{
			driver.close();
			
		} catch(WebDriverException e){
			System.out.println("WebDriver Error Occured while Closing the close App() method");
			
		} catch(Exception e){
			System.out.println("Error Encountered while closing the driver");
		}
	}
	
	public boolean isTextboxEmpty(String id){
		boolean flag = false;
		if(getTextById(id).isEmpty()){
			flag = true;
		}
		return flag;
	}
	
	

}
