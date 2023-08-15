package step.definitions;

import genaricUtil.EndPoint;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FetchAnchorSummary {
    private Response response;
    @cucumber.api.java.en.Given("^Providing the autharization$")
    public void providingTheAutharization() {
        RestAssured.given().header("Authorization", "Bearer "+token);
    }

    @cucumber.api.java.en.When("^Providing the Endpoints to fetch the Details$")
    public void providingTheEndpointsToFetchTheDetails() {
        response = RestAssured.when().get(EndPoint.FetchSchemeTypes);
    }

    @cucumber.api.java.en.Then("^Validate all the status code$")
    public void validateAllTheStatusCode() {
        int statusCode = response.then().extract().statusCode();
        System.out.println("statusLine is "+statusLine);
    }

    @cucumber.api.java.en.And("^Status Line$")
    public void statusLine() {
        String statusLine = response.then().extract().statusLine();
        System.out.println("statusLine is "+statusLine);
    }
}