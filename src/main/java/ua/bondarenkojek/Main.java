package ua.bondarenkojek;

import ua.bondarenkojek.model.Patient;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Date;

public class Main {
    private static EntityManager entityManager;

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("PatientPU");
        entityManager = entityManagerFactory.createEntityManager();
//        Patient patient = createPatient("Bob", "Bobik");
        System.out.println(getPatientByName("Bob"));

//        Patient patient = getPatient(1);
//
//        System.out.println(patient);

//        updatePatient(Patient.builder()
//                .id(1)
//                .name("Bobie")
//                .lastName("Last")
//                .date(new Date())
//                .build());


//        Patient.builder()
//                .name("Bob")
//                .lastName("LastBobName");

//        Patient patient = deletePatient(1);
//        System.out.println(patient);
    }

    public static Patient createPatient(String name, String lastName) {
        Date dateOfBirth = new Date();
        entityManager.getTransaction().begin();
        Patient patient = Patient.builder()
                .name(name)
                .lastName(lastName)
                .date(dateOfBirth)
                .build();

        entityManager.persist(patient);
        entityManager.flush();
        entityManager.getTransaction().commit();
        return patient;
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
