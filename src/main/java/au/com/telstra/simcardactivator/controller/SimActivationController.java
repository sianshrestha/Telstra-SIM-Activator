package au.com.telstra.simcardactivator.controller;

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
    public ResponseEntity<String> activateSim(@RequestBody SimActivationRequest request) {
        boolean success = activationService.activateSimCard(request);

        System.out.println("SIM Activation for ICCID " + request.getIccid() + ":" + (success ? "SUCCESS" : "FAILED"));

        return ResponseEntity.ok("Activation Status: " + (success ? "Success" : "Failed"));

    }
}
