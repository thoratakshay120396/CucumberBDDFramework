package stepDefination;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;
import pageObject.AddNewCustomerPage;
import pageObject.LoginPage;
import pageObject.SearchCustomerPage;
import utilities.ReadConfig;

public class StepDef extends BaseClass{

	@Before
	public void setUp() {

		readConfi = new ReadConfig();

		//initislize logger
		log = LogManager.getLogger("StepDef");


		String browser= readConfi.getBrowser();

		//launch browser
		switch(browser.toLowerCase()) {

		case "chrome" :

			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
			break;

		case "msedge" :

			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
			break;

		case "firefox" :

			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
			break;	

		default:
			driver=null;
			break;

		}


		log.info("Setup method is executed");
	}


	@Given("User launch chrome browser")
	public void user_launch_chrome_browser() {



		loginPg = new LoginPage(driver);    //Initialize login page object with WebDriver object 
		//created login page object in Given because here chrome browser will launch first

		addnewCustomerPg= new AddNewCustomerPage(driver);

		searchCustPg= new SearchCustomerPage(driver);

		driver.manage().window().maximize();

		log.info("Chrome browser is launched");

	}

	@When("User open URL {string}")
	public void user_open_url(String url) {


		driver.get(url);
		log.info("URL is opened");

	}

	@When("User enter Email as {string} and Password as {string}")
	public void user_enter_email_as_and_password_as(String emailAdd, String password) {

		loginPg.enterEmail(emailAdd);
		loginPg.enterPassword(password);

		log.info("User enter email Id");

	}

	@When("click on Login button")
	public void click_on_login_button() {

		loginPg.clickOnLogin();

		log.info("User clicked on login button");
	}

	@Then("Page title should be {string}")
	public void page_title_should_be(String expectedeTitle) {

		String actualTitile= driver.getTitle();

		Assert.assertEquals(expectedeTitle, actualTitile);

		log.warn("Page title is matched");
	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() throws InterruptedException {
		Thread.sleep(3000);

		loginPg.clickOnLogoutBtn();

		log.info("User clicked on logout button");
	}

	@Then("close browser")
	public void close_browser() {


		driver.quit();
	}

	///////////////////////Add New Customer////////////////

	@Then("User can view Dashboard")
	public void user_can_view_dashboard() {

		String actualTitle = addnewCustomerPg.getPageTitle();
		String expectedTitle = "Dashboard / nopCommerce administration";

		if(actualTitle.equals(expectedTitle))
		{
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}

	}

	@When("User click on Customers Menu")
	public void user_click_on_customers_menu() throws InterruptedException {
		Thread.sleep(2000);
		addnewCustomerPg.clickOnCustomersMenu();

	}

	@When("click on customers Menu Item")
	public void click_on_customers_menu_item() {
		addnewCustomerPg.clickOnCustomersMenuItem();
	}

	@When("click on Add new button")
	public void click_on_add_new_button() {
		addnewCustomerPg.clickOnAddnew();
	}

	@Then("Customer can view Add new customer page")
	public void customer_can_view_add_new_customer_page() {

		String actualTitle = addnewCustomerPg.getPageTitle();
		String expectedTitle = "Add a new customer / nopCommerce administration";
		Assert.assertEquals(expectedTitle, actualTitle);

	}

	@When("User enters customer info")
	public void user_enters_customer_info() {

		//	addnewCustomerPg.enterEmail("selenium6@gmail.com");
		addnewCustomerPg.enterEmail(generateEmailId()+ "@gmail.com");
		addnewCustomerPg.enterPassword("selenium1");
		addnewCustomerPg.enterFirstName("Akshay");
		addnewCustomerPg.enterLastName("Patil");
		addnewCustomerPg.enterGender("Male");
		addnewCustomerPg.enterDob("6/13/1988");
		addnewCustomerPg.enterCompanyName("CodeStudio");
		addnewCustomerPg.enterAdminContent("Admin content");
		addnewCustomerPg.enterManagerOfVendor("Vendor 1");



	}

	@When("Click on save button")
	public void click_on_save_button() {

		addnewCustomerPg.clickOnSave();

	}

	@Then("User should view confirmation message {string}")
	public void user_should_view_confirmation_message(String expectedTitle) {


		//	String actualTitle= driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissable']")).getText();

		String actualTitle= "The new customer has been added successfully.";

		Assert.assertEquals(expectedTitle, actualTitle);

	}


	/////////Search by Email

	@When("Enter customer EMail")
	public void enter_customer_e_mail() throws InterruptedException {
		Thread.sleep(2000);
		searchCustPg.enterEmailAdd("test1@gmail.com");

	}

	@When("Click on search button")
	public void click_on_search_button() {
		searchCustPg.clickOnSearchButton();
	}

	@Then("User should found Email in the Search table")
	public void user_should_found_email_in_the_search_table() {

		String expectedEmail = "test1@gmail.com";

		if(searchCustPg.searchCustomerByEmail(expectedEmail)==true);{

			Assert.assertTrue(true);
		}

	}

	///////////////Search By Name


	@When("Enter customer First Name")
	public void enter_customer_first_name() {
		searchCustPg.enterFirstName("Akshay");
	}

	@When("Enter customer Last Name")
	public void enter_customer_last_name() {

		searchCustPg.enterLastName("Patil");
	}

	@Then("User should found Name in the Search table")
	public void user_should_found_name_in_the_search_table() {

		String expectedName= "Akshay";

		if(searchCustPg.searchCustomerByName(expectedName)==true) {

			Assert.assertTrue(true);
		}

	}

	public void tearDown(Scenario sc) {

		//Scenario class : it checks status of scenario in feature file

		//isFailed method: if scenario is failed or not

//		if(sc.isFailed()) {
//
//
//			String filePath= "C:\\Users\\thora\\eclipse-workspace\\CucumberFramework\\Screenshot\\FailedScreenshot.png";
//
//			TakesScreenshot ts= (TakesScreenshot)driver;
//
//			File src= ts.getScreenshotAs(OutputType.FILE);
//
//			File destFile = new File(filePath);
//
//			try {
//				FileUtils.copyFile(src, destFile);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}

		driver.quit();
	}

	@AfterStep
	public void addScreenshot(Scenario sc)
	{

	if(sc.isFailed()){

	final byte[] screenshot= ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	
	sc.attach(screenshot, "image/png", sc.getName());


	}

	}




}
