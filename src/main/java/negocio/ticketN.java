package negocio;
import java.util.List;

import DAO.lugarDao;
import DAO.tarifaDao;
import DAO.ticketDao;
import DAO.usuarioDao;
import DAO.vehiculoDao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import modelo.Lugar;
import modelo.Tarifa;
import modelo.Ticket;
import modelo.Usuario;
import modelo.Vehiculo;

@Stateless
public class ticketN {
	 @Inject
	    private ticketDao daoTicket;

	    @Inject
	    private usuarioDao daoUsuario;

	    @Inject
	    private lugarDao daoLugar;

	    @Inject
	    private vehiculoDao daoVehiculo;

	    @Inject
	    private tarifaDao daoTarifa;

	    public void registrarTicket(Ticket ticket, int usuarioId, int lugarId, int vehiculoId, int tarifaId) throws Exception {
	        Usuario usuario = daoUsuario.read(usuarioId);
	        if (usuario == null) {
	            throw new Exception("El usuario con el id " + usuarioId + " no existe.");
	        }
	        ticket.setUsuario(usuario);

	        Lugar lugar = daoLugar.read(lugarId);
	        if (lugar == null) {
	            throw new Exception("El lugar con el id " + lugarId + " no existe.");
	        }
	        ticket.setLugar(lugar);

	        Vehiculo vehiculo = daoVehiculo.read(vehiculoId);
	        if (vehiculo == null) {
	            throw new Exception("El vehiculo con el id " + vehiculoId + " no existe.");
	        }
	        ticket.setVehiculo(vehiculo);

	        // Validar si el vehículo tiene un ticket en estado "P" (parqueado)
	        Ticket ticketActivo = daoTicket.getTicketActivoPorVehiculo(vehiculoId);
	        if (ticketActivo != null && ticketActivo.getEstado().equalsIgnoreCase("P")) {
	            throw new Exception("El vehículo ya tiene un ticket activo en estado 'P'.");
	        }

	        Tarifa tarifa = daoTarifa.read(tarifaId);
	        if (tarifa == null) {
	            throw new Exception("La tarifa con el id " + tarifaId + " no existe.");
	        }
	        ticket.setTarifa(tarifa);

	        daoTicket.insert(ticket);
	    }

	    
	    public void eliminarTicketPorId(int ticketId) throws Exception {
	        Ticket ticket = daoTicket.read(ticketId);
	        if (ticket == null) {
	            throw new Exception("El ticket con el ID " + ticketId + " no existe.");
	        }
	        daoTicket.delete(ticketId);
	    }
	    public void actualizarTicketPorId(int ticketId, Ticket ticketActualizado) throws Exception {
	        Ticket ticketExistente = daoTicket.read(ticketId);
	        if (ticketExistente == null) {
	            throw new Exception("El ticket con el ID " + ticketId + " no existe.");
	        }

	        // Actualizar los campos del ticketExistente con los nuevos valores del ticketActualizado
	        ticketExistente.setFecha(ticketActualizado.getFecha());
	        ticketExistente.setHora_entrada(ticketActualizado.getHora_entrada());
	        ticketExistente.setHora_salida(ticketActualizado.getHora_salida());
	        ticketExistente.setUsuario(ticketActualizado.getUsuario());
	        ticketExistente.setLugar(ticketActualizado.getLugar());
	        ticketExistente.setVehiculo(ticketActualizado.getVehiculo());
	        ticketExistente.setTarifa(ticketActualizado.getTarifa());
	        ticketExistente.setEstado(ticketActualizado.getEstado());

	        daoTicket.update(ticketExistente);
	    }
	    
	    public List<Ticket> listarTickets() {
	        return daoTicket.getAll();
	    }



}
