package uce.edu.ec.apicitapractica.applicacition.representation;

import java.time.LocalDateTime;

import jakarta.json.bind.annotation.JsonbDateFormat;


public class CitaRepresentation {
    public Long id;
    
    // Para RECIBIR desde Vue (Simplificado)
    public Long cedulaDoctor;
    public Long cedulaPaciente;
    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm")
    public LocalDateTime fechacita;

    // Para ENVIAR a la tabla de Vue (Opcional pero recomendado)
    public String nombreDoctor;
    public String nombrePaciente;
}