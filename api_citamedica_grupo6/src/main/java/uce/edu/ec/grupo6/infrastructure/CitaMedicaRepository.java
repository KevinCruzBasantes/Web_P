package uce.edu.ec.grupo6.infrastructure;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.ec.grupo6.domain.CitaMedica;

@ApplicationScoped
public class CitaMedicaRepository implements PanacheRepositoryBase<CitaMedica,Long>{

}
