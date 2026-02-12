package uce.edu.ec.grupo6.domain;

import java.time.LocalDate;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
@Entity
@Table(name="pacientes")
@SequenceGenerator(name="paciente_seq", sequenceName = "paciente_secuencia",allocationSize = 1)
public class Paciente extends PanacheEntityBase{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paciente_seq")
    public Long id;
    @NotBlank(message = "Nombre Obligatorio")
    @Column(length = 100, nullable = false)
    public String nombre;
    @NotBlank(message = "Apellido Obligatorio")
    @Column(length = 100, nullable = false)
    public String apellido;
    @NotBlank(message = "Cedula Obligatoria")
    @Size(min = 10,max = 10, message = "10 digitos obligatorios")
    @Column(length = 10, unique = true,nullable = false)
    public String cedula;
    @Size(min = 10, max=10, message = "10 digitos")
    @Column(length = 10, nullable = false)
    public String celular;
    @Column(nullable = false)
    public LocalDate fechanacimiento;
    @Column(length = 100 , nullable = false)
    public String direccion; 


}
