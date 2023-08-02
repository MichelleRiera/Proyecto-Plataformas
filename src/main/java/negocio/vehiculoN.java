package negocio;

import modelo.Vehiculo;

import java.util.List;

import DAO.vehiculoDao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class vehiculoN {
	 /**
     * 
     */
 
	@Inject
    private vehiculoDao vehiculoDao;

   

	public void guardarVehiculo(Vehiculo vehiculo) {
        // Verificar si ya existe un vehículo con la misma placa
        Vehiculo vehiculoExistente = vehiculoDao.getByPlaca(vehiculo.getPlaca());

        if (vehiculoExistente != null) {
            // Si ya existe un vehículo con la misma placa, lanzar una excepción
            throw new IllegalArgumentException("Ya existe un vehículo registrado con la placa: " + vehiculo.getPlaca());
        }

        // Si la placa no está registrada, procedemos a guardar el vehículo
        vehiculoDao.insert(vehiculo);
    }

    public void actualizarVehiculo(String placa, Vehiculo vehiculoActualizado) {
        Vehiculo vehiculoExistente = vehiculoDao.getByPlaca(placa);

        if (vehiculoExistente == null) {
            // El vehículo no existe en la base de datos.
            throw new IllegalArgumentException("No se encontró ningún vehículo con la placa: " + placa);
        } else {
            if (vehiculoActualizado.getPlaca() == null || vehiculoActualizado.getPlaca().isEmpty()) {
                // La placa no puede ser nula o vacía.
                throw new IllegalArgumentException("La placa del vehículo no puede ser nula o vacía.");
            }
            
            // Actualizar los datos del vehículo existente con los datos del vehículo actualizado.
            vehiculoExistente.setPlaca(vehiculoActualizado.getPlaca());
            vehiculoExistente.setTipoVehiculo(vehiculoActualizado.getTipoVehiculo());

            // Realizar la actualización en la base de datos.
            vehiculoDao.update(vehiculoExistente);
        }
    }


    public void eliminarVehiculoPorPlaca(String placa) {
        List<Vehiculo> vehiculos = vehiculoDao.getAll();
        boolean encontrado = false;
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getPlaca().equalsIgnoreCase(placa)) {
                vehiculoDao.delete(vehiculo.getVehiculoId());
                encontrado = true;
                break; // Se encontró el vehículo con la placa, se eliminó y salimos del bucle.
            }
        }
        if (!encontrado) {
            // No se encontró ningún vehículo con esa placa.
            throw new IllegalArgumentException("No se encontró ningún vehículo con la placa: " + placa);
        }
    }

    public List<Vehiculo> listarVehiculos() {
        return vehiculoDao.getAll();
    }

}
