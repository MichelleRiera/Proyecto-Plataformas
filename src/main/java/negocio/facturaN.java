package negocio;

import java.util.List;

import DAO.facturaDao;
import DAO.personaDao;
import DAO.ticketDao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import modelo.Factura;
import modelo.Persona;
import modelo.Ticket;

@Stateless
public class facturaN {

	@Inject
    private facturaDao daoFactura;
	
	@Inject
    private ticketDao daoTicket;
	
	@Inject
    private personaDao daoPersona;
	
	public void registrarFactura(Factura factura, int personaId, int ticketId) throws Exception {
        Persona persona = daoPersona.read(personaId);
        if (persona == null) {
            throw new Exception("La persona con el id " + personaId + " no existe.");
        }
        factura.setPersona(persona);

        Ticket ticket = daoTicket.read(ticketId);
        if (ticket == null) {
            throw new Exception("El Ticket con el id " + ticketId + " no existe.");
        }
        factura.setTicket(ticket);

        daoFactura.insert(factura);
    }
	
	public void eliminarFacturaPorId(int facturaId) throws Exception {
        Factura factura = daoFactura.read(facturaId);
        if (factura == null) {
            throw new Exception("La factura con el ID " + factura + " no existe.");
        }
        daoFactura.delete(facturaId);
    }
	
	public void actualizarFacturaPorId(int facturaId, Factura facturaActualizada) throws Exception {
        Factura facturaExistente = daoFactura.read(facturaId);
        if (facturaExistente == null) {
            throw new Exception("La Factura con el ID " + facturaId + " no existe.");
        }

        // Actualizar los campos del ticketExistente con los nuevos valores del ticketActualizado
        facturaExistente.setFecha(facturaActualizada.getFecha());
        facturaExistente.setIva(facturaActualizada.getIva());
        facturaExistente.setSubtotal(facturaActualizada.getSubtotal());
        facturaExistente.setTotal(facturaActualizada.getTotal());
        facturaExistente.setPersona(facturaActualizada.getPersona());
        facturaExistente.setTicket(facturaActualizada.getTicket());

        daoFactura.update(facturaExistente);
    }
	
	public List<Factura> listarFacturas() {
        return daoFactura.getAll();
    }
}
