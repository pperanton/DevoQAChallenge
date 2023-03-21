package apiTestsDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;

import static io.restassured.RestAssured.baseURI;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class QueryRequestSteps {

    private String path;
    private Response response;
    private ResponseBody body;


    @Given("query endpoint exists")
    public void query_endpoint_exists() {
        baseURI = "https://apiv2-eu.devo.com/search";
        path = "/query";
    }

    @When("I send a valid query request")
    public void i_send_a_valid_query_request() {
        RequestSpecification myreq = RestAssured.given();
        myreq.header("Content-Type", "application/json");
        myreq.header("Authorization", "Bearer 3b27ee980b87284463da35a89cdb0374");

        JSONObject myjson = new JSONObject();
        myjson.put("queryId", "a41f6b48-9d9d-47e2-abda-b77451b09058");
        myjson.put("from", "1633075200");
        myjson.put("to", "1633075260");

        JSONObject type = new JSONObject();
        type.put("type", "csv");
        myjson.put("mode", type);
        myjson.put("dateFormat", "default");
        myjson.put("timeZone", "GMT+1");

        myreq.body(myjson.toString());
        response = myreq.post(path);
        body = response.getBody();

    }

    @Then("validations on the response should be OK")
    public void validations_on_the_response_should_be_ok() {

        //Storing data to validations
        String contentType = response.header("Content-Type");
        Date date = new Date();
        String dateyyyyMMddHHmmssSSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date);
        String eventDateFrom = "2021-10-01 09:00:00.000";
        String eventDateTo = "2021-10-01 09:01:00.000";
        String eventDateResponse = response.jsonPath().get("eventdate");
        String columns = "eventdate,method,protocol";

        //Assertions
        Assert.assertEquals(contentType, "text/csv;charset=utf-8;header=present");
        Assert.assertEquals(eventDateResponse, everyItem(greaterThanOrEqualTo(eventDateFrom)));
        Assert.assertEquals(eventDateResponse, everyItem(lessThanOrEqualTo(eventDateTo)));
        //Assert.assertEquals(eventDateResponse, everyItem(DateTimeFormatter.ofPattern(dateyyyyMMddHHmmssSSS)));
        /**I was unable to came by a proper assertion for date format*/
        Assert.assertEquals(body, containsString(columns));
    }
}