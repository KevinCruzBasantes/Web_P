package uce.edu.ec.grupo6.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.ec.grupo6.application.representation.DoctorRepresentation;
import uce.edu.ec.grupo6.domain.Doctor;
import uce.edu.ec.grupo6.infrastructure.DoctorRepository;

@ApplicationScoped
public class DoctorService {
    @Inject
    private DoctorRepository doctorRepository;

    @Transactional
    public DoctorRepresentation guardar(DoctorRepresentation doctorRepre) {
        // 1. Convertimos el DTO que viene de la web a una entidad de JPA
        Doctor doctor = this.mapperToDoctor(doctorRepre);

        // 2. Al persistir, Hibernate llena el campo 'id' de la instancia 'doctor'
        this.doctorRepository.persist(doctor);

        // 3. Mapeamos la entidad (ahora con ID) de vuelta al DTO para la respuesta
        return this.mapperToDR(doctor);
    }

    public List<DoctorRepresentation> listarTodos() {
        List<DoctorRepresentation> listaRepre = new ArrayList<>();
        for (Doctor doc : this.doctorRepository.listAll()) {
            listaRepre.add(this.mapperToDR(doc));
        }
        return listaRepre;
    }

    public DoctorRepresentation consultarPorId(Long id) {
        Doctor doc = this.doctorRepository.findById(id);
        return (doc != null) ? this.mapperToDR(doc) : null;
    }

    @Transactional
    public DoctorRepresentation actualizar(Long id, DoctorRepresentation doctorRepre) {
        // buscar la entidad original directamente
        Doctor doc = this.doctorRepository.findById(id);

        if (doc == null) {
            return null;
        }

        // actualizar el estado de la entidad gestionada por Hibernate
        doc.nombre = doctorRepre.nombre;
        doc.apellido = doctorRepre.apellido;
        doc.numerolicenciamedica = doctorRepre.numerolicenciamedica;
        doc.estado = doctorRepre.estado;
        doc.especialidad = doctorRepre.especialidad;

        return this.mapperToDR(doc);
    }

    @Transactional
    public boolean eliminar(Long id) {
        return this.doctorRepository.deleteById(id);
    }

    private DoctorRepresentation mapperToDR(Doctor doctor) {
        DoctorRepresentation docRepre = new DoctorRepresentation();
        docRepre.id = doctor.id;
        docRepre.nombre = doctor.nombre;
        docRepre.apellido = doctor.apellido;
        docRepre.numerolicenciamedica = doctor.numerolicenciamedica;
        docRepre.estado = doctor.estado;
        docRepre.especialidad = doctor.especialidad;
        return docRepre;
    }

    private Doctor mapperToDoctor(DoctorRepresentation docRepre) {
        Doctor doctor = new Doctor();

        doctor.nombre = docRepre.nombre;
        doctor.apellido = docRepre.apellido;
        doctor.numerolicenciamedica = docRepre.numerolicenciamedica;
        doctor.estado = docRepre.estado;
        doctor.especialidad = docRepre.especialidad;
        return doctor;
    }
}
