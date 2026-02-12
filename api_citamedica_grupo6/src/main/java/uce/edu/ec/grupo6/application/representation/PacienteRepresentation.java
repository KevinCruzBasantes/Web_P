package uce.edu.ec.grupo6.application.representation;

import java.time.LocalDate;

public class PacienteRepresentation {
    public Long id;
    public String nombre;
    public String apellido;
    public String cedula;
    public String celular;
    public LocalDate fechanacimiento;
    public String direccion;
    public PacienteRepresentation() {
    }
    public PacienteRepresentation(Long id, String nombre, String apellido, String cedula, String celular,
            LocalDate fechanacimiento, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.celular = celular;
        this.fechanacimiento = fechanacimiento;
        this.direccion = direccion;
    }

    
}
