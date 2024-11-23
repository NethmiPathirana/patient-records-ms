package com.patient.patientRecords.dtos.request;

import lombok.Data;

@Data
public class PrescriptionDTO {
    private String medication;
    private String dosage;
    private String frequency;
    private String prescribedDate;
}
