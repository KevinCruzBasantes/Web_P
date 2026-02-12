package uce.edu.ec.apicitapractica.applicacition;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.ec.apicitapractica.applicacition.representation.PacienteRepresentation;
import uce.edu.ec.apicitapractica.domain.Paciente;
import uce.edu.ec.apicitapractica.infrastructure.PacienteRepository;

@ApplicationScoped
public class PacienteService {
    @Inject
    private PacienteRepository pacienteRepository;

    @Transactional
    public PacienteRepresentation gurdar(PacienteRepresentation pacRepre){
        Paciente paciente = this.mapperToPaciente(pacRepre);
        this.pacienteRepository.persist(paciente);
        return this.mapperToPR(paciente);
    }

    public List<PacienteRepresentation> listar(){
        List<PacienteRepresentation> listaPac = new ArrayList<>();
        for(Paciente paciente: this.pacienteRepository.listAll()){
            listaPac.add(this.mapperToPR(paciente));
        }
        return listaPac;

    }
    private PacienteRepresentation mapperToPR(Paciente paciente){
        PacienteRepresentation pacRepre = new PacienteRepresentation();
        pacRepre.cedula = paciente.cedula;
        pacRepre.nombre=paciente.nombre;
        pacRepre.apellido=paciente.apellido;
        pacRepre.fechanacimiento=paciente.fechanacimiento;
        return pacRepre;
    }

    private Paciente mapperToPaciente(PacienteRepresentation pacRepre){
        Paciente paciente = new Paciente();
        paciente.cedula=pacRepre.cedula;
        paciente.nombre=pacRepre.nombre;
        paciente.apellido=pacRepre.apellido;
        paciente.fechanacimiento=pacRepre.fechanacimiento;
        return paciente;
    }
}
