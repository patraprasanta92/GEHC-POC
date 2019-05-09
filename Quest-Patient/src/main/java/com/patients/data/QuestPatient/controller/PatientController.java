package com.patients.data.QuestPatient.controller;

import com.patients.data.QuestPatient.model.Patient;
import com.patients.data.QuestPatient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping("/getPatients")
    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }

    @GetMapping("/getPatient/{id}")
    public Patient getPatient(@PathVariable String id) {
        return patientService.getPatient(id);
    }

    @DeleteMapping("/deletePatient/{id}")
    public void deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
    }

    @PostMapping("/addPatient")
    @ResponseStatus(value = HttpStatus.CREATED, reason="Records successfully added.")
    public void addPatient(@RequestBody Patient patient) {
        patientService.addPatient(patient);
    }

//    @DeleteMapping("/deletePatient/{lastName}")
//    public void deletePatient(@PathVariable String lastName) {
//        patientService.deleteByLastName(lastName);
//    }

}
