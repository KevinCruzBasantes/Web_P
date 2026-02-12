package uce.edu.ec.grupo6.application.representation;

import java.time.LocalDate;
import java.time.LocalTime;

import uce.edu.ec.grupo6.domain.Doctor;
import uce.edu.ec.grupo6.domain.Paciente;
import uce.edu.ec.grupo6.domain.CitaMedica.EstadoCita;

public class CitaMedicaRepresentation {
    public Long id;
    public Doctor doctor;
    public Paciente paciente;
    public LocalDate fechacita;
    public LocalTime horacita;
    public String motivo;
    public EstadoCita estadocita;
    public CitaMedicaRepresentation() {
    }
    public CitaMedicaRepresentation(Long id, Doctor doctor, Paciente paciente, LocalDate fechacita, LocalTime horacita,
            String motivo, EstadoCita estadocita) {
        this.id = id;
        this.doctor = doctor;
        this.paciente = paciente;
        this.fechacita = fechacita;
        this.horacita = horacita;
        this.motivo = motivo;
        this.estadocita = estadocita;
    }
    
    
}
