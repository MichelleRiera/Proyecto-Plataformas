package DAO;

import java.io.Serializable;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import modelo.Tarifa;

@Stateless
public class tarifaDao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;
	//insertar tarifa
	public void insert(Tarifa tarifa) {
		em.persist(tarifa);
	}
	//actualizar tarifa
	public void update(Tarifa tarifa) {
		em.merge(tarifa);
	}
	//leer tarifa
	public Tarifa read(int tarifaId) {
		Tarifa p = em.find(Tarifa.class, tarifaId);
		return p;
	}
	//eliminar tarifa
	public void delete(int tarifaId) {
		Tarifa p = em.find(Tarifa.class, tarifaId);
		em.remove(p);
	}
	//listar tarifa
	public List<Tarifa> getAll(){
		String jpql = "SELECT p FROM Tarifa p";
		Query q = em.createQuery(jpql);
		return q.getResultList();
	}

}
