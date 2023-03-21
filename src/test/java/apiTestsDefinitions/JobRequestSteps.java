package apiTestsDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;

import static io.restassured.RestAssured.baseURI;

public class JobRequestSteps {

    private Response response;

    @Given("job endpoint exists")
    public void job_endpoint_exists() {

        baseURI = "https://apiv2-eu.devo.com/search/job/remove/";
    }

    @When("I send a valid job request")
    public void i_send_a_valid_job_request() {

        JSONObject myBody = new JSONObject();
        JSONObject destination = new JSONObject();
        JSONObject params = new JSONObject();

        myBody.put("from", "20ad");
        myBody.put("destination", destination);

        destination.put("type", "email");
        destination.put("params", params);

        params.put("email.to", "pperanton@gmail.com");

        RequestSpecification myHeader = RestAssured.given().pathParam("jobId", "973700a9-afb5-4069-972b-0a929b5d4edd");
        myHeader.header("Content-Type", "application/json");
        myHeader.header("Authorization", "Bearer 3b27ee980b87284463da35a89cdb0374");

        RequestSpecification fullRequest = myHeader.body(myBody.toString());
        response = fullRequest.when().get("{jobId}");
    }

    @Then("validations on the response must be OK")
    public void validations_on_the_response_must_be_ok() {
        //Storing data to validations
        String contentType = response.header("Content-Type");
        String status = response.jsonPath().get("status");
        String objectStatus = response.jsonPath().get("object.status");
        String objectId = response.jsonPath().get("object.id");
        String table = response.jsonPath().get("Table");
        String domain = response.jsonPath().get("Domain");

        //Assertions
        Assert.assertEquals(contentType, "application/json");
        Assert.assertEquals(status, "0");
        Assert.assertEquals(objectStatus, "REMOVED");
        Assert.assertEquals(objectId, "J1 jobID");
        Assert.assertEquals(table, "demo.ecommerce.data");
        Assert.assertEquals(domain, "qacodechallenge");
    }

   /* @Test
    public void getJobs() {
        String url = "https://apiv2-eu.devo.com/search/jobs";

        JSONObject myBody = new JSONObject();
        JSONObject destination = new JSONObject();
        JSONObject params = new JSONObject();

        myBody.put("from", "3000ad");
        myBody.put("destination", destination);

        RequestSpecification myHeader = RestAssured.given();
        myHeader.header("Content-Type", "application/json");
        myHeader.header("Authorization", "Bearer 3b27ee980b87284463da35a89cdb0374");

        RequestSpecification fullRequest = myHeader.body(myBody.toString());
        Response response = fullRequest.when().get(url);

        Headers headers = response.headers();
        ResponseBody body = response.getBody();

        System.out.println("Response header is:" + headers.toString());
        System.out.println("Response body is:" + body.asPrettyString());
    }*/

     /*
        *******Response body for above Request is:************
         *
         * {
         *     "status": 0,
         *     "cid": "1f02febaca06",
         *     "timestamp": 1638267013672,
         *     "object": [
         *
         *     ]
         * }
         }
         */
}
