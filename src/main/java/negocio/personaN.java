package negocio;

import java.util.List;

import DAO.personaDao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import modelo.Persona;

@Stateless
public class personaN {
	
	 @Inject
	 private personaDao daoPersona;
	 
	 public void guardarClientes(Persona persona) throws Exception {
			if(!this.isCedulaValida(persona.getCedula()))
				throw new Exception("Cedula incorrecta");
			
			if(daoPersona.read(persona.getPersonaid()) == null) {
				try {
					daoPersona.insert(persona);
				}catch(Exception e) {
					throw new Exception("Error al insertar: " + e.getMessage());
				}
			}else {
				try {
					daoPersona.update(persona);
				}catch(Exception e) {
					throw new Exception("Error al actualizar: " + e.getMessage());
				}
			}
		}

	    public void guardarClientes(String cedula, String nombre, String apellido, String direccion, String telefono) {
	        // Implementa la lógica para guardar un cliente utilizando los parámetros proporcionados
	    }

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

	    public List<Persona> listarPersonas() {
	        return daoPersona.getAll();
	    }
	    
	    public void delete(String cedula) {
	        daoPersona.delete(cedula);
	    }
	    public void actualizarClientePorCedula(String cedula, String nombre,  String direccion, String telefono) throws Exception {
	        if (!isCedulaValida(cedula)) {
	            throw new Exception("Cedula incorrecta");
	        }

	        Persona personaExistente = daoPersona.getByCedula(cedula);
	        if (personaExistente == null) {
	            throw new EntityNotFoundException("No existe una persona con la cédula proporcionada.");
	        }

	        personaExistente.setNombre(nombre);
	        personaExistente.setDireccion(direccion);
	        personaExistente.setTelefono(telefono);

	        try {
	            daoPersona.update(personaExistente);
	        } catch (Exception e) {
	            throw new Exception("Error al actualizar: " + e.getMessage());
	        }
	    }

}
