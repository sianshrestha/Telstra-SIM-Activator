package au.com.telstra.simcardactivator.service;

import au.com.telstra.simcardactivator.component.SimCardActuationHandler;
import au.com.telstra.simcardactivator.entity.SimActivationRecord;
import au.com.telstra.simcardactivator.foundation.ActuationResult;
import au.com.telstra.simcardactivator.foundation.SimCard;
import au.com.telstra.simcardactivator.repository.SimCardRecordRepository;
import org.springframework.stereotype.Service;

@Service
public class SimActivationService {

    private final SimCardActuationHandler actuationHandler;
    private final SimCardRecordRepository repository;

    public SimActivationService(SimCardActuationHandler actuationHandler, SimCardRecordRepository repository) {
        this.actuationHandler = actuationHandler;
        this.repository = repository;
    }

    public boolean activateSimCard(SimCard simCard, String customerEmail) {
        boolean success = false;
        try {
            ActuationResult result = actuationHandler.actuate(simCard);
            success = result != null && result.isSuccess();
        } catch (Exception e) {
            System.err.println("Actuation Failed: " + e.getMessage());
            return false;
        }

        SimActivationRecord record = new SimActivationRecord(simCard.getIccid(), customerEmail, success);
        repository.save(record);

        return success;
    }

    public SimActivationRecord getRecordById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
