package negocio;

import java.util.List;

import modelo.Piso;
import DAO.pisoDao;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class pisoN {

    @Inject
    private pisoDao pisoDao;

    public void guardarPiso(Piso piso) {
        if (piso.getNumeroPiso() <= 0) {
            throw new IllegalArgumentException("El número de piso debe ser mayor que 0.");
        }
        if (piso.getEstado() == null || piso.getEstado().isEmpty()) {
            throw new IllegalArgumentException("El estado del piso no puede ser nulo o vacío.");
        }
        pisoDao.insert(piso);
    }

    public void actualizarPisoPorNumeroPiso(int numeroPiso, int nuevoNumeroPiso, String nuevoEstado) {
        if (nuevoNumeroPiso <= 0) {
            throw new IllegalArgumentException("El número de piso debe ser mayor que 0.");
        }
        if (nuevoEstado == null || nuevoEstado.isEmpty()) {
            throw new IllegalArgumentException("El estado del piso no puede ser nulo o vacío.");
        }

        // Buscar el piso por el número de piso
        Piso pisoExistente = pisoDao.getPisoPorNumeroPiso(numeroPiso);

        if (pisoExistente == null) {
            throw new IllegalArgumentException("No se encontró el piso con el número de piso: " + numeroPiso);
        } else {
            pisoExistente.setNumeroPiso(nuevoNumeroPiso);
            pisoExistente.setEstado(nuevoEstado);
            pisoDao.update(pisoExistente);
        }
    }

    public void eliminarPisoPorNumeroPiso(int numeroPiso) {
        // Buscar el piso por el número de piso
        Piso pisoExistente = pisoDao.getPisoPorNumeroPiso(numeroPiso);

        if (pisoExistente == null) {
            throw new IllegalArgumentException("No se encontró el piso con el número de piso: " + numeroPiso);
        } else {
            pisoDao.delete(pisoExistente.getPisoId());
        }
    }

    public List<Piso> listarPisos() {
        return pisoDao.getAll();
    }
}
