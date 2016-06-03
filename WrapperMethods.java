package main.java.wrapper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
//import org.openqa.selenium.Point;
//import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WrapperMethods implements GenericWrappers {
	RemoteWebDriver driver;
	WebElement element;

//1. invokeAPP Method	
	public boolean invokeApp(String browser) throws TimeoutException {
			
		boolean flag = false;
			
		try{
			if(browser.equalsIgnoreCase("firefox")){
				
				driver = new FirefoxDriver();
				System.out.println("Lauched browser is Firefox ");
				flag = true;
				
			}else if(browser.equalsIgnoreCase("chrome")){
				
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-popup-blocking");
				System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
				driver = new ChromeDriver(options);
				System.out.println("Lauched browser is Chrome");
				flag = true;
				
			}else if(browser.equalsIgnoreCase("IE")||browser.equalsIgnoreCase("internet explorer")||browser.equalsIgnoreCase("internetexplorer")){
				
				System.setProperty("webdriver.ie.driver","./drivers/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				System.out.println("Lauched browser is Internet Explorer");
				flag = true;
				
			}else{
				
				System.out.println("Driver Not Found for the browser provided");
				flag = false;
			}
			
			if(flag){
				driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
				driver.manage().window().maximize();
				flag = true;
			}
			
		} catch(WebDriverException e){
			
			System.out.println("WebDriver Exception in invokeApp() method - : "+e);
			
		} catch(Exception e){
			
			System.out.println("Exception Encountered in invokeApp(): "+e);
			
		} finally {
			
			captureScreen();
		}
		return flag;
		
	}
		
//2. getUrl Method
	public boolean getUrl(String url){
		
		boolean flag = false;
		try{
			
			driver.get(url);
			flag = true;
			
		} catch(Exception e){
			
			System.out.println("Exception in loading the URL : "+ e);
			
		} finally{
			
			captureScreen();
		}
		return flag;
	}
	

//3. enterById Method
	public boolean enterById(String idValue, String data) {
		
		boolean returnValue = false;
		try {
			element = driver.findElement(By.id(idValue));
			
			if(element.isDisplayed()){			
				element.clear();
				element.sendKeys(data);
				Thread.sleep(1000);
				element.sendKeys(Keys.TAB);
				returnValue = true;
				
			} else{
				System.out.println("Element with given id: "+idValue+" is not Visible");
			}
			
		} catch(NoSuchElementException e){
			
			System.out.println("Element not found Exception : id - "+idValue);
			
		} catch(Exception e){
			
			System.out.println("Exception Encountered");
			
		} finally{
			captureScreen();
		}
		return returnValue;
	}

//4. verifyTitle Method
	public boolean verifyTitle(String title) {
		boolean returnValue = false;
		try {
			String actualTitle = driver.getTitle();
			if(actualTitle.equalsIgnoreCase(title)){
				System.out.println("Title: "+actualTitle);
				returnValue = true;
			}
			else{
				System.out.println("Expected Title: "+title);
				System.out.println("Actual Title: "+actualTitle);
			}
			
		} catch (WebDriverException e) {
			System.out.println("Some Problem with WebDriver");
		} catch (Exception e) {
			System.out.println("Exception occured while verifyTitle() method");
		}
		finally
		{
			captureScreen();
		}
		return returnValue;
	}

//5. verifyTextByXpath Method
	public boolean verifyTextByXpath(String xpath, String text) {
		
		boolean returnValue = false;
		try {
			element = driver.findElement(By.xpath(xpath));
			String actualText = element.getText();
			if(verifyTextEquals(actualText, text)) {
				System.out.println("Text: "+text);
				returnValue = true;
			}else{
				System.out.println("Expected Text: "+text);
				System.out.println("Actual Text: "+actualText);
			}
		}
		catch(NoSuchElementException e)
		{
			System.out.println("Element not found");
			
		}
		catch(Exception e)
		{
			System.out.println("Exception Encountered");	
		}
		finally
		{
			captureScreen();
		}

		return returnValue;
	}

//6. verifyTextContainsByXpath
	public boolean verifyTextContainsByXpath(String xpath, String text) {
		
		boolean returnValue = false;
		try {
			element = driver.findElement(By.xpath(xpath));
			String actualText = element.getText();
			
			if(verifyTextEquals(actualText, text)) {
				System.out.println("Text: "+text);
				returnValue = true;
			} else {
				System.out.println("Expected Text: "+text);
				System.out.println("Actual Text: "+actualText);
			}
		} catch(NoSuchElementException e) {
			System.out.println("Element not found");
			
		} catch(Exception e) {
			System.out.println("Exception Encountered");	
		} finally {
			captureScreen();
		}
		return returnValue;
	}

//7. quitBrowser
	public void quitBrowser() {
		try{
			driver.quit();
			
		} catch(Exception e) {
			System.out.println("Some Error Occured while quitBrowser() method");
		}
		
		
	}


//8. mouseOverByXpath	
	public boolean mouseOverByXpath(String xpathVal) {
		boolean flag = false;
		try {
			Actions action = new Actions(driver);
			element = driver.findElement(By.xpath(xpathVal));
			action.moveToElement(element).build().perform();
			flag = true;
		} catch (Exception e) {
			System.out.println("Exception mouserOverByXpath "+e);
		} finally {
			captureScreen();
		}
		return flag;
	}

//9. mouseOverByLinkText
	public boolean mouseOverByLinkText(String linkName) {
		boolean flag = false;
		try {
			Actions action = new Actions(driver);
			element = driver.findElement(By.linkText(linkName));
			action.moveToElement(element).build().perform();
			flag = true;
		} catch (Exception e) {
			System.out.println("Exception mouserOverByLink "+e);
		} finally {
			captureScreen();
		}
		return flag;
	}

//10. clickById	
	public boolean clickById(String id) {
		boolean returnValue = false;
		try {
			element = driver.findElement(By.id(id));
			//int elementLoc = element.getLocation().x;
			int locX = element.getLocation().x;
			int locY = element.getLocation().y;
			
			if(element.isDisplayed() && element.isEnabled()){
				scrollPageToLocationXY(locY, locX);
				element.click();
				returnValue = true;
				
			} else if(element.isEnabled() && !element.isDisplayed()){
				element.sendKeys(Keys.RETURN);
				returnValue = true;
			}

		} catch (Exception e) {
			System.out.println("Some Error Occured while clickById() method"+e);
			
		} finally {
			captureScreen();
		}
		return returnValue;
	}

//11. clickByClassName
	public boolean clickByClassName(String classVal) {
		boolean returnValue = false;
		try {
			element = driver.findElement(By.className(classVal));
			//int elementLoc = element.getLocation().x;
			
			if(element.isDisplayed() && element.isEnabled()){
				//scrollPage(elementLoc);
				element.click();
				returnValue = true;
			}
			else{
				System.out.println("Element with Class Name: "+classVal+" is not visible or enabled");
			}
		} catch (Exception e) {
			System.out.println("Some Error Occured while clickByClassName() method");
		}
		finally
		{
			captureScreen();
		}
		return returnValue;
	}

//12. clickByName
	public boolean clickByName(String name) {
		boolean returnValue = false;
		try {
			element = driver.findElement(By.name(name));
			//int elementLoc = element.getLocation().x;
			
			if(element.isDisplayed() && element.isEnabled()){
				//scrollPage(elementLoc);
				element.click();
				returnValue = true;
			}
			else{
				System.out.println("Element with Name: "+name+" is not visible or enabled");
			}
		} catch (Exception e) {
			System.out.println("Some Error Occured while clickByName() method");
		}
		finally
		{
			captureScreen();
		}
		return returnValue;
	}

//13. clickByLink
	public boolean clickByLink(String name) {
		boolean returnValue = false;
		try {
			element = driver.findElement(By.linkText(name));
			int elementLoc = element.getLocation().x;
			
			if(element.isDisplayed() && element.isEnabled()){
				scrollPageToLocationX(elementLoc);
				element.click();
				returnValue = true;
			}
			else{
				System.out.println("Element with Link: "+name+" is not visible or enabled");
			}
		} catch (Exception e) {
			System.out.println("Some Error Occured while clickByLinkText() method");
		}
		finally
		{
			captureScreen();
		}
		return returnValue;
	}
	
//14. clickByXpath
	public boolean clickByXpath(String xpathVal) {
		boolean returnValue = false;
		try {
			element = driver.findElement(By.xpath(xpathVal));
			System.out.println(element.getText());
			int elementLoc = element.getLocation().x;
			
			if(element.isDisplayed() && element.isEnabled()) {
				scrollPageToLocationX(elementLoc);
				element.click();
				returnValue = true;
			}
			else{
				System.out.println("Element with xpath: "+xpathVal+" is not visible or enabled");
			}
		} catch (Exception e) {
			System.out.println("Some Error Occured while clickByXpath() method"+e);
			
		} finally {
			captureScreen();
		}
		return returnValue;
	}


//15. getTextByXpath
	public String getTextByXpath(String xpathVal) {
		String textByxpath = null;
		try {
			element = driver.findElement(By.xpath(xpathVal));
			textByxpath = element.getText();
			return textByxpath;
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		finally
		{
			captureScreen();
		}
		return textByxpath;
	}

//16. selectById
	public boolean selectById(String id, String value) {
		boolean returnValue = false;
		try {
			Select dropdown = new Select(driver.findElement(By.id(id)));
			dropdown.selectByValue(value);
			returnValue = true;
		} catch (Exception e) {
			System.out.println("Some Error Occured while selectById() method");
		}
		finally
		{
			captureScreen();
		}
		return returnValue;
	}
	
//17. captureScreen
	public void captureScreen(){
		try {
			File screenShot = driver.getScreenshotAs(OutputType.FILE);
			File target = new File(snapName());
			
			FileUtils.copyFile(screenShot,target);
		} catch (WebDriverException e) {
			System.out.println("WebDriver Exception during captureScreen() method");
		} catch (IOException e) {
			System.out.println("IO Exception during captureScreen() method");
		} catch (Exception e){
			System.out.println("Exception during captureScreen() method");
		}
	}
	
//18. snapName	
	public String snapName(){
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH-mm-ss");
		String todate = ft.format(date).toString().substring(0, 10);
		//System.out.println(todate);
		String time = ft.format(date).substring(11, 19);
		String screenShotName = "./Screenshot/"+todate+"/SCREEN_"+time+".jpeg";
		return screenShotName;
	}

//19. verifyRequiredTextIsInListByXpath
	public boolean verifyRequiredTextIsInListByXpath(String xpath,String value){
		boolean flag=false;
		try {
			List <WebElement> allTexts = driver.findElementsByXPath(xpath);
			for (WebElement text : allTexts) {
				if(verifyTextEquals(text.getText(), value)){
				flag=true;
				break;
				}
			}
		
		} catch (NoSuchElementException e) {
			System.out.println("Element not found");
			
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		return flag;
	}

//20. verifyTextEquals
	public boolean verifyTextEquals(String textActual,String textRequired){
		boolean flag=false;
		try
		{
			if(textActual.equalsIgnoreCase(textRequired) || textActual.equals(textRequired) || textActual.toLowerCase().contains(textRequired)){
				flag=true;			
			}
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		return flag;
	}
	
//21. getTextById
	public String getTextById(String id) {
		String textById = null;
		try {
			element = driver.findElement(By.id(id));
			textById = element.getText();
			return textById;
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			captureScreen();
		}
		return textById;
	}
	
//22. getBackgroundColorByXpath
	public String getBackgroundColorByXpath(String xpath) {
		String bgColor = null;
		
		try {
			bgColor = driver.findElement(By.xpath(xpath)).getCssValue("background-color");
		} catch (Exception e) {
			System.out.println("Exception in getBackgroundColorByXpath() method "+e);
		}
		return bgColor;
	}
	
//23. uncheckCheckbox
	public boolean uncheckCheckbox(String id) {
		boolean flag = true;
		try {
			element = driver.findElement(By.id(id));
			if(element.isSelected()){
				element.click();
				flag = false;
			}
		} catch(Exception e) {
			System.out.println("Exception in Checkbox method "+e);
		}
		return flag;
	}
//24. scrollPageToLocationX
	public void scrollPageToLocationX(int location){
		
	       try {
			JavascriptExecutor js =(JavascriptExecutor)driver;
			   js.executeScript("window.scrollTo(0,"+(location-100)+")");
			   
		} catch (Exception e) {
			System.out.println("scrollPage Exception "+ e);
		}
	}

//25. scrollPageToLocationY
	public void scrollPageToLocationY(int location){
		
	       try {
			JavascriptExecutor js =(JavascriptExecutor)driver;
			   js.executeScript("window.scrollTo("+(location)+",0)");
			   
		} catch (Exception e) {
			System.out.println("scrollPage Exception "+ e);
		}
	}
//26. scrollPageToLocationXY
	public void scrollPageToLocationXY(int locationY, int locationX){
		
	       try {
			JavascriptExecutor js =(JavascriptExecutor)driver;
			   js.executeScript("window.scrollTo("+(locationX)+","+(locationY)+")");
			   
		} catch (Exception e) {
			System.out.println("scrollPage Exception "+ e);
		}
	}
	
//27. ScrollPageUp	
	public void scrollPageUp() {
		JavascriptExecutor js =(JavascriptExecutor)driver;
		   js.executeScript("window.scrollTo(0,0)");
	}

//28. ScrollPageDown	
	public void scrollPageDown() {
		JavascriptExecutor js =(JavascriptExecutor)driver;
		   js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
//29. scrollIntoView
	public void scrollIntoViewPage(WebElement element){
		try {
			JavascriptExecutor js =(JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView(true);",element);
		} catch (Exception e) {
			System.out.println("ScrollIntoViewPage Exception "+e);
		}
	}
	
//30. waitForVisibilityOfElement
	public boolean waitForVisibilityOfElementByXpath(WebElement element) {
		boolean flag = false;
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement myDynamicElement = wait.until(ExpectedConditions.visibilityOf(element));
			myDynamicElement.click();
			flag=true;
		} catch (Exception e) {
			System.out.println("Exception in waitForVisibilityOfElement() method "+e);
		} finally {
			captureScreen();
		}
		return flag;
	}
	
//31. waitForElementToBeClickable
	public boolean waitForVisibilityOfElementByID(WebElement element) {
		boolean flag = false;
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement myDynamicElement = wait.until(ExpectedConditions.elementToBeClickable(element));
			myDynamicElement.click();
			flag=true;
		} catch (Exception e) {
			System.out.println("Exception in waitForElementToBeClickable() method "+e);
		} finally {
			captureScreen();
		}
		return flag;
	}
	
	public boolean clickByIdSendKeys(String idValue) {
		
		boolean returnValue = false;
		try {
			element = driver.findElement(By.id(idValue));
			
			if(element.isDisplayed()){			
				element.sendKeys(Keys.ENTER);
				returnValue = true;
				
			} else{
				System.out.println("Element with given id: "+idValue+" is not Visible");
			}
			
		} catch(NoSuchElementException e){
			
			System.out.println("Element not found Exception : id - "+idValue);
			
		} catch(Exception e){
			
			System.out.println("Exception Encountered "+e);
			
		} finally{
			captureScreen();
		}
		return returnValue;
	}
	
	public boolean clickByXpathSendKeys(String xpath) {
		
		boolean returnValue = false;
		try {
			element = driver.findElement(By.xpath(xpath));
			
			if(element.isDisplayed()){			
				element.sendKeys(Keys.ENTER);
				returnValue = true;
				
			} else{
				System.out.println("Element with given id: "+xpath+" is not Visible");
			}
			
		} catch(NoSuchElementException e){
			
			System.out.println("Element not found Exception : id - "+xpath);
			
		} catch(Exception e){
			
			System.out.println("Exception Encountered "+e);
			
		} finally{
			captureScreen();
		}
		return returnValue;
	}
	
	public boolean clickByLinkSendKeys(String link) {
		
		boolean returnValue = false;
		try {
			element = driver.findElement(By.linkText(link));
			
			if(element.isDisplayed()){			
				element.sendKeys(Keys.ENTER);
				returnValue = true;
				
			} else{
				System.out.println("Element with given id: "+link+" is not Visible");
			}
			
		} catch(NoSuchElementException e){
			
			System.out.println("Element not found Exception : id - "+link);
			
		} catch(Exception e){
			
			System.out.println("Exception Encountered "+e);
			
		} finally{
			captureScreen();
		}
		return returnValue;
	}
}
