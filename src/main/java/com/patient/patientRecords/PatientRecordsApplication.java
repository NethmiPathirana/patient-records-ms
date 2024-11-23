package com.patient.patientRecords;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com/patient/patientRecords")
public class PatientRecordsApplication {
//    echo "# patient-records-ms" >> README.md
//    git init
//    git add README.md
//    git commit -m "first commit"
//    git branch -M main
//    git remote add origin https://github.com/NethmiPathirana/patient-records-ms.git
//    git push -u origin main
    public static void main(String[] args) {
        SpringApplication.run(PatientRecordsApplication.class, args);
    }
}
