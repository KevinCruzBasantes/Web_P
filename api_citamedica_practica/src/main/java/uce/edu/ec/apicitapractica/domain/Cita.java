package uce.edu.ec.apicitapractica.domain;

import java.time.LocalDateTime;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="cita")
@SequenceGenerator(name="cita_seq", sequenceName = "cita_secuencia" , allocationSize = 1)
public class Cita extends PanacheEntityBase{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cita_seq")
    public Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="doctor_cedula_fk")
    public Doctor doctor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="paciente_cedula_fk")
    public Paciente paciente;
    @Column(nullable = false)
    public LocalDateTime fechacita;
}
