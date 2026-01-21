package com.dayan.cookinghub;

import com.dayan.cookinghub.model.Recipe;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
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

    String latestRecipeTitle;

    @Given("^no recipes are in the DB$")
    public void noRecipesAreInTheDB() throws Throwable {
        restTemplate.delete("/recipes");
    }

    @When("^the client calls /recipes$")
    public void theClientCallsRecipes() throws Throwable {
        response = restTemplate.getForEntity("/recipes", String.class);
    }

    @Then("^the client receives status code of (\\d+)$")
    public void theClientReceivesStatusCodeOf(int code) throws Throwable {
        assertThat(code, is(response.getStatusCodeValue()));
    }

    @Then("^the client receives an empty list$")
    public void theClientReceivesAnEmptyList() throws Throwable {
        String body = response.getBody();
        assertThat(body, containsString("\"content\":[]"));
    }

    @Given("^the client adds a recipe named (.+)$")
    public void theClientAddsARecipe(String title) throws Throwable {
        Recipe r = new Recipe(title, "thing number 1. Then thing number 2. Then thing number3");
        response = restTemplate.postForEntity("/recipes", r, String.class);
        latestRecipeTitle = title;
    }

    @When("^the client retrieves the new recipe$")
    public void theClientRetrievesTheNewRecipe() throws Throwable {
        response = restTemplate.getForEntity(String.format("/recipes/%s", latestRecipeTitle), String.class);
    }

    @Then("^the recipe just added is returned$")
    public void theRecipeIsReturned() throws Throwable {
        assertThat(response.getBody(), containsString(latestRecipeTitle));
    }
}


