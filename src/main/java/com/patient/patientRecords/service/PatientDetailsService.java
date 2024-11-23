package com.patient.patientRecords.service;

import com.patient.patientRecords.dtos.request.PatientDTO;
import com.patient.patientRecords.dtos.response.PatientDetailsResDTO;
import com.patient.patientRecords.dtos.response.PatientResDTO;
import com.patient.patientRecords.entity.Patient;

import java.util.List;

public interface PatientDetailsService {
    PatientResDTO savePatient(PatientDTO patientDTO);

    boolean deletePatient(Long id);

    boolean updatePatient(Long id, PatientDTO patientDTO);

    PatientDetailsResDTO getPatientDetails(Long id);

    List<Patient> getAllPatients();
}
