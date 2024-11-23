package com.patient.patientRecords.dtos.request;
import lombok.Data;

@Data
public class LabResultDTO {
    private String testName;
    private String result;
    private String date;

}
