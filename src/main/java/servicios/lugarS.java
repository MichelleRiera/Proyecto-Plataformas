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
            // Validar el lugar antes de guardar
            if (lugar == null) {
                throw new IllegalArgumentException("El lugar no puede ser nulo.");
            }
            if (lugar.getNumeroLugar() <= 0) {
                throw new IllegalArgumentException("El número de lugar debe ser mayor que 0.");
            }
            if (lugar.getEstado() == null || lugar.getEstado().isEmpty() || (!lugar.getEstado().equals("A") && !lugar.getEstado().equals("I"))) {
                throw new IllegalArgumentException("El estado del lugar debe ser 'A' (activo) o 'I' (inactivo).");
            }
            if (lugar.getPiso() <= 0) {
                throw new IllegalArgumentException("El número de piso debe ser mayor que 0.");
            }
            if (lugar.getTipoVehiculo() == null || lugar.getTipoVehiculo().isEmpty()) {
                throw new IllegalArgumentException("El tipo de vehículo no puede estar vacío.");
            }

            lugarNegocio.guardarLugar(lugar); // Pasar el lugar al negocio para validar y guardar
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
