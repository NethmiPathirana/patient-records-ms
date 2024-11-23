package com.patient.patientRecords.service.impl;

import com.patient.patientRecords.dtos.request.PrescriptionDTO;
import com.patient.patientRecords.entity.LabResult;
import com.patient.patientRecords.entity.Patient;
import com.patient.patientRecords.entity.Prescription;
import com.patient.patientRecords.repositories.LabResultRepository;
import com.patient.patientRecords.repositories.PatientRepository;
import com.patient.patientRecords.repositories.PrescriptionRepository;
import com.patient.patientRecords.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository,
                                PatientRepository patientRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.patientRepository = patientRepository;
    }


    @Override
    public Prescription addPrescription(Long patientId, PrescriptionDTO prescriptionDTO) throws RuntimeException{
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);

        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            Prescription prescription = new Prescription();
            prescription.setMedication(prescriptionDTO.getMedication());
            prescription.setDosage(prescriptionDTO.getDosage());
            prescription.setFrequency(prescriptionDTO.getFrequency());
            prescription.setPrescribedDate(prescriptionDTO.getPrescribedDate());
            prescription.setPatient(patient);

            return prescriptionRepository.save(prescription);
        } else {
            throw new RuntimeException("Patient with ID " + patientId + " not found.");
        }
    }

    @Override
    public boolean deletePrescription(Long id) {
        if (prescriptionRepository.existsById(id)) {
            prescriptionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePrescription(Long id, PrescriptionDTO prescriptionDTO) {
        Optional<Prescription> optionalLabResult = prescriptionRepository.findById(id);

        if (optionalLabResult.isPresent()) {
            Prescription prescription = optionalLabResult.get();
            prescription.setMedication(prescriptionDTO.getMedication());
            prescription.setDosage(prescriptionDTO.getDosage());
            prescription.setFrequency(prescriptionDTO.getFrequency());
            prescription.setPrescribedDate(prescriptionDTO.getPrescribedDate());
            prescriptionRepository.save(prescription);
            return true;
        } else {
            return false;
        }
    }
}
