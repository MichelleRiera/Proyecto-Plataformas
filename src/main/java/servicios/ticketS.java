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
import modelo.Ticket;
import negocio.ticketN;

@Path("Tickets")
public class ticketS {
	@Inject
	private ticketN ticketNegocio;
	
	@GET
    @Path("listarTickets")
    @Produces("application/json")
    public Response listarTickets() {
        List<Ticket> listado = ticketNegocio.listarTickets();
        return Response.status(Response.Status.OK).entity(listado).build();
    }

    @POST
    @Path("registrar")
    @Consumes("application/json")
    @Produces("application/json")
    public Response guardarTarifa(Ticket ticket) {
        try {
        	ticketNegocio.agregarTicket(ticket);
            return Response.status(Response.Status.OK).entity(ticket).build();
        } catch (Exception e) {
            e.printStackTrace();
            Error error = new Error();
            error.setCodigo(99);
            error.setMensaje("Error al guardar el ticket: " + e.getMessage());
            return Response.status(Response.Status.OK).entity(error).build();
        }
    }

    @PUT
    @Path("actualizar")
    @Consumes("application/json")
    @Produces("application/json")
    public Response actualizarTarifaPorId(Ticket ticket) {
        try {
        	ticketNegocio.actualizarTicket(ticket);
            return Response.status(Response.Status.OK).entity(ticket).build();
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
    public Response eliminarTarifaPorId(Ticket ticket) {
        try {
        	ticketNegocio.eliminarTicket(ticket.getTicketid());
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
