package managers;

import org.openqa.selenium.WebDriver;

import functionLibrary.JavaGeneralUtilities;

import pageObjects.GoogleSearchPage;


public class PageObjectManager {

	private WebDriver driver;
	private JavaGeneralUtilities genUtils;
	private GoogleSearchPage googleSearchPage;
	
	
	

	public PageObjectManager(WebDriver driver) {

		this.driver = driver;

	}
	
	public JavaGeneralUtilities getGeneralUtilitiesObject() {

		return (genUtils == null) ? genUtils = new JavaGeneralUtilities() : genUtils;

	}

	public GoogleSearchPage getGoogleSearchPage() {

		return (googleSearchPage == null) ? googleSearchPage = new GoogleSearchPage(driver) : googleSearchPage;

	}

}