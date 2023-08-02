package modelo;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Lugar implements Serializable{
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "lugar_id")
	private int lugarId;
	
	private int numeroLugar;
	
	private String estado;
	private String tipoVehiculo;
	private int piso;
	

	public Lugar(int lugarId,int numeroLugar, String estado,String tipoVehiculo) {
		this.lugarId = lugarId;
		this.numeroLugar = numeroLugar;
		this.estado = estado;
		this.tipoVehiculo=tipoVehiculo;
	}

	public Lugar() {
	}

	public int getLugarId() {
		return lugarId;
	}

	public void setLugarId(int lugarId) {
		this.lugarId = lugarId;
	}


	public int getNumeroLugar() {
		return numeroLugar;
	}

	public void setNumeroLugar(int numeroLugar) {
		this.numeroLugar = numeroLugar;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
        // Agrega una validación para asegurarte de que el estado solo pueda ser "A" o "I"
        if ("A".equals(estado) || "I".equals(estado)) {
            this.estado = estado;
        } else {
            throw new IllegalArgumentException("El estado debe ser 'A' (Activo) o 'I' (Inactivo).");
        }
    }

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}
	
	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	
	@Override
	public String toString() {
		return "Lugar [lugarId=" + lugarId + ", numeroLugar=" + numeroLugar + ", estado=" + estado + ", piso=" + piso
				+ "]";
	}

	
	
	
	
	

}
