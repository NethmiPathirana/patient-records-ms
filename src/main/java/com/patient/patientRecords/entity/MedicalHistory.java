package com.patient.patientRecords.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String medCondition;
    private String diagnosisDate;
    private String treatment;

    @ManyToOne
    @JoinColumn(name = "patient_id") // Foreign key to Patient entity
    @JsonIgnore
    private Patient patient;

    // Getters and setters
}
