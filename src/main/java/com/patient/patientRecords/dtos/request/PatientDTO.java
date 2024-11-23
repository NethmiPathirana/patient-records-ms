package com.patient.patientRecords.dtos.request;

import lombok.Data;

@Data
public class PatientDTO {
    private String firstName;
    private String lastName;
    private String gender;
    private String dob;
    private String contactInfo;
}
