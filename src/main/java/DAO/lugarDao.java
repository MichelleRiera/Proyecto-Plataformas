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
	//buscar numero de lugar y numero de piso
	public List<Lugar> getLugaresByNumeroLugarAndPisoAndTipoVehiculo(int numeroLugar, int piso) {
	    String jpql = "SELECT p FROM Lugar p WHERE p.numeroLugar = :numeroLugar AND p.piso = :piso";
	    Query q = em.createQuery(jpql);
	    q.setParameter("numeroLugar", numeroLugar);
	    q.setParameter("piso", piso);
	    return q.getResultList();
	}




}
