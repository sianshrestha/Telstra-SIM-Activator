package au.com.telstra.simcardactivator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import au.com.telstra.simcardactivator.entity.SimActivationRecord;

public interface SimCardRecordRepository  extends JpaRepository<SimActivationRecord, Long> {}
