package uce.edu.ec.apicitapractica.infrastructure;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.ec.apicitapractica.domain.Cita;

@ApplicationScoped
public class CitaRepository implements PanacheRepositoryBase<Cita,Long>{
    
}
