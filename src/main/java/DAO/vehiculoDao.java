package DAO;

import java.io.Serializable;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import modelo.Vehiculo;


@Stateless
public class vehiculoDao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager em;
	//insertar vehiculo
	public void insert(Vehiculo vehiculo) {
		em.persist(vehiculo);
	}
	//actualizar vehiculo
	public void update(Vehiculo vehiculo) {
		em.merge(vehiculo);
	}
	//leer por vehiculoId
	public Vehiculo read(int vehiculoId) {
		Vehiculo p = em.find(Vehiculo.class, vehiculoId);
		return p;
	}
	//delete por vehiculoid
	public void delete(int vehiculoId) {
		Vehiculo p = em.find(Vehiculo.class, vehiculoId);
		em.remove(p);
	}
	//listar vehiculos
	public List<Vehiculo> getAll(){
		String jpql = "SELECT p FROM Vehiculo p";
		Query q = em.createQuery(jpql);
		return q.getResultList();
	}
	//buscar por placa de vehiculo si es que no se registrado antes
	public Vehiculo getByPlaca(String placa) {
	    String jpql = "SELECT v FROM Vehiculo v WHERE v.placa = :placa";
	    Query q = em.createQuery(jpql);
	    q.setParameter("placa", placa);
	    try {
	        return (Vehiculo) q.getSingleResult();
	    } catch (NoResultException e) {
	        return null;
	    }
	}


}
