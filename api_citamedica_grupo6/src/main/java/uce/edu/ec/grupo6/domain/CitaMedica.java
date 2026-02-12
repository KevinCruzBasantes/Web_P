package uce.edu.ec.grupo6.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="citas_medicas")
@SequenceGenerator(name = "cita_seq", sequenceName = "cita_secuencia", allocationSize = 1)
public class CitaMedica extends PanacheEntityBase{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cita_seq")
    public Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="doctor_id_fk" , nullable = false)
    public Doctor doctor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="paciente_id_fk", nullable = false)
    public Paciente paciente;
    @Column(nullable = false)
    public LocalDate fechacita;
    @Column(nullable = false)
    @JsonbDateFormat("HH:mm")
    public LocalTime horacita;
    @Column(length = 500, nullable = false)
    public String motivo;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public EstadoCita estadocita;
    public enum EstadoCita{
        CREADA,
        CANCELADA,
        COMPLETADA
    }
}
