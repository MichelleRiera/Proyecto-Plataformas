package DAO;

import java.io.Serializable;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import modelo.Usuario;

@Stateless
public class usuarioDao implements Serializable{
	
	/**
	 * 
	 */
	 private static final long serialVersionUID = 1L;

	    @PersistenceContext
	    private EntityManager em;
           //insertar usuario
	    public void insert(Usuario usuario) {
	        em.persist(usuario);
	    }
	    //actualizar usuario
	    public void update(Usuario usuario) {
	        em.merge(usuario);
	    }
	    //leer por usuarioId
	    public Usuario read(Integer UsuarioId) {
	        Usuario p = em.find(Usuario.class, UsuarioId);
	        return p;
	    }
	    //eliminar usuario
	    public void delete(Integer UsuarioId) {
	        Usuario p = em.find(Usuario.class, UsuarioId);
	        em.remove(p);
	    }
	    	//listar usuarios
	    public List<Usuario> getAll() {
	        String jpql = "SELECT p FROM Usuario p";
	        Query q = em.createQuery(jpql);
	        return q.getResultList();
	    }
	    //buscar nombre de usuario si es que no se ha registrado antes
	    public Usuario getByNombreUsuario(String nombreUsuario) {
	        String jpql = "SELECT u FROM Usuario u WHERE u.usuario = :nombreUsuario";
	        Query query = em.createQuery(jpql);
	        query.setParameter("nombreUsuario", nombreUsuario);

	        List<Usuario> resultados = query.getResultList();
	        if (!resultados.isEmpty()) {
	            return resultados.get(0);
	        } else {
	            return null;
	        }
	    }

}    