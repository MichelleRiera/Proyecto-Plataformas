package negocio;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import DAO.personaDao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import modelo.Persona;

@Stateless
public class personaN {
	
	 @Inject
	 private personaDao daoPersona;
	 //guardar persona
	 public void guardarClientes(Persona persona) throws Exception {
		    if (!this.isCedulaValida(persona.getCedula())) {
		        throw new Exception("Cedula incorrecta");
		    }
		    
		    if (!this.isCorreoValido(persona.getCorreo())) {
		        throw new Exception("Correo incorrecto");
		    }

		    Persona existingPerson = daoPersona.getByCedula(persona.getCedula());
		    if (existingPerson != null) {
		        throw new Exception("Ya existe una persona con la misma cedula");
		    }

		    try {
		        daoPersona.insert(persona);
		    } catch (Exception e) {
		        throw new Exception("Error al insertar: " + e.getMessage());
		    }
		}


	    public void guardarClientes(String cedula, String nombre, String apellido, String direccion, String telefono) {
	    }
         //validar cedula
	    private boolean isCedulaValida(String cedula) {
	        // Valida que la cédula tenga 10 dígitos
	        if (cedula.length() != 10) {
	            return false;
	        }

	        // Valida que la cédula esté compuesta solo por dígitos numéricos
	        if (!cedula.matches("[0-9]+")) {
	            return false;
	        }

	        // Aquí puedes agregar otras validaciones específicas para cédulas de Ecuador si es necesario

	        return true;
	    }
	    //Validar Correo
	    private boolean isCorreoValido(String correo) {
	        // Patrón para validar el correo electrónico
	        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

	        Pattern pattern = Pattern.compile(emailPattern);
	        Matcher matcher = pattern.matcher(correo);

	        return matcher.matches();
	    }
        //listar personas
	    public List<Persona> listarPersonas() {
	        return daoPersona.getAll();
	    }
	    //eliminar persona por cedula
	    public void delete(String cedula) {
	        daoPersona.delete(cedula);
	    }
	    //actualizar persona por cedula
	    public void actualizarClientePorCedula(String cedula, String nombre,
	    		String direccion, String telefono,String correo) throws Exception {
	        if (!isCedulaValida(cedula)) {
	            throw new Exception("Cedula incorrecta");
	        }

	        if (!this.isCorreoValido(correo)) {
		        throw new Exception("Correo incorrecto");
		    }
	        Persona personaExistente = daoPersona.getByCedula(cedula);
	        if (personaExistente == null) {
	            throw new EntityNotFoundException("No existe una persona con la cédula proporcionada.");
	        }

	        personaExistente.setNombre(nombre);
	        personaExistente.setDireccion(direccion);
	        personaExistente.setTelefono(telefono);
	        personaExistente.setCorreo(correo);
	        try {
	            daoPersona.update(personaExistente);
	        } catch (Exception e) {
	            throw new Exception("Error al actualizar: " + e.getMessage());
	        }
	    }

}
