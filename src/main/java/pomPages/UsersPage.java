package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UsersPage {

	//Declaration
	@FindBy(xpath="//h1[normalize-space(text())='Users']")
	private WebElement pageHeader;

    @FindBy(xpath="//a[text()=' New']")
    private WebElement newButton;
    
    @FindBy(xpath="//h4[text()=' Success!']")
	private WebElement successMessage;
	
    
    //initialization
    public UsersPage(WebDriver driver) {
    	PageFactory.initElements(driver,this);
    }
    //utilization
    public String getPageHeader() {
    	return pageHeader.getText();
    }
    public void clickNewButton() {
    	newButton.click();
    }
    public String getSuccessMessage() {
		return successMessage.getText();
	}
}