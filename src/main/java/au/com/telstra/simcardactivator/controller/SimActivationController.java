package au.com.telstra.simcardactivator.controller;

import au.com.telstra.simcardactivator.entity.SimActivationRecord;
import au.com.telstra.simcardactivator.dto.SimActivationRequest;
import au.com.telstra.simcardactivator.foundation.SimCard;
import au.com.telstra.simcardactivator.service.SimActivationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sim")
public class SimActivationController {

    private final SimActivationService service;

    public SimActivationController(SimActivationService service) {
        this.service = service;
    }

    @PostMapping("/activate")
    public ResponseEntity<String> activateSim(@RequestBody SimActivationRequest request) {
        SimCard simCard = new SimCard(request.getIccid());
        boolean success = service.activateSimCard(simCard, request.getCustomerEmail());
        return ResponseEntity.ok("Activation Status: " + (success ? "Success" : "Failed"));
    }

    @GetMapping("/record")
    public ResponseEntity<SimActivationRecord> getRecord(@RequestParam Long simCardID) {
        SimActivationRecord record = service.getRecordById(simCardID);
        if (record == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(record);
    }
}
