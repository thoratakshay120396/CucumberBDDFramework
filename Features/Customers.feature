Feature: Add new customer
Background: Prerequisite
Given User launch chrome browser
    When User open URL "https://admin-demo.nopcommerce.com/admin/"
    And User enter Email as "admin@yourstore.com" and Password as "admin"
    And click on Login button
    Then User can view Dashboard
    When User click on Customers Menu
    And click on customers Menu Item
    
@Sanity
  Scenario: Add new customer to the application
    
    And click on Add new button
    Then Customer can view Add new customer page
    When User enters customer info
    And Click on save button
    Then User should view confirmation message "The new customer has been added successfully."  
    And close browser
    
    @Regression
   Scenario: Search Customer by Email 
	And Enter customer EMail
	When Click on search button
	Then User should found Email in the Search table
	And close browser
	
	@Regression
	Scenario: Search Customer by Name
	And Enter customer First Name
	And Enter customer Last Name
	When Click on search button
	Then User should found Name in the Search table
	And close browser 