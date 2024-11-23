package com.patient.patientRecords.service.impl;

import com.patient.patientRecords.dtos.request.PatientDTO;
import com.patient.patientRecords.dtos.response.PatientDetailsResDTO;
import com.patient.patientRecords.dtos.response.PatientResDTO;
import com.patient.patientRecords.entity.Patient;
import com.patient.patientRecords.repositories.PatientRepository;
import com.patient.patientRecords.service.PatientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientDetailsServiceImpl implements PatientDetailsService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientDetailsServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public PatientResDTO savePatient(PatientDTO patientDTO) {

        // Map PatientDTO to Patient entity
        Patient patient = new Patient();
        patient.setFirstName(patientDTO.getFirstName());
        patient.setLastName(patientDTO.getLastName());
        patient.setGender(patientDTO.getGender());
        patient.setDob(patientDTO.getDob());
        patient.setContactInfo(patientDTO.getContactInfo());

        // Save patient entity to the database
        Patient patient1 = patientRepository.save(patient);
        PatientResDTO patientResDTO = new PatientResDTO();
        patientResDTO.setFirstName(patient1.getFirstName());
        patientResDTO.setLastName(patient1.getLastName());
        patientResDTO.setGender(patient1.getGender());
        patientResDTO.setDob(patient1.getDob());
        patientResDTO.setContactInfo(patient1.getContactInfo());
        return patientResDTO;

    }

    @Override
    public boolean deletePatient(Long id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePatient(Long id, PatientDTO patientDTO) {
        Optional<Patient> existingPatient = patientRepository.findById(id);

        if (existingPatient.isPresent()) {
            Patient patient = existingPatient.get();
            patient.setFirstName(patientDTO.getFirstName());
            patient.setLastName(patientDTO.getLastName());
            patient.setGender(patientDTO.getGender());
            patient.setDob(patientDTO.getDob());
            patient.setContactInfo(patientDTO.getContactInfo());

            patientRepository.save(patient);  // Update patient with the same ID
            return true;
        }

        return false;

    }

    @Override
    public PatientDetailsResDTO getPatientDetails(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);

        if (patient.isPresent()) {
            Patient patient1 = patient.get();
            PatientDetailsResDTO patientDetailsResDTO = new PatientDetailsResDTO();
            patientDetailsResDTO.setFirstName(patient1.getFirstName());
            patientDetailsResDTO.setLastName(patient1.getLastName());
            patientDetailsResDTO.setGender(patient1.getGender());
            patientDetailsResDTO.setDob(patient1.getDob());
            patientDetailsResDTO.setContactInfo(patient1.getContactInfo());
            patientDetailsResDTO.setMedicalHistory(patient1.getMedicalHistory());
            patientDetailsResDTO.setPrescriptions(patient1.getPrescriptions());
            patientDetailsResDTO.setLabResults(patient1.getLabResults());
            return patientDetailsResDTO;
        }
        return null;
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

}
