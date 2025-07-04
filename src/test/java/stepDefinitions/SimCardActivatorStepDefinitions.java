package stepDefinitions;

import au.com.telstra.simcardactivator.SimCardActivator;
import au.com.telstra.simcardactivator.dto.SimActivationRequest;
import au.com.telstra.simcardactivator.entity.SimActivationRecord;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = SimCardActivator.class)
public class SimCardActivatorStepDefinitions {

    @Autowired
    private TestRestTemplate restTemplate;

    private SimActivationRequest request;
    private int lastRecordId;

    @Given("a functional sim card")
    public void aFunctionalSimCard() {
        request = new SimActivationRequest("1255789453849037777", "horatio.yakima@groovemail.com");
        lastRecordId = 1; // Auto-increment starts from 1
    }

    @Given("a broken sim card")
    public void aBrokenSimCard() {
        request = new SimActivationRequest("8944500102198304826", "notorious.criminal@gonepostal.com");
        lastRecordId = 2;
    }

    @When("a request to activate the sim card is submitted")
    public void aRequestToActivateTheSimCardIsSubmitted() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<SimActivationRequest> entity = new HttpEntity<>(request, headers);
        restTemplate.postForEntity("http://localhost:8080/sim/activate", entity, String.class);
    }

    @Then("the sim card is activated and its state is recorded to the database")
    public void theSimCardIsActivatedAndItsStateIsRecordedToTheDatabase() {
        SimActivationRecord simRecord = restTemplate.getForObject(
                "http://localhost:8080/sim/record?simCardID={id}", SimActivationRecord.class, lastRecordId);
        assertTrue(simRecord.isActive());
    }

    @Then("the sim card fails to activate and its state is recorded to the database")
    public void theSimCardFailsToActivateAndItsStateIsRecordedToTheDatabase() {
        SimActivationRecord simRecord = restTemplate.getForObject(
                "http://localhost:8080/sim/record?simCardID={id}", SimActivationRecord.class, lastRecordId);
        assertFalse(simRecord.isActive());
    }
}
