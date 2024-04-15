package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddNewCustomerPage {

	WebDriver ldriver;

	public AddNewCustomerPage(WebDriver rDriver) {

		ldriver= rDriver;
		PageFactory.initElements(rDriver, this);

	}
	
	@FindBy(xpath ="//a[@href='#']//p[contains(text(),'Customers')]")
	WebElement lnk_Customer_menu;
	
	@FindBy(xpath ="//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]")
	WebElement lnk_Customer_menuitem;
		
	
	@FindBy(xpath ="//a[@class='btn btn-primary']")
	WebElement AddNewBtn;
	
	@FindBy(xpath = "//input[@id='Email']")
	WebElement txtEmail;
	
	@FindBy(xpath = "//input[@id='Password']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//div[@class='k-multiselect-wrap k-floatwrap']")
	WebElement txtCustomerRoles;
	
	@FindBy(xpath = "//li[contains(text(),'Administrators')]")
	WebElement listItemAdministrators;


	@FindBy(xpath = "//li[contains(text(),'Registered')]")
	WebElement listItemRegistered;

	@FindBy(xpath = "//li[contains(text(),'Guests')]")
	WebElement listItemGuests;


	@FindBy(xpath = "//li[contains(text(),'Guests')]")
	WebElement listItemVendors;


	@FindBy(xpath = "//*[@id='VendorId']")
	WebElement dropdownVendorMgr;

	@FindBy(id = "Gender_Male")
	WebElement MaleGender;


	@FindBy(id = "Gender_Female")
	WebElement FeMaleGender;
	
	@FindBy(xpath = "//input[@id='FirstName']")
	WebElement txtFirstName;


	@FindBy(xpath = "//input[@id='LastName']")
	WebElement txtLastName;

	@FindBy(xpath = "//input[@id='DateOfBirth']")
	WebElement txtDob;

	@FindBy(xpath = "//input[@id='Company']")
	WebElement txtCompanyName;

	@FindBy(xpath = "//textarea[@id='AdminComment']")
	WebElement txtAdminContent;

	@FindBy(xpath = "//button[@name='save']")
	WebElement btnSave;

	//Actions Methods for web elements

	public String getPageTitle()
	{
		return ldriver.getTitle();
	}
	
	public void clickOnCustomersMenu() {
		lnk_Customer_menu.click();
	}

	public void clickOnCustomersMenuItem() {
		lnk_Customer_menuitem.click();
	}

	public void clickOnAddnew() {
		AddNewBtn.click();
	}

	public void enterEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void enterPassword(String password)
	{
		txtPassword.sendKeys(password);
	}
	
	
	
	public void enterManagerOfVendor(String value)
	{
		Select drp=new Select(dropdownVendorMgr);
		drp.selectByVisibleText(value);
	}

	public void enterGender(String gender)
	{
		if(gender.equals("Male"))
		{
			MaleGender.click();
		}
		else if(gender.equals("Female"))
		{
			FeMaleGender.click();
		}
		else//default set Male gender
		{
			MaleGender.click();
		}

	}
	
	public void enterFirstName(String firstName)
	{
		txtFirstName.sendKeys(firstName);
	}
	
	
	
	public void enterLastName(String lastName)
	{
		txtLastName.sendKeys(lastName);
	}

	public void enterDob(String dob)
	{
		txtDob.sendKeys(dob);
	}

	public void enterCompanyName(String coName)
	{
		txtCompanyName.sendKeys(coName);
	}

	public void enterAdminContent(String content)
	{
		txtAdminContent.sendKeys(content);
	}

	public void clickOnSave()
	{
		btnSave.click();
	}
	
	
	
	
	
	
	
}
