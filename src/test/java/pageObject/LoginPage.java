package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {



	WebDriver ldriver;

	public LoginPage(WebDriver rDriver) {

		ldriver= rDriver;
		PageFactory.initElements(rDriver, this);

	}


	@FindBy(id="Email")
	WebElement email;

	@FindBy(id ="Password")
	WebElement password;

	@FindBy(xpath ="//button[text()='Log in']")
	WebElement login;

	@FindBy(linkText ="Logout")
	WebElement logoutBtn;

	//Actions

	public void enterEmail(String emailAdd) {
		email.clear();
		email.sendKeys(emailAdd);
	}

	public void enterPassword(String pwd) {
		password.clear();
		password.sendKeys(pwd);
	}

	public void clickOnLogin() {

		login.click();;
	}

	public void clickOnLogoutBtn() {

		logoutBtn.click();;
	}


}
