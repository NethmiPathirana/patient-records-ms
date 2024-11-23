package com.patient.patientRecords.controllers;

import com.patient.patientRecords.dtos.request.MedicalHistoryDTO;
import com.patient.patientRecords.service.MedicalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health-sync")
public class MedicalHistoryController {
    private final MedicalHistoryService medicalHistoryService;

    @Autowired
    public MedicalHistoryController(MedicalHistoryService medicalHistoryService) {
        this.medicalHistoryService = medicalHistoryService;
    }

    @PostMapping("/patients/{patientId}/medical-records")
    public ResponseEntity<String> addMedicalRecord(@PathVariable Long patientId, @RequestBody MedicalHistoryDTO medicalHistoryDTO) {
        String response = medicalHistoryService.addMedicalRecord(patientId, medicalHistoryDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-medical-history/{id}")
    public ResponseEntity<String> deleteMedicalHistory(@PathVariable Long id) {
        boolean isDeleted = medicalHistoryService.deleteMedicalHistory(id);
        if (isDeleted) {
            return new ResponseEntity<>("Medical history record with ID " + id + " has been successfully deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Medical history record with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-medical-history/{id}")
    public ResponseEntity<String> updateMedicalHistory(@PathVariable Long id, @RequestBody MedicalHistoryDTO medicalHistoryDTO) {

        boolean isUpdated = medicalHistoryService.updateMedicalHistory(id, medicalHistoryDTO);

        if (isUpdated) {
            return new ResponseEntity<>("Medical history record with ID " + id + " has been successfully updated.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Medical history record with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }


}
