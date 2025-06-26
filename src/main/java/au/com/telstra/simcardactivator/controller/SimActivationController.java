package au.com.telstra.simcardactivator.controller;

import au.com.telstra.simcardactivator.foundation.SimCard;
import au.com.telstra.simcardactivator.model.SimActivationRequest;
import au.com.telstra.simcardactivator.service.SimActivationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sim")
public class SimActivationController {

    private final SimActivationService activationService;

    public SimActivationController(SimActivationService activationService) {
        this.activationService = activationService;
    }

    @PostMapping("/activate")
    public ResponseEntity<String> activateSim(@RequestBody SimCard simCard) {
        boolean success = activationService.activateSimCard(simCard);
        return ResponseEntity.ok("Activation Status: " + (success ? "Success" : "Failed"));
    }
}
