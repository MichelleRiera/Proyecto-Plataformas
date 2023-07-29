package modelo;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;

@Entity
public class Piso {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "lugar_id")
	private int pisoId;
	
	private int numeroPiso;
	
    @Pattern(regexp = "[AI]", message = "El estado debe ser 'A' (Activo) o 'I' (Inactivo).")
	private String estado;
	
    @OneToMany
	@JoinColumn(name = "lugar_id")
	private Set<Lugar> lugar;

	public Piso(int pisoId, int numeroPiso, String estado) {
		this.pisoId = pisoId;
		this.numeroPiso = numeroPiso;
		this.estado = estado;
	}

	public Piso() {
	}

	public int getPisoId() {
		return pisoId;
	}

	public void setPisoId(int pisoId) {
		this.pisoId = pisoId;
	}

	public int getNumeroPiso() {
		return numeroPiso;
	}

	public void setNumeroPiso(int numeroPiso) {
		this.numeroPiso = numeroPiso;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Set<Lugar> getLugar() {
		return lugar;
	}

	public void setLugar(Set<Lugar> lugar) {
		this.lugar = lugar;
	}

	@Override
	public String toString() {
		return "Piso [pisoId=" + pisoId + ", numeroPiso=" + numeroPiso + ", estado=" + estado + ", lugar=" + lugar
				+ "]";
	}
    
    
    
    
}
