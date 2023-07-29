package servicios;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import modelo.Piso;
import negocio.pisoN;

@Path("Pisos")
public class pisoS {

    @Inject
    private pisoN pisoNegocio;

    @GET
    @Path("listarPisos")
    @Produces("application/json")
    public List<Piso> listarPisos() {
        return pisoNegocio.listarPisos();
    }

    @POST
    @Path("registrar")
    @Consumes("application/json")
    @Produces("application/json")
    public Response guardarPiso(Piso piso) {
        try {
            pisoNegocio.guardarPiso(piso);
            return Response.status(Response.Status.OK).entity(piso).build();
        } catch (Exception e) {
            e.printStackTrace();
            Error error = new Error();
            error.setCodigo(99);
            error.setMensaje("Error al guardar el piso: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        }
    }

    @PUT
    @Path("actualizar/{numeroPiso}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response actualizarPisoPorNumeroPiso(Piso piso, @PathParam("numeroPiso") int numeroPiso) {
        try {
            pisoNegocio.actualizarPisoPorNumeroPiso(numeroPiso, piso.getNumeroPiso(), piso.getEstado());
            return Response.status(Response.Status.OK).entity(piso).build();
        } catch (Exception e) {
            e.printStackTrace();
            Error respuesta = new Error();
            respuesta.setCodigo(99);
            respuesta.setMensaje("Error al actualizar el piso por número de piso: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(respuesta).build();
        }
    }

    @DELETE
    @Path("eliminar/{numeroPiso}")
    @Produces("application/json")
    public Response eliminarPisoPorNumeroPiso(@PathParam("numeroPiso") int numeroPiso) {
        try {
            pisoNegocio.eliminarPisoPorNumeroPiso(numeroPiso);
            Error respuesta = new Error();
            respuesta.setCodigo(1);
            respuesta.setMensaje("Piso eliminado correctamente.");
            return Response.status(Response.Status.OK).entity(respuesta).build();
        } catch (Exception e) {
            e.printStackTrace();
            Error respuesta = new Error();
            respuesta.setCodigo(99);
            respuesta.setMensaje("Error al eliminar el piso por número de piso: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(respuesta).build();
        }
    }
}
