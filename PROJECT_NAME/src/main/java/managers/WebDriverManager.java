package managers;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import enums.DriverType;
import functionLibrary.SeleniumGenericFunctionsWeb;

public class WebDriverManager {
	private WebDriver driver;
	private static DriverType driverType;
	private static String chromeDownloadPath;
	private static String ffDownloadPath;
	private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
	private static final String FF_DRIVER_PROPERTY = "webdriver.gecko.driver";
	private static final String IE_DRIVER_PROPERTY = "webdriver.ie.driver";
	SeleniumGenericFunctionsWeb genFunction = new SeleniumGenericFunctionsWeb();

	public WebDriverManager() {
		driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
		chromeDownloadPath = ffDownloadPath = FileReaderManager.getInstance().getConfigReader().getDownloadFilePath();
		
	}

	public WebDriver getDriver() {
		if(driver == null) driver = createDriver();
		return driver;
	}

	private WebDriver createDriver() {
        switch (driverType) {	    
        case FIREFOX : 
	        System.setProperty(FF_DRIVER_PROPERTY, FileReaderManager.getInstance().getConfigReader().getDriverPath_FF());
	        FirefoxProfile profile = new FirefoxProfile();
	        profile.setPreference("browser.download.folderList", 2);
	        profile.setPreference("browser.download.manager.showWhenStarting", false);
	        profile.setPreference("browser.download.dir", ffDownloadPath);
	        profile.setPreference("browser.download.useDownloadDir", true);
		    profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf,application/octet-stream,application/vnd.ms-excel,text/csv,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		    FirefoxOptions options = new FirefoxOptions();
	        options.setProfile(profile);
			driver = new FirefoxDriver(options);
			driver.manage().window().maximize();
	    	break;
        case CHROME : 
        	Map<String, Object> prefs = new HashMap<String, Object>();
        	prefs.put("download.default_directory", chromeDownloadPath);
			DesiredCapabilities capabilitiesChrome = DesiredCapabilities.chrome();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("prefs", prefs);
			chromeOptions.addArguments("disable-infobars");
			chromeOptions.addArguments("--disable-extensions");
			capabilitiesChrome.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        	System.setProperty(CHROME_DRIVER_PROPERTY, FileReaderManager.getInstance().getConfigReader().getDriverPath_GC());
        	driver = new ChromeDriver(chromeOptions);
        	driver.manage().window().maximize();
    		break;
        case INTERNETEXPLORER : 
        	DesiredCapabilities capabilities = new DesiredCapabilities();
    		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        	System.setProperty(IE_DRIVER_PROPERTY, FileReaderManager.getInstance().getConfigReader().getDriverPath_IE());
        	driver = new InternetExplorerDriver();
    		break;
    		
        case SAFARI : driver = new SafariDriver();
        }
		return driver;
	}	

	public void closeDriver() {
	    String browserName = genFunction.getBrowserName(driver);
	    
		if(browserName.equals("FF")) {			
			driver.quit();
		}else {
			driver.close();
			driver.quit();
		}
	
	}

}