package com.patient.patientRecords.dtos.response;

import lombok.Data;

@Data
public class MedicalHistoryResDTO {
    private String medCondition;
    private String diagnosisDate;
    private String treatment;
}
