package negocio;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import modelo.Tarifa;

import java.util.List;

import DAO.tarifaDao;

@Stateless
public class tarifaN {
	    @Inject
	    private tarifaDao tarifaDao;
	    //guardar tarifa
	    public void guardarTarifa(Tarifa tarifa) {
	        if (tarifa == null) {
	            throw new IllegalArgumentException("La tarifa no puede ser nula.");
	        }
	        tarifaDao.insert(tarifa);
	    }
	    	//actualizar tarifa por id
	    public void actualizarTarifaPorId(int tarifaId, float nuevoCosto, String nuevoTipoVehiculo) {
	        if (nuevoCosto <= 0) {
	            throw new IllegalArgumentException("El costo de la tarifa debe ser mayor que cero.");
	        }
	        Tarifa tarifaExistente = tarifaDao.read(tarifaId);
	        if (tarifaExistente == null) {
	            throw new IllegalArgumentException("No se encontró la tarifa con el ID: " + tarifaId);
	        } else {
	            tarifaExistente.setCostoTarifa(nuevoCosto);
	            tarifaExistente.setTipoVehiculo(nuevoTipoVehiculo);
	            tarifaDao.update(tarifaExistente);
	        }
	    }
	    //eliminar tarifa por id
	    public void eliminarTarifaPorId(int tarifaId) {
	        if (tarifaId <= 0) {
	            throw new IllegalArgumentException("El ID de la tarifa debe ser mayor que cero.");
	        }
	        Tarifa tarifaExistente = tarifaDao.read(tarifaId);
	        if (tarifaExistente == null) {
	            throw new IllegalArgumentException("No se encontró la tarifa con el ID: " + tarifaId);
	        }
	        tarifaDao.delete(tarifaId);
	    }
	    //listar tarifa
	    public List<Tarifa> listarTarifas() {
	        return tarifaDao.getAll();
	    }

}
