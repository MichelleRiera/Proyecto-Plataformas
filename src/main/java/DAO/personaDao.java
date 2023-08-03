package DAO;

import java.io.Serializable;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import modelo.Persona;

@Stateless
public class personaDao implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;
	
	//insertar personas
	public void insert(Persona persona) {
		em.persist(persona);
	}
	//actualizar persona
	public void update(Persona persona) {
		em.merge(persona);
	}
	//leer por personaId
	public Persona read(Integer personaid) {
	    Persona p = em.find(Persona.class, personaid);
	    return p;
	}

	//eliminar por cedula
	public void delete(String cedula) {
	    Query query = em.createQuery("DELETE FROM Persona p WHERE p.cedula = :cedula");
	    query.setParameter("cedula", cedula);
	    int rowsAffected = query.executeUpdate();
	    
	    if (rowsAffected == 0) {
	        throw new EntityNotFoundException("La persona con cédula " + cedula + " no existe.");
	    }
	}
       //listar personas
	public List<Persona> getAll(){
		String jpql = "SELECT p FROM Persona p";
		Query q = em.createQuery(jpql);
		return q.getResultList();
	}
	   //buscar si la cedula ya se ha registrado anteriormente
	public Persona getByCedula(String cedula) {
	    String jpql = "SELECT p FROM Persona p WHERE p.cedula = :cedula";
	    TypedQuery<Persona> query = em.createQuery(jpql, Persona.class);
	    query.setParameter("cedula", cedula);
	    
	    List<Persona> results = query.getResultList();
	    if (results.isEmpty()) {
	        return null; // Devolver null cuando no se encuentra ninguna persona
	    } else {
	        return results.get(0); // Devolver la primera persona encontrada
	    }
	}


     
}