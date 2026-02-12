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
import uce.edu.ec.apicitapractica.applicacition.PacienteService;
import uce.edu.ec.apicitapractica.applicacition.representation.PacienteRepresentation;

@ApplicationScoped
@Path("/pacientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PacienteResource {
    @Inject
    private PacienteService pacienteService;
    @POST
    @Path("")
    @RolesAllowed("admin")
    public Response registrarPaciente(PacienteRepresentation pacRepre){
        PacienteRepresentation nuevoPaciente = this.pacienteService.gurdar(pacRepre);
        return Response.status(Response.Status.CREATED).entity(nuevoPaciente).build();
    }
}
