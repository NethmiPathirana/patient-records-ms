package com.patient.patientRecords.repositories;

import com.patient.patientRecords.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
}
