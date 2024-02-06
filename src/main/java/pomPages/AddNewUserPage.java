package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddNewUserPage {
 
		// Declaration
		@FindBy(xpath = "//b[text()='Add New User']")
		private WebElement PageHeader;

		@FindBy(xpath = "//input[@id='email' and @reqired]")
		private WebElement emailTF;

		@FindBy(xpath = "//input[@id='password'and @required]")
		private WebElement passwordTF;

		@FindBy(xpath = "//input[@id='firstname' and @required]")
		private WebElement firstnameTF;

		@FindBy(xpath = "//input[@id='Lastname' and @required]")
		private WebElement lastnameTF;

		@FindBy(xpath = "//textarea[@id='address']")
		private WebElement addressTextArea;

		@FindBy(xpath = "//input[@id='contact']")
		private WebElement contactInfoTF;

		@FindBy(xpath = "//input[@id='photo'])[2]")
		private WebElement photouploadButton;

		@FindBy(xpath = "//button[text()='Save' and @name='add']")
		private WebElement saveButton;

		// Initialization
		public AddNewUserPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}

		// utilization
		public String getPageHeader() {
			return PageHeader.getText();
		}

		public void setEmail(String email) {
			emailTF.sendKeys(email);
		}

		public void setPassword(String password) {
			passwordTF.sendKeys(password);
		}

		public void setFirstname(String firstname) {
			lastnameTF.sendKeys(firstname);
		}

		public void setLastname(String lastname) {
			lastnameTF.sendKeys(lastname);
		}

		public void setAddress(String address) {
			addressTextArea.sendKeys(address);
		}

		public void setContactInfo(String contact) {
			contactInfoTF.sendKeys(contact);
		}

		public void uploadPhoto(String photoPath) {
			photouploadButton.sendKeys(photoPath);
		}

		public void clickSave() {
			saveButton.click();
		}

	}
	

