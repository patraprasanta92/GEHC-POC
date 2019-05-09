package com.patients.data.QuestPatient.repo;

import com.patients.data.QuestPatient.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class Repo {

    @Autowired
    private EntityManager entityManager;

    public List<Patient> getAllPatients() {
        return entityManager.createNativeQuery("select * from patient",Patient.class).getResultList();
    }

    public Patient getPatient(String id) {
        return (Patient)entityManager.createNativeQuery("select * from patient where id = '" + id + "'",Patient.class).getSingleResult();
    }

    public void addPatient(Patient patient) {
        entityManager.persist(patient);
    }

    public void deletePatient(String id) {
        Patient patient = (Patient)entityManager.createNativeQuery("select * from patient where id = '" + id + "'",Patient.class).getSingleResult();
        entityManager.remove(patient);
    }

    public void deleteByLastName(String lastName) {
        entityManager.createNativeQuery("delete from patient where last_name = '" + lastName + "'",Patient.class);
    }
}
