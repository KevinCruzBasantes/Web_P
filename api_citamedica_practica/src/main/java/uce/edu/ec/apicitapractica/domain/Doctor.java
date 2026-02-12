package uce.edu.ec.apicitapractica.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "doctor")
public class Doctor extends PanacheEntityBase{
    @Id
    @Column(length = 10, nullable = false, unique = true)
    public Long cedula;
    @Column(length = 50, nullable = false)
    public String nombre;
    @Column(length = 50, nullable = false)
    public String apellido;
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    public Genero genero;

    public enum Genero {
        MASCULINO,
        FEMENINO
    }
}
