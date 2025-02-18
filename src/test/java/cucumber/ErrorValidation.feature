Feature: Error Validation

  Background:
    Given I landed on ECommerce page

  @ErrorValidation
  Scenario Outline: Error validation test submitting the order
    Given I logged in with username <username> and password <password>
    Then "<verificationMsg>" error message is displayed on the ConfirmationPage
    Examples:
      | username | password | verificationMsg|
      |ars123@gmail.com|Arrs123|Incorrect email or password.|