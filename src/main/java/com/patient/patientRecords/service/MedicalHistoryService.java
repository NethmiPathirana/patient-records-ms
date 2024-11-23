package com.patient.patientRecords.service;

import com.patient.patientRecords.dtos.request.MedicalHistoryDTO;

public interface MedicalHistoryService {
    String addMedicalRecord(Long patientId, MedicalHistoryDTO medicalHistoryDTO);

    boolean deleteMedicalHistory(Long id);

    boolean updateMedicalHistory(Long id, MedicalHistoryDTO medicalHistoryDTO);
}
