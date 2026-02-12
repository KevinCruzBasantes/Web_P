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
import uce.edu.ec.grupo6.application.CitaMedicaService;
import uce.edu.ec.grupo6.application.representation.CitaMedicaRepresentation;

@ApplicationScoped
@Path("/citas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CitaMedicaResource {
    @Inject
    private CitaMedicaService citaMedicaService;

    @POST
    @Path("")
    @RolesAllowed("admin")
    public Response registrarCita(CitaMedicaRepresentation citaRepre) {
        // Capturamos la cita procesada por el servicio
        CitaMedicaRepresentation creada = this.citaMedicaService.guardar(citaRepre);

        if (creada == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Doctor o Paciente no existen").build();
        }

        // Devolvemos 'creada' (con ID), no 'citaRepre' (sin ID)
        return Response.status(Response.Status.CREATED).entity(creada).build();
    }

    @GET
    @Path("")
    @RolesAllowed("admin")

    public List<CitaMedicaRepresentation> mostrarCitas() {
        List<CitaMedicaRepresentation> listaCitas = this.citaMedicaService.listarTodos();
        return listaCitas;
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response modificarCita(@PathParam("id") Long id, CitaMedicaRepresentation citaRepre) {
        CitaMedicaRepresentation actualizada = this.citaMedicaService.actualizar(id, citaRepre);
        if (actualizada == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Cita, Doctor o Paciente no encontrado").build();
        }
        return Response.ok(actualizada).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response borrarCita(@PathParam("id") Long id) {
        boolean eliminado = this.citaMedicaService.eliminar(id);
        if (eliminado) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response obtenerCitaPorId(@PathParam("id") Long id) {
        CitaMedicaRepresentation cita = this.citaMedicaService.obtenerPorId(id);
        if (cita == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(cita).build();
    }
}
