Feature: Sanity Test for JsonPlaceHolder API's.

  @Test1
  Scenario: Search a specific user
    Given "users" API should be available
    When Make a GET call to user API with username "Delphine"
    Then Print the user details
    And Captured the userId of that user for further execution
    And "users" API request should be successful with OK response

    @Test2
    Scenario: Get all the posts posted by the user
      Given "posts" API should be available
      When Make a GET call to user API with username "Delphine"
      Then Captured the userId of that user for further execution
      And Make a post call with userId and collect all of the post details
      And "posts" API request should be successful with response OK

      @Test3
      Scenario: Validate emails from comments section
        Given "comments" API should be available
        When Make a GET call to user API with username "Delphine"
        Then Captured the userId of that user for further execution
        And Make a post call with userId and collect all of the post details
        And Process all the comments by postIds
        And Verify emails present in comments

