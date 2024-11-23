package com.patient.patientRecords.service;

import com.patient.patientRecords.dtos.request.PrescriptionDTO;
import com.patient.patientRecords.entity.Prescription;

public interface PrescriptionService {
    Prescription addPrescription(Long patientId, PrescriptionDTO prescriptionDTO);

    boolean deletePrescription(Long id);

    boolean updatePrescription(Long id, PrescriptionDTO prescriptionDTO);
}
