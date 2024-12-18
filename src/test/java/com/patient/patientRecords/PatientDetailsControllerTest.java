package com.patient.patientRecords;

import com.patient.patientRecords.controllers.PatientDetailsController;
import com.patient.patientRecords.dtos.request.PatientDTO;
import com.patient.patientRecords.dtos.response.PatientDetailsResDTO;
import com.patient.patientRecords.dtos.response.PatientResDTO;
import com.patient.patientRecords.entity.Patient;
import com.patient.patientRecords.service.PatientDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PatientDetailsControllerTest {

    @Mock
    private PatientDetailsService patientDetailsService;

    @InjectMocks
    private PatientDetailsController patientDetailsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPatient() {
        // Arrange
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setFirstName("John");
        patientDTO.setLastName("Doe");
        patientDTO.setGender("Male");
        patientDTO.setDob("1990-01-01");
        patientDTO.setContactInfo("1234567890");

        PatientResDTO patientResDTO = new PatientResDTO();
        patientResDTO.setFirstName("John");
        patientResDTO.setLastName("Doe");
        patientResDTO.setGender("Male");
        patientResDTO.setDob("1990-01-01");
        patientResDTO.setContactInfo("1234567890");

        when(patientDetailsService.savePatient(patientDTO)).thenReturn(patientResDTO);

        // Act
        ResponseEntity<PatientResDTO> response = patientDetailsController.addPatient(patientDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(patientResDTO, response.getBody());
        verify(patientDetailsService, times(1)).savePatient(patientDTO);
    }

    @Test
    void testDeletePatient_Success() {
        // Arrange
        Long patientId = 1L;
        when(patientDetailsService.deletePatient(patientId)).thenReturn(true);

        // Act
        ResponseEntity<String> response = patientDetailsController.deletePatient(patientId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Patient with ID 1 has been successfully deleted.", response.getBody());
        verify(patientDetailsService, times(1)).deletePatient(patientId);
    }

    @Test
    void testDeletePatient_NotFound() {
        // Arrange
        Long patientId = 1L;
        when(patientDetailsService.deletePatient(patientId)).thenReturn(false);

        // Act
        ResponseEntity<String> response = patientDetailsController.deletePatient(patientId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Patient with ID 1 not found.", response.getBody());
        verify(patientDetailsService, times(1)).deletePatient(patientId);
    }

    @Test
    void testUpdatePatient_Success() {
        // Arrange
        Long patientId = 1L;
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setFirstName("Jane");
        patientDTO.setLastName("Doe");
        patientDTO.setGender("Female");
        patientDTO.setDob("1992-05-15");
        patientDTO.setContactInfo("0987654321");

        when(patientDetailsService.updatePatient(patientId, patientDTO)).thenReturn(true);

        // Act
        ResponseEntity<String> response = patientDetailsController.updatePatient(patientId, patientDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Patient with ID 1 has been successfully updated.", response.getBody());
        verify(patientDetailsService, times(1)).updatePatient(patientId, patientDTO);
    }

    @Test
    void testUpdatePatient_NotFound() {
        // Arrange
        Long patientId = 1L;
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setFirstName("Jane");
        patientDTO.setLastName("Doe");
        patientDTO.setGender("Female");
        patientDTO.setDob("1992-05-15");
        patientDTO.setContactInfo("0987654321");

        when(patientDetailsService.updatePatient(patientId, patientDTO)).thenReturn(false);

        // Act
        ResponseEntity<String> response = patientDetailsController.updatePatient(patientId, patientDTO);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Patient with ID 1 not found.", response.getBody());
        verify(patientDetailsService, times(1)).updatePatient(patientId, patientDTO);
    }

    @Test
    void testGetPatientDetails_Success() {
        // Arrange
        Long patientId = 1L;
        PatientDetailsResDTO patientDetails = new PatientDetailsResDTO();
        patientDetails.setFirstName("John");
        patientDetails.setLastName("Doe");
        patientDetails.setGender("Male");
        patientDetails.setDob("1990-01-01");
        patientDetails.setContactInfo("1234567890");

        when(patientDetailsService.getPatientDetails(patientId)).thenReturn(patientDetails);

        // Act
        ResponseEntity<Object> response = patientDetailsController.getPatientDetails(patientId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patientDetails, response.getBody());
        verify(patientDetailsService, times(1)).getPatientDetails(patientId);
    }

    @Test
    void testGetPatientDetails_NotFound() {
        // Arrange
        Long patientId = 1L;
        when(patientDetailsService.getPatientDetails(patientId)).thenReturn(null);

        // Act
        ResponseEntity<Object> response = patientDetailsController.getPatientDetails(patientId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Patient with ID 1 not found.", response.getBody());
        verify(patientDetailsService, times(1)).getPatientDetails(patientId);
    }

    @Test
    void testGetAllPatients() {
        // Arrange
        List<Patient> patients = new ArrayList<>();
        Patient patient1 = new Patient();
        patient1.setFirstName("John");
        patient1.setLastName("Doe");
        patient1.setGender("Male");
        patient1.setDob("1990-01-01");
        patient1.setContactInfo("1234567890");

        Patient patient2 = new Patient();
        patient2.setFirstName("Jane");
        patient2.setLastName("Doe");
        patient2.setGender("Female");
        patient2.setDob("1992-05-15");
        patient2.setContactInfo("0987654321");

        patients.add(patient1);
        patients.add(patient2);

        when(patientDetailsService.getAllPatients()).thenReturn(patients);

        // Act
        ResponseEntity<List<Patient>> response = patientDetailsController.getAllPatients();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patients, response.getBody());
        verify(patientDetailsService, times(1)).getAllPatients();
    }
}