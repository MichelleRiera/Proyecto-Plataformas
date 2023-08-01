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
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import modelo.Ticket;
import negocio.ticketN;

@Path("Tickets")
public class ticketS {
	@Inject
	private ticketN ticketNegocio;
	
	 @POST
	    @Path("registrar")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response registrarTicket(Ticket ticket) {
	        try {
	            ticketNegocio.registrarTicket(ticket, ticket.getUsuario().getUsuarioId(), ticket.getLugar().getLugarId(),
	                    ticket.getVehiculo().getVehiculoId(), ticket.getTarifa().getTarifaId());
	            return Response.status(Response.Status.CREATED).entity(ticket).build();
	        } catch (Exception e) {
	            return Response.status(Response.Status.BAD_REQUEST).entity("Error: " + e.getMessage()).build();
	        }
	    }

		 
		    @DELETE
		    @Path("eliminar/{ticketId}")
		    @Produces(MediaType.APPLICATION_JSON)
		    public Response eliminarTicket(@PathParam("ticketId") int ticketId) {
	        try {
	            ticketNegocio.eliminarTicketPorId(ticketId);
	            return Response.status(Response.Status.OK).entity("Ticket eliminado correctamente.").build();
	        } catch (Exception e) {
	            return Response.status(Response.Status.BAD_REQUEST).entity("Error al eliminar el ticket: " + e.getMessage()).build();
	        }
	    }


			 @PUT
			 @Path("actualizar")
			 @Consumes(MediaType.APPLICATION_JSON)
			 @Produces(MediaType.APPLICATION_JSON)
			 public Response actualizarTicket(Ticket ticketActualizado) {
			     try {
			         int ticketId = ticketActualizado.getTicketid();
			         ticketNegocio.actualizarTicketPorId(ticketId, ticketActualizado);
			         return Response.status(Response.Status.OK).entity("Ticket con ID " + ticketId + " actualizado correctamente.").build();
			     } catch (Exception e) {
			         return Response.status(Response.Status.BAD_REQUEST).entity("Error al actualizar el ticket: " + e.getMessage()).build();
			     }
			 }


	     @GET
	     @Path("listarTickets")
	     @Produces(MediaType.APPLICATION_JSON)
	     public Response listarTodosLosTickets() {
	         List<Ticket> listado = ticketNegocio.listarTickets();
	         return Response.status(Response.Status.OK).entity(listado).build();
	     }
	 }



