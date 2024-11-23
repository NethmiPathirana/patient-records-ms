package com.patient.patientRecords.dtos.response;

import com.patient.patientRecords.entity.LabResult;
import com.patient.patientRecords.entity.MedicalHistory;
import com.patient.patientRecords.entity.Prescription;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class PatientDetailsResDTO {
    private String firstName;
    private String lastName;
    private String gender;
    private String dob;
    private String contactInfo;
    private List<Prescription> prescriptions;
    private List<LabResult> labResults;
    private List<MedicalHistory> medicalHistory;
}
