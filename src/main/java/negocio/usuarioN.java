package negocio;

import java.util.List;

import DAO.personaDao;
import DAO.usuarioDao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import modelo.Usuario;


@Stateless
public class usuarioN {

	    @Inject
	    private usuarioDao daoUsuario;

	    @Inject
	    private personaDao daoPersona;

	 // ...
       //agregar usuario pasando el parametro de la tabla persona
	    public void agregarUsuarioConIdPersona(Usuario usuario) throws Exception {
	        if (usuario.getPersona() == null || usuario.getPersona().getPersonaid() == 0) {
	            throw new Exception("El usuario debe tener una persona asociada con un id válido.");
	        }

	        int idPersona = usuario.getPersona().getPersonaid();
	        if (daoPersona.read(idPersona) == null) {
	            throw new Exception("La persona con el id " + idPersona + " no existe.");
	        }

	        // Verificar si el nombre de usuario ya existe en la base de datos
	        Usuario usuarioExistente = daoUsuario.getByNombreUsuario(usuario.getUsuario());
	        if (usuarioExistente != null) {
	            throw new Exception("El nombre de usuario ya está registrado. Por favor, elija otro nombre de usuario.");
	        }

	        daoUsuario.insert(usuario);
	    }
	    // ...
        //actualizar persona por nombre de usuario
	    public void actualizarUsuarioPorNombreUsuario(Usuario usuario) throws Exception {
	        if (usuario.getUsuario() == null || usuario.getUsuario().isEmpty()) {
	            throw new Exception("Debe proporcionar un nombre de usuario válido para actualizar.");
	        }

	        Usuario usuarioExistente = daoUsuario.getByNombreUsuario(usuario.getUsuario());

	        if (usuarioExistente == null) {
	            throw new Exception("No existe un usuario con el nombre de usuario proporcionado.");
	        }

	        // Actualizar los campos del usuarioExistente con los nuevos valores de usuario
	        usuarioExistente.setCargo(usuario.getCargo());
	        usuarioExistente.setContrasenia(usuario.getContrasenia());

	        // Guardar los cambios en la base de datos utilizando el método update del daoUsuario
	        daoUsuario.update(usuarioExistente);
	    }

	    	//eliminar persona por nombre de usuario
	    public void eliminarUsuarioPorNombreUsuario(String nombreUsuario) throws Exception {
	        if (nombreUsuario == null || nombreUsuario.isEmpty()) {
	            throw new Exception("Debe proporcionar un nombre de usuario válido para eliminar.");
	        }

	        Usuario usuarioExistente = daoUsuario.getByNombreUsuario(nombreUsuario);

	        if (usuarioExistente == null) {
	            throw new Exception("No existe un usuario con el nombre de usuario proporcionado.");
	        }

	        daoUsuario.delete(usuarioExistente.getUsuarioId());
	    }
	    	//listar usuario
	    public List<Usuario> listarUsuarios() {
	        return daoUsuario.getAll();
	    }
	    //login de usuario validar usuario y contraseña
	    public Usuario login(String nombreUsuario, String contrasenia) throws Exception {
	        if (nombreUsuario == null || nombreUsuario.isEmpty() || contrasenia == null || contrasenia.isEmpty()) {
	            throw new Exception("Debe proporcionar un nombre de usuario y una contraseña válida para iniciar sesión.");
	        }

	        Usuario usuario = daoUsuario.getByNombreUsuario(nombreUsuario);

	        if (usuario == null || !usuario.getContrasenia().equals(contrasenia)) {
	            throw new Exception("Credenciales de inicio de sesión inválidas.");
	        }

	        return usuario;
	    }
	
}