package ua.bondarenkojek;

import ua.bondarenkojek.model.Patient;
import ua.bondarenkojek.model.SuperPatient;
import ua.bondarenkojek.model.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.HashSet;

public class Main {
    private static EntityManager entityManager;

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("PatientPU");
        entityManager = entityManagerFactory.createEntityManager();

//        Patient patient =
//                new SuperPatient("Bob", "Bobsky", LocalDate.of(1992, 2, 1),"super");
//        createPatient(patient);
//        Patient patient = Patient
//                .builder()
//                .name("Bob")
//                .lastName("Bobsky")
//                .dateOfBirth(LocalDate.of(1990, 1, 7))
//                .tests(new HashSet<>())
//                .build();
//        createPatient(patient);
//
//        Test test1 = Test
//                .builder()
//                .name("First Test")
//                .date(LocalDate.of(2019, 1, 1))
//                .build();
//
//        Test test2 = Test
//                .builder()
//                .name("Second Test")
//                .date(LocalDate.of(2019, 1, 2))
//                .build();
//
//        patient.addTest(test1);
//        patient.addTest(test2);
//
//        createPatient(patient);

        System.out.println(getPatientByName("Bob"));

//        Patient patient = getPatient(1);
//
//        System.out.println(patient);

//        Patient patient = deletePatient(1);
//        System.out.println(patient);

//        deletePatient(1);
    }

    public static void createPatient(Patient patient) {
        entityManager.getTransaction().begin();
        entityManager.persist(patient);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    public static void createPatient(SuperPatient patient) {
        entityManager.getTransaction().begin();
        entityManager.persist(patient);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    public static Patient getPatient(long id) {
        entityManager.getTransaction().begin();
        Patient patient = entityManager.find(Patient.class, id);
        entityManager.getTransaction().commit();
        return patient;
    }

    public static void updatePatient(Patient patient) {
        entityManager.getTransaction().begin();
        entityManager.merge(patient);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    public static Patient deletePatient(long id) {
        Patient patient = getPatient(id);
        entityManager.getTransaction().begin();
        entityManager.remove(patient);
        entityManager.getTransaction().commit();
        return patient;
    }

    public static Patient getPatientByName(String name) {
        TypedQuery<Patient> query =
                entityManager
                        .createQuery("select p from patient p where name = :name",Patient.class)
                        .setParameter("name", name);
        return query.getSingleResult();
    }
}
