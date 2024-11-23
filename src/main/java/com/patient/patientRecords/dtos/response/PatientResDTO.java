package com.patient.patientRecords.dtos.response;

import lombok.Data;

@Data
public class PatientResDTO {
    private String firstName;
    private String lastName;
    private String gender;
    private String dob;
    private String contactInfo;
}
