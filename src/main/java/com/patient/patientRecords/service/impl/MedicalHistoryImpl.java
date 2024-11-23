package com.patient.patientRecords.service.impl;

import com.patient.patientRecords.dtos.request.MedicalHistoryDTO;
import com.patient.patientRecords.entity.MedicalHistory;
import com.patient.patientRecords.entity.Patient;
import com.patient.patientRecords.repositories.MedicalHistoryRepository;
import com.patient.patientRecords.repositories.PatientRepository;
import com.patient.patientRecords.service.MedicalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicalHistoryImpl implements MedicalHistoryService {
    private final MedicalHistoryRepository medicalHistoryRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public MedicalHistoryImpl(MedicalHistoryRepository medicalHistoryRepository,
                              PatientRepository patientRepository) {
        this.medicalHistoryRepository = medicalHistoryRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public String addMedicalRecord(Long patientId, MedicalHistoryDTO medicalHistoryDTO) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        MedicalHistory medicalHistory = new MedicalHistory();
        medicalHistory.setMedCondition(medicalHistoryDTO.getMedCondition());
        medicalHistory.setDiagnosisDate(medicalHistoryDTO.getDiagnosisDate());
        medicalHistory.setTreatment(medicalHistoryDTO.getTreatment());
        medicalHistory.setPatient(patient);
        medicalHistoryRepository.save(medicalHistory);
        return "Medical record added successfully";
    }

    @Override
    public boolean deleteMedicalHistory(Long id) {
        if (medicalHistoryRepository.existsById(id)) {
            medicalHistoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateMedicalHistory(Long id, MedicalHistoryDTO medicalHistoryDTO) {
        Optional<MedicalHistory> optionalMedicalHistory = medicalHistoryRepository.findById(id);

        if (optionalMedicalHistory.isPresent()) {
            MedicalHistory medicalHistory = optionalMedicalHistory.get();
            medicalHistory.setMedCondition(medicalHistoryDTO.getMedCondition());
            medicalHistory.setDiagnosisDate(medicalHistoryDTO.getDiagnosisDate());
            medicalHistory.setTreatment(medicalHistoryDTO.getTreatment());
            medicalHistoryRepository.save(medicalHistory);
            return true;
        } else {
            return false;
        }
    }
}
