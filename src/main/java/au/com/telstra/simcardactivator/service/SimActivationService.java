package au.com.telstra.simcardactivator.service;

import au.com.telstra.simcardactivator.model.SimActivationRequest;
import au.com.telstra.simcardactivator.model.ActuatorResponse;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SimActivationService {
    private final RestTemplate restTemplate = new RestTemplate();

    public boolean activateSimCard(SimActivationRequest request) {
        String actuatorUrl = "http://localhost:8444/actuate";
        String payload = "{\"iccid\":\"" + request.getIccid() + "\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<ActuatorResponse> response = restTemplate.postForEntity(actuatorUrl, entity, ActuatorResponse.class);

            return response.getBody() != null && response.getBody().isSuccess();

        } catch (Exception e) {
            System.err.println("Failed to call actuator: " + e.getMessage());
            return false;
        }
    }
}
