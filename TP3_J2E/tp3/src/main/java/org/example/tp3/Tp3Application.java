package org.example.tp3;

import org.example.tp3.entities.Patient;
import org.example.tp3.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class Tp3Application implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(Tp3Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        patientRepository.save(new Patient(null,"Rem",new Date(),false,34));
        patientRepository.save(new Patient(null,"Hafssa",new Date(),false,44));
        patientRepository.save(new Patient(null,"Samia",new Date(),false,54));
    }
}
