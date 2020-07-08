@googleSearch
Feature: Google Search
 
Scenario Outline: verify Google search 
	Given user is on the google search page 
	When user enter the search text : "<Search Text>"
	And click on google search button
	Then user gets search results
	Examples:
	|Search Text|
	|Hello			|