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
import modelo.Factura;
import negocio.facturaN;


@Path("Facturas")
public class facturaS {
	@Inject
	private facturaN facturaNegocio;
	
	@POST
    @Path("registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarFactura(Factura factura) {
        try {
        	facturaNegocio.registrarFactura(factura, factura.getPersona().getPersonaid(), factura.getTicket().getTicketid());
            return Response.status(Response.Status.CREATED).entity(factura).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error: " + e.getMessage()).build();
        }
    }
	
	@DELETE
    @Path("eliminar/{facturaId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarFactura(@PathParam("facturaId") int facturaId) {
		try {
	        facturaNegocio.eliminarFacturaPorId(facturaId);
	        return Response.status(Response.Status.OK).entity("Factura eliminada correctamente.").build();
	    } catch (Exception e) {
	        return Response.status(Response.Status.BAD_REQUEST).entity("Error al eliminar Factura: " + e.getMessage()).build();
	    } 
	}
	
	@PUT
	 @Path("actualizar")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response actualizarFactura(Factura facturaActualizada) {
	     try {
	         int facturaId = facturaActualizada.getFacturaId();
	         facturaNegocio.actualizarFacturaPorId(facturaId, facturaActualizada);
	         return Response.status(Response.Status.OK).entity("Factura con ID " + facturaId + " actualizada correctamente.").build();
	     } catch (Exception e) {
	         return Response.status(Response.Status.BAD_REQUEST).entity("Error al actualizar la Factura: " + e.getMessage()).build();
	     }
	 }
	
	@GET
    @Path("listarFacturas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodosLasFacturas() {
        List<Factura> listado = facturaNegocio.listarFacturas();
        return Response.status(Response.Status.OK).entity(listado).build();
    }

}
