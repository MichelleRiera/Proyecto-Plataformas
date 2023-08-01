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
import modelo.Lugar;
import negocio.lugarN;

@Path("Lugares")
public class lugarS {
    @Inject
    private lugarN lugarNegocio;

    @POST
    @Path("registrar")
    @Consumes("application/json")
    @Produces("application/json")
    public Response guardarLugar(Lugar lugar) {
        try {
            lugarNegocio.guardarLugar(lugar); // Pasar el lugar y el pisoId al negocio
            return Response.status(Response.Status.OK).entity(lugar).build();
        } catch (Exception e) {
            e.printStackTrace();
            Error error = new Error();
            error.setCodigo(99);
            error.setMensaje("Error al guardar el lugar: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        }
    }



    @GET
    @Path("listarLugares")
    @Produces("application/json")
    public Response listarLugares() {
        List<Lugar> listado = lugarNegocio.listarLugares();
        return Response.status(Response.Status.OK).entity(listado).build();
    }

    @PUT
    @Path("actualizar")
    @Consumes("application/json")
    @Produces("application/json")
    public Response actualizarLugar(Lugar lugar) {
        try {
            lugarNegocio.actualizarLugarPorId(lugar.getLugarId(), lugar.getNumeroLugar(), lugar.getEstado());
            return Response.status(Response.Status.OK).entity(lugar).build();
        } catch (Exception e) {
            e.printStackTrace();
            Error respuesta = new Error();
            respuesta.setCodigo(99);
            respuesta.setMensaje("Error al actualizar el lugar por ID: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(respuesta).build();
        }
    }

    @DELETE
    @Path("eliminar/{lugarId}")
    @Produces("application/json")
    public Response eliminarLugar(@PathParam("lugarId") int lugarId) {
        try {
            lugarNegocio.eliminarLugarPorId(lugarId);
            Error respuesta = new Error();
            respuesta.setCodigo(1);
            respuesta.setMensaje("Lugar eliminado correctamente.");
            return Response.status(Response.Status.OK).entity(respuesta).build();
        } catch (Exception e) {
            e.printStackTrace();
            Error respuesta = new Error();
            respuesta.setCodigo(99);
            respuesta.setMensaje("Error al eliminar el lugar por ID: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(respuesta).build();
        }
    }

}
