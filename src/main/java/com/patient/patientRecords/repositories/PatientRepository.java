package com.patient.patientRecords.repositories;

import com.patient.patientRecords.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
