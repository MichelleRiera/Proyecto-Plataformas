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
import modelo.Vehiculo;
import negocio.vehiculoN;


@Path("Vehiculos")
public class vehiculoS {
	
	   @Inject
	    private vehiculoN vehiculoNegocio;


	    @POST
	    @Path("registrar")
	    @Consumes("application/json")
	    @Produces("application/json")
	    public Response guardarVehiculo(Vehiculo vehiculo) {
	        try {
	            vehiculoNegocio.guardarVehiculo(vehiculo);
	            return Response.status(Response.Status.OK).entity(vehiculo).build();
	        } catch (Exception e) {
	            e.printStackTrace();
	            Error error = new Error();
	            error.setCodigo(99);
	            error.setMensaje("Error al guardar: " + e.getMessage());
	            return Response.status(Response.Status.OK).entity(error).build();
	        }
	    }

	    @GET
	    @Path("all")
	    @Produces("application/json")
	    public Response getVehiculos() {
	        List<Vehiculo> listado = vehiculoNegocio.listarVehiculos();
	        return Response.status(Response.Status.OK).entity(listado).build();
	    }

	    @DELETE
	    @Path("eliminar")
	    @Consumes("application/json")
	    @Produces("application/json")
	    public Response eliminarVehiculo(Vehiculo vehiculo) {
	        try {
	            vehiculoNegocio.eliminarVehiculoPorPlaca(vehiculo.getPlaca());
	            Error respuesta = new Error();
	            respuesta.setCodigo(1);
	            respuesta.setMensaje("Vehiculo eliminado correctamente.");
	            return Response.status(Response.Status.OK).entity(respuesta).build();
	        } catch (Exception e) {
	            e.printStackTrace();
	            Error respuesta = new Error();
	            respuesta.setCodigo(99);
	            respuesta.setMensaje("Error al eliminar: " + e.getMessage());
	            return Response.status(Response.Status.BAD_REQUEST).entity(respuesta).build();
	        }
	    }

	    @PUT
	    @Path("actualizar")
	    @Consumes("application/json")
	    @Produces("application/json")
	    public Response actualizarVehiculo(Vehiculo vehiculo) {
	        try {
	            vehiculoNegocio.actualizarVehiculo(vehiculo.getPlaca(), vehiculo);
	            return Response.status(Response.Status.OK).entity(vehiculo).build();
	        } catch (Exception e) {
	            e.printStackTrace();
	            Error respuesta = new Error();
	            respuesta.setCodigo(99);
	            respuesta.setMensaje("Error al actualizar: " + e.getMessage());
	            return Response.status(Response.Status.BAD_REQUEST).entity(respuesta).build();
	        }
	    }


	}
