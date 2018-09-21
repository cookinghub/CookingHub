package com.dayan.cookinghub;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@ContextConfiguration
public class StepDefsIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    ResponseEntity<String> response;

    @When("^the client calls /recipes$")
    public void theClientCallsRecipes() throws Throwable {
        response =  restTemplate.getForEntity("/recipes", String.class);
    }

    @Then("^the client receives status code of (\\d+)$")
    public void theClientReceivesStatusCodeOf(int code) throws Throwable {
        assertThat(code, is(response.getStatusCodeValue()));
    }

    @And("^the client receives an empty list$")
    public void theClientReceivesAnEmptyList() throws Throwable {
        String body = response.getBody();
        assertThat(body, containsString("\"content\":[]"));
    }

}