package ua.bondarenkojek.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;

@Entity
@Table(name = "superpatient")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuperPatient extends Patient {
    @Column(name = "power")
    private String power;

    public SuperPatient(String name, String lastName, LocalDate dateOfBirth, String power) {
        super(name, lastName, dateOfBirth);
        this.power = power;
    }
}
