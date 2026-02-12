package uce.edu.ec.grupo6.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.ec.grupo6.application.representation.CitaMedicaRepresentation;
import uce.edu.ec.grupo6.domain.CitaMedica;
import uce.edu.ec.grupo6.domain.Doctor;
import uce.edu.ec.grupo6.domain.Paciente;
import uce.edu.ec.grupo6.infrastructure.CitaMedicaRepository;
import uce.edu.ec.grupo6.infrastructure.DoctorRepository;
import uce.edu.ec.grupo6.infrastructure.PacienteRepository;

@ApplicationScoped
public class CitaMedicaService {
    @Inject
    private CitaMedicaRepository citaMedicaRepository;
    @Inject
    private DoctorRepository doctorRepository;
    @Inject
    private PacienteRepository pacienteRepository;

    public List<CitaMedicaRepresentation> listarTodos() {
        List<CitaMedicaRepresentation> listaCitas = new ArrayList<>();
        for (CitaMedica cita : this.citaMedicaRepository.listAll()) {
            listaCitas.add(this.mapperToCR(cita));
        }
        return listaCitas;
    }

    @Transactional
    public CitaMedicaRepresentation guardar(CitaMedicaRepresentation citaRepre) {
        Doctor doctorDB = this.doctorRepository.findById(citaRepre.doctor.id);
        Paciente pacienteDB = this.pacienteRepository.findById(citaRepre.paciente.id);

        if (doctorDB != null && pacienteDB != null) {
            CitaMedica nuevaCita = mapperToCitaMedica(citaRepre);
            nuevaCita.doctor = doctorDB;
            nuevaCita.paciente = pacienteDB;

            // Al persistir, se genera el ID
            this.citaMedicaRepository.persist(nuevaCita);

            // IMPORTANTE: Retornamos el objeto mapeado que ya incluye el ID generado
            return this.mapperToCR(nuevaCita);
        }
        return null; // O lanzar una excepción personalizada
    }

    @Transactional
    public CitaMedicaRepresentation actualizar(Long id, CitaMedicaRepresentation citaRepre) {
        // 1. Buscamos la cita que queremos modificar
        CitaMedica citaDB = this.citaMedicaRepository.findById(id);
        if (citaDB == null)
            return null;

        // 2. Buscamos al doctor y paciente (por si cambiaron en el JSON)
        Doctor doctorDB = this.doctorRepository.findById(citaRepre.doctor.id);
        Paciente pacienteDB = this.pacienteRepository.findById(citaRepre.paciente.id);

        if (doctorDB != null && pacienteDB != null) {
            // 3. Actualizamos los datos simples
            citaDB.fechacita = citaRepre.fechacita;
            citaDB.horacita = citaRepre.horacita;
            citaDB.motivo = citaRepre.motivo;
            citaDB.estadocita = citaRepre.estadocita;

            // 4. Actualizamos las relaciones
            citaDB.doctor = doctorDB;
            citaDB.paciente = pacienteDB;

            return mapperToCR(citaDB);
        }
        return null;
    }

    @Transactional
    public boolean eliminar(Long id) {
        return this.citaMedicaRepository.deleteById(id);
    }

    public CitaMedicaRepresentation obtenerPorId(Long id) {
        CitaMedica cita = this.citaMedicaRepository.findById(id);
        if (cita == null) {
            return null;
        }
        return this.mapperToCR(cita);
    }

    private CitaMedicaRepresentation mapperToCR(CitaMedica cita) {
        CitaMedicaRepresentation citaRepre = new CitaMedicaRepresentation();
        citaRepre.id = cita.id;
        citaRepre.doctor = cita.doctor;
        citaRepre.paciente = cita.paciente;
        citaRepre.fechacita = cita.fechacita;
        citaRepre.horacita = cita.horacita;
        citaRepre.motivo = cita.motivo;
        citaRepre.estadocita = cita.estadocita;
        return citaRepre;
    }

    private CitaMedica mapperToCitaMedica(CitaMedicaRepresentation citaRepre) {
        CitaMedica cita = new CitaMedica();
        cita.fechacita = citaRepre.fechacita;
        cita.horacita = citaRepre.horacita;
        cita.motivo = citaRepre.motivo;
        cita.estadocita = citaRepre.estadocita;
        return cita;
    }
}
