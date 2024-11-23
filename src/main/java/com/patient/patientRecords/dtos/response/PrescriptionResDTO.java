package com.patient.patientRecords.dtos.response;

import lombok.Data;

@Data
public class PrescriptionResDTO {
    private String medication;
    private String dosage;
    private String frequency;
    private String prescribedDate;
}
