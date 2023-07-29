package DAO;

import java.io.Serializable;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import modelo.Lugar;

@Stateless
public class lugarDao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;
	
	public void insert(Lugar lugar) {
		em.persist(lugar);
	}
	
	public void update(Lugar lugar) {
		em.merge(lugar);
	}
	
	public Lugar read(int lugarId) {
		Lugar p = em.find(Lugar.class,lugarId);
		return p;
	}
	
	public void delete(int lugarId) {
		Lugar p = em.find(Lugar.class, lugarId);
		em.remove(p);
	}
	
	public List<Lugar> getAll(){
		String jpql = "SELECT p FROM Lugar p";
		Query q = em.createQuery(jpql);
		return q.getResultList();
	}



}
