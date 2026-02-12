package uce.edu.ec.apicitapractica.domain;

import java.time.LocalDate;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="paciente")
public class Paciente extends PanacheEntityBase{
    @Id
    @Column(length = 10, nullable = false, unique = true)
    public Long cedula;
    @Column(length = 50, nullable = false)
    public String nombre;
    @Column(length = 50, nullable = false)
    public String apellido;
    @Column(nullable = false)
    public LocalDate fechanacimiento;

}
