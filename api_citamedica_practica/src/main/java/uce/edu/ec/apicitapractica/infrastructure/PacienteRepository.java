package uce.edu.ec.apicitapractica.infrastructure;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.ec.apicitapractica.domain.Paciente;

@ApplicationScoped
public class PacienteRepository implements PanacheRepositoryBase<Paciente,Long>{

}
