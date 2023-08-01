package negocio;
import java.util.List;

import DAO.ticketDao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import modelo.Ticket;

@Stateless
public class ticketN {
	@Inject
    private ticketDao daoTicket;

    public void agregarTicket(Ticket ticket) {
        daoTicket.insert(ticket);
    }

    public void actualizarTicket(Ticket ticket) {
        daoTicket.update(ticket);
    }

    public void eliminarTicket(int ticketid) {
        daoTicket.delete(ticketid);
    }

    public List<Ticket> listarTickets() {
        return daoTicket.getAll();
    }

    public Ticket buscarTicketPorId(int ticketid) {
        return daoTicket.read(ticketid);
    }
}
