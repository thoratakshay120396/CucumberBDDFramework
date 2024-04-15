Feature: Login
  @Sanity
  Scenario: Successful login with valid credentials
    Given User launch chrome browser
    When User open URL "https://admin-demo.nopcommerce.com/admin/"
    And User enter Email as "admin@yourstore.com" and Password as "admin"
    And click on Login button
    Then Page title should be "Dashboard / nopCommerce administration"
    When User click on Log out link
    Then Page title should be "Your store. Login"
    And close browser

@Regression
 Scenario Outline: Successful login with valid credentials
  Given User launch chrome browser
    When User open URL "https://admin-demo.nopcommerce.com/admin/"
    And User enter Email as "<email>" and Password as "<password>"
    And click on Login button
    Then Page title should be "Dashboard / nopCommerce administration"
    When User click on Log out link
    Then Page title should be "Your store. Login"
    And close browser
    
   
Examples: 
|email|password|
|admin@yourstore.com|admin|
|test@yourstore.com|admin|