package com.patient.patientRecords.controllers;

import com.patient.patientRecords.dtos.request.LabResultDTO;
import com.patient.patientRecords.entity.LabResult;
import com.patient.patientRecords.service.LabResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health-sync")
public class LabResultController {
    private final LabResultService labResultService;

    @Autowired
    public LabResultController(LabResultService labResultService) {
        this.labResultService = labResultService;
    }

    @PostMapping("/patients/{patientId}/lab-result")
    public ResponseEntity<String> addLabResult(@PathVariable Long patientId, @RequestBody LabResultDTO labResultDTO) {
        try {
            LabResult savedLabResult = labResultService.addLabResult(patientId, labResultDTO);
            return new ResponseEntity<>("Lab result has been successfully added for Patient ID " + patientId, HttpStatus.CREATED);
        } catch (RuntimeException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-lab-result/{id}")
    public ResponseEntity<String> deleteMedicalHistory(@PathVariable Long id) {
        boolean isDeleted = labResultService.deleteLabResult(id);
        if (isDeleted) {
            return new ResponseEntity<>("Lab result record with ID " + id + " has been successfully deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Lab result  record with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-lab-result/{id}")
    public ResponseEntity<String> updateMedicalHistory(@PathVariable Long id, @RequestBody LabResultDTO labResultDTO) {

        boolean isUpdated = labResultService.updateLabResult(id, labResultDTO);

        if (isUpdated) {
            return new ResponseEntity<>("Lab result record with ID " + id + " has been successfully updated.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Lab result record with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}
