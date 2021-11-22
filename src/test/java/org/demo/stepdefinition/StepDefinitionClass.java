package org.demo.stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.demo.baseclass.BaseClass;
import org.json.simple.JSONArray;
import org.junit.Assert;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.demo.commonmethods.CommonReusableMethods.*;

/**
 *
 * Step Definition Class
 */
public class StepDefinitionClass extends BaseClass
{
    @Given("{string} API should be available")
    public void api_should_be_available(String apiName)
    {
        RestAssured.baseURI = baseUrl;
        httpRequest = RestAssured.given().relaxedHTTPSValidation();
        httpResponse = getUrlResponse(apiName);
        int statusCode = httpResponse.getStatusCode();
        Assert.assertEquals(200,statusCode);
    }


    @When("Make a GET call to user API with username {string}")
    public void make_a_get_call_to_user_api_with_username(String user)throws Exception
    {
        userName=user;
        userDetails = getResponseBody("users");
        jsonArray = (JSONArray) parser.parse(userDetails);
        boolean result = findSpecificUser(jsonArray,userName);
        Assert.assertTrue(result);
    }

    @When("Make a post call with userId and collect all of the post details")
    public void make_a_post_call_with_userId_and_collect_all_of_the_post_details()throws Exception
    {
        postId = getAllPostIdByUserId(userId);
    }

    @When("Process all the comments by postIds")
    public void process_all_the_comments_by_postIds()throws Exception
    {
        if(postId.size() > 0)
            getAllCommentsIdByPostId(postId);
    }

    @Then("Print the user details")
    public void print_the_user_details()
    {
        System.out.println("User Details Are - ");
        userId = findUserId(jsonArray,userName);
        System.out.println(getResponseBody("users/"+userId));
    }

    @Then("Captured the userId of that user for further execution")
    public void captured_the_user_id_of_that_user_for_further_execution()
    {
        userId = findUserId(jsonArray,userName);
    }

    @Then("{string} API request should be successful with OK response")
    public void api_request_should_be_successful_with_ok_response(String apiName)
    {
        httpResponse = getUrlResponse("users/"+userId);
        Assert.assertEquals(200,httpResponse.getStatusCode());
    }

    @Then("{string} API request should be successful with response OK")
    public void api_request_should_be_successful_with_response_ok(String apiName)
    {
        httpResponse = getUrlResponse("posts");
        Assert.assertEquals(200,httpResponse.getStatusCode());
    }

    @Then("Verify emails present in comments")
    public void verify_emails_present_in_comments()
    {
        int count = 0;
        Pattern pattern = Pattern.compile(emailRegex);

        for(String email:emails)
        {
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches())
                count++;
        }

        Assert.assertEquals(count,emails.size());
    }
}
