package com.patient.patientRecords.controllers;

import com.patient.patientRecords.dtos.request.LabResultDTO;
import com.patient.patientRecords.dtos.request.PrescriptionDTO;
import com.patient.patientRecords.entity.LabResult;
import com.patient.patientRecords.entity.Prescription;
import com.patient.patientRecords.service.LabResultService;
import com.patient.patientRecords.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health-sync")
public class PrescriptionController{
    private final PrescriptionService prescriptionService;

    @Autowired
    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @PostMapping("/patients/{patientId}/prescription")
    public ResponseEntity<String> addPrescription(@PathVariable Long patientId, @RequestBody PrescriptionDTO prescriptionDTO) {
        try {
            Prescription savedPrescription = prescriptionService.addPrescription(patientId, prescriptionDTO);
            return new ResponseEntity<>("Prescription has been successfully added for Patient ID " + patientId, HttpStatus.CREATED);
        } catch (RuntimeException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-prescription/{id}")
    public ResponseEntity<String> deletePrescription(@PathVariable Long id) {
        boolean isDeleted = prescriptionService.deletePrescription(id);
        if (isDeleted) {
            return new ResponseEntity<>("Prescription with ID " + id + " has been successfully deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Prescription with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-prescription/{id}")
    public ResponseEntity<String> updatePrescription(@PathVariable Long id, @RequestBody PrescriptionDTO prescriptionDTO) {

        boolean isUpdated = prescriptionService.updatePrescription(id, prescriptionDTO);

        if (isUpdated) {
            return new ResponseEntity<>("Prescription with ID " + id + " has been successfully updated.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Prescription with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}
