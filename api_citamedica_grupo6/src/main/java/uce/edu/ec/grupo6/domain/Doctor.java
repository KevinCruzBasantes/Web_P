package uce.edu.ec.grupo6.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="doctores")
@SequenceGenerator(name="doctor_seq", sequenceName = "doctor_secuencia", allocationSize = 1)
public class Doctor extends PanacheEntityBase{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_seq")
    public Long id;
    @NotBlank(message = "Nombre obligatorio")
    @Column(length = 100, nullable = false)
    public String nombre;
    @NotBlank(message = "Apellido Obligatorio")
    @Column(length = 100, nullable = false)
    public String apellido;
    @NotBlank(message = "Licencia medica Obligatoria")
    @Column(length = 10, unique = true, nullable = false)
    public String numerolicenciamedica;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public estadoDoctor estado;
    @Column(length = 100,nullable = false)
    public String especialidad;

    public enum estadoDoctor{
        ACTIVO, INACTIVO
    }
}
