package ua.bondarenkojek.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "patient")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "patient_id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "test")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Test> tests;

    public Patient(String name, String lastName, LocalDate dateOfBirth) {
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public void addTest(Test test) {
        if (tests == null) {
            tests = new HashSet<>();
        }
        tests.add(test);
    }
}
