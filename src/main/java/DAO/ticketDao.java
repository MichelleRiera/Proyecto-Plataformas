package DAO;

import java.io.Serializable;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import modelo.Ticket;
@Stateless
public class ticketDao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;
	
	public void insert(Ticket ticket) {
		em.persist(ticket);
	}
	
	public void update(Ticket ticket) {
		em.merge(ticket);
	}
	
	public Ticket read(int ticketid) {
		Ticket p = em.find(Ticket.class, ticketid);
		return p;
	}
	
	public void delete(int ticketid) {
		Ticket p = em.find(Ticket.class, ticketid);
		em.remove(p);
	}
	
	public List<Ticket> getAll(){
		String jpql = "SELECT p FROM Ticket p";
		Query q = em.createQuery(jpql);
		return q.getResultList();
	}
	
	//buscar estado de vehiculo si esta P
	public Ticket getTicketActivoPorVehiculo(int vehiculoId) {
	    String jpql = "SELECT t FROM Ticket t WHERE t.vehiculo.vehiculoId = :vehiculoId AND t.estado = 'P'";
	    Query query = em.createQuery(jpql);
	    query.setParameter("vehiculoId", vehiculoId);

	    List<Ticket> resultados = query.getResultList();
	    if (!resultados.isEmpty()) {
	        return resultados.get(0);
	    } else {
	        return null;
	    }
	}


}
