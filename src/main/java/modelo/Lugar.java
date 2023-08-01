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
	private int piso;
	

	
    @OneToMany
	@JoinColumn(name = "tarifa_id")
	private Set<Ticket> ticket;

	public Lugar(int lugarId,int numeroLugar, String estado) {
		this.lugarId = lugarId;
		this.numeroLugar = numeroLugar;
		this.estado = estado;
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
		this.estado = estado;
	}

	public Set<Ticket> getTicket() {
		return ticket;
	}

	public void setTicket(Set<Ticket> ticket) {
		this.ticket = ticket;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	@Override
	public String toString() {
		return "Lugar [lugarId=" + lugarId + ", numeroLugar=" + numeroLugar + ", estado=" + estado + ", piso=" + piso
				+ ", ticket=" + ticket + "]";
	}

	
	
	
	
	

}
