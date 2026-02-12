package uce.edu.ec.grupo6.infrastructure;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.ec.grupo6.domain.Paciente;
@ApplicationScoped
public class PacienteRepository implements PanacheRepositoryBase<Paciente,Long>{

}
