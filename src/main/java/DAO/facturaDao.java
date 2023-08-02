package DAO;

import java.io.Serializable;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import modelo.Factura;

@Stateless
public class facturaDao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;
	
	public void insert(Factura factura) {
		em.persist(factura);
	}
	
	public void update(Factura factura) {
		em.merge(factura);
	}
	
	public Factura read(int facturaId) {
		Factura p = em.find(Factura.class, facturaId);
		return p;
	}
	
	public void delete(int facturaId) {
		Factura p = em.find(Factura.class, facturaId);
		em.remove(p);
	}
	
	public List<Factura> getAll(){
		String jpql = "SELECT p FROM Factura p";
		Query q = em.createQuery(jpql);
		return q.getResultList();
	}

}
