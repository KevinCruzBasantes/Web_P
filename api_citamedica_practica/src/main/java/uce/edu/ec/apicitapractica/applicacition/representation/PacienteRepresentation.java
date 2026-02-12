package uce.edu.ec.apicitapractica.applicacition.representation;

import java.time.LocalDate;

public class PacienteRepresentation {
    public Long cedula;
    public String nombre;
    public String apellido;
    public LocalDate fechanacimiento;
    public PacienteRepresentation() {
    }
    public PacienteRepresentation(Long cedula, String nombre, String apellido, LocalDate fechanacimiento) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechanacimiento = fechanacimiento;
    }
    
}
