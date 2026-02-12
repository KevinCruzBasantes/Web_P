package uce.edu.ec.grupo6.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.ec.grupo6.application.representation.PacienteRepresentation;
import uce.edu.ec.grupo6.domain.Paciente;
import uce.edu.ec.grupo6.infrastructure.PacienteRepository;

@ApplicationScoped
public class PacienteService {
    @Inject
    private PacienteRepository pacienteRepository;

    @Transactional
    public PacienteRepresentation guardar(PacienteRepresentation pacienteRepre) {
        // 1. Convertimos el DTO a Entidad
        Paciente paciente = this.mapperToPaciente(pacienteRepre);

        // 2. Persistimos (aquí se genera el ID en el objeto 'paciente')
        this.pacienteRepository.persist(paciente);

        // 3. Convertimos la entidad (que ya tiene ID) de vuelta a DTO y lo retornamos
        return this.mapperToPR(paciente);
    }

    public List<PacienteRepresentation> listarTodos() {
        List<PacienteRepresentation> listaPacientesRepre = new ArrayList<>();
        for (Paciente paciente : this.pacienteRepository.listAll()) {
            listaPacientesRepre.add(this.mapperToPR(paciente));
        }
        return listaPacientesRepre;
    }

    @Transactional
    public PacienteRepresentation actualizar(Long id, PacienteRepresentation pacienteRepre) {
        Paciente paciente = this.pacienteRepository.findById(id);
        if (paciente == null) {
            return null;
        }
        paciente.nombre = pacienteRepre.nombre;
        paciente.apellido = pacienteRepre.apellido;
        paciente.cedula = pacienteRepre.cedula;
        paciente.celular = pacienteRepre.celular;
        paciente.fechanacimiento = pacienteRepre.fechanacimiento;
        paciente.direccion = pacienteRepre.direccion;
        return this.mapperToPR(paciente);
    }

    @Transactional
    public boolean eleminar(Long id) {
        return this.pacienteRepository.deleteById(id);
    }

    private PacienteRepresentation mapperToPR(Paciente paciente) {
        PacienteRepresentation pacRepre = new PacienteRepresentation();
        pacRepre.id = paciente.id;
        pacRepre.nombre = paciente.nombre;
        pacRepre.apellido = paciente.apellido;
        pacRepre.cedula = paciente.cedula;
        pacRepre.celular = paciente.celular;
        pacRepre.fechanacimiento = paciente.fechanacimiento;
        pacRepre.direccion = paciente.direccion;

        return pacRepre;

    }

    private Paciente mapperToPaciente(PacienteRepresentation pacRepre) {
        Paciente paciente = new Paciente();
        paciente.nombre = pacRepre.nombre;
        paciente.apellido = pacRepre.apellido;
        paciente.cedula = pacRepre.cedula;
        paciente.celular = pacRepre.celular;
        paciente.fechanacimiento = pacRepre.fechanacimiento;
        paciente.direccion = pacRepre.direccion;
        return paciente;
    }

}
