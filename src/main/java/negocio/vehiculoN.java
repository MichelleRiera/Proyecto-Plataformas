package negocio;

import modelo.Vehiculo;

import java.util.List;

import DAO.vehiculoDao;
import jakarta.ejb.Stateless;

@Stateless
public class vehiculoN {
	 /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private vehiculoDao vehiculoDao;

    public vehiculoN() {
        vehiculoDao = new vehiculoDao();
    }

    public void guardarVehiculo(Vehiculo vehiculo) {
        vehiculoDao.insert(vehiculo);
    }

    public void actualizarVehiculo(Vehiculo vehiculo) {
        Vehiculo vehiculoExistente = vehiculoDao.read(vehiculo.getVehiculoId());
        if (vehiculoExistente == null) {
            // El vehículo no existe en la base de datos.
            throw new IllegalArgumentException("No se encontró el vehículo con el ID: " + vehiculo.getVehiculoId());
        } else {
            if (vehiculo.getPlaca() == null || vehiculo.getPlaca().isEmpty()) {
                // La placa no puede ser nula o vacía.
                throw new IllegalArgumentException("La placa del vehículo no puede ser nula o vacía.");
            }
            vehiculoExistente.setPlaca(vehiculo.getPlaca());
            vehiculoExistente.setTipoVehiculo(vehiculo.getTipoVehiculo());
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
