package functionLibrary;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.listener.Reporter;

//import managers.FileReaderManager;

public class SeleniumGenericFunctionsWeb {

	private static WebElement element;
	static JavaGeneralUtilities genUtils = new JavaGeneralUtilities();
	static String screenshotImgPath = "";
//	static String screenshotFolderPath = FileReaderManager.getInstance().getConfigReader().getScreenshotFolderPath();

	/**
	 * Method Name : click 
	 * Purpose     : Method for click by Locator
	 * @param      : driver,driverWait,locator
	 * @return     : None
	 * @Author     : EVRY
	 */
	public static void click(WebDriver driver, WebDriverWait driverWait, By locator) {
		try {
			element = getWebElement(driver, driverWait, locator);
			element.click();
		} catch (Exception e) {

			Assert.assertTrue("Exception occured : " + e.getMessage(), false);
		}
	}

	/**
	 * Method Name : click 
	 * Purpose     : Method for click by Element
	 * @param      : driver,driverWait,element
	 * @return     : None
	 * @Author     : EVRY
	 */
	public static void click(WebDriver driver, WebDriverWait driverWait, WebElement element) {
		try {
			if (isWebElementExists(driver, driverWait, element))
				element.click();
		} catch (Exception e) {

			//Assert.assertTrue("Exception occured : " + e.getMessage(), false);
		}
	}

	/**
	 * Method Name : getWebElement 
	 * Purpose     : Return the web element associated
	 * with given locator 
	 * @param      : driver,driverWait,locator
	 * @return     : WebElement
	 * @Author     : EVRY
	 */
	public static WebElement getWebElement(WebDriver driver, WebDriverWait driverWait, By locator) {
		try {

			if (isWebElementExists(driver, driverWait, locator)) {
				element = driver.findElement(locator);
			} else {
			}
		} catch (Exception e) {

			Assert.assertTrue("Exception occured : " + e.getMessage(), false);
		}
		return element;
	}

	/**
	 * Method Name : isWebElementExists 
	 * Purpose     : return true if web control
	 * present in the page  
	 * @param      : driver,locator
	 * @return     : boolean value true/false
	 * @Author     : EVRY
	 */
	public static boolean isWebElementExists(WebDriver driver, WebDriverWait driverWait, By locator) {
		try {
			driverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return driver.findElement(locator).isDisplayed();
		} catch (Exception e) {

			return false;
		}

	}

	/**
	 * Method Name : isWebElementExists 
	 * Purpose: return true if web element
	 * present in the page  
	 * @param      : driver,element
	 * @return     : boolean value true/false
	 * @Author     : EVRY
	 */
	public static boolean isWebElementExists(WebDriver driver, WebDriverWait driverWait, WebElement ele) {
		try {
			driverWait.until(ExpectedConditions.visibilityOf(ele));
			return ele.isDisplayed();
		} catch (Exception e) {
			Assert.assertTrue("Element Not displayed: " + e.getMessage(), false);
			return false;
		}

	}

	/**
	 * Method Name : getText 
	 * Purpose     : Method to get the Text value of the webelement
	 * @param      : driver,driverWait,by locator
	 * @return     : text of the element
	 * @Author     : EVRY
	 */
	public static String getText(WebDriver driver, WebDriverWait driverWait, By locator) {
		String strText = "";
		try {
			element = getWebElement(driver, driverWait, locator);
			strText = element.getText().toString();
		} catch (Exception e) {

			Assert.assertTrue("Exception occured : " + e.getMessage(), false);
		}
		return strText;
	}

	/**
	 * Method Name : getText 
	 * Purpose     : Method to get the Text value of the webelement
	 * @param      : gDriver,driverWait,webelement
	 * @return     : text of the element
	 * @Author     : EVRY
	 */
	public static String getText(WebDriver driver, WebDriverWait driverWait, WebElement element) {
		String strText = "";
		try {
				strText = element.getText().toString();
		} catch (Exception e) {
			strText = "";
		}
		return strText;
	}

	/**
	 * Method Name : enterTextField 
	 * Purpose     : This method keys the value in to the
	 * text field specified by the element locator 
	 * @param      : driver,driverWait,elementLocator,inputData
	 * @return     : None
	 * @author     : EVRY
	 */
	public void enterTextField(WebDriver driver, WebDriverWait webDriverWait, By elementLocator, String inputData) {
		try {
			element = getWebElement(driver, webDriverWait, elementLocator);
			element.clear();
			element.sendKeys(inputData);
		} catch (Exception e) {
			Assert.assertTrue("Exception occured : " + e.getMessage(), false);
		}

	}

	/**
	 * Method Name: enterTextField 
	 * Purpose    : This method keys the value in to the
	 * text field specified by the element
	 * @param     : driver,driverWait,webelement,inputData
	 * @return    : None
	 * @author    : EVRY
	 */
	public void enterTextField(WebDriver driver, WebDriverWait webDriverWait, WebElement element, String inputData) {
		try {
			if (isWebElementExists(driver, webDriverWait, element)) {
				element.clear();
				element.sendKeys(inputData);
			}
		} catch (Exception e) {

			Assert.assertTrue("Exception occured : " + e.getMessage(), false);
		}

	}

	/**
	 * Method Name : waitForTime 
	 * Purpose     : Method to wait For Time in milliseconds
	 * @param      : longtime
	 * @return     : None
	 * @Author     : EVRY
	 */
	public static void waitForTime(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			Assert.assertTrue("Exception in the method waitForTime due to   " + e.getMessage(), false);
		}
	}

	/**
	 * Method Name : pageScroll 
	 * Purpose     : Method to scroll page
	 * @param      : driver
	 * @return     : None
	 * @Author     : EVRY
	 */
	public void pageScroll(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.documentElement.scrollTop");
	}

	
	/**
	 * Method Name : pageScroll 
	 * Purpose     : Method to scroll to element
	 * @param      : driver,WebElement 
	 * @return     : None
	 * @Author     : EVRY
	 */
	public void pageScrollToElement(WebDriver driver, WebElement element) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	
	/**
	 * Method Name : pressEscKey 
	 * Purpose     : Method to press Escape key
	 * @param      : None
	 * @return     : None
	 * @Author     : EVRY
	 */
	public void pressEscKey() {
		try {
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_ESCAPE);
			r.keyRelease(KeyEvent.VK_ESCAPE);
		} catch (AWTException e) {
			e.printStackTrace();
		}

	}
	
	
	/**
	 * Method Name 	: sendKeyBoardKey
	 * Purpose		: Send specific keyboard key to a WebElement
	 * @param		: driver
	 * @param		: locator
	 * @param		: key
	 * @author		: Shubham.Saxena2
	 */
	public void sendKeyBoardKey(WebDriver driver,By locator, Keys key ) {
		try {
			driver.findElement(locator).sendKeys(key);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method Name : pressTabKey 
	 * Purpose     : Method to press tab key
	 * @param      : None
	 * @return     : None
	 * @Author     : EVRY
	 */
	public void pressTabKey() {
		try {
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
		} catch (AWTException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method Name : pressEnterKey 
	 * Purpose     : Method to press Enter key
	 * @param      : None
	 * @return     : None
	 * @Author     : EVRY
	 */
	public void pressEnterKey() {
		try {
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method Name : getBrowserName 
	 * Purpose     : Method to get Browser Name
	 * @param      : driver
	 * @return     : Browser Name
	 * @Author     : EVRY
	 */
	public String getBrowserName(WebDriver driver) {
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = cap.getBrowserName().toLowerCase();
		if (browserName.equals("firefox"))
			return "FF";
		else if (browserName.equals("chrome"))
			return "GC";
		else
			return "IE";
	}

	/**
	 * Method Name : uploadFile 
	 * Purpose     : Method to upload file
	 * @param      : AutoITFilePath, browserName, UploadFilePath
	 * @return     : status
	 * @Author     : EVRY
	 */
	public boolean uploadFile(String AutoITFilePath, String browserName, String UploadFilePath)
			throws IOException, InterruptedException {
		boolean status=false;

		Process result = Runtime.getRuntime().exec(AutoITFilePath + " " + browserName + " " + UploadFilePath);
		// Necessary to put space before browserName & UploadFilePath in above
		// command
		result.waitFor();

		if (result.exitValue() == 1) {
			//File uploaded successfully
			status=true;
		} else {
			//File upload failed
			status=false;
		}
		waitForTime(15000);
		return status;
	}

	/**
	 * Method Name : mouseHoverByElement 
	 * Purpose     : Method to mouse hover on element
	 * @param      : driver,element
	 * @return     : None
	 * @Author     : EVRY
	 */
	public void mouseHoverByElement(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	/**
	 * Method Name : selectByValue 
	 * Purpose     : Method for selecting the drop down
	 * by its values 
	 * @param      : gDriver,driverWait,identifyBylocator,valuetoSelect
	 * @return     : None
	 * @Author     : EVRY
	 */
	public static void selectByValue(WebDriver driver, WebDriverWait driverWait, By locator, String value) {

		try {
			element = getWebElement(driver, driverWait, locator);
			Select select = new Select(element);
			select.selectByValue(value);

		} catch (Exception e) {

			Assert.assertTrue("Exception occured : " + e.getMessage(), false);
		}
	}

	/**
	 * Method Name : selectByText 
	 * Purpose     : Method for selecting the drop down by
	 * visible text
	 * @param      : gDriver,driverWait,identifyBylocator,valuetoSelect
	 * @return     : None
	 * @Author     : EVRY
	 */
	public static void selectByText(WebDriver driver, WebDriverWait driverWait, By locator, String value) {
		try {
			element = getWebElement(driver, driverWait, locator);
			Select select = new Select(element);
			select.selectByVisibleText(value);

		} catch (Exception e) {

			Assert.assertTrue("Exception occured : " + e.getMessage(), false);
		}
	}


	/**
	 * Method Name : selectByIndex 
	 * Purpose     : Method for selecting the drop down
	 * by its index value
	 * @param      : gDriver,driverWait,identifyBylocator,Index
	 * @return     : None
	 * @Author     : EVRY
	 */
	public static void selectByIndex(WebDriver driver, WebDriverWait driverWait, By locator, int index) {
		try {
			element = getWebElement(driver, driverWait, locator);
			Select select = new Select(element);
			select.deselectByIndex(index);
		} catch (Exception e) {

			Assert.assertTrue("Exception occured : " + e.getMessage(), false);
		}
	}

	/**
	 * Method Name : selectByIndex Purpose: Method for selecting the drop down
	 * by its index value
	 * 
	 * @param: gDriver,driverWait,webelement,Index
	 * @return None
	 * @Author: EVRY
	 */
	public static void selectByIndex(WebDriver driver, WebDriverWait driverWait, WebElement element, int index) {

		try {
			Select select = new Select(element);
			select.deselectByIndex(index);
		} catch (Exception e) {

			Assert.assertTrue("Exception occured : " + e.getMessage(), false);
		}
	}

	/**
	 * Method Name : selectByValue Purpose: Method for selecting the drop down
	 * by its values
	 * 
	 * @param: driver,driverWait,webelement,value to select
	 * @return None
	 * @Author: EVRY
	 */
	public static void selectByValue(WebDriver driver, WebDriverWait driverWait, WebElement element, String value) {

		try {
			Select select = new Select(element);
			select.selectByValue(value);

		} catch (Exception e) {

			Assert.assertTrue("Exception occured : " + e.getMessage(), false);
		}
	}

	/**
	 * Method Name : selectByText 
	 * Purpose: Method for selecting the drop down by
	 * visible text
	 * @param      : gDriver,driverWait,webelement,value to select
	 * @return     : None
	 * @Author     : EVRY
	 */
	public static void selectByText(WebDriver driver, WebDriverWait driverWait, WebElement element, String value) {
		try {
			Select select = new Select(element);
			Thread.sleep(1000);
			select.selectByVisibleText(value);

		} catch (Exception e) {

			Assert.assertTrue("Exception occured : " + e.getMessage(), false);
		}
	}

	/**
	 * Method Name : jsClick 
	 * Purpose     : Method to click using JavascriptExecutor
	 * @param      : driver, element
	 * @return     : None
	 * @Author     : EVRY
	 */
	public void jsClick(WebDriver driver, WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method Name : selectCheckBoxeInDropdown 
	 * Purpose     : this method select
	 * the check box items listed in the dropdown
	 * @param      : driver, driverWait, element, value
	 * @return     : None
	 * @Author     : EVRY
	 */
	public void selectCheckBoxeInDropdown(WebDriver driver, WebDriverWait driverWait, WebElement element,
			String value) {
		int totalElements = 0;
		String baseXpath_DropdownListItem = "//div[@class='ui-multiselect-items-wrapper']/ul/li";
		String derivedXpath_DropdownListItem = "";
		String actual_txtOnElement = "", expected_txtOnElement = value;

		click(driver, driverWait, element);
		List<WebElement> checkBoxesInDropdown = driver.findElements(By.xpath(baseXpath_DropdownListItem));
		totalElements = checkBoxesInDropdown.size();
		for (int i = 1; i <= totalElements; i++) {
			derivedXpath_DropdownListItem = baseXpath_DropdownListItem + "[" + i + "]";
			actual_txtOnElement = getText(driver, driverWait, By.xpath(derivedXpath_DropdownListItem));
			if (expected_txtOnElement.equalsIgnoreCase(actual_txtOnElement)) {
				click(driver, driverWait, By.xpath(derivedXpath_DropdownListItem));
				break;
			}
		}
	}

	/**
	 * Method name : getAttributeValue 
	 * Purpose     : The method returns the value in
	 * attribute of the web element passed to it
	 * @param      : element, attributeName
	 * @return     : value in given attribute
	 * @author     : EVRY
	 */
	public String getAttributeValue(WebElement element, String attributeName) {

		String attributeValue = "";

		attributeValue = element.getAttribute(attributeName);

		return attributeValue;

	}

	/**
	 * Method name : dragandDrop 
	 * Purpose     : Method to drap and drop file from source to target
	 * @param      : dragandDrop, srclocator, destlocator
	 * @return     : None
	 * @author     : EVRY
	 */
	public static void dragandDrop(WebDriver driver, By srclocator, By destlocator) {

		Actions actions = new Actions(driver);
		try {
			WebElement element = driver.findElement(srclocator);
			WebElement target = driver.findElement(destlocator);
			actions.dragAndDrop(element, target).build().perform();
			;
		} catch (Exception e) {

			Assert.assertTrue(
					"Exception in the method dragAndDrop for the element " + element + " due to  " + e.getMessage(),
					false);
		}
	}
	
	/**
	 * Method name : attachScreenshot
	 * Purpose     : Method to attach screenshot
	 * @param      : screenPath
	 * @return     : None
	 * @author     : EVRY
	 */
	public static void attachScreenshot(String screenPath) {

		try {
			Reporter.addScreenCaptureFromPath(screenPath, "ClickHereToShowScreenshot");

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Method name : getElementsCount
	 * Purpose     : Method that returns total number of elements present in the given locator
	 * @param      : driver, driverWait, elementlocator - locator of the element
	 * @return     : totalElements
	 * @author     : EVRY
	 */
	public int getElementsCount(WebDriver driver, WebDriverWait driverWait, By elementlocator){
		int totalElements=0;

		try {
			totalElements = driver.findElements(elementlocator).size();
		} catch (Exception e) {

			Assert.assertTrue("Exception occured : " + e.getMessage(), false);
		}
		return totalElements;
	}
	
	/**
	 * Method name : getElementsCount
	 * Purpose     : Method that returns total number of elements present in the given web-element list
	 * @param      : Webelement List
	 * @return     : totalElements
	 * @author     : EVRY
	 */
	public int getElementsCount(List<WebElement> eleList){
		int totalElements=0;

		try {
			totalElements = eleList.size();
		} catch (Exception e) {
			totalElements=0;
		}
		return totalElements;
	}
	
	/**
	 * Method name : waitForPresenceOfWebElement
	 * Purpose     : Method waits for the presence of WebElement
	 * @param      : driver, driverWait, element 
	 * @return     : totalElements
	 * @author     : EVRY
	 */
	public static void waitForPresenceOfWebElement(WebDriver driver,WebDriverWait driverWait,WebElement element) {

	driverWait.until(ExpectedConditions.visibilityOf(element));

	}
	
	/**
	 * Method name : waitForElementToBeClickable
	 * Purpose     : Method waits for the element to be clickable
	 * @param      : driver, driverWait, element 
	 * @return     : totalElements
	 * @author     : EVRY
	 */
	public static void waitForElementToBeClickable(WebDriver driver,WebDriverWait driverWait, WebElement element ) {

		driverWait.until(ExpectedConditions.elementToBeClickable(element));

	}
	
	/**
	* Method Name : pageScrollUp
	* Purpose     : Method to scroll page to the top of the page
	* @param      : driver
	* @return     : None
	* @Author     : EVRY
	*/

	public void pageScrollUp(WebDriver driver) {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
	}
	
	/**
	* Method Name : refreshPage
	* Purpose     : Refresh a Page
	* @param      : driver
	* @return     : None
	* @Author     : CBRE
	*/
    public void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }
    
	/**
	 * Method Name : isWebElementExists 
	 * Purpose: return true if web element
	 * present in the page  
	 * @param      : driver,element
	 * @return     : boolean value true/false
	 * @Author     : EVRY
	 */
	public static boolean isWebElementExistsInPage(WebDriver driver, WebDriverWait driverWait, WebElement ele) {
		try {
			driverWait.until(ExpectedConditions.visibilityOf(ele));
			return ele.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * Method Name : isWebElementPresent 
	 * Purpose     : return true if web element
	 * present in the page  
	 * @param      : driver,element
	 * @return     : boolean value true/false
	 * @Author     : EVRY
	 */
	public static boolean isWebElementPresent(WebDriver driver, WebDriverWait driverWait, WebElement ele) {
		try {
			driverWait.until(ExpectedConditions.visibilityOf(ele));
			return ele.isDisplayed();
		} catch (Exception e) {
			
			return false;
		}

	}
}
