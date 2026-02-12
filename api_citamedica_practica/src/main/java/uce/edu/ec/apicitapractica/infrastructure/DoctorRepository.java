package uce.edu.ec.apicitapractica.infrastructure;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.ec.apicitapractica.domain.Doctor;
@ApplicationScoped
public class DoctorRepository implements PanacheRepositoryBase<Doctor, Long> {

}
