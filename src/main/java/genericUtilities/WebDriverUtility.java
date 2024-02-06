package genericUtilities;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * This class contains all reusable methods to perform driver related options
 * @author vanik
 */
public class WebDriverUtility {
    WebDriver driver;
    /**
     * This method is used to launch specific browser and maximize it
     * @param browser
     * @return
     */
	public WebDriver launchBrowser(String browser) {
		switch(browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver","./src/main/resources/chromedriver.exe");
			driver=new ChromeDriver();
			break;
		
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			break;
			default:
				System.out.println("invalid browser info");
				
		}
		driver.manage().window().maximize();
		return driver;
	}
	/**
	 * This method is is used to navigate to specified url
	 * @param url
	 */
	public void navigateToApp(String url) 
	{
		driver.get(url);	
	}
	/**
	 * This method is an implicit wait statement which waits until element is found
	 * @param time
	 */
	public void waitTillElementFound(long time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	/**
	 * This method waits until element is displayed on the web page  is enabled to recieve click 
	 * @param time
	 * @param element
	 * @return
	 */
	public WebElement explicitWait(long time,WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver,time);
		return wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	/**
	 * this method waits until element on the web page is enabled to recieve click
	 * 
	 * @param element
	 * @param time
	 * @return
	 */
	public WebElement explicitWait(WebElement element,long time) {
		WebDriverWait wait=new WebDriverWait(driver,time);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	/**
	 * This method is used to wait until title appears on the web page
	 * @param time
	 * @param title
	 * @return
	 */
	public Boolean explicitWait(long time,String title) {
		WebDriverWait wait=new WebDriverWait(driver,time);
		return wait.until(ExpectedConditions.titleContains(title));
	}
	/**
	 * This method is used to mousehover on an element
	 * @param element
	 */
	public void mouseHoverToElement(WebElement element) {
		Actions action =new Actions(driver);
		action.moveToElement(element).perform();
	}
	/**
	 * This method is used to double click on the element
	 * @param element
	 */
	public void doubleClickOnElement(WebElement element) {
		Actions action=new Actions(driver);
		action.doubleClick(element).perform();
	}
	/**
	 * This method is used to perform right click on the element
	 * @param element
	 */
	public void rightClick(WebElement element) {
		Actions action=new Actions(driver);
		action.contextClick(element).perform();
	}
	/**
	 * this method is used to drag and drop an element to target location
	 * @param element
	 * @param target
	 */
	public void dragAndDropAnElement(WebElement element,WebElement target)	{
		Actions action=new Actions(driver);
		action.dragAndDrop(element, target).perform();
	}
	/**
	 * this method is used to switch to frame based on specified index
	 * @param index
	 */
	public void switchToFrame(int index) {
		driver.switchTo().frame(index);
	}
	/**
	 * this method is used to switch to frame based on specified id or name attribute
	 * @param idOrName
	 */
	public void switchToFrame(String idOrName) {
		driver.switchTo().frame(idOrName);
	}
	/**
	 * this method is used to switch to frame based on frame element
	 * @param frameElement
	 */
    public void switchToFrame(WebElement frameElement) {
		driver.switchTo().frame(frameElement);
	}
    /**
     * This method is used to switch back to default web page
     */
    public void switchBackFromFrame() {
    	driver.switchTo().defaultContent();
    }
    /**
     * This method is used to select an element from drop down based on element index
     * @param element
     * @param index
     */
    public void selectFromDropdown(WebElement element,int index) {
    	Select select=new Select(element);
    	select.selectByIndex(index);
    }
    /**
     * this method is used to select an element from drop down based on value
     * @param element
     * @param value
     */
    public void selectFromDropdown(WebElement element,String value) {
    	Select select=new Select(element);
    	select.selectByValue(value);
    }
    /**
     * This method is used to select an element based on visible text
     * @param text
     * @param element
     */
    public void selectFromDropdown(String text,WebElement element) {
    	Select select=new Select(element);
    	select.selectByVisibleText(text);
    }
    /**
     * This method fetches all the elements from drop down
     * @param element
     * @return
     */
    public List<WebElement> getDropdownList(WebElement element)
    {
    	Select select=new Select(element);
    	return select.getOptions();
    }
    /**
     * this method is used to capture screenshot of the web page
     * @param driver
     * @param className
     * @param jutil
     */
    public void captureScreenshot(WebDriver driver, String className, JavaUtility jutil) {
    	TakesScreenshot ts=(TakesScreenshot)driver;
    	File src=ts.getScreenshotAs(OutputType.FILE);
    	File dest=new File("./Screenshot/"+className+"_"+jutil.getCurrentTime()+".png");
    try {
		FileUtils.copyFile(src,dest);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    /**
     * This method is used to scroll till the specified element on the web page 
     * @param element 
     */
    public void scrollTillElement(Object element) {
    	JavascriptExecutor js=(JavascriptExecutor)driver;
    	js.executeScript("arguments[0],scrollIntoView(true)",element);
    }
    
    /**
     * this method is used to handle alert popup 
     * @param status
     */
    public void handleAlert(String status) {
    	Alert al=driver.switchTo().alert();
    	if(status.equalsIgnoreCase("ok"))
    		al.accept();
    	else
    		al.dismiss();
    }
    /**
     * This method is used to switch to child browser
     */
    public void switchToChildBrowser() {
    	
    	Set<String> windowIDs=driver.getWindowHandles();
    	for(String window : windowIDs) {
    		driver.switchTo().window(window);
    	}
    }
    /**
     * This method returns parent window address
     * @return
     */
    
    public String getParentWindow() {
    	return driver.getWindowHandle();
    }
    /**
     * this method is used to switch to specified window
     * @param windowID
     */
    public void switchToWindow(String windowID) {
    	driver.switchTo().window(windowID);
    }
    /**
     * this method is used to close the current tab
     */
    public void closeBrowser() {
    	driver.close();
    }
    /*
     * this method is used to quit all the windows
     */
    public void quitAllWindows() {
    	driver.quit();
    }
    
      
    public WebElement convertPathToWebElement(String path,String replaceData) {
	  String requiredPath=String.format(path, replaceData);
	  WebElement element=driver.findElement(By.xpath(requiredPath));
	  return element;
	
}
}   
    
    
    
    
    
    
    

