package pageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import enums.DriverType;
import functionLibrary.SeleniumGenericFunctionsWeb;
import managers.FileReaderManager;

public class GoogleSearchPage extends SeleniumGenericFunctionsWeb {
	WebDriver driver;
	protected WebDriverWait driverWait;
	int pageWeight = 10;
	String appURL = "";

	public GoogleSearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		driverWait = new WebDriverWait(driver, pageWeight * 2, pageWeight);

	}

	@FindBy(how = How.XPATH, using = "//input[@name='q']")
	private WebElement searchInputField;

	@FindBy(how = How.XPATH, using = "//input[@name='btnK']")
	private WebElement gogleSearchBtn;

	/**
	 * Method name : navigateTo_LoginPage Purpose : Method to navigate to Login page
	 * 
	 * @param : None
	 * @return : None
	 * @author : EVRY
	 */
	public void navigateTo_GoogleSearchPage() {
		appURL = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();
		driver.get(appURL);
	}

	/**
	 * Method name : enter_SearchText Purpose : Method to enter text in search field
	 * 
	 * @param : None
	 * @return : None
	 * @author : EVRY
	 */
	public void enter_SearchText(String searchText) throws InterruptedException {

		enterTextField(driver, driverWait, searchInputField, searchText);

	}

	/**
	 * Method name : click_googleSearchBtn Purpose : Method to click on Google search button
	 * 
	 * @param : None
	 * @return : None
	 * @author : EVRY
	 */
	public void click_googleSearchBtn() {
		click(driver, driverWait, gogleSearchBtn);
	}

}