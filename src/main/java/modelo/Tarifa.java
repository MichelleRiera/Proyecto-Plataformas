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

@Entity
public class Tarifa implements Serializable{
	
	 private static final long serialVersionUID= 1L;
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name = "tarifa_id")
		private int tarifaId;
		
		
	    private float costoTarifa;
	    private String tipoVehiculo;
	    


		public Tarifa(int tarifaId, float costoTarifa, String tipoVehiculo) {
			this.tarifaId = tarifaId;
			this.costoTarifa = costoTarifa;
			this.tipoVehiculo = tipoVehiculo;
		}

		public Tarifa() {
		}

		public int getTarifaId() {
			return tarifaId;
		}

		public void setTarifaId(int tarifaId) {
			this.tarifaId = tarifaId;
		}

		public float getCostoTarifa() {
			return costoTarifa;
		}

		public void setCostoTarifa(float costoTarifa) {
			this.costoTarifa = costoTarifa;
		}

		public String getTipoVehiculo() {
			return tipoVehiculo;
		}

		public void setTipoVehiculo(String tipoVehiculo) {
			this.tipoVehiculo = tipoVehiculo;
		}



		@Override
		public String toString() {
			return "Tarifa [tarifaId=" + tarifaId + ", costoTarifa=" + costoTarifa + ", tipoVehiculo=" + tipoVehiculo
					+ "]";
		}
		
		
	    
	    

}
