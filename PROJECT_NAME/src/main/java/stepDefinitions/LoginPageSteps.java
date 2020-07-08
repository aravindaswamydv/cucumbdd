package stepDefinitions;

import org.junit.Assert;

import cucumber.TestContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import enums.DriverType;
import managers.FileReaderManager;
import pageObjects.GoogleSearchPage;

public class LoginPageSteps {
	
	TestContext testContext;
	GoogleSearchPage googleSearchPage;
	
	public LoginPageSteps(TestContext context){
		testContext = context;
		googleSearchPage = testContext.getPageObjectManager().getGoogleSearchPage();
		
	}
	
	@Given("^user is on the google search page$")
	public void user_is_on_the_login_page() {
		googleSearchPage.navigateTo_GoogleSearchPage();
	}

	@When("^user enter the search text : \"([^\"]*)\"$")
	public void enter_searchText(String searchTxt) throws InterruptedException {
		googleSearchPage.enter_SearchText(searchTxt);
		
	}
	
	@And("^click on google search button$")
	public void click_googleSearchBtn() {
		googleSearchPage.click_googleSearchBtn();
	}

	@When("^user gets search results$")
	public void verifySearchresult(){

	}

	

}