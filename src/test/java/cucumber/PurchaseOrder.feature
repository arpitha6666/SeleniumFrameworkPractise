Feature: Purchase the order from ecommerce website

  Background:
    Given I landed on ECommerce page

  @Regression
  Scenario Outline: Positive test of submitting the order
    Given I logged in with username <username> and password <password>
    When Add the product <searchByPrd> to cart
    And checkout <searchByPrd> and submit the order by selecting the country <country>
    Then "<verificationMsg>" message is displayed on the ConfirmationPage
    Examples:
    | username | password | searchByPrd| country|verificationMsg|
    |ars123@gmail.com|Arsars123|ZARA COAT 3|  India  |THANKYOU FOR THE ORDER.|
    |shetty@gmail.com|Iamking@000|ADIDAS ORIGINAL| India |THANKYOU FOR THE ORDER. |

