package uce.edu.ec.grupo6.interfaces;

import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.ec.grupo6.application.PacienteService;
import uce.edu.ec.grupo6.application.representation.PacienteRepresentation;

@Path("/pacientes")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PacienteResource {
    @Inject
    private PacienteService pacienteService;

    @POST
    @Path("")
    @RolesAllowed("admin")
    public Response crearPaciente(PacienteRepresentation pacienteRepre) {
        // Capturamos el DTO que ahora sí trae el ID generado
        PacienteRepresentation creado = this.pacienteService.guardar(pacienteRepre);

        // Devolvemos 'creado' en lugar de 'pacienteRepre'
        return Response.status(Response.Status.CREATED).entity(creado).build();
    }

    @GET
    @Path("")
    @RolesAllowed("admin")
    public List<PacienteRepresentation> obtenerPacientes() {
        List<PacienteRepresentation> listaPacienteRepresentation = this.pacienteService.listarTodos();
        return listaPacienteRepresentation;
    }

    @PUT
    @Path("/{id}")
    public Response modificarPaciente(@PathParam("id") Long id, PacienteRepresentation pacienteRepre) {
        PacienteRepresentation actualizarPaciente = this.pacienteService.actualizar(id, pacienteRepre);
        if (actualizarPaciente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(actualizarPaciente).build();
    }
    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response borrarPaciente(@PathParam("id") Long id) {
        boolean eliminado = this.pacienteService.eleminar(id);
        if (eliminado) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }
}
