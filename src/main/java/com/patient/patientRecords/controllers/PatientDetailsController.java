package com.patient.patientRecords.controllers;

import com.patient.patientRecords.dtos.request.PatientDTO;
import com.patient.patientRecords.dtos.response.PatientDetailsResDTO;
import com.patient.patientRecords.dtos.response.PatientResDTO;
import com.patient.patientRecords.entity.Patient;
import com.patient.patientRecords.service.PatientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/health-sync")
public class PatientDetailsController {

    private final PatientDetailsService patientDetailsService;

    @Autowired
    public PatientDetailsController(PatientDetailsService patientDetailsService) {
        this.patientDetailsService = patientDetailsService;
    }

    @PostMapping("/add-patient")
    public ResponseEntity<PatientResDTO> addPatient(@RequestBody PatientDTO patientDTO) {
        PatientResDTO savedPatient = patientDetailsService.savePatient(patientDTO);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-patient/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        boolean isDeleted = patientDetailsService.deletePatient(id);

        if (isDeleted) {
            return new ResponseEntity<>("Patient with ID " + id + " has been successfully deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Patient with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-patient/{id}")
    public ResponseEntity<String> updatePatient(@PathVariable Long id, @RequestBody PatientDTO patientDTO) {
        boolean isUpdated = patientDetailsService.updatePatient(id, patientDTO);

        if (isUpdated) {
            return new ResponseEntity<>("Patient with ID " + id + " has been successfully updated.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Patient with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-patient/{id}")
    public ResponseEntity<Object> getPatientDetails(@PathVariable Long id) {
        PatientDetailsResDTO patientDetailsResDTO = patientDetailsService.getPatientDetails(id);

        if (patientDetailsResDTO != null) {
            return new ResponseEntity<>(patientDetailsResDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Patient with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/all-patients")
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientDetailsService.getAllPatients();
        return ResponseEntity.ok(patients);
    }


}


