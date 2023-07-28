package servicios;

import java.util.ArrayList;
import java.util.List;
import DAO.personaDao;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import modelo.Persona;
import negocio.personaN;

@Path("Personas")
public class personaS {
	
	@Inject 
	private personaDao daoPersona;
	
	@Inject
	private personaN clientes;
	
	@GET
    @Path("listarPersonas")
    @Produces("application/json")
    public List<Persona> listarPersonas() {
        return clientes.listarPersonas();
    }
    
   

    @POST
    @Path("registrar")
    @Produces("application/json")
    public Response guardarCliente(Persona persona) {
        try {
            clientes.guardarClientes(persona);
            return Response.status(Response.Status.OK).entity(persona).build();
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
    public Response getClientes() {
        List<Persona> listado = clientes.listarPersonas();
        
        return Response.status(Response.Status.OK).entity(listado).build();
    }
    
    @DELETE
    @Path("eliminar")
    @Consumes("application/json")
    @Produces("application/json")
    public Response eliminarPersona(Persona persona) {
        try {
            clientes.delete(persona.getCedula());
            Error respuesta = new Error();
            respuesta.setCodigo(1);
            respuesta.setMensaje("Persona eliminada correctamente.");
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
    public Response actualizarClientePorCedula(Persona persona) {
        try {
            clientes.actualizarClientePorCedula(persona.getCedula(), persona.getNombre(), 
            persona.getDireccion(), persona.getTelefono());

            return Response.status(Response.Status.OK).entity(persona).build();
        } catch (Exception e) {
            e.printStackTrace();
            Error respuesta = new Error();
            respuesta.setCodigo(99);
            respuesta.setMensaje("Error al actualizar: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(respuesta).build();
        }
    }

}
