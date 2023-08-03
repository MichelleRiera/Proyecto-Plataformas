package modelo;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vehiculo implements Serializable{
	
	//Se genera el id automaticamente 

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "vehiculo_id")
	private int vehiculoId;
	
	private String placa, tipoVehiculo;
	
	

	public Vehiculo(int vehiculoId, String placa, String tipoVehiculo) {
		this.vehiculoId = vehiculoId;
		this.placa = placa;
		this.tipoVehiculo = tipoVehiculo;
	}
	
	

	public Vehiculo() {
	}



	public int getVehiculoId() {
		return vehiculoId;
	}

	public void setVehiculoId(int vehiculoId) {
		this.vehiculoId = vehiculoId;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	@Override
	public String toString() {
		return "Vehiculo [vehiculoId=" + vehiculoId + ", placa=" + placa + ", tipoVehiculo=" + tipoVehiculo + "]";
	}
	
	
	
	

}
