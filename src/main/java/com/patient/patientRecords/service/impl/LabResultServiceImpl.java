package com.patient.patientRecords.service.impl;

import com.patient.patientRecords.dtos.request.LabResultDTO;
import com.patient.patientRecords.entity.LabResult;
import com.patient.patientRecords.entity.MedicalHistory;
import com.patient.patientRecords.entity.Patient;
import com.patient.patientRecords.repositories.LabResultRepository;
import com.patient.patientRecords.repositories.PatientRepository;
import com.patient.patientRecords.service.LabResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class LabResultServiceImpl implements LabResultService {
    private final LabResultRepository labResultRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public LabResultServiceImpl(LabResultRepository labResultRepository,
                                PatientRepository patientRepository) {
        this.labResultRepository = labResultRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public LabResult addLabResult(Long patientId, LabResultDTO labResultDTO) throws RuntimeException{
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);

        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            LabResult labResult = new LabResult();
            labResult.setTestName(labResultDTO.getTestName());
            labResult.setDate(labResultDTO.getDate());
            labResult.setResult(labResultDTO.getResult());
            labResult.setPatient(patient);

            return labResultRepository.save(labResult);
        } else {
            throw new RuntimeException("Patient with ID " + patientId + " not found.");
        }
    }



    @Override
    public boolean deleteLabResult(Long id) {
        if (labResultRepository.existsById(id)) {
            labResultRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateLabResult(Long id, LabResultDTO labResultDTO) {
        Optional<LabResult> optionalLabResult = labResultRepository.findById(id);

        if (optionalLabResult.isPresent()) {
            LabResult labResult = optionalLabResult.get();
            labResult.setDate(labResultDTO.getDate());
            labResult.setResult(labResultDTO.getResult());
            labResult.setTestName(labResultDTO.getTestName());
            labResultRepository.save(labResult);
            return true;
        } else {
            return false;
        }
    }
}
