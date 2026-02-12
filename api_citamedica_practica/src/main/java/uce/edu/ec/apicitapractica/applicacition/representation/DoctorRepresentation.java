package uce.edu.ec.apicitapractica.applicacition.representation;

import uce.edu.ec.apicitapractica.domain.Doctor.Genero;

public class DoctorRepresentation {
    public Long cedula;
    public String nombre;
    public String apellido;
    public Genero genero;
    public DoctorRepresentation() {
    }
    public DoctorRepresentation(Long cedula, String nombre, String apellido, Genero genero) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
    }

    
}
