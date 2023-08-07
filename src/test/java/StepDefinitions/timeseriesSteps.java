package StepDefinitions;

import API.Timeseries;
import Utility.ENV;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class timeseriesSteps extends Timeseries {
    private RequestSpecification request;
    private Response resp;
    private String route;
    public Map<String,String> headers;

    @Before
    public void setUp(){
        SetUp(ENV.DEV);
        headers = initHeaders();
    }

    @Given("I set {string} and {string}")
    public void i_set_and(String startDate, String endDate) {
        request = given()
                .headers(headers);
        route = getTimeseriesURL(startDate,endDate);
    }

    @When("I use {string} request")
    public void i_use_method_request(String method) {
        resp = callRestMethod(method);
        //resp.prettyPrint();
    }

    @Then("I get status code {int}")
    public void i_get_status_code(int statusCode) {
        resp.then().assertThat()
                .statusCode(statusCode);
    }

    @And("start date is {string} and end date is{string}")
    public void startDateIsAndEndDateIs(String startDate, String endDate) {
        resp.then().assertThat()
                .body("start_date",is(startDate)).and()
                .body("end_date",is(endDate));
    }

    @And("Response contains {string}")
    public void responseContains(String message) {
        resp.then().assertThat()
                .body(containsString(message));
    }

    @Given("I set request apiKey and route to {string},{string}")
    public void iSetRequestApiKeyAndRouteTo(String route, String apiKey) {
        Header header = new Header("apiKey",apiKey);
        request = given()
                .header(header);

        /** start and end date are set hard coded since this method is used to for apiKey and rout testing.
         * This is checked in another test scenario with other step.
         **/
        this.route = getTimeseriesURL(route,"2023-08-01","2023-08-01");
    }


    /** Additional method to use different API methods. Depends on the approach. if we set a strict API method as scenario
     then there is no point in creating or using such method.
     **/
    public Response callRestMethod(String method) {
        Response resp = null;
        switch (method) {
            case "POST": {
                resp = request.post(BASE_URL+ route);
                break;
            }
            case "GET": {
                resp = request.get(BASE_URL+ route);
                break;
            }
            case "PUT": {
                resp = request.put(BASE_URL+ route);
                break;
            }
            case "DELETE": {
                resp = request.delete(BASE_URL+ route);
                break;
            }
            case "PATCH": {
                resp = request.patch(BASE_URL+ route);
                break;
            }
        }
        return resp;
    }
}
