package com.patient.patientRecords.service;

import com.patient.patientRecords.dtos.request.LabResultDTO;
import com.patient.patientRecords.entity.LabResult;

public interface LabResultService {
    LabResult addLabResult(Long patientId, LabResultDTO labResultDTO) throws RuntimeException;

    boolean deleteLabResult(Long id);

    boolean updateLabResult(Long id, LabResultDTO labResultDTO);
}
