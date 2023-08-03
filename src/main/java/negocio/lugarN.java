package negocio;

import modelo.Lugar;
import modelo.Ticket;
import DAO.lugarDao;


import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class lugarN {

    @Inject
    private lugarDao lugarDao;


    //guardar lugar
    public void guardarLugar(Lugar lugar) {
        if (lugar == null) {
            throw new IllegalArgumentException("El lugar no puede ser nulo.");
        }
        if (lugar.getNumeroLugar() <= 0) {
            throw new IllegalArgumentException("El número de lugar debe ser mayor que 0.");
        }
        if (lugar.getEstado() == null || lugar.getEstado().isEmpty() || (!lugar.getEstado().equals("A") && !lugar.getEstado().equals("I"))) {
            throw new IllegalArgumentException("El estado del lugar debe ser 'A' (activo) o 'I' (inactivo).");
        }

        // Obtener la lista de lugares existentes con el mismo número de lugar y piso para el tipo de vehículo dado
        List<Lugar> lugaresExistentes = lugarDao.getLugaresByNumeroLugarAndPisoAndTipoVehiculo(lugar.getNumeroLugar(), lugar.getPiso());

        // Validar si ya existe un lugar con el mismo número de lugar y piso para el tipo de vehículo dado
        if (!lugaresExistentes.isEmpty()) {
            throw new IllegalArgumentException("Ya existe un lugar con el mismo número de lugar y piso para el tipo de vehículo: " );
        }

        lugarDao.insert(lugar);
    }

     //actualizar lugar por id
    public void actualizarLugarPorId(int lugarId, int nuevoNumeroLugar, String nuevoEstado) {
        if (nuevoNumeroLugar <= 0) {
            throw new IllegalArgumentException("El número de lugar debe ser mayor que 0.");
        }
        if (nuevoEstado == null || nuevoEstado.isEmpty() || (!nuevoEstado.equals("A") && !nuevoEstado.equals("I"))) {
            throw new IllegalArgumentException("El estado del lugar debe ser 'A' (activo) o 'I' (inactivo).");
        }

        Lugar lugarExistente = lugarDao.read(lugarId);
        if (lugarExistente == null) {
            throw new IllegalArgumentException("No se encontró el lugar con el ID: " + lugarId);
        }

        lugarExistente.setNumeroLugar(nuevoNumeroLugar);
        lugarExistente.setEstado(nuevoEstado);
        lugarDao.update(lugarExistente);
    }
    //eliminar lugar por id
    public void eliminarLugarPorId(int lugarId) throws Exception {
        Lugar lugar = lugarDao.read(lugarId);
        if (lugar == null) {
            throw new Exception("El ticket con el ID " + lugarId + " no existe.");
        }
        lugarDao.delete(lugarId);
    }

    //listar lugar por id
    public List<Lugar> listarLugares() {
        return lugarDao.getAll();
    }
}
