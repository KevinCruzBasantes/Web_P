package uce.edu.ec.grupo6.application.representation;

import uce.edu.ec.grupo6.domain.Doctor.estadoDoctor;

public class DoctorRepresentation {
    public Long id;
    public String nombre;
    public String apellido;
    
    public String numerolicenciamedica;
    public estadoDoctor estado;
    public String especialidad;
    

    public DoctorRepresentation() {
    }


    public DoctorRepresentation(Long id, String nombre, String apellido, String numerolicenciamedica,
            estadoDoctor estado, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numerolicenciamedica = numerolicenciamedica;
        this.estado = estado;
        this.especialidad = especialidad;
    }

    


    
}
