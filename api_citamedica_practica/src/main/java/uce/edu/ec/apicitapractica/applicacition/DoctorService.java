package uce.edu.ec.apicitapractica.applicacition;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.ec.apicitapractica.applicacition.representation.DoctorRepresentation;
import uce.edu.ec.apicitapractica.domain.Doctor;
import uce.edu.ec.apicitapractica.infrastructure.DoctorRepository;

@ApplicationScoped
public class DoctorService {
    @Inject
    private DoctorRepository doctorRepository;
    @Transactional
    public DoctorRepresentation guardar(DoctorRepresentation doctorRepresentation){
        Doctor doctor = this.mapperToDoctor(doctorRepresentation);
        this.doctorRepository.persist(doctor);
        return this.mapperToDR(doctor);
    }

    public List<DoctorRepresentation> listar(){
        List<DoctorRepresentation> listaDoc = new ArrayList<>();
        for(Doctor doctor : this.doctorRepository.listAll()){
            listaDoc.add(this.mapperToDR(doctor));
        }
        return listaDoc;
    }

    private DoctorRepresentation mapperToDR(Doctor doctor){
        DoctorRepresentation docRepre = new DoctorRepresentation();
        docRepre.cedula = doctor.cedula;
        docRepre.nombre = doctor.nombre;
        docRepre.apellido = doctor.apellido;
        docRepre.genero = doctor.genero;
        return docRepre;
    }

    private Doctor mapperToDoctor(DoctorRepresentation doctorRepresentation){
        Doctor doctor = new Doctor();
        doctor.cedula=doctorRepresentation.cedula;
        doctor.nombre = doctorRepresentation.nombre;
        doctor.apellido = doctorRepresentation.apellido;
        doctor.genero = doctorRepresentation.genero;
        return doctor;
    }
    
}
