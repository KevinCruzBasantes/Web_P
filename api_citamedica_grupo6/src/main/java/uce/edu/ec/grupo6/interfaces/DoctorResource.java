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
import uce.edu.ec.grupo6.application.DoctorService;
import uce.edu.ec.grupo6.application.representation.DoctorRepresentation;

@Path("/doctores")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DoctorResource {
    @Inject
    private DoctorService doctorService;

    @POST
    @Path("")
    @RolesAllowed("admin")
    public Response crearDoctor(DoctorRepresentation docRepre) {
        // Llamamos al servicio modificado
        DoctorRepresentation nuevoDoctor = this.doctorService.guardar(docRepre);

        // Devolvemos el objeto que YA TIENE el ID generado
        return Response.status(Response.Status.CREATED).entity(nuevoDoctor).build();
    }

    @GET
    @Path("")
    @RolesAllowed("admin")
    public List<DoctorRepresentation> obtenerDoctores() {
        List<DoctorRepresentation> docRepre = this.doctorService.listarTodos();
        return docRepre;
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response modificarDoctor(@PathParam("id") Long id, DoctorRepresentation docRepre) {
        DoctorRepresentation actualizado = this.doctorService.actualizar(id, docRepre);

        if (actualizado == null) {
            return Response.status(Response.Status.NOT_FOUND).build(); // 404 si no existe
        }

        return Response.ok(actualizado).build(); // 200 OK
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response borrarDoctor(@PathParam("id") Long id) {
        boolean eliminado = this.doctorService.eliminar(id);

        if (eliminado) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
