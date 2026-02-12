package uce.edu.ec.apicitapractica.interfaces;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.ec.apicitapractica.applicacition.CitaService;
import uce.edu.ec.apicitapractica.applicacition.representation.CitaRepresentation;

@ApplicationScoped
@Path("/citas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CitaResource {
    @Inject
    private CitaService citaService;

    @POST
    @Path("")
    @RolesAllowed("admin")
    public Response registrarCita(CitaRepresentation citaRepre) {
        CitaRepresentation citaCreada = this.citaService.guardar(citaRepre);
        if (citaCreada == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("no existe doctor o paciente").build();
        }
        return Response.status(Response.Status.CREATED).entity(citaCreada).build();
    }

    @GET
    @RolesAllowed("admin")
    public Response obtenerTodas() {
        return Response.ok(this.citaService.listarTodas()).build();
    }

}
