package TestRunner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



@CucumberOptions(

		features= {".//Features/Customers.feature",".//Features/LoginFeature.feature"},
		glue="stepDefination" ,
		dryRun= false ,
		monochrome= true ,
		//plugin= {"pretty", "html:target/Cucumber-report/report1.html"},
		
		plugin= {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		
		tags="@Sanity"


		)


public class Run extends AbstractTestNGCucumberTests{

}
