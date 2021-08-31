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
	private static boolean dunit=false;
	public Hooks(TestContext context) {
		testContext = context;

	}
	
	@Before		
	public void beforeScenario(Scenario scenario) {
		   Reporter.assignAuthor("AuthorName");
		
	}

	@After
	public void AfterSteps(Scenario scenario) {
		
		if(scenario.isFailed()){
			screenshotImgPath = genUtils.getScreenshot(testContext.getDriver(), screenshotFolderPath, genUtils.appendTimeStamp(""));
			genUtils.attachScreenshot(screenshotImgPath);
		}
		
		testContext.getWebDriverManager().closeDriver();

	}
	
	@Before
	public void beforeAll() {
		if(!dunit) {
			Runtime.getRuntime().addShutdownHook(new Thread(){
				// Execute this method after all test are executed
				public void run() {
					System.out.println("All tests are executed");
					// create report
					
					genUtils.sendEmailHtmlReport("Test report", "Hello\n this is sample report attached", "C:\\Users\\Ei01864\\git\\cucumbdd\\PROJECT_NAME\\src\\main\\resources\\TestReport\\cucumber-reports\\report.html");
				}
				
			});
			
			//This part will be exuted before all test exution
			System.out.println("Before all test exution");
			dunit=true;
		}
	}
	

}