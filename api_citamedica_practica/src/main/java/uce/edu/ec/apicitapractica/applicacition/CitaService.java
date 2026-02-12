package uce.edu.ec.apicitapractica.applicacition;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.ec.apicitapractica.applicacition.representation.CitaRepresentation;
import uce.edu.ec.apicitapractica.domain.Cita;
import uce.edu.ec.apicitapractica.domain.Doctor;
import uce.edu.ec.apicitapractica.domain.Paciente;
import uce.edu.ec.apicitapractica.infrastructure.CitaRepository;
import uce.edu.ec.apicitapractica.infrastructure.DoctorRepository;
import uce.edu.ec.apicitapractica.infrastructure.PacienteRepository;

@ApplicationScoped
public class CitaService {
    @Inject
    private CitaRepository citaRepository;
    @Inject
    private DoctorRepository doctorRepository;
    @Inject
    private PacienteRepository pacienteRepository;

    @Transactional
    public CitaRepresentation guardar(CitaRepresentation citaRepre) {
        // Buscamos directamente por el Long que viene en el DTO
        Doctor doctorDB = doctorRepository.findById(citaRepre.cedulaDoctor);
        Paciente pacienteDB = pacienteRepository.findById(citaRepre.cedulaPaciente);

        if (doctorDB != null && pacienteDB != null) {
            Cita cita = new Cita();
            cita.doctor = doctorDB;
            cita.paciente = pacienteDB;
            cita.fechacita = citaRepre.fechacita;

            citaRepository.persist(cita);

            // Devolvemos el DTO con los nombres para que Vue los muestre en la tabla
            CitaRepresentation response = new CitaRepresentation();
            response.id = cita.id;
            response.cedulaPaciente = pacienteDB.cedula;
            response.nombrePaciente = pacienteDB.nombre + " " + pacienteDB.apellido;
            response.nombreDoctor = doctorDB.nombre + " " + doctorDB.apellido;
            response.fechacita = cita.fechacita;

            return response;
        }
        return null;
    }

    public List<CitaRepresentation> listarTodas() {
        List<Cita> citas = citaRepository.listAll();
        List<CitaRepresentation> listaDTO = new ArrayList<>();

        for (Cita c : citas) {
            CitaRepresentation dto = new CitaRepresentation();
            dto.id = c.id;
            dto.fechacita = c.fechacita;
            dto.nombreDoctor = c.doctor.nombre + " " + c.doctor.apellido;
            dto.nombrePaciente = c.paciente.nombre + " " + c.paciente.apellido;
            dto.cedulaPaciente = c.paciente.cedula;
            listaDTO.add(dto);
        }
        return listaDTO;
    }

    private CitaRepresentation mapperToCR(Cita cita) {
        CitaRepresentation citaRepre = new CitaRepresentation();
        citaRepre.id = cita.id;

        citaRepre.fechacita = cita.fechacita;
        return citaRepre;
    }

    private Cita mapperToCita(CitaRepresentation citaRepre) {
        Cita cita = new Cita();

        cita.fechacita = citaRepre.fechacita;
        return cita;
    }
}
