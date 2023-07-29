package servicios;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import modelo.Tarifa;
import negocio.tarifaN;

@Path("Tarifas")
public class tarifaS {
	@Inject
	private tarifaN tarifaNegocio;

	    @GET
	    @Path("listarTarifas")
	    @Produces("application/json")
	    public Response listarTarifas() {
	        List<Tarifa> listado = tarifaNegocio.listarTarifas();
	        return Response.status(Response.Status.OK).entity(listado).build();
	    }

	    @POST
	    @Path("registrar")
	    @Consumes("application/json")
	    @Produces("application/json")
	    public Response guardarTarifa(Tarifa tarifa) {
	        try {
	            tarifaNegocio.guardarTarifa(tarifa);
	            return Response.status(Response.Status.OK).entity(tarifa).build();
	        } catch (Exception e) {
	            e.printStackTrace();
	            Error error = new Error();
	            error.setCodigo(99);
	            error.setMensaje("Error al guardar la tarifa: " + e.getMessage());
	            return Response.status(Response.Status.OK).entity(error).build();
	        }
	    }

	    @PUT
	    @Path("actualizar")
	    @Consumes("application/json")
	    @Produces("application/json")
	    public Response actualizarTarifaPorId(Tarifa tarifa) {
	        try {
	            tarifaNegocio.actualizarTarifaPorId(tarifa.getTarifaId(), tarifa.getCostoTarifa(), tarifa.getTipoVehiculo());
	            return Response.status(Response.Status.OK).entity(tarifa).build();
	        } catch (Exception e) {
	            e.printStackTrace();
	            Error respuesta = new Error();
	            respuesta.setCodigo(99);
	            respuesta.setMensaje("Error al actualizar la tarifa por ID: " + e.getMessage());
	            return Response.status(Response.Status.BAD_REQUEST).entity(respuesta).build();
	        }
	    }

	    @DELETE
	    @Path("eliminar")
	    @Consumes("application/json")
	    @Produces("application/json")
	    public Response eliminarTarifaPorId(Tarifa tarifa) {
	        try {
	            tarifaNegocio.eliminarTarifaPorId(tarifa.getTarifaId());
	            Error respuesta = new Error();
	            respuesta.setCodigo(1);
	            respuesta.setMensaje("Tarifa eliminada correctamente.");
	            return Response.status(Response.Status.OK).entity(respuesta).build();
	        } catch (Exception e) {
	            e.printStackTrace();
	            Error respuesta = new Error();
	            respuesta.setCodigo(99);
	            respuesta.setMensaje("Error al eliminar la tarifa por ID: " + e.getMessage());
	            return Response.status(Response.Status.BAD_REQUEST).entity(respuesta).build();
	        }
	    }

	

}
