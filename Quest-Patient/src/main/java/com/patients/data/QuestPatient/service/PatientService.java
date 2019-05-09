package com.patients.data.QuestPatient.service;

import com.patients.data.QuestPatient.model.Patient;
import com.patients.data.QuestPatient.repo.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private Repo patientRepo;

    public List<Patient> getAllPatients() {
        return patientRepo.getAllPatients();
    }

    public Patient getPatient(String id) {
        return patientRepo.getPatient(id);
    }

    public void addPatient(Patient patient) {
        patientRepo.addPatient(patient);
    }

    public void deletePatient(String id) {
        patientRepo.deletePatient(id);
    }

    public void deleteByLastName(String lastName) {
        patientRepo.deleteByLastName(lastName);
    }
}
