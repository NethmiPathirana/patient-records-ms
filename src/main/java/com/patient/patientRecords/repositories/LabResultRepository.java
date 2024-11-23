package com.patient.patientRecords.repositories;

import com.patient.patientRecords.entity.LabResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabResultRepository extends JpaRepository<LabResult, Long> {
}
