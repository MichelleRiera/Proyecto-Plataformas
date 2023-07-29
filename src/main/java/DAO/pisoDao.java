package DAO;

import java.io.Serializable;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import modelo.Piso;

@Stateless
public class pisoDao implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    public void insert(Piso piso) {
        em.persist(piso);
    }

    public void update(Piso piso) {
        em.merge(piso);
    }

    public Piso read(int pisoId) {
        Piso p = em.find(Piso.class, pisoId);
        return p;
    }

    public void delete(int pisoId) {
        Piso p = em.find(Piso.class, pisoId);
        em.remove(p);
    }

    public List<Piso> getAll() {
        String jpql = "SELECT p FROM Piso p";
        Query q = em.createQuery(jpql);
        return q.getResultList();
    }

    // Método para obtener un piso por su número de piso
    public Piso getPisoPorNumeroPiso(int numeroPiso) {
        String jpql = "SELECT p FROM Piso p WHERE p.numeroPiso = :numeroPiso";
        Query q = em.createQuery(jpql);
        q.setParameter("numeroPiso", numeroPiso);

        List<Piso> resultados = q.getResultList();
        if (!resultados.isEmpty()) {
            return resultados.get(0);
        } else {
            return null;
        }
    }

}
