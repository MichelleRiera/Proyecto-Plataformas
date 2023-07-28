package DAO;

import java.io.Serializable;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import modelo.Persona;

@Stateless
public class personaDao implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;
	
	public void insert(Persona persona) {
		em.persist(persona);
	}
	
	public void update(Persona persona) {
		em.merge(persona);
	}
	
	public Persona read(Integer personaid) {
	    Persona p = em.find(Persona.class, personaid);
	    return p;
	}

	
	/*public void delete(String cedula) {
	    Persona p = em.find(Persona.class, cedula);
	    em.remove(p);
	}*/
	public void delete(String cedula) {
	    Query query = em.createQuery("DELETE FROM Persona p WHERE p.cedula = :cedula");
	    query.setParameter("cedula", cedula);
	    int rowsAffected = query.executeUpdate();
	    
	    if (rowsAffected == 0) {
	        throw new EntityNotFoundException("La persona con cédula " + cedula + " no existe.");
	    }
	}



	
	public List<Persona> getAll(){
		String jpql = "SELECT p FROM Persona p";
		Query q = em.createQuery(jpql);
		return q.getResultList();
	}
	public Persona getByCedula(String cedula) {
	    String jpql = "SELECT p FROM Persona p WHERE p.cedula = :cedula";
	    Query query = em.createQuery(jpql);
	    query.setParameter("cedula", cedula);
	    return (Persona) query.getSingleResult();
	}

     
}