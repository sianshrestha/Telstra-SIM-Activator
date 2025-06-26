package au.com.telstra.simcardactivator.service;

import au.com.telstra.simcardactivator.component.SimCardActuationHandler;
import au.com.telstra.simcardactivator.foundation.ActuationResult;
import au.com.telstra.simcardactivator.foundation.SimCard;
import au.com.telstra.simcardactivator.model.SimActivationRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SimActivationService {
    private final SimCardActuationHandler actuationHandler;

    public SimActivationService(SimCardActuationHandler actuationHandler) {
        this.actuationHandler = actuationHandler;
    }

    public boolean activateSimCard(SimCard simCard) {
        try {
            ActuationResult result = actuationHandler.actuate(simCard);
            return result != null && result.isSuccess();
        } catch (Exception e) {
            System.err.println("Actuation Failed: " + e.getMessage());
            return false;
        }
    }
}
