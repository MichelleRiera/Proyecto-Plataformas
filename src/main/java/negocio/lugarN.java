package negocio;

import modelo.Lugar;
import modelo.Piso;
import DAO.lugarDao;
import DAO.pisoDao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class lugarN {

    @Inject
    private lugarDao lugarDao;

    @Inject
    private pisoDao pisoDao;

    public void guardarLugar(Lugar lugar, int pisoId) {
        if (lugar == null) {
            throw new IllegalArgumentException("El lugar no puede ser nulo.");
        }
        if (lugar.getNumeroLugar() <= 0) {
            throw new IllegalArgumentException("El número de lugar debe ser mayor que 0.");
        }
        if (lugar.getEstado() == null || lugar.getEstado().isEmpty() || (!lugar.getEstado().equals("A") && !lugar.getEstado().equals("I"))) {
            throw new IllegalArgumentException("El estado del lugar debe ser 'A' (activo) o 'I' (inactivo).");
        }

        Piso piso = pisoDao.read(pisoId);
        if (piso == null) {
            throw new IllegalArgumentException("No se encontró el piso con el ID: " + pisoId);
        }

        lugar.setPiso(piso);
        lugarDao.insert(lugar);
    }

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

    public void eliminarLugarPorId(int lugarId) {
        Lugar lugarExistente = lugarDao.read(lugarId);
        if (lugarExistente == null) {
            throw new IllegalArgumentException("No se encontró el lugar con el ID: " + lugarId);
        }

        lugarDao.delete(lugarId);
    }

    public List<Lugar> listarLugares() {
        return lugarDao.getAll();
    }
}
