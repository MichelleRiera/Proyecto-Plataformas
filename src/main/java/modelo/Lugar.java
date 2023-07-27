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
public class Lugar implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "lugar_id")
	private int lugarId;
	
	private int numeroPiso, numeroLugar;
	
	private String estado;
	
    @OneToMany
	@JoinColumn(name = "tarifa_id")
	private Set<Ticket> ticket;

	public Lugar(int lugarId, int numeroPiso, int numeroLugar, String estado) {
		this.lugarId = lugarId;
		this.numeroPiso = numeroPiso;
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

	public int getNumeroPiso() {
		return numeroPiso;
	}

	public void setNumeroPiso(int numeroPiso) {
		this.numeroPiso = numeroPiso;
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

	@Override
	public String toString() {
		return "Lugar [lugarId=" + lugarId + ", numeroPiso=" + numeroPiso + ", numeroLugar=" + numeroLugar + ", estado="
				+ estado + ", ticket=" + ticket + "]";
	}
	
	
	
	

}
