package uce.edu.ec.apicitapractica.interfaces;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.ec.apicitapractica.applicacition.DoctorService;
import uce.edu.ec.apicitapractica.applicacition.representation.DoctorRepresentation;

@ApplicationScoped
@Path("/doctores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DoctorResource {
    @Inject
    private DoctorService doctorService;

    @POST
    @Path("")
    @RolesAllowed("admin")
    public Response crearDoctor(DoctorRepresentation doctorRepresentation){
        DoctorRepresentation nuevoDoctor = this.doctorService.guardar(doctorRepresentation);
        return Response.status(Response.Status.CREATED).entity(nuevoDoctor).build();
    }

}
