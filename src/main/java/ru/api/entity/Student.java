package ru.api.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student")
@NoArgsConstructor
@Getter
@Setter
public class Student {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_seq")
    @SequenceGenerator(name = "student_id_seq", sequenceName = "student_id_seq", allocationSize = 1, initialValue = 2)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "passport", nullable = false)
    private String passport;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassport() {
        return passport;
    }
}
