package com.patient.patientRecords.dtos.request;
import lombok.Data;

@Data
public class MedicalHistoryDTO {
    private String medCondition;
    private String diagnosisDate;
    private String treatment;
}

