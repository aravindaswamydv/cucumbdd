package stepDefinitions;

import com.cucumber.listener.Reporter;

import cucumber.TestContext;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import functionLibrary.JavaGeneralUtilities;
import managers.FileReaderManager;

public class Hooks {

	TestContext testContext;
	String screenshotImgPath;
	JavaGeneralUtilities genUtils = new JavaGeneralUtilities();
	String screenshotFolderPath = FileReaderManager.getInstance().getConfigReader().getScreenshotFolderPath();
	public Hooks(TestContext context) {
		testContext = context;

		
	}
	@Before		
	public void beforeScenario(Scenario scenario) {
		   Reporter.assignAuthor("EVRY");
		
	}

	@After
	public void AfterSteps(Scenario scenario) {
		
		if(scenario.isFailed()){
			screenshotImgPath = genUtils.getScreenshot(testContext.getDriver(), screenshotFolderPath, genUtils.appendTimeStamp(""));
			genUtils.attachScreenshot(screenshotImgPath);
		}
		
		testContext.getWebDriverManager().closeDriver();

	}

}